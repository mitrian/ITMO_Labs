package com.mitrian.lab.common.commands.utils.validator;

import com.mitrian.lab.common.elements.*;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Class for validating fields of data classes by parsed input
 */
public class ArgumentValidator {


    /**
     * Validator of name
     * @param line for validating
     * @return status of validation
     * @throws IncorrectFieldException field is empty
     */
    public static boolean validationName(String line) throws IncorrectFieldException {
        if (line == null || line.trim().isEmpty())
            throw new IncorrectFieldException("Поле name не может быть пустым. ");
        return true;
        }



    /**
     * Validator of xCoordiantes
     * @param x to check condition (max: 884)
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationXCoordinates(Long x) throws IncorrectFieldException {
        if (x != null){
            if (x <= 884L){
                return true;
            }
        }
        throw new IncorrectFieldException("Значение поля x должно быть меньше 884.");

    }


    /**
     * Validator of yCoordiantes
     * @param y to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationYCoordinates(Integer y) throws IncorrectFieldException {
        if (y == null){
            throw new IncorrectFieldException("Значение поля y не может быть null");
        }
        return true;
    }


    /**
     * Validator of coordinateds
     * @param coordinates to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationCoordinates(Coordinates coordinates) throws IncorrectFieldException {
        if (validationXCoordinates(coordinates.getX()) && validationYCoordinates(coordinates.getY())){
            return true;
        } else {
            throw new IncorrectFieldException("Значение поля сoordinates не валидно");
        }
    }

    /**
     * Validator of creationDate
     * @param creationDate to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationCreationDate(LocalDate creationDate) throws IncorrectFieldException {
        if (creationDate == null){
            throw new IncorrectFieldException("Значение поля creationDate не может быть null.");
        }
        return true;
    }


    /**
     * Validator of salary
     * @param salary to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationSalary(Float salary) throws IncorrectFieldException {
        if (salary > 0f || salary == null){
            return true;
        } else {
            throw new IncorrectFieldException("Значение поля salary должно быть больше 0.");
        }
    }


    /**
     * Validator of startDate
     * @param startDate to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationStartDate(LocalDate startDate) throws IncorrectFieldException {
        if (startDate == null){
            throw new IncorrectFieldException("Значение поля startDate не может быть null.");
        }
        return true;
    }


    /**
     * Validator of endDate
     * @param endDate to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationEndDate(Date endDate, LocalDate startDate ) throws IncorrectFieldException {
        if (endDate == null && startDate != null){
            return true;
        }
        assert endDate != null;
        if (endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(startDate)){
            throw new IncorrectFieldException("Значение поля endDate должно быть больше startDate.");
        }
        return true;
    }


    /**
     * Validator of status
     * @param status to check condition
     * @return status of validation
     */
    public static boolean validationStatus(Status status){
        return true;
    }


    /**
     * Validator of weight
     * @param weight to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationWeight(Double weight) throws IncorrectFieldException {
        if (!Double.isInfinite(weight)){
            if (Double.compare(weight, 0) < 0) {
                throw new IncorrectFieldException("Значение поля weight должно быть больше 0");
            }
            return true;
        }
        throw new IncorrectFieldException("Значение поля weight должно быть больше 0");
    }


    /**
     * Validator of color
     * @param color to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationHairColor(Color color) throws IncorrectFieldException {
        if (color == null){
            throw new IncorrectFieldException("Значение поля color не может быть null");
        }
        return true;
    }


    /**
     * Validator of nationality
     * @param nationality to check condition
     * @return status of validation
     */
    public static boolean validationCountry(Country nationality){
        return true;
    }


    /**
     * Validator of xLocation
     * @param x to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationXLocation(Long x) throws IncorrectFieldException {
        if (x == null){
            throw new IncorrectFieldException("Значение поля x не может быть null");
        }
        return true;
    }


    /**
     * Validator of yLocation
     * @param y to check condition
     * @return status of validation
     */
    public static boolean validationYLocation(double y){
        return true;
    }


    /**
     * Validator of zLocation
     * @param z to check condition
     * @return status of validation
     */
    public static boolean validationZLocation(int z){
        return true;
    }


    /**
     * Validator of location
     * @param location to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationLocation(Location location) throws IncorrectFieldException {
        if (validationXLocation(location.getX()) && validationYLocation(location.getY())
                && validationZLocation(location.getZ())){
            return true;
        } else {
            throw new IncorrectFieldException("Поле location не валидно");
        }
    }


    /**
     * Validator of person
     * @param person to check condition
     * @return status of validation
     * @throws IncorrectFieldException field is incorrect
     */
    public static boolean validationPerson(Person person) throws IncorrectFieldException {
        if (person == null){
            throw new IncorrectFieldException("Значение поля person не может быть null");
        }
        if (validationWeight(person.getWeight()) && validationHairColor(person.getHairColor())
                && validationCountry(person.getNationality()) && validationLocation(person.getLocation())){
            return true;
        } else {
            throw new IncorrectFieldException("Значение поля person не валидно");
        }
    }


    /**
     * Validator of id
     * @param workers Worker collection
     * @return status of validation
     */
    public static boolean validationFileId(List<Worker> workers){
        List<Integer> ids = new ArrayList<>();
        for (Worker worker: workers){
            if (!ids.contains(worker.getId())){
                ids.add(worker.getId());
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * Validator of worker
     * @param worker to check condition
     * @return status of validation
     */
    public static boolean validationWorker(Worker worker) throws IncorrectFieldException {
        if (validationName(worker.getName()) && validationCoordinates(worker.getCoordinates())
                && validationCreationDate(worker.getCreationDate()) && validationSalary(worker.getSalary())
                && validationStartDate(worker.getStartDate()) && validationEndDate(worker.getEndDate(), worker.getStartDate())
                && validationStatus(worker.getStatus()) && validationPerson(worker.getPerson())){
            return true;
        } else {
            throw new IncorrectFieldException("Поле не валидно");
        }
    }
}
