package com.mitrian.lab.commands;

import com.mitrian.lab.LinkedListCollection;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class ShowCommand extends AbstractCommand {
    private String descriptor = "вывести все элементы коллекции";
    static Printer printer = new ConsolePrinter();

    @Override
    public boolean execute(String args) {
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
            if (LinkedListCollection.workersCollection.size() == 0){
                printer.print("Коллекция пуста"+"\n");
                return true;
            } else{
                for (int i = 0; i < LinkedListCollection.workersCollection.size(); i++){
                    System.out.println(LinkedListCollection.workersCollection.get(i).toString());
                }
                return true;
            }

        } catch (Exception e){
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

}
