package com.mitrian.lab;

import com.mitrian.lab.commands.AddCommand;
import com.mitrian.lab.source.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        List<Worker> workers = new LinkedList<>();


        String command;
        Scanner scanner = new Scanner(System.in);
        command = scanner.nextLine();
        if (command.equals("add")){
      //сделать tream
            String name = AddCommand.readName(scanner);
            Coordinates c = AddCommand.readCoordinates(scanner);
            Integer salary = AddCommand.readSalary(scanner);
            ZonedDateTime startDate = AddCommand.readStartDate(scanner);
            Date endDate = AddCommand.readEndDate(scanner);
            Status status = AddCommand.readStatus(scanner);
            Person p = AddCommand.readPerson(scanner);
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