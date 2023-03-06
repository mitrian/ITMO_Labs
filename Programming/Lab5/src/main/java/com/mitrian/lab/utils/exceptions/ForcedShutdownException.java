package com.mitrian.lab.utils.exceptions;

import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class ForcedShutdownException extends Exception{

    private static final String EXCEPTION_PREFIX = "Не нажимайте ctrl+D: ";

    Printer printer = new ConsolePrinter();

    public ForcedShutdownException(){printer.print("принудительно закрыто");}
}
