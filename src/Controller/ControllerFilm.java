package Controller;

import Model.Film.*;
import Model.Jadwal.DAOJadwal;
import Model.Jadwal.InterfaceDAOJadwal;
import View.Film.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerFilm {

    ViewData halamanTable;
    InputData halamanInput;
    EditData halamanEdit;

    InterfaceDAOFilm daoFilm;
    InterfaceDAOJadwal daoJadwal;

    List<ModelFilm> daftarFilm;

    public ControllerFilm(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoFilm = new DAOFilm();
        this.daoJadwal = new DAOJadwal();
    }

    public ControllerFilm(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoFilm = new DAOFilm();
        this.daoJadwal = new DAOJadwal();
    }

    public ControllerFilm(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoFilm = new DAOFilm();
        this.daoJadwal = new DAOJadwal();
    }

    public ControllerFilm() {
        this.daoFilm = new DAOFilm();
        this.daoJadwal = new DAOJadwal();
    }

    public void showAllFilm() {
        daftarFilm = daoFilm.getAll();
        updateTableView(daftarFilm);
    }

    public void insertFilm() {
        try {
            ModelFilm filmBaru = new ModelFilm();

            String judul = halamanInput.getInputJudul();
            int durasi = halamanInput.getInputDurasi();
            String genre = halamanInput.getInputGenre();
            String kategori = halamanInput.getInputKategori();
            String tanggal_tayang_perdana_string = halamanInput.getInputTanggal_tayang_perdana(); // Mengambil tanggal dalam format string
            int hargafilm = halamanInput.getInputHargafilm();

            if ("".equals(judul) || durasi <= 0 || "".equals(genre) || "".equals(kategori) || "".equals(tanggal_tayang_perdana_string) || hargafilm <= 0) {
                throw new Exception("Inputan tidak boleh kosong!");
            }

            // Check if the film already exists
            if (daoFilm.isFilmExists(judul, durasi, genre, kategori, tanggal_tayang_perdana_string, hargafilm)) {
                throw new Exception("Data sudah ada di database");
            }

            filmBaru.setJudul(judul);
            filmBaru.setDurasi(durasi);
            filmBaru.setGenre(genre);
            filmBaru.setKategori(kategori);
            filmBaru.setTanggal_tayang_perdanaFromString(tanggal_tayang_perdana_string); // Mengatur tanggal dari string
            filmBaru.setHargafilm(hargafilm);

            daoFilm.insert(filmBaru);

            JOptionPane.showMessageDialog(null, "Film baru berhasil ditambahkan");

            halamanInput.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void editFilm(int id) {
        try {
            ModelFilm filmYangMauDiedit = new ModelFilm();

            String judul = halamanEdit.getInputJudul();
            int durasi = halamanEdit.getInputDurasi();
            String genre = halamanEdit.getInputGenre();
            String kategori = halamanEdit.getInputKategori();
            String tanggal_tayang_perdana_string = halamanEdit.getInputTanggal_tayang_perdana(); // Mengambil tanggal dalam format string
            int hargafilm = halamanEdit.getInputHargafilm();

            if ("".equals(judul) || durasi <= 0 || "".equals(genre) || "".equals(kategori) || "".equals(tanggal_tayang_perdana_string) || hargafilm <= 0) {
                throw new Exception("Inputan tidak boleh kosong!");
            }

            // Check if the edited film data is the same as an existing film data
            if (daoFilm.isFilmExistsExceptId(id, judul, durasi, genre, kategori, tanggal_tayang_perdana_string, hargafilm)) {
                throw new Exception("Data sudah ada di database");
            }

            filmYangMauDiedit.setId(id);
            filmYangMauDiedit.setJudul(judul);
            filmYangMauDiedit.setDurasi(durasi);
            filmYangMauDiedit.setGenre(genre);
            filmYangMauDiedit.setKategori(kategori);
            filmYangMauDiedit.setTanggal_tayang_perdanaFromString(tanggal_tayang_perdana_string); // Mengatur tanggal dari string
            filmYangMauDiedit.setHargafilm(hargafilm);

            daoFilm.update(filmYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Film berhasil diedit");

            halamanEdit.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteFilm(Integer baris) {
        Integer id = (int) halamanTable.getTableFilm().getValueAt(baris, 0);
        String judul = halamanTable.getTableFilm().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + judul + "?",
                "Hapus Film",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            try {
                // Check if the film exists in the schedule
                if (daoJadwal.isFilmExistInJadwal(id)) {
                    throw new Exception("Film ada di jadwal. Film tidak bisa dihapus");
                }
                
                daoFilm.delete(id);
                JOptionPane.showMessageDialog(null, "Berhasil menghapus data");
                showAllFilm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Delete Failed: " + e.getLocalizedMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    public List<ModelFilm> searchData(String keyword) {
        try {
            List<ModelFilm> result = new ArrayList<>();
            for (ModelFilm film : daftarFilm) {
                if (film.getJudul().toLowerCase().contains(keyword.toLowerCase()) ||
                        film.getGenre().toLowerCase().contains(keyword.toLowerCase()) ||
                        film.getKategori().toLowerCase().contains(keyword.toLowerCase()) ||
                        film.getTanggal_tayang_perdana().toString().contains(keyword) ||
                        String.valueOf(film.getDurasi()).contains(keyword) ||
                        String.valueOf(film.getHargafilm()).contains(keyword)) {
                    result.add(film);
                }
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return null;
        }
    }

    private void updateTableView(List<ModelFilm> films) {
        if (halamanTable != null) {
            halamanTable.updateTable(films);
        }
    }
}
