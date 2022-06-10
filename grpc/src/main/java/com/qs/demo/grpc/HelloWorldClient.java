package com.qs.demo.grpc;

import cn.hutool.core.io.FileUtil;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HelloWorldClient {
    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;
    private final GreeterGrpc.GreeterStub greeterStub;

    public HelloWorldClient(String host, int port) {
        //usePlaintext表示明文传输，否则需要配置ssl
        //channel  表示通信通道
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        //存根
        this.blockingStub = GreeterGrpc.newBlockingStub(this.channel);
        this.greeterStub = GreeterGrpc.newStub(this.channel);
    }

    public void shutdown() throws InterruptedException {
        this.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void test(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(ByteString.copyFrom(SerializationUtil.serializer(name))).build();
        HelloReply helloReply = blockingStub.sayHello(request);
        System.out.println(SerializationUtil.deserializer(helloReply.getMessage().toByteArray(), String.class));
    }

    public String sendFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        String value = null;
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<FileInfo> requestObserver = greeterStub.sendFile(new StreamObserver<Info>() {
            @Override
            public void onNext(Info info) {
                System.out.println("" + info.getMsg());
            }

            @Override
            public void onError(Throwable throwable) {
                latch.countDown();
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            long size = 2048;
            byte[] buff = new byte[Math.toIntExact(size)];
            long max = file.length() / size;
            long maxSize = file.length() % size;
            int num = 0;
            int len;
            while ((len = is.read(buff)) != -1) {
                if (num + 1 > max) {
                    size = maxSize;
                }
                requestObserver.onNext(FileInfo.newBuilder().setName(file.getName()).setIndex(num).setSize(size)
                        .setArrs(ByteString.copyFrom(buff)).build());
                num++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            requestObserver.onError(e);
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        requestObserver.onCompleted();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1", 50051);
        client.sendFile("D:\\IdeaProjects\\ht-data-generator\\upload\\task\\2_1518139567796912128\\result_file\\trajectory-1.out");
//        client.test("aaa");
        try {
            client.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
