package com.simple.dubbo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author chengzhengzheng
 * @date 2019-05-18
 */
public class DebbyServiceLoader<S> implements Iterable<S> {

    private static final String PREFIX = "META-INF/services/";

    // The class or interface representing the service being loaded
    private final Class<S> service;

    // The class loader used to locate, load, and instantiate providers
    private final ClassLoader loader;

    // The access control context taken when the ServiceLoader is created
    private final AccessControlContext acc;

    // Cached providers, in instantiation order
    private LinkedHashMap<String, S> providers = new LinkedHashMap<>();

    // The current lazy-lookup iterator
    private LazyIterator lookupIterator;

    public DebbyServiceLoader(Class<S> srv, ClassLoader cl) {
        this.service = Objects.requireNonNull(srv, "Service interface cannot be null");
        this.loader = (cl == null) ? ClassLoader.getSystemClassLoader() : cl;
        this.acc = (System.getSecurityManager() != null) ? AccessController.getContext() : null;
        reload();
    }

    private void reload() {
        providers.clear();
        lookupIterator = new LazyIterator(service, loader);
    }

    public static <S> DebbyServiceLoader<S> load(Class<S> service) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return DebbyServiceLoader.load(service, cl);
    }

    private static <S> DebbyServiceLoader<S> load(Class<S> service, ClassLoader classLoader) {

        return new DebbyServiceLoader<>(service, classLoader);
    }

    @Override
    public Iterator<S> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super S> action) {

    }

    public S find(String implName) {
        for (S s : providers.values()) {
            SpiMetadata spi = s.getClass().getAnnotation(SpiMetadata.class);
            if (spi != null && spi.name().equals(implName)) {
                return s;
            }
        }

        while (lookupIterator.hasNext()) {
            Pair<String, Class<S>> e = lookupIterator.next();
            String name = e.getFirst();
            Class<S> cls = e.getSecond();
            SpiMetadata spi = cls.getAnnotation(SpiMetadata.class);
            if (spi != null && spi.name().equalsIgnoreCase(implName)) {
                try {
                    S provider = service.cast(cls.newInstance());
                    providers.put(name, provider);
                    return provider;
                } catch (Throwable x) {
                    throw fail(service, "provider " + name + " could not be instantiated", x);
                }
            }
        }
        throw fail(service, "provider " + implName + " could not be found");
    }

    @Override
    public Spliterator<S> spliterator() {
        return null;
    }

    private class LazyIterator implements Iterator<Pair<String, Class<S>>> {

        Class<S> service;
        ClassLoader loader;
        String nextName;
        Enumeration<URL> configs = null;
        Iterator<String> pending = null;


        public LazyIterator(Class<S> service, ClassLoader loader) {
            this.service = service;
            this.loader = loader;
        }

        @Override
        public boolean hasNext() {
            if (acc == null) {
                return hasNextService();
            }
            PrivilegedAction<Boolean> action = () -> hasNextService();
            return AccessController.doPrivileged(action, acc);
        }

        @Override
        public Pair<String, Class<S>> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String name = nextName;
            Class<?> cls;
            try {
                cls = Class.forName(name, false, loader);
            } catch (ClassNotFoundException e) {
                throw fail(service, "provider " + name + " not found");
            }
            if (!service.isAssignableFrom(cls)) {
                throw fail(service, "provider " + name + " not a subtype");
            }
            return Pair.of(name, (Class<S>) cls);
        }

        private boolean hasNextService() {
            if (nextName != null) {
                return true;
            }
            if (configs == null) {
                try {
                    String fullName = PREFIX + service.getName();
                    if (loader == null) {
                        configs = ClassLoader.getSystemResources(fullName);
                    } else {
                        configs = loader.getResources(fullName);
                    }
                } catch (IOException e) {
                    fail(service, "Error locating configuration files", e);
                }
            }

            while ((pending == null) || !pending.hasNext()) {
                if (!configs.hasMoreElements()) {
                    return false;
                }
                pending = parse(service, configs.nextElement());
            }
            nextName = pending.next();
            return false;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Iterator<String> parse(Class<S> service, URL u) {
        InputStream in = null;
        BufferedReader r = null;
        ArrayList<String> names = new ArrayList<>();
        try {
            in = u.openStream();
            r = new BufferedReader(new InputStreamReader(in, "utf-8"));
            int lc = 1;
            while ((lc = parseLine(service, u, r, lc, names)) >= 0) ;
        } catch (IOException x) {
            fail(service, "Error reading configuration file", x);
        } finally {
            try {
                if (r != null) r.close();
                if (in != null) in.close();
            } catch (IOException y) {
                fail(service, "Error closing configuration file", y);
            }
        }
        return names.iterator();
    }

    private static ServiceConfigurationError fail(Class<?> service, String msg, Throwable cause) {
        return new ServiceConfigurationError(service.getName() + ": " + msg, cause);
    }


    private static ServiceConfigurationError fail(Class<?> service, String msg) {
        return new ServiceConfigurationError(service.getName() + ": " + msg);
    }

    private static ServiceConfigurationError fail(Class<?> service, URL url, int line, String msg) {
        return fail(service, url + ":" + line + ": " + msg);
    }

    private int parseLine(Class<S> service, URL u, BufferedReader r, int lc, List<String> names) throws IOException {

        String ln = r.readLine();
        if (ln == null) {
            return -1;
        }
        int ci = ln.indexOf('#');
        if (ci >= 0) ln = ln.substring(0, ci);
        ln = ln.trim();
        int n = ln.length();
        if (n != 0) {
            if ((ln.indexOf(' ') >= 0) || (ln.indexOf('\t') >= 0))
                fail(service, u, lc, "Illegal configuration-file syntax");
            int cp = ln.codePointAt(0);
            if (!Character.isJavaIdentifierStart(cp))
                fail(service, u, lc, "Illegal provider-class name: " + ln);
            for (int i = Character.charCount(cp); i < n; i += Character.charCount(cp)) {
                cp = ln.codePointAt(i);
                if (!Character.isJavaIdentifierPart(cp) && (cp != '.'))
                    fail(service, u, lc, "Illegal provider-class name: " + ln);
            }
            if (!providers.containsKey(ln) && !names.contains(ln))
                names.add(ln);
        }
        return lc + 1;

    }

    @Override
    public String toString() {
       return "com.simple.debby.util.MyServiceLoader[" + service.getName() + "]";
    }
}
