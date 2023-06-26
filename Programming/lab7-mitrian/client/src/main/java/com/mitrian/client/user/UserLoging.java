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
            ) {
        this.printer = printer;
        this.scanner = scanner;
        this.adressTo = adressTo;
        this.adressFrom = adressFrom;
        this.userReader = userReader;
    }


    public RegisterRequest register(User user) {
       return new RegisterRequest(adressFrom, adressTo, new User(user.getUserName(), user.getPassword()));
    }


    public LoginRequest login(User user) {
        return new LoginRequest(adressFrom, adressTo, new User(user.getUserName(), user.getPassword()));
    }

    public User readUserData() throws ForcedShutdownException {
        String userName = userReader.readUsername();
        String password = userReader.readPassword();
        return new User(userName, password);
    }
}