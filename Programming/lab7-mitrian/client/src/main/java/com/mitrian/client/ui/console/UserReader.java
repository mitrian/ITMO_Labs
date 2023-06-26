package com.mitrian.client.ui.console;

import com.mitrian.client.util.Printer;
import com.mitrian.common.exceptions.ForcedShutdownException;

import java.util.Scanner;

public class UserReader {
    private final Scanner scanner;
    private final Printer printer;

    public UserReader(Scanner scanner, Printer printer) {
        this.scanner = scanner;
        this.printer = printer;

    }

    public String readUsername() throws ForcedShutdownException {
//        if(scanner.hasNextLine()){
//            return scanner.nextLine().trim();
//        }
//        System.exit(0);
//        return null;

        printer.print("Введите имя пользователя (более 5 символов и менее 50):");
        while (true){
            if (!scanner.hasNextLine()){
                throw new ForcedShutdownException("Принудительно закрыто");
            }
            String userName = scanner.nextLine().trim();
            System.out.println(userName.length());
            if (userName.length()>=5 & userName.length()<=50){
                return userName;
            }
            printer.print("Введите имя пользователя (более 4 символов и менее 50):");
        }
    }

    public String readPassword() throws ForcedShutdownException {
//        if(scanner.hasNextLine()){
//            return scanner.nextLine().trim();
//        }
//        System.exit(0);
//        return null;
        printer.print("Введите пароль пользователя (более 4 символов и менее 50):");
        while (true){
            if (!scanner.hasNextLine()){
                throw new ForcedShutdownException("Принудительно закрыто.");
            }
            String userName = scanner.nextLine().trim();
            if (userName.length()>=5 && userName.length()<=50) return userName;
            printer.print("Введите пароль пользователя (более 4 символов и менее 50):");
        }

    }

}
