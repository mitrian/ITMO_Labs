package com.mitrian.lab.server.collection;

import com.mitrian.lab.common.data.Status;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exceptions.impl.collection.CollectionEmptyException;
import com.mitrian.lab.common.exceptions.impl.collection.IdUnavailableException;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class CollectionImpl implements Collection<Worker> {

    private final List<Worker> workers;

  //  private final File file;

    private LocalDate creationDate = LocalDate.now();

    public CollectionImpl(){
        workers = new LinkedList<>();
     //   this.file = file;
    }

    @Override
    public List<Worker> getAllElements() {
        return workers;
    }

    @Override
    public void add(Worker item) {
        workers.add(item);
    }

    @Override
    public void update(Integer id, Worker item) throws CollectionException {
        for (Worker worker: workers){
            if (id.compareTo(worker.getId()) == 0){
                worker.setName(item.getName());
                worker.setCoordinates(item.getCoordinates());
                worker.setCreationDate();
                worker.setSalary(item.getSalary());
                worker.setStartDate(item.getStartDate());
                worker.setEndDate(item.getEndDate());
                worker.setStatus(item.getStatus());
                worker.setPerson(item.getPerson());
                return;
            }
        }
        throw new IdUnavailableException("Такого id не существует");
    }

    @Override
    public void remove(Integer id) throws CollectionException {
        Iterator<Worker> iterator = workers.iterator();
        while (iterator.hasNext()){
            Worker worker = iterator.next();
            if (worker.getId().compareTo(id) == 0){
                iterator.remove();
                return;
            }
        }
        throw new IdUnavailableException("Такого id не существует");
    }

    @Override
    public void clear() {
        workers.clear();
    }

    @Override
    public void save() {
//        TODO: implement save method

    }

    @Override
    public void removeFirst() throws CollectionException{
        if (workers.size() != 0){
            workers.remove(0);
        } else {
            throw new CollectionEmptyException("В коллекции нет первого элемента");
        }
    }

    @Override
    public Optional<Worker> removeHead() throws CollectionException {
        if (workers.size() != 0){
            Optional<Worker> worker = Optional.ofNullable(workers.get(0));
            workers.remove(0);
            return worker;
        } else {
            throw new CollectionEmptyException("В коллекции нет первого элемента");
        }
    }

    @Override
    public void removeGreater(Worker item) {
        workers.removeIf(worker -> worker.compareTo(item) > 0);
    }

    @Override
    public Optional<Worker> getMinByName(){
        return Optional.ofNullable(Collections.min(workers, Comparator.comparing(Worker::getName)));
    }

    @Override
    public List<Worker> filterByStatus(Status status) {
        List<Worker> workersByStatus = new LinkedList<>();

        for (Worker worker : workers) {
            if (worker.getStatus() == status) {
                workersByStatus.add(worker);
            }
        }
        return workersByStatus;
    }

    @Override
    public int getSize(){
        return workers.size();
    }

    @Override
    public LocalDate getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(){
        this.creationDate = LocalDate.now();
    }
}
