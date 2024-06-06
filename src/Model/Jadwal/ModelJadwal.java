package Model.Jadwal;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ModelJadwal {

    private int id;
    private int id_film;
    private int id_teater;
    private String studio;
    private Date tanggal_tayang;
    private String jam_tayang;
    private int harga;
    private String film_judul; // Added film_judul field
    private String teater_nama; // Added teater_nama field
    private int kapasitas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getkapasitas() {
        return kapasitas;
    }

    public void setkapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public int getId_teater() {
        return id_teater;
    }

    public void setId_teater(int id_teater) {
        this.id_teater = id_teater;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Date getTanggal_tayang() {
        return tanggal_tayang;
    }

    public void setTanggal_tayang(Date tanggal_tayang) {
        this.tanggal_tayang = tanggal_tayang;
    }

    public void setTanggal_tayangFromString(String tanggal_tayang_string) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal_tayang_string);
            this.tanggal_tayang = new Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getJam_tayang() {
        return jam_tayang;
    }

    public void setJam_tayang(String jam_tayang) {
        this.jam_tayang = jam_tayang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
   
    //Setter method for film_judul
    public void setFilm_judul(String film_judul) {
        this.film_judul = film_judul;
    }

    // Setter method for teater_nama
    public void setTeater_nama(String teater_nama) {
        this.teater_nama = teater_nama;
    }
 

    public String getFilm_judul() {
        return film_judul;
    }

    // method untuk mendapatkan nama teater
    public String getTeater_nama() {
        return teater_nama;
    }
    
}
