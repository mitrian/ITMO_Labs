package com.mitrian.client.ui.console;

import com.mitrian.common.commands.util.parser.ArgumentParser;
import com.mitrian.common.commands.util.validator.ArgumentValidator;
import com.mitrian.common.exceptions.ForcedShutdownException;
import com.mitrian.common.exceptions.IncorrectFieldException;
import com.mitrian.common.elements.Coordinates;
import com.mitrian.client.util.Printer;

import java.util.Scanner;

/**
 * Class for creating Coordinates class object with initialized fields
 */
public class CoordinatesConsoleReader {

    /** Current scanner field */
    private Scanner scanner;
    /** Current printer field */
    private Printer printer;


    /**
     * Constructor for creating object of Coordinates Console Reader class
     * @param scanner scanner for initializing scanner field
     * @param printer printer for initializing printer field
     */
    public CoordinatesConsoleReader(Scanner scanner, Printer printer){
        this.scanner = scanner;
        this.printer = printer;
    }


    /**
     * Reading x coordinate for Coordinates class from console
     * @return parsed x coordinate
     * @throws ForcedShutdownException ctl+D exception
     */
    public long readXCoordinates() throws ForcedShutdownException {
        printer.print("Введите координату x (меньшее 884) типа int: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                long x = ArgumentParser.parseLong(scanner.nextLine());
                if (ArgumentValidator.validationXCoordinates(x)){
                    return x;
                }
            } catch (IncorrectFieldException e){
                printer.print(e.getMessage()+" Повторите ввод: ");
            }
        }
    }


    /**
     * Reading y coordinate for Coordinates class from console
     * @return parsed y coordinate
     * @throws ForcedShutdownException ctl+D exception
     */
    public Integer readYCoordinates() throws ForcedShutdownException {
        printer.print("Введите координату y типа Integer: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                Integer y = ArgumentParser.parseInteger(scanner.nextLine());
                if (ArgumentValidator.validationYCoordinates(y)){
                    return y;
                }
            } catch (IncorrectFieldException e){
                printer.print(e.getMessage()+" Повторите ввод: ");
            }
        }
    }


    /**
     * Creating object of Coordinates class
     * @return Coordinate class object
     * @throws ForcedShutdownException ctl+D exception
     */
    public Coordinates createCoordinatesObject() throws ForcedShutdownException {
        Long xCoordinates = readXCoordinates();
        Integer yCoordinates = readYCoordinates();
        return Coordinates.newBuilder().setX(xCoordinates).setY(yCoordinates).build();
    }
}
