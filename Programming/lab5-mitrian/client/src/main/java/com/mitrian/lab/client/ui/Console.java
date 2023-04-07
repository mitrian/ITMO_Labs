package com.mitrian.lab.client.ui;

import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.exetutors.Executor;
import com.mitrian.lab.common.utils.Printer;
import java.util.Scanner;

/**
 * Class for running this app from console
 */
public class Console {

    /** Current resolver field */
    private final Resolver resolver;
    /** Current scanner field */
    private final Scanner scanner;
    /** Current printer field */
    private final Printer printer;
    /** Current executor field */
    private final Executor executor;


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param scanner param for initialize scanner field
     * @param resolver param for initialize resolver field
     * @param executor param for initialize executor field
     */
    public Console(Printer printer, Scanner scanner, Resolver resolver, Executor executor) {
        this.resolver = resolver;
        this.printer = printer;
        this.scanner = scanner;
        this.executor = executor;
    }


    /**
     * running app from console
     */
    public void run()
    {
        while (true) {
            if (!scanner.hasNextLine()){
                printer.println("НЕ НАДО ТАК");
            }
            String input = scanner.nextLine().trim();
           try{
               AbstractCommand command = resolver.resolve(input);
               if (executor.execute(command)){
                   printer.println("------------------------------------------------------------------------");
               }
           } catch (Exception e){
               throw new RuntimeException(e);
           }
        }
    }

}
