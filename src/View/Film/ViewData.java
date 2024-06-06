package View.Film;

import Controller.ControllerFilm;
import Model.Film.ModelFilm;
import View.AdminPage;
import java.awt.Color;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

public class ViewData extends JFrame {

    Integer baris;
    ControllerFilm controller;

    JLabel header = new JLabel("DATA FILM");

    JLabel labelInputSearch = new JLabel("Search Data Film");
    JTextField inputSearch = new JTextField();
    JButton tombolTambah = new JButton("Tambah Film");
    JButton tombolEdit = new JButton("Edit Film");
    JButton tombolHapus = new JButton("Hapus Film");
    JButton tombolKembali = new JButton("Kembali ke Admin Page");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"ID", "Judul", "Durasi", "Genre", "Kategori", "Tanggal Tayang Perdana", "Harga Film"};
    TableRowSorter<DefaultTableModel> sorter;

    public ViewData(String username, int userId) {
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        scrollPane = new JScrollPane(table);

        setTitle("Data Film");
        setVisible(true);
        setSize(560, 620);
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
        
        labelInputSearch.setBounds(20, 40, 100, 40);
        inputSearch.setBounds(130, 45, 220, 30);

        scrollPane.setBounds(20, 90, 512, 280);
        tombolTambah.setBounds(20, 380, 512, 40);
        tombolEdit.setBounds(20, 424, 512, 40);
        tombolHapus.setBounds(20, 468, 512, 40);
        tombolKembali.setBounds(20, 512, 512, 40);
        
        tombolTambah.setFont(tombolTambah.getFont().deriveFont(14.0f));
        tombolEdit.setFont(tombolEdit.getFont().deriveFont(14.0f));
        tombolHapus.setFont(tombolHapus.getFont().deriveFont(14.0f));
        tombolKembali.setFont(tombolKembali.getFont().deriveFont(14.0f));
        
        tombolTambah.setBackground(Color.decode("#a9b5d1"));
        tombolEdit.setBackground(Color.decode("#dae0f0"));
        tombolHapus.setBackground(Color.decode("#ffb8c6"));
        tombolKembali.setBackground(Color.decode("#f0dfef"));
        

        controller = new ControllerFilm(this);
        controller.showAllFilm();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                baris = table.getSelectedRow();
            }
        });

        tombolTambah.addActionListener(e -> {
            dispose();
            new InputData(username,userId);
        });

        tombolEdit.addActionListener(e -> {
            if (baris != null) {
                ModelFilm filmTerpilih = new ModelFilm();

                Integer id = (Integer) table.getValueAt(baris, 0);
                String judul = table.getValueAt(baris, 1).toString();
                Integer durasi = (Integer) table.getValueAt(baris, 2);
                String genre = table.getValueAt(baris, 3).toString();
                String kategori = table.getValueAt(baris, 4).toString();
                Date tanggal_tayang_perdana = Date.valueOf(table.getValueAt(baris, 5).toString());
                Integer hargafilm = (Integer) table.getValueAt(baris, 6);

                filmTerpilih.setId(id);
                filmTerpilih.setJudul(judul);
                filmTerpilih.setDurasi(durasi);
                filmTerpilih.setGenre(genre);
                filmTerpilih.setKategori(kategori);
                filmTerpilih.setTanggal_tayang_perdana(tanggal_tayang_perdana);
                filmTerpilih.setHargafilm(hargafilm);

                new EditData(filmTerpilih,username,userId);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Pilih film yang ingin diedit!");
            }
        });

        tombolHapus.addActionListener(e -> {
            if (baris != null) {
                controller.deleteFilm(baris);
                baris = null;
            } else {
                JOptionPane.showMessageDialog(null, "Pilih film yang ingin dihapus!");
            }
        });

        tombolKembali.addActionListener(e -> {
            dispose();
            new AdminPage(username, userId);
        });

        // Add DocumentListener for real-time search
        inputSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchFilm();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchFilm();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchFilm();
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
    } /* tutup*/

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    public void searchFilm() {
        String searchText = inputSearch.getText();
        if (searchText != null && !searchText.trim().isEmpty()) {
            List<ModelFilm> searchResult = controller.searchData(searchText);
            if (searchResult != null && !searchResult.isEmpty()) {
                updateTable(searchResult);
            } else {
                clearTable();
            }
        } else {
            controller.showAllFilm();  // Show all films if search text is empty
        }
    }

    public void updateTable(List<ModelFilm> films) {
        clearTable();
        for (ModelFilm film : films) {
            Object[] row = {film.getId(), film.getJudul(), film.getDurasi(), film.getGenre(), film.getKategori(), film.getTanggal_tayang_perdana(), film.getHargafilm()};
            tableModel.addRow(row);
        }
    }

    public JTable getTableFilm() {
        return table;
    }
}
