package com.mitrian.lab.client.cli;

import com.mitrian.lab.client.exceptions.ForcedShutdownException;
import com.mitrian.lab.client.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.data.Color;
import com.mitrian.lab.common.data.Country;
import com.mitrian.lab.common.data.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


/**
 * Class for parsing and validating string input to source classes fields
 */
public class ConsoleArgumentParser {


    final static DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * Parse Long fields
     * @param line to parse
     * @return Long field
     */
    public static Long validationLong(String line) throws IncorrectFieldException {

        try {
            return Long.parseLong(line.trim());
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
        }

    }


    /**
     * Parse Long fields which are more than comparable
     * @param line to parse & number to compare with parsed line
     * @return Long field
     */
    public static Long validationLongLessNumber(String line, Long numberCompare) throws  IncorrectFieldException {
        long number;
        try {

            number = ConsoleArgumentParser.validationLong(line);
            if (number < numberCompare) {
                return number;
            }
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");

        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
        }

    }


//    /**
//     * Parse Long fields which can be null
//     * @return Long field
//     * @throws ForcedShutdownException
//     */
//    public static Long validationLongNullable() throws ForcedShutdownException {
//        try {
//            if (!scanner.hasNextLine()) {
//                throw new ForcedShutdownException("Принудительно закрыто");
//            }
//            String localScanner = scanner.nextLine().trim();
//            if ("".equals(localScanner)){
//                return null;
//            }
//            return Long.parseLong(localScanner);
//
//        } catch (NumberFormatException e) {
//            printer.print("Введены некорректные данные, повторите ввод: ");
//            return validationLongNullable();
//        }
//    }


    /**
     * Parse Float fields
     * @param line to parse
     * @return Float field
     */
    public static Float validationFloat(String line) throws  IncorrectFieldException {

        try {
            float localFloat = Float.parseFloat(line.trim());
            if (!Float.isFinite(localFloat)){
                return localFloat;
            } else {
                throw  new NumberFormatException();
            }


        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
        }
    }


//    /**
//     * Parse Float fields which can be null
//     * @return Float field
//     * @throws ForcedShutdownException
//     */
//    public static Float validationFloatNullable() throws ForcedShutdownException {
//
//        try {
//            if (!scanner.hasNextLine()) {
//                throw new ForcedShutdownException("Принудительно закрыто");
//            }
//
//            String localScanner = scanner.nextLine().trim();
//            if ("".equals(localScanner)) {
//                return null;
//            }
//
//            return Float.parseFloat(localScanner);
//
//        } catch (NumberFormatException e) {
//            printer.print("Введены некорректные данные, повторите ввод: ");
//            return validationFloatNullable();
//        }
//    }


