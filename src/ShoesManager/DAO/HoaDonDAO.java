package ShoesManager.DAO;
/**
 * Mã hdách hàng là hdóa chính
 */
import ShoesManager.DTO.HoaDonDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class HoaDonDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<HoaDonDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("tblhoadon", condition, orderBy);
        ArrayList<HoaDonDTO> hds = new ArrayList<>();
        while ( result.next() ) {
            HoaDonDTO hd = new HoaDonDTO();
            hd.setStrMaHD(result.getString("mahd"));
            hd.setStrMaKH(result.getString("makh"));
            hd.setStrMaKM(result.getString("makm"));
            hd.setStrMaNV(result.getString("manv"));
            hd.setStrNgayBan(result.getString("ngayban"));
            hd.setTongTien(result.getDouble("Tongtien"));
            hds.add(hd);
        }
        connect.Close();
        return hds;
    }
    
    public ArrayList<HoaDonDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<HoaDonDTO> docDB() throws Exception {
        return docDB("deleted = 0");
    }
    
    public Boolean them(HoaDonDTO hd) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("mahd", hd.getStrMaHD());
        insertValues.put("makh", hd.getStrMaKH());
        insertValues.put("makm", hd.getStrMaKM());
        insertValues.put("manv", hd.getStrMaNV());
        insertValues.put("ngayban", hd.getStrNgayBan());
        insertValues.put("tongtien", hd.getTongTien());
        
        Boolean check = connect.Insert("tblhoadon", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(HoaDonDTO hd) throws Exception {
        connect = new MyConnectUnit();
        HashMap<String, Object> updateValues = new HashMap<>(); 
        updateValues.put("deleted", 1);

        String condition = " mahd = '"+hd.getStrMaHD()+"'";
        Boolean check = connect.Update("tblhoadon", updateValues, condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(HoaDonDTO hd) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("makh", hd.getStrMaKH());
        insertValues.put("makm", hd.getStrMaKM());
        insertValues.put("manv", hd.getStrMaNV());
        insertValues.put("ngayban", hd.getStrNgayBan());
        insertValues.put("tongtien", hd.getTongTien());
        
        String condition = " MaHD = '"+hd.getStrMaHD()+"'";
        
        Boolean check = connect.Update("tblhoadon", insertValues, condition);
        
        connect.Close();
        return check;
    }

    public String getNewMaHD() throws Exception {
        connect = new MyConnectUnit();

        // Lấy mã hóa đơn mới nhất
        String query = "SELECT TOP 1 mahd FROM tblhoadon ORDER BY mahd DESC";
        ResultSet rs = connect.excuteQuery(query);

        String newMaHD = "HD001"; // Mặc định nếu chưa có dữ liệu

        if (rs.next()) {
            String lastMaHD = rs.getString("mahd"); // ví dụ: HD015
            try {
                int number = Integer.parseInt(lastMaHD.replaceAll("[^0-9]", "")); // lấy 15
                number++;
                newMaHD = String.format("HD%03d", number); // HD016
            } catch (NumberFormatException e) {
                newMaHD = "HD001";
            }
        }
        connect.Close();
        return newMaHD;
    }

    public boolean kiemTraTonKho(String maSP, int soLuongMua) {
        try {
            MyConnectUnit connect = new MyConnectUnit();

            // Gọi đúng hàm Select (chỉ truyền bảng + điều kiện)
            ResultSet rs = connect.Select("tblsanpham", "magiay = '" + maSP + "'");

            if (rs.next()) {
                int soLuongTon = rs.getInt("soluong");

                // Hết hàng
                if (soLuongTon <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Sản phẩm " + maSP + " đã hết hàng, không thể lập hóa đơn!",
                        "Lỗi tồn kho",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }

                // Không đủ hàng
                if (soLuongMua > soLuongTon) {
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Số lượng mua (" + soLuongMua + ") vượt quá tồn kho (" + soLuongTon + ").",
                        "Lỗi tồn kho",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }

                return true; // đủ hàng
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Không tìm thấy sản phẩm có mã " + maSP,
                    "Lỗi dữ liệu",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Lỗi khi kiểm tra tồn kho!",
                "Lỗi hệ thống",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }
}
