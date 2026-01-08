package ShoesManager.DAO;
import ShoesManager.DTO.ChiTietPNDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ChiTietPNDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<ChiTietPNDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        // chỉ lấy những dòng chưa bị xóa
        if (condition == null || condition.trim().isEmpty()) {
            condition = "deleted = 0";
        } else {
            condition += " AND deleted = 0";
        }
        
        ResultSet result = this.connect.Select("tblchitietpn", condition, orderBy);
        ArrayList<ChiTietPNDTO> ctpns = new ArrayList<>();
        while ( result.next() ) {
            ChiTietPNDTO ctpn = new ChiTietPNDTO();
            ctpn.setStrMaGiay(result.getString("Magiay"));
            ctpn.setStrMaPN(result.getString("MaPN"));
            ctpn.setiGiaNhap(result.getInt("Gianhap"));
            ctpn.setiSoLuong(result.getInt("soluong"));
            ctpn.setDeleted(result.getInt("deleted"));
            ctpns.add(ctpn);
        }
        connect.Close();
        return ctpns;
    }
    
    public ArrayList<ChiTietPNDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<ChiTietPNDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    public Boolean them(ChiTietPNDTO ctpn) throws Exception {
        connect = new MyConnectUnit();
            
        // Kiểm tra xem bản ghi đã tồn tại chưa
        String condition = "mapn = '" + ctpn.getStrMaPN() + "' AND magiay = '" + ctpn.getStrMaGiay() + "'";
        ResultSet rs = connect.Select("tblchitietpn", condition, null);

        if (rs.next()) {
            // Nếu tồn tại và đang deleted = 1 → cập nhật lại deleted = 0
            if (rs.getInt("deleted") == 1) {
                HashMap<String, Object> updateValues = new HashMap<>();
                updateValues.put("deleted", 0);
                updateValues.put("soluong", ctpn.getiSoLuong());
                updateValues.put("gianhap", ctpn.getiGiaNhap());
                Boolean check = connect.Update("tblchitietpn", updateValues, condition);
                rs.close();
                connect.Close();
                return check;
            } else {
                // Nếu đã tồn tại và deleted = 0 thì không thêm mới
                rs.close();
                connect.Close();
                return false;
            }
        }

        // Nếu chưa tồn tại, thêm mới
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("mapn", ctpn.getStrMaPN());
        insertValues.put("magiay", ctpn.getStrMaGiay());
        insertValues.put("soluong", ctpn.getiSoLuong());
        insertValues.put("gianhap", ctpn.getiGiaNhap());
        
        Boolean check = connect.Insert("tblchitietpn", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(ChiTietPNDTO ctpn) throws Exception {
        connect = new MyConnectUnit();
        HashMap<String, Object> updateValues = new HashMap<>();
        updateValues.put("deleted", 1); //gán đã xóa
        
        String condition = "mapn = '" + ctpn.getStrMaPN() + "' AND magiay = '" + ctpn.getStrMaGiay() + "'";
        Boolean check = connect.Update("tblchitietpn", updateValues, condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(ChiTietPNDTO ctpn) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("soluong", ctpn.getiSoLuong());
        insertValues.put("gianhap", ctpn.getiGiaNhap());
        
        String condition = " mapn = '"+ctpn.getStrMaPN()+"' AND magiay = '"+ctpn.getStrMaGiay()+"'";
        
        Boolean check = connect.Update("tblchitietpn", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
