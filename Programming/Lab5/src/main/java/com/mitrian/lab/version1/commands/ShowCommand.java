package com.mitrian.lab.version1.commands;

import com.mitrian.lab.version1.LinkedListCollection;
import com.mitrian.lab.version1.source.Worker;
import com.mitrian.lab.version1.utils.AbstractCommand;
import com.mitrian.lab.version1.utils.Printer;

import java.util.List;

public class ShowCommand extends AbstractCommand {
    private String descriptor = "вывести все элементы коллекции";
    private Printer printer;
    private String arguments;
    private List<Worker> workersCollection;

    public ShowCommand(List<Worker> workersCollection, String arguments, Printer printer){
        this.arguments = arguments;
        this.workersCollection = workersCollection;
        this.printer = printer;
    }

    @Override
    public boolean execute(){
        try{
            if ("".equals(arguments)){
                if (LinkedListCollection.workersCollection.size() == 0){
                    printer.print("Коллекция пуста"+"\n");
                    return true;
                } else{
                    for (int i = 0; i < LinkedListCollection.workersCollection.size(); i++){
                        System.out.println(LinkedListCollection.workersCollection.get(i).toString());
                    }
                    return true;
                }
            } else {
                printer.print("Не указывайте аргументы при использовании данной команды");
                return false;
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
