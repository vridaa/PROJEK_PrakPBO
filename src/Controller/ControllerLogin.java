package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Connector;

public class ControllerLogin {

    public String authenticate(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = Connector.Connect();

            // SQL query to check username and password
            String query = "SELECT role FROM login WHERE username = ? AND password = ?";

            // Prepare the statement
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            resultSet = statement.executeQuery();

            // If a record is found, authentication is successful
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            // Close resources to avoid resource leaks
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Authentication failed
        return null;
    }

    public boolean checkPassword(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = Connector.Connect();

            // SQL query to check the password for the given username
            String query = "SELECT password FROM login WHERE username = ?";

            // Prepare the statement
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            // Execute the query
            resultSet = statement.executeQuery();

            // If a record is found, check the password
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            // Close resources to avoid resource leaks
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Password check failed
        return false;
    }

    public boolean checkUsername(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = Connector.Connect();

            // SQL query to check if the username exists
            String query = "SELECT username FROM login WHERE username = ?";

            // Prepare the statement
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            // Execute the query
            resultSet = statement.executeQuery();

            // If a record is found, the username exists
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            // Close resources to avoid resource leaks
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Username check failed
        return false;
    }

    public int getUserId(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = Connector.Connect();

            // SQL query to get user ID
            String query = "SELECT id FROM login WHERE username = ?";

            // Prepare the statement
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            // Execute the query
            resultSet = statement.executeQuery();

            // If a record is found, return the user ID
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            // Close resources to avoid resource leaks
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // User ID retrieval failed
        return -1;
    }
}
