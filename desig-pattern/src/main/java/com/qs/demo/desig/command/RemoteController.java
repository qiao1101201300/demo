package com.qs.demo.desig.command;

public class RemoteController {
    private Command[] onCommands = new Command[2];
    private Command[] offCommands = new Command[2];
    private Command undoCommand;

    public void setCommand(int nu, Command onCommands, Command offCommands) {
        this.onCommands[nu] = onCommands;
        this.offCommands[nu] = offCommands;
    }

    public void onButton(int nu) {
        offCommands[nu].execute();
        undoCommand = onCommands[nu];
    }

    public void offButton(int nu) {
        onCommands[nu].execute();
        undoCommand = onCommands[nu];
    }

    public void undo() {
        if (undoCommand != null) {
            undoCommand.undo();
        }
    }
}
