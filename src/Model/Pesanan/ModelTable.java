/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Pesanan;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class ModelTable extends AbstractTableModel {
   List<ModelPesanan> daftarPesanan;

    String kolom[] = {"ID",  "Film","Teater","Studio","Tanggal","Jam","harga","Jumlah","Total Bayar"}; 
    
    public ModelTable(List<ModelPesanan> daftarPesanan) {
        this.daftarPesanan = daftarPesanan;
    }

    @Override
    public int getRowCount() {
        return daftarPesanan.size();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex) {
            case 0:
                return daftarPesanan.get(rowIndex).getId();
            case 1:
                return daftarPesanan.get(rowIndex).getJudul_Film();
            case 2:
                return daftarPesanan.get(rowIndex).getTeater();
            case 3:
                return daftarPesanan.get(rowIndex).getStudio();
            case 4 :
                return daftarPesanan.get(rowIndex).getTanggal();
            case 5 :
                return daftarPesanan.get(rowIndex).getJam();
            case 6 :
                return daftarPesanan.get(rowIndex).getharga();
            case 7:
                return daftarPesanan.get(rowIndex).getjumlah();
            case 8:
                return daftarPesanan.get(rowIndex).gettotal_harga();
            default:
                return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}
