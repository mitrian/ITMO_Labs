package com.mitrian.lab.utils;

public class ConsolePrinter implements Printer{

    @Override
    public void print(String printable) {
        System.out.print(printable);
    }
}
