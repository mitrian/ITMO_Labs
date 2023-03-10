package com.mitrian.lab.commands.managers;

import com.mitrian.lab.source.*;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.ExtraCheck;
import com.mitrian.lab.utils.Printer;
import com.mitrian.lab.utils.exceptions.ForcedShutdownException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class ValidatorOfType {
    static Printer printer = new ConsolePrinter();

    final static DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ");

    public static Long validation(long type) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                type = Long.valueOf(scanner.nextLine().trim());
                return type;

            } catch (NumberFormatException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }

    public static String validation(String type) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                type = scanner.nextLine();

                if (type != null && !type.trim().isEmpty()) {
                    return type;
                }
            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }


    public static Integer validation(int type, ExtraCheck dop, ExtraCheck dop1) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }
                int localType = type;
                String localScanner = scanner.nextLine().trim();
                type = Integer.parseInt(localScanner);
                if ( dop1.equals(ExtraCheck.NULL) && localScanner.equals("")){
                    return null;
                }
                if (dop.equals(ExtraCheck.LESS885) && type>884){
                    throw new NumberFormatException();
                }
                if (dop.equals(ExtraCheck.MORE0) && type<0){
                    throw new NumberFormatException();
                }

                return type;

            } catch (NumberFormatException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }


    public static ZonedDateTime validation(ZonedDateTime type) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
//
//            try {
//                printer.print("Пример для ввода "+ ZonedDateTime.now()+":");
//
//                if (!scanner.hasNext()) {
//                    throw new ForcedShutdownException();
//                }
//
//                type = ZonedDateTime.parse(scanner.nextLine());
//                return type;
//
//
//            } catch (DateTimeParseException e) {
//                printer.print("Введены некорректные данные, повторите ввод: "+"\n");
//            }

            try {
                printer.print("Пример для ввода( 2019-03-27 10:15:30 am -05:00 ):");

                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }


                type = ZonedDateTime.parse(scanner.nextLine(),formatter);
                return type.withZoneSameInstant(ZoneId.systemDefault());


            } catch (DateTimeParseException e) {
                printer.print("Введены некорректные данные, повторите ввод: "+"\n");
            }
        }
    }


    //разобраться как это проверить
    public static Date validation(Date type) throws ForcedShutdownException {
        while (true){
            Scanner scanner = new Scanner(System.in);

            try{
                printer.print("пример для ввода( 2008-10-28 ):");

                if (!scanner.hasNext()){
                    throw new ForcedShutdownException();
                }

                type = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

                return type;

            } catch (ParseException e){
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }

    public static Status validation(Status type) throws ForcedShutdownException {
        while (true){
            Scanner scanner = new Scanner(System.in);
            String nullChecker;

            try {
                printer.print("Предложенные значения( FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION ): ");

                printer.print("");
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                nullChecker = scanner.nextLine();

                if (nullChecker.equals("")){
                    return null;
                }

                type = Status.valueOf(nullChecker);

                return type;

            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод. ");
            }
        }
    }

    public static Double validation(Double type, ExtraCheck dop) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {

                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }
                Double localType = type;
                String localScanner = scanner.nextLine();
                type = Double.parseDouble(localScanner);
                if (dop.equals(ExtraCheck.MORE0) && type <= 0){
                    throw new NumberFormatException();
                }
                if (!Double.isInfinite(type)){
                    return type;
                }


            } catch (NumberFormatException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }

    public static Color validation(Color type) throws ForcedShutdownException {
        while (true){
            Scanner scanner = new Scanner(System.in);

            try {
                printer.print("Возмжные значения: GREEN, RED, WHITE, BROWN");

                printer.print("");
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                type = Color.valueOf(scanner.nextLine());

                return type;

            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод. ");
            }
        }
    }

    public static Country validation(Country type) throws ForcedShutdownException {
        while (true){
            Scanner scanner = new Scanner(System.in);

            try {
                printer.print("Введите одно из предложенных значений: GERMANY, INDIA, VATICAN");

                printer.print("");
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                type = Country.valueOf(scanner.nextLine().trim());

                return type;

            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }
}