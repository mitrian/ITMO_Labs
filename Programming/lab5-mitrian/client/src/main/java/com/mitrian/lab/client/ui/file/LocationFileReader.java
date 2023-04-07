package com.mitrian.lab.client.ui.file;

import com.mitrian.lab.common.commands.utils.ArgumentParser;
import com.mitrian.lab.common.commands.utils.ArgumentValidator;
import com.mitrian.lab.common.elements.Location;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.exceptions.ReaderException;

import java.util.Scanner;

/**
 * Class for creating Coordinates class object with initialized fields
 */
public class LocationFileReader {

    /** Current scanner field */
    private Scanner scanner;


    /**
     * Constructor for creating object of Location Console Reader class
     * @param scanner scanner for initializing scanner field
     */
    public LocationFileReader(Scanner scanner){
        this.scanner = scanner;
    }


    /**
     * Reading x coordinate for Location class from file
     * @return parsed x coordinate
     * @throws ReaderException wrong field for initializing
     */
    public Long readXLocation() throws ReaderException {
        try {
            return ArgumentValidator.validationXLocation(ArgumentParser.parseLong(scanner.nextLine()));
        } catch (Exception e) {
            throw new ReaderException("Ошибка при считывании поля x location");
        }
    }


    /**
     * Reading y coordinate for Location class from file
     * @return parsed y coordinate
     * @throws ReaderException wrong field for initializing
     */
    public double readYLocation() throws ReaderException {
        try {
            return ArgumentValidator.validationYLocation(ArgumentParser.parseDouble(scanner.nextLine()));
        } catch (IncorrectFieldException e) {
            throw new ReaderException("Ошибка при считывании поля y location");
        }
    }



    /**
     * Reading z coordinate for Location class from file
     * @return parsed z coordinate
     * @throws ReaderException wrong field for initializing
     */
    public int readZLocation() throws ReaderException {
        try{
            return ArgumentValidator.validationZLocation(ArgumentParser.parseInteger(scanner.nextLine()));
        } catch (IncorrectFieldException e) {
            throw new ReaderException("Ошибка при считывании поля z location");
        }
    }


    /**
     * Creating object of Location class
     * @return Coordinate class object
     * @throws ReaderException wrong field for initializing
     */
    public Location createLocationObject() throws ReaderException {
        Long x = readXLocation();
        double y = readYLocation();
        int z = readZLocation();
        return Location.newBuilder().setX(x).setY(y).setZ(z).build();

    }
}
