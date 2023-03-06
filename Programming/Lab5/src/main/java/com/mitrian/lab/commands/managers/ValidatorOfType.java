package com.mitrian.lab.commands.managers;

import com.mitrian.lab.source.*;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;
import com.mitrian.lab.utils.exceptions.ForcedShutdownException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ValidatorOfType {
    static Printer printer = new ConsolePrinter();

    public static Long validation(long type) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                type = Long.valueOf(scanner.nextLine());
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

                if (type != null && type.trim().isEmpty()) {
                    return type;
                }
            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }


    public static Integer validation(int type) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                type = Integer.valueOf(scanner.nextLine());
                return type;

            } catch (NumberFormatException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }


    public static ZonedDateTime validation(ZonedDateTime type) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {
                printer.print("Пример для ввода: 2023-02-21T17:41:27.4415691+03:00[Europe/Moscow]");

                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                type = ZonedDateTime.parse(scanner.nextLine());
                return type;

            } catch (DateTimeParseException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }


    //разобраться как это проверить
//    public static Date validation(String type) throws ForcedShutdownException {
//        while (true){
//            Scanner scanner = new Scanner(System.in);
//
//            try{
//                printer.print("Пример для ввода: 2008-10-28");
//
//                if (!scanner.hasNext()){
//                    throw new ForcedShutdownException();
//                }
//
//                type = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").parse(type));
//
//                return type.;
//
//            } catch (ParseException e){
//                printer.print("Введены некорректные данные, повторите ввод: ");
//            }
//        }
//    }

    public static Status validation(Status type) throws ForcedShutdownException {
        while (true){
            Scanner scanner = new Scanner(System.in);
            String nullChecker;

            try {
                printer.print("Введите одно из предложенных значений: FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION");

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
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }

    public static Double validation(Double type) throws ForcedShutdownException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {

                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }
                if (!Double.isFinite(type)){
                    type = Double.valueOf(scanner.nextLine());
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
                printer.print("Введите одно из предложенных значений: GREEN, RED, WHITE, BROWN");

                printer.print("");
                if (!scanner.hasNext()) {
                    throw new ForcedShutdownException();
                }

                type = Color.valueOf(scanner.nextLine());

                return type;

            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
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

                type = Country.valueOf(scanner.nextLine());

                return type;

            } catch (IllegalArgumentException e) {
                printer.print("Введены некорректные данные, повторите ввод: ");
            }
        }
    }
}