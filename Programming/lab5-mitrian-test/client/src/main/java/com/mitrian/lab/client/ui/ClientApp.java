package com.mitrian.lab.client.ui;

import com.mitrian.lab.common.Request;
import com.mitrian.lab.common.Response;
import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.exceptions.ForcedShutdownException;
import com.mitrian.lab.common.utils.Printer;
import sun.misc.Signal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Class for running this app from console
 */
public class ClientApp {

    /** Current resolver field */
    private final Resolver resolver;
    /** Current scanner field */
    private final Scanner scanner;
    /** Current printer field */
    private final Printer printer;


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param scanner param for initialize scanner field
     * @param resolver param for initialize resolver field
     */
    public ClientApp(Printer printer, Scanner scanner, Resolver resolver) {
        this.resolver = resolver;
        this.printer = printer;
        this.scanner = scanner;
    }


    /**
     * running app from console
     */
    public void run() throws ForcedShutdownException {
        while (true) {
            Signal.handle(new Signal("INT"),(handler)->{
                printer.print("SignalCatch");
            });
            if (!scanner.hasNextLine()){
                throw new ForcedShutdownException("Не надо так");
            }
            String input = scanner.nextLine().trim();
            try{

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                ObjectOutputStream oos = new ObjectOutputStream(baos);


                AbstractCommand command = resolver.resolve(input);

                byte[] responses = new byte[10241*10241];

                InetSocketAddress serverAddress = new InetSocketAddress(8080);

                Request request = new Request(command);

                try (DatagramSocket datagramSocket = new DatagramSocket(8887)) {

                    System.out.println("0");
                    oos.writeObject(request);
                    System.out.println("1");
                    byte[] arr = baos.toByteArray();
                    DatagramPacket datagramPacket = new DatagramPacket(arr,arr.length, serverAddress);
                    datagramSocket.send(datagramPacket);
                    System.out.println("5");
                    DatagramPacket responsePacket = new DatagramPacket(responses,responses.length);
                    datagramSocket.receive(responsePacket);
                    ByteArrayInputStream bais = new ByteArrayInputStream(responsePacket.getData());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Response response = (Response) ois.readObject();



                } catch (Exception e){
                   System.out.println(e.getMessage()+" EXCEPTION");
                } finally {
                    oos .close();
                    baos.close();
                }

//               if (executor.execute(command)){
//
//                   printer.println("----------------------------------------------------------------------------------------");
//               }
            } catch (Exception e){
                printer.println(e.getMessage());
            }
        }
    }

}
