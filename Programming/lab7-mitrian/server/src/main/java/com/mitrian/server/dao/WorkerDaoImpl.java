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
    public List<Worker> getAllElements(User user) throws SQLException, UserExistenceException {
        return workerCollection.getAllElements(user);
    }

    @Override
    public Set<Person> printUniquePerson(User user) throws SQLException, UserExistenceException {
        return workerCollection.printUniquePerson(user);
    }

    @Override
    public void add(Worker item) throws DBCollectionException, SQLException, UserExistenceException {
        workerCollection.add(item);
    }

    @Override
    public void update(Integer id, Worker item, User user) throws CollectionException, DBCollectionException, UserExistenceException {
        workerCollection.update(id, item, user);
    }

    @Override
    public void remove(Integer id, User user) throws CollectionException, DBCollectionException, UserExistenceException {
        workerCollection.remove(id, user);
    }

    @Override
    public void clear(User user) throws DBCollectionException, UserExistenceException {
        workerCollection.clear(user);
    }


    @Override
    public void removeFirst(User user) throws CollectionException, SQLException, DBCollectionException, UserExistenceException {
        workerCollection.removeFirst(user);
    }

    @Override
    public Optional<Worker> removeHead(User user) throws CollectionException, DBCollectionException, UserExistenceException {
        return workerCollection.removeHead(user);
    }



    @Override
    public void removeGreater(Worker item, User user) throws DBCollectionException, UserExistenceException {
        workerCollection.removeGreater(item, user);
    }

    @Override
    public Worker getMinByName(User user) throws CollectionException, SQLException, UserExistenceException {
        return workerCollection.getMinByName(user);
    }

    @Override
    public List<Worker> filterByStatus(Status status, User user) throws SQLException, UserExistenceException {

        return workerCollection.filterByStatus(status, user);

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
