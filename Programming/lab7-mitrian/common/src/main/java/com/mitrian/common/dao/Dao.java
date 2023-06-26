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
import com.mitrian.common.exceptions.impl.user.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Dao<T> {


    List<T> getAllElements(User user) throws SQLException, UserExistenceException;
    Set<Person> printUniquePerson(User user) throws SQLException, UserExistenceException;
    void add(T item) throws DBCollectionException, SQLException, UserExistenceException;

    void update(Integer id, T item, User user) throws CollectionException, DBCollectionException, UserExistenceException, UserAccesException;
    void remove(Integer id, User user) throws CollectionException, DBCollectionException, UserExistenceException;
    void clear(User user) throws DBCollectionException, UserExistenceException;
    void removeFirst(User user) throws CollectionException, SQLException, DBCollectionException, UserExistenceException;
    Optional<T> removeHead(User user) throws CollectionException, DBCollectionException, UserExistenceException;
    void removeGreater(T item, User user) throws DBCollectionException, UserExistenceException;
    T getMinByName(User user) throws CollectionException, SQLException, UserExistenceException;
    List<T> filterByStatus(Status status, User user) throws SQLException, UserExistenceException;
    LocalDate getCreationDate();
    int getSize();
    boolean idUnique (int id) throws IdUnavailableException;
    boolean checkIdExists(int id);
    boolean signIn(User user) throws SQLException;
    boolean checkAccess(User user, Worker worker) throws SQLException, UserExistenceException, CollectionElementException;
    boolean checkAccess(User user, int id) throws SQLException, IdUnavailableException, UserExistenceException;
    void register(User user) throws UserNameLenghtException, SQLException, UserPasswordLengthException, SHA512Exception, UserNameExistenceException;
}
