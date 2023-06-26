package com.mitrian.server.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerCLIThread extends Thread{

    @Override
    public void run(){
        while (true)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                if (reader.ready())
                {
                    String line = reader.readLine().strip();
                    if ("exit".equalsIgnoreCase(line))
                        System.exit(0);
                }
            } catch (IOException ignored) {
            }
        }
    }
}
