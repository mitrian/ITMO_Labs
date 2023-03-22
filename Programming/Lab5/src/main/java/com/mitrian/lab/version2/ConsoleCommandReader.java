package com.mitrian.lab.version2;

import com.mitrian.lab.version2.utils.Printer;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleCommandReader {
    private Printer printer;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleCommandReader(Printer printer){
        this.printer = printer;
    }

    public String[] readLine(){
        printer.print("Введите команду: ");
        while (true){
            return scanner.nextLine().trim().split(" ");
        }
    }

    public String readConsoleCommand(){
        return readLine()[0];
    }

    public String readConsoleArguments(){
        if (readLine().length > 2){
            return "Уберите лишние данные при вводе";
        } else {
            return readLine()[1];
        }
    }
}
