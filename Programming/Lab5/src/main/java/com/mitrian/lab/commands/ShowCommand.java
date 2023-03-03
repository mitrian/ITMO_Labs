package com.mitrian.lab.commands;

import com.mitrian.lab.Command;
import com.mitrian.lab.WorkersCollectionReceiver;

public class ShowCommand implements Command {
    private WorkersCollectionReceiver receiver;

    public ShowCommand(WorkersCollectionReceiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(){
        System.out.println(this.receiver.show());
    }
}
