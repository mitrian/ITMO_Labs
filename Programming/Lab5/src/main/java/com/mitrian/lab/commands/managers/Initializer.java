package com.mitrian.lab.commands.managers;

import com.mitrian.lab.IdCollection;
import com.mitrian.lab.source.*;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.ExtraCheck;
import com.mitrian.lab.utils.Printer;
import com.mitrian.lab.utils.exceptions.ForcedShutdownException;

import java.time.ZonedDateTime;
import java.util.Date;

public class Initializer {

    static Printer printer = new ConsolePrinter();

    public static boolean createWorkerObject() throws ForcedShutdownException {

        long id =  IdCollection.createWorkerId();

        String name;
        printer.print("Введите имя: ");
        name = ValidatorOfType.validation("");


        int xCoordinates;
        int localXCoordinates = 1000;

        printer.print("Введите координату x (меньшее 884) типа int: ");

        xCoordinates = ValidatorOfType.validation(0, ExtraCheck.LESS885, ExtraCheck.NOTHING);



        Long yCoordinates;

        printer.print("Введите координату y типа Long: ");
        yCoordinates = ValidatorOfType.validation(1L);


        ZonedDateTime creationDate = ZonedDateTime.now();

        Integer salary;

        printer.print("Введите значение salary (большее 0) типа int:");
        salary = ValidatorOfType.validation(0,ExtraCheck.MORE0, ExtraCheck.NULL);


        printer.print("Введите время начала работ"+"\n");
        ZonedDateTime startDate = ValidatorOfType.validation(ZonedDateTime.now());
        printer.print("Введите дату окончания работ, ");
        Date endDate;
        endDate = ValidatorOfType.validation(new Date());


        Status status;
        printer.print("Введите статус. ");
        status = ValidatorOfType.validation(Status.FIRED);


        Double weight;
        printer.print("Введите вес: ");
        weight = ValidatorOfType.validation(0.31d,ExtraCheck.MORE0);


        Color color;

        printer.print("Введите цвет волос. ");
        color = ValidatorOfType.validation(Color.GREEN);


        Country nationality;

        printer.print("Введите национальность");
        nationality = ValidatorOfType.validation(Country.GERMANY);


        int xLocation;

        printer.print("Введите координату x: ");
        xLocation = ValidatorOfType.validation(0,ExtraCheck.NOTHING, ExtraCheck.NOTHING);


        Long yLocation;

        printer.print("Введите координату y: ");
        yLocation = ValidatorOfType.validation(1l);


        return true;
    }
}
