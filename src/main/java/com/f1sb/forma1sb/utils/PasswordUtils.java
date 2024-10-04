package com.f1sb.forma1sb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Jelszóval kapcsolatos eljárások
 */
public class PasswordUtils {

    /**
     * Hashelés
     * @param password string
     * @return string, hashelt jelszó
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes); // Base64 kódolás a kimenethez
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Salt generálás a jelszóhoz
     * @return string, generált salt
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 16 byte só
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt); // Base64 kódolás
    }

}
