package com.mitrian.lab.server;

import com.mitrian.lab.common.Request;
import com.mitrian.lab.common.Response;
import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.cmdclasses.ShowCommand;
import com.mitrian.lab.common.commands.utils.FileManager;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.exceptions.impl.file.FileReadableException;
import com.mitrian.lab.common.exceptions.impl.file.FileWritableException;
import com.mitrian.lab.common.exceptions.impl.file.NoSuchFileException;
import com.mitrian.lab.common.exetutors.Executor;
import com.mitrian.lab.common.utils.Printer;
import com.mitrian.lab.server.collection.Collection;
import com.mitrian.lab.server.collection.CollectionImpl;
import com.mitrian.lab.server.dao.WorkerDaoImpl;
import com.mitrian.lab.server.executors.SingleCommandExecutor;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerApp {
    // Серверный UDP-сокет запущен на этом порту
    public final static int SERVICE_PORT=8080;

    public static void main(String[] args) throws IOException, NoSuchFileException, FileWritableException, FileReadableException {
        //try {
        // Создайте новый экземпляр DatagramSocket, чтобы получать ответы от клиента
        DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);



      /* Создайте буферы для хранения отправляемых и получаемых данных.
Они временно хранят данные в случае задержек связи */
        byte[] receivingDataBuffer = new byte[10241 * 10241];
        byte[] sendingDataBuffer = new byte[10241];

        /* Создайте экземпляр UDP-пакета для хранения клиентских данных с использованием буфера для полученных данных */
        DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
        System.out.println("Waiting for a client to connect...");

        // Получите данные от клиента и сохраните их в inputPacket
        serverSocket.receive(inputPacket);

        ByteArrayInputStream bais = new ByteArrayInputStream(inputPacket.getData());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();


        //Collection<Worker> workerCollection = new CollectionImpl(FileManager.getFileByName(args[0]));
        Collection<Worker> workerCollection = new CollectionImpl(FileManager.getFileByName("Col.csv"));
        Dao<Worker> workerDao = new WorkerDaoImpl(workerCollection);
        Executor executor = new SingleCommandExecutor(workerDao);




        try (bais; baos; ObjectInputStream ois = new ObjectInputStream(bais);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            Request commandRequest = (Request) ois.readObject();
            AbstractCommand command = commandRequest.command();

            System.out.println("commandRequest");

            InetAddress senderAddress = inputPacket.getAddress();
            int senderPort = inputPacket.getPort();

            executor.execute(command);
            Response response = new Response(command != null, "выполнено");
            oos.writeObject(response);
            byte[] respBytes = baos.toByteArray();

            DatagramPacket responsePacket = new DatagramPacket(respBytes, respBytes.length, senderAddress, senderPort);
            serverSocket.send(responsePacket);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        serverSocket.close();
    }
}
