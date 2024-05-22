package DAOImplement;

import Config.Connector;
import Model.DataBuku;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DAODataBuku implements DAOBukuInterface {

    Connection conn;
    final String select = "SELECT * FROM buku";
    final String search = "SELECT * FROM buku WHERE judul LIKE ?";
    final String insert = "INSERT INTO buku (judul, penulis, rating, harga) VALUES (?,?,?,?)";
    final String update = "UPDATE buku SET judul=?, penulis=?, rating=?, harga=? WHERE id=?";
    final String delete = "DELETE FROM buku WHERE id=?";

    public DAODataBuku() {
        conn = Connector.getConnection();
    }

    @Override
    public void insert(DataBuku db) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, db.getJudul());
            statement.setString(2, db.getPenulis());
            statement.setDouble(3, db.getRating());
            statement.setDouble(4, db.getHarga());
            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLIntegrityConstraintViolationException exc) {
            JOptionPane.showMessageDialog(null, "Film dengan judul tersebut sudah ada, Data gagal ditambahkan",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat menambahkan data, coba lagi",
                    "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DAODataBuku.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAODataBuku.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(DataBuku db) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(update);
            statement.setString(1, db.getJudul());
            statement.setString(2, db.getPenulis());
            statement.setDouble(3, db.getRating());
            statement.setDouble(4, db.getHarga());
            statement.setInt(5, db.getId());
            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diperbarui", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat menambahkan data, coba lagi",
                    "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DAODataBuku.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAODataBuku.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(delete);
            statement.setInt(1, id);
            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat menambahkan data, coba lagi",
                    "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DAODataBuku.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {

                Logger.getLogger(DAODataBuku.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }

    }

    @Override
    public List<DataBuku> getAll() {
        List<DataBuku> dataBuku = null;
        try {
            dataBuku = new ArrayList<DataBuku>();
            PreparedStatement statement = conn.prepareStatement(select);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DataBuku buku = new DataBuku();
                buku.setId(result.getInt("id"));
                buku.setJudul(result.getString("judul"));
                buku.setPenulis(result.getString("penulis"));
                buku.setRating(result.getDouble("rating"));
                buku.setHarga(result.getInt("harga"));
                dataBuku.add(buku);
            }
            statement.close();
        } catch (SQLException ex) {

            Logger.getLogger(DAODataBuku.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return dataBuku;
    }
}
