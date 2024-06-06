package Model;

import java.sql.*;

public class Connector {
  
    private static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static String nama_db = "bioskop2";
    private static String url_db = "jdbc:mysql://localhost:3306/" + nama_db;
    private static String username_db = "root";
    private static String password_db = "";

    static Connection conn;
    
    // Mencoba menghubungkan program kita dengan ke database MySQL.
    public static Connection Connect() {
        try {
            // 1. Register driver yang akan dipakai
            Class.forName(jdbc_driver);
            
            // 2. Buat koneksi ke database
            conn = DriverManager.getConnection(url_db, username_db, password_db);

            // 3. Menampilkan pesan "Connection Success" jika berhasil terhubung ke database.
            System.out.println("MySQL Connected");
        } catch (ClassNotFoundException | SQLException exception) {
            // Menampilkan pesan error ketika MySQL gagal terhubung.
            System.out.println("Connection Failed: " + exception.getLocalizedMessage());
        }
        return conn;
    }

    public PreparedStatement prepareStatement(String sql) {
        if (conn == null) {
            System.err.println("Error: No database connection established.");
            return null; 
        }

        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.err.println("Error preparing statement: " + e.getMessage());
            e.printStackTrace();
            return null; 
        }
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
