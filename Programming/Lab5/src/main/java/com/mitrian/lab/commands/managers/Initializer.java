package com.mitrian.lab.commands.managers;

import com.mitrian.lab.IdCollection;
import com.mitrian.lab.LinkedListCollection;
import com.mitrian.lab.source.*;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.ExtraCheck;
import com.mitrian.lab.utils.Printer;
import com.mitrian.lab.utils.exceptions.ForcedShutdownException;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Initializer {

    static Printer printer = new ConsolePrinter();

    public static boolean createWorkerObject() throws ForcedShutdownException {

        long id =  IdCollection.createWorkerId();

        String name;
        printer.print("Введите имя: ");
        name = ValidatorOfType.validationString();


        int xCoordinates;
        int localXCoordinates = 1000;

        printer.print("Введите координату x (меньшее 884) типа int: ");

        xCoordinates = ValidatorOfType.validationInteger(Set.of(ExtraCheck.LESS885));



        Long yCoordinates;

        printer.print("Введите координату y типа Long: ");
        yCoordinates = ValidatorOfType.validationLong();


        ZonedDateTime creationDate = ZonedDateTime.now();

        Integer salary;

        printer.print("Введите значение salary (большее 0) типа int:");
        salary = ValidatorOfType.validationInteger(Set.of(ExtraCheck.MORE0, ExtraCheck.NULL));


        printer.print("Введите время начала работ"+"\n");
        ZonedDateTime startDate = ValidatorOfType.validationZoned();
        printer.print("Введите дату окончания работ, ");
        Date endDate;
        endDate = ValidatorOfType.validationDate();


        Status status;
        printer.print("Введите статус. ");
        status = ValidatorOfType.validationStatus();


        Double weight;
        printer.print("Введите вес: ");
        weight = ValidatorOfType.validationDouble(ExtraCheck.MORE0);


        Color color;

        printer.print("Введите цвет волос. ");
        color = ValidatorOfType.validationColor();


        Country nationality;

        printer.print("Введите национальность. ");
        nationality = ValidatorOfType.validationCountry();


        int xLocation;

        printer.print("Введите координату x: ");
        xLocation = ValidatorOfType.validationInteger(Set.of(ExtraCheck.NOTHING));


        Long yLocation;

        printer.print("Введите координату y: ");
        yLocation = ValidatorOfType.validationLong();

        Float zLocation;

        printer.print("Введите координату z: ");
        zLocation = ValidatorOfType.validationFloat();

        Coordinates c1 = Coordinates.newBuilder().setX(xCoordinates).setY(yCoordinates).build();

        Location l = Location.newBuilder().setX(xLocation).setY(yLocation).setZ(zLocation).build();

        Person p = new Person.Builder(weight, color)
                .setNationality(nationality)
                .setLocation(l)
                .build();
        Worker worker = new Worker.Builder(name, c1, startDate, p).setCreationDate(ZonedDateTime.now()).build();

        LinkedListCollection.workersCollection.add(worker);

        return true;
    }
}
