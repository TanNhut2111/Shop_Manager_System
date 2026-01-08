package ShoesManager.DAO;
import ShoesManager.DTO.ChiTietHDDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ChiTietHDDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<ChiTietHDDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        // chỉ lấy những dòng chưa bị xóa
        if (condition == null || condition.trim().isEmpty()) {
            condition = "deleted = 0";
        } else {
            condition += " AND deleted = 0";
        }
        
        ResultSet result = this.connect.Select("tblchitiethd", condition, orderBy);
        ArrayList<ChiTietHDDTO> cthds = new ArrayList<>();
        while ( result.next() ) {
            ChiTietHDDTO cthd = new ChiTietHDDTO();
            cthd.setStrMaGiay(result.getString("Magiay"));
            cthd.setStrMaHD(result.getString("MaHD"));
            cthd.setiGiaBan(result.getInt("GiaBan"));
            cthd.setiSoLuong(result.getInt("soluong"));
            cthds.add(cthd);
        }
        connect.Close();
        return cthds;
    }
    
    public ArrayList<ChiTietHDDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<ChiTietHDDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    public Boolean them(ChiTietHDDTO cthd) throws Exception {
        connect = new MyConnectUnit();
        
        // Kiểm tra xem bản ghi đã tồn tại chưa
        String condition = "mahd = '" + cthd.getStrMaHD() + "' AND magiay = '" + cthd.getStrMaGiay() + "'";
        ResultSet rs = connect.Select("tblchitiethd", condition, null);

        if (rs.next()) {
            // Nếu tồn tại và đang deleted = 1 → cập nhật lại deleted = 0
            if (rs.getInt("deleted") == 1) {
                HashMap<String, Object> updateValues = new HashMap<>();
                updateValues.put("deleted", 0);
                updateValues.put("soluong", cthd.getiSoLuong());
                updateValues.put("giaban", cthd.getiGiaBan());
                Boolean check = connect.Update("tblchitiethd", updateValues, condition);
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
        insertValues.put("mahd", cthd.getStrMaHD());
        insertValues.put("magiay", cthd.getStrMaGiay());
        insertValues.put("soluong", cthd.getiSoLuong());
        insertValues.put("giaban", cthd.getiGiaBan());
        
        Boolean check = connect.Insert("tblchitiethd", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(ChiTietHDDTO cthd) throws Exception {
        connect = new MyConnectUnit();
        HashMap<String, Object> updateValues = new HashMap<>();
        updateValues.put("deleted", 1);
        
        String condition = " mahd = '"+cthd.getStrMaHD()+"' AND magiay = '"+cthd.getStrMaGiay()+"'";
        Boolean check = connect.Update("tblchitiethd", updateValues, condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(ChiTietHDDTO cthd) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("soluong", cthd.getiSoLuong());
        insertValues.put("giaban", cthd.getiGiaBan());
        
        String condition = " mahd = '"+cthd.getStrMaHD()+"' AND magiay = '"+cthd.getStrMaGiay()+"'";
        
        Boolean check = connect.Update("tblchitiethd", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
