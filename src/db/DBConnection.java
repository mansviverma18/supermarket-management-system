package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL  = "jdbc:mysql://localhost:3306/supermarket";
    private static final String USER = "root";
    private static final String PASS = "Kiet@1234";          // ← your MySQL password

    public static Connection get() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

