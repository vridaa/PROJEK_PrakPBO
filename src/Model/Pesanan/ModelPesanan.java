/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Pesanan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ModelPesanan {
    private Integer id, userId,id_jadwal, Studio,harga,jumlah,total_harga, Kapasitas;
    private String teater, Judul_Film, Jam;
    private Date Tanggal;
    


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getKapasitas() {
        return Kapasitas;
    }

    public void setKapasitas(Integer Kapasitas) {
        this.Kapasitas = Kapasitas;
    }
    
    public Integer getStudio() {
        return Studio;
    }

    public void setStudio(Integer Studio) {
        this.Studio = Studio;
    }
    
    public Integer getuserId() {
        return userId;
    }

    public void setuserId(Integer userId) {
        this.userId= userId;
    }

    public int getId_Jadwal() {
        return id_jadwal;
    }

    public void setId_Jadwal(int id_jadwal) {
        this.id_jadwal = id_jadwal;
    }
    
    public int getharga(){
        return harga;
    }
    
    public void setharga(int harga){
        this.harga= harga;
    }

    public Integer gettotal_harga() {
        return total_harga;
    }

    public void settotal_harga(Integer total_harga) {
        this.total_harga = total_harga;
    }

    public Integer getjumlah() {
        return jumlah;
    }

    public void setjumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
    
    public String getJudul_Film(){
        return Judul_Film;
    }
    
    public void setJudul_Film(String Judul_Film){
        this.Judul_Film= Judul_Film;
    }
    
    public String getTeater() {
        return teater;
    }

    public void setTeater(String teater) {
        this.teater= teater;
    }
    
     public java.sql.Date getTanggal() {
        return (java.sql.Date) Tanggal;
    }

    public void setTanggal(java.sql.Date Tanggal) {
        this.Tanggal = Tanggal;
    }

    public void setTanggalFromString(String Tanggalstring) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Tanggalstring);
            this.Tanggal = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getJam() {
        return Jam;
    }

    public void setJam(String Jam) {
        this.Jam = Jam;
    }
}
