package com.mitrian.server.dao;

import com.mitrian.common.auth.User;
import com.mitrian.common.dao.Dao;
import com.mitrian.common.elements.Person;
import com.mitrian.common.elements.Status;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.impl.algorithm.SHA512Exception;
import com.mitrian.common.exceptions.impl.collection.CollectionElementException;
import com.mitrian.common.exceptions.impl.collection.IdUnavailableException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;
import com.mitrian.common.exceptions.impl.user.UserNameExistenceException;
import com.mitrian.common.exceptions.impl.user.UserNameLenghtException;
import com.mitrian.common.exceptions.impl.user.UserPasswordLengthException;
import com.mitrian.server.collection.Collection;

import java.io.IOException;
import java.sql.SQLException;
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
    public void add(Worker item) throws DBCollectionException {
        workerCollection.add(item, new User("ignored", "ignored"));
    }

    @Override
    public void update(Integer id, Worker item) throws CollectionException, DBCollectionException {
        workerCollection.update(id, item);
    }

    @Override
    public void remove(Integer id) throws CollectionException, DBCollectionException {
        workerCollection.remove(id);
    }

    @Override
    public void clear() throws DBCollectionException {
        workerCollection.clear();
    }

    @Override
    public void save() throws IOException {
        workerCollection.save();
    }

    @Override
    public void removeFirst() throws CollectionException, SQLException, DBCollectionException {
        workerCollection.removeFirst();
    }

    @Override
    public Optional<Worker> removeHead() throws CollectionException, DBCollectionException {
        return workerCollection.removeHead();
    }



    @Override
    public void removeGreater(Worker item) throws DBCollectionException {
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

    @Override
    public boolean signIn(User user) throws SQLException {
        return workerCollection.signIn(user);
    }

    @Override
    public boolean checkAccess(User user, Worker worker) throws SQLException, UserExistenceException, CollectionElementException {
        return workerCollection.checkAccess(user, worker);
    }

    @Override
    public boolean checkAccess(User user, int id) throws SQLException, IdUnavailableException, UserExistenceException {
        return workerCollection.checkAccess(user, id);
    }

    @Override
    public void register(User user) throws UserNameLenghtException, SQLException, UserPasswordLengthException, SHA512Exception, UserNameExistenceException {
        workerCollection.register(user);
    }
}
