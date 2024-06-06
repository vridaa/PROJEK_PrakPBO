package Model.Film;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {

    List<ModelFilm> daftarFilm;

    String kolom[] = {"ID", "Judul", "Durasi", "Genre", "Kategori", "Tanggal Tayang Perdana", "Harga Film"};

    public ModelTable(List<ModelFilm> daftarFilm) {
        this.daftarFilm = daftarFilm;
    }

    @Override
    public int getRowCount() {
        return daftarFilm.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return daftarFilm.get(rowIndex).getId();
            case 1:
                return daftarFilm.get(rowIndex).getJudul();
            case 2:
                return daftarFilm.get(rowIndex).getDurasi();
            case 3:
                return daftarFilm.get(rowIndex).getGenre();
            case 4:
                return daftarFilm.get(rowIndex).getKategori();
            case 5:
                return daftarFilm.get(rowIndex).getTanggal_tayang_perdana();
            case 6:
                return daftarFilm.get(rowIndex).getHargafilm();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}
