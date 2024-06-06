package Controller;

import Model.Jadwal.*;
import View.Jadwal.*;
import View.PenggunaPage;
import java.util.ArrayList;
//import View.PenggunaPage;
//import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerJadwal {

    ViewData halamanTable;
    InputData halamanInput;
    PenggunaPage halamanpengguna;

    InterfaceDAOJadwal daoJadwal;

    List<ModelJadwal> daftarJadwal;
    private List<Integer> filmIds;
    private List<Integer> teaterIds;

    public ControllerJadwal(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoJadwal = new DAOJadwal();
    }
    public ControllerJadwal(PenggunaPage halamanpengguna){
        this.halamanpengguna=halamanpengguna;
        this.daoJadwal= new DAOJadwal();
    }

    public ControllerJadwal(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoJadwal = new DAOJadwal();
    }

    public ControllerJadwal() {
        
        this.daoJadwal = new DAOJadwal();
    }

 

//    public ControllerJadwal(PenggunaPage aThis) {
//        d
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    /*
    public ControllerJadwal(PenggunaPage aThis) {
        daftarJadwal = daoJadwal.getAll();
        ModelTable table = new ModelTable(daftarJadwal);
        halamanTable.getTableJadwal().setModel(table);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    public void showAllJadwal() {
        daftarJadwal = daoJadwal.getAll();
        updateTableView(daftarJadwal);
    }
    public void isijadwal(){
        daftarJadwal=daoJadwal.getAll();
        ModelTable mb = new ModelTable(daftarJadwal);
        halamanpengguna.getTableJadwal().setModel(mb);
        
    }
    
    public void insertJadwal() {
    try {
        ModelJadwal jadwalBaru = new ModelJadwal();

        int id_film = Integer.parseInt(halamanInput.getInputId_film());
        int id_teater = Integer.parseInt(halamanInput.getInputId_teater());
        String studio = halamanInput.getInputStudio();
        String tanggal_tayang_string = halamanInput.getInputTanggal_tayang();
        String jam_tayang = halamanInput.getInputJam_tayang();
        int kapasitas = daoJadwal.getKapasitasById(id_teater);

        // Menghitung harga film dan teater
        int hargaFilm = daoJadwal.getHargaFilmById(id_film);
        int hargaTeater = daoJadwal.getHargaTeaterById(id_teater);
        
        // Menghitung total harga
        int harga = hargaFilm + hargaTeater;

        if (id_film <= 0 || id_teater <= 0 || studio.isEmpty() || tanggal_tayang_string.isEmpty() || jam_tayang.isEmpty()) {
            throw new Exception("Inputan tidak boleh kosong!");
        }

        jadwalBaru.setId_film(id_film);
        jadwalBaru.setId_teater(id_teater);
        jadwalBaru.setStudio(studio);
        jadwalBaru.setTanggal_tayangFromString(tanggal_tayang_string);
        jadwalBaru.setJam_tayang(jam_tayang);
        jadwalBaru.setHarga(harga);
        jadwalBaru.setkapasitas(kapasitas);
        daoJadwal.insert(jadwalBaru);

        JOptionPane.showMessageDialog(null, "Jadwal baru berhasil ditambahkan");

        halamanInput.dispose();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

   public void deleteJadwal(int rowIndex) {
    try {
        Integer id = (Integer) halamanTable.getTableJadwal().getValueAt(rowIndex, 0);
        String film_judul = halamanTable.getTableJadwal().getValueAt(rowIndex, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + film_judul + "?",
                "Hapus Film",
                JOptionPane.YES_NO_OPTION
        );

        if (input == JOptionPane.YES_OPTION) {
            daoJadwal.delete(id);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data");
            showAllJadwal();
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    public List<Integer> getAllFilmIds() {
        return daoJadwal.getAllFilmIds();
    }

    public List<Integer> getAllTeaterIds() {
        return daoJadwal.getAllTeaterIds();
    }

    public List<Integer> getListFilmIds() {
        if (filmIds == null) {
            filmIds = daoJadwal.getAllFilmIds();
        }
        return filmIds;
    }

    public List<Integer> getListTeaterIds() {
        if (teaterIds == null) {
            teaterIds = daoJadwal.getAllTeaterIds();
        }
        return teaterIds;
    }
    
    public int getHargaFilmById(int filmId) {
    return daoJadwal.getHargaFilm(filmId);
    }

    public int getHargaTeaterById(int teaterId) {
    return daoJadwal.getHargaTeater(teaterId);
    }
    
    public List<ModelJadwal> searchData(String keyword) {
    try {
        List<ModelJadwal> result = new ArrayList<>();
        for (ModelJadwal jadwal : daftarJadwal) {
            if (jadwal.getFilm_judul().toLowerCase().contains(keyword.toLowerCase()) ||
                jadwal.getTeater_nama().toLowerCase().contains(keyword.toLowerCase()) ||
                jadwal.getStudio().toLowerCase().contains(keyword.toLowerCase()) ||
                jadwal.getTanggal_tayang().toString().contains(keyword) ||
                jadwal.getJam_tayang().toLowerCase().contains(keyword.toLowerCase()) ||
                String.valueOf(jadwal.getHarga()).contains(keyword)) {
                result.add(jadwal);
            }
        }
        return result;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        return null;
    }
    }
    public void searchJadwal(String cari){
        //String judul = frame2.getJjudul().getText();

        daftarJadwal = daoJadwal.search(cari);
        ModelTable mb = new ModelTable(daftarJadwal);
        halamanpengguna.getTableJadwal().setModel(mb);
    }


    private void updateTableView(List<ModelJadwal> jadwals) {
        if (halamanTable != null) {
            halamanTable.updateTable(jadwals);
        }
    }

    public void editJadwal(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
