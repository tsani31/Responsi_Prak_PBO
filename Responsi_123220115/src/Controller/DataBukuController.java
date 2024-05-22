package Controller;

import DAOImplement.DAOBukuInterface;
import DAOImplement.DAODataBuku;
import Model.DataBuku;
import Model.TableDataBuku;
import View.Main;
import View.MainView;
import java.util.List;
import javax.swing.JOptionPane;

public class DataBukuController {
    MainView frame;
    DAOBukuInterface bukuDAO;
    List<DataBuku> dataBuku;

    public DataBukuController(MainView frame) {
        this.frame = frame;
        bukuDAO = new DAODataBuku();
        dataBuku = bukuDAO.getAll();
    }

    public void fillTable() {
        dataBuku = bukuDAO.getAll();
        TableDataBuku tdm = new TableDataBuku(dataBuku);
        frame.getTable().setModel(tdm);
    }

    public void insert() {
        try {
            DataBuku db = new DataBuku();
            String judul = frame.getInputJudul().getText();
            String penulis = frame.getInputPenulis().getText();
            double rating = Double.parseDouble(frame.getInputRating().getText());
            double hargaPerJam = Double.parseDouble(frame.getInputHarga().getText());
            double hargaFinal = hargaPerJam + 500 + (rating*100);
            db.setJudul(judul);
            db.setPenulis(penulis);
            db.setRating(rating);
            db.setHarga(hargaFinal);
            bukuDAO.insert(db);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harap masukan inputan berupa angka pada kolom Alur, Penokohan, dan Akting",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update() {
        try {
            String strId = frame.getInputId().getText();
            int id = Integer.parseInt(strId);
            DataBuku dm = new DataBuku();
            String judul = frame.getInputJudul().getText();
            String penulis = frame.getInputPenulis().getText();
            double rating = Double.parseDouble(frame.getInputRating().getText());
            double hargaPerJam = Double.parseDouble(frame.getInputHarga().getText());
            double hargaFinal = hargaPerJam + 500 + (rating*100);
            dm.setId(id);
            dm.setJudul(judul);
            dm.setPenulis(penulis);
            dm.setRating(rating);
            dm.setHarga(hargaFinal);
            bukuDAO.update(dm);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harap masukan inputan berupa angka pada kolom Alur, Penokohan, dan Akting",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete() {
        String strId = frame.getInputId().getText();
        int id = Integer.parseInt(strId);
        bukuDAO.delete(id);
    }
    
}
