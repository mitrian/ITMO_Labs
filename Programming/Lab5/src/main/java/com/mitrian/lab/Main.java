package com.mitrian.lab;

import com.mitrian.lab.commands.AddCommand;
import com.mitrian.lab.source.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        LinkedList<Worker> workers = new LinkedList<>();


        String command;
        Scanner scanner = new Scanner(System.in);
        command = scanner.nextLine();
        if (command.equals("add")){
            System.out.print("Введите имя:");
            String name = scanner.nextLine();
            Coordinates c = AddCommand.readCoordinates(scanner);
//            System.out.print("Введите координату x: ");
//            int x = Integer.parseInt(scanner.nextLine());
//            while (!Coordinates.checkX(x)){
//                System.out.print("Введена некорректная координата, ее максимальное значение = 884, повторите ввод: ");
//                x = Integer.parseInt(scanner.nextLine());
//            }
//            System.out.print("Введите координату y: ");
//            long y = Long.parseLong(scanner.nextLine());
            System.out.print("Введите зарплату работника: ");
            String localSalary = scanner.nextLine();
            Integer salary;
            if (localSalary.equals("")){
                salary = null;
            } else{
                salary = Integer.parseInt(localSalary);
            }
            System.out.println();
            System.out.print("Введите время начала работ: ");
            String localStartDate = scanner.nextLine();
            ZonedDateTime startDate;

            while (localStartDate.equals("")){
                System.out.print("Введены некорректные данные, значение не может быть пустым, повторите ввод: ");
                localStartDate = scanner.nextLine();
            }
            startDate = ZonedDateTime.parse(localStartDate);

            System.out.print("Введите дату окончания работ: ");
            String localDate = scanner.nextLine();
            Date endDate;
            if (localDate.equals("")){
                endDate = null;
            } else{
                endDate = new SimpleDateFormat("yyyy-MM-dd").parse(localDate);;
            }

            System.out.print("Введите тип работы: ");
            String localStatus = scanner.nextLine();
            Status status;
            if (localDate.equals("")){
                status = null;
            } else{
                status = Status.valueOf(localStatus);
            }


            System.out.print("Введите вес сотрудника: ");
            Double weight = Double.parseDouble(scanner.nextLine());
            while(!Person.checkWeight(weight)){
                System.out.print("Введен некорректный вес, он должен быть больше 0, повторите ввод: ");
                weight = Double.parseDouble(scanner.nextLine());
            }

            String localHairColor = scanner.nextLine();
            Color hairColor = null;
            while (localHairColor.equals("")  ){
                System.out.print("Цвет волос некорректен, повторите ввод: ");
                localHairColor = scanner.nextLine();
                try {
                    hairColor = Color.valueOf(localHairColor);
                    //yes
                } catch (IllegalArgumentException ex) {
                    localHairColor = "";
                }
            }

            String localNationality = scanner.nextLine();
            Country nationality = null;
            try {
                nationality = Country.valueOf(localNationality);
                //yes
            } catch (IllegalArgumentException ex) {
                 localNationality = "";
            }

            while (localNationality.equals("")  ){
                System.out.print("Нет опции выбора такой страны, повторите ввод: ");
                localNationality = scanner.nextLine();
                try {
                    nationality = Country.valueOf(localNationality);
                    //yes
                } catch (IllegalArgumentException ex) {
                    localNationality = "";
                }
            }

            Location location;

            System.out.print("Введите координату x: ");
            String localXLocation = scanner.nextLine();
            if (localXLocation.equals("")){
                location = null;
            } else {
              //  int xLocation = localXLocation.;
                int xLocation = Integer.parseInt(localXLocation);
                System.out.print("Введите координату y: ");
                long yLocation = Long.parseLong(scanner.nextLine());
                System.out.print("Введите координату z: ");
                float zLocation = Float.parseFloat(scanner.nextLine());
                location = Location.newBuilder()
                        .setX(xLocation)
                        .setY(yLocation)
                        .setZ(zLocation)
                        .build();
            }

            Person p = Person.newBuilder()
                    .setWeight(weight)
                    .setHairColor(hairColor)
                    .setNationality(nationality)
                    .setLocation(location).build();
//            Coordinates c = Coordinates.newBuilder()
//                    .setX(x)
//                    .setY(y)
//                    .build();
            Worker w = new Worker.Builder(name, c, startDate, p)
                    .setSalary(salary)
                    .setEndDate(endDate)
                    .setStatus(status)
                    .build();

                    //(name, new Coordinates(x, 10L), ZonedDateTime.now(),p);
            workers.add(w);
        }
    }

}