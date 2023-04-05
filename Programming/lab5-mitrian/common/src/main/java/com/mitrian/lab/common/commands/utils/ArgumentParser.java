package com.mitrian.lab.common.commands.utils;

import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.data.Color;
import com.mitrian.lab.common.data.Country;
import com.mitrian.lab.common.data.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


/**
 * Class for parsing and validating string input to source classes fields
 */
public class ArgumentParser {


    final static DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * Parse Long fields
     * @param line to parse
     * @return Long field
     */
    public static Long parseLong(String line) throws IncorrectFieldException {
        try {
            return Long.parseLong(line.trim());
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат.");
        }
    }


//    /**
//     * Parse Long fields which are more than comparable
//     * @param line to parse & number to compare with parsed line
//     * @return Long field
//     */
//    public static Long parseLongLessNumber(String line, Long numberCompare) throws  IncorrectFieldException {
//        long number;
//        try {
//
//            number = ArgumentParser.parseLong(line);
//            if (number < numberCompare) {
//                return number;
//            }
//            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. ");
// //TODO: раздели валидацию и парсинг
//        } catch (NumberFormatException e) {
//            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. ");
//        }
//
//    }




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
                throw  new NumberFormatException();
            }


        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
        }
    }



    /**
     * Parse Float fields which can be null and must be more than comparable
     * @param line to parse & number to compare with parsed line
     * @return Float field
     */
    public static Float parseFloatNullableAndMore(String line, Float numberCompare) throws IncorrectFieldException {
        float number;
        try {
            number = Float.parseFloat(line);
            if (number > numberCompare) {
                return number;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
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
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. ");
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
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат. ");
        }
    }




    /**
     * Parse Date fields
     * @param line to parse
     * @return Date field
     */
    public static Date parseDate(String line) throws IncorrectFieldException {
            try{
                if ("".equals(line)){
                    return null;
                }
                Date localDate = new SimpleDateFormat("yyyy-MM-dd").parse(line);
//                if (localDate == null){
//                    throw new ParseException("Не надо", 0);
//                }
                return localDate;
            } catch (ParseException e){
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат.");
            }
        }


    /**
     * Parse LocalDateTime fields
     * @param line to parse
     * @return LocalDateTime field
     */
    public static LocalDate parseLocalDate(String line) throws  IncorrectFieldException {
        try{
            return LocalDate.parse(line.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e){
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат.");
        }
    }

    //TODO : DateNullable

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
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат.");
            }
    }


    /**
     * Parse Double fields
     * @return Double field
     */
    public static Double parseDouble(String line) throws  IncorrectFieldException {
            try {
//                if (!scanner.hasNextLine()) {
//                    throw new ForcedShutdownException();
//                }
//                Double localType = type;
//                String localScanner = scanner.nextLine().trim();
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат.");
            }
    }


    /**
     * Parse Color fields
     * @return Color field
     */
    public static Color parseColor(String line) throws IncorrectFieldException  {
            try {
//                printer.print("Возможные значения: GREEN, RED, WHITE, BROWN: ");
//
//                printer.print("");
//                if (!scanner.hasNextLine()) {
//                    throw new ForcedShutdownException();
//                }
                return Color.valueOf(line.trim());
            } catch (IllegalArgumentException e) {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат.");
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
                    throw new IncorrectFieldException("Введенные данные имеют неправильный формат.");
                }
            }
    }


