package com.mitrian.lab.version1.commands.managers;

import com.mitrian.lab.version1.source.Color;
import com.mitrian.lab.version1.source.Country;
import com.mitrian.lab.version1.source.Location;
import com.mitrian.lab.version1.source.Person;
import com.mitrian.lab.version1.utils.ExtraCheck;
import com.mitrian.lab.version1.utils.Printer;
import com.mitrian.lab.version1.utils.exceptions.ForcedShutdownException;

public class PersonInitializer {
    private Printer printer;

    public PersonInitializer(Printer printer){
        this.printer = printer;
    }

    public Double initializeWeight() throws ForcedShutdownException {
        printer.print("Введите вес: ");
        return ValidatorOfType.validationDouble(ExtraCheck.MORE0);
    }

    public Color initializeColor() throws ForcedShutdownException{
        printer.print("Введите цвет волос. ");
        return ValidatorOfType.validationColor();
    }

    public Country initializeCountry() throws ForcedShutdownException{
        printer.print("Введите национальность. ");
        return ValidatorOfType.validationCountryNullable();
    }

    public Person createPersonObject() throws ForcedShutdownException{
        Double weight = initializeWeight();
        Color hairColor = initializeColor();
        Country nationality = initializeCountry();
        LocationInitializer locationCreating = new LocationInitializer(printer);
        Location l = locationCreating.createLocationObject();
        Person p = new Person.Builder(weight, hairColor)
                .setNationality(nationality)
                .setLocation(l)
                .build();
        return p;
    }
}
