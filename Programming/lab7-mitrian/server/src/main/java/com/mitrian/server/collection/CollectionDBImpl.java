package com.mitrian.server.collection;

import com.mitrian.common.elements.Person;
import com.mitrian.common.elements.Status;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.IncorrectFieldException;
import com.mitrian.common.exceptions.impl.collection.IdUnavailableException;
import com.mitrian.common.exceptions.impl.file.CommaException;
import com.mitrian.common.exceptions.impl.file.FileFormatException;
import com.mitrian.common.exceptions.impl.file.PointerExc;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CollectionDBImpl implements Collection<Worker> {
    @Override
    public List<Worker> getAllElements() {
        return null;
    }

    @Override
    public Set<Person> printUniquePerson() {
        return null;
    }

    @Override
    public void add(Worker item) {

    }

    @Override
    public void update(Integer id, Worker item) throws CollectionException {

    }

    @Override
    public void remove(Integer id) throws CollectionException {

    }

    @Override
    public void clear() {

    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public void load() throws IOException, IncorrectFieldException, CommaException, FileFormatException, PointerExc {

    }

    @Override
    public void removeFirst() throws CollectionException {

    }

    @Override
    public Optional<Worker> removeHead() throws CollectionException {
        return Optional.empty();
    }

    @Override
    public void removeGreater(Worker item) {

    }

    @Override
    public Worker getMinByName() throws CollectionException {
        return null;
    }

    @Override
    public List<Worker> filterByStatus(Status status) {
        return null;
    }

    @Override
    public LocalDate getCreationDate() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean idUnique(int id) throws IdUnavailableException {
        return false;
    }

    @Override
    public boolean checkIdExists(int id) {
        return false;
    }
}
