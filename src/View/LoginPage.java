package View;

/**
 *
 * author ASUS
 */
import Controller.ControllerLogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener {
    JLabel tulisan1 = new JLabel("Selamat Datang !");
    JLabel tulisan2 = new JLabel("Silahkan masuk untuk melanjutkan.");
    JLabel labelinput1 = new JLabel("Username");
    JTextField Inputuser = new JTextField();    
    JLabel labelpassword = new JLabel("Password");
    JPasswordField inputpassword = new JPasswordField(); // Ganti menjadi JPasswordField untuk keamanan
    
    JLabel labelRole = new JLabel("Role");
    JComboBox<String> roleComboBox = new JComboBox<>(new String[] { "admin", "pengguna" });

    JButton login1 = new JButton("Login");
    JButton registerButton = new JButton("Register");

    public LoginPage() {
        setVisible(true);
        setSize(500, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.WHITE);

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
        
        add(labelRole);
        labelRole.setFont(new Font("Sans", Font.BOLD, 15));
        labelRole.setBounds(40, 250, 100, 20);
        
        add(roleComboBox);
        roleComboBox.setBounds(40, 270, 410, 35);

        add(login1);
        login1.setBounds(40, 320, 410, 35);
        login1.setBackground(Color.decode("#edf6ff"));
        login1.setFont(new Font("Arial", Font.BOLD, 15));
        login1.addActionListener(this);

        add(registerButton);
        registerButton.setBounds(40, 370, 410, 35);
        registerButton.setBackground(Color.pink);
        registerButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the register page
                RegisterPage registerPage = new RegisterPage();
                registerPage.setVisible(true);
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Mengambil nilai dari username, password, dan role
            String username = Inputuser.getText();
            String password = new String(inputpassword.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            // Memberikan error handling jika username atau password kosong
            if (username.isEmpty()) {
                throw new Exception("Username tidak boleh kosong");
            }
            if (password.isEmpty()) {
                throw new Exception("Password tidak boleh kosong");
            }

            // Memeriksa autentikasi menggunakan ControllerLogin
            ControllerLogin controller = new ControllerLogin();
            
            // Check if the username exists
            if (!controller.checkUsername(username)) {
                throw new Exception("Username tidak terdaftar. Silakan membuat akun baru.");
            }

            // If username exists, check the password
            if (!controller.checkPassword(username, password)) {
                throw new Exception("Password tidak sesuai");
            }

            // Authenticate the role
            String actualRole = controller.authenticate(username, password);
            if (!actualRole.equals(role)) {
                throw new Exception("Role tidak sesuai");
            }

            // Retrieve the user ID
            int userId = controller.getUserId(username);
            if (userId == -1) {
                throw new Exception("Gagal mendapatkan ID pengguna");
            }

            // Jika autentikasi berhasil, periksa perannya
            JOptionPane.showMessageDialog(this, "Login berhasil sebagai " + role);

            // Buka interface berdasarkan role
            if (role.equals("admin")) {
                AdminPage adminPage = new AdminPage(username, userId);
                adminPage.setVisible(true);
                // Buka interface admin
            } else {
                System.out.println(username + userId);
                PenggunaPage penggunaPage = new PenggunaPage(username, userId);
                penggunaPage.setVisible(true);
                // Buka interface pengguna
            }
            this.dispose();
        } catch (Exception error) {
            // Menampilkan error dalam bentuk pop up
            JOptionPane.showMessageDialog(this, error.getMessage());
        }
    }
}