package com.mitrian.lab.common.commands.utils;

import com.mitrian.lab.common.data.*;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.utils.ConsolePriner;
import com.mitrian.lab.common.utils.Printer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ArgumentValidator {

    public static String validationName(String line) throws IncorrectFieldException {
        if (line == null || line.trim().isEmpty()) {
            throw new IncorrectFieldException("Поле name не может быть пустым. ");
        }
        return line;
    }



    public static Long validationXCoordinates(Long x) throws IncorrectFieldException {
        if (x != null){
            if (x <= 884L){
                return x;
            }
        }
        throw new IncorrectFieldException("Значение поля x должно быть меньше 884.");

    }

    public static Integer validationYCoordinates(Integer y) throws IncorrectFieldException {
        if (y == null){
            throw new IncorrectFieldException("Значение поля y не может быть null");
        }
        return y;
    }

    public static LocalDate validationCreationDate(LocalDate creationDate) throws IncorrectFieldException {
        if (creationDate == null){
            throw new IncorrectFieldException("Значение поля creationDate не может быть null.");
        }
        return creationDate;
    }

    public static Float validationSalary(Float salary) throws IncorrectFieldException {
        if (salary == null){
            return null;
        }
        if (salary != Float.POSITIVE_INFINITY && salary != Float.NEGATIVE_INFINITY && salary > 0){
            return salary;
        } else {
            throw new IncorrectFieldException("Значение поля salary должно быть больше 0.");
        }
    }


    public static LocalDate validationStartDate(LocalDate startDate) throws IncorrectFieldException {
        if (startDate == null){
            throw new IncorrectFieldException("Значение поля startDate не может быть null.");
        }
        return startDate;
    }

    public static Date validationEndDate(Date endDate, LocalDate startDate ) throws IncorrectFieldException {
        if (endDate == null){
            return null;
        }
        if (endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(startDate)){
            throw new IncorrectFieldException("Значение поля endDate должно быть больше startDate.");
        }
        return endDate;
    }

    public static Status validationStatus(Status status){
        return status;
    }

    public static Person validationPerson(Person person) throws IncorrectFieldException {
        if (person == null){
            throw new IncorrectFieldException("Значени поля person не может быть null");
        }
        return person;
    }

    public static Double validationWeight(Double weight) throws IncorrectFieldException {
        if (!Double.isInfinite(weight)){
            if (weight == null || Double.compare(weight, 0) < 0) {
                throw new IncorrectFieldException("Значение поля weight должно быть больше 0");
            }
            return weight;
        }
        throw new IncorrectFieldException("Значение поля weight должно быть больше 0");

    }

    public static Color validationColor(Color color) throws IncorrectFieldException {
        if (color == null){
            throw new IncorrectFieldException("Значение поля color не может быть null");
        }
        return color;
    }

    public static Country validationCountry(Country nationality){
        return nationality;
    }

    public static Location validationLocation(Location location){
        return location;
    }

    public static Long validationXLocation(Long x) throws IncorrectFieldException {
        if (x == null){
            throw new IncorrectFieldException("Значение поля x не может быть null");
        }
        return x;
    }

    public static double validationYLocation(double y){
        return y;
    }

    public static int validationZLocation(int z){
        return z;
    }
}
