package com.mitrian.lab;

import com.mitrian.lab.source.Worker;

import java.util.LinkedList;
import java.util.List;

public class WorkersCollectionReceiver {
    private final List<Worker> workers = new LinkedList<Worker>();

    public String show(){
        StringBuilder sb = new StringBuilder();
        for (Worker worker: workers){
            sb.append(worker.toString()).append('\n');
        }
        return sb.toString();
    }
}
