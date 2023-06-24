package com.mitrian.client.user;

import com.mitrian.client.ui.console.UserReader;
import com.mitrian.client.util.Printer;
import com.mitrian.common.auth.User;
import com.mitrian.common.exceptions.ForcedShutdownException;
import com.mitrian.common.network.model.request.LoginRequest;
import com.mitrian.common.network.model.request.RegisterRequest;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class UserLoging {

    private final Printer printer;

    private final Scanner scanner;

    private final InetSocketAddress adressTo;

    private final InetSocketAddress adressFrom;

    private final UserReader userReader;

    public UserLoging
            (
            Scanner scanner,
            Printer printer,
            InetSocketAddress adressTo,
            InetSocketAddress adressFrom,
            UserReader userReader
            ) throws ForcedShutdownException {
        this.printer = printer;
        this.scanner = scanner;
        this.adressTo = adressTo;
        this.adressFrom = adressFrom;
        this.userReader = userReader;
    }


    public RegisterRequest register() throws ForcedShutdownException {
       User user = readUserData();
       return new RegisterRequest(adressFrom, adressTo, new User(user.getUserName(), user.getPassword()));
    }


    public LoginRequest login() throws ForcedShutdownException {
        User user = readUserData();
        return new LoginRequest(adressFrom, adressTo, new User(user.getUserName(), user.getPassword()));
    }

    public User readUserData(){
        printer.println("Введите имя пользователя (более 5 символов и менее 50):");
        String username = userReader.readUsername();
        while(username.length()<=4 || username.length() > 50){
            printer.println("Имя пользователя должно содержать более 5 символов");
            username = userReader.readUsername();
        }
        printer.println("Введите пароль(более 5 символов и менее 50): ");
        String password = userReader.readPassword();
        while(password.length()<=4 ||password.length()> 50){
            printer.println("Пароль должен содержать более 5 символов и менее 50 символов");
            password = userReader.readPassword();
        }
        return new User(username, password);
    }
}