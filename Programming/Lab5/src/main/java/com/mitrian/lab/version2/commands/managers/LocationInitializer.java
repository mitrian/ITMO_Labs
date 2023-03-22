package com.mitrian.lab.version2.commands.managers;

import com.mitrian.lab.version2.source.Location;
import com.mitrian.lab.version2.utils.Printer;
import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

public class LocationInitializer {
    private Printer printer;

    public LocationInitializer(Printer printer){
        this.printer = printer;
    }

    public Long initializeXLocation() throws ForcedShutdownException {
        printer.print("Введите координату x: ");
        return ValidatorOfType.validationLong();
    }

    public int initializeYLocation() throws ForcedShutdownException {
        printer.print("Введите координату y: ");
        return ValidatorOfType.validationInteger();
    }

    public Float initializeZLocation() throws ForcedShutdownException {
        printer.print("Введите координату z: ");
        return ValidatorOfType.validationFloat();
    }

    public Location createLocationObject() throws ForcedShutdownException {
        long x = initializeXLocation();
        int y = initializeYLocation();
        float z = initializeZLocation();
        return Location.newBuilder().setX(x).setY(y).setZ(z).build();

    }
}
