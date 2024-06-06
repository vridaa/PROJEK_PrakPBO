package Model.Film;

import java.util.List;

public interface InterfaceDAOFilm {
    
    public void insert(ModelFilm film);
    
    public void update(ModelFilm film);
    
    public void delete(int id);
    
    public List<ModelFilm> getAll();

    public List<ModelFilm> search(String text);

    public boolean isFilmExists(String judul, int durasi, String genre, String kategori, String tanggal_tayang_perdana_string, int hargafilm);

    public boolean isFilmExistsExceptId(int id, String judul, int durasi, String genre, String kategori, String tanggal_tayang_perdana_string, int hargafilm);

}
