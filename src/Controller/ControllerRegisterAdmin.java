/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class ControllerRegisterAdmin {
   public boolean registerUser(String username, String password) {
        Connection connection = null;
        PreparedStatement checkStatement = null;
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;

        String checkUserSQL = "SELECT username FROM login WHERE username = ?";
        String insertUserSQL = "INSERT INTO login (username, password, role) VALUES (?, ?, ?)";

        try {
            connection = Connector.Connect();
            if (connection != null) {
                // Check if the username already exists
                checkStatement = connection.prepareStatement(checkUserSQL);
                checkStatement.setString(1, username);
                resultSet = checkStatement.executeQuery();

                if (resultSet.next()) {
                    // Username already exists
                    return false;
                }

                // Username does not exist, proceed with the insert
                insertStatement = connection.prepareStatement(insertUserSQL);
                insertStatement.setString(1, username);
                insertStatement.setString(2, password);
                insertStatement.setString(3, "admin"); // Set the default role as "pengguna"

                int affectedRows = insertStatement.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (checkStatement != null) {
                    checkStatement.close();
                }
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    } 
}
