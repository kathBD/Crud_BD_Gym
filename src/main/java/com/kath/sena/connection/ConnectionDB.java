package com.kath.sena.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/vibrafitapp";
    private static final String USER = "root";
    private static final String PASSWORD = "Test1234";

    public static Connection connect() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("🚀 Conexión exitosa!!!");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }

        return connection;
    }
}

