package by.overone.clinic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static String url = "jdbc:mysql://localhost:3306/clinicDB";
    private static String dbUser = "root";
    private static String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, dbUser, password);
    }
}