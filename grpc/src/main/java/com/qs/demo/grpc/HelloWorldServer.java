package com.qs.demo.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class HelloWorldServer {

    //定义端口
    private final int port = 50051;
    //服务
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();
        Runtime.getRuntime().removeShutdownHook(new Thread(() -> {
            System.err.println("------shutting down gRPC server since JVM is shutting down-------");
            this.stop();
            System.err.println("------server shut down------");
        }));
    }

    //stop服务
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    //server阻塞到程序退出
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }

}
