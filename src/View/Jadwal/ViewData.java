package View.Jadwal;

import Controller.ControllerJadwal;
import Model.Jadwal.ModelJadwal;
//import Model.Jadwal.ModelJadwal;
import View.AdminPage;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
//import java.sql.Date;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class ViewData extends JFrame {

    private Integer baris = null;
    //private String username;
    ControllerJadwal controller;

    JLabel header = new JLabel("DATA JADWAL");

    JLabel labelInputSearch = new JLabel("Search Data Jadwal");
    JTextField inputSearch = new JTextField();
    
    JButton tombolTambah = new JButton("Tambah Jadwal");
    JButton tombolHapus = new JButton("Hapus Jadwal");
    JButton tombolKembali = new JButton("Kembali ke Admin Page");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"ID", "Film", "Teater", "Studio", "Tanggal Tayang", "Jam Tayang", "Harga"};

    public ViewData(String username, int userId) {
        //this.username = username;
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        scrollPane = new JScrollPane(table);

        setTitle("Data Jadwal");
        setVisible(true);
        setSize(560, 570);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.decode("#f5f5fa"));

        add(header);
        add(scrollPane);
        
        add(labelInputSearch);
        add(inputSearch);

        add(tombolTambah);
        add(tombolHapus);
        add(tombolKembali);

        header.setBounds(210, 8, 440, 24);
        header.setFont(header.getFont().deriveFont(22.0f));
        
        labelInputSearch.setBounds(20, 40, 130, 40);
        inputSearch.setBounds(150, 45, 220, 30);

        scrollPane.setBounds(20, 90, 512, 270);
        tombolTambah.setBounds(20, 370, 512, 40);
        tombolHapus.setBounds(20, 414, 512, 40);
        tombolKembali.setBounds(20, 458, 512, 40);
        
        tombolTambah.setFont(tombolTambah.getFont().deriveFont(14.0f));
        tombolHapus.setFont(tombolHapus.getFont().deriveFont(14.0f));
        tombolKembali.setFont(tombolKembali.getFont().deriveFont(14.0f));
        
        tombolTambah.setBackground(Color.decode("#a9b5d1"));
        tombolHapus.setBackground(Color.decode("#ffb8c6"));
        tombolKembali.setBackground(Color.decode("#f0dfef"));

        controller = new ControllerJadwal(this);
        controller.showAllJadwal();

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
                List<Integer> listFilmIds = controller.getListFilmIds();
                List<Integer> listTeaterIds = controller.getListTeaterIds();
                dispose();
                new InputData(listFilmIds, listTeaterIds, username, userId);
            }
        });
        
        tombolHapus.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if (baris != null) {
            controller.deleteJadwal(baris); // Pass the selected row index directly
            baris = null;
            controller.showAllJadwal();
        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
        }
            }
        });


        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new AdminPage(username, userId);
            }
        });
        
        // Add DocumentListener for real-time search
        inputSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchJadwal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchJadwal();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchJadwal();
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

    public void searchJadwal() {
        String searchText = inputSearch.getText();
        if (searchText != null && !searchText.trim().isEmpty()) {
            List<ModelJadwal> searchResult = controller.searchData(searchText);
            if (searchResult != null && !searchResult.isEmpty()) {
                updateTable(searchResult);
            } else {
                clearTable();
            }
        } else {
            controller.showAllJadwal();  // Show all films if search text is empty
        }
    }

    public void updateTable(List<ModelJadwal> jadwals) {
        clearTable();
        for (ModelJadwal jadwal : jadwals) {
            Object[] row = {jadwal.getId(), jadwal.getFilm_judul(), jadwal.getTeater_nama(), jadwal.getStudio(), jadwal.getTanggal_tayang(), jadwal.getJam_tayang(), jadwal.getHarga()};
            tableModel.addRow(row);
        }
    }

    public JTable getTableJadwal() {
        return table;
    }
}