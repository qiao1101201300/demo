package com.qs.demo.grpc;

import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GreeterImpl extends com.qs.demo.grpc.GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(com.qs.demo.grpc.HelloRequest request, StreamObserver<com.qs.demo.grpc.HelloReply> responseObserver) {
        com.qs.demo.grpc.HelloReply reply = com.qs.demo.grpc.HelloReply.newBuilder().setMessage(request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<FileInfo> sendFile(StreamObserver<Info> responseObserver) {
        System.out.println("send File");
        return new StreamObserver<FileInfo>() {

            File file = null;
            RandomAccessFile writeFile = null;

            @Override
            public void onNext(FileInfo fileInfo) {
                System.out.println(fileInfo.getName());
                System.out.println(fileInfo.getIndex());
                try {
                    file = new File(System.getProperty("user.dir") + File.separator + fileInfo.getName());
                    writeFile = new RandomAccessFile(file, "rw");
                    writeFile.seek((long) fileInfo.getIndex() * fileInfo.getArrs().size());
                    writeFile.write(fileInfo.getArrs().toByteArray(), 0, (int) fileInfo.getSize());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    if (writeFile != null) {
                        try {
                            writeFile.close();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(Info.newBuilder().setMsg(file.getAbsolutePath()).build());
                responseObserver.onCompleted();
            }
        };
    }
}
