package View.Teater;

import Controller.ControllerTeater;
import Model.Teater.ModelTeater;
import View.AdminPage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class ViewData extends JFrame {

    Integer baris;
    ControllerTeater controller;

    JLabel header = new JLabel("DATA TEATER");
    
    JLabel labelInputSearch = new JLabel("Search Data Teater");
    JTextField inputSearch = new JTextField();
    JButton tombolTambah = new JButton("Tambah Teater");
    JButton tombolEdit = new JButton("Edit Teater");
    JButton tombolHapus = new JButton("Hapus Teater");
    JButton tombolKembali = new JButton("Kembali ke Admin Page");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"ID", "Kelas", "Harga", "Kapasitas"};
    TableRowSorter<DefaultTableModel> sorter;

    public ViewData(String username, int userId) {
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        
        scrollPane = new JScrollPane(table);

        setTitle("Data Teater");
        setVisible(true);
        setSize(560, 480);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.decode("#f5f5fa"));

        add(header);
        add(scrollPane);
        
        add(labelInputSearch);
        add(inputSearch);
        
        add(tombolTambah);
        add(tombolEdit);
        add(tombolHapus);
        add(tombolKembali);

        header.setBounds(210, 8, 440, 24);
        header.setFont(header.getFont().deriveFont(22.0f));
        
        labelInputSearch.setBounds(20, 40, 150, 40);
        inputSearch.setBounds(150, 45, 220, 30);
        
        scrollPane.setBounds(20, 90, 512, 140);
        tombolTambah.setBounds(20, 250, 512, 40);
        tombolEdit.setBounds(20, 294, 512, 40);
        tombolHapus.setBounds(20, 338, 512, 40);
        tombolKembali.setBounds(20, 382, 512, 40);
        
        tombolTambah.setFont(tombolTambah.getFont().deriveFont(14.0f));
        tombolEdit.setFont(tombolEdit.getFont().deriveFont(14.0f));
        tombolHapus.setFont(tombolHapus.getFont().deriveFont(14.0f));
        tombolKembali.setFont(tombolKembali.getFont().deriveFont(14.0f));
        
        tombolTambah.setBackground(Color.decode("#a9b5d1"));
        tombolEdit.setBackground(Color.decode("#dae0f0"));
        tombolHapus.setBackground(Color.decode("#ffb8c6"));
        tombolKembali.setBackground(Color.decode("#f0dfef"));

        controller = new ControllerTeater(this);
        controller.showAllTeater();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                baris = table.getSelectedRow();
            }
        });

        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InputData(username,userId);
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    ModelTeater teaterTerpilih = new ModelTeater();

                    Integer id = (Integer) table.getValueAt(baris, 0);
                    String kelas = table.getValueAt(baris, 1).toString();
                    Integer harga = (Integer) table.getValueAt(baris, 2);
                    Integer kapasitas = (Integer) table.getValueAt(baris, 3);

                    teaterTerpilih.setId(id);
                    teaterTerpilih.setKelas(kelas);
                    teaterTerpilih.setHarga(harga);
                    teaterTerpilih.setKapasitas(kapasitas);

                    new EditData(teaterTerpilih,username,userId);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang ingin diedit!");
                }
            }
        });

        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    
                        controller.deleteTeater(baris);
                        //tableModel.removeRow(baris);
                        baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
                }
            }
        });

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminPage(username, userId);
            }
        });
        
        inputSearch.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void changedUpdate(DocumentEvent e) {
        searchTeater();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        searchTeater();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        searchTeater();
    }
});


        // Add MouseListener for table header sorting
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                sorter.toggleSortOrder(col);
            }
        });

        // Fix number sorting issue
        for (int i = 0; i < table.getColumnCount(); i++) {
            sorter.setComparator(i, (o1, o2) -> {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    return ((Integer) o1).compareTo((Integer) o2);
                }
                if (o1 instanceof Date && o2 instanceof Date) {
                    return ((Date) o1).compareTo((Date) o2);
                }
                if (o1 instanceof String && o2 instanceof String) {
                    return ((String) o1).compareTo((String) o2);
                }
                return 0;
            });
        }

    } /* tutup */
    
    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    public void searchTeater() {
    String searchText = inputSearch.getText();
    if (searchText != null && !searchText.trim().isEmpty()) {
        List<ModelTeater> searchResult = controller.searchData(searchText);
        if (searchResult != null && !searchResult.isEmpty()) {
            updateTable(searchResult);
        } else {
            clearTable();
        }
    } else {
        // Panggil showAllTeater() jika pencarian kosong
        controller.showAllTeater();
    }
}

public void updateTable(List<ModelTeater> teaters) {
    // Bersihkan tabel sebelum menambahkan hasil pencarian baru
    clearTable();

    for (ModelTeater teater : teaters) {
        Object[] row = {teater.getId(), teater.getKelas(), teater.getHarga(), teater.getKapasitas()};
        tableModel.addRow(row);
    }
}

    public JTable getTableTeater() {
        return table;
    }
    
}
