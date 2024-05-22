package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableDataBuku extends AbstractTableModel{
    
    List<DataBuku> dataBuku;
    
    public TableDataBuku(List<DataBuku> dataBuku){
        this.dataBuku = dataBuku;
    }
    
    @Override
    public int getRowCount() {
        return dataBuku.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int column){
        switch (column) {
            case 0:
                return "Id";                
            case 1:
                return "Judul";          
            case 2:
                return "Penulis"; 
            case 3:
                return "Rating";
            case 4:
                return "Harga";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return dataBuku.get(row).getId();
            case 1:
                return dataBuku.get(row).getJudul();
            case 2:
                return dataBuku.get(row).getPenulis();
            case 3:
                return dataBuku.get(row).getRating();
            case 4:
                return dataBuku.get(row).getHarga();
            default:
                return null;
        }
    }
    
}
