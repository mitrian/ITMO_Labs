package com.mitrian.lab.client.ui.file;

import com.mitrian.lab.common.commands.utils.parser.ArgumentParser;
import com.mitrian.lab.common.commands.utils.validator.ArgumentValidator;
import com.mitrian.lab.common.elements.Coordinates;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.exceptions.ReaderException;

import java.util.Scanner;

public class CoordinatesFileReader {

    /** Current scanner field */
    private Scanner scanner;


    /**
     * Constructor for creating object of Coordinates Console Reader class
     * @param scanner scanner for initializing scanner field
     */
    public CoordinatesFileReader(Scanner scanner){
        this.scanner = scanner;
    }


    /**
     * Reading x coordinate for Coordinates class from file
     * @return parsed x coordinate
     * @throws ReaderException wrong field for initializing
     */
    public long readXCoordinates() throws ReaderException {
        try{
            long x = ArgumentParser.parseLong(scanner.nextLine());
            if (ArgumentValidator.validationXCoordinates(x)){
                return x;
            }
            return 0L;
        } catch (IncorrectFieldException e) {
            throw new ReaderException("Ошибка при считывании поля x coordinates");
        }
    }


    /**
     * Reading y coordinate for Coordinates class from file
     * @return parsed y coordinate
     * @throws ReaderException wrong field for initializing
     */
    public Integer readYCoordinates() throws ReaderException {
        try {
            Integer y = ArgumentParser.parseInteger(scanner.nextLine());
            if (ArgumentValidator.validationYCoordinates(y)){
                return y;
            }
            return 0;
        } catch (IncorrectFieldException e) {
            throw new ReaderException("Ошибка при считывании поля y coordinates");
        }
    }


    /**
     * Creating object of Coordinates class
     * @return Coordinate class object
     * @throws ReaderException wrong field for initializing
     */
    public Coordinates createCoordinatesObject() throws ReaderException {
        long xCoordinates = readXCoordinates();
        Integer yCoordinates = readYCoordinates();
        return Coordinates.newBuilder().setX(xCoordinates).setY(yCoordinates).build();
    }
}
