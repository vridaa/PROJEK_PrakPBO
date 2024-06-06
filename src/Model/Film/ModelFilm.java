package Model.Film;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ModelFilm {
    private Integer id, durasi, hargafilm;
    private String judul, genre, kategori;
    private Date tanggal_tayang_perdana;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public void setDurasi(Integer durasi) {
        this.durasi = durasi;
    }

    public Integer getHargafilm() {
        return hargafilm;
    }

    public void setHargafilm(Integer hargafilm) {
        this.hargafilm = hargafilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Date getTanggal_tayang_perdana() {
        return tanggal_tayang_perdana;
    }

    public void setTanggal_tayang_perdana(Date tanggal_tayang_perdana) {
        this.tanggal_tayang_perdana = tanggal_tayang_perdana;
    }

    public void setTanggal_tayang_perdanaFromString(String tanggal) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(sdf.parse(tanggal).getTime());
            this.tanggal_tayang_perdana = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
