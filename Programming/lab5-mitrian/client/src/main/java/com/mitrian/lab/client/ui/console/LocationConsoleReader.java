package com.mitrian.lab.client.ui.console;

import com.mitrian.lab.common.commands.utils.parser.ArgumentParser;
import com.mitrian.lab.common.commands.utils.validator.ArgumentValidator;
import com.mitrian.lab.common.exceptions.ForcedShutdownException;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.elements.Location;
import com.mitrian.lab.common.utils.Printer;
import java.util.Scanner;

/**
 * Class for creating Coordinates class object with initialized fields
 */
public class LocationConsoleReader {

    /** Current scanner field */
    private Scanner scanner;
    /** Current printer field */
    private Printer printer;


    /**
     * Constructor for creating object of Location Console Reader class
     * @param scanner scanner for initializing scanner field
     * @param printer printer for initializing printer23 field
     */
    public LocationConsoleReader(Scanner scanner, Printer printer){
        this.scanner = scanner;
        this.printer = printer;
    }


    /**
     * Reading x coordinate for Location class from console
     * @return parsed x coordinate
     * @throws ForcedShutdownException ctl+D exception
     */
    public Long readXLocation() throws ForcedShutdownException {
        printer.print("Введите координату x: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                Long x = ArgumentParser.parseLong(scanner.nextLine());
                if  (ArgumentValidator.validationXLocation(x)){
                    return x;
                }
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage()+ " Повторите ввод: ");
            }
        }
    }


    /**
     * Reading y coordinate for Location class from console
     * @return parsed y coordinate
     * @throws ForcedShutdownException ctl+D exception
     */
    public double readYLocation() throws ForcedShutdownException {
        printer.print("Введите координату y: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                double y = ArgumentParser.parseDouble(scanner.nextLine());
                if (ArgumentValidator.validationYLocation(y)){
                    return y;
                }
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + "Повторите ввод.");
            }
        }
    }



    /**
     * Reading z coordinate for Location class from console
     * @return parsed z coordinate
     * @throws ForcedShutdownException ctl+D exception
     */
    public int readZLocation() throws ForcedShutdownException {
        printer.print("Введите координату z: ");
        while (true){
            try {
                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException("Принудительно закрыто");
                }
                int z = ArgumentParser.parseInteger(scanner.nextLine());
                if (ArgumentValidator.validationZLocation(z)){
                    return z;
                }
            } catch (IncorrectFieldException e) {
                printer.print(e.getMessage() + "Повторите ввод.");
            }
        }
    }


    /**
     * Creating object of Location class
     * @return Coordinate class object
     * @throws ForcedShutdownException ctl+D exception
     */
    public Location createLocationObject() throws ForcedShutdownException {
        Long x = readXLocation();
        double y = readYLocation();
        int z = readZLocation();
        return Location.newBuilder().setX(x).setY(y).setZ(z).build();

    }
}
