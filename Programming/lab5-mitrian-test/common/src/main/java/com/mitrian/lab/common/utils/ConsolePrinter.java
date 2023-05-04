package com.mitrian.lab.common.utils;

import java.io.Serializable;

/**
 * Class for printing
 */
public class ConsolePrinter implements Printer, Serializable {

    @Override
    public void print(String printable) {
        System.out.print(printable);
    }

    @Override
    public void println(String printable) {
        System.out.println(printable);
    }
}

