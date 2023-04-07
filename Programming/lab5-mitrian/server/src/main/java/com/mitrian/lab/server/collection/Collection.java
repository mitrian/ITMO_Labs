package com.mitrian.lab.server.collection;

import com.mitrian.lab.common.elements.Status;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exceptions.impl.collection.IdUnavailableException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Collection<T> {

    List<T> getAllElements();
    //TODO: print_unique_person
    void add(T item);
    void update(Integer id, T item) throws CollectionException;
    void remove(Integer id) throws CollectionException;
    void clear();
    void save() throws IOException;
    void removeFirst() throws CollectionException;
    Optional<T> removeHead() throws CollectionException;
    void removeGreater(T item);
    Optional<T> getMinByName() throws CollectionException;
    List<T> filterByStatus(Status status);
    LocalDate getCreationDate();
    int getSize();
    boolean idUnique(int id) throws IdUnavailableException;
}
