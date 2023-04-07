package com.mitrian.lab.client.ui.console;

import com.mitrian.lab.common.commands.utils.ArgumentParser;
import com.mitrian.lab.common.commands.utils.ArgumentValidator;
import com.mitrian.lab.common.exceptions.ForcedShutdownException;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.elements.Coordinates;
import com.mitrian.lab.common.utils.Printer;

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
                return ArgumentValidator.validationXCoordinates(ArgumentParser.parseLong(scanner.nextLine()));
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
                return ArgumentValidator.validationYCoordinates(ArgumentParser.parseInteger(scanner.nextLine()));
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
