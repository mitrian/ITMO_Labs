package com.mitrian.lab.client.cli;

import com.mitrian.lab.client.exceptions.ForcedShutdownException;
import com.mitrian.lab.client.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.data.Coordinates;
import com.mitrian.lab.common.utils.Printer;

import java.util.Scanner;

/**
 * Class for creating Coordinates class object with initialized fields
 */
public class CoordinatesConsoleReader {


    private Scanner scanner;
    /** Current printer field */
    private Printer printer;

    /**
     * Constructor for creating object of Coordinates Console Reader class
     * @param printer
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
                return ConsoleArgumentParser.validationLongLessNumber(scanner.nextLine(), 884L);
            } catch (IncorrectFieldException e){
                printer.print(e.getMessage()+"Повторите ввод");
            }
        }
    }


    /**
     * Reading y coordinate for Coordinates class from console
     * @return parsed y coordinate
     * @throws ForcedShutdownException ctl+D exception
     */
    public Long readYCoordinates() throws ForcedShutdownException {
        printer.print("Введите координату y типа Long: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                return ConsoleArgumentParser.validationLong(scanner.nextLine());
            } catch (IncorrectFieldException e){
                printer.print(e.getMessage()+"Повторите ввод");
            }
        }
    }


    /**
     * Creating object of Coordinates class
     * @return Coordinate class object
     * @throws ForcedShutdownException ctl+D exception
     */
    public Coordinates createCoordinatesObject() throws ForcedShutdownException {
        long xCoordinates = readXCoordinates();
        Long yCoordinates = readYCoordinates();
        return Coordinates.newBuilder().setX(xCoordinates).setY(yCoordinates).build();
    }
}
