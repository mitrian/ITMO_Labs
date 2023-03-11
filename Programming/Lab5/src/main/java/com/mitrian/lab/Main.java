package com.mitrian.lab;

import com.mitrian.lab.commands.AddCommand;
import com.mitrian.lab.commands.ClearCommand;
import com.mitrian.lab.commands.HelpCommand;
import com.mitrian.lab.commands.ShowCommand;
import com.mitrian.lab.commands.managers.CommandCollection;
import com.mitrian.lab.source.*;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;
import sun.misc.Signal;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class Main {

    public static void main(String[] args) throws ParseException {


        Coordinates c1 = Coordinates.newBuilder().setX(10).setY(13l).build();

        Location l = Location.newBuilder().setX(31).setY(31l).setZ(99).build();

        Person p = new Person.Builder(130D, Color.GREEN)
                .setNationality(Country.GERMANY)
                .setLocation(l)
                .build();

        Printer printer = new ConsolePrinter();



        CommandCollection c = new CommandCollection();




        Scanner scanner = new Scanner(System.in);

        Worker abc = new Worker.Builder("VASYA", c1, ZonedDateTime.now(), p).setCreationDate(ZonedDateTime.now()).build();
        Worker def = new Worker.Builder("VANYA", c1, ZonedDateTime.now(), p).setCreationDate(ZonedDateTime.now()).build();
        Set<String> key = CommandCollection.commandCollection.keySet();
        printer.print("Для списка возможных команд воспользуйтесь help"+"\n");

        while (true) {
            Signal.handle(new Signal("INT"),(handler)->{
                printer.print("SignalCatch");
            });
            String command;
            String[] arguments;
            while (true) {
                printer.print("Введите команду: ");
                if (!scanner.hasNext()) {
                    System.out.println("Ошибка ввода, принудительное завершение");
                    System.exit(1);
                }

                String in;
                while (true) {
                    try {
                        in = scanner.nextLine();
                        break;
                    } catch (IllegalStateException e) {
                        System.out.print("Ошибка, повторите ввод: ");
                    }
                }

                in = in.trim();
                command = in.split(" ")[0];

                String strArgs;
                try {
                    strArgs = in.replaceFirst(command, "").trim();
                } catch (PatternSyntaxException e) {
                    strArgs = "";
                }

                arguments = strArgs.split(" ");

                try {
                    if (!key.contains(command)) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Такой команды не существует повторите ввод.");
                }
            }

            if (arguments.length == 1 && arguments[0].isEmpty()) {
                if ((CommandCollection.commandCollection.get(command)).execute()) {
                    System.out.println("---Команда выполнена---");
                } else {
                    System.out.println("\n"+"---Ошибка выполнения---");
                }
            } else if ((CommandCollection.commandCollection.get(command)).execute(arguments[0])) {
                System.out.println("---Команда выполнена---");
            } else {
                System.out.println("\n"+"---Ошибка выполнения---");
            }
        }
    }
}



/*
        String command;

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

 */

