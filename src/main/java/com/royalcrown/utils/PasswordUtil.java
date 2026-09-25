package com.royalcrown.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for password hashing and password verification.
 *
 * @author Nguyen Duong Y
 */
public class PasswordUtil {

    /**
     * Hashes a plain-text password using SHA-256.
     *
     * @param password the plain-text password
     * @return the SHA-256 hashed password in hexadecimal format
     */
    public static String hashPassword(String password) {

        if (password == null) {
            return null;
        }

        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance("SHA-256");

            byte[] hash = messageDigest.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "SHA-256 algorithm is not available.", e
            );
        }
    }

    /**
     * Verifies a plain-text password against a hashed password.
     *
     * @param password the plain-text password entered by the user
     * @param hashedPassword the password hash stored in the database
     * @return true if the password matches; otherwise false
     */
    public static boolean verifyPassword(
            String password,
            String hashedPassword) {

        if (password == null || hashedPassword == null) {
            return false;
        }

        String inputHash = hashPassword(password);

        return inputHash.equalsIgnoreCase(hashedPassword);
    }
}