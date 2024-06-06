package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminPage extends JFrame {
    
    //JLabel image = new JLabel(new ImageIcon(getClass().getResource("/images/Cinema_XXI_1.png")));
    JLabel header = new JLabel();
    JButton tombolFilm = new JButton("Data Film");
    JButton tombolJadwal = new JButton("Data Jadwal");
    JButton tombolTeater = new JButton("Data Teater");
    JButton tombolAdmin= new JButton("Tambah Admin");
    JButton tombolLogout = new JButton("Logout");

    public AdminPage(String username, int userId) {
        setTitle("Data Film, Jadwal, dan Teater");
        setVisible(true);
        setSize(390, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);
  
        tombolFilm.setBackground(Color.decode("#edf6ff"));
        tombolJadwal.setBackground(Color.decode("#edf6ff"));
        tombolTeater.setBackground(Color.decode("#edf6ff"));
        tombolAdmin.setBackground(Color.decode("#edf6ff"));
        tombolLogout.setBackground(Color.decode("#ff6a70"));

        //add(image);
        add(header);
        header.setText("Selamat Datang "+username);
        add(tombolFilm);
        add(tombolJadwal);
        add(tombolTeater);
        add(tombolLogout);
        add(tombolAdmin);
        
        header.setFont(header.getFont().deriveFont(24.0f));
        //image.setBounds(20, 60, 100, 100);
        header.setBounds(60, 12, 440, 36);
        tombolFilm.setBounds(30, 60, 320, 40);
        tombolFilm.setFont(tombolJadwal.getFont().deriveFont(14.0f));
        tombolTeater.setBounds(30, 110, 320, 40);
        tombolTeater.setFont(tombolJadwal.getFont().deriveFont(14.0f));
        tombolJadwal.setBounds(30, 160, 320, 40);
        tombolJadwal.setFont(tombolJadwal.getFont().deriveFont(14.0f));
        tombolAdmin.setBounds(30, 210, 320, 40);
        tombolAdmin.setFont(tombolAdmin.getFont().deriveFont(14.0f));
        tombolLogout.setBounds(30,260,320,40);

        tombolFilm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Film.ViewData(username, userId);
            }
        });
        
        tombolJadwal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Jadwal.ViewData(username, userId);
            }
        });
        
        tombolTeater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Teater.ViewData(username, userId);
            }
        });
        
        tombolLogout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage();
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        });
        
        tombolAdmin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterAdmin();
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        });
    }

    
}
