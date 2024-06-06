package View;

import Controller.ControllerRegisterAdmin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class RegisterAdmin extends JFrame implements ActionListener {
    JLabel tulisan1 = new JLabel("Selamat Datang !");
    JLabel tulisan2 = new JLabel("Silahkan masuk untuk melanjutkan.");
    JLabel labelinput1 = new JLabel("Username");
    JTextField Inputuser = new JTextField();    
    JLabel labelpassword = new JLabel("Password");
    JPasswordField inputpassword = new JPasswordField();
    JLabel labelpassword2 = new JLabel("Ulangi Password");
    JPasswordField inputpassword2 = new JPasswordField(); 

    JButton login1 = new JButton("Register");

    public RegisterAdmin() {
        setVisible(true);
        setSize(500, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(tulisan1);
        tulisan1.setHorizontalAlignment(JLabel.CENTER);
        tulisan1.setBounds(100, 10, 250, 30);
        tulisan1.setFont(new Font("Arial", Font.BOLD, 25));

        add(tulisan2);
        tulisan2.setBounds(20, 50, 300, 20);
        tulisan2.setFont(new Font("Arial", Font.BOLD, 15));

        add(labelinput1);
        labelinput1.setBounds(40, 110, 100, 20);
        labelinput1.setFont(new Font("Arial", Font.BOLD, 15));

        add(Inputuser);
        Inputuser.setBounds(40, 130, 410, 35);

        add(labelpassword);
        labelpassword.setFont(new Font("Sans", Font.BOLD, 15));
        labelpassword.setBounds(40, 180, 100, 20);

        add(inputpassword);
        inputpassword.setBounds(40, 200, 410, 35);
        
        add(labelpassword2);
        labelpassword2.setFont(new Font("Sans", Font.BOLD, 15));
        labelpassword2.setBounds(40, 250, 200, 20);

        add(inputpassword2);
        inputpassword2.setBounds(40, 270, 410, 35);

        add(login1);
        login1.setBounds(40, 320, 410, 35);
        login1.setBackground(Color.white);
        login1.setFont(new Font("Arial", Font.BOLD, 15));
        login1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = Inputuser.getText();
        String password = new String(inputpassword.getPassword());
        String confirmPassword = new String(inputpassword2.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!");
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Password tidak cocok!");
        } else {
            ControllerRegisterAdmin controller = new ControllerRegisterAdmin();
            boolean isRegistered = controller.registerUser(username, password);
            if (isRegistered) {
                JOptionPane.showMessageDialog(this, "Registrasi Berhasil!");
                this.dispose(); // Menutup halaman registrasi
                new LoginPage(); // Membuka halaman login
            } else {
                JOptionPane.showMessageDialog(this, "Username sudah digunakan!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterAdmin().setVisible(true);
            }
        });
    }
}