    /**
     * Parse Float fields which can be null and must be more than comparable
     * @param line to parse & number to compare with parsed line
     * @return Float field
     */
    public static Float validationFloatNullableAndMore(String line, Float numberCompare) throws IncorrectFieldException {
        float number;
        try {
            number = Float.parseFloat(line);
            if (number > numberCompare) {
                return number;
            } else {
                throw new IllegalArgumentException();
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
    public static String validationString(String line) throws IncorrectFieldException {
        try {
            if (line == null || line.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
            return line;
        } catch (IllegalArgumentException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
        }
    }


    /**
     * Parse Integer fields
     * @param line to parse
     * @return Integer field
     */
    public static Integer validationInteger(String line) throws IncorrectFieldException {
        try {
            return  Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
        }
    }


//    /**
//     * Parse Integer fields which must be more than comparable
//     * @return Integer field
//     * @throws ForcedShutdownException
//     */
//    public static Integer validationIntegerMoreNumber(Integer numberCompare) throws ForcedShutdownException {
//        int number;
//        while (true){
//            number = ConsoleArgumentParser.validationInteger();
//            if (number>numberCompare){
//                return number;
//            } else {
//                printer.print("Данное число должно быть больше "+numberCompare+", повторите ввод: ");
//            }
//        }
//    }


//    /**
//     * Parse Integer fields which must be less than comparable
//     * @return Integer field
//     * @throws ForcedShutdownException
//     */
//    public static Integer validationIntegerLessNumber(Integer numberCompare) throws ForcedShutdownException {
//        int number;
//        while (true){
//            number = ConsoleArgumentParser.validationInteger();
//            if (number<numberCompare){
//                return number;
//            } else {
//                printer.print("Данное число должно быть больше "+numberCompare+", повторите ввод: ");
//            }
//        }
//    }


//    /**
//     * Parse Integer fields which can be null
//     * @return Integer field
//     * @throws ForcedShutdownException
//     */
//    public static Integer validationIntegerNullable() throws ForcedShutdownException {
//        try {
//            if (!scanner.hasNextLine()) {
//                throw new ForcedShutdownException();
//            }
//            String localScanner = scanner.nextLine().trim();
//            if ("".equals(localScanner)) {
//                return null;
//            }
//            return Integer.parseInt(localScanner);
//
//        } catch (NumberFormatException e) {
//            printer.print("Введены некорректные данные, повторите ввод: ");
//            return validationIntegerNullable();
//        }
//    }


//    /**
//     * Parse Integer fields which can be null and must be more than comparable
//     * @return Integer field
//     * @throws ForcedShutdownException
//     */
//    public static Integer validationIntegerNullableAndMore(Integer numberCompare) throws ForcedShutdownException {
//        try {
//            if (!scanner.hasNextLine()) {
//                throw new ForcedShutdownException();
//            }
//            String localScanner = scanner.nextLine().trim();
//            if ("".equals(localScanner)) {
//                return null;
//            }
//            return ConsoleArgumentParser.validationIntegerNullableAndMore(numberCompare);
//
//        } catch (NumberFormatException e) {
//            printer.print("Введены некорректные данные, повторите ввод: ");
//            return validationIntegerNullableAndMore(numberCompare);
//        }
//    }

//
//    /**
//     * Parse ZonedDateTime fields
//     * @return ZonedDateTime field
//     * @throws ForcedShutdownException
//     */
//    public static ZonedDateTime validationZoned() throws ForcedShutdownException {
//            try {
//                printer.print("Пример для ввода( 2019-03-27 10:15:30 ):");
//
//                if (!scanner.hasNextLine()) {
//                    throw new ForcedShutdownException();
//                }
//                return ZonedDateTime.of(LocalDateTime.parse(scanner.nextLine().trim(),formatter),ZoneId.systemDefault())
//                        .withZoneSameInstant(ZoneId.systemDefault());
//
//
//            } catch (DateTimeParseException e) {
//                printer.print("Введены некорректные данные, повторите ввод: "+"\n");
//                return validationZoned();
//            }
//        }


    /**
     * Parse Date fields
     * @param line to parse
     * @return Date field
     */
    public static Date validationDate(String line) throws IncorrectFieldException {
            try{
                return  new SimpleDateFormat("yyyy-MM-dd").parse(line);
            } catch (ParseException e){
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
            }
        }


    /**
     * Parse LocalDateTime fields
     * @param line to parse
     * @return LocalDateTime field
     */
    public static LocalDateTime validationLocalDate(String line) throws  IncorrectFieldException {
        try{
            return LocalDateTime.parse(line.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e){
            throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
        }
    }


    /**
     * Parse Status fields which can be null
     * @return Status field
     */
    public static Status validationStatusNullable(String line) throws IncorrectFieldException {
            try {
                if (line.equals("")){
                    return null;
                }
                return Status.valueOf(line.trim());
            } catch (IllegalArgumentException e) {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
            }
    }


    /**
     * Parse Double fields
     * @return Double field
     */
    public static Double validationDouble(String line) throws  IncorrectFieldException {
            try {
//                if (!scanner.hasNextLine()) {
//                    throw new ForcedShutdownException();
//                }
//                Double localType = type;
//                String localScanner = scanner.nextLine().trim();
                Double type = Double.parseDouble(line);
                if (!Double.isInfinite(type)){
                    return type;
                }
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат");

            } catch (NumberFormatException e) {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
            }
    }


    /**
     * Parse Color fields
     * @return Color field
     */
    public static Color validationColor(String line) throws IncorrectFieldException  {
            try {
//                printer.print("Возможные значения: GREEN, RED, WHITE, BROWN: ");
//
//                printer.print("");
//                if (!scanner.hasNextLine()) {
//                    throw new ForcedShutdownException();
//                }
                return Color.valueOf(line.trim());
            } catch (IllegalArgumentException e) {
                throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
            }
    }


    /**
     * Parse Country fields
     * @return Country field
     */
    public static Country validationCountryNullable(String line) throws IncorrectFieldException {
            try {
                if ("".equals(line.trim())){
                    return null;
                }
                return Country.valueOf(line.trim());

            } catch (IllegalArgumentException e) {
                    throw new IncorrectFieldException("Введенные данные имеют неправильный формат");
                }
            }
    }


