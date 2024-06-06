package View.Teater;

import Controller.ControllerTeater;
import Model.Teater.ModelTeater;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditData extends JFrame {

    ControllerTeater controller;

    JLabel header = new JLabel("EDIT DATA TEATER");
    JLabel labelInputKelas = new JLabel("Kelas");
    JTextField inputKelas = new JTextField();
    JLabel labelInputHarga = new JLabel("Harga");
    JTextField inputHarga = new JTextField();
    JLabel labelInputKapasitas = new JLabel("Kapasitas");
    JTextField inputKapasitas = new JTextField();
    JButton tombolEdit = new JButton("Edit");
    JButton tombolKembali = new JButton("Kembali");

    public EditData(ModelTeater teater, String username, int userId) {
        setTitle("Edit Data Teater");
        setVisible(true);
        setSize(360, 310);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.decode("#f5f5fa"));

        add(header);
        add(labelInputKelas);
        add(inputKelas);
        add(labelInputHarga);
        add(inputHarga);
        add(labelInputKapasitas);
        add(inputKapasitas);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(85, 20, 440, 24);
        header.setFont(header.getFont().deriveFont(20.0f));
        
        labelInputKelas.setBounds(20, 60, 200, 30);
        inputKelas.setBounds(100, 60, 220, 30);
        labelInputHarga.setBounds(20, 100, 200, 30);
        inputHarga.setBounds(100, 100, 220, 30);
        labelInputKapasitas.setBounds(20, 140, 200, 30);
        inputKapasitas.setBounds(100, 140, 220, 30);
        tombolEdit.setBounds(70, 190, 100, 40);
        tombolKembali.setBounds(180, 190, 100, 40);
        
        tombolEdit.setBackground(Color.decode("#a9b5d1"));
        tombolKembali.setBackground(Color.decode("#ffb8c6"));

        inputKelas.setText(teater.getKelas());
        inputHarga.setText(String.valueOf(teater.getHarga()));
        inputKapasitas.setText(String.valueOf(teater.getKapasitas()));

        controller = new ControllerTeater(this);

        tombolEdit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        try {
            if (inputKelas.getText().isEmpty() || inputHarga.getText().isEmpty() ||
                inputKapasitas.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Inputan ada yang kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.editTeater(teater.getId());
            dispose();
            new ViewData(username,userId);
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
    
    public String getInputKelas() {
    return inputKelas.getText();
    }

    public int getInputHarga() {
    return Integer.parseInt(inputHarga.getText());
    }
    
    public int getInputKapasitas() {
    return Integer.parseInt(inputKapasitas.getText());
    }
    
}
