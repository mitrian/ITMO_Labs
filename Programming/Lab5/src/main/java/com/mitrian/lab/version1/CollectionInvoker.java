package com.mitrian.lab.version1;

public class CollectionInvoker {
    public Command command;

    public CollectionInvoker(Command command){
        this.command = command;
    }

    public void execute(){
        this.command.execute();
    }

}
