package Model.Jadwal;

import java.util.List;

public interface InterfaceDAOJadwal {
    
    public void insert(ModelJadwal jadwal);
    
    public void update(ModelJadwal jadwal);
    
    public void delete(int id);
    
    public List<ModelJadwal> getAll();

    public List<Integer> getAllFilmIds();

    public List<Integer> getAllTeaterIds();
    public List<ModelJadwal> search(String cari);

    public int getHargaFilm(int filmId);

    public int getHargaTeater(int teaterId);

    public int getHargaFilmById(int id_film);

    public int getHargaTeaterById(int id_teater);
    
    public int getKapasitasById(int id_teater);

    public boolean isFilmExistInJadwal(Integer id);

    public boolean isTeaterExistInJadwal(Integer id);

    public boolean isJadwalExists(int id_film, String studio, String tanggal_tayang_string, String jam_tayang);
}
