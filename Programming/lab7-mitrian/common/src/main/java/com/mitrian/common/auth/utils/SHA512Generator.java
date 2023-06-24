package com.mitrian.common.auth.utils;

import com.mitrian.common.exceptions.impl.algorithm.SHA512Exception;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512Generator {
    public static String hash(String str) throws SHA512Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(str.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new SHA512Exception("Для хеширования требуется использовать алгоритм SHA-512");
        }
    }
}
