package com.mitrian.server.collection;

import com.mitrian.common.auth.User;
import com.mitrian.common.elements.Person;
import com.mitrian.common.elements.Status;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.elements.initializer.IdCollection;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.impl.algorithm.SHA512Exception;
import com.mitrian.common.exceptions.impl.collection.CollectionElementException;
import com.mitrian.common.exceptions.impl.collection.CollectionEmptyException;
import com.mitrian.common.exceptions.impl.collection.IdUnavailableException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;
import com.mitrian.common.exceptions.impl.user.UserNameExistenceException;
import com.mitrian.common.exceptions.impl.user.UserNameLenghtException;
import com.mitrian.common.exceptions.impl.user.UserPasswordLengthException;
import com.mitrian.server.Server;
import com.mitrian.server.database.DBConnectionManager;
import com.mitrian.server.database.DBConnectionManagerImpl;
import com.mitrian.server.database.DatabaseManager;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class CollectionDBImpl implements Collection<Worker> {

    /**
     * Current worker collection
     */
    private final List<Worker> workers;
    /**
     * Current worker creationDate
     */
    private LocalDate creationDate = LocalDate.now();
    /**
     * Current unique persons
     */
    private final Set<Person> persons = new HashSet<>();
    private DBConnectionManager dbConnectionManagerImpl;
    private DatabaseManager databaseManager;

    private final String pepper = "Oaoamm";
    private final Lock writeLock = new ReentrantReadWriteLock().writeLock();

    private final Lock readLock = new ReentrantReadWriteLock().readLock();

    private Logger logger;

    public CollectionDBImpl(DBConnectionManager dbConnectionManager, DatabaseManager databaseManager) throws SQLException {
        workers = new LinkedList<>();
        this.dbConnectionManagerImpl = dbConnectionManager;
        this.databaseManager = databaseManager;
        databaseManager.createTablesIfNotExists();
        this.logger = Logger.getLogger(Server.class.getName());

    }

    @Override
    public List<Worker> getAllElements() {
        return workers.stream()
                .sorted(Comparator.comparing(Worker::getName))
                .toList();
    }

    @Override
    public Set<Person> printUniquePerson() {
        persons.clear();
        for (Worker worker : workers) {
            persons.add(worker.getPerson());
        }
        workers.forEach(worker -> persons.add(worker.getPerson()));
        return persons;
    }


    @Override
    public void add(Worker item, User user) throws DBCollectionException {
        try {
            writeLock.lock();
            databaseManager.insertWorker(item);
            workers.add(item);
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при добавлении в бд", e);
        } finally {
            writeLock.unlock();
        }

    }


    @Override
    public void update(Integer id, Worker item) throws CollectionException, DBCollectionException {
        try {
            writeLock.lock();
            databaseManager.updateWorker(id, item.getName(), item.getCoordinates(), item.getSalary(),
                    item.getStartDate(), (java.sql.Date) item.getEndDate(), item.getStatus(),
                    item.getPerson(), item.getPerson().getLocation());

            Optional<Worker> optionalWorker = workers.stream()
                    .filter((worker) -> id.compareTo(worker.getId()) == 0)
                    .findFirst();

            if (optionalWorker.isPresent()) {
                doUpdate(optionalWorker.get(), item);
                return;
            }
            throw new IdUnavailableException("Такого id не существует");
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при обновление элемента в бд", e);
        } finally {
            writeLock.unlock();
        }
    }


    private void doUpdate(Worker updatable, Worker updatedWith) {
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
            writeLock.lock();
            databaseManager.deleteWorker(id);
            Iterator<Worker> iterator = workers.iterator();
            while (iterator.hasNext()) {
                Worker worker = iterator.next();
                if (worker.getId().compareTo(id) == 0) {
                    iterator.remove();
                    IdCollection.idCollection.remove(id);
                    return;
                }
            }
            throw new IdUnavailableException("Такого id не существует");
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при удалении элемента из бд", e);
        } finally {
            writeLock.unlock();
        }

    }


    @Override
    public void clear() throws DBCollectionException {
        try {
            writeLock.lock();
            for (Worker worker : workers) {
                databaseManager.deleteWorker(worker.getId());
            }
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при отчистке бд", e);
        } finally {
            writeLock.unlock();
        }

    }


    @Override
    public void save() throws IOException {
    }


    @Override
    public void load() throws DBCollectionException {
        try {
            writeLock.lock();
            workers.clear();
            workers.addAll(databaseManager.load());
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при загрузке коллекции", e);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void removeFirst() throws CollectionException, SQLException, DBCollectionException {
        try {
            writeLock.lock();
            int idd = workers.get(0).getId();
            databaseManager.deleteWorker(idd);
            if (workers.size() != 0) {
                workers.remove(0);
            } else {
                throw new CollectionEmptyException("В коллекции нет первого элемента");
            }
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при удалении первого элемента из бд", e);
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public Optional<Worker> removeHead() throws CollectionException, DBCollectionException {
        try {
            writeLock.lock();
            if (workers.size() != 0) {
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
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public void removeGreater(Worker item) throws DBCollectionException {
        try {
            writeLock.lock();
            for (Worker worker : workers) {
                if (worker.compareTo(item) > 0) {
                    databaseManager.deleteWorker(worker.getId());
                }
            }
            workers.removeIf(worker -> worker.compareTo(item) > 0);
        } catch (SQLException e) {
            throw new DBCollectionException("Ошибка при удалении элементов из бд, которые больше заданного", e);
        } finally {
            writeLock.unlock();
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
        for (Worker worker : workers) {
            if (worker.getId() != id) {
                sz += 1;
            }
        }
        if (sz == workers.size()) {
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


    @Override
    public boolean signIn(User user) throws SQLException {
        if (!databaseManager.usernameExist(user.getUserName())) {
            return false;
        }
        Pair<String, String> passwordAndSalty = databaseManager.getPasswordAndSalty(user.getUserName());
        String realPassword = passwordAndSalty.getLeft();
        String salty = passwordAndSalty.getRight();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update((pepper + user.getPassword() + salty).getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest();
            return realPassword.equals(new String(bytes, StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkAccess(User user, Worker worker) throws SQLException, UserExistenceException, CollectionElementException {
        if (!signIn(user)) {
            throw new UserExistenceException("Введенны неправильные данные");
        }
        if (!workers.contains(worker)) {
            throw new CollectionElementException("В Вашей коллекции такого элемента нет");
        }
        return databaseManager.checkAccess(user.getUserName(), worker.getId());
    }

    @Override
    public boolean checkAccess(User user, int id) throws SQLException, IdUnavailableException, UserExistenceException {
        if (!signIn(user)) {
            throw new UserExistenceException("Данные пользователя введены неверно");
        }
        if (!checkIdExists(id)) {
            throw new IdUnavailableException("Такого id в коллекции нет");
        }
        return databaseManager.checkAccess(user.getUserName(), id);
    }

    @Override
    public void register(User user) throws UserNameLenghtException, SQLException, UserPasswordLengthException, SHA512Exception, UserNameExistenceException {
        if (user.getUserName().length() <= 4) {
            throw new UserNameLenghtException("Имя пользователя должно содержать больше 4 символов");
        }

        if (user.getPassword().length() <= 4) {
            throw new UserPasswordLengthException("Пароль должен быть длиной более 4 символов");
        }

        if (databaseManager.usernameExist(user.getUserName())) {
            throw new UserNameExistenceException("Данное имя пользователя (" + user.getUserName()
                    + ") уже используется попробуйте еще раз");}

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new SHA512Exception("Для хеширования требуется использовать алгоритм SHA-512" + e.getMessage());
        }
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String salty = new String(array, StandardCharsets.UTF_8);
        md.update((pepper + user.getPassword() + salty).getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest();
        writeLock.lock();
        try {
            databaseManager.insertUser(user.getUserName(), new String(bytes, StandardCharsets.UTF_8), salty);
            logger.info("Пользователь успешно зарегистрирован");
        } finally {
            writeLock.unlock();
        }
    }
}

