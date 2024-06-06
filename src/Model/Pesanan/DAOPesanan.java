package Model.Pesanan;

import Model.Connector;
import Model.Jadwal.ModelJadwal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOPesanan implements InterfaceDAOpesanan {
   
    @Override
    public void insert(ModelPesanan pesanan) {
        try (Connection connection = Connector.Connect()) {  // Use try-with-resources for automatic closing
            
            // Check the capacity of the jadwal
            String cekKapasitasQuery = "SELECT kapasitas FROM jadwal WHERE id_jadwal = ?";
            try (PreparedStatement cekKapasitasStmt = connection.prepareStatement(cekKapasitasQuery)) {
                cekKapasitasStmt.setInt(1, pesanan.getId_Jadwal());
                try (ResultSet rs = cekKapasitasStmt.executeQuery()) {
                    if (rs.next()) {
                        int kapasitas = rs.getInt("kapasitas");
                        if (kapasitas >= pesanan.getjumlah()) {
                            // Reduce the capacity of the jadwal
                            String kurangiKapasitasQuery = "UPDATE jadwal SET kapasitas = kapasitas - ? WHERE id_jadwal = ?";
                            try (PreparedStatement kurangiKapasitasStmt = connection.prepareStatement(kurangiKapasitasQuery)) {
                                kurangiKapasitasStmt.setInt(1, pesanan.getjumlah());
                                kurangiKapasitasStmt.setInt(2, pesanan.getId_Jadwal());
                                kurangiKapasitasStmt.executeUpdate();

                                // Insert the order into pemesanan table
                                String insertPesananQuery = "INSERT INTO pemesanan(id_user, id_jadwal, total_harga, jumlah_tiket, total_bayar) VALUES (?, ?, ?, ?, ?)";
                                try (PreparedStatement insertStmt = connection.prepareStatement(insertPesananQuery)) {
                                    insertStmt.setInt(1, pesanan.getuserId());
                                    insertStmt.setInt(2, pesanan.getId_Jadwal());
                                    insertStmt.setInt(3, pesanan.getharga());
                                    insertStmt.setInt(4, pesanan.getjumlah());
                                    insertStmt.setInt(5, pesanan.gettotal_harga());
                                    insertStmt.executeUpdate();
                                }

                                JOptionPane.showMessageDialog(null, "Pesanan baru berhasil ditambahkan.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Kapasitas tidak mencukupi.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Input Failed: " + e.getMessage()); // Print to stderr for error logs
        }
    }


    public boolean isiKapasitasJadwalDariTeater(int idJadwal, int idTeater) {
        try (Connection connection = Connector.Connect()) {
            // Get kapasitas from teater
            String getKapasitasTeaterQuery = "SELECT kapasitas FROM teater WHERE id_teater = ?";
            try (PreparedStatement getKapasitasTeaterStmt = connection.prepareStatement(getKapasitasTeaterQuery)) {
                getKapasitasTeaterStmt.setInt(1, idTeater);
                try (ResultSet rs = getKapasitasTeaterStmt.executeQuery()) {
                    if (rs.next()) {
                        int kapasitasTeater = rs.getInt("kapasitas");

                        // Update kapasitas in jadwal
                        String updateKapasitasJadwalQuery = "UPDATE jadwal SET kapasitas = ?, id_teater = ? WHERE id_jadwal = ?";
                        try (PreparedStatement updateKapasitasJadwalStmt = connection.prepareStatement(updateKapasitasJadwalQuery)) {
                            updateKapasitasJadwalStmt.setInt(1, kapasitasTeater);
                            updateKapasitasJadwalStmt.setInt(2, idTeater);
                            updateKapasitasJadwalStmt.setInt(3, idJadwal);
                            updateKapasitasJadwalStmt.executeUpdate();
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error updating jadwal capacity: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<ModelPesanan> getAll(int userid) {
        List<ModelPesanan> listPesanan = new ArrayList<>();

        String query2 = "SELECT pemesanan.id_pemesanan, jadwal.tanggal_tayang, jadwal.jam_tayang,jadwal.studio, film.judul, teater.kelas, pemesanan.total_bayar, pemesanan.total_harga , pemesanan.jumlah_tiket " +
"FROM pemesanan " +
"INNER JOIN jadwal ON pemesanan.id_jadwal = jadwal.id_jadwal " +
"INNER JOIN film ON jadwal.id_film = film.id_film " +
"INNER JOIN teater ON jadwal.id_teater = teater.id_teater " +
"WHERE pemesanan.id_user = ?";


        try (Connection connection = Connector.Connect();
             PreparedStatement statement = connection.prepareStatement(query2)) {

            statement.setInt(1, userid);


            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ModelPesanan pesanan = new ModelPesanan();
                    pesanan.setId(resultSet.getInt("id_pemesanan"));
                    pesanan.setTanggalFromString(resultSet.getString("tanggal_tayang"));
                    pesanan.setJam(resultSet.getString("jam_tayang"));
                    pesanan.setStudio(resultSet.getInt("studio"));
                    pesanan.setJudul_Film(resultSet.getString("judul"));
                    pesanan.setTeater(resultSet.getString("kelas"));
                    pesanan.setharga(resultSet.getInt("total_harga"));
                    pesanan.setjumlah(resultSet.getInt("jumlah_tiket"));
                    pesanan.settotal_harga(resultSet.getInt("total_bayar"));
                    listPesanan.add(pesanan);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return listPesanan;
    }
}