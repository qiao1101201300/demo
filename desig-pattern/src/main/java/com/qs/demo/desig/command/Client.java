package com.qs.demo.desig.command;

public class Client {

    public static void main(String[] args) {
        LightReceiver lightReceiver = new LightReceiver();
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        RemoteController remoteController = new RemoteController();
        remoteController.setCommand(0, lightOnCommand, lightOffCommand);
        System.out.println("------------------------------------");
        System.out.println("按下打开按钮");
        remoteController.onButton(0);
        System.out.println("按下关闭按钮");
        remoteController.offButton(0);
        System.out.println("按下撤销按钮");
        remoteController.undo();
        TVReceiver tvReceiver = new TVReceiver();
        TVOnCommand tvOnCommand = new TVOnCommand(tvReceiver);
        TVOffCommand tvOffCommand = new TVOffCommand(tvReceiver);
        remoteController.setCommand(1, tvOnCommand, tvOffCommand);
        System.out.println("------------------------------------");
        System.out.println("按下打开按钮");
        remoteController.onButton(1);
        System.out.println("按下关闭按钮");
        remoteController.offButton(1);
        System.out.println("按下撤销按钮");
        remoteController.undo();
    }
}
