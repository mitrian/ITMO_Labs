package com.mitrian.lab;

import java.util.HashSet;

public class IdCollection {

    public static HashSet<Long> idCollection = new HashSet<>();

    public static long createWorkerId(){
        long id;
        do{
            id = (System.currentTimeMillis() & 0xffff);
        } while (idCollection.contains(id));
        return id;
    }

}
