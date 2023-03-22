package com.mitrian.lab.version1.commands.managers;

import com.mitrian.lab.version1.IdCollection;
import com.mitrian.lab.version1.LinkedListCollection;
import com.mitrian.lab.source.*;
import com.mitrian.lab.version1.utils.Printer;
import com.mitrian.lab.version1.utils.exceptions.ForcedShutdownException;
import com.mitrian.lab.version1.source.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class WorkerInitializer {

    private Printer printer;

    public WorkerInitializer(Printer printer){
        this.printer = printer;
    }

    public String initializeWorkerName() throws ForcedShutdownException {
        printer.print("Введите имя: ");
        return ValidatorOfType.validationString();
    }

    public Integer intializeSalary() throws ForcedShutdownException{
        printer.print("Введите значение salary (большее 0) типа int:");
        return ValidatorOfType.validationIntegerNullableAndMore(0);
    }

    public ZonedDateTime initializeStartDate() throws ForcedShutdownException{
        printer.print("Введите время начала работ"+"\n");
        return ValidatorOfType.validationZoned();
    }

    public LocalDateTime initializeEndDate() throws ForcedShutdownException{
        printer.print("Введите дату окончания работ, ");
        return ValidatorOfType.validationLocalDate();
    }

    public Status initializeStatus() throws ForcedShutdownException{
        printer.print("Введите статус. ");
        return ValidatorOfType.validationStatusNullable();
    }

    public Color initializeColor() throws ForcedShutdownException{
        printer.print("Введите цвет волос. ");
        return ValidatorOfType.validationColor();
    }

    public Country initializeCountry() throws ForcedShutdownException{
        printer.print("Введите национальность. ");
        return ValidatorOfType.validationCountryNullable();
    }




    public Worker createWorkerObject() throws ForcedShutdownException {
        long id =  IdCollection.createWorkerId();
        String name = initializeWorkerName();
        CoordinatesInitializer coordinatesCreating = new CoordinatesInitializer(printer);
        Coordinates c = coordinatesCreating.createCoordinatesObject();
        ZonedDateTime creationDate = ZonedDateTime.now();
        Integer salary = intializeSalary();
        ZonedDateTime startDate = initializeStartDate();
        LocalDateTime endDate = initializeEndDate();
        Status status = initializeStatus();
        Color color = initializeColor();
        Country nationality = initializeCountry();


        PersonInitializer personCreating = new PersonInitializer(printer);
        Person p = personCreating.createPersonObject();

        Worker worker = new Worker.Builder(name, c, startDate, p).setCreationDate(ZonedDateTime.now())
                .setEndDate(endDate)
                .build();

        LinkedListCollection.workersCollection.add(worker);
        return worker;
    }
}
