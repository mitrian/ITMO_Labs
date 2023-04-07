package com.mitrian.lab.client.ui.file;

import com.mitrian.lab.common.commands.utils.parser.ArgumentParser;
import com.mitrian.lab.common.commands.utils.validator.ArgumentValidator;
import com.mitrian.lab.common.elements.Color;
import com.mitrian.lab.common.elements.Country;
import com.mitrian.lab.common.elements.Location;
import com.mitrian.lab.common.elements.Person;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.exceptions.ReaderException;

import java.util.Scanner;

public class PersonFileReader {

    /** Current scanner field */
    private Scanner scanner;


    /**
     * Constructor for creating object of Person Console Reader class
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
            Double weight = ArgumentParser.parseDouble(scanner.nextLine());
            if (ArgumentValidator.validationWeight(weight)){
                return weight;
            }
            return 0d;
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
           Color hairColor = ArgumentParser.parseColor(scanner.nextLine());
           if (ArgumentValidator.validationHairColor(hairColor)){
               return hairColor;
           }
           return null;
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
            Country nationality = ArgumentParser.parseCountry(scanner.nextLine());
            if (ArgumentValidator.validationCountry(nationality)){
                return nationality;
            }
            return null;
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
