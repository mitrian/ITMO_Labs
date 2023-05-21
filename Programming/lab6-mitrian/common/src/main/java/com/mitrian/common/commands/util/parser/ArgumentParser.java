package com.mitrian.common.commands.util.parser;

import com.mitrian.common.elements.Color;
import com.mitrian.common.elements.Country;
import com.mitrian.common.elements.Status;
import com.mitrian.common.exceptions.IncorrectFieldException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Class for parsing and validating string input to source classes fields
 */
public class ArgumentParser {

    /** Current format field for parsing dates */
    final static DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * Parse Long fields
     * @param line to parse
     * @return Long field
     */
    public static Long parseLong(String line) throws IncorrectFieldException
    {
        try {
            return Long.parseLong(line.trim());
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Long");
        }
    }


    /**
     * Parse Float fields
     * @param line to parse
     * @return Float field
     */
    public static Float parseFloat(String line) throws  IncorrectFieldException {
        try {
            float localFloat = Float.parseFloat(line.trim());
            if (localFloat != Float.POSITIVE_INFINITY && localFloat != Float.NEGATIVE_INFINITY){
                return localFloat;
            } else {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Float");
            }
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Float");
        }
    }


    /**
     * Parse String fields
     * @param line to parse
     * @return String field
     */
    public static String parseString(String line) throws IncorrectFieldException {
        try {
            return line;
        } catch (IllegalArgumentException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. String");
        }
    }


    /**
     * Parse Integer fields
     * @param line to parse
     * @return Integer field
     */
    public static Integer parseInteger(String line) throws IncorrectFieldException {
        try {
            return  Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Integer");
        }
    }


    /**
     * Parse Date fields
     * @param line to parse
     * @return Date field
     */
    public static Date parseDate(String line) throws IncorrectFieldException {
            try{
                if ("".equals(line) || line == null){
                    return null;
                }
                String localLine = line.trim();
                Date localDate = new SimpleDateFormat("yyyy-MM-dd").parse(localLine);
                return localDate;
            } catch (ParseException e){
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Date");
            }
        }


    /**
     * Parse LocalDate fields
     * @param line to parse
     * @return LocalDateTime field
     */
    public static LocalDate parseLocalDate(String line) throws  IncorrectFieldException {
        try{
            return LocalDate.parse(line.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e){
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. LocalDate");
        }
    }


    /**
     * Parse LocalDateTime fields
     * @param line to parse
     * @return LocalDateTime field
     */
    public static LocalDateTime parseLocalDateTime(String line) throws  IncorrectFieldException {
        try{
            return LocalDateTime.parse(line.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e){
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. LocalDateTime");
        }
    }


    /**
     * Parse Status fields which can be null
     * @return Status field
     */
    public static Status parseStatus(String line) throws IncorrectFieldException {
            try {
                if ("".equals(line)){
                    return null;
                }
                return Status.valueOf(line.trim());
            } catch (IllegalArgumentException e) {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Status");
            }
    }


    /**
     * Parse Double fields
     * @return Double field
     */
    public static Double parseDouble(String line) throws  IncorrectFieldException {
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Double");
            }
    }


    /**
     * Parse Color fields
     * @return Color field
     */
    public static Color parseColor(String line) throws IncorrectFieldException  {
            try {
                return Color.valueOf(line.trim());
            } catch (IllegalArgumentException e) {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Color");
            }
    }


    /**
     * Parse Country fields
     * @return Country field
     */
    public static Country parseCountry(String line) throws IncorrectFieldException {
        try {
            if ("".equals(line)){
                return null;
            }
            return Country.valueOf(line.trim());
        } catch (IllegalArgumentException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. Country");
        }
    }
}


