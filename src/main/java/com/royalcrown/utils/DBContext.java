package com.royalcrown.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides database connection to the RoyalCrownHotel database.
 *
 * @author Nguyen Duong Y
 */
public class DBContext {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=RoyalCrownHotel;"
            + "encrypt=true;"
            + "trustServerCertificate=true";

    private static final String USER = "sa";

    private static final String PASSWORD = "Hyblu02012005@";

    /**
     * Creates a connection to the RoyalCrownHotel database.
     *
     * @return a database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}