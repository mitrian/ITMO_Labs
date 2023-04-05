package com.mitrian.lab.client.ui.file;

import com.mitrian.lab.common.commands.utils.ArgumentParser;
import com.mitrian.lab.common.commands.utils.ArgumentValidator;
import com.mitrian.lab.common.data.Color;
import com.mitrian.lab.common.data.Country;
import com.mitrian.lab.common.data.Location;
import com.mitrian.lab.common.data.Person;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.exceptions.impl.ReaderException;

import java.util.Scanner;

public class PersonFileReader {
    /** Current scanner field */
    private Scanner scanner;


    /**
     * Contracture for creating object of Person Console Reader class
     * @param scanner scanner for initializing scanner field
     */
    public PersonFileReader(Scanner scanner){
        this.scanner = scanner;
    }


    /**
     * Reading weight for Person class from file
     * @return parsed weight
     * @throws ReaderException wrong field for initializing
     */
    public Double readWeight() throws ReaderException {
        try {
            return ArgumentValidator.validationWeight(ArgumentParser.parseDouble(scanner.nextLine()));
        } catch (IncorrectFieldException e) {
            throw new ReaderException("Ошибка при считывании поля weight");
        }
    }


    /**
     * Reading hairColor for Person class from file
     * @return parsed hairColor
     * @throws ReaderException wrong field for initializing
     */
    public Color readColor() throws ReaderException {
       try {
           return ArgumentValidator.validationColor(ArgumentParser.parseColor(scanner.nextLine()));
       } catch (IncorrectFieldException e){
           throw new ReaderException("Ошибка при считывании поля color");
       }
    }


    /**
     * Reading nationality  for Person class from file
     * @return parsed nationality
     * @throws ReaderException wrong field for initializing
     */
    public Country readCountry() throws ReaderException {
        try{
            return ArgumentValidator.validationCountry(ArgumentParser.parseCountry(scanner.nextLine()));
        } catch (IncorrectFieldException e) {
            throw new ReaderException("Ошибка при считывании поля country");
        }
    }


    /**
     * Creating object of Person class
     * @return Person class object
     * @throws ReaderException wrong field for initializing
     */
    public Person createPersonObject() throws ReaderException {
        Double weight = readWeight();
        Color hairColor = readColor();
        Country nationality = readCountry();
        LocationFileReader locationCreating = new LocationFileReader(scanner);
        Location l = locationCreating.createLocationObject();
        return new Person.Builder(weight, hairColor)
                .setNationality(nationality)
                .setLocation(l)
                .build();
    }
}