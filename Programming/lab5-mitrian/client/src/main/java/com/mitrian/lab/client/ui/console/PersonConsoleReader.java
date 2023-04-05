package com.mitrian.lab.client.ui.console;

import com.mitrian.lab.common.commands.utils.ArgumentParser;
import com.mitrian.lab.common.commands.utils.ArgumentValidator;
import com.mitrian.lab.common.exceptions.ForcedShutdownException;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.data.Color;
import com.mitrian.lab.common.data.Country;
import com.mitrian.lab.common.data.Location;
import com.mitrian.lab.common.data.Person;
import com.mitrian.lab.common.utils.Printer;

import java.util.Scanner;

/**
 * Class for creating Person class object with initialized fields
 */
public class PersonConsoleReader {

    /** Current scanner field */
    private Scanner scanner;
    /** Current printer field */
    private Printer printer;



    /**
     * Contracture for creating object of Person Console Reader class
     * @param printer printer for initializing printer field
     * @param scanner scanner for initializing scanner field
     */
    public PersonConsoleReader(Scanner scanner, Printer printer){
        this.scanner = scanner;
        this.printer = printer;
    }


    /**
     * Reading weight for Person class from console
     * @return parsed weight
     * @throws ForcedShutdownException ctl+D exception
     */
    public Double readWeight() throws ForcedShutdownException {
        printer.print("Введите вес: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто.");
                }
                return ArgumentValidator.validationWeight(ArgumentParser.parseDouble(scanner.nextLine()));
            } catch (IncorrectFieldException e){
                printer.print(e.getMessage() + " Повторите ввод: ");
            }
        }
    }


    /**
     * Reading hairColor for Person class from console
     * @return parsed hairColor
     * @throws ForcedShutdownException ctl+D exception
     */
    public Color readColor() throws ForcedShutdownException {
        printer.print("Введите цвет волос. ");
        while (true){
            try {
                printer.print("Возможные значения: GREEN, RED, WHITE, BROWN: ");
                printer.print("");
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто.");
                }

                return ArgumentValidator.validationColor(ArgumentParser.parseColor(scanner.nextLine()));
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + " Повторите ввод: ");
            }
        }
    }


    /**
     * Reading nationality  for Person class from console
     * @return parsed nationality
     * @throws ForcedShutdownException ctl+D exception
     */
    public Country readCountry() throws ForcedShutdownException {
        printer.print("Введите национальность. ");
        while (true){
            try {
                printer.print("Введите одно из предложенных значений: GERMANY, INDIA, VATICAN: ");
                printer.print("");
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто.");
                }
                return ArgumentValidator.validationCountry(ArgumentParser.parseCountry(scanner.nextLine()));
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage()+ " Повторите ввод: ");
            }
        }
    }


    /**
     * Creating object of Person class
     * @return Person class object
     * @throws ForcedShutdownException ctl+D exception
     */
    public Person createPersonObject() throws ForcedShutdownException {
        Double weight = readWeight();
        Color hairColor = readColor();
        Country nationality = readCountry();
        LocationConsoleReader locationCreating = new LocationConsoleReader(scanner,printer);
        Location l = locationCreating.createLocationObject();
        return new Person.Builder(weight, hairColor)
                .setNationality(nationality)
                .setLocation(l)
                .build();
    }
}

