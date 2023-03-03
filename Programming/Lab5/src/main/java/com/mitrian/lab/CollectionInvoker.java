package com.mitrian.lab;

public class CollectionInvoker {
    public Command command;

    public CollectionInvoker(Command command){
        this.command = command;
    }

    public void execute(){
        this.command.execute();
    }

}
