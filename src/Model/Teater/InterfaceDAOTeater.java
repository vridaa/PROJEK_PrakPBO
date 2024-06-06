package Model.Teater;

import java.util.List;

public interface InterfaceDAOTeater {
    
    public void insert(ModelTeater teater);
    
    public void update(ModelTeater teater);
    
    public void delete(int id);
    
    public List<ModelTeater> getAll();
    
    public List<ModelTeater> search(String text);

    public boolean isTeaterExists(String kelas, int harga, int kapasitas);

    public boolean isTeaterExistsExceptId(int id, String kelas, int harga, int kapasitas);

}
