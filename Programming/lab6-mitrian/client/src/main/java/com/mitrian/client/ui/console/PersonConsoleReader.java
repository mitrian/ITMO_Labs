package com.mitrian.client.ui.console;

import com.mitrian.common.commands.util.parser.ArgumentParser;
import com.mitrian.common.commands.util.validator.ArgumentValidator;
import com.mitrian.common.exceptions.ForcedShutdownException;
import com.mitrian.common.exceptions.IncorrectFieldException;
import com.mitrian.common.elements.Color;
import com.mitrian.common.elements.Country;
import com.mitrian.common.elements.Location;
import com.mitrian.common.elements.Person;
import com.mitrian.client.util.Printer;

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
                Double weight = ArgumentParser.parseDouble(scanner.nextLine());
                if (ArgumentValidator.validationWeight(weight)){
                    return weight;
                }
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
                Color hairColor = ArgumentParser.parseColor(scanner.nextLine());
                if (ArgumentValidator.validationHairColor(hairColor)){
                    return hairColor;
                }
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
                Country nationality = ArgumentParser.parseCountry(scanner.nextLine());
                if (ArgumentValidator.validationCountry(nationality)){
                    return nationality;
                }
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

