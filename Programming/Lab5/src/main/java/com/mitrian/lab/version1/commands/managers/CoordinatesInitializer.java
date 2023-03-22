package com.mitrian.lab.version1.commands.managers;

import com.mitrian.lab.version1.source.Coordinates;
import com.mitrian.lab.version1.utils.Printer;
import com.mitrian.lab.version1.utils.exceptions.ForcedShutdownException;

public class CoordinatesInitializer {

    private Printer printer;

    public CoordinatesInitializer(Printer printer){
        this.printer = printer;
    }

    public int initializeXCoordinates() throws ForcedShutdownException {
        printer.print("Введите координату x (меньшее 884) типа int: ");
        return ValidatorOfType.validationIntegerLessNumber(884);
    }

    public Long initializeYCoordinates() throws ForcedShutdownException{
        printer.print("Введите координату y типа Long: ");
        return ValidatorOfType.validationLong();
    }

    public Coordinates createCoordinatesObject() throws ForcedShutdownException {
        int xCoordinates = initializeXCoordinates();
        Long yCoordinates = initializeYCoordinates();
        Coordinates c1 = Coordinates.newBuilder().setX(xCoordinates).setY(yCoordinates).build();
        return c1;
    }
}
