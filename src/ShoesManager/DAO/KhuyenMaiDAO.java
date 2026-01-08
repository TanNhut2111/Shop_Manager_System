package ShoesManager.DAO;

import ShoesManager.DTO.KhuyenMaiDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class KhuyenMaiDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<KhuyenMaiDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("tblkhuyenmai", condition, orderBy);
        ArrayList<KhuyenMaiDTO> kms = new ArrayList<>();
        while ( result.next() ) {
            KhuyenMaiDTO km = new KhuyenMaiDTO();
            km.setStrDieuKien(result.getString("DieuKien"));
            km.setStrLoaiChuongTrinh(result.getString("LoaiChuongtrinh"));
            km.setStrMaKM(result.getString("MaKM"));
            km.setStrNgayBatDau(result.getString("Ngaybatdau"));
            km.setStrNgayKetThuc(result.getString("ngayketthuc"));
            km.setStrTenChuongTrinh(result.getString("tenchuongtrinh"));
            km.setDeleted(result.getInt("deleted"));           
            kms.add(km);
        }
        connect.Close();
        return kms;
    }
    
    public ArrayList<KhuyenMaiDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<KhuyenMaiDTO> docDB() throws Exception {
        return docDB("deleted = 0");
    }
    
    public Boolean them(KhuyenMaiDTO km) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("dieukien", km.getStrDieuKien());
        insertValues.put("loaichuongtrinh", km.getStrLoaiChuongTrinh());
        insertValues.put("makm", km.getStrMaKM());
        insertValues.put("ngaybatdau", km.getStrNgayBatDau());
        insertValues.put("ngayketthuc", km.getStrNgayKetThuc());
        insertValues.put("tenchuongtrinh", km.getStrTenChuongTrinh());
        
        Boolean check = connect.Insert("tblkhuyenmai", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(KhuyenMaiDTO km) throws Exception {
        connect = new MyConnectUnit();
        String condition = " makm = '"+km.getStrMaKM()+"'";

        // cập nhật deleted = 1 chứ không xóa thật
        HashMap<String, Object> updateValues = new HashMap<>();
        updateValues.put("deleted", 1);
        
        Boolean check = connect.Update("tblkhuyenmai", updateValues, condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(KhuyenMaiDTO km) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("dieukien", km.getStrDieuKien());
        insertValues.put("loaichuongtrinh", km.getStrLoaiChuongTrinh());
        insertValues.put("ngaybatdau", km.getStrNgayBatDau());
        insertValues.put("ngayketthuc", km.getStrNgayKetThuc());
        insertValues.put("tenchuongtrinh", km.getStrTenChuongTrinh());
        
        String condition = " MaKM = '"+km.getStrMaKM()+"'";
        
        Boolean check = connect.Update("tblkhuyenmai", insertValues, condition);
        
        connect.Close();
        return check;
    }

    public String getNewMaKM() throws Exception {
        connect = new MyConnectUnit();

        // Truy vấn mã phiếu nhập mới nhất
        String query = "SELECT TOP 1 makm FROM tblkhuyenmai ORDER BY makm DESC";
        ResultSet rs = connect.excuteQuery(query);

        String newMaKM = "KM001"; // Mặc định nếu chưa có dữ liệu

        if (rs.next()) {
            String lastMaKM = rs.getString("makm"); // ví dụ km15
            try {
                int number = Integer.parseInt(lastMaKM.replaceAll("[^0-9]", "")); // lấy 15
                number++;
                newMaKM = String.format("KM%03d", number); // PN016
            } catch (NumberFormatException e) {
                newMaKM = "KM001"; // fallback an toàn
            }
        }

        connect.Close();
        return newMaKM;
    }
}
