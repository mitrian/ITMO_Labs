package com.mitrian.lab.client.ui.console;

import com.mitrian.lab.common.commands.utils.ArgumentParser;
import com.mitrian.lab.common.commands.utils.ArgumentValidator;
import com.mitrian.lab.common.exceptions.ForcedShutdownException;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.elements.*;
import com.mitrian.lab.common.elements.initializer.IdCollection;
import com.mitrian.lab.common.utils.Printer;

import java.time.*;
import java.util.Date;
import java.util.Scanner;

/**
 * Class for creating Worker class object with initialized fields
 */
public class WorkerConsoleReader {

    /** Current scanner field*/
    private Scanner scanner;
    /** Current printer field */
    private Printer printer;
    /** Current start date to compare with end date field */
    private LocalDate startingDate;


    /**
     * Constructor for creating object of Worker Console Reader class
     * @param printer printer for initializing printer field
     * @param scanner scanner for initializing scanner field
     */
    public WorkerConsoleReader(Scanner scanner, Printer printer){
        this.scanner = scanner;
        this.printer = printer;
    }


    /**
     * Reading name for Worker class from console
     * @return parsed name
     * @throws ForcedShutdownException ctl+D exception
     */
    public String readWorkerName() throws ForcedShutdownException {
        printer.print("Введите имя: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                return ArgumentValidator.validationName(ArgumentParser.parseString(scanner.nextLine()));
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage()+ " Повторите ввод: ");
            }
        }
    }


    /**
     * Reading salary for Worker class from console
     * @return parsed salary
     * @throws ForcedShutdownException ctl+D exception
     */
    public Float readSalary() throws ForcedShutdownException {
        printer.print("Введите значение salary (большее 0) типа Float: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                return ArgumentValidator.validationSalary(ArgumentParser.parseFloat(scanner.nextLine()));
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + " Повторите ввод: ");
            }
        }
    }

    /**
     * Reading startDate for Worker class from console
     * @return parsed startDate
     * @throws ForcedShutdownException ctl+D exception
     */
    public LocalDate readStartDate() throws ForcedShutdownException {
        printer.print("Введите время начала работ. ");
        while (true){
            try {
                printer.print("Пример для ввода( 2008-10-28 ): ");
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                startingDate = ArgumentValidator.validationStartDate(ArgumentParser.parseLocalDate(scanner.nextLine()));
                return startingDate;
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + " Повторите ввод. ");
            }
        }
    }


    /**
     * Reading endDate for Worker class from console
     * @return parsed endDate
     * @throws ForcedShutdownException ctl+D exception
     */
    //TODO : change println
    public Date readEndDate() throws  ForcedShutdownException {
        printer.print("Введите дату окончания работ. ");
        while (true){
            try {
                printer.print("Пример для ввода( 2008-10-28 ): ");
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                Date endDate = ArgumentValidator.validationEndDate(ArgumentParser.parseDate(scanner.nextLine()),startingDate);
                //TODO: compare Date
                return endDate;
            } catch (IncorrectFieldException e) {
                printer.println(e.getMessage()+ " Повторите ввод. ");
            }
        }
    }


    /**
     * Reading status for Worker class from console
     * @return parsed status
     * @throws ForcedShutdownException ctl+D exception
     */
    public Status readStatus() throws ForcedShutdownException {
        printer.print("Введите статус. ");
        while (true) {
            try {
                printer.print("Предложенные значения( FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION ): ");
                printer.print("");
                if (!scanner.hasNextLine()) {
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                return ArgumentValidator.validationStatus(ArgumentParser.parseStatus(scanner.nextLine()));
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + " Повторите ввод. ");
            }
        }
    }


    /**
     * Creating object of Worker class
     * @return Worker class object
     * @throws ForcedShutdownException ctl+D exception
     */
    public Worker createWorkerObject() throws ForcedShutdownException {
        long id =  IdCollection.createWorkerId();
        PersonConsoleReader personCreating = new PersonConsoleReader(scanner, printer);
        String name = readWorkerName();
        CoordinatesConsoleReader coordinatesCreating = new CoordinatesConsoleReader(scanner, printer);
        Coordinates c = coordinatesCreating.createCoordinatesObject();
        ZonedDateTime creationDate = ZonedDateTime.now();
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
