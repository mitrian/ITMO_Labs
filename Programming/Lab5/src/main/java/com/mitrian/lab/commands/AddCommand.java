package com.mitrian.lab.commands;

import com.mitrian.lab.source.Coordinates;

import java.util.Scanner;

public class AddCommand {
    public static Coordinates readCoordinates(Scanner scanner){
        System.out.print("Введите координату x: ");
        int x = Integer.parseInt(scanner.nextLine());
        while (!Coordinates.checkX(x)){
            System.out.print("Введена некорректная координата, ее максимальное значение = 884, повторите ввод: ");
            x = Integer.parseInt(scanner.nextLine());
        }
        System.out.print("Введите координату y: ");
        long y = Long.parseLong(scanner.nextLine());
        Coordinates c = Coordinates.newBuilder()
                .setX(x)
                .setY(y)
                .build();
        return c;
    }
}
