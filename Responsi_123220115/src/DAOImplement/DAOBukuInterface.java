package DAOImplement;

import Model.DataBuku;
import java.util.List;

public interface DAOBukuInterface {
    public void insert(DataBuku db);
    public void update(DataBuku db);
    public void delete(int id);
    public List<DataBuku> getAll();
}
