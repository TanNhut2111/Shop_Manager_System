package ShoesManager.DAO;

import ShoesManager.DTO.PhieuNhapDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;


public class PhieuNhapDAO {
    MyConnectUnit connect;
     
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<PhieuNhapDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("tblphieunhap", condition, orderBy);
        ArrayList<PhieuNhapDTO> pns = new ArrayList<>();
        while ( result.next() ) {
            PhieuNhapDTO pn = new PhieuNhapDTO();
            pn.setStrMaPN(result.getString("mapn"));
            pn.setStrMaNCC(result.getString("mancc"));
            pn.setStrMaNV(result.getString("manv"));
            pn.setStrNgayNhap(result.getString("ngaynhap"));
            pn.setTongTien(result.getDouble("tongtien"));
            pn.setDeleted(result.getInt("deleted"));
            pns.add(pn);
        }
        connect.Close();
        return pns;
    }
    
    public ArrayList<PhieuNhapDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<PhieuNhapDTO> docDB() throws Exception {
        return docDB("deleted = 0");
    }
    
    public Boolean them(PhieuNhapDTO pn) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("mancc", pn.getStrMaNCC());
        insertValues.put("manv", pn.getStrMaNV());
        insertValues.put("mapn", pn.getStrMaPN());
        insertValues.put("ngaynhap", pn.getStrNgayNhap());
        insertValues.put("tongtien", pn.getTongTien());
        
        Boolean check = connect.Insert("tblphieunhap", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(PhieuNhapDTO nv) throws Exception {
        connect = new MyConnectUnit();
        String condition = " mapn = '" + nv.getStrMaPN() + "'";

        // cập nhật deleted = 1 chứ không xóa thật
        HashMap<String, Object> updateValues = new HashMap<>();
        updateValues.put("deleted", 1);

        Boolean check = connect.Update("tblphieunhap", updateValues, condition);

        connect.Close();
        return check;
    }

    public Boolean sua(PhieuNhapDTO pn) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("mancc", pn.getStrMaNCC());
        insertValues.put("manv", pn.getStrMaNV());
        insertValues.put("ngaynhap", pn.getStrNgayNhap());
        insertValues.put("tongtien", pn.getTongTien());
        
        String condition = " mapn = '"+pn.getStrMaPN()+"'";
        
        Boolean check = connect.Update("tblphieunhap", insertValues, condition);
        
        connect.Close();
        return check;
    }

    public String getNewMaPN() throws Exception {
        connect = new MyConnectUnit();

        // Truy vấn mã phiếu nhập mới nhất
        String query = "SELECT TOP 1 mapn FROM tblphieunhap ORDER BY mapn DESC";
        ResultSet rs = connect.excuteQuery(query);

        String newMaPN = "PN001"; // Mặc định nếu chưa có dữ liệu

        if (rs.next()) {
            String lastMaPN = rs.getString("mapn"); // ví dụ PN015
            try {
                int number = Integer.parseInt(lastMaPN.replaceAll("[^0-9]", "")); // lấy 15
                number++;
                newMaPN = String.format("PN%03d", number); // PN016
            } catch (NumberFormatException e) {
                newMaPN = "PN001"; // fallback an toàn
            }
        }

        connect.Close();
        return newMaPN;
    }
}
