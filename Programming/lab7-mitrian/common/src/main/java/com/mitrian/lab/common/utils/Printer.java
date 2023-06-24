package com.mitrian.lab.common.utils;

import java.io.Serializable;

/**
 * Printer interface
 */
public interface Printer extends Serializable {
    void print(String printable);
    void println(String printable);
}
