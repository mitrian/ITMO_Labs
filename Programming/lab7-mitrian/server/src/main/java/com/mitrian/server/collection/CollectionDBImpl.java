package com.mitrian.server.collection;

import com.mitrian.common.elements.Person;
import com.mitrian.common.elements.Status;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.elements.initializer.IdCollection;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.IncorrectFieldException;
import com.mitrian.common.exceptions.impl.collection.CollectionEmptyException;
import com.mitrian.common.exceptions.impl.collection.IdUnavailableException;
import com.mitrian.common.exceptions.impl.file.CommaException;
import com.mitrian.common.exceptions.impl.file.FileFormatException;
import com.mitrian.common.exceptions.impl.file.PointerExc;
import com.mitrian.server.database.DBConnectionManager;
import com.mitrian.server.database.DBConnectionManagerImpl;
import com.mitrian.server.database.DatabaseManager;
import com.mitrian.server.database.DatabaseUtilies;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CollectionDBImpl implements Collection<Worker> {

    /** Current worker collection */
    private final List<Worker> workers;
    /** Current worker creationDate */
    private LocalDate creationDate = LocalDate.now();
    /** Current unique persons */
    private final Set<Person> persons = new HashSet<>();
    private DBConnectionManagerImpl dbConnectionManagerImpl;
    private DatabaseManager databaseManager;

    public CollectionDBImpl(DBConnectionManager dbConnectionManager, DatabaseManager databaseManager) {
        workers = new LinkedList<>();
        this.dbConnectionManagerImpl = dbConnectionManagerImpl;
        this.databaseManager = databaseManager;
    }

    @Override
    public List<Worker> getAllElements() {
        return null;
    }

    @Override
    public Set<Person> printUniquePerson() {
        persons.clear();
        for (Worker worker: workers){
            persons.add(worker.getPerson());
        }
        workers.forEach(worker -> persons.add(worker.getPerson()));
        return persons;
    }

    @Override
    public void add(Worker item) throws DBCollectionException {
        try {
            databaseManager.insertWorker(item);
            workers.add(item);
        } catch (SQLException e){
            throw new DBCollectionException("Ошибка при добавлении в бд", e);
        }

    }

    @Override
    public void update(Integer id, Worker item) throws CollectionException, DBCollectionException {
        try {
            databaseManager.updateWorker(id, item.getName(), item.getCoordinates(),item.getSalary(),
                    item.getStartDate(), (java.sql.Date) item.getEndDate(),item.getStatus(),
                    item.getPerson(), item.getPerson().getLocation());

            Optional<Worker> optionalWorker = workers.stream()
                    .filter((worker) -> id.compareTo(worker.getId()) == 0)
                    .findFirst();

            if (optionalWorker.isPresent())
            {
                doUpdate(optionalWorker.get(), item);
                return;
            }
            throw new IdUnavailableException("Такого id не существует");
        } catch (SQLException e){
            throw new DBCollectionException("Ошибка при обновление элемента в бд", e);
        }
    }

    private void doUpdate(Worker updatable, Worker updatedWith)
    {
        updatable.setName(updatedWith.getName());
        updatable.setCoordinates(updatedWith.getCoordinates());
        updatable.setCreationDate();
        updatable.setSalary(updatedWith.getSalary());
        updatable.setStartDate(updatedWith.getStartDate());
        updatable.setEndDate(updatedWith.getEndDate());
        updatable.setStatus(updatedWith.getStatus());
        updatable.setPerson(updatedWith.getPerson());
    }

    @Override
    public void remove(Integer id) throws CollectionException, DBCollectionException {
        try {
            databaseManager.deleteWorker(id);
            Iterator<Worker> iterator = workers.iterator();
            while (iterator.hasNext()){
                Worker worker = iterator.next();
                if (worker.getId().compareTo(id) == 0){
                    iterator.remove();
                    IdCollection.idCollection.remove(id);
                    return;
                }
            }
            throw new IdUnavailableException("Такого id не существует");
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при удалении элемента из бд", e);
        }

    }

    @Override
    public void clear() throws DBCollectionException {
        try {
            for (Worker worker: workers){
                databaseManager.deleteWorker(worker.getId());
            }
        } catch (SQLException e){
            throw new DBCollectionException("Ошибка при отчистке бд", e);
        }

    }

    @Override
    public void save() throws IOException {
    }

    @Override
    public void load() throws DBCollectionException {
        try {
            databaseManager.createTablesIfNotExists();
            workers.clear();
            workers.addAll(databaseManager.getAllWorkers(dbConnectionManagerImpl.getConnection()));
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при загрузке коллекции", e);
        }
    }

    @Override
    public void removeFirst() throws CollectionException, SQLException, DBCollectionException {
        try {
            int idd = workers.get(0).getId();
            databaseManager.deleteWorker(idd);
            if (workers.size() != 0){
                workers.remove(0);
            } else {
                throw new CollectionEmptyException("В коллекции нет первого элемента");
            }
        } catch (SQLException e){
            throw new DBCollectionException("Ошибка при удалении первого элемента из бд", e);
        }

    }

    @Override
    public Optional<Worker> removeHead() throws CollectionException, DBCollectionException {
        try {
            if (workers.size() != 0){
                int idd = workers.get(0).getId();
                databaseManager.deleteWorker(idd);
                Optional<Worker> worker = Optional.ofNullable(workers.get(0));
                workers.remove(0);
                return worker;
            } else {
                throw new CollectionEmptyException("В коллекции нет первого элемента");
            }
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при удалении первого элемента из бд", e);
        }

    }

    @Override
    public void removeGreater(Worker item) throws DBCollectionException {
        try{
            for (Worker worker: workers){
                if (worker.compareTo(item)>0){
                    databaseManager.deleteWorker(worker.getId());
                }
            }
            workers.removeIf(worker -> worker.compareTo(item) > 0);
        } catch (SQLException e){
            throw new DBCollectionException("Ошибка при удалении элементов из бд, которые больше заданного",e);
        }

    }

    @Override
    public Worker getMinByName() throws CollectionException {
        Optional<Worker> optionalWorker = workers.stream().min(Comparator.comparing(Worker::getName));
        if (optionalWorker.isPresent())
            return optionalWorker.get();
        throw new CollectionEmptyException("Коллекция пуста");
    }

    @Override
    public List<Worker> filterByStatus(Status status) {
        return workers.stream()
                .filter((worker) -> (worker.getStatus() != null && worker.getStatus() == status))
                .toList();
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public int getSize() {
        return workers.size();
    }

    @Override
    public boolean idUnique(int id) throws IdUnavailableException {
        int sz = 0;
        for (Worker worker: workers){
            if (worker.getId() != id){
                sz +=1;
            }
        }
        if (sz == workers.size()){
            return true;
        } else {
            throw new IdUnavailableException("Такое id уже существует.");
        }
    }

    @Override
    public boolean checkIdExists(int id) {
        Optional<Worker> result = workers.stream().filter((worker) -> worker.getId() == id).findFirst();
        return result.isPresent();
    }
}
