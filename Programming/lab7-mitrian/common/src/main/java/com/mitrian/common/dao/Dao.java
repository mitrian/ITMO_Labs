package com.mitrian.common.dao;

import com.mitrian.common.auth.User;
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

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Dao<T> {


    List<T> getAllElements();
    Set<Person> printUniquePerson();
    void add(T item) throws DBCollectionException;
    void update(Integer id, T item) throws CollectionException, DBCollectionException;
    void remove(Integer id) throws CollectionException, DBCollectionException;
    void clear() throws DBCollectionException;
    void save() throws IOException;
    void removeFirst() throws CollectionException, SQLException, DBCollectionException;
    Optional<T> removeHead() throws CollectionException, DBCollectionException;
    void removeGreater(T item) throws DBCollectionException;
    T getMinByName() throws CollectionException;
    List<T> filterByStatus(Status status);
    LocalDate getCreationDate();
    int getSize();
    boolean idUnique (int id) throws IdUnavailableException;
    boolean checkIdExists(int id);
    boolean signIn(User user) throws SQLException;
    boolean checkAccess(User user, Worker worker) throws SQLException, UserExistenceException, CollectionElementException;
    boolean checkAccess(User user, int id) throws SQLException, IdUnavailableException, UserExistenceException;
    void register(User user) throws UserNameLenghtException, SQLException, UserPasswordLengthException, SHA512Exception, UserNameExistenceException;
}
