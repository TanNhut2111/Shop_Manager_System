package ShoesManager.DAO;
import ShoesManager.DTO.SanPhamDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class SanPhamDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<SanPhamDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("tblsanpham", condition, orderBy);
        ArrayList<SanPhamDTO> sps = new ArrayList<>();
        while ( result.next() ) {
            SanPhamDTO sp = new SanPhamDTO();
            sp.setStrMaGiay(result.getString("Magiay"));
            sp.setStrChatLieu(result.getString("ChatLieu"));
            sp.setStrDoiTuongSD(result.getString("doituongsd"));
            sp.setStrMaLoai(result.getString("maloai"));
            sp.setStrMaMau(result.getString("mamau"));
            sp.setStrMaThuongHieu(result.getString("mathuonghieu"));
            sp.setStrMaxx(result.getString("maxx"));
            sp.setStrTenGiay(result.getString("tengiay"));
            sp.setiGia(result.getInt("gia"));
            sp.setiSize(result.getInt("Size"));
            sp.setiSoLuong(result.getInt("soluong"));
            
            sps.add(sp);
        }
        connect.Close();
        return sps;
    }
    
    public ArrayList<SanPhamDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<SanPhamDTO> docDB() throws Exception {
        return docDB("deleted = 0");
    }
    
    public Boolean them(SanPhamDTO sp) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("magiay", sp.getStrMaGiay());
        insertValues.put("soluong", sp.getiSoLuong());
        insertValues.put("gia", sp.getiGia());
        insertValues.put("size", sp.getiSize());
        insertValues.put("chatlieu", sp.getStrChatLieu());
        insertValues.put("doituongsd", sp.getStrDoiTuongSD());
        insertValues.put("maloai", sp.getStrMaLoai());
        insertValues.put("mamau", sp.getStrMaMau());
        insertValues.put("mathuonghieu", sp.getStrMaThuongHieu());
        insertValues.put("maxx", sp.getStrMaxx());
        insertValues.put("tengiay", sp.getStrTenGiay());
        
        Boolean check = connect.Insert("tblsanpham", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(SanPhamDTO sp) throws Exception {
        connect = new MyConnectUnit();
        String condition = " magiay = '"+sp.getStrMaGiay()+"'";

        // cập nhật deleted = 1 chứ không xóa thật
        HashMap<String, Object> updateValues = new HashMap<>();
        updateValues.put("deleted", 1);
        
        Boolean check = connect.Update("tblsanpham", updateValues, condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(SanPhamDTO sp) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("soluong", sp.getiSoLuong());
        insertValues.put("gia", sp.getiGia());
        insertValues.put("size", sp.getiSize());
        insertValues.put("chatlieu", sp.getStrChatLieu());
        insertValues.put("doituongsd", sp.getStrDoiTuongSD());
        insertValues.put("maloai", sp.getStrMaLoai());
        insertValues.put("mamau", sp.getStrMaMau());
        insertValues.put("mathuonghieu", sp.getStrMaThuongHieu());
        insertValues.put("maxx", sp.getStrMaxx());
        insertValues.put("tengiay", sp.getStrTenGiay());
        
        System.out.println(sp.toString());
        
        String condition = " magiay = '"+sp.getStrMaGiay()+"'";
        
        Boolean check = connect.Update("tblsanpham", insertValues, condition);
        
        connect.Close();
        return check;
    }

    public String getNewMaSP() throws Exception {
        connect = new MyConnectUnit();

        // Lấy mã SP mới nhất
        String query = "SELECT TOP 1 magiay FROM tblsanpham ORDER BY magiay DESC";
        ResultSet rs = connect.excuteQuery(query);

        String newMaSP = "SP001"; // mặc định
        if (rs.next()) {
            String lastMaSP = rs.getString("magiay"); // ví dụ SP015
            try {
                int number = Integer.parseInt(lastMaSP.replaceAll("[^0-9]", "")); // lấy số
                number++;
                newMaSP = String.format("SP%03d", number); // SP016
            } catch (NumberFormatException e) {
                newMaSP = "SP001";
            }
        }

        connect.Close();
        return newMaSP;
    }
}
