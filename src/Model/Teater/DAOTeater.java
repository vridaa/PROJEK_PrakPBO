package Model.Teater;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOTeater implements InterfaceDAOTeater {

    @Override
    public void insert(ModelTeater teater) {
        try {
            String query = "INSERT INTO teater (kelas, harga, kapasitas) VALUES (?, ?, ?);";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, teater.getKelas());
            statement.setInt(2, teater.getHarga());
            statement.setInt(3, teater.getKapasitas());
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(ModelTeater teater) {
        try {
            String query = "UPDATE teater SET kelas=?, harga=?, kapasitas=? WHERE id_teater=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, teater.getKelas());
            statement.setInt(2, teater.getHarga());
            statement.setInt(3, teater.getKapasitas());
            statement.setInt(4, teater.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM teater WHERE id_teater=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelTeater> getAll() {
        List<ModelTeater> listTeater = new ArrayList<>();

        try {
            Statement statement = Connector.Connect().createStatement();
            String query = "SELECT * FROM teater;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ModelTeater ttr = new ModelTeater();
                ttr.setId(resultSet.getInt("id_teater"));
                ttr.setKelas(resultSet.getString("kelas"));
                ttr.setHarga(resultSet.getInt("harga"));
                ttr.setKapasitas(resultSet.getInt("kapasitas"));
               
                listTeater.add(ttr);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listTeater;
    }
    
    @Override
public List<ModelTeater> search(String text) {
    List<ModelTeater> searchResult = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        connection = Connector.Connect();
        String query = "SELECT * FROM teater WHERE kelas LIKE ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, "%" + text + "%");
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            ModelTeater teater = new ModelTeater();
            teater.setId(resultSet.getInt("id_teater"));
            teater.setKelas(resultSet.getString("kelas"));
            teater.setHarga(resultSet.getInt("harga"));
            teater.setKapasitas(resultSet.getInt("kapasitas"));

            searchResult.add(teater);
        }
    } catch (SQLException e) {
        System.out.println("Search Failed: " + e.getMessage());
    } finally {
        // Pastikan koneksi, statement, dan resultSet ditutup dalam blok finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Failed to close ResultSet: " + e.getMessage());
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Failed to close PreparedStatement: " + e.getMessage());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close Connection: " + e.getMessage());
            }
        }
    }
        return searchResult;
    }

    @Override
    public boolean isTeaterExists(String kelas, int harga, int kapasitas) {
    try {
        Connection connection = Connector.Connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM teater WHERE kelas=? AND harga=? AND kapasitas=?");
        statement.setString(1, kelas);
        statement.setInt(2, harga);
        statement.setInt(3, kapasitas);
        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet.next();
        statement.close();
        connection.close();
        return exists;
    } catch (SQLException e) {
        System.out.println("Error checking teater existence: " + e.getMessage());
        return false;
        }
    }

    @Override
    public boolean isTeaterExistsExceptId(int id, String kelas, int harga, int kapasitas) {
    try {
        Connection connection = Connector.Connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM teater WHERE kelas=? AND harga=? AND kapasitas=? AND id_teater <> ?");
        statement.setString(1, kelas);
        statement.setInt(2, harga);
        statement.setInt(3, kapasitas);
        statement.setInt(4, id);
        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet.next();
        statement.close();
        connection.close();
        return exists;
    } catch (SQLException e) {
        System.out.println("Error checking teater existence: " + e.getMessage());
        return false;
        }
    }

    public boolean isTeaterExistInJadwal(int id_teater) {
    try {
        Connection connection = Connector.Connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM jadwal WHERE id_teater=?");
        statement.setInt(1, id_teater);
        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet.next();
        statement.close();
        connection.close();
        return exists;
    } catch (SQLException e) {
        System.out.println("Error checking teater existence in schedule: " + e.getMessage());
        return false;
        }
    }

}
