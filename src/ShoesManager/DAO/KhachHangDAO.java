package ShoesManager.DAO;
/**
 * Mã khách hàng là khóa chính
 */
import ShoesManager.DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class KhachHangDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<KhachHangDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("tblkhachhang", condition, orderBy);
        ArrayList<KhachHangDTO> khs = new ArrayList<>();
        while ( result.next() ) {
            KhachHangDTO kh = new KhachHangDTO();
            kh.setStrMaKH(result.getString("MaKH"));
            kh.setStrHo(result.getString("Ho"));
            kh.setStrTen(result.getString("Ten"));
            kh.setStrDiaChi(result.getString("DiaChi"));
            kh.setStrEmail(result.getString("Email"));
            kh.setStrGioiTinh(result.getString("GioiTinh"));
            kh.setStrLoai(result.getString("Loai"));
            kh.setiTongChiTieu(result.getDouble("TongChiTieu"));
            khs.add(kh);
        }
        connect.Close();
        return khs;
    }
    
    public ArrayList<KhachHangDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<KhachHangDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    public Boolean them(KhachHangDTO kh) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("diachi", kh.getStrDiaChi());
        insertValues.put("Email", kh.getStrEmail());
        insertValues.put("GioiTinh", kh.getStrGioiTinh());
        insertValues.put("ho", kh.getStrHo());
        insertValues.put("loai", kh.getStrLoai());
        insertValues.put("makh", kh.getStrMaKH());
        insertValues.put("ten", kh.getStrTen());
        insertValues.put("tongchitieu", kh.getiTongChiTieu());
        
        Boolean check = connect.Insert("tblkhachhang", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(KhachHangDTO kh) throws Exception {
        connect = new MyConnectUnit();
        String condition = " makh = '"+kh.getStrMaKH()+"'";
        
        Boolean check = connect.Delete("tblkhachhang", condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(KhachHangDTO kh) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("diachi", kh.getStrDiaChi());
        insertValues.put("Email", kh.getStrEmail());
        insertValues.put("GioiTinh", kh.getStrGioiTinh());
        insertValues.put("ho", kh.getStrHo());
        insertValues.put("loai", kh.getStrLoai());
        insertValues.put("ten", kh.getStrTen());
        insertValues.put("tongchitieu", kh.getiTongChiTieu());
        
        String condition = " MaKH = '"+kh.getStrMaKH()+"'";
        
        Boolean check = connect.Update("tblkhachhang", insertValues, condition);
        
        connect.Close();
        return check;
    }

    public String getNewMaKH() throws Exception {
        connect = new MyConnectUnit();

        // Truy vấn mã phiếu nhập mới nhất
        String query = "SELECT TOP 1 makh FROM tblkhachhang ORDER BY makh DESC";
        ResultSet rs = connect.excuteQuery(query);

        String newMaKH = "KH001"; // Mặc định nếu chưa có dữ liệu

        if (rs.next()) {
            String lastMaKH = rs.getString("makh"); // ví dụ PN015
            try {
                int number = Integer.parseInt(lastMaKH.replaceAll("[^0-9]", "")); // lấy 15
                number++;
                newMaKH = String.format("KH%03d", number); // PN016
            } catch (NumberFormatException e) {
                newMaKH = "KH001"; // fallback an toàn
            }
        }

        connect.Close();
        return newMaKH;
    }
}
