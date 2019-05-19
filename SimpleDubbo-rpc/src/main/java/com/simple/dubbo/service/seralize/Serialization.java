public interface Serialization {

   byte[] serialize(Object obj) throws IOException;

   <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException;
}
