package com.taxisystem.Database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.taxisystem.Models.Driver;

public class DatabaseConnection {
    public static void writeToDB(int driver_id, String cell_id, double latitude, double longitude, int seat){
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        try (Connection conn = DriverManager.getConnection(dbURL, "postgres", "dunghoinua")) {
            if (conn != null){
                String query = "INSERT INTO \"TaxiPosition\"(driver_id, cell_id, latitude, longitude, seat) VALUES (?,?,?,?,?)";
                PreparedStatement psqlstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                psqlstmt.setInt(1, driver_id);
                psqlstmt.setString(2, cell_id);
                psqlstmt.setDouble(3, latitude);
                psqlstmt.setDouble(4, longitude);
                psqlstmt.setInt(5, seat);
                psqlstmt.executeUpdate();
            }
            else {
                System.out.println("Failed to connect");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Driver> queryInDB1Cell(String cell_id, int seat){
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        List<Driver> listResult = new LinkedList<Driver>();
        try (Connection conn = DriverManager.getConnection(dbURL, "postgres", "dunghoinua")) {
            if (conn != null){
                String query = "SELECT * FROM \"TaxiPosition\" WHERE cell_id = ? AND seat = ?";
                PreparedStatement psqlstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                psqlstmt.setString(1, cell_id);
                psqlstmt.setInt(2, seat);
                ResultSet resultSet = psqlstmt.executeQuery();
                while (resultSet.next()){
                    int id = resultSet.getInt("driver_id");
                    double longitude = resultSet.getDouble("longitude");
                    double latitude = resultSet.getDouble("latitude");
                    Driver newDriver =  new Driver(id, longitude, latitude, seat);
                    listResult.add(newDriver);
                }
                return listResult;
            }
            else {
                System.out.println("Failed to connect");
                return listResult;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return listResult;
        }
    }

    public static void updateDB(int driver_id, String cell_id, double latitude, double longitude){
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        try (Connection conn = DriverManager.getConnection(dbURL, "postgres", "dunghoinua")) {
            if (conn != null){
                String query = "UPDATE \"TaxiPosition\" SET cell_id= ?, longitude = ?, latitude = ?  WHERE driver_id = ?";
                PreparedStatement psqlstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                psqlstmt.setString(1, cell_id);
                psqlstmt.setDouble(2, longitude);
                psqlstmt.setDouble(3, latitude);
                psqlstmt.setInt(4, driver_id);
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
