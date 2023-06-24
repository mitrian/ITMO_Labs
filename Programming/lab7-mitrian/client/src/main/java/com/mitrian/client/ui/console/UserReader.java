package com.mitrian.client.ui.console;

import java.util.Scanner;

public class UserReader {
    private final Scanner scanner;

    public UserReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readUsername(){
        if(scanner.hasNextLine()){
            return scanner.nextLine().trim();
        }
        System.exit(0);
        return null;
    }

    public String readPassword(){
        if(scanner.hasNextLine()){
            return scanner.nextLine().trim();
        }
        System.exit(0);
        return null;
    }

    public String readLine(){
        if(scanner.hasNextLine()){
            return scanner.nextLine().trim();
        }
        System.exit(0);
        return null;
    }
}
