package Model.Teater;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {

    List<ModelTeater> daftarTeater;

    String kolom[] = {"ID", "Kelas", "Harga", "Kapasitas"};

    public ModelTable(List<ModelTeater> daftarTeater) {
        this.daftarTeater = daftarTeater;
    }

    @Override
    public int getRowCount() {
        return daftarTeater.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return daftarTeater.get(rowIndex).getId();
            case 1:
                return daftarTeater.get(rowIndex).getKelas();
            case 2:
                return daftarTeater.get(rowIndex).getHarga();
            case 3:
                return daftarTeater.get(rowIndex).getKapasitas();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}
