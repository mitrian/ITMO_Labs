package com.mitrian.lab.common.commands.utils;

import com.mitrian.lab.common.elements.*;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class for validating fields of data classes by parsed input
 */
public class ArgumentValidator {


    /**
     * Validator of name
     * @param line for validating
     * @return validated line
     * @throws IncorrectFieldException field is empty
     */
    public static String validationName(String line) throws IncorrectFieldException {
        if (line == null || line.trim().isEmpty()) {
            throw new IncorrectFieldException("Поле name не может быть пустым. ");
        }
        return line;
    }


    /**
     * Validator of xCoordiantes
     * @param x to check condition (max: 884)
     * @return validated xCoordinates
     * @throws IncorrectFieldException field is incorrect
     */
    public static Long validationXCoordinates(Long x) throws IncorrectFieldException {
        if (x != null){
            if (x <= 884L){
                return x;
            }
        }
        throw new IncorrectFieldException("Значение поля x должно быть меньше 884.");

    }


    /**
     * Validator of yCoordiantes
     * @param y to check condition
     * @return validated yCoordinates
     * @throws IncorrectFieldException field is incorrect
     */
    public static Integer validationYCoordinates(Integer y) throws IncorrectFieldException {
        if (y == null){
            throw new IncorrectFieldException("Значение поля y не может быть null");
        }
        return y;
    }


    /**
     * Validator of creationDate
     * @param creationDate to check condition
     * @return validated creationDate
     * @throws IncorrectFieldException field is incorrect
     */
    public static LocalDate validationCreationDate(LocalDate creationDate) throws IncorrectFieldException {
        if (creationDate == null){
            throw new IncorrectFieldException("Значение поля creationDate не может быть null.");
        }
        return creationDate;
    }


    /**
     * Validator of salary
     * @param salary to check condition
     * @return validated salary
     * @throws IncorrectFieldException field is incorrect
     */
    public static Float validationSalary(Float salary) throws IncorrectFieldException {
        if (salary > 0f){
            return salary;
        } else {
            throw new IncorrectFieldException("Значение поля salary должно быть больше 0.");
        }
    }


    /**
     * Validator of startDate
     * @param startDate to check condition
     * @return validated startDate
     * @throws IncorrectFieldException field is incorrect
     */
    public static LocalDate validationStartDate(LocalDate startDate) throws IncorrectFieldException {
        if (startDate == null){
            throw new IncorrectFieldException("Значение поля startDate не может быть null.");
        }
        return startDate;
    }


    /**
     * Validator of endDate
     * @param endDate to check condition
     * @return validated endDate
     * @throws IncorrectFieldException field is incorrect
     */
    public static Date validationEndDate(Date endDate, LocalDate startDate ) throws IncorrectFieldException {
        if (endDate == null){
            return null;
        }
        if (endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(startDate)){
            throw new IncorrectFieldException("Значение поля endDate должно быть больше startDate.");
        }
        return endDate;
    }


    /**
     * Validator of status
     * @param status to check condition
     * @return validated status
     */
    public static Status validationStatus(Status status){
        return status;
    }


    /**
     * Validator of person
     * @param person to check condition
     * @return validated person
     * @throws IncorrectFieldException field is incorrect
     */
    public static Person validationPerson(Person person) throws IncorrectFieldException {
        if (person == null){
            throw new IncorrectFieldException("Значени поля person не может быть null");
        }
        return person;
    }


    /**
     * Validator of weight
     * @param weight to check condition
     * @return validated weight
     * @throws IncorrectFieldException field is incorrect
     */
    public static Double validationWeight(Double weight) throws IncorrectFieldException {
        if (!Double.isInfinite(weight)){
            if (weight == null || Double.compare(weight, 0) < 0) {
                throw new IncorrectFieldException("Значение поля weight должно быть больше 0");
            }
            return weight;
        }
        throw new IncorrectFieldException("Значение поля weight должно быть больше 0");
    }


    /**
     * Validator of color
     * @param color to check condition
     * @return validated color
     * @throws IncorrectFieldException field is incorrect
     */
    public static Color validationColor(Color color) throws IncorrectFieldException {
        if (color == null){
            throw new IncorrectFieldException("Значение поля color не может быть null");
        }
        return color;
    }


    /**
     * Validator of nationality
     * @param nationality to check condition
     * @return validated nationality
     */
    public static Country validationCountry(Country nationality){
        return nationality;
    }


    /**
     * Validator of location
     * @param location to check condition
     */
    public static Location validationLocation(Location location){
        return location;
    }


    /**
     * Validator of xLocation
     * @param x to check condition
     * @return validated xLocation
     * @throws IncorrectFieldException field is incorrect
     */
    public static Long validationXLocation(Long x) throws IncorrectFieldException {
        if (x == null){
            throw new IncorrectFieldException("Значение поля x не может быть null");
        }
        return x;
    }


    /**
     * Validator of yLocation
     * @param y to check condition
     * @return validated yLocation
     */
    public static double validationYLocation(double y){
        return y;
    }


    /**
     * Validator of zLocation
     * @param z to check condition
     * @return validated zLocation
     */
    public static int validationZLocation(int z){
        return z;
    }


    /**
     * Validator of id
     * @param workers Worker collection
     * @return specifying a unique id
     */
    public static boolean validationFileId(List<Worker> workers){
        Set<Integer> id = new HashSet<>();
        workers.forEach(element -> id.add(element.getId()));
        return id.size() == workers.size();
    }
}
