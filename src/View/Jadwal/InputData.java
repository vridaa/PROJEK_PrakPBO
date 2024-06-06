package View.Jadwal;

import Controller.ControllerJadwal;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class InputData extends JFrame {

    ControllerJadwal controller;

    JLabel header = new JLabel("INPUT DATA JADWAL");
    JLabel labelInputId_film = new JLabel("ID Film");
    JComboBox<Integer> inputId_film = new JComboBox<>();
    JLabel labelInputId_teater = new JLabel("ID Teater");
    JComboBox<Integer> inputId_teater = new JComboBox<>();
    JLabel labelInputStudio = new JLabel("Studio");
    JComboBox<String> inputStudio = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
    JLabel labelInputTanggal = new JLabel("Tanggal Tayang");
    JDateChooser inputTanggal = new JDateChooser();
    JLabel labelInputJam_tayang = new JLabel("Jam Tayang");
    JFormattedTextField inputJam_tayang; // Moved here

    JButton tombolTambah = new JButton("Tambah");
    JButton tombolKembali = new JButton("Kembali");

    public InputData(List<Integer> listFilmIds, List<Integer> listTeaterIds, String username, int userId) {
        setTitle("Input Data Jadwal");
        setVisible(true);
        setSize(430, 410);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.decode("#f5f5fa"));

        try {
            MaskFormatter formatter = new MaskFormatter("##:##");
            inputJam_tayang = new JFormattedTextField(formatter); // Initialized here
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        inputId_film.setModel(new DefaultComboBoxModel<>(listFilmIds.toArray(new Integer[listFilmIds.size()])));
        inputId_teater.setModel(new DefaultComboBoxModel<>(listTeaterIds.toArray(new Integer[listTeaterIds.size()])));

        add(header);
        add(labelInputId_film);
        add(inputId_film);
        add(labelInputId_teater);
        add(inputId_teater);
        add(labelInputStudio);
        add(inputStudio);
        add(labelInputTanggal);
        add(inputTanggal);
        add(labelInputJam_tayang);
        add(inputJam_tayang);
        add(tombolTambah);
        add(tombolKembali);

        header.setBounds(120, 20, 440, 24);
        header.setFont(header.getFont().deriveFont(20.0f));
        
        labelInputId_film.setBounds(20, 60, 200, 30);
        inputId_film.setBounds(170, 60, 220, 30);
        labelInputId_teater.setBounds(20, 100, 200, 30);
        inputId_teater.setBounds(170, 100, 220, 30);
        labelInputStudio.setBounds(20, 140, 200, 30);
        inputStudio.setBounds(170, 140, 220, 30);
        labelInputTanggal.setBounds(20, 180, 200, 30);
        inputTanggal.setBounds(170, 180, 220, 30);
        labelInputJam_tayang.setBounds(20, 220, 200, 30);
        inputJam_tayang.setBounds(170, 220, 220, 30);
        tombolTambah.setBounds(100, 280, 100, 40);
        tombolKembali.setBounds(220, 280, 100, 40);
        
        tombolTambah.setBackground(Color.decode("#a9b5d1"));
        tombolKembali.setBackground(Color.decode("#ffb8c6"));

        controller = new ControllerJadwal(this);
        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.insertJadwal();
                dispose();
                new ViewData(username,userId);
            }
        });

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewData(username, userId);
            }
        });
    }

    public String getInputId_film() {
        return inputId_film.getSelectedItem().toString();
    }

    public String getInputId_teater() {
        return inputId_teater.getSelectedItem().toString();
    }

    public String getInputStudio() {
        return inputStudio.getSelectedItem().toString();
    }

    public String getInputTanggal_tayang() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(inputTanggal.getDate());
    }

    public String getInputJam_tayang() {
        return inputJam_tayang.getText();
    }

    public int getInputHarga() {
        int idFilm = Integer.parseInt(getInputId_film());
        int idTeater = Integer.parseInt(getInputId_teater());

        int hargaFilm = controller.getHargaFilmById(idFilm);
        int hargaTeater = controller.getHargaTeaterById(idTeater);

        // Jumlahkan harga film dan teater
        int totalHarga = hargaFilm + hargaTeater;

        return totalHarga;
    }
}
