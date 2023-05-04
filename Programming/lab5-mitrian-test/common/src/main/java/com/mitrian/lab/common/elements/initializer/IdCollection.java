package com.mitrian.lab.common.elements.initializer;

import java.util.HashSet;

/** Class for generating id */
public class IdCollection {

    /** Current used id */
    public static HashSet<Integer> idCollection = new HashSet<>();


    /**
     * Creating id
     * @return id
     */
    public static Integer createWorkerId(){
        Integer id;
        do{
            id = Math.toIntExact((System.currentTimeMillis() & 0xffffff));
        } while (idCollection.contains(id));
        return id;
    }
}
