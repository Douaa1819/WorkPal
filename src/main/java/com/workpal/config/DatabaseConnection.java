package com.workpal.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
   private static String url = "jdbc:postgresql://localhost:5432/jdbc";
    // Identifiants de connexion
    private static  String user = "postgres";
    private static  String password = "password";
    public static Connection connect() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connexion réussie !");
            } else {
                System.out.println("Échec de la connexion.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return connection;
    }
        public static void main(String[] args) {
            connect();
        }
}
