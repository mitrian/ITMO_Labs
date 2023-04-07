package com.mitrian.lab.client.ui.file;


import com.mitrian.lab.common.commands.utils.parser.ArgumentParser;
import com.mitrian.lab.common.commands.utils.validator.ArgumentValidator;
import com.mitrian.lab.common.elements.*;
import com.mitrian.lab.common.elements.initializer.IdCollection;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.exceptions.ReaderException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class WorkerFileReader {

    /** Current scanner field */
    private Scanner scanner;
    /** Current startingDate field to compare it with endDate */
    private LocalDate startingDate;


    /**
     * Constructor for creating object of Worker Console Reader class
     * @param scanner scanner for initializing scanner field
     */
    public WorkerFileReader(Scanner scanner){
        this.scanner = scanner;
    }



    /**
     * Reading name for Worker class from file
     * @return parsed name
     * @throws ReaderException wrong field for initializing
     */
    public String readWorkerName() throws ReaderException {
        try{
            String name = ArgumentParser.parseString(scanner.nextLine());
            if (ArgumentValidator.validationName(name)){
                return name;
            }
            return "";
        } catch (IncorrectFieldException e){
            throw new ReaderException("Ошибка при считывании поля name");
        }
    }


    /**
     * Reading salary for Worker class from file
     * @return parsed salary
     * @throws ReaderException wrong field for initializing
     */
    public Float readSalary() throws ReaderException {
        try{
            Float salary = ArgumentParser.parseFloat(scanner.nextLine());
            if  (ArgumentValidator.validationSalary(salary)){
                return  salary;
            }
            return 0f;
        } catch (IncorrectFieldException e) {
            throw new ReaderException("Ошибка при считывании поля salary");
        }
    }


    /**
     * Reading startDate for Worker class from file
     * @return parsed startDate
     * @throws ReaderException wrong field for initializing
     */
    public LocalDate readStartDate() throws ReaderException {
        try{
            startingDate = ArgumentParser.parseLocalDate(scanner.nextLine());
            if (ArgumentValidator.validationStartDate(startingDate)){
                return startingDate;
            }
            return LocalDate.now();
        } catch (IncorrectFieldException e){
            throw new ReaderException("Ошибка при считывании поля startDate");
        }
    }


    /**
     * Reading endDate for Worker class from file
     * @return parsed endDate
     * @throws ReaderException wrong field for initializing
     */
    public Date readEndDate() throws ReaderException {
        try{
            Date endDate = ArgumentParser.parseDate(scanner.nextLine());
            if (ArgumentValidator.validationEndDate(endDate, startingDate)){
                return endDate;
            }
            return Date.from(Instant.now());
        } catch (IncorrectFieldException e){
            throw new ReaderException("Ошибка при считывании поля endDate");
        }
    }


    /**
     * Reading status for Worker class from file
     * @return parsed status
     * @throws ReaderException wrong field for initializing
     */
    public Status readStatus() throws ReaderException {
        try {
            Status status = ArgumentParser.parseStatus(scanner.nextLine());
            if (ArgumentValidator.validationStatus(status)){
                return status;
            }
            return null;
        } catch (IncorrectFieldException e){
            throw new ReaderException("Ошибка при считывании поля status");
        }
    }


    /**
     * Creating object of Worker class
     * @return Worker class object
     * @throws ReaderException wrong field for initializing
     */
    public Worker createWorkerObject() throws ReaderException, IncorrectFieldException {
        long id =  IdCollection.createWorkerId();
        PersonFileReader personCreating = new PersonFileReader(scanner);
        String name = readWorkerName();
        CoordinatesFileReader coordinatesCreating = new CoordinatesFileReader(scanner);
        Coordinates c = coordinatesCreating.createCoordinatesObject();
        LocalDate creationDate = LocalDate.now();
        Float salary = readSalary();
        LocalDate startDate = readStartDate();
        Date endDate = readEndDate();
        Status status = readStatus();
        Person p = personCreating.createPersonObject();



        Worker worker = new Worker.Builder(name, c, startDate, p).setCreationDate()
                .setEndDate(endDate)
                .setStatus(status)
                .setSalary(salary)
                .build();


        return worker;
    }
}
