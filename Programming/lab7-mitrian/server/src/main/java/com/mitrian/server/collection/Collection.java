package com.mitrian.server.collection;

import com.mitrian.common.elements.Person;
import com.mitrian.common.elements.Status;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.IncorrectFieldException;
import com.mitrian.common.exceptions.impl.collection.IdUnavailableException;
import com.mitrian.common.exceptions.impl.file.CommaException;
import com.mitrian.common.exceptions.impl.file.FileFormatException;
import com.mitrian.common.exceptions.impl.file.PointerExc;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Collection<T> {

    List<T> getAllElements();
    Set<Person> printUniquePerson();
    void add(T item) throws DBCollectionException;
    void update(Integer id, T item) throws CollectionException, DBCollectionException;
    void remove(Integer id) throws CollectionException, DBCollectionException;
    void clear() throws DBCollectionException;
    void save() throws IOException;
    void load() throws IOException, IncorrectFieldException, CommaException, FileFormatException, PointerExc, DBCollectionException;
    void removeFirst() throws CollectionException, SQLException, DBCollectionException;
    Optional<T> removeHead() throws CollectionException, DBCollectionException;
    void removeGreater(T item) throws DBCollectionException;
    T getMinByName() throws CollectionException;
    List<T> filterByStatus(Status status);
    LocalDate getCreationDate();
    int getSize();
    boolean idUnique(int id) throws IdUnavailableException;
    boolean checkIdExists(int id);
}
