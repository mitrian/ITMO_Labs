//package com.mitrian.lab.commands;
//
//import com.mitrian.lab.source.*;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeParseException;
//import java.util.Date;
//import java.util.Scanner;
//
//public class TrashCommand {
//
//    //private final WorkersCollectionReceiver receiver;
////    public AddCommand(WorkersCollectionReceiver receiver) {
////        this.receiver = receiver;
////    }
//
//    public static String readName(Scanner scanner){
//        System.out.print("Введите имя: ");
//        String localName = scanner.nextLine();
//        String name = "";
//        while (localName.equals("")){
//            System.out.print("Имя не может быть пустым, повторите ввод: ");
//            localName = scanner.nextLine();
//            if (localName.equals("")){
//                continue;
//            }
//        }
//        name = localName;
//        return name;
//    }
//
//    public static Coordinates readCoordinates(Scanner scanner){
////        System.out.print("Введите координату x: ");
////        String localX = scanner.nextLine();
////        int x = 0;
////        while (localX.equals("")){
////            System.out.println("int nado");
////            localX = scanner.nextLine();
////            if (localX.equals("")){
////                continue;
////            }
////            try{
////                x = Integer.parseInt(localX);
////            } catch (NumberFormatException e){
////                localX = "";
////                continue;
////            }
////            if (!Coordinates.checkX(x)){
////                System.out.print("Введена некорректная координата, ее максимальное значение = 884, повторите ввод: ");
////                localX = "";
////            }
//        String localX = "";
//        int x = 0;
//        System.out.print("Введите координату x: ");
//        while (localX.equals("")){
//            localX = scanner.nextLine();
//            if (localX.equals("")){
//                System.out.print("Введена некорректная координата, ее значение не может быть пустым, повторите ввод: ");
//                localX = "";
//                continue;
//            }
//            try{
//                x = Integer.parseInt(localX);
//            } catch (NumberFormatException e){
//                System.out.print("Введена некорректная координата, ее значение должно быть типа int, повторите ввод: ");
//                localX = "";
//                continue;
//            }
//            if (!Coordinates.checkX(x)){
//                System.out.print("Введена некорректная координата, ее максимальное значение = 884, повторите ввод: ");
//                localX = "";
//            }
//        }
//        String localY = "";
//        System.out.print("Введите координату y: ");
//        long y = 0;
//        while (localY.equals("")){
//            localY = scanner.nextLine();
//            if (localY.equals("")){
//                System.out.print("Введена некорректная координата, ее значение не может быть пустым, повторите ввод: ");
//                continue;
//            }
//            try{
//                y = Long.parseLong(localY);
//            } catch (NumberFormatException e){
//                System.out.print("Введена некорректная координата, ее значение должно быть типа long, повторите ввод: ");
//                localY = "";
//                continue;
//            }
//        }
//        Coordinates c = Coordinates.newBuilder()
//                .setX(x)
//                .setY(y)
//                .build();
//        return c;
//    }
//
//    public static Integer readSalary(Scanner scanner){
//        System.out.print("Введите зарплату сотрудника: ");
//        int salary = 0;
//        while (salary == 0){
//            String localSalary = scanner.nextLine();
//            if (localSalary.equals("")){
//                return null;
//            }
//            try{
//                salary = Integer.parseInt(localSalary);
//            } catch (NumberFormatException e){
//                System.out.println("Введена некорректная координата, ее значение должно быть типа int, повторите ввод: ");
//                salary = 0;
//                continue;
//            }
//            if (!Worker.checkSalary(salary)){
//                //System.out.print("Введена некорректная координата, ее максимальное значение = 884, повторите ввод: ");
//                System.out.println("Значение должно быть больше 0, повторите ввод: ");
//                salary = 0;
//            }
//        }
//        return salary;
//    }
//
//    public static ZonedDateTime readStartDate(Scanner scanner){
//        System.out.print("Введите время начала работ: ");
//        String localStartDate = scanner.nextLine();
//        ZonedDateTime startDate = null;
//
//        while (localStartDate.equals("")){
//            localStartDate = scanner.nextLine();
//            if (localStartDate.equals("")){
//                System.out.print("Введены некорректные данные, значение не может быть пустым, повторите ввод: ");
//                continue;
//            }
//            try {
//                startDate = ZonedDateTime.parse(localStartDate);
//            } catch (DateTimeParseException e) {
//                System.out.print("Формат даты не соответствует ZonedDateTime, повторите ввод");
//                localStartDate = "";
//            }
//        }
//        return startDate;
//    }
//
//    public static Date readEndDate(Scanner scanner) {
//
////        System.out.print("Введите дату окончания работ: ");
////        String localDate = null;
////        Date endDate = null;
////        while (endDate == null){
////            String localDate = scanner.nextLine();
////            if (localDate.equals("")) {
////                return null;
////            }
////            try{
////                endDate = new SimpleDateFormat("yyyy-MM-dd").parse(localDate);
////            } catch (ParseException e){
////                System.out.println("Значение даты не соответствует формату, повторите ввод: ");
////                endDate = null;
////
////            }
////
////        }
////       return endDate;
//        System.out.print("Введите дату окончания работ: ");
//        String localDate = null;
//        Date endDate = null;
//        while (localDate == null){
//            localDate = scanner.nextLine();
//            if (localDate.equals("")) {
//                return null;
//            }
//            try{
//                endDate = new SimpleDateFormat("yyyy-MM-dd").parse(localDate);
//            } catch (ParseException e){
//                System.out.println("Значение даты не соответствует формату, повторите ввод: ");
//                localDate = null;
//            }
//
//        }
//        return endDate;
//    }
//
//    public static Status readStatus(Scanner scanner){
//        System.out.print("Введите тип работы: ");
//        Status status = null;
//        while (status == null){
//            String localStatus = scanner.nextLine();
//            if (localStatus.equals("")) {
//                return null;
//            }
//            try{
//                status = Status.valueOf(localStatus);
//            } catch (IllegalArgumentException e){
//                status = null;
//                System.out.println("Такого типа работы не существует, повторите ввод: ");
//            }
//
//        }
//        return status;
//    }
//
//    public static Person readPerson(Scanner scanner){
//        Double weight = readPersonWeight(scanner);
//        Color hairColor = readPersonHairColor(scanner);
//        Country nationality = readPersonNationality(scanner);
//        Location location = readPersonLocation(scanner);
//        Person p = new Person.Builder(weight, hairColor)
//                .setNationality(nationality)
//                .setLocation(location)
//                .build();
//        return p;
//    }
//
//    private static Double readPersonWeight(Scanner scanner){
//
//        System.out.print("Введите вес сотрудника: ");
//        String localWeight = scanner.nextLine();
//        Double weight = null;
//
//        while (localWeight.equals("")){
//            localWeight = scanner.nextLine();
//            if (localWeight.equals("")){
//                System.out.print("Введены некорректные данные, значение не может быть пустым, повторите ввод: ");
//                continue;
//            }
//            try {
//                weight = Double.parseDouble(localWeight);
//            } catch (NumberFormatException e) {
//                localWeight = "";
//                continue;
//            }
//            if (!Person.checkWeight(weight)){
//                localWeight = "";
//                System.out.print("Введен некорректный вес, он должен быть больше 0, повторите ввод: ");
//            }
//        }
//        return weight;
//    }
//
//    private static Color readPersonHairColor(Scanner scanner){
//        System.out.print("Введите вес сотрудника: ");
//        String localHairColor = scanner.nextLine();
//        Color hairColor = null;
//
//        while (localHairColor.equals("")){
//            System.out.print("Введены некорректные данные, значение не может быть пустым, повторите ввод: ");
//            localHairColor = scanner.nextLine();
//            if (localHairColor.equals("")){
//                continue;
//            }
//            try {
//                hairColor = Color.valueOf(localHairColor);
//            } catch (IllegalArgumentException e) {
//                localHairColor = "";
//                continue;
//            }
//        }
//        return hairColor;
//    }
//
//    private static Country readPersonNationality (Scanner scanner){
//        System.out.print("Введите страну: ");
//        Country nationality = null;
//        while (nationality == null){
//            String localNationality = scanner.nextLine();
//            if (localNationality.equals("")) {
//                return null;
//            }
//            try{
//                nationality = Country.valueOf(localNationality);
//            } catch (IllegalArgumentException e){
//                nationality = null;
//                System.out.println("format");
//            }
//
//        }
//        return nationality;
//    }
//
//    private static Location readPersonLocation (Scanner scanner){
//        System.out.print("Введите координату x: ");
//        String localX = scanner.nextLine();
//        int x = 0;
//        if (localX.equals("")){
//            return null;
//        }
//        while (x == 0){
//            System.out.println("int nado");
//            localX = scanner.nextLine();
//            if (localX.equals("")){
//                return null;
//            }
//            try{
//                x = Integer.parseInt(localX);
//            } catch (NumberFormatException e){
//                x = 0;
//            }
//        }
//        System.out.print("Введите координату y: ");
//        String localY = scanner.nextLine();
//        long y = 0;
//        while (localY.equals("")){
//            System.out.println("int nado");
//            localY = scanner.nextLine();
//            if (localY.equals("")){
//                continue;
//            }
//            try{
//                y = Long.parseLong(localY);
//            } catch (NumberFormatException e){
//                localY = "";
//            }
//        }
//        System.out.print("Введите координату z: ");
//        String localZ = scanner.nextLine();
//        float z = 0f;
//        while (localZ.equals("")){
//            System.out.println("int nado");
//            localZ = scanner.nextLine();
//            if (localZ.equals("")){
//                continue;
//            }
//            try{
//                z = Float.parseFloat(localZ);
//            } catch (NumberFormatException e){
//                localZ = "";
//
//            }
//        }
//        Location l = Location.newBuilder()
//                .setX(x)
//                .setY(in
//                .setZ(z)
//                .build();
//        return l;
//    }
//
//}
