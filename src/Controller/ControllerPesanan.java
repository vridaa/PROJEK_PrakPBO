package Controller;

import Model.Connector;
import Model.Pesanan.DAOPesanan;
import Model.Pesanan.InterfaceDAOpesanan;
import Model.Pesanan.ModelPesanan;
import Model.Pesanan.ModelTable;
import View.PenggunaPage;
import View.RiwayatPesanan;
import java.sql.*;


import javax.swing.*;
import java.util.List;

public class ControllerPesanan {

    PenggunaPage halamanpengguna;
    RiwayatPesanan halamanriwayat;

    InterfaceDAOpesanan daoPesanan;
    Connector connection;

    List<ModelPesanan> daftarPesanan;
    private List<Integer> jadwalIds;
    private List<Integer> teaterIds;
//
//        public ControllerPesanan(ViewData halamanTable) {
//        this.halamanTable = halamanTable;
//        this.daoPesanan = new DAOPesanan();
//    }
//
//    public ControllerPesanan(InputData halamanInput) {
//        this.halamanInput = halamanInput;
//        this.daoPesanan = new DAOPesanan();
//    }
//    public void showAllPesanan() {
//        daftarPesanan = daoPesanan.getAll();
//        updateTableView(daftarPesanan);
//    }
    
 public ControllerPesanan(PenggunaPage halamanpengguna){
    this.halamanpengguna= halamanpengguna;
    this.daoPesanan= new DAOPesanan();
}
 public ControllerPesanan(RiwayatPesanan halamanriwayat){
     this.halamanriwayat=halamanriwayat;
     this.daoPesanan= new DAOPesanan();
 }


    public ControllerPesanan() {
        this.daoPesanan = new DAOPesanan();
    }
public void isitabel(int userid){
    System.out.println("Masuk Control");
        daftarPesanan =daoPesanan.getAll(userid);
        ModelTable mb = new ModelTable(daftarPesanan);
        halamanriwayat.getTablePesanan().setModel(mb);
    
}
    
//    public void showAllPesanan() {
//        daftarPesanan = daoPesanan.getAll();
//        ModelTable table = new ModelTable(daftarPesanan);
//        halamanTable.getTablePesanan().setModel(table);
//    }

    public void insertPesanan() {
        try {
            ModelPesanan pesananBaru = new ModelPesanan();

            int id= Integer.parseInt(halamanpengguna.getID().getText());
            String nama=halamanpengguna.getNama().getText();
            int id_jadwal= Integer.parseInt(halamanpengguna.getID_Jadwal().getText());
            String film=halamanpengguna.getFILM().getText();
            String teater= halamanpengguna.getTeater().getText();

            int harga=Integer.parseInt(halamanpengguna.getHargaSatuan().getText());
            int jumlah=Integer.parseInt(halamanpengguna.getJumlah().getText());
            int total_harga=Integer.parseInt(halamanpengguna.getTotal().getText());
            
            if (id <= 0 ||id_jadwal <= 0 || jumlah <= 0 ||  "".equals(teater)||"".equals(nama)||"".equals(film)||harga<=0||total_harga<=0) {
                throw new Exception("Inputan tidak boleh kosong!");
            }
            pesananBaru.setuserId(id);
            pesananBaru.setId_Jadwal(id_jadwal);
            pesananBaru.setJudul_Film(film);
            pesananBaru.setTeater(teater);
            pesananBaru.setharga(harga);
            pesananBaru.setjumlah(jumlah);
            pesananBaru.settotal_harga(total_harga);

            daoPesanan.insert(pesananBaru);;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
public boolean cekKetersediaanKursiDanKurangiKapasitas(int idJadwal, int jumlahPesanan) {
    try {
        // Query untuk mendapatkan kapasitas dari tabel teater berdasarkan id_jadwal
        String cekKapasitasQuery = "SELECT j.kapasitas " +
                                   "FROM teater t " +
                                   "JOIN jadwal j ON t.id_teater = j.id_teater " +
                                   "WHERE j.id_jadwal = ?";
        try (PreparedStatement cekKapasitasStmt = connection.prepareStatement(cekKapasitasQuery)) {
            cekKapasitasStmt.setInt(1, idJadwal);
            try (ResultSet rs = cekKapasitasStmt.executeQuery()) {
                if (rs.next()) {
                    int kapasitas = rs.getInt("kapasitas");
                    if (kapasitas >= jumlahPesanan) {
                        // Kurangi kapasitas pada tabel teater
                        String kurangiKapasitasQuery = "UPDATE teater t " +
                                                       "JOIN jadwal j ON t.id_teater = j.id_teater " +
                                                       "SET t.kapasitas = t.kapasitas - ? " +
                                                       "WHERE j.id_jadwal = ?";
                        try (PreparedStatement kurangiKapasitasStmt = connection.prepareStatement(kurangiKapasitasQuery)) {
                            kurangiKapasitasStmt.setInt(1, jumlahPesanan);
                            kurangiKapasitasStmt.setInt(2, idJadwal);
                            kurangiKapasitasStmt.executeUpdate();
                            return true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Kapasitas tidak mencukupi.");
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error checking and reducing capacity: " + e.getMessage());
    }
    return false;
}



}
