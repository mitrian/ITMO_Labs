package com.mitrian.lab.server.dao;

import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Status;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.server.collection.Collection;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class WorkerDaoImpl implements Dao<Worker> {

    private final Collection<Worker> workerCollection;

    public WorkerDaoImpl(Collection<Worker> workerCollection)
    {
        this.workerCollection = workerCollection;
    }


    @Override
    public List<Worker> getAllElements() {
        return workerCollection.getAllElements();
    }

    @Override
    public void add(Worker item) {
        workerCollection.add(item);
    }

    @Override
    public void update(Integer id, Worker item) throws CollectionException {
        workerCollection.update(id, item);
    }

    @Override
    public void remove(Integer id) throws CollectionException {
        workerCollection.remove(id);
    }

    @Override
    public void clear() {
        workerCollection.clear();
    }

    @Override
    public void save() {
        workerCollection.save();
    }

    @Override
    public void removeFirst() throws CollectionException {
        workerCollection.removeFirst();
    }

    @Override
    public Optional<Worker> removeHead() throws CollectionException {
        return workerCollection.removeHead();
    }



    @Override
    public void removeGreater(Worker item) {
        workerCollection.removeGreater(item);
    }

    @Override
    public Optional<Worker> getMinByName() throws CollectionException {
        return workerCollection.getMinByName();
    }

    @Override
    public List<Worker> filterByStatus(Status status) {
        return workerCollection.filterByStatus(status);
    }

    @Override
    public LocalDate getCreationDate() {
        return workerCollection.getCreationDate();
    }

    @Override
    public int getSize(){
        return workerCollection.getSize();
    }
}