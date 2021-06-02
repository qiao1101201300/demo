package com.qs.demo.grpc;

import cn.hutool.core.io.FileUtil;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class HelloWorldClient {
    private final ManagedChannel channel;
    private final com.qs.demo.grpc.GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(String host, int port) {
        //usePlaintext表示明文传输，否则需要配置ssl
        //channel  表示通信通道
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        //存根
        this.blockingStub = com.qs.demo.grpc.GreeterGrpc.newBlockingStub(this.channel);
    }

    public void shutdown() throws InterruptedException {
        this.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void test(String name) {
        com.qs.demo.grpc.HelloRequest request = com.qs.demo.grpc.HelloRequest.newBuilder().setName(ByteString.copyFrom(SerializationUtil.serializer(name))).build();
        com.qs.demo.grpc.HelloReply helloReply = blockingStub.sayHello(request);
        System.out.println(SerializationUtil.deserializer(helloReply.getMessage().toByteArray(),String.class));
    }

    public static void main(String[] args) {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1", 50051);
        String s = FileUtil.readUtf8String("C:\\Users\\qiaoshen\\Desktop\\新建文本文档 (4).txt");
        client.test(s);
    }
}
