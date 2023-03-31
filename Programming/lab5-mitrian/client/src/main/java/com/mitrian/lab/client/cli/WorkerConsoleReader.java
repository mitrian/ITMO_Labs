package com.mitrian.lab.client.cli;

import com.mitrian.lab.client.exceptions.ForcedShutdownException;
import com.mitrian.lab.client.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.data.*;
import com.mitrian.lab.common.data.initializer.IdCollection;
import com.mitrian.lab.common.utils.Printer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

/**
 * Class for creating Worker class object with initialized fields
 */
public class WorkerConsoleReader {

    private Scanner scanner;
    /** Current printer field */
    private Printer printer;


    /**
     * Constructor for creating object of Worker Console Reader class
     * @param printer
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
                return ConsoleArgumentParser.validationString(scanner.nextLine());
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage()+ "Повторите ввод");
            }
        }
    }


    /**
     * Reading salary for Worker class from console
     * @return parsed salary
     * @throws ForcedShutdownException ctl+D exception
     */
    public Float readSalary() throws ForcedShutdownException {
        printer.print("Введите значение salary (большее 0) типа Float:");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                return ConsoleArgumentParser.validationFloatNullableAndMore(scanner.nextLine(),0F);
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + "Повторите ввод");
            }
        }
    }



    /**
     * Reading startDate for Worker class from console
     * @return parsed startDate
     * @throws ForcedShutdownException ctl+D exception
     */
    public Date readStartDate() throws ForcedShutdownException {
        printer.print("Введите время начала работ"+"\n");
        while (true){
            try {
                printer.print("пример для ввода( 2008-10-28 ):");
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                return ConsoleArgumentParser.validationDate(scanner.nextLine());
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + "Повторите ввод");
            }
        }
    }



    /**
     * Reading endDate for Worker class from console
     * @return parsed endDate
     * @throws ForcedShutdownException ctl+D exception
     */
    public LocalDateTime readEndDate() throws  ForcedShutdownException {
        printer.print("Введите дату окончания работ, ");
        while (true){
            try {
                printer.print("пример для ввода( 2008-10-28 ):");
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                return ConsoleArgumentParser.validationLocalDate(scanner.nextLine());
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage()+ "Повторите ввод");
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
                return ConsoleArgumentParser.validationStatusNullable(scanner.nextLine());
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + "Повторите ввод");
            }
        }
    }


    /**
     * Reading status for Worker class from console
     * @return parsed status
     * @throws ForcedShutdownException ctl+D exception
     */
    public Color readColor() throws ForcedShutdownException {
        printer.print("Введите цвет волос. ");
        while (true){
            try {
                printer.print("Возможные значения: GREEN, RED, WHITE, BROWN: ");
                printer.print("");
                if (!scanner.hasNextLine()) {
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                return ConsoleArgumentParser.validationColor(scanner.nextLine());
            } catch (IncorrectFieldException e){
                printer.print(e.getMessage() + "Повторите ввод");
            }
        }
    }

//    public Country readCountry() throws ForcedShutdownException {
//        printer.print("Введите национальность. ");
//        return ConsoleArgumentParser.validationCountryNullable();
//    }


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
        Date startDate = readStartDate();
        LocalDateTime endDate = readEndDate();
        Status status = readStatus();
        Person p = personCreating.createPersonObject();
        Color color = readColor();


        Worker worker = new Worker.Builder(name, c, startDate, p).setCreationDate(Date.from(Instant.now()))
                .setEndDate(endDate)
                .setStatus(status)
                .setSalary(salary)
                .build();


        return worker;
    }
}
