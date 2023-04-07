package com.mitrian.lab.common.utils;

/**
 * Class for printing
 */
public class ConsolePriner implements Printer {

    @Override
    public void print(String printable) {
        System.out.print(printable);
    }

    @Override
    public void println(String printable) {
        System.out.println(printable);
    }
}

