package com.simple.dubbo.service;

/**
 * @author chengzhengzheng
 * @date 2019-05-12
 **/
public class RegisterManagerComponent implements RegisterManagerService {
    @Override
    public void publish(ServiceMetadata serviceMetadata) {
        //1. 获取发布服务地址
        final String serviceUri = serviceMetadata.getServiceUri();
        final String targetUri = "";//TODO
        //2. 异步定时发布服务
        publishService(serviceUri,targetUri);
    }

    private void publishService(String serviceUri, String targetUri) {
        //使用 ScheduledExecutorService 定义发布服务

    }

    @Override
    public void subscribe(ServiceMetadata data) {

    }

    class PublishTask implements Runnable{
        private final String serviceUri;
        private final String targetUri;

        public PublishTask(String serviceUri, String targetUri) {
            this.serviceUri = serviceUri;
            this.targetUri = targetUri;
        }

        @Override
        public void run() {
            //TODO 调用zookeeper或者redis发布服务
        }
    }
}
