/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ControllerJadwal;
import Controller.ControllerPesanan;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ASUS
 */
public class PenggunaPage extends javax.swing.JFrame {

    /**
     * Creates new form PenggunaPage
     */
    ControllerJadwal CD;
    ControllerPesanan CP;
    Map<String, Integer> seatCount;
    public PenggunaPage(String username, int userId) {
        
        initComponents();
        Nama.setText(username);
        ID.setText(String.valueOf(userId));
        CD = new ControllerJadwal(this);
        CD.isijadwal();
        CP = new ControllerPesanan(this);
        ID.setEditable(false);
        Nama.setEditable(false);
        FILM.setEditable(false);
        ID_Jadwal.setEditable(false);
        HargaSatuan.setEditable(false);
        Total.setEditable(false);
        Teater.setEditable(false);
        Jumlah.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                validateJumlah();
            }
            public void removeUpdate(DocumentEvent e) {
                validateJumlah();
            }
            public void insertUpdate(DocumentEvent e) {
                validateJumlah();
            }

            public void validateJumlah() {
                try {
                    int jumlah = Integer.parseInt(Jumlah.getText());
                    if (jumlah > 5) {
                        Jumlah.setText("5");
                    }
                } catch (NumberFormatException e) {
                    // Handle exception if Jumlah is not a number
                }
            }
        });

        
//        CD.showAllJadwal();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SearchBar = new javax.swing.JTextField();
        ButtonCari = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        Nama = new javax.swing.JTextField();
        Total = new javax.swing.JTextField();
        FILM = new javax.swing.JTextField();
        ID_Jadwal = new javax.swing.JTextField();
        HargaSatuan = new javax.swing.JTextField();
        Jumlah = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Pesan = new javax.swing.JButton();
        Riwayat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableJadwal = new javax.swing.JTable();
        Logout = new javax.swing.JButton();
        Teater = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SearchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBarActionPerformed(evt);
            }
        });
        SearchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchBarKeyPressed(evt);
            }
        });
        getContentPane().add(SearchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 30));

        ButtonCari.setText("Cari");
        ButtonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCariActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 32, 77, 30));

        jLabel1.setText("ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 73, 66, 24));

        jLabel2.setText("Nama");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 103, 66, 24));

        jLabel3.setText(" Total Harga");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 301, 86, 24));

        jLabel4.setText("FILM");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 169, 66, 24));

        jLabel5.setText("ID Jadwal");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 133, 66, 24));

        jLabel6.setText("Teater");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 205, 66, 24));

        jLabel7.setText("Harga satuan");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 241, 86, 24));

        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });
        getContentPane().add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 74, 111, -1));

        Nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaActionPerformed(evt);
            }
        });
        getContentPane().add(Nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 104, 111, -1));

        Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalActionPerformed(evt);
            }
        });
        getContentPane().add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 302, 111, -1));

        FILM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FILMActionPerformed(evt);
            }
        });
        getContentPane().add(FILM, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 111, -1));

        ID_Jadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_JadwalActionPerformed(evt);
            }
        });
        getContentPane().add(ID_Jadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, 111, -1));

        HargaSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HargaSatuanActionPerformed(evt);
            }
        });
        getContentPane().add(HargaSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, 111, -1));

        Jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumlahActionPerformed(evt);
            }
        });
        Jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JumlahKeyTyped(evt);
            }
        });
        getContentPane().add(Jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 271, 111, -1));

        jLabel8.setText("Jumlah");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 271, 66, 24));

        Pesan.setBackground(new java.awt.Color(204, 255, 204));
        Pesan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Pesan.setText("PESAN");
        Pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesanActionPerformed(evt);
            }
        });
        getContentPane().add(Pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 380, 101, 33));

        Riwayat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Riwayat.setText("Riwayat Anda");
        Riwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RiwayatActionPerformed(evt);
            }
        });
        getContentPane().add(Riwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, 161, 33));

        TableJadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableJadwalMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableJadwal);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 72, 554, 284));

        Logout.setBackground(new java.awt.Color(255, 51, 51));
        Logout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        getContentPane().add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 100, 31));
        getContentPane().add(Teater, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 200, 111, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\Downloads\\Pink And Faded Red Illustration Cinema Ticket.jpg")); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-130, 0, 950, 510));

        jLabel10.setText("jLabel10");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBarActionPerformed
        // TODO add your handling code here:
//        String judul = getJjudul().getText();
//        CD.isitabelcari(judul);
    }//GEN-LAST:event_SearchBarActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDActionPerformed

    private void NamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaActionPerformed

    private void TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalActionPerformed

    private void FILMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FILMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FILMActionPerformed

    private void ID_JadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_JadwalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_JadwalActionPerformed

    private void HargaSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HargaSatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HargaSatuanActionPerformed

    private void JumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumlahActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JumlahActionPerformed

    private void PesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesanActionPerformed
        // TODO add your handling code here:
        CP.insertPesanan();
        CD.isijadwal();
        ID_Jadwal.setText("");
        FILM.setText("");
        Teater.setText("");
        HargaSatuan.setText("");
        Jumlah.setText("");
        Total.setText("");
        
    }//GEN-LAST:event_PesanActionPerformed

    private void ButtonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCariActionPerformed
        String judul = getSearchBar().getText();
        CD.searchJadwal(judul);
