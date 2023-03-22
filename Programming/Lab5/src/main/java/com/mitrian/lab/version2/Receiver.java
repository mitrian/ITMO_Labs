package com.mitrian.lab.version2;

import com.mitrian.lab.version1.source.Status;
import com.mitrian.lab.version1.source.Worker;
import com.mitrian.lab.version2.commands.managers.WorkerInitializer;
import com.mitrian.lab.version2.utils.ConsolePrinter;
import com.mitrian.lab.version2.utils.Printer;
import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Receiver {

    private List<Worker> workersCollection;

    private Printer printer = new ConsolePrinter();

    private final LocalDateTime creationDate;

    public Receiver() {
        workersCollection = new LinkedList();
        creationDate = LocalDateTime.now();
    }

    public String help(){
        return "change it";
    }

    public String info(){
        return "тип коллекции: LinkedList; дата инициализации " + creationDate + "; количество элементов: "+workersCollection.size();
    }

    public String show() {
        String localOutput = "";
        if (workersCollection.size() == 0) {
            return "Коллекция пуста" + "\n";
        } else {
            for (int i = 0; i < LinkedListCollection.workersCollection.size(); i++) {
                localOutput = localOutput + LinkedListCollection.workersCollection.get(i).toString();
            }
            return localOutput;
        }
    }

    public void add() throws ForcedShutdownException {
        workersCollection.add(new WorkerInitializer(printer).createWorkerObject());
        }

//    public void update(Long id, String name, Coordinates coordinates, Float salary, Date startDate,
//                LocalDateTime endDate, Status status, Person person){
//            for (int i = 0; i<LinkedListCollection.workersCollection.size(); i++){
//                if (LinkedListCollection.workersCollection.get(i).getId()==id){
//                    Worker worker = workersCollection.get(i);
//                    worker.s
//                    break;
//                }
//        }
    public void remove_by_id(Long id){
        for (int i = 0; i < workersCollection.size(); i++) {
            if (workersCollection.get(i).getId() == id) {
                workersCollection.remove(i);
                break;
            }
        }
    }

    public void clear(){
        workersCollection.clear();
    }
//
//    public save(){
//
//    }

 // public executeScript(){}

    public void exit(){
        System.exit(0);
    }

    public void removeFirst(){
        if (LinkedListCollection.workersCollection.size()>0) {
            LinkedListCollection.workersCollection.remove(0);
        }
    }

    public String removeHead(){
        String localWorker = workersCollection.get(0).toString();
        workersCollection.remove(0);
        return localWorker;
    }

    public void removeGreater(Worker worker) {
        for (int i = 0; i < workersCollection.size(); i++) {
            if (workersCollection.get(i).getId() < worker.getId()) {
                workersCollection.remove(i);
            }
        }
    }

    public String minByName(){
        long localNameLength = 999999999999999999l;
        int localIndex = 0;
        String localWorker;
        for (int i = 0; i < workersCollection.size(); i++) {
            if (workersCollection.get(i).getName().length() < localNameLength) {
                localNameLength = workersCollection.get(i).getName().length();
                localIndex = i;
            }
        }
        localWorker = workersCollection.get(localIndex).toString();
        workersCollection.remove(localIndex);
        return localWorker;
    }

    public String filterByStatus(Status status){
        String localWorkers = "";
        for (int i = 0; i < workersCollection.size(); i++) {
            if (workersCollection.get(i).getStatus().equals(status)) {
                localWorkers = workersCollection.get(i).toString()+"\n";
            }
        }
        if ("".equals(localWorkers)){
            return "Таких элементов не существует";
        } else {
            return localWorkers;
        }
    }

    //public String printUniquePerson(){}
}