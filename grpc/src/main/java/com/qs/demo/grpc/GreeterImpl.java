package com.qs.demo.grpc;

import io.grpc.stub.StreamObserver;

public class GreeterImpl extends com.qs.demo.grpc.GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(com.qs.demo.grpc.HelloRequest request, StreamObserver<com.qs.demo.grpc.HelloReply> responseObserver) {
        com.qs.demo.grpc.HelloReply reply = com.qs.demo.grpc.HelloReply.newBuilder().setMessage(request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