// TODO add your handling code here:
    }//GEN-LAST:event_ButtonCariActionPerformed

    private void TableJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJadwalMouseClicked
        // TODO add your handling code here:
        int baris = TableJadwal.getSelectedRow();
        ID_Jadwal.setText(TableJadwal.getValueAt(baris, 0).toString());
        FILM.setText(TableJadwal.getValueAt(baris, 1).toString());
        Teater.setText(TableJadwal.getValueAt(baris, 2).toString());
        HargaSatuan.setText(TableJadwal.getValueAt(baris, 7).toString());
        
    }//GEN-LAST:event_TableJadwalMouseClicked

    private void JumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahKeyPressed
        // TODO add your handling code here:
     try {
        String jumlahText = Jumlah.getText().trim(); // Hapus spasi di awal dan akhir
        if (!jumlahText.isEmpty()) { // Periksa apakah input tidak kosong
            int jumlah = Integer.parseInt(jumlahText);
            if (jumlah > 5) {
                JOptionPane.showMessageDialog(this, "Jumlah pembelian maksimal 5 dalam 1 kali .");
                Jumlah.setText("5");
                jumlah = 5; // Batasi jumlah menjadi 5 jika lebih dari 5
            }
            
            String hargaSatuanText = HargaSatuan.getText().trim(); // Hapus spasi di awal dan akhir
            if (!hargaSatuanText.isEmpty()) { // Periksa apakah harga satuan tidak kosong
                int hargaSatuan = Integer.parseInt(hargaSatuanText);
                
                // Hitung total harga
                int total = hargaSatuan * jumlah;
                
                // Tampilkan total harga
                Total.setText(Integer.toString(total));
            }
        }
    } catch (NumberFormatException e) {
        // Handle exception if Jumlah or HargaSatuan is not a number
        JOptionPane.showMessageDialog(this, "Masukkan angka yang valid.");
        Jumlah.setText(""); // Kosongkan input jumlah
        Total.setText("");  // Kosongkan total harga
    }
    }//GEN-LAST:event_JumlahKeyPressed

    private void JumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahKeyTyped
        
    }//GEN-LAST:event_JumlahKeyTyped

    private void RiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RiwayatActionPerformed
        // TODO add your handling code here:
        dispose();
        String username= Nama.getText();
        int userid=Integer.parseInt(ID.getText());
        RiwayatPesanan rwyt = new RiwayatPesanan(username, userid);
        rwyt.setVisible(true);
        
    }//GEN-LAST:event_RiwayatActionPerformed

    private void SearchBarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchBarKeyPressed
       String judul =getSearchBar().getText();
        CD.searchJadwal(judul);
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchBarKeyPressed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
      dispose();
        LoginPage login = new LoginPage();
      login.setVisible(true);
       
        // TODO add your handling code here:
    }//GEN-LAST:event_LogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PenggunaPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenggunaPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenggunaPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenggunaPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenggunaPage("",0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCari;
    private javax.swing.JTextField FILM;
    private javax.swing.JTextField HargaSatuan;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField ID_Jadwal;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JButton Logout;
    private javax.swing.JTextField Nama;
    private javax.swing.JButton Pesan;
    private javax.swing.JButton Riwayat;
    private javax.swing.JTextField SearchBar;
    private javax.swing.JTable TableJadwal;
    private javax.swing.JTextField Teater;
    private javax.swing.JTextField Total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    public JTextField getFILM() {
        return FILM;
    }

    public void setFILM(JTextField FILM) {
        this.FILM = FILM;
    }

    public JTextField getHargaSatuan() {
        return HargaSatuan;
    }

    public void setHargaSatuan(JTextField HargaSatuan) {
        this.HargaSatuan = HargaSatuan;
    }

    public JTextField getID() {
        return ID;
    }

    public void setID(JTextField ID) {
        this.ID = ID;
    }

    public JTextField getID_Jadwal() {
        return ID_Jadwal;
    }

    public void setID_Jadwal(JTextField ID_Jadwal) {
        this.ID_Jadwal = ID_Jadwal;
    }

    public JTextField getJumlah() {
        return Jumlah;
    }

    public void setJumlah(JTextField Jumlah) {
        this.Jumlah = Jumlah;
    }

   

    public JTextField getNama() {
        return Nama;
    }

    public void setNama(JTextField Nama) {
        this.Nama = Nama;
    }

    public JButton getPesan() {
        return Pesan;
    }

    public void setPesan(JButton Pesan) {
        this.Pesan = Pesan;
    }

    public JButton getRiwayat() {
        return Riwayat;
    }

    public void setRiwayat(JButton Riwayat) {
        this.Riwayat = Riwayat;
    }

    public JTextField getSearchBar() {
        return SearchBar;
    }

    public void setSearchBar(JTextField SearchBar) {
        this.SearchBar = SearchBar;
    }

    public JTable getTableJadwal() {
        return TableJadwal;
    }

    public void setTableJadwal(JTable TableJadwal) {
        this.TableJadwal = TableJadwal;
    }

    public JTextField getTotal() {
        return Total;
    }

    public void setTotal(JTextField Total) {
        this.Total = Total;
    }

    public JTextField getTeater() {
        return Teater;
    }

    public void setTeater(JTextField Teater) {
        this.Teater = Teater;
    }

   


}



