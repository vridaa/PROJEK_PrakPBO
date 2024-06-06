package Controller;

import Model.Jadwal.DAOJadwal;
import Model.Jadwal.InterfaceDAOJadwal;
import Model.Teater.*;
import View.Teater.*;
import java.sql.SQLException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerTeater {

    ViewData halamanTable;
    InputData halamanInput;
    EditData halamanEdit;

    InterfaceDAOTeater daoTeater;
    InterfaceDAOJadwal daoJadwal;

    List<ModelTeater> daftarTeater;
    
    public ControllerTeater(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoTeater = new DAOTeater();
        this.daoJadwal = new DAOJadwal();
    }
    
    public ControllerTeater(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoTeater = new DAOTeater();
        this.daoJadwal = new DAOJadwal();
    }
    
    public ControllerTeater(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoTeater = new DAOTeater();
        this.daoJadwal = new DAOJadwal();
    }

    public ControllerTeater() {
        this.daoTeater = new DAOTeater();
        this.daoJadwal = new DAOJadwal();
    }

    public void showAllTeater() {
        daftarTeater = daoTeater.getAll();
        updateTableView(daftarTeater);
    }

    public void insertTeater() {
        try {
            ModelTeater teaterBaru = new ModelTeater();
            
            String kelas = halamanInput.getInputKelas();
            int harga = halamanInput.getInputHarga();
            int kapasitas = halamanInput.getInputKapasitas();

            if ("".equals(kelas) || harga <= 0 || kapasitas <= 0) {
                throw new Exception("Inputan tidak boleh kosong!");
            }
            
            // Check if the teater already exists
            if (daoTeater.isTeaterExists(kelas, harga, kapasitas)) {
                throw new Exception("Data sudah ada di database");
            }
            
            teaterBaru.setKelas(kelas);
            teaterBaru.setHarga(harga);
            teaterBaru.setKapasitas(kapasitas);

            daoTeater.insert(teaterBaru);
            
            JOptionPane.showMessageDialog(null, "Teater baru berhasil ditambahkan");
            
            halamanInput.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editTeater(int id) {
        try {
            ModelTeater teaterYangMauDiedit = new ModelTeater();
                        
            String kelas = halamanEdit.getInputKelas();
            int harga = halamanEdit.getInputHarga();
            int kapasitas = halamanEdit.getInputKapasitas();

            if ("".equals(kelas) || harga <= 0 || kapasitas <= 0) {
                throw new Exception("Inputan tidak boleh kosong!");
            }
            
            if (daoTeater.isTeaterExistsExceptId(id, kelas, harga, kapasitas)) {
                throw new Exception("Data sudah ada di database");
            }
            
            teaterYangMauDiedit.setId(id);
            teaterYangMauDiedit.setKelas(kelas);
            teaterYangMauDiedit.setHarga(harga);
            teaterYangMauDiedit.setKapasitas(kapasitas);
            
            daoTeater.update(teaterYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data teater berhasil diubah");

            halamanEdit.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteTeater(Integer baris) {
        Integer id = (int) halamanTable.getTableTeater().getValueAt(baris, 0);
        String kelas = halamanTable.getTableTeater().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + kelas + "?",
                "Hapus Teater",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            try {
                // Check if the teater exists in the schedule
                if (daoJadwal.isTeaterExistInJadwal(id)) {
                    throw new Exception("Teater ada di jadwal. Teater tidak bisa dihapus");
                }
                
                daoTeater.delete(id);
                JOptionPane.showMessageDialog(null, "Berhasil menghapus data");
                showAllTeater();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Delete Failed: " + e.getLocalizedMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }
    
   public List<ModelTeater> searchData(String keyword) {
    try {
        List<ModelTeater> result = new ArrayList<>();
        for (ModelTeater teater : daftarTeater) {
            if (teater.getKelas().toLowerCase().contains(keyword.toLowerCase()) ||
                String.valueOf(teater.getKapasitas()).contains(keyword) ||
                String.valueOf(teater.getHarga()).contains(keyword) ||
                String.valueOf(teater.getId()).contains(keyword)) {
                result.add(teater);
            }
        }
        return result;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        return null;
    }
}


    private void updateTableView(List<ModelTeater> teaters) {
        if (halamanTable != null) {
            halamanTable.updateTable(teaters);
        }
    }
    
}
