package View.Film;

import Controller.ControllerFilm;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InputData extends JFrame {

    ControllerFilm controller;

    JLabel header = new JLabel("INPUT DATA FILM");
    JLabel labelInputJudul = new JLabel("Judul Film");
    JTextField inputJudul = new JTextField();
    JLabel labelInputDurasi = new JLabel("Durasi Film (menit)");
    JTextField inputDurasi = new JTextField();
    JLabel labelInputGenre = new JLabel("Genre Film");
    JTextField inputGenre = new JTextField();
    JLabel labelInputKategori = new JLabel("Kategori Film");
    JComboBox<String> inputKategori = new JComboBox<>(new String[] {"2D", "3D"});
    JLabel labelInputTanggal = new JLabel("Tanggal Tayang Perdana");
    JDateChooser inputTanggal = new JDateChooser();
    JLabel labelInputHarga = new JLabel("Harga Film");
    JTextField inputHarga = new JTextField();
    JButton tombolTambah = new JButton("Tambah");
    JButton tombolKembali = new JButton("Kembali");

    public InputData(String username, int userId) {
        setTitle("Input Data Film");
        setVisible(true);
        setSize(430, 440);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.decode("#f5f5fa"));

        add(header);
        add(labelInputJudul);
        add(inputJudul);
        add(labelInputDurasi);
        add(inputDurasi);
        add(labelInputGenre);
        add(inputGenre);
        add(labelInputKategori);
        add(inputKategori);
        add(labelInputTanggal);
        add(inputTanggal);
        add(labelInputHarga);
        add(inputHarga);
        add(tombolTambah);
        add(tombolKembali);

        header.setBounds(130, 20, 440, 24);
        header.setFont(header.getFont().deriveFont(20.0f));
        
        labelInputJudul.setBounds(20, 60, 200, 30);
        inputJudul.setBounds(170, 60, 220, 30);
        labelInputDurasi.setBounds(20, 100, 200, 30);
        inputDurasi.setBounds(170, 100, 220, 30);
        labelInputGenre.setBounds(20, 140, 200, 30);
        inputGenre.setBounds(170, 140, 220, 30);
        labelInputKategori.setBounds(20, 180, 200, 30);
        inputKategori.setBounds(170, 180, 220, 30);
        labelInputTanggal.setBounds(20, 220, 200, 30);
        inputTanggal.setBounds(170, 220, 220, 30);
        labelInputHarga.setBounds(20, 260, 200, 30);
        inputHarga.setBounds(170, 260, 220, 30);
        tombolTambah.setBounds(100, 320, 100, 40);
        tombolKembali.setBounds(220, 320, 100, 40);
        
        tombolTambah.setBackground(Color.decode("#a9b5d1"));
        tombolKembali.setBackground(Color.decode("#ffb8c6"));

        controller = new ControllerFilm(this);
        
        tombolTambah.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        try {
            if (inputJudul.getText().isEmpty() || inputDurasi.getText().isEmpty() ||
                inputGenre.getText().isEmpty() || inputTanggal.getDate() == null ||
                inputHarga.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Inputan ada yang kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.insertFilm();
                dispose();
                new ViewData(username, userId);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Inputan harus berupa angka", "Error", JOptionPane.ERROR_MESSAGE);
        }
            }
        });
        
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewData(username,userId);
            }
        });

    }
    
    public String getInputJudul() {
    return inputJudul.getText();
    }

    public int getInputDurasi() {
    return Integer.parseInt(inputDurasi.getText());
    }

    public String getInputGenre() {
    return inputGenre.getText();
    }

    public String getInputKategori() {
        return inputKategori.getSelectedItem().toString();
    }
    
    public String getInputTanggal_tayang_perdana() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(inputTanggal.getDate());
    }

    public int getInputHargafilm() {
    return Integer.parseInt(inputHarga.getText());
    }

}
