package com.mitrian.server.collection;

import com.mitrian.common.elements.Person;
import com.mitrian.common.elements.Status;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.elements.initializer.IdCollection;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.IncorrectFieldException;
import com.mitrian.common.exceptions.impl.collection.CollectionEmptyException;
import com.mitrian.common.exceptions.impl.collection.IdUnavailableException;
import com.mitrian.common.exceptions.impl.file.CommaException;
import com.mitrian.common.exceptions.impl.file.FileFormatException;
import com.mitrian.common.exceptions.impl.file.PointerExc;
import com.mitrian.server.file.csv.CsvLoader;
import com.mitrian.server.file.csv.CsvReader;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Class to interact with collection
 */
public class CollectionImpl implements Collection<Worker>
{


    /** Current heading for csv */
    private final String[] csvHeading = { "id", "name", "coordinates_x", "coordinates_y",
            "creationDate", "salary", "startDate", "endDate", "status", "person_weight",
            "person_hairColor", "person_nationality", "location_x", "location_y", "location_z"};
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
        return workers.stream()
                .sorted(Comparator.comparing(Worker::getName))
                .toList();
        //TODO сортировка по имени
    }

    @Override
    public Set<Person> printUniquePerson() {
        //TODO посмотри поч одинаковые выводятся
        persons.clear();
        for (Worker worker: workers){
            persons.add(worker.getPerson());
        }

        workers.forEach(worker -> persons.add(worker.getPerson()));
        return persons;
    }


    /**
     * Add element in collection
     * @param item element to adding
     */
    @Override
    public void add(Worker item) {
        workers.add(item);
        IdCollection.idCollection.add(item.getId());
    }


    /**
     * Update element with inputed id
     * @param id of element
     * @param item new element
     * @throws CollectionException some troubles with collection
     */
    @Override
    public void update(Integer id, Worker item) throws CollectionException {
//        for (Worker worker: workers){
//            if (id.compareTo(worker.getId()) == 0){
//                worker.setName(item.getName());
//                worker.setCoordinates(item.getCoordinates());
//                worker.setCreationDate();
//                worker.setSalary(item.getSalary());
//                worker.setStartDate(item.getStartDate());
//                worker.setEndDate(item.getEndDate());
//                worker.setStatus(item.getStatus());
//                worker.setPerson(item.getPerson());
//                return;
//            }
//        }


        Optional<Worker> optionalWorker = workers.stream()
                .filter((worker) -> id.compareTo(worker.getId()) == 0)
                .findFirst();

        if (optionalWorker.isPresent())
        {
            doUpdate(optionalWorker.get(), item);
            return;
        }

//        workers.stream()
//                .forEach(worker -> {
//                    if (id.compareTo(worker.getId()) == 0){
//                        worker.setName(item.getName());
//                        worker.setCoordinates(item.getCoordinates());
//                        worker.setCreationDate();
//                        worker.setSalary(item.getSalary());
//                        worker.setStartDate(item.getStartDate());
//                        worker.setEndDate(item.getEndDate());
//                        worker.setStatus(item.getStatus());
//                        worker.setPerson(item.getPerson());
//                        return;
//                    }
//                });
        throw new IdUnavailableException("Такого id не существует");
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
    public void remove(Integer id) throws CollectionException {
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
    public void load() throws IOException, IncorrectFieldException, CommaException, FileFormatException, PointerExc {
        Reader fileCheck =  new FileReader(file);
        try {
            BufferedReader reading  = new BufferedReader(fileCheck);
            String line;
            String[] heading = reading.readLine().split(",");
            int head = heading.length;
            if (head != csvHeading.length || !Arrays.equals(heading,csvHeading)){
                throw new FileFormatException("Данные в файле не корректны");
            }
            while ( (line = reading.readLine()) != null){
                if (!"".equals(line)){
                    if (head != line.split(",").length){
                        throw new CommaException("Файл не корректен, проверьте данные");
                    }
                }

            }
            reading.close();
        } catch (CommaException e) {
            throw new CommaException(e.getMessage());
        } catch (FileFormatException e) {
            throw new FileFormatException(e.getMessage());
        } catch (NullPointerException e){
            throw new PointerExc("Уберите лишние строки");
        }
        Reader fileCSV = new FileReader(file);
        CsvReader csvReader = new CsvReader(this, fileCSV);
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
    public Worker getMinByName() throws CollectionEmptyException {
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

    public boolean checkIdExists(int id)
    {
        Optional<Worker> result = workers.stream().filter((worker) -> worker.getId() == id).findFirst();
        return result.isPresent();
    }
}
