package Model.Film;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOFilm implements InterfaceDAOFilm {

    @Override
    public void insert(ModelFilm film) {
        try {
            String query = "INSERT INTO film (judul, durasi, genre, kategori, tanggal_tayang_perdana, hargafilm) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, film.getJudul());
            statement.setInt(2, film.getDurasi());
            statement.setString(3, film.getGenre());
            statement.setString(4, film.getKategori());
            statement.setDate(5, film.getTanggal_tayang_perdana());
            statement.setInt(6, film.getHargafilm());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(ModelFilm film) {
        try {
            String query = "UPDATE film SET judul=?, durasi=?, genre=?, kategori=?, tanggal_tayang_perdana=?, hargafilm=? WHERE id_film=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, film.getJudul());
            statement.setInt(2, film.getDurasi());
            statement.setString(3, film.getGenre());
            statement.setString(4, film.getKategori());
            statement.setDate(5, film.getTanggal_tayang_perdana());
            statement.setInt(6, film.getHargafilm());
            statement.setInt(7, film.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM film WHERE id_film=?;";
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
    public List<ModelFilm> getAll() {
        List<ModelFilm> listFilm = new ArrayList<>();

        try {
            Statement statement = Connector.Connect().createStatement();
            String query = "SELECT * FROM film;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ModelFilm flm = new ModelFilm();
                flm.setId(resultSet.getInt("id_film"));
                flm.setJudul(resultSet.getString("judul"));
                flm.setDurasi(resultSet.getInt("durasi"));
                flm.setGenre(resultSet.getString("genre"));
                flm.setKategori(resultSet.getString("kategori"));
                flm.setTanggal_tayang_perdana(resultSet.getDate("tanggal_tayang_perdana"));
                flm.setHargafilm(resultSet.getInt("hargafilm"));
                listFilm.add(flm);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listFilm;
    }
    
    @Override
    public List<ModelFilm> search(String text) {
    List<ModelFilm> searchResult = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    try {
        connection = Connector.Connect();
        String query = "SELECT * FROM film WHERE judul LIKE ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, "%" + text + "%");
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            ModelFilm film = new ModelFilm();
            film.setId(resultSet.getInt("id_film"));
            film.setJudul(resultSet.getString("judul"));
            film.setDurasi(resultSet.getInt("durasi"));
            film.setGenre(resultSet.getString("genre"));
            film.setKategori(resultSet.getString("kategori"));
            film.setTanggal_tayang_perdana(resultSet.getDate("tanggal_tayang_perdana"));
            film.setHargafilm(resultSet.getInt("hargafilm"));
            searchResult.add(film);
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
    public boolean isFilmExists(String judul, int durasi, String genre, String kategori, String tanggal_tayang_perdana_string, int hargafilm) {
    try {
        Connection connection = Connector.Connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM film WHERE judul=? AND durasi=? AND genre=? AND kategori=? AND tanggal_tayang_perdana=? AND hargafilm=?");
        statement.setString(1, judul);
        statement.setInt(2, durasi);
        statement.setString(3, genre);
        statement.setString(4, kategori);
        statement.setString(5, tanggal_tayang_perdana_string);
        statement.setInt(6, hargafilm);
        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet.next();
        statement.close();
        connection.close();
        return exists;
    } catch (SQLException e) {
        System.out.println("Error checking film existence: " + e.getMessage());
        return false;
        }
    }

    @Override
    public boolean isFilmExistsExceptId(int id, String judul, int durasi, String genre, String kategori, String tanggal_tayang_perdana_string, int hargafilm) {
    try {
        Connection connection = Connector.Connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM film WHERE judul=? AND durasi=? AND genre=? AND kategori=? AND tanggal_tayang_perdana=? AND hargafilm=? AND id_film <> ?");
        statement.setString(1, judul);
        statement.setInt(2, durasi);
        statement.setString(3, genre);
        statement.setString(4, kategori);
        statement.setString(5, tanggal_tayang_perdana_string);
        statement.setInt(6, hargafilm);
        statement.setInt(7, id);
        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet.next();
        statement.close();
        connection.close();
        return exists;
    } catch (SQLException e) {
        System.out.println("Error checking film existence: " + e.getMessage());
        return false;
    }
}

public boolean isFilmExistInJadwal(int id_film) {
    try {
        Connection connection = Connector.Connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM jadwal WHERE id_film=?");
        statement.setInt(1, id_film);
        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet.next();
        statement.close();
        connection.close();
        return exists;
    } catch (SQLException e) {
        System.out.println("Error checking film existence in schedule: " + e.getMessage());
        return false;
        }
    }

}
