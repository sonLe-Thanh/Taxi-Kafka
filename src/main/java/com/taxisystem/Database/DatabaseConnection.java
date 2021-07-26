package com.taxisystem.Database;

import java.sql.*;

public class DatabaseConnection {
    public static void writeToDB(int driver_id, String cell_id, double latitude, double longitude){
        String dbURL = "jdbc:postgresql://localhost:5432/taxi system?currentSchema=\"Taxi coordinate\"";
        try (Connection conn = DriverManager.getConnection(dbURL, "postgres", "dunghoinua")) {
            if (conn != null){
                System.out.println("Connection success");
                String query = "INSERT INTO Taxi(driver_id, cell_id, latitude, longitude) VALUES (?,?,?,?)";
                PreparedStatement psqlstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                psqlstmt.setInt(1, driver_id);
                psqlstmt.setString(2, cell_id);
                psqlstmt.setDouble(3, latitude);
                psqlstmt.setDouble(4, longitude);
                psqlstmt.executeUpdate();
            }
            else {
                System.out.println("Failed to connect");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
