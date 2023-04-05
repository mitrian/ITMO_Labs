package com.mitrian.lab.common.data.initializer;

import java.util.HashSet;

public class IdCollection {

    public static HashSet<Long> idCollection = new HashSet<>();

    public static Integer createWorkerId(){
        Integer id;
        do{
            id = Math.toIntExact((System.currentTimeMillis() & 0xffff));
        } while (idCollection.contains(id));
        return id;
    }
}
