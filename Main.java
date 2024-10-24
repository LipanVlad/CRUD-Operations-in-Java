package org.example;

import java.sql.*;

public class Main {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/proiect1db";
    private static final String DATABASE_USERNAME = "admin";
    private static final String DATABASE_PASSWORD = "admin";

    public static void main(String[] args){

    }

    /*
        Adds a new employee in the database.
   */
    public static void addEmployee(String name, String email, double salary) {
        String query = "INSERT INTO employees (name, email, salary) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query)) {
             stmt.setString(1, name);
             stmt.setString(2, email);
             stmt.setDouble(3, salary);

             int rowsInserted = stmt.executeUpdate();
             if (rowsInserted > 0) {
                 System.out.println("A new employee was inserted successfully!");
             }
         }
             catch (SQLException e) {
                 e.printStackTrace();
        }
    }
    /*
        Returns a list of the employees that have a greater salary than the argument.
    */
    public static void compareSalary (double salary){
        String query = "SELECT * FROM employees WHERE salary > ?";
        try (Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();
            boolean check = false;
            while (rs.next()) {
                check = true;
                    System.out.println(rs.getString("name") + " has a bigger salary than " + salary);
                }
            if (check == false){
                System.out.println("No employee has a bigger salary than " + salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Can update an employee's salary.
   */

    public static void updateEmployeeSalary (double newSalary, double salary){
        String query = "UPDATE employees SET salary = ? WHERE salary < ?";
        try (Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setDouble(1, newSalary);
            stmt.setDouble(2, salary);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("All the employees were updated successfully!");
            } else {
                System.out.println("No employee found with a salary less than " + salary);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL errors
        }
    }



    /*
        Can remove employees based on their id. if multiple have the same id they will all be deleted.
    */

    public static void removeEmployee(int id){
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1,id);


            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An employee was deleted successfully!");
            } else {
                System.out.println("No employee found with the provided name.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    }



