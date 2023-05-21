package com.mitrian.server.dao;

import com.mitrian.common.dao.Dao;
import com.mitrian.common.elements.Person;
import com.mitrian.common.elements.Status;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.impl.collection.IdUnavailableException;
import com.mitrian.server.collection.Collection;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Set<Person> printUniquePerson() {
        return workerCollection.printUniquePerson();
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
    public void save() throws IOException {
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
    public Worker getMinByName() throws CollectionException {
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

    public boolean idUnique(int id) throws IdUnavailableException {
        return workerCollection.idUnique(id);
    }

    public boolean checkIdExists(int id)
    {
        return workerCollection.checkIdExists(id);
    }
}
