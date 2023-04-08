package com.mitrian.lab.server.collection;

import com.mitrian.lab.common.elements.Person;
import com.mitrian.lab.common.elements.Status;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exceptions.FileException;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.exceptions.impl.collection.CollectionEmptyException;
import com.mitrian.lab.common.exceptions.impl.collection.IdUnavailableException;
import com.mitrian.lab.common.exceptions.impl.file.CommaException;
import com.mitrian.lab.server.file.csv.CsvLoader;
import com.mitrian.lab.server.file.csv.CsvReader;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Class to interact with collection
 */
public class CollectionImpl implements Collection<Worker> {

    /** Current worker collection */
    private final List<Worker> workers;
    /** Current file */
    private final File file;
    /** Current worker creationDate */
    private LocalDate creationDate = LocalDate.now();
    /** Current unique persons */
    private final Set<Person> persons = new HashSet<>();


    /**
     * Constructor to create collection and initialize file field
     * @param file file for initializing file field
     */
    public CollectionImpl(File file){

        workers = new LinkedList<>();
        this.file = file;
    }


    /**
     * Getting list of collection elements
     * @return list of collection elements
     */
    @Override
    public List<Worker> getAllElements() {
        return workers;
    }

    @Override
    public Set<Person> printUniquePerson() {
        persons.clear();
        for (Worker worker: workers){
            persons.add(worker.getPerson());
        }
        return persons;
    }


    /**
     * Add element in collection
     * @param item element to adding
     */
    @Override
    public void add(Worker item) {
        workers.add(item);
    }


    /**
     * Update element with inputed id
     * @param id of element
     * @param item new element
     * @throws CollectionException some troubles with collection
     */
    @Override
    public void update(Integer id, Worker item) throws CollectionException {
        for (Worker worker: workers){
            if (id.compareTo(worker.getId()) == 0){
                worker.setName(item.getName());
                worker.setCoordinates(item.getCoordinates());
                worker.setCreationDate();
                worker.setSalary(item.getSalary());
                worker.setStartDate(item.getStartDate());
                worker.setEndDate(item.getEndDate());
                worker.setStatus(item.getStatus());
                worker.setPerson(item.getPerson());
                return;
            }
        }
        //TODO: update, рекурсия, запись в csv и его валидация
        throw new IdUnavailableException("Такого id не существует");
    }

    @Override
    public void remove(Integer id) throws CollectionException {
        Iterator<Worker> iterator = workers.iterator();
        while (iterator.hasNext()){
            Worker worker = iterator.next();
            if (worker.getId().compareTo(id) == 0){
                iterator.remove();
                return;
            }
        }
        throw new IdUnavailableException("Такого id не существует");
    }

    @Override
    public void clear() {
        workers.clear();
    }

    @Override
    public void save() throws IOException {
        CsvLoader csvLoader = new CsvLoader(file);
        csvLoader.load(workers);
        csvLoader.close();
    }

    @Override
    public void load() throws IOException, IncorrectFieldException, CommaException {
        Reader fileCheck =  new FileReader(file);
        try {
            BufferedReader reading  = new BufferedReader(fileCheck);
            String line;
            int head = reading.readLine().split(",").length;
            while ( (line = reading.readLine()) != null){
                if (head != line.split(",").length){
                    throw new CommaException("Файл не корректен, уберите лишние запятые");
                }
            }
        } catch (CommaException e) {
            throw new CommaException(e.getMessage());
        }
        CsvReader csvReader = new CsvReader(this, fileCheck);
        workers.addAll(csvReader.read());
        csvReader.close();
    }

    @Override
    public void removeFirst() throws CollectionException{
        if (workers.size() != 0){
            workers.remove(0);
        } else {
            throw new CollectionEmptyException("В коллекции нет первого элемента");
        }
    }

    @Override
    public Optional<Worker> removeHead() throws CollectionException {
        if (workers.size() != 0){
            Optional<Worker> worker = Optional.ofNullable(workers.get(0));
            workers.remove(0);
            return worker;
        } else {
            throw new CollectionEmptyException("В коллекции нет первого элемента");
        }
    }

    @Override
    public void removeGreater(Worker item) {
        workers.removeIf(worker -> worker.compareTo(item) > 0);
    }

    @Override
    public Optional<Worker> getMinByName() throws CollectionEmptyException {
        if (workers.size() == 0){
            throw new CollectionEmptyException("Коллекция пуста");
        }
        return Optional.ofNullable(Collections.min(workers, Comparator.comparing(Worker::getName)));
    }

    @Override
    public List<Worker> filterByStatus(Status status) {
        List<Worker> workersByStatus = new LinkedList<>();

        for (Worker worker : workers) {
            if (worker.getStatus() == status) {
                workersByStatus.add(worker);
            }
        }
        return workersByStatus;
    }

    @Override
    public int getSize(){
        return workers.size();
    }

    @Override
    public LocalDate getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(){
        this.creationDate = LocalDate.now();
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
}
