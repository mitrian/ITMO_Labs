package com.mitrian.lab.common.dao;

import com.mitrian.lab.common.data.Status;
import com.mitrian.lab.common.exceptions.CollectionException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> getAllElements();
    void add(T item);
    void update(Integer id, T item) throws CollectionException;
    void remove(Integer id) throws CollectionException;
    void clear();
    void save();
    void removeFirst() throws CollectionException;
    Optional<T> removeHead() throws CollectionException;
    void removeGreater(T item);
    Optional<T> getMinByName() throws CollectionException;
    List<T> filterByStatus(Status status);

    LocalDate getCreationDate();
    int getSize();
}
