package com.mitrian.lab.commands.managers;

import com.mitrian.lab.source.*;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;
import com.mitrian.lab.utils.exceptions.ForcedShutdownException;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.util.Date;

public class Initializar {

    static Printer printer = new ConsolePrinter();

    public static boolean createWorkerObject(String args[]) throws ForcedShutdownException {

        String name;
        try{
            name = String.valueOf(args[0]);
            if (name.trim().isEmpty()){
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException e){
            printer.print("Введите имя: ");
            name = ValidatorOfType.validation(args[0]);
        }

        int xCoordinates;
        try{
            xCoordinates = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
            printer.print("Введите координату x (меньшее 884) типа int: ");
            while (true){
                xCoordinates = ValidatorOfType.validation(0);
                if (xCoordinates < 884){
                    break;
                }
            }
        }

        Long yCoordinates;
        try{
            yCoordinates = Long.parseLong(args[2]);
        } catch (NumberFormatException e){
            printer.print("Введите координату y типа Long");
            yCoordinates = ValidatorOfType.validation(1L);
        }

        ZonedDateTime creationDate = ZonedDateTime.now();

        Integer salary;
        try{
            salary = Integer.parseInt(args[3]);
        } catch (NumberFormatException e){
            printer.print("Введите значение salary (большее 0) типа int:");
            while (true){
                salary = ValidatorOfType.validation(0);
                if (salary > 0){
                    break;
                }
            }
        }
//        как сделать????
//        ZonedDateTime startDate;
//        try{
//            startDate = ZonedDateTime.parse(args[4]);
//        } catch (DateTimeException e){
//            startDate = ValidatorOfType.validation(2023-02-21T17:41:27.4415691+03:00[Europe/Moscow]);
//        }
//
//        Date endDate;
//        try{
//            e
//        }
//

        Status status;
        try{
            status = Status.valueOf(args[6]);
        } catch (IllegalArgumentException e){
            printer.print("Введите статус: ");
            status = ValidatorOfType.validation(Status.FIRED);
        }

        Double weight;
        try{
            weight = Double.parseDouble(args[7]);
        } catch (NumberFormatException e){
            printer.print("Введите вес: ");
            weight = ValidatorOfType.validation(0.31d);
        }

        Color color;
        try {
            color = Color.valueOf(args[8]);
        } catch (IllegalArgumentException e){
            printer.print("Введите цвет волос: ");
            color = ValidatorOfType.validation(Color.GREEN);
        }

        Country nationality;
        try {
            nationality = Country.valueOf(args[9]);
        } catch (IllegalArgumentException e){
            printer.print("Введите национальность");
            nationality = ValidatorOfType.validation(Country.GERMANY);
        }

        int xLocation;
        try {
            xLocation = Integer.parseInt(args[10]);
        } catch (NumberFormatException e){
            printer.print("Введите координату x: ");
            xLocation = ValidatorOfType.validation(0);
        }

        Long yLocation;
        try {
            yLocation = Long.parseLong(args[11]);
        } catch (NumberFormatException e){
            printer.print("Введите координату y: ");
            yLocation = ValidatorOfType.validation(1l);
        }

        return true;
    }
}
