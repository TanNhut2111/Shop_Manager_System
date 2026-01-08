package ShoesManager.DAO;
import ShoesManager.DTO.ChiTietKMDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ChiTietKMDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<ChiTietKMDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        // chỉ lấy những dòng chưa bị xóa
        if (condition == null || condition.trim().isEmpty()) {
            condition = "deleted = 0";
        } else {
            condition += " AND deleted = 0";
        }
        
        ResultSet result = this.connect.Select("tblchitietkm", condition, orderBy);
        ArrayList<ChiTietKMDTO> ctkms = new ArrayList<>();
        while ( result.next() ) {
            ChiTietKMDTO ctkm = new ChiTietKMDTO();
            ctkm.setStrMaGiay(result.getString("Magiay"));
            ctkm.setStrMaKM(result.getString("MaKM"));
            ctkm.setTiLeKM(result.getDouble("TiLeKm"));
            ctkm.setDeleted(result.getInt("deleted"));
            ctkms.add(ctkm);
        }
        connect.Close();
        return ctkms;
    }
    
    public ArrayList<ChiTietKMDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<ChiTietKMDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    public Boolean them(ChiTietKMDTO ctkm) throws Exception {
        connect = new MyConnectUnit();

        // Kiểm tra xem bản ghi đã tồn tại chưa
        String condition = "makm = '" + ctkm.getStrMaKM() + "' AND magiay = '" + ctkm.getStrMaGiay() + "'";
        ResultSet rs = connect.Select("tblchitietkm", condition, null);

        if (rs.next()) {
            // Nếu tồn tại và đang deleted = 1 → cập nhật lại deleted = 0
            if (rs.getInt("deleted") == 1) {
                HashMap<String, Object> updateValues = new HashMap<>();
                updateValues.put("deleted", 0);
                updateValues.put("tilekm", ctkm.getTiLeKM());
                Boolean check = connect.Update("tblchitietkm", updateValues, condition);
                connect.Close();
                return check;
            } else {
                rs.close();
                connect.Close();
                return false;
            }
        }

        // Nếu chưa tồn tại, thêm mới
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("makm", ctkm.getStrMaKM());
        insertValues.put("magiay", ctkm.getStrMaGiay());
        insertValues.put("tilekm", ctkm.getTiLeKM());
        
        Boolean check = connect.Insert("tblchitietkm", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(ChiTietKMDTO ctkm) throws Exception {
        connect = new MyConnectUnit();
        HashMap<String, Object> updateValues = new HashMap<>();
        updateValues.put("deleted", 1); //gán đã xóa
        
        String condition = " makm = '"+ctkm.getStrMaKM()+"' AND magiay = '"+ctkm.getStrMaGiay()+"'";
        Boolean check = connect.Update("tblchitietkm", updateValues, condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(ChiTietKMDTO ctkm) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("magiay", ctkm.getStrMaGiay());
        insertValues.put("tilekm", ctkm.getTiLeKM());
        
        String condition = " makm = '"+ctkm.getStrMaKM()+"' AND magiay = '"+ctkm.getStrMaGiay()+"'";
        
        Boolean check = connect.Update("tblchitietkm", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
