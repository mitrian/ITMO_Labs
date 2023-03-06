package com.mitrian.lab.commands;

import com.mitrian.lab.Command;
import com.mitrian.lab.LinkedListCollection;
import com.mitrian.lab.WorkersCollectionReceiver;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class ShowCommand extends AbstractCommand {
    private String descriptor = "вывести все элементы коллекции";
    static Printer printer = new ConsolePrinter();

    @Override
    public boolean execute(String[] args) {
        printer.print("Не указывайте аргументы при использовании данной команды");
        return false;
    }

    //    private WorkersCollectionReceiver receiver;
//
//    public ShowCommand(WorkersCollectionReceiver receiver){
//        this.receiver = receiver;
//    }
//    @Override
//    public void execute(){
//        System.out.println(this.receiver.show());
//    }
    @Override
    public boolean execute(){
        try{
            for (int i = 0; i < LinkedListCollection.WorkersCollection.size(); i++){
                printer.print(LinkedListCollection.WorkersCollection.get(i).toString());
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

}
