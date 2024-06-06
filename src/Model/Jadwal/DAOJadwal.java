package Model.Jadwal;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOJadwal implements InterfaceDAOJadwal {
    
    @Override
    public List<Integer> getAllFilmIds() {
        List<Integer> filmIds = new ArrayList<>();
        try (Connection connection = Connector.Connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_film FROM film")) {
             
            while (resultSet.next()) {
                filmIds.add(resultSet.getInt("id_film"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmIds;
    }

    @Override
    public List<Integer> getAllTeaterIds() {
        List<Integer> teaterIds = new ArrayList<>();
        try (Connection connection = Connector.Connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_teater FROM teater")) {
             
            while (resultSet.next()) {
                teaterIds.add(resultSet.getInt("id_teater"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teaterIds;
    }

    @Override
    public int getHargaFilm(int filmId) {
        int harga = 0;
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement("SELECT hargafilm FROM film WHERE id_film = ?")) {
             
            statement.setInt(1, filmId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    harga = resultSet.getInt("hargafilm");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harga;
    }

    @Override
    public int getHargaTeater(int teaterId) {
        int harga = 0;
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement("SELECT harga FROM teater WHERE id_teater = ?")) {
             
            statement.setInt(1, teaterId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    harga = resultSet.getInt("harga");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harga;
    }

    public void insert(ModelJadwal jadwal) {
    try {
        int hargaFilm = getHargaFilm(jadwal.getId_film());
        int hargaTeater = getHargaTeater(jadwal.getId_teater());
        int totalHarga = hargaFilm + hargaTeater;

        String query = "INSERT INTO jadwal (id_film, id_teater, studio, kapasitas, tanggal_tayang, jam_tayang, harga) VALUES (?,?,?,?,?,?,?);";
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, jadwal.getId_film());
            statement.setInt(2, jadwal.getId_teater());
            statement.setString(3, jadwal.getStudio());
            statement.setInt(4, jadwal.getkapasitas());
            statement.setDate(5, jadwal.getTanggal_tayang()); // Pastikan format tanggal sudah sesuai dengan yang diharapkan oleh database
            statement.setString(6, jadwal.getJam_tayang());
            statement.setInt(7, totalHarga);
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("Input Failed: " + e.getLocalizedMessage());
    }
}

    @Override
    public void update(ModelJadwal jadwal) {
        String query = "UPDATE jadwal SET id_film=?, id_teater=?, studio=?, kapasitas=?, tanggal_tayang=?, jam_tayang=?, harga=? WHERE id_jadwal=?;";
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, jadwal.getId_film());
            statement.setInt(2, jadwal.getId_teater());
            statement.setString(3, jadwal.getStudio());
            statement.setInt(4, jadwal.getkapasitas());
            statement.setDate(5, jadwal.getTanggal_tayang());
            statement.setString(6, jadwal.getJam_tayang());
            statement.setInt(7, jadwal.getHarga());
            statement.setInt(8, jadwal.getId());
            statement.executeUpdate();
            System.out.println("Masuk DAO");
        } catch (SQLException e) {
            System.out.println("Update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM jadwal WHERE id_jadwal=?;";
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelJadwal> getAll() {
        List<ModelJadwal> listJadwal = new ArrayList<>();
        try (Connection connection = Connector.Connect()) {
            
            String selectQuery = "SELECT j.*, f.judul as film_judul, t.kelas as teater_nama " +
                                 "FROM jadwal j " +
                                 "INNER JOIN film f ON j.id_film = f.id_film " +
                                 "INNER JOIN teater t ON j.id_teater = t.id_teater";
            try (Statement selectStatement = connection.createStatement();
                 ResultSet resultSet = selectStatement.executeQuery(selectQuery)) {
                 
                while (resultSet.next()) {
                    ModelJadwal jdw = new ModelJadwal();
                    jdw.setId(resultSet.getInt("id_jadwal"));
                    jdw.setFilm_judul(resultSet.getString("film_judul"));
                    jdw.setTeater_nama(resultSet.getString("teater_nama"));
                    jdw.setId_teater(resultSet.getInt("id_teater"));
                    jdw.setStudio(resultSet.getString("studio"));
                    jdw.setTanggal_tayang(resultSet.getDate("tanggal_tayang"));
                    jdw.setJam_tayang(resultSet.getString("jam_tayang"));
                    jdw.setHarga(resultSet.getInt("harga"));
                    jdw.setkapasitas(resultSet.getInt("kapasitas"));
                    listJadwal.add(jdw);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listJadwal;
    }

    @Override
    public boolean isFilmExistInJadwal(Integer id) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM jadwal WHERE id_film = ?";
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    exists = resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
    
    @Override
    public boolean isTeaterExistInJadwal(Integer id) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM jadwal WHERE id_teater = ?";
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    exists = resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean isJadwalExists(int id_film, String studio, String tanggal_tayang_string, String jam_tayang) {
        String query = "SELECT * FROM jadwal WHERE id_film=? AND studio=? AND tanggal_tayang=? AND jam_tayang=?";
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, id_film);
            statement.setString(2, studio);
            statement.setString(3, tanggal_tayang_string);
            statement.setString(4, jam_tayang);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking film existence: " + e.getMessage());
            return false;
        }
    }

     public List<ModelJadwal> search(String cari) {
        List<ModelJadwal> listJadwal = new ArrayList<>();
        String query = "SELECT j.*, f.judul as film_judul, t.kelas as teater_nama " +
                       "FROM jadwal j " +
                       "INNER JOIN film f ON j.id_film = f.id_film " +
                       "INNER JOIN teater t ON j.id_teater = t.id_teater " +
                       "WHERE f.judul LIKE ?";
        try (Connection connection = Connector.Connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setString(1, "%" + cari + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ModelJadwal jdw = new ModelJadwal();
                    jdw.setId(resultSet.getInt("id_jadwal"));
                    jdw.setFilm_judul(resultSet.getString("film_judul"));
                    jdw.setTeater_nama(resultSet.getString("teater_nama"));
                    jdw.setId_teater(resultSet.getInt("id_teater"));
                    jdw.setStudio(resultSet.getString("studio"));
                    jdw.setTanggal_tayang(resultSet.getDate("tanggal_tayang"));
                    jdw.setJam_tayang(resultSet.getString("jam_tayang"));
                    jdw.setHarga(resultSet.getInt("harga"));
                    jdw.setkapasitas(resultSet.getInt("kapasitas"));
                    listJadwal.add(jdw);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listJadwal;
    }

    @Override
     public int getHargaFilmById(int id_film) {
        int harga = 0;
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement("SELECT harga FROM film WHERE id_film = ?")) {
             
            statement.setInt(1, id_film);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    harga = resultSet.getInt("harga");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harga;
    }

    public int getHargaTeaterById(int id_teater) {
        int harga = 0;
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement("SELECT harga FROM teater WHERE id_teater = ?")) {
             
            statement.setInt(1, id_teater);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    harga = resultSet.getInt("harga");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harga;
    }

    public int getKapasitasById(int id_teater) {
        int kapasitas = 0;
        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement("SELECT kapasitas FROM teater WHERE id_teater = ?")) {
             
            statement.setInt(1, id_teater);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    kapasitas = resultSet.getInt("kapasitas");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kapasitas;
    }
}