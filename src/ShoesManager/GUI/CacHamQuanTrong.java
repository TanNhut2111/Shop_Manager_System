package ShoesManager.GUI;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import ShoesManager.DAO.MyConnectUnit;

public class CacHamQuanTrong {

    private static final String[] STR_MONTHS = {
        "Jan","Feb","Mar","Apr","May","Jun",
        "Jul","Aug","Sep","Oct","Nov","Dec"
    };
    
    /**
     * Kiểm tra tham số truyền vào có phải là <h2>số</h2> hay không
     * @return true nếu là kiểu dữ liệu số Double
     */
    public boolean isNumeric(String var) {
        try {  
            Double.parseDouble(var);  
            return true;
        
        // return false when "var" can't be converted to double    
        // NumberFormatException will return null
        } catch(NumberFormatException e){  
            return false;  
        }
    }

    public boolean kiemTraDuLieuSo(String var) {
        try {
            int soLuong = Integer.parseInt(var);

            if (soLuong <= 0) {
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Số lượng phải lớn hơn 0!",
                    "Lỗi nhập liệu",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }

            return true; // Hợp lệ nếu là số nguyên dương
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Sai số lượng! Vui lòng nhập số nguyên hợp lệ.",
                "Lỗi nhập liệu",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    public boolean kiemTraGiaHopLe(int giaNhap, int giaBan) {
        if (giaNhap <= 0) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Giá nhập phải lớn hơn 0",
                "Lỗi nhập liệu",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (giaNhap >= giaBan) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Giá nhập không được lớn hơn hoặc bằng giá bán",
                "Cảnh báo",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean kiemTraTiLeKhuyenMai(String var) {
        try {
            double tiLe = Double.parseDouble(var);

            // Chỉ hợp lệ nếu 0 < tiLe <= 1
            if (tiLe <= 0 || tiLe > 1) {
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Tỉ lệ khuyến mãi phải lớn hơn 0 và nhỏ hơn hoặc bằng 1!\n(Ví dụ: 0.1 = 10%)",
                    "Lỗi nhập liệu",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }

            return true; // hợp lệ
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Sai định dạng! Vui lòng nhập số thực hợp lệ (ví dụ: 0.1, 0.25, 0.5).",
                "Lỗi nhập liệu",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }
    
    /**
     * Kiểm tra xem ngày bắt đầu có lớn hơn ngày kết thúc hay không
     * @param Date1 ngày bắt đầu
     * @param Date2 ngày kết thúc
     * @return true nếu Date1 lớn hơn Date2
     */
    public boolean kiemTraDate(String currentDateStr, String inputDateStr) {
        try {
            // Chuẩn hóa dữ liệu (ví dụ: "2025-11-01" hoặc "01 Nov 2025")
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime input;

            if (inputDateStr.contains("-")) { // dạng yyyy-MM-dd
                String[] parts = inputDateStr.split("-");
                input = LocalDateTime.of(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    0, 0
                );
            } else { // dạng 01 Nov 2025
                String[] sInput = inputDateStr.split("\\ ");
                int day = Integer.parseInt(sInput[0]);
                int year = Integer.parseInt(sInput[sInput.length - 1]);
                int month = 0;
                for (int i = 0; i < STR_MONTHS.length; i++) {
                    if (STR_MONTHS[i].equalsIgnoreCase(sInput[1])) month = i + 1;
                }
                input = LocalDateTime.of(year, month, day, 0, 0);
            }

            return !input.isBefore(now.toLocalDate().atStartOfDay());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * So sánh 2 ngày bất kỳ (dùng cho thống kê)
     * @return true nếu date1 <= date2
     */
    public boolean kiemTraDateTK(String date1Str, String date2Str) {
        try {
            LocalDateTime date1, date2;

            // Xử lý date1
            if (date1Str.contains("-")) { // dạng yyyy-MM-dd
                String[] parts = date1Str.split("-");
                date1 = LocalDateTime.of(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    0, 0
                );
            } else { // dạng 01 Nov 2025
                String[] s = date1Str.split("\\ ");
                int day = Integer.parseInt(s[0]);
                int year = Integer.parseInt(s[s.length - 1]);
                int month = 0;
                for (int i = 0; i < STR_MONTHS.length; i++) {
                    if (STR_MONTHS[i].equalsIgnoreCase(s[1])) month = i + 1;
                }
                date1 = LocalDateTime.of(year, month, day, 0, 0);
            }

            // Xử lý date2
            if (date2Str.contains("-")) { // dạng yyyy-MM-dd
                String[] parts = date2Str.split("-");
                date2 = LocalDateTime.of(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    0, 0
                );
            } else { // dạng 01 Nov 2025
                String[] s = date2Str.split("\\ ");
                int day = Integer.parseInt(s[0]);
                int year = Integer.parseInt(s[s.length - 1]);
                int month = 0;
                for (int i = 0; i < STR_MONTHS.length; i++) {
                    if (STR_MONTHS[i].equalsIgnoreCase(s[1])) month = i + 1;
                }
                date2 = LocalDateTime.of(year, month, day, 0, 0);
            }

            // Trả về true nếu date1 <= date2
            return !date1.isAfter(date2);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        public boolean kiemtrathangTK(String monthyear,String date1){
            String[] s1 = monthyear.split("\\ ");
            String[] s2 = date1.split("\\ ");

            if ( Integer.parseInt(s1[1]) != Integer.parseInt(s2[2])) {
                return false;
            }
            int iThang1 = 0, iThang2 = 0;
            for (int i=0 ; i < STR_MONTHS.length ; i++) {
                if (STR_MONTHS[i].equals(s1[0]))
                    iThang1 = i;
                if (STR_MONTHS[i].equals(s2[1]))
                    iThang2 = i;
            }
            
            if ( iThang1 != iThang2 ) {
                System.out.println("Lỗi tháng");
                return false;
            }
            return true;
        }

        public boolean kiemtranamTK(String year,String date1){        
            try {
            int inputYear = 0;

            // Trường hợp dạng "2025-11-01"
            if (date1.contains("-")) {
                String[] parts = date1.split("-");
                inputYear = Integer.parseInt(parts[0]);
            } 
            // Trường hợp dạng "01 Nov 2025"
            else if (date1.contains(" ")) {
                String[] s2 = date1.split("\\ ");
                inputYear = Integer.parseInt(s2[s2.length - 1]);
            }

            return Integer.parseInt(year) == inputYear;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isKhuyenMaiDangApDung(String startDateStr, String endDateStr) {
        try {
            String[] sStart = startDateStr.split("\\ ");
            String[] sEnd = endDateStr.split("\\ ");

            int dayStart = Integer.parseInt(sStart[0]);
            int yearStart = Integer.parseInt(sStart[2]);
            int dayEnd = Integer.parseInt(sEnd[0]);
            int yearEnd = Integer.parseInt(sEnd[2]);

            int monthStart = 0, monthEnd = 0;
            for (int i = 0; i < STR_MONTHS.length; i++) {
                if (STR_MONTHS[i].equalsIgnoreCase(sStart[1])) monthStart = i + 1;
                if (STR_MONTHS[i].equalsIgnoreCase(sEnd[1])) monthEnd = i + 1;
            }

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime start = LocalDateTime.of(yearStart, monthStart, dayStart, 0, 0);
            LocalDateTime end = LocalDateTime.of(yearEnd, monthEnd, dayEnd, 23, 59);

            // chỉ đúng nếu hiện tại nằm giữa ngày bắt đầu và ngày kết thúc
            return (now.isAfter(start) || now.isEqual(start)) && now.isBefore(end);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra sản phẩm có đang được sử dụng trong các bảng khác không
    public boolean isSanPhamDangSuDung(String maSP) {
        try {
            MyConnectUnit connect = new MyConnectUnit();

            ResultSet rs1 = connect.Select("tblchitietkm", "magiay = '" + maSP + "'");
            if (rs1.next()) return true;

            ResultSet rs2 = connect.Select("tblchitietpn", "magiay = '" + maSP + "'");
            if (rs2.next()) return true;

            ResultSet rs3 = connect.Select("tblchitiethd", "magiay = '" + maSP + "'");
            if (rs3.next()) return true;

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true; // Lỗi thì coi như đang dùng để tránh xóa nhầm
        }
    }
}
    

