package Model.Jadwal;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {

    List<ModelJadwal> daftarJadwal;

    String kolom[] = {"ID", "Film", "Teater","Kapasitas", "Studio", "Tanggal Tayang", "Jam Tayang", "Harga"};

    public ModelTable(List<ModelJadwal> daftarJadwal) {
        this.daftarJadwal = daftarJadwal;
    }

    @Override
    public int getRowCount() {
        return daftarJadwal.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return daftarJadwal.get(rowIndex).getId();
            case 1:
                return daftarJadwal.get(rowIndex).getFilm_judul();
            case 2:
                return daftarJadwal.get(rowIndex).getTeater_nama();
            case 3:
                return daftarJadwal.get(rowIndex).getkapasitas();
            case 4:
                return daftarJadwal.get(rowIndex).getStudio();
            case 5:
                return daftarJadwal.get(rowIndex).getTanggal_tayang();
            case 6:
                return daftarJadwal.get(rowIndex).getJam_tayang();
            case 7:
                return daftarJadwal.get(rowIndex).getHarga();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}
