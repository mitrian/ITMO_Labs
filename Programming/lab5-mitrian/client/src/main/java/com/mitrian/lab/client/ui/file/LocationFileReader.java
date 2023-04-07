package com.mitrian.lab.client.ui.file;

import com.mitrian.lab.common.commands.utils.parser.ArgumentParser;
import com.mitrian.lab.common.commands.utils.validator.ArgumentValidator;
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
            Long x = ArgumentParser.parseLong(scanner.nextLine());
            if  (ArgumentValidator.validationXLocation(x)){
                return x;
            }
            return 0L;
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
            double y = ArgumentParser.parseDouble(scanner.nextLine());
            if (ArgumentValidator.validationYLocation(y)){
                return y;
            }
            return 0d;
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
            int z = ArgumentParser.parseInteger(scanner.nextLine());
            if (ArgumentValidator.validationZLocation(z)){
                return z;
            }
            return 0;
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
