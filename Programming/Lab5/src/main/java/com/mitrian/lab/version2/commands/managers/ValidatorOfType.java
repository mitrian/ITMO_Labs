package com.mitrian.lab.version2.commands.managers;

import com.mitrian.lab.version2.Color;
import com.mitrian.lab.version2.source.Country;
import com.mitrian.lab.version2.source.Status;
import com.mitrian.lab.version2.utils.ConsolePrinter;
import com.mitrian.lab.version2.utils.ExtraCheck;
import com.mitrian.lab.version2.utils.Printer;
import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class ValidatorOfType {
    static Printer printer = new ConsolePrinter();
    static Scanner scanner = new Scanner(System.in);
    final static DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Long validationLong() throws ForcedShutdownException {

        try {
            if (!scanner.hasNextLine()) {
                throw new ForcedShutdownException();
            }

            return Long.parseLong(scanner.nextLine().trim());

        } catch (NumberFormatException e) {
            printer.print("Введены некорректные данные, повторите ввод: ");
            return validationLong();
        }

    }

    public static Long validationLongNullable() throws ForcedShutdownException {
        try {
            if (!scanner.hasNextLine()) {
                throw new ForcedShutdownException();
            }
            String localScanner = scanner.nextLine().trim();
            if ("".equals(localScanner)){
                return null;
            }
            return Long.parseLong(localScanner);

        } catch (NumberFormatException e) {
            printer.print("Введены некорректные данные, повторите ввод: ");
            return validationLongNullable();
        }
    }

    public static Float validationFloat() throws ForcedShutdownException {

        try {
            if (!scanner.hasNextLine()) {
                throw new ForcedShutdownException();
            }

            return Float.parseFloat(scanner.nextLine().trim());

        } catch (NumberFormatException e) {
            printer.print("Введены некорректные данные, повторите ввод: ");
            return validationFloat();
        }

    }

    public static Float validationFloatNullable() throws ForcedShutdownException {

        try {
            if (!scanner.hasNextLine()) {
                throw new ForcedShutdownException();
            }

            String localScanner = scanner.nextLine().trim();
            if ("".equals(localScanner)) {
                return null;
            }

            return Float.parseFloat(localScanner);

        } catch (NumberFormatException e) {
            printer.print("Введены некорректные данные, повторите ввод: ");
            return validationFloatNullable();
        }

    }

    public static String validationString() throws ForcedShutdownException {
        try {
            if (!scanner.hasNextLine()) {
                throw new ForcedShutdownException();
            }

            String userInput = scanner.nextLine();

            if (userInput == null || userInput.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
            return userInput;

        } catch (IllegalArgumentException e) {
            printer.print("Введены некорректные данные, повторите ввод: ");
            return validationString();
        }
    }


    public static Integer validationIntegerMoreNumber(Integer numberCompare) throws ForcedShutdownException {
        int number;
        while (true){
            number = ValidatorOfType.validationInteger();
            if (number>numberCompare){
                return number;
            } else {
                printer.print("Данное число должно быть больше "+numberCompare.toString()+", повторите ввод: ");
            }
        }
    }

    public static Integer validationIntegerLessNumber(Integer numberCompare) throws ForcedShutdownException {
        int number;
        while (true){
            number = ValidatorOfType.validationInteger();
            if (number<numberCompare){
                return number;
            } else {
                printer.print("Данное число должно быть больше "+numberCompare.toString()+", повторите ввод: ");
            }
        }
    }


    public static Integer validationIntegerNullable() throws ForcedShutdownException {
        try {
            if (!scanner.hasNextLine()) {
                throw new ForcedShutdownException();
            }
            String localScanner = scanner.nextLine().trim();
            if ("".equals(localScanner)) {
                return null;
            }
            return Integer.parseInt(localScanner);

        } catch (NumberFormatException e) {
            printer.print("Введены некорректные данные, повторите ввод: ");
            return validationIntegerNullable();
        }
    }

    public static Integer validationIntegerNullableAndMore(Integer numberCompare) throws ForcedShutdownException {
        try {
            if (!scanner.hasNextLine()) {
                throw new ForcedShutdownException();
            }
            String localScanner = scanner.nextLine().trim();
            if ("".equals(localScanner)) {
                return null;
            }
            return ValidatorOfType.validationIntegerNullableAndMore(numberCompare);

        } catch (NumberFormatException e) {
            printer.print("Введены некорректные данные, повторите ввод: ");
            return validationIntegerNullableAndMore(numberCompare);
        }
    }

    public static Integer validationInteger() throws ForcedShutdownException {
            try {
                if (!scanner.hasNextLine()) {
                    throw new ForcedShutdownException();
                }
                String localScanner = scanner.nextLine().trim();

                return  Integer.parseInt(localScanner);

            } catch (NumberFormatException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
                return validationInteger();
            }
        }



    public static ZonedDateTime validationZoned() throws ForcedShutdownException {
            try {
                printer.print("Пример для ввода( 2019-03-27 10:15:30 ):");

                if (!scanner.hasNextLine()) {
                    throw new ForcedShutdownException();
                }
                return ZonedDateTime.of(LocalDateTime.parse(scanner.nextLine().trim(),formatter),ZoneId.systemDefault())
                        .withZoneSameInstant(ZoneId.systemDefault());


            } catch (DateTimeParseException e) {
                printer.print("Введены некорректные данные, повторите ввод: "+"\n");
                return validationZoned();
            }
        }



    public static Date validationDate() throws ForcedShutdownException {
            try{
                printer.print("пример для ввода( 2008-10-28 ):");

                if (!scanner.hasNextLine()){
                    throw new ForcedShutdownException();
                }



                return  new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

            } catch (ParseException e){
                printer.print("Введены некорректные данные, повторите ввод: ");
                return validationDate();
            }
        }


    public static LocalDateTime validationLocalDate() throws ForcedShutdownException {
        try{
            printer.print("пример для ввода( 2008-10-28 ):");

            if (!scanner.hasNextLine()){
                throw new ForcedShutdownException();
            }

            return LocalDateTime.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        } catch (DateTimeParseException e){
            printer.print("Введены некорректные данные, повторите ввод: ");
            return validationLocalDate();
        }
    }


    public static Status validationStatusNullable() throws ForcedShutdownException {
            String nullChecker;

            try {
                printer.print("Предложенные значения( FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION ): ");

                printer.print("");
                if (!scanner.hasNextLine()) {
                    throw new ForcedShutdownException();
                }

                nullChecker = scanner.nextLine();

                if (nullChecker.equals("")){
                    return null;
                }

                return Status.valueOf(nullChecker.trim());

            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод. ");
                return validationStatusNullable();
            }
        }

    public static Double validationDouble( ExtraCheck dop) throws ForcedShutdownException {
            Double type = 0d;
            try {

                if (!scanner.hasNextLine()) {
                    throw new ForcedShutdownException();
                }
                Double localType = type;
                String localScanner = scanner.nextLine().trim();
                type = Double.parseDouble(localScanner);
                if (dop.equals(ExtraCheck.MORE0) && type <= 0){
                    throw new NumberFormatException();
                }
                if (!Double.isInfinite(type)){
                    return type;
                }


            } catch (NumberFormatException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
                return validationDouble(dop);
            }
        return type;
    }
    //add
    //da
    //2.1f
    //
    public static Color validationColor() throws ForcedShutdownException {
            try {
                printer.print("Возмжные значения: GREEN, RED, WHITE, BROWN: ");

                printer.print("");
                if (!scanner.hasNextLine()) {
                    throw new ForcedShutdownException();
                }

                return Color.valueOf(scanner.nextLine().trim());

            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод. ");
                return validationColor();
            }
        }


    public static Country validationCountryNullable() throws ForcedShutdownException {

            try {
                printer.print("Введите одно из предложенных значений: GERMANY, INDIA, VATICAN: ");

                printer.print("");
                if (!scanner.hasNextLine()) {
                    throw new ForcedShutdownException();
                }


                return Country.valueOf(scanner.nextLine().trim());

            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
                return validationCountryNullable();
            }
        }


}
