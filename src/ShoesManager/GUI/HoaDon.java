package ShoesManager.GUI;

import ShoesManager.BUS.ChiTietHDBUS;
import ShoesManager.BUS.ChiTietKMBUS;
import ShoesManager.BUS.HoaDonBUS;
import ShoesManager.BUS.KhachHangBUS;
import ShoesManager.BUS.KhuyenMaiBUS;
import ShoesManager.BUS.NhanVienBUS;
import ShoesManager.BUS.SanPhamBUS;
import ShoesManager.DTO.ChiTietHDDTO;
import ShoesManager.DTO.ChiTietKMDTO;
import ShoesManager.DTO.HoaDonDTO;
import ShoesManager.DTO.KhachHangDTO;
import ShoesManager.DTO.KhuyenMaiDTO;
import ShoesManager.DTO.NhanVienDTO;
import ShoesManager.DTO.SanPhamDTO;
import ShoesManager.DAO.HoaDonDAO;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import jxl.write.Label;




public class HoaDon extends JFrame implements MouseListener{

    private javax.swing.JButton btnChiTiet_Sua;
    private javax.swing.JButton btnChiTiet_TaoMoi;
    private javax.swing.JButton btnChiTiet_Them;
    private javax.swing.JButton btnChiTiet_Xoa;
    private javax.swing.JButton btnThongTin_Sua;
    private javax.swing.JButton btnThongTin_Them;
    private javax.swing.JButton btnThongTin_Trong;
    private javax.swing.JButton btnThongTin_Xoa;
    private javax.swing.JButton btnTimKiem_HuyTimKiem;
    private javax.swing.JButton btnTimKiem_TimKiem;
    private javax.swing.JButton btnTimKiem_Trong1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.toedter.calendar.JDateChooser calendarThongTin;
    private javax.swing.JComboBox<String> cbbThongTin_MaKH;
    private javax.swing.JComboBox<String> cbbThongTin_MaKM;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblChiTiet_3cham;
    private javax.swing.JLabel lblChiTiet_3cham1;
    private javax.swing.JLabel lblChiTiet_3cham2;
    private javax.swing.JLabel lblChiTiet_ChiTietHoaDon;
    private javax.swing.JLabel lblChiTiet_Gia;
    private javax.swing.JLabel lblChiTiet_MaHD;
    private javax.swing.JLabel lblChiTiet_MaSP;
    private javax.swing.JLabel lblChiTiet_MaSP1;
    private javax.swing.JLabel lblChiTiet_SL;
    private javax.swing.JLabel lblChiTiet_SoLuong2;
    private javax.swing.JLabel lblChiTiet_Tatca;
    private javax.swing.JLabel lblChiTiet_Xuat;
    private javax.swing.JLabel lblMuiTen;
    private javax.swing.JLabel lblMuiTen1;
    private javax.swing.JLabel lblThongTin_MaHD;
    private javax.swing.JLabel lblThongTin_MaHD1;
    private javax.swing.JLabel lblThongTin_MaKH;
    private javax.swing.JLabel lblThongTin_MaKH1;
    private javax.swing.JLabel lblThongTin_MaKM;
    private javax.swing.JLabel lblThongTin_MaKM1;
    private javax.swing.JLabel lblThongTin_MaNV;
    private javax.swing.JLabel lblThongTin_MaNV1;
    private javax.swing.JLabel lblThongTin_NgayBan;
    private javax.swing.JLabel lblThongTin_ThongTin;
    private javax.swing.JLabel lblThongTin_TimKiem;
    private javax.swing.JLabel lblThongTin_TongTien;
    private javax.swing.JLabel lblThongTin_TongTien1;
    private javax.swing.JPanel pCenter;
    private javax.swing.JPanel pChiTiet;
    private javax.swing.JPanel pChiTietSanPham;
    private javax.swing.JPanel pChiTietTimKiem;
    private javax.swing.JPanel pThongTin;
    private javax.swing.JPanel pThongTin_ThongTin;
    private javax.swing.JPanel pThongTin_TimKiem;
    private javax.swing.JPanel pTop;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenu menuTaiKhoan;
    private javax.swing.JMenuItem menuTaiKhoan_DangXuat;
    private javax.swing.JMenuItem menuTaiKhoan_ThongTin;
    private javax.swing.JMenu menuTroVe;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTable tblThongTin1;
    private javax.swing.JTextField tfChiTiet_Gia;
    private javax.swing.JTextField tfChiTiet_MaHD;
    private javax.swing.JTextField tfChiTiet_MaSP;
    private javax.swing.JTextField tfChiTiet_MaSP2;
    private javax.swing.JTextField tfChiTiet_MaSP3;
    private javax.swing.JTextField tfChiTiet_SL;
    private javax.swing.JTextField tfChiTiet_SL2;
    private javax.swing.JTextField tfChiTiet_SL3;
    private javax.swing.JTextField tfThongTin_MaHD;
    private javax.swing.JTextField tfThongTin_MaNV;
    private javax.swing.JTextField tfThongTin_TenKH;
    private javax.swing.JTextField tfThongTin_TongTien;
    private javax.swing.JTextField tfTimKiem_MaHD1;
    private javax.swing.JTextField tfTimKiem_MaKH1;
    private javax.swing.JTextField tfTimKiem_MaKM1;
    private javax.swing.JTextField tfTimKiem_MaNV1;
    private javax.swing.JTextField tfTimKiem_TongTien1;

    private javax.swing.JButton btnDocFile, btnGhiFile, btnDocDatabase;
    private Object[][] data_HD = { 
            { "MaHD", "MaNV", "MaKH","MaKM", "NgayBan", "TongTien" }
        };
    private Object[][] data_CTHD = { 
            { "MaHD", "MaGiay", "SoLuong","GiaBan", "MaKM", "TiLeKM" }
        };

    private CacHamQuanTrong cachamquantrong;
    private JLabel[] listLblTop;
    private String[] strArr_Top;
    private HoaDonBUS list_HD;
    private ChiTietHDBUS list_ChiTietHD;
    private ChiTietKMBUS list_ChiTietKM;
    private SanPhamBUS list_SanPham;
    private KhachHangBUS list_KH;
    private KhuyenMaiBUS list_KM;
    private DefaultTableModel model, modelChiTiet;
    private double TongTien;
    private HoaDonDTO hoadon;
    private static final String[] strMonths = {"Jan","Feb","Mar","Apr","May","Jun",
                                  "Jul","Aug","Sep","Oct","Nov","Dec"};
    // Ngày của hệ thống
    private String date1 = LocalDateTime.now().getDayOfMonth() + " " + LocalDateTime.now().getMonth() + " " + LocalDateTime.now().getYear();
    
    // writeFileExcel
    private void writeFileExcel_HD() {
        // Kiểm tra người dùng có chọn file hay không
        if (Memory.filechoose == null || Memory.filechoose.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Bạn chưa chọn file để ghi!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn ghi dữ liệu hóa đơn ra file Excel không?",
                "Xác nhận ghi file", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(new File(Memory.filechoose));

            // create sheet
            WritableSheet sheet1 = workbook.createSheet("HoaDon", 0);
            WritableSheet sheet2 = workbook.createSheet("ChiTietHoaDon", 1);

            int rowBegin = 0;
            int colBegin = 0;

            // Ghi tiêu đề hóa đơn
            for (int row = rowBegin, i = 0; row < data_HD.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data_HD[0].length + colBegin; col++, j++) {
                    sheet1.addCell(new Label(col, row, (String) data_HD[i][j]));
                }
            }

            // Ghi tiêu đề chi tiết hóa đơn
            for (int row = rowBegin, i = 0; row < data_CTHD.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data_CTHD[0].length + colBegin; col++, j++) {
                    sheet2.addCell(new Label(col, row, (String) data_CTHD[i][j]));
                }
            }

            rowBegin = 1;

            // Ghi dữ liệu hóa đơn
            for (int row = rowBegin, i = 0; row < list_HD.getNumbHoaDon() + rowBegin; row++, i++) {
                HoaDonDTO hd = list_HD.getInfor(i);
                sheet1.addCell(new Label(0, row, hd.getStrMaHD()));
                sheet1.addCell(new Label(1, row, hd.getStrMaNV()));
                sheet1.addCell(new Label(2, row, hd.getStrMaKH()));
                sheet1.addCell(new Label(3, row, hd.getStrMaKM()));
                sheet1.addCell(new Label(4, row, hd.getStrNgayBan()));
                sheet1.addCell(new Label(5, row, String.valueOf(hd.getTongTien())));
            }

            // Ghi dữ liệu chi tiết hóa đơn (thêm 2 cột khuyến mãi)
            for (int row = rowBegin, i = 0; row < list_ChiTietHD.getNumbChiTietHD() + rowBegin; row++, i++) {
                ChiTietHDDTO cthd = list_ChiTietHD.getInfor(i);

                sheet2.addCell(new Label(0, row, cthd.getStrMaHD()));
                sheet2.addCell(new Label(1, row, cthd.getStrMaGiay()));
                sheet2.addCell(new Label(2, row, String.valueOf(cthd.getiSoLuong())));
                sheet2.addCell(new Label(3, row, String.valueOf(cthd.getiGiaBan())));

                // Thêm cột Mã KM và Tỉ lệ KM
                String maKM = list_HD.getMaKM(cthd.getStrMaHD());
                ChiTietKMDTO km = list_ChiTietKM.getInfor(cthd.getStrMaGiay(), maKM);
                String tiLeKM = (km != null && !km.getStrMaGiay().equals("null"))
                        ? String.valueOf(km.getTiLeKM() * 100) + "%"
                        : "0%";
                sheet2.addCell(new Label(4, row, maKM));
                sheet2.addCell(new Label(5, row, tiLeKM));
            }

            workbook.write();
            workbook.close();

            JOptionPane.showMessageDialog(null,
                    "Ghi file Excel thành công!",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException | WriteException e) {
            JOptionPane.showMessageDialog(null,
                    "Lỗi khi ghi file Excel: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // readFileExcel
    private void readFileExcel_HD() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn đọc dữ liệu hóa đơn từ file Excel không?",
                "Xác nhận đọc file", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        int duplicateCount = 0;
        int errorCount = 0;

        try {
            // Khởi tạo nếu null
            if (list_HD == null) list_HD = new HoaDonBUS();
            if (list_ChiTietHD == null) list_ChiTietHD = new ChiTietHDBUS();

            // Đọc dữ liệu hiện có trong DB để kiểm tra trùng
            list_HD.docDB();
            list_ChiTietHD.docDB();

            Workbook workbook = Workbook.getWorkbook(new File(Memory.filechoose));
            Sheet sheet = workbook.getSheet(0);
            Sheet sheet1 = workbook.getSheet(1);

            // Dùng HashSet để kiểm tra trùng trong file Excel
            Set<String> maHDTrongFile = new HashSet<>();

            // Đọc hóa đơn
            for (int row = 1; row < sheet.getRows(); row++) {
                String[] arr = new String[sheet.getColumns()];
                for (int col = 0; col < sheet.getColumns(); col++) {
                    arr[col] = sheet.getCell(col, row).getContents().trim();
                }

                String maHD = arr[0];
                if (maHD.isEmpty()) continue;

                boolean isDuplicate = false;

                // Kiểm tra trùng trong DB
                for (int i = 0; i < list_HD.getNumbHoaDon(); i++) {
                    if (list_HD.getInfor(i).getStrMaHD().equalsIgnoreCase(maHD)) {
                        isDuplicate = true;
                        break;
                    }
                }

                // Kiểm tra trùng trong chính file Excel
                if (maHDTrongFile.contains(maHD)) {
                    isDuplicate = true;
                } else {
                    maHDTrongFile.add(maHD);
                }

                if (isDuplicate) {
                    duplicateCount++;
                    JOptionPane.showMessageDialog(null,
                            "Mã hóa đơn '" + maHD + "' bị trùng. Bỏ qua dòng " + (row + 1),
                            "Lỗi trùng mã", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                try {
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setStrMaHD(maHD);
                    hd.setStrMaNV(arr[1]);
                    hd.setStrMaKH(arr[2]);
                    hd.setStrMaKM(arr[3]);
                    hd.setStrNgayBan(arr[4]);
                    hd.setTongTien(Double.parseDouble(arr[5]));

                    list_HD.them(hd);
                } catch (Exception ex) {
                    errorCount++;
                    JOptionPane.showMessageDialog(null,
                            "Lỗi khi thêm hóa đơn dòng " + (row + 1) + ": " + ex.getMessage(),
                            "Lỗi thêm dữ liệu", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Đọc chi tiết hóa đơn
            for (int row = 1; row < sheet1.getRows(); row++) {
                String[] arr = new String[sheet1.getColumns()];
                for (int col = 0; col < sheet1.getColumns(); col++) {
                    arr[col] = sheet1.getCell(col, row).getContents().trim();
                }

                try {
                    ChiTietHDDTO cthd = new ChiTietHDDTO();
                    cthd.setStrMaHD(arr[0]);
                    cthd.setStrMaGiay(arr[1]);
                    cthd.setiSoLuong(Integer.parseInt(arr[2]));
                    cthd.setiGiaBan(Integer.parseInt(arr[3]));

                    list_ChiTietHD.them(cthd);
                } catch (Exception ex) {
                    // Có thể bỏ qua hoặc đếm lỗi nếu muốn
                    errorCount++;
                }
            }

            workbook.close();

            // Làm mới danh sách từ DB sau khi thêm
            list_HD.docDB();
            list_ChiTietHD.docDB();

            // Thông báo kết quả
            if (duplicateCount == 0 && errorCount == 0) {
                JOptionPane.showMessageDialog(null,
                        "Đọc file Excel và cập nhật dữ liệu thành công!",
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Đọc file hoàn tất nhưng có " + duplicateCount + " dòng trùng mã và "
                                + errorCount + " dòng lỗi!",
                        "Kết quả đọc file", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Không thể đọc file Excel: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public HoaDon() throws Exception {
        this.setUndecorated(true);

        init();
        initComponents();
        actionVisiblePanel();
        pThongTin_ThongTin.setVisible(true);
        // các hàm xử lý sau khi vào
        kiemTraCapBac();
        anHienCacNut(false);
        
        // hiển thị các dữ liệu table
        actionButtondisplay();
        actionButtondisplayChiTiet();
        
        // cac su kien khac
        actionTFChiTiet();
        
        //chinh sua cac cbb
        nhapLuongKH();
        nhapLuongKM();
        
        tfThongTin_MaNV.setText(Memory.maNV);
        
        tblThongTin.setAutoCreateRowSorter(true);
        tblThongTin1.setAutoCreateRowSorter(true);

        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

         try {
            list_ChiTietHD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void init() throws Exception {
        
        list_HD = new HoaDonBUS();
        list_ChiTietHD = new ChiTietHDBUS();
        list_ChiTietKM = new ChiTietKMBUS();
        list_SanPham = new SanPhamBUS();
        list_KH = new KhachHangBUS();
        list_KM = new KhuyenMaiBUS();
        hoadon = new HoaDonDTO();
        cachamquantrong = new CacHamQuanTrong();
               
        strArr_Top = getMenuItemsByRole();
    }
    
    private String[] getMenuItemsByRole() {
        int iKey = Memory.iCapBac;
        switch (iKey) {
            case 1 -> {
                return new String[]{"Dashboard", "Hóa đơn", "Khuyến mãi", "Nhập hàng", "Hàng hóa", "Thống kê"};
            }
            case 2 -> {
                return new String[]{"Dashboard", "Thống kê"};
            }
            case 3 -> {
                return new String[]{"Dashboard", "Hàng hóa"};
            }
            case 4 -> {
                return new String[]{"Dashboard", "Nhập hàng"};
            }
            case 5 -> {
                return new String[]{"Dashboard", "Khuyến mãi"};
            }
            case 6 -> {
                return new String[]{"Dashboard", "Hóa đơn"};
            }
            default -> {
                return new String[]{"Dashboard"};
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();
        buttonGroup3 = new ButtonGroup();
        pCenter = new JPanel();
        pThongTin = new JPanel();
        lblThongTin_ThongTin = new JLabel();
        lblThongTin_TimKiem = new JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        pThongTin_ThongTin = new javax.swing.JPanel();
        lblThongTin_MaHD = new javax.swing.JLabel();
        lblThongTin_MaKH = new javax.swing.JLabel();
        lblThongTin_MaNV = new javax.swing.JLabel();
        lblThongTin_MaKM = new javax.swing.JLabel();
        lblThongTin_NgayBan = new javax.swing.JLabel();
        lblThongTin_TongTien = new javax.swing.JLabel();
        btnThongTin_Them = new javax.swing.JButton();
        btnThongTin_Xoa = new javax.swing.JButton();
        tfThongTin_MaHD = new javax.swing.JTextField();
        tfThongTin_MaNV = new javax.swing.JTextField();
        tfThongTin_TongTien = new javax.swing.JTextField();
        calendarThongTin = new com.toedter.calendar.JDateChooser();
        btnThongTin_Sua = new JButton();
        btnThongTin_Trong = new JButton();
        cbbThongTin_MaKH = new javax.swing.JComboBox<>();
        cbbThongTin_MaKM = new javax.swing.JComboBox<>();
        tfThongTin_TenKH = new javax.swing.JTextField();
        pThongTin_TimKiem = new javax.swing.JPanel();
        lblThongTin_MaKH1 = new javax.swing.JLabel();
        lblThongTin_TongTien1 = new javax.swing.JLabel();
        lblThongTin_MaHD1 = new javax.swing.JLabel();
        lblThongTin_MaNV1 = new javax.swing.JLabel();
        lblThongTin_MaKM1 = new javax.swing.JLabel();
        btnTimKiem_TimKiem = new javax.swing.JButton();
        btnTimKiem_HuyTimKiem = new javax.swing.JButton();
        tfTimKiem_MaHD1 = new javax.swing.JTextField();
        tfTimKiem_MaKM1 = new javax.swing.JTextField();
        tfTimKiem_MaKH1 = new javax.swing.JTextField();
        tfTimKiem_MaNV1 = new javax.swing.JTextField();
        tfTimKiem_TongTien1 = new javax.swing.JTextField();
        btnTimKiem_Trong1 = new javax.swing.JButton();
        // rdbtnThongTin_Tang = new javax.swing.JRadioButton();
        // rdbtnThongTin_Giam = new javax.swing.JRadioButton();
        pChiTiet = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThongTin1 = new javax.swing.JTable();
        lblChiTiet_ChiTietHoaDon = new javax.swing.JLabel();
        pChiTietSanPham = new javax.swing.JPanel();
        lblChiTiet_SL = new javax.swing.JLabel();
        btnChiTiet_Xoa = new javax.swing.JButton();
        lblChiTiet_Gia = new javax.swing.JLabel();
        btnChiTiet_Sua = new javax.swing.JButton();
        tfChiTiet_MaSP = new javax.swing.JTextField();
        tfChiTiet_MaHD = new javax.swing.JTextField();
        tfChiTiet_SL = new javax.swing.JTextField();
        lblChiTiet_MaSP = new javax.swing.JLabel();
        tfChiTiet_Gia = new javax.swing.JTextField();
        lblChiTiet_MaHD = new javax.swing.JLabel();
        btnChiTiet_Them = new javax.swing.JButton();
        lblChiTiet_3cham = new javax.swing.JLabel();
        btnChiTiet_TaoMoi = new javax.swing.JButton();
        pChiTietTimKiem = new javax.swing.JPanel();
        lblChiTiet_SoLuong2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        tfChiTiet_MaSP2 = new javax.swing.JTextField();
        lblChiTiet_MaSP1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        lblMuiTen = new javax.swing.JLabel();
        lblMuiTen1 = new javax.swing.JLabel();
        lblChiTiet_3cham1 = new javax.swing.JLabel();
        lblChiTiet_3cham2 = new javax.swing.JLabel();
        tfChiTiet_SL2 = new javax.swing.JTextField();
        tfChiTiet_SL3 = new javax.swing.JTextField();
        tfChiTiet_MaSP3 = new javax.swing.JTextField();
        lblChiTiet_Tatca = new javax.swing.JLabel();
        lblChiTiet_Xuat = new javax.swing.JLabel();
        pTop = createPanel_LblLeft(strArr_Top);

        btnDocFile = new JButton();
        btnGhiFile = new JButton();
        btnDocDatabase = new JButton();

        // Menubar
        menubar = new JMenuBar();
        menuTaiKhoan = new JMenu("Tài khoản");
        menuTaiKhoan_ThongTin = new JMenuItem("Thông tin");
        menuTaiKhoan_DangXuat = new JMenuItem("Đăng xuất");
        menuTroVe = new JMenu("Trở về");

        // Thêm item vào menu
        menuTaiKhoan.add(menuTaiKhoan_ThongTin);
        menuTaiKhoan.add(menuTaiKhoan_DangXuat);
        menubar.add(menuTaiKhoan);
        menubar.add(menuTroVe);
        
        // Create header panel with logo
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(173, 216, 230)); // Light blue header
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        // Logo and brand name
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        logoPanel.setBackground(new Color(173, 216, 230));
        
        // Logo icon (shoe icon)
        JLabel lblLogoIcon = new JLabel("👟");
        lblLogoIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        
        // Brand name
        JPanel brandPanel = new JPanel();
        brandPanel.setLayout(new BoxLayout(brandPanel, BoxLayout.Y_AXIS));
        brandPanel.setBackground(new Color(173, 216, 230));
        
        JLabel lblBrand = new JLabel("ShoeHub Pro");
        lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBrand.setForeground(new Color(44, 62, 80));
        
        JLabel lblSubtitle = new JLabel("Quản lý bán giày");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(44, 62, 80));
        
        brandPanel.add(lblBrand);
        brandPanel.add(lblSubtitle);
        
        logoPanel.add(lblLogoIcon);
        logoPanel.add(brandPanel);
        
        headerPanel.add(logoPanel, BorderLayout.CENTER);
        
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(pCenter, BorderLayout.CENTER);
        pTop.setPreferredSize(new Dimension(220, 0)); 
        getContentPane().add(pTop, BorderLayout.WEST); 
        pTop.setBackground(new Color(44, 62, 80));
        
        // Cấu hình JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Hệ Thống Quản Lý Bán Giày - Dashboard");
        setSize(1430, 720);
        setMinimumSize(new Dimension(1280, 720));
        setResizable(true);
        setUndecorated(false);
        setLocationRelativeTo(null);

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        Color xanhNen = new Color(245, 247, 250); // Modern light gray background
        getContentPane().setBackground(xanhNen);
        // pCenter.setBackground(xanhNen);
        pThongTin.setBackground(xanhNen);
        pChiTiet.setBackground(xanhNen);
        // pTop.setBackground(xanhNen);
        pThongTin_ThongTin.setBackground(xanhNen);
        pThongTin_TimKiem.setBackground(xanhNen);
        pChiTietSanPham.setBackground(xanhNen);
        pChiTietTimKiem.setBackground(xanhNen);

        pCenter.setPreferredSize(new Dimension(1280, 600));  
        pThongTin.setPreferredSize(new Dimension(340, 580));
        pThongTin.setVerifyInputWhenFocusTarget(false);

        lblThongTin_ThongTin.setIcon(new ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/210_70/Tìm kiếm.png"))); // NOI18N
        lblThongTin_ThongTin.setMaximumSize(new Dimension(210, 131));
        lblThongTin_ThongTin.setMinimumSize(new Dimension(210, 131));
        lblThongTin_ThongTin.setName("pThongTin_ThongTin"); // NOI18N
        lblThongTin_ThongTin.setPreferredSize(new Dimension(210, 131));
        lblThongTin_ThongTin.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                lblThongTin_ThongTinMouseReleased(evt);
            }
        });

        lblThongTin_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/210_70/Thông tin.png"))); // NOI18N
        lblThongTin_TimKiem.setName("pThongTin_TiemKiem"); // NOI18N
        lblThongTin_TimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblThongTin_TimKiemMouseReleased(evt);
            }
        });

        tblThongTin.setAutoCreateRowSorter(true);
        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Mã KH", "Mã NV", "Mã KM", "Ngày Bán", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        tblThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThongTin);
        if (tblThongTin.getColumnModel().getColumnCount() > 0) {
            tblThongTin.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblThongTin.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblThongTin.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblThongTin.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblThongTin.getColumnModel().getColumn(4).setPreferredWidth(90);
            tblThongTin.getColumnModel().getColumn(5).setPreferredWidth(90);
        }

        pThongTin_ThongTin.setPreferredSize(new java.awt.Dimension(405, 280));

        lblThongTin_MaHD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaHD.setText("Mã hóa đơn");

        lblThongTin_MaKH.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaKH.setText("Mã khách hàng");

        lblThongTin_MaNV.setFont(new Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaNV.setText("Mã nhân viên");

        lblThongTin_MaKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaKM.setText("Mã khuyến mãi");

        lblThongTin_NgayBan.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_NgayBan.setText("Ngày bán");

        lblThongTin_TongTien.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_TongTien.setText("Tổng tiền");

        btnThongTin_Them.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThongTin_Them.setText("Thêm");
        btnThongTin_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThongTin_ThemActionPerformed(evt);
            }
        });

        btnThongTin_Xoa.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThongTin_Xoa.setText("Xóa");
        btnThongTin_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTin_XoaActionPerformed(evt);
            }
        });

        tfThongTin_MaHD.setEditable(false);
        tfThongTin_MaHD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfThongTin_MaHD.setPreferredSize(new java.awt.Dimension(170, 28));
        tfThongTin_MaHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfThongTin_MaHDMouseClicked(evt);
            }
        });
        tfThongTin_MaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfThongTin_MaHDActionPerformed(evt);
            }
        });

        tfThongTin_MaNV.setEditable(false);
        tfThongTin_MaNV.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfThongTin_MaNV.setPreferredSize(new java.awt.Dimension(170, 28));

        tfThongTin_TongTien.setEditable(false);
        tfThongTin_TongTien.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfThongTin_TongTien.setPreferredSize(new java.awt.Dimension(170, 28));

        calendarThongTin.setDate(new java.util.Date());
        calendarThongTin.setDateFormatString("dd / MM / yyyy");
        calendarThongTin.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        btnThongTin_Sua.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThongTin_Sua.setText("Sửa");
        btnThongTin_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTin_SuaActionPerformed(evt);
            }
        });

        btnThongTin_Trong.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThongTin_Trong.setText("Tạo Mới");
        btnThongTin_Trong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongTin_TrongMouseClicked(evt);
            }
        });
        btnThongTin_Trong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTin_TrongActionPerformed(evt);
            }
        });

        cbbThongTin_MaKH.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbbThongTin_MaKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KH1", "KH2" }));
        cbbThongTin_MaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThongTin_MaKHActionPerformed(evt);
            }
        });

        cbbThongTin_MaKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbbThongTin_MaKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KM001", "KM002" }));

        tfThongTin_TenKH.setEditable(false);
        tfThongTin_TenKH.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfThongTin_TenKH.setPreferredSize(new java.awt.Dimension(170, 28));

        javax.swing.GroupLayout pThongTin_ThongTinLayout = new javax.swing.GroupLayout(pThongTin_ThongTin);
        pThongTin_ThongTin.setLayout(pThongTin_ThongTinLayout);
        pThongTin_ThongTinLayout.setHorizontalGroup(
            pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                        .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThongTin_NgayBan)
                            .addComponent(lblThongTin_MaHD)
                            .addComponent(lblThongTin_MaKH)
                            .addComponent(lblThongTin_MaNV)
                            .addComponent(lblThongTin_MaKM)
                            .addComponent(lblThongTin_TongTien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                                .addComponent(cbbThongTin_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfThongTin_TenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                            .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfThongTin_MaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfThongTin_TongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(calendarThongTin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbThongTin_MaKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfThongTin_MaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                        .addComponent(btnThongTin_Them)
                        .addGap(18, 18, 18)
                        .addComponent(btnThongTin_Xoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnThongTin_Sua)
                        .addGap(18, 18, 18)
                        .addComponent(btnThongTin_Trong)))
                .addContainerGap())
        );
        pThongTin_ThongTinLayout.setVerticalGroup(
            pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaHD)
                    .addComponent(tfThongTin_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaKH)
                    .addComponent(cbbThongTin_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfThongTin_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaNV)
                    .addComponent(tfThongTin_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaKM)
                    .addComponent(cbbThongTin_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblThongTin_NgayBan)
                    .addComponent(calendarThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_TongTien)
                    .addComponent(tfThongTin_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThongTin_Them)
                    .addComponent(btnThongTin_Xoa)
                    .addComponent(btnThongTin_Sua)
                    .addComponent(btnThongTin_Trong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pThongTin_TimKiem.setPreferredSize(new java.awt.Dimension(405, 280));

        lblThongTin_MaKH1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaKH1.setText("Mã khách hàng");

        lblThongTin_TongTien1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_TongTien1.setText("Tổng tiền");

        lblThongTin_MaHD1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaHD1.setText("Mã hóa đơn");

        lblThongTin_MaNV1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaNV1.setText("Mã nhân viên");

        lblThongTin_MaKM1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaKM1.setText("Mã khuyến mãi");

        btnTimKiem_TimKiem.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTimKiem_TimKiem.setText("Tìm kiếm");
        btnTimKiem_TimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiem_TimKiemMouseClicked(evt);
            }
        });
        btnTimKiem_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem_TimKiemActionPerformed(evt);
            }
        });

        btnTimKiem_HuyTimKiem.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTimKiem_HuyTimKiem.setText("Hủy tìm kiếm");
        btnTimKiem_HuyTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiem_HuyTimKiemMouseClicked(evt);
            }
        });
        btnTimKiem_HuyTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem_HuyTimKiemActionPerformed(evt);
            }
        });

        tfTimKiem_MaHD1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfTimKiem_MaHD1.setPreferredSize(new java.awt.Dimension(170, 28));

        tfTimKiem_MaKM1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfTimKiem_MaKM1.setPreferredSize(new java.awt.Dimension(170, 28));

        tfTimKiem_MaKH1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfTimKiem_MaKH1.setPreferredSize(new java.awt.Dimension(170, 28));
        tfTimKiem_MaKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimKiem_MaKH1ActionPerformed(evt);
            }
        });

        tfTimKiem_MaNV1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfTimKiem_MaNV1.setPreferredSize(new java.awt.Dimension(170, 28));

        tfTimKiem_TongTien1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfTimKiem_TongTien1.setPreferredSize(new java.awt.Dimension(170, 28));

        btnTimKiem_Trong1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTimKiem_Trong1.setText("Trống");
        btnTimKiem_Trong1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiem_Trong1MouseClicked(evt);
            }
        });
        btnTimKiem_Trong1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem_Trong1ActionPerformed(evt);
            }
        });

        // buttonGroup1.add(rdbtnThongTin_Tang);
        // rdbtnThongTin_Tang.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        // rdbtnThongTin_Tang.setText("Tăng dần");
        // rdbtnThongTin_Tang.setBackground(new java.awt.Color(131,189,246)); // Xanh dương nhẹ

        // buttonGroup1.add(rdbtnThongTin_Giam);
        // rdbtnThongTin_Giam.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        // rdbtnThongTin_Giam.setText("Giảm dần");
        // rdbtnThongTin_Giam.setBackground(new java.awt.Color(131,189,246)); // Xanh dương nhẹ


        javax.swing.GroupLayout pThongTin_TimKiemLayout = new javax.swing.GroupLayout(pThongTin_TimKiem);
        pThongTin_TimKiem.setLayout(pThongTin_TimKiemLayout);
        pThongTin_TimKiemLayout.setHorizontalGroup(
            pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThongTin_TimKiemLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThongTin_MaKH1)
                    .addComponent(lblThongTin_MaHD1)
                    .addComponent(lblThongTin_MaNV1)
                    .addComponent(lblThongTin_MaKM1)
                    .addComponent(lblThongTin_TongTien1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfTimKiem_MaHD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfTimKiem_MaKH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfTimKiem_MaNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfTimKiem_MaKM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfTimKiem_TongTien1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnTimKiem_TimKiem)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem_HuyTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiem_Trong1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pThongTin_TimKiemLayout.setVerticalGroup(
            pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaHD1)
                    .addComponent(tfTimKiem_MaHD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaKH1)
                    .addComponent(tfTimKiem_MaKH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaNV1)
                    .addComponent(tfTimKiem_MaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaKM1)
                    .addComponent(tfTimKiem_MaKM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_TongTien1)
                    .addComponent(tfTimKiem_TongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem_TimKiem)
                    .addComponent(btnTimKiem_HuyTimKiem)
                    .addComponent(btnTimKiem_Trong1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        javax.swing.GroupLayout pThongTinLayout = new javax.swing.GroupLayout(pThongTin);
        pThongTin.setLayout(pThongTinLayout);
        pThongTinLayout.setHorizontalGroup(
            pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addComponent(lblThongTin_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongTin_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addComponent(pThongTin_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pThongTin_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        pThongTinLayout.setVerticalGroup(
            pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThongTin_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThongTin_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pThongTin_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pThongTin_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
        );

        pChiTiet.setPreferredSize(new java.awt.Dimension(880, 580));

        tblThongTin1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã HĐ", "Số Lượng", "Giá Bán", "Mã Khuyến Mãi", "Tỉ Lệ KM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
       
        tblThongTin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTin1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblThongTin1);
        if (tblThongTin1.getColumnModel().getColumnCount() > 0) {
            tblThongTin1.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblThongTin1.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblThongTin1.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblThongTin1.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblThongTin1.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblThongTin1.getColumnModel().getColumn(5).setPreferredWidth(60);
        }

        lblChiTiet_ChiTietHoaDon.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_ChiTietHoaDon.setText("Chi Tiết Hóa Đơn");


        lblChiTiet_SL.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_SL.setText("Số Lượng");

        btnChiTiet_Xoa.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnChiTiet_Xoa.setText("Xóa");
        btnChiTiet_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTiet_XoaActionPerformed(evt);
            }
        });

        lblChiTiet_Gia.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_Gia.setText("Giá Bán");

        btnChiTiet_Sua.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnChiTiet_Sua.setText("Sửa");
        btnChiTiet_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTiet_SuaActionPerformed(evt);
            }
        });

        tfChiTiet_MaSP.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfChiTiet_MaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfChiTiet_MaSPMouseClicked(evt);
            }
        });

        tfChiTiet_MaHD.setEditable(false);
        tfChiTiet_MaHD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        tfChiTiet_SL.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfChiTiet_SL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfChiTiet_SLActionPerformed(evt);
            }
        });

        lblChiTiet_MaSP.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_MaSP.setText("Mã Sản Phẩm");

        tfChiTiet_Gia.setEditable(false);
        tfChiTiet_Gia.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblChiTiet_MaHD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_MaHD.setText("Mã Hóa Đơn");

        btnChiTiet_Them.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnChiTiet_Them.setText("Thêm");
        btnChiTiet_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTiet_ThemActionPerformed(evt);
            }
        });

        lblChiTiet_3cham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/40_20/3 chấm đen.png"))); // NOI18N
        lblChiTiet_3cham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChiTiet_3chamMouseClicked(evt);
            }
        });

        btnChiTiet_TaoMoi.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnChiTiet_TaoMoi.setText("Tạo mới");
        btnChiTiet_TaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTiet_TaoMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pChiTietSanPhamLayout = new javax.swing.GroupLayout(pChiTietSanPham);
        pChiTietSanPham.setLayout(pChiTietSanPhamLayout);
        pChiTietSanPhamLayout.setHorizontalGroup(
            pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblChiTiet_MaSP)
                            .addComponent(lblChiTiet_MaHD)
                            .addComponent(lblChiTiet_SL)
                            .addComponent(lblChiTiet_Gia))
                        .addGap(38, 38, 38)
                        .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfChiTiet_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                                .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfChiTiet_MaSP)
                                    .addComponent(tfChiTiet_Gia)
                                    .addComponent(tfChiTiet_SL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(lblChiTiet_3cham))))
                    .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnChiTiet_Them)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChiTiet_Xoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChiTiet_Sua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChiTiet_TaoMoi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pChiTietSanPhamLayout.setVerticalGroup(
            pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChiTiet_MaSP)
                        .addComponent(tfChiTiet_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblChiTiet_3cham, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChiTiet_MaHD)
                    .addComponent(tfChiTiet_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChiTiet_SL)
                    .addComponent(tfChiTiet_SL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChiTiet_Gia)
                    .addComponent(tfChiTiet_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChiTiet_Them)
                    .addComponent(btnChiTiet_Xoa)
                    .addComponent(btnChiTiet_Sua)
                    .addComponent(btnChiTiet_TaoMoi))
                .addContainerGap())
        );

        pChiTietTimKiem.setPreferredSize(new java.awt.Dimension(380, 238));

        lblChiTiet_SoLuong2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_SoLuong2.setText("Số Lượng");

        jButton4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton4.setText("Hủy Tìm Kiếm");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton5.setText("Trống");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        tfChiTiet_MaSP2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblChiTiet_MaSP1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_MaSP1.setText("Mã Sản Phẩm");

        jButton6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton6.setText("Tìm Kiếm");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        lblMuiTen.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblMuiTen.setText("->");

        lblMuiTen1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblMuiTen1.setText("->");

        lblChiTiet_3cham1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/40_20/3 chấm đen.png"))); // NOI18N
        lblChiTiet_3cham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChiTiet_3cham1MouseClicked(evt);
            }
        });

        lblChiTiet_3cham2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/40_20/3 chấm đen.png"))); // NOI18N
        lblChiTiet_3cham2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChiTiet_3cham2MouseClicked(evt);
            }
        });

        tfChiTiet_SL2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        tfChiTiet_SL3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        tfChiTiet_MaSP3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N


        GroupLayout pChiTietTimKiemLayout = new GroupLayout(pChiTietTimKiem);
        pChiTietTimKiem.setLayout(pChiTietTimKiemLayout);

        pChiTietTimKiemLayout.setHorizontalGroup(
            pChiTietTimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, pChiTietTimKiemLayout.createSequentialGroup()
                            .addComponent(tfChiTiet_MaSP2, 103, 103, 103)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblChiTiet_3cham1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblMuiTen)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfChiTiet_MaSP3, 103, 103, 103)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblChiTiet_3cham2)
                            .addGap(171, 171, 171))
                        .addGroup(GroupLayout.Alignment.TRAILING, pChiTietTimKiemLayout.createSequentialGroup()
                            .addComponent(lblChiTiet_SoLuong2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfChiTiet_SL2, 103, 103, 103)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblMuiTen1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfChiTiet_SL3, 103, 103, 103)
                            .addGap(171, 171, 171))
                        .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                            .addComponent(jButton6)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton5)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lblChiTiet_MaSP1)))
        );

        pChiTietTimKiemLayout.setVerticalGroup(
            pChiTietTimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                    .addGap(25)
                    .addComponent(lblChiTiet_MaSP1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(pChiTietTimKiemLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(tfChiTiet_MaSP2)
                            .addComponent(lblMuiTen)
                            .addComponent(tfChiTiet_MaSP3))
                        .addComponent(lblChiTiet_3cham1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblChiTiet_3cham2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                    .addGap(40)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChiTiet_SoLuong2)
                        .addComponent(lblMuiTen1)
                        .addComponent(tfChiTiet_SL2)
                        .addComponent(tfChiTiet_SL3))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6)
                        .addComponent(jButton4)
                        .addComponent(jButton5))
                    .addContainerGap())
        );
  
        lblChiTiet_Tatca.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_Tatca.setText("Tất cả");
        lblChiTiet_Tatca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChiTiet_TatcaMouseClicked(evt);
            }
        });

        // Thêm cấu hình cho 3 nút mới
        btnDocFile.setFont(new Font("Arial", 1, 18));
        btnDocFile.setText("Đọc File");
        btnDocFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDocFileActionPerformed(evt);
            }
        });
        btnGhiFile.setFont(new Font("Arial", 1, 18));
        btnGhiFile.setText("Ghi File");
        btnGhiFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGhiFileActionPerformed(evt);
            }
        });
        btnDocDatabase.setFont(new Font("Arial", 1, 18));
        btnDocDatabase.setText("Đọc từ Database");
        btnDocDatabase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDocDatabaseActionPerformed(evt);
            }
        });

        btnDocFile.setText("📂"); // icon đọc file
        btnDocFile.setToolTipText("Đọc File");

        btnGhiFile.setText("💾"); // icon ghi file
        btnGhiFile.setToolTipText("Ghi File");

        btnDocDatabase.setText("🗄️"); // icon database
        btnDocDatabase.setToolTipText("Đọc từ Database");

        // Có thể làm nhỏ lại cho đẹp:
        Font iconFont = new Font("Segoe UI Emoji", Font.PLAIN, 16);
        btnDocFile.setFont(iconFont);
        btnGhiFile.setFont(iconFont);
        btnDocDatabase.setFont(iconFont);

        lblChiTiet_Xuat.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_Xuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/50_50/icons8-print.png"))); // NOI18N
        lblChiTiet_Xuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChiTiet_XuatMouseClicked(evt);
            }
        });

        GroupLayout pChiTietLayout = new GroupLayout(pChiTiet);
pChiTiet.setLayout(pChiTietLayout);

pChiTietLayout.setHorizontalGroup(
    pChiTietLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pChiTietLayout.createSequentialGroup()
            .addGap(25)
            .addComponent(lblChiTiet_ChiTietHoaDon)
            .addGap(75)
            .addComponent(lblChiTiet_Tatca)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnDocFile)
            .addGap(5)
            .addComponent(btnGhiFile)
            .addGap(5)
            .addComponent(btnDocDatabase)
            .addGap(20)
            .addComponent(lblChiTiet_Xuat)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(pChiTietLayout.createSequentialGroup()
            .addComponent(pChiTietSanPham, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(pChiTietTimKiem, 387, 387, 387)
            .addGap(0, 19, Short.MAX_VALUE))
        .addComponent(jScrollPane3)
);

pChiTietLayout.setVerticalGroup(
    pChiTietLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pChiTietLayout.createSequentialGroup()
            .addGap(15)
            .addGroup(pChiTietLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblChiTiet_ChiTietHoaDon)
                .addComponent(lblChiTiet_Tatca)
                .addComponent(btnDocFile)
                .addComponent(btnGhiFile)
                .addComponent(btnDocDatabase)
                .addComponent(lblChiTiet_Xuat))
            .addGap(15)
            .addGroup(pChiTietLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addComponent(pChiTietSanPham, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pChiTietTimKiem, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
            .addGap(20)
            .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
);


        javax.swing.GroupLayout pCenterLayout = new javax.swing.GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
            pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCenterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pChiTiet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pCenterLayout.setVerticalGroup(
            pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCenterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addComponent(pChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                .addContainerGap())
        );
    
        // Menu Bar
        menuTaiKhoan.setText("Tài khoản");
        menuTaiKhoan_ThongTin.setIcon(new ImageIcon(getClass().getResource("/ShoesManager/images/user.png"))); 
        menuTaiKhoan_ThongTin.setText("Thông tin");
        menuTaiKhoan_ThongTin.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                menuTaiKhoan_ThongTinMouseReleased(evt);
            }
        });
        menuTaiKhoan.add(menuTaiKhoan_ThongTin);
        menuTaiKhoan_DangXuat.setIcon(new ImageIcon(getClass().getResource("/ShoesManager/images/logout.png"))); 
        menuTaiKhoan_DangXuat.setText("Đăng xuất");
        menuTaiKhoan_DangXuat.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                menuTaiKhoan_DangXuatMouseReleased(evt);
            }
        });
        menuTroVe.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                try {
                    new Home().setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override public void menuDeselected(MenuEvent e) {}
            @Override public void menuCanceled(MenuEvent e) {}
        });

        setJMenuBar(menubar);
        pack();
        setLocationRelativeTo(null);
    }
    

    // Hiển thị thông tin user
    private void menuTaiKhoan_ThongTinMouseReleased(MouseEvent evt) {
        UserInfor ui = new UserInfor();
        ui.setVisible(true);
    }
    // Đăng xuất
    private void menuTaiKhoan_DangXuatMouseReleased(MouseEvent evt) {
        Login lg;
        try {
            lg = new Login();
            lg.setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actionVisiblePanel(){
        pThongTin_ThongTin.setVisible(false);
        pThongTin_TimKiem.setVisible(false);
    }

    // Thêm action listeners cho 3 nút mới
    private void btnDocFileActionPerformed(ActionEvent evt) {
        ChonFile cf = new ChonFile(this, true);
        cf.setVisible(true);
        // Kiểm tra người dùng có chọn file hay không
        if (Memory.filechoose == null || Memory.filechoose.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Bạn chưa chọn file để đọc!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] s = Memory.filechoose.split("\\.");
        if (s.length > 1 && s[1].equals("xls")) {
            readFileExcel_HD();
            actionButtondisplay();
            actionButtondisplayChiTiet();
        } else {
            JOptionPane.showMessageDialog(this, "File không đúng, hãy chọn file Excel (.xls)", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnGhiFileActionPerformed(ActionEvent evt) {
        ChonFile cf = new ChonFile(this, true);
        cf.setVisible(true);
        writeFileExcel_HD();
    }
    private void btnDocDatabaseActionPerformed(ActionEvent evt) {
        try {
            list_HD.docDB();
            list_ChiTietHD.docDB();
            list_SanPham.docDB();
            actionButtondisplay();
            actionButtondisplayChiTiet();
            JOptionPane.showMessageDialog(this, "Đã đọc dữ liệu từ database thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc từ database: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void lblThongTin_ThongTinMouseReleased(java.awt.event.MouseEvent evt) {
        actionVisiblePanel();
        pThongTin_ThongTin.setVisible(true);
    }

    private void lblThongTin_TimKiemMouseReleased(java.awt.event.MouseEvent evt) {
        actionVisiblePanel();
        pThongTin_TimKiem.setVisible(true);
    }

    private void btnThongTin_TrongActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnTimKiem_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnTimKiem_HuyTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnTimKiem_Trong1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void tfTimKiem_MaKH1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    
    public void nhapLuongKH() throws Exception{
        KhachHangBUS bus =new KhachHangBUS();
        ArrayList<KhachHangDTO> arr =bus.getList_KH();
        String[] s = new String[bus.getNumbKH()];
        int i = 0;
        for (KhachHangDTO dto : arr) {
            s[i] = dto.getStrMaKH();
            i++;
            System.out.println(dto.getStrMaKH());
        }
        cbbThongTin_MaKH.setModel(new DefaultComboBoxModel<>(s));
    }
   
    // nhập liệu mã khuyến mãi
    public void nhapLuongKM() throws Exception{
        KhuyenMaiBUS bus = new KhuyenMaiBUS();
        ArrayList<KhuyenMaiDTO> arr = bus.getList_KM();
        CacHamQuanTrong helper = new CacHamQuanTrong();

        String[] s = new String[bus.getNumbKM()];
        int i = 0;
        for (KhuyenMaiDTO dto : arr)
            if (helper.isKhuyenMaiDangApDung(dto.getStrNgayBatDau(), dto.getStrNgayKetThuc()))
                s[i++] = dto.getStrMaKM();

        String[] valid = new String[i];
        System.arraycopy(s, 0, valid, 0, i);

        cbbThongTin_MaKM.setModel(new DefaultComboBoxModel<>(valid));
    }
    
    private void anHienCacNut(boolean bl) {
        // hiển thị nút btn
        btnThongTin_Them.setEnabled(bl);
        btnThongTin_Xoa.setEnabled(bl);
        btnThongTin_Sua.setEnabled(bl);
        
        btnChiTiet_Sua.setEnabled(bl);
        btnChiTiet_TaoMoi.setEnabled(bl);
        btnChiTiet_Them.setEnabled(bl);
        btnChiTiet_Xoa.setEnabled(bl);
        
        // không cho chỉnh sửa
        tfChiTiet_MaSP.setEditable(bl);
        tfChiTiet_SL.setEditable(bl);
        tfChiTiet_Gia.setEditable(bl);
    }
    
    // Hiển thị thông tin hóa đơn
    private void tblThongTinMouseClicked(java.awt.event.MouseEvent evt) {
        int  i=tblThongTin.getSelectedRow();
        
        tfThongTin_MaHD.setText(tblThongTin.getModel().getValueAt(i, 0).toString());
        cbbThongTin_MaKH.setSelectedItem(tblThongTin.getModel().getValueAt(i, 1));
        tfThongTin_MaNV.setText(tblThongTin.getModel().getValueAt(i, 2).toString());         
        cbbThongTin_MaKM.setSelectedItem(tblThongTin.getModel().getValueAt(i, 3));
        Date date = new Date((String) tblThongTin.getModel().getValueAt(i, 4));
        calendarThongTin.setDate(date);
        tfThongTin_TongTien.setText(tblThongTin.getModel().getValueAt(i, 5).toString());
        // lấy thông tin
        TongTien = Double.parseDouble(tfThongTin_TongTien.getText());
        
        // hiển thị tên nv và kh
        HoaDonDTO hd = new HoaDonDTO();
        hd = list_HD.getInfor(tfThongTin_MaHD.getText());
        KhachHangDTO kh = list_KH.getInfor(tblThongTin.getModel().getValueAt(i, 1).toString());
        tfThongTin_TenKH.setText(kh.getStrHo() + " " + kh.getStrTen());
        
        // HoaDonDTO hd = new HoaDonDTO();
        hoadon.setStrMaHD( tfThongTin_MaHD.getText());
        hoadon.setStrMaKH((String) cbbThongTin_MaKH.getSelectedItem());
        hoadon.setStrMaKM((String) cbbThongTin_MaKM.getSelectedItem());
        hoadon.setStrMaNV(tfThongTin_MaNV.getText());
        hoadon.setStrNgayBan((String) tblThongTin.getModel().getValueAt(i, 4));
        hoadon.setTongTien(Double.parseDouble(tfThongTin_TongTien.getText()));
        System.out.println(hoadon.toString());
        
        // hiển thị bên chi tiết
        actionButtondisplayChiTiet(tfThongTin_MaHD.getText());
        
        anHienCacNut(false);
        
        if (TongTien == 0) {
            btnThongTin_Xoa.setEnabled(true);
         }
        
        System.out.println(date1 + tblThongTin.getModel().getValueAt(i, 4).toString() );
        if (cachamquantrong.kiemTraDate(date1, tblThongTin.getModel().getValueAt(i, 4).toString())) {
            btnThongTin_Sua.setEnabled(true);
            btnChiTiet_TaoMoi.setEnabled(true);
        }
        
        tfChiTiet_MaSP.setText("");
        tfChiTiet_MaHD.setText("");
        tfChiTiet_SL.setText("");
        tfChiTiet_Gia.setText("");
    }

    // Tạo mới hóa đơn
    private void btnThongTin_TrongMouseClicked(java.awt.event.MouseEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn tạo mới hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) { 
            try {
                String newMaHD = list_HD.getNewMaHDFromDB();
                tfThongTin_MaHD.setText(newMaHD);

                list_HD.docDB();

                btnThongTin_Sua.setEnabled(true);
                btnThongTin_Xoa.setEnabled(true);
                TongTien = 0;

                cbbThongTin_MaKH.setSelectedIndex(0);
                tfThongTin_MaNV.setText(Memory.maNV);
                cbbThongTin_MaKM.setSelectedIndex(0);
                tfThongTin_TongTien.setText("");

                tfChiTiet_MaSP.setText("");
                tfChiTiet_SL.setText("");
                tfChiTiet_Gia.setText("");
                tfThongTin_TenKH.setText("");
                tfChiTiet_MaHD.setText(tfThongTin_MaHD.getText());

                String s = LocalDateTime.now().getDayOfMonth() + " " + LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth();
                Date date = new Date(s);
                calendarThongTin.setDate(date);

                tfThongTin_MaHD.requestFocus();

                anHienCacNut(false);
                btnThongTin_Them.setEnabled(true);

                // xóa thông tin bên chi tiết
                actionButtondisplayChiTiet(tfThongTin_MaHD.getText());

                JOptionPane.showMessageDialog(null, "Tạo mới hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Tạo mới hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    // Thêm hóa đơn
    private void btnThongTin_ThemActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) { 
            try {
                list_HD.docDB();

                KhuyenMaiDTO km = list_KM.getInfor(cbbThongTin_MaKM.getSelectedItem().toString());

                String date = LocalDateTime.now().getDayOfMonth() + " " +
                        strMonths[LocalDateTime.now().getMonthValue() - 1] + " " +
                        LocalDateTime.now().getYear();

                date1 = date;

                if (cachamquantrong.kiemTraDate(km.getStrNgayBatDau(), date)
                        && cachamquantrong.kiemTraDate(date, km.getStrNgayKetThuc())) {
                    actionThemHoaDon();
                    JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Chương trình khuyến mãi không hợp lệ", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void actionThemHoaDon() {

        hoadon.setStrMaHD( tfThongTin_MaHD.getText());
        hoadon.setStrMaKH((String) cbbThongTin_MaKH.getSelectedItem());
        hoadon.setStrMaKM((String) cbbThongTin_MaKM.getSelectedItem());
        hoadon.setStrMaNV(tfThongTin_MaNV.getText());
        hoadon.setStrNgayBan(date1);
        
        if (tfThongTin_TongTien.getText().equals(""))
            tfThongTin_TongTien.setText("0");
        hoadon.setTongTien( Double.parseDouble(tfThongTin_TongTien.getText()));
        
        try {
            list_HD.them(hoadon);
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tfChiTiet_MaHD.setText(hoadon.getStrMaHD());
        
        tfChiTiet_MaSP.requestFocus();
        tfChiTiet_MaHD.setText(tfThongTin_MaHD.getText());
        
        tfThongTin_MaHD.setText("");
        tfThongTin_TongTien.setText("");
        cbbThongTin_MaKH.setSelectedIndex(0);
        cbbThongTin_MaKM.setSelectedIndex(0);
        tfThongTin_TenKH.setText("");
        
        actionButtondisplay();
        anHienCacNut(false);
        btnChiTiet_Them.setEnabled(true);
    }

    // Sửa hóa đơn
    private void btnThongTin_SuaActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) { 
            try {
                KhuyenMaiDTO km = list_KM.getInfor(cbbThongTin_MaKM.getSelectedItem().toString());

                String date = LocalDateTime.now().getDayOfMonth() + " " +
                        strMonths[LocalDateTime.now().getMonthValue() - 1] + " " +
                        LocalDateTime.now().getYear();

                if (cachamquantrong.kiemTraDate(km.getStrNgayBatDau(), date)
                        && cachamquantrong.kiemTraDate(date, km.getStrNgayKetThuc())) {
                    actionSuaHoaDon();
                    JOptionPane.showMessageDialog(null, "Sửa hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Chương trình khuyến mãi không hợp lệ", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Sửa hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void actionSuaHoaDon() {
        int iPos = 0;
        
        for (int i = 0 ; i < list_HD.getNumbHoaDon() ; i++) {
            HoaDonDTO hd = list_HD.getInfor(i);
            if (hd.getStrMaHD().equals(hoadon.getStrMaHD()))
                iPos = i;
        }
        
        String strMaKM_Cu = tblThongTin.getModel().getValueAt(iPos, 3).toString();
        
        hoadon.setStrMaHD( tfThongTin_MaHD.getText());
        hoadon.setStrMaKH((String) cbbThongTin_MaKH.getSelectedItem());
        hoadon.setStrMaKM((String) cbbThongTin_MaKM.getSelectedItem());
        hoadon.setStrMaNV(tfThongTin_MaNV.getText());
        hoadon.setStrNgayBan(date1);
        hoadon.setTongTien( Double.parseDouble(tfThongTin_TongTien.getText()));
        
        try {
            list_HD.sua(hoadon);
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        actionButtondisplay();
        actionButtondisplayChiTiet(hoadon.getStrMaHD());
        
        // nếu không sửa đổi mã khuyến mãi
        if (!strMaKM_Cu.equals(hoadon.getStrMaKM()))
            // duyệt hết
            for (int i = 0 ; i < list_ChiTietHD.getNumbChiTietHD() ; i++) {
                ChiTietHDDTO cthd = list_ChiTietHD.getInfor(i);
                // nếu là chi tiết hoa đơn đó
                if (cthd.getStrMaHD().equals(hoadon.getStrMaHD())) {
                    System.out.println(cthd.toString());
                    System.out.println(strMaKM_Cu + " - " + hoadon.getStrMaKM());
                    actionSuaMaKM_TT_CT(i, strMaKM_Cu, hoadon.getStrMaKM(), cthd, hoadon);
                }
            }
        
        if (!tfThongTin_MaHD.getText().equals("")) {
            tfThongTin_MaHD.setText("");
            tfThongTin_TongTien.setText("");
            cbbThongTin_MaKH.setSelectedIndex(0);
            cbbThongTin_MaKM.setSelectedIndex(0);
        }
    }

    // Xóa hóa đơn
    private void btnThongTin_XoaActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                actionxoathongtin();
                JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void actionxoathongtin() {
        HoaDonDTO hd = new HoaDonDTO();
        hd.setStrMaHD( tfThongTin_MaHD.getText());
        
        try {
            list_HD.xoa(hd);
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        actionButtondisplay();
        
        tfThongTin_MaHD.setText("");
        tfThongTin_TongTien.setText("");
        cbbThongTin_MaKH.setSelectedIndex(0);
        cbbThongTin_MaKM.setSelectedIndex(0);
    }

    private void tfThongTin_MaHDMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("Click here PLae");
    }

    private void tfChiTiet_SLActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // Hiển thị tất cả chi tiết hóa đơn
    private void lblChiTiet_TatcaMouseClicked(java.awt.event.MouseEvent evt) {
        tfThongTin_MaHD.setText("");
        cbbThongTin_MaKH.setSelectedIndex(0);
        tfThongTin_MaNV.setText(Memory.maNV);         
        cbbThongTin_MaKM.setSelectedItem(0);
        tfThongTin_TongTien.setText("");
        
        
        String s = LocalDateTime.now().getDayOfMonth() + " " + LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth() ;
        System.out.println(s);
        
        Date date = new Date(s);
        calendarThongTin.setDate(date);
        
        actionButtondisplayChiTiet();
    }
    // Hiển thị thông tin chi tiết hóa đơn
    private void tblThongTin1MouseClicked(java.awt.event.MouseEvent evt) {
        int  i=tblThongTin1.getSelectedRow();
        
        tfChiTiet_MaSP.setText(tblThongTin1.getModel().getValueAt(i, 0).toString());
        tfChiTiet_MaHD.setText(tblThongTin1.getModel().getValueAt(i, 1).toString());
        tfChiTiet_SL.setText(tblThongTin1.getModel().getValueAt(i, 2).toString());
        tfChiTiet_Gia.setText(tblThongTin1.getModel().getValueAt(i, 3).toString());

        anHienCacNut(false);

        if ( cachamquantrong.kiemTraDate(date1, hoadon.getStrNgayBan())) {
            btnChiTiet_TaoMoi.setEnabled(true);
            btnChiTiet_Sua.setEnabled(true);
            btnChiTiet_Xoa.setEnabled(true);
           
            tfChiTiet_SL.setEditable(true);
        }
    }
    
    // Tạo mới chi tiết hóa đơn
    private void btnChiTiet_TaoMoiActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn tạo mới chi tiết hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                tfChiTiet_MaSP.setText("");
                tfChiTiet_MaHD.setText(hoadon.getStrMaHD());
                tfChiTiet_Gia.setText("");
                tfChiTiet_SL.setText("");

                anHienCacNut(false);
                btnChiTiet_Them.setEnabled(true);

                JOptionPane.showMessageDialog(null, "Đã tạo mới chi tiết hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Tạo mới thất bại: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Thêm chi tiết hóa đơn
    private void btnChiTiet_ThemActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm chi tiết hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                // Kiểm tra số lượng (đã bao gồm kiểm tra âm, =0, sai kiểu)
                if (!cachamquantrong.kiemTraDuLieuSo(tfChiTiet_SL.getText().trim())) {
                    return;
                }

                String maSP = tfChiTiet_MaSP.getText().trim();
                int soLuongMua = Integer.parseInt(tfChiTiet_SL.getText().trim());
                
                HoaDonDAO spDAO = new HoaDonDAO();
                if (!spDAO.kiemTraTonKho(maSP, soLuongMua)) {
                    return; // không đủ hàng, dừng lại
                }

                // Thực hiện thêm chi tiết
                boolean isSuccess = xulyThemChiTietHD();
                if (isSuccess) {
                    JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void actionTFChiTiet() {
        tfChiTiet_MaSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key){
                if ( key.getKeyCode() == KeyEvent.VK_ENTER ) {
                   if (!kiemtraMaSP()) {
                        JOptionPane.showMessageDialog(null, "SP không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                        tfChiTiet_MaSP.requestFocus();
                    }
                    else 
                        tfChiTiet_SL.requestFocus();
                }
            }
        });
        
        tfChiTiet_SL.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key){
                if ( key.getKeyCode() == KeyEvent.VK_ENTER ) {
                    btnChiTiet_ThemActionPerformed(null);
                    tfChiTiet_MaSP.requestFocus();
                }
            }
        });
    }

    // Sửa chi tiết hóa đơn
    private void btnChiTiet_SuaActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa chi tiết hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                int row = tblThongTin1.getSelectedRow();

                if (!cachamquantrong.kiemTraDuLieuSo(tfChiTiet_SL.getText().trim())) {
                    return;
                }
                // Số lượng cũ trong hóa đơn
                int soLuongCu = Integer.parseInt(tblThongTin1.getValueAt(row, 2).toString());

                String maSP = tfChiTiet_MaSP.getText().trim();
                int soLuongMoi = Integer.parseInt(tfChiTiet_SL.getText().trim());

                // Trả lại số lượng cũ về kho
                chinhSuaSoLuong_SP(maSP, soLuongCu, true);   // true = cộng vào kho

                // Kiểm tra tồn kho sau khi trả
                HoaDonDAO spDAO = new HoaDonDAO();
                if (!spDAO.kiemTraTonKho(maSP, soLuongMoi)) {
                    // Không đủ hàng → khôi phục lại tồn kho ban đầu rồi dừng
                    chinhSuaSoLuong_SP(maSP, soLuongCu * -1, true);   // trừ lại như mức cũ
                    return;
                }

                // Tạo DTO mới
                ChiTietHDDTO hd = new ChiTietHDDTO();
                hd.setStrMaGiay(maSP);
                hd.setStrMaHD(tfChiTiet_MaHD.getText());
                hd.setiGiaBan(Integer.parseInt(tfChiTiet_Gia.getText()));
                hd.setiSoLuong(soLuongMoi);

                // Lấy khuyến mãi
                ChiTietKMDTO km = list_ChiTietKM.getInfor(hd.getStrMaGiay(),
                                                        list_HD.getMaKM(hd.getStrMaHD()));

                // Cập nhật tổng tiền cũ -> mới
                tinhTongTien(soLuongCu,
                            Integer.parseInt(tblThongTin1.getValueAt(row, 3).toString()),
                            km.getTiLeKM(), false);

                list_ChiTietHD.sua(hd);
                actionButtondisplayChiTiet(hoadon.getStrMaHD());

                tinhTongTien(hd.getiGiaBan(), hd.getiSoLuong(),
                            km.getTiLeKM(), true);

                // Trừ số lượng mới ra khỏi kho
                chinhSuaSoLuong_SP(maSP, soLuongMoi * -1, true);

                // Lưu lại hóa đơn
                hoadon.setTongTien(TongTien);
                list_HD.sua(hoadon);
                actionButtondisplay();

                JOptionPane.showMessageDialog(null, "Sửa chi tiết hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Sửa chi tiết hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Xóa chi tiết hóa đơn
    private void btnChiTiet_XoaActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chi tiết hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                actionxoachitiet();
                JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actionxoachitiet() {
        ChiTietKMDTO km = new ChiTietKMDTO();

        ChiTietHDDTO hd = new ChiTietHDDTO();
        hd.setStrMaGiay(tfChiTiet_MaSP.getText());
        hd.setStrMaHD(tfChiTiet_MaHD.getText());
        hd.setiSoLuong(Integer.parseInt(tfChiTiet_SL.getText()));
        hd.setiGiaBan(Integer.parseInt(tfChiTiet_Gia.getText()));

        // Lấy thông tin khuyến mãi (nếu có)
        km = list_ChiTietKM.getInfor(
                hd.getStrMaGiay(),
                list_HD.getMaKM(hd.getStrMaHD())
        );

        double tiLeKM = (km != null) ? km.getTiLeKM() : 0;

        // Giảm tổng tiền hiện tại
        tinhTongTien(
                Integer.parseInt(tfChiTiet_SL.getText()),
                Integer.parseInt(tfChiTiet_Gia.getText()),
                tiLeKM,
                false
        );

        // 🔧 Khắc phục lỗi sai số double (5.684341886080802E-14)
        if (Math.abs(TongTien) < 0.0001) {
            TongTien = 0;
        } else {
            TongTien = Math.round(TongTien * 100.0) / 100.0; // làm tròn 2 chữ số
        }

        hoadon.setTongTien(TongTien);

        try {
            list_HD.sua(hoadon);
            actionButtondisplay();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            list_ChiTietHD.xoa(hd);
            list_ChiTietHD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        chinhSuaSoLuong_SP(hd.getStrMaGiay(), hd.getiSoLuong(), true);
        actionButtondisplayChiTiet(hoadon.getStrMaHD());

        // Xóa dữ liệu nhập
        tfChiTiet_Gia.setText("");
        tfChiTiet_MaSP.setText("");
        tfChiTiet_MaHD.setText("");
        tfChiTiet_SL.setText("");
    }   
    
    private void actionSuaMaKM_TT_CT(int i, String strMaKM_Cu, String strMaKM_Moi, ChiTietHDDTO cthd, HoaDonDTO hd) {
        ChiTietKMDTO km  = new ChiTietKMDTO();
        
        // xóa giá cũ
        km = list_ChiTietKM.getInfor(cthd.getStrMaGiay(), strMaKM_Cu);
        
        tinhTongTien(cthd.getiSoLuong(), cthd.getiGiaBan(), km.getTiLeKM(), false);
        
        // cập nhật lại giá mới
        if ( strMaKM_Moi.toString().equals("") ) {
            // nếu không có tahay đổi gì hết
            // khuyến mãi 0%
            // cộng thẳng giá tiền
            tinhTongTien(cthd.getiSoLuong(), cthd.getiGiaBan(), 0, true);
        }
        else {
            // nếu là mã khuyến mãi khác
            km = list_ChiTietKM.getInfor(cthd.getStrMaGiay(), strMaKM_Moi);
            
            tinhTongTien(cthd.getiSoLuong(), cthd.getiGiaBan(), km.getTiLeKM(), true);
        }

        System.out.println("SL va Gia " + cthd.getiSoLuong()+" - "+cthd.getiGiaBan());
        System.out.println("Ti le km: " +  km.getTiLeKM());
        System.out.println("Tong tien mới: " + TongTien);
        
        // cập nhật lại bên table thông tin
        hd.setTongTien(TongTien);
        
        try {
            list_HD.sua(hd);
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        actionButtondisplay();
    }
    
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        try {
            // Đọc dữ liệu từ DB
            list_ChiTietHD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        // --- Bắt đầu tìm kiếm đơn giản ---
        if (!tfChiTiet_MaSP2.getText().trim().equals("")) {
            // Tìm theo mã sản phẩm (không cần tăng/giảm)
            list_ChiTietHD.setList_HD(
                list_ChiTietHD.timKiem_MaSP(
                    tfChiTiet_MaSP2.getText().trim(),
                    tfChiTiet_MaSP3.getText().trim(),
                    0 // giữ 0 nếu hàm yêu cầu tham số thứ 3 (nhưng không ảnh hưởng)
                )
            );
        }

        if (!tfChiTiet_SL2.getText().trim().equals("")) {
            // Tìm theo số lượng (không cần tăng/giảm)
            list_ChiTietHD.setList_HD(
                list_ChiTietHD.timKiem_SoLuong(
                    Integer.parseInt(tfChiTiet_SL2.getText().trim()),
                    Integer.parseInt(tfChiTiet_SL3.getText().trim()),
                    0 // giữ 0 để tương thích
                )
            );
        }

        // Cập nhật lại bảng hiển thị
        if (tfThongTin_MaHD.getText().trim().equals("")) {
            actionButtondisplayChiTiet();
        } else {
            actionButtondisplayChiTiet(tfThongTin_MaHD.getText().trim());
        }

        System.out.println("Đang tìm kiếm (phiên bản rút gọn)");
    }


    private void btnTimKiem_TimKiemMouseClicked(java.awt.event.MouseEvent evt) {                                                
        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!tfTimKiem_MaHD1.getText().isEmpty())
            list_HD.setList_HD(list_HD.timKiem_MaHD(tfTimKiem_MaHD1.getText(), true));
        if (!tfTimKiem_MaKH1.getText().isEmpty())
            list_HD.setList_HD(list_HD.timKiem_MaKH(tfTimKiem_MaKH1.getText(), true));
        if (!tfTimKiem_MaNV1.getText().isEmpty())
            list_HD.setList_HD(list_HD.timKiem_MaNV(tfTimKiem_MaNV1.getText(), true));
        if (!tfTimKiem_MaKM1.getText().isEmpty())
            list_HD.setList_HD(list_HD.timKiem_MaKM(tfTimKiem_MaKM1.getText(), true));
        if (!tfTimKiem_TongTien1.getText().isEmpty())
            list_HD.setList_HD(list_HD.timKiem_TongTien(tfTimKiem_TongTien1.getText(), true));

        actionButtondisplay();
        System.out.println("Đang tìm kiếm");
    }


    private void btnTimKiem_HuyTimKiemMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            list_HD = new HoaDonBUS();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        actionButtondisplay();
    }

    private void btnTimKiem_Trong1MouseClicked(java.awt.event.MouseEvent evt) {
        try{
            list_HD = new HoaDonBUS();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tfTimKiem_MaHD1.setText("");
        tfTimKiem_MaKH1.setText("");
        tfTimKiem_MaKM1.setText("");
        tfTimKiem_MaNV1.setText("");
        tfTimKiem_TongTien1.setText("");
    }

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
        try {
            list_ChiTietHD.docDB();
            if (tfThongTin_MaHD.getText().equals(""))
                actionButtondisplayChiTiet();
            else
                actionButtondisplayChiTiet(tfThongTin_MaHD.getText());
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
        tfChiTiet_MaSP2.setText("");
        tfChiTiet_MaSP3.setText("");
        tfChiTiet_SL2.setText("");
        tfChiTiet_SL3.setText("");
    }

    private void tfThongTin_MaHDActionPerformed(java.awt.event.ActionEvent evt) {
    }

    /**
     * tong tien = tong tien + So Luong * Gia Ban * ti le khuyen mai
     * @param iSoLuong
     * @param iGiaBan
     * @param tiLeKM
     * @param hanhDong true nếu là thêm và false nếu là xóa
     */
    private double tinhTongTien(int iSoLuong, int iGiaBan, double tiLeKM, boolean hanhDong) {
        if (hanhDong)
            TongTien += iSoLuong * iGiaBan * (1 - tiLeKM);
        else
             TongTien -= iSoLuong * iGiaBan * (1 - tiLeKM);
        return TongTien;
    }
    
    //thêm, sửa hàm với sanphambus
    private boolean xulyThemChiTietHD() {
        // Kiểm tra dữ liệu nhập
        String maSP = tfChiTiet_MaSP.getText().trim();
        String maHD = tfChiTiet_MaHD.getText().trim();
        String slText = tfChiTiet_SL.getText().trim();

        if (maSP.isEmpty() || maHD.isEmpty() || slText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ Mã SP, Mã HD và Số lượng!",
                    "Thiếu dữ liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(slText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số hợp lệ!",
                    "Sai định dạng", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Lấy giá bán tự động
        int giaBan = list_SanPham.getGia(maSP);
        if (giaBan <= 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy giá bán của sản phẩm " + maSP,
                    "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            list_ChiTietHD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChiTietHDDTO hd = new ChiTietHDDTO();
        ChiTietKMDTO km = new ChiTietKMDTO();

        hd.setStrMaGiay(maSP);
        hd.setStrMaHD(maHD);
        hd.setiGiaBan(giaBan);
        hd.setiSoLuong(soLuong);

        boolean flag = list_ChiTietHD.kiemTraKhoachinh(hd);

        try {
            if (!flag) {
                list_ChiTietHD.them(hd);
                chinhSuaSoLuong_SP(hd.getStrMaGiay(), hd.getiSoLuong(), false);
            } else {
                JOptionPane.showMessageDialog(null, "Bị trùng mã SP", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false; // <--- quan trọng
            }
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        try {
            list_ChiTietHD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        actionButtondisplayChiTiet(hoadon.getStrMaHD());

        if (!flag) {
            km = list_ChiTietKM.getInfor(hd.getStrMaGiay(), list_HD.getMaKM(hd.getStrMaHD()));
            tinhTongTien(soLuong, giaBan, km.getTiLeKM(), true);

            hoadon.setTongTien(TongTien);

            try {
                list_HD.sua(hoadon);
            } catch (Exception ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

            actionButtondisplay();
        }

        tfChiTiet_MaSP.setText("");
        tfChiTiet_SL.setText("");
        tfChiTiet_Gia.setText("");
        tfChiTiet_MaSP.requestFocus();

        anHienCacNut(false);
        btnChiTiet_Them.setEnabled(true);

        return true; // trả về true nếu thêm thành công
    }
    
    private boolean kiemtraMaSP() {
        SanPhamDTO sp = new SanPhamDTO();
        sp = list_SanPham.getInfor(tfChiTiet_MaSP.getText());
        
        if (sp.getStrMaGiay().equals("null"))
            return false;
        
        tfChiTiet_SL.setText(String.valueOf(sp.getiSoLuong()));
        tfChiTiet_Gia.setText(String.valueOf(sp.getiGia()));
        
        return true;
    }
    /**
     * thêm hoặc bớt số lượng sản phẩm
     * @param strMaSP
     * @param iSoLuong so luong can thay doi
     * @param hanhdong <h2>Tăng số lượng sản phẩm là true</h2>
     */
    private void chinhSuaSoLuong_SP(String strMaSP, int iSoLuong, boolean hanhdong) {
        SanPhamDTO sp = new SanPhamDTO();
        
        sp = list_SanPham.getInfor(strMaSP);
        
        if (hanhdong) {
            sp.setiSoLuong( sp.getiSoLuong() + iSoLuong );
            System.out.println("tang so luong sp");
        }
        else {
            sp.setiSoLuong( sp.getiSoLuong() - iSoLuong );
            System.out.println("Giam so luong sp");
        }
        
        try {
            System.out.println("hanh dong chinh sua sp: "+ list_SanPham.sua(sp) );
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    private void tfChiTiet_MaSPMouseClicked(java.awt.event.MouseEvent evt) {
        tfChiTiet_Gia.setText("");
        tfChiTiet_SL.setText("");
    }

    // In chi tiết hóa đơn
    private void lblChiTiet_XuatMouseClicked(java.awt.event.MouseEvent evt) {
        if (hoadon.getTongTien() == 0)
            JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            InHoaDon in = new InHoaDon(hoadon);
            in.setVisible(true);
        }
    }

    int x_Mouse, y_Mouse;
    
    private void formMousePressed(java.awt.event.MouseEvent evt) {
         x_Mouse = evt.getX();
         y_Mouse = evt.getY();
    }

    private void formMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        setLocation(x - x_Mouse, y - y_Mouse);
    }

    private void lblChiTiet_3chamMouseClicked(java.awt.event.MouseEvent evt) {
        ChonSanPham csp = new ChonSanPham(this, true);
        csp.setVisible(true);
        tfChiTiet_MaSP.setText(Memory.maSP);
        tfChiTiet_Gia.setText(String.valueOf(Memory.giaSP));

        tfChiTiet_SL.setEditable(true);

    }

    private void cbbThongTin_MaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThongTin_MaKHActionPerformed
        // TODO add your handling code here:
        System.out.println(cbbThongTin_MaKH.getSelectedItem().toString());
        KhachHangDTO kh = list_KH.getInfor(cbbThongTin_MaKH.getSelectedItem().toString());
        tfThongTin_TenKH.setText(kh.getStrHo() + " " + kh.getStrTen());
    }//GEN-LAST:event_cbbThongTin_MaKHActionPerformed

    private void lblChiTiet_3cham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChiTiet_3cham1MouseClicked
        // TODO add your handling code here:
        ChonSanPham csp = new ChonSanPham(this, true);
        csp.setVisible(true);
        tfChiTiet_MaSP2.setText(Memory.maSP);
    }//GEN-LAST:event_lblChiTiet_3cham1MouseClicked

    private void lblChiTiet_3cham2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChiTiet_3cham2MouseClicked
        // TODO add your handling code here:
        ChonSanPham csp = new ChonSanPham(this, true);
        csp.setVisible(true);
        tfChiTiet_MaSP3.setText(Memory.maSP);
    }//GEN-LAST:event_lblChiTiet_3cham2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    
    private JLabel getMenuLabelByName(String name) {
        for (JLabel lbl : listLblTop) {
            if (name.equals(lbl.getName())) return lbl;
        }
        return null;
    }

    public void kiemTraCapBac() {
        int iKey = Memory.iCapBac;

        // Ẩn hết
        for (JLabel lbl : listLblTop) {
            lbl.setVisible(false);
        }

        // Luôn cho thấy Dashboard
        JLabel dash = getMenuLabelByName("Dashboard");
        if (dash != null) dash.setVisible(true);

        switch (iKey) {
            case 1: // Admin
                for (JLabel lbl : listLblTop) lbl.setVisible(true);
                break;
            case 2: // Dashboard + Thống kê
                JLabel tk = getMenuLabelByName("Thống kê");
                if (tk != null) tk.setVisible(true);
                break;
            case 3: // Dashboard + Hàng hóa
                JLabel hh = getMenuLabelByName("Hàng hóa");
                if (hh != null) hh.setVisible(true);
                break;
            case 4: // Dashboard + Nhập hàng
                JLabel nh = getMenuLabelByName("Nhập hàng");
                if (nh != null) nh.setVisible(true);
                break;
            case 5: // Dashboard + Khuyến mãi
                JLabel km = getMenuLabelByName("Khuyến mãi");
                if (km != null) km.setVisible(true);
                break;
            case 6: // Dashboard + Hóa đơn
                JLabel hd = getMenuLabelByName("Hóa đơn");
                if (hd != null) hd.setVisible(true);
                break;
        }
    }
    
    private void createVectorHeader() {
        if (model.getRowCount()==0) {
            Vector<String> header = new Vector();
            header.add("Mã HĐ");
            header.add("Mã KH");
            header.add("Mã NV");
            header.add("Mã KM");
            header.add("Ngày Bán");
            header.add("Tổng Tiền");
            
            model = new DefaultTableModel(header, 0){
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
        }
    }
    
    private void actionButtondisplay() {
        model = new DefaultTableModel();      
        createVectorHeader();
        
        for (int i = 0; i < list_HD.getNumbHoaDon(); i++) {
            HoaDonDTO hd = list_HD.getInfor(i); // lấy dữ liệu thật
            
            // Chỉ hiển thị hóa đơn chưa bị xóa mềm
            if (hd.getDeleted() == 0) {    
                Vector row = new Vector();
                row.add(hd.getStrMaHD());
                row.add(hd.getStrMaKH());
                row.add(hd.getStrMaNV());
                row.add(hd.getStrMaKM());
                row.add(hd.getStrNgayBan());
                row.add(hd.getTongTien());
                model.addRow(row);   
            }
        }
        
        tblThongTin.setModel(model);
        tblThongTin.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblThongTin.getColumnModel().getColumn(3).setPreferredWidth(50);
    }

    private void createVectorHeaderChiTiet() {
        if (modelChiTiet.getRowCount()==0) {
            Vector<String> header = new Vector();
            header.add("Mã Sản Phẩm");
            header.add("Mã Hóa Đơn");
            header.add("Số Lượng");
            header.add("Giá Bán");
            header.add("Mã Khuyến Mãi");
            header.add("Tỉ Lệ KM");
            modelChiTiet = new DefaultTableModel(header, 0) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
        }
    }
    
    private void actionButtondisplayChiTiet() {
        actionButtondisplayChiTiet("null");
    }
    
    private void actionButtondisplayChiTiet(String strMaHD) {
        modelChiTiet = new DefaultTableModel();
        
        createVectorHeaderChiTiet();
        for (int i=0 ; i < list_ChiTietHD.getNumbChiTietHD() ; i++) {
            ChiTietHDDTO hd = new ChiTietHDDTO();
            
            hd = list_ChiTietHD.getInfor(i);
            
            createVectorHeaderChiTiet();
            
            if ( hd.getStrMaHD().equalsIgnoreCase(strMaHD) || 
                    strMaHD.equals("null")) {
                
                Vector row=new Vector();
                row.add(hd.getStrMaGiay());
                row.add(hd.getStrMaHD());
                row.add(hd.getiSoLuong());
                row.add(hd.getiGiaBan());
                chenMaKM(row, hd.getStrMaGiay(), list_HD.getMaKM(hd.getStrMaHD()));
                modelChiTiet.addRow(row);
            }
        }
        
        tblThongTin1.setModel(modelChiTiet);
    }
    
    private Vector chenMaKM(Vector row, String strMaGiay, String strMaKM) {
        ChiTietKMDTO km = new ChiTietKMDTO();
        boolean flag = false;          
        
        if (strMaKM.equals("null"))
            km = list_ChiTietKM.getInfor(strMaGiay);
        else {
            km = list_ChiTietKM.getInfor(strMaGiay, strMaKM);
            flag = !flag;
            if ( km.getStrMaGiay().equals("null") )    
                flag = !flag;
        }
        
        if (flag) {
            row.add(strMaKM);
            double TiLeKM = km.getTiLeKM() * 100;
            row.add(TiLeKM+"%");
        }
        else {
            row.add("");
            row.add("0%");
        }
        
        return row;
    }
    
        private JPanel createPanel_LblLeft(String[] strArr_Left) {
        JPanel panel = new JPanel();
        listLblTop = new JLabel[strArr_Left.length];

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(44, 62, 80)); // Darker blue like in images
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(52, 73, 94))); // Right border

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(44, 62, 80));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5)); // Right padding
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // User Profile Section
        try {
            NhanVienBUS nvBUS = new NhanVienBUS();
            NhanVienDTO nv = nvBUS.getNhanVien_MaNV(Memory.maNV);
            
            JPanel profilePanel = new JPanel();
            profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
            profilePanel.setBackground(new Color(44, 62, 80));
            profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
            profilePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Avatar with border
            JLabel lblAvatar = new JLabel();
            ImageIcon avatarIcon = new ImageIcon("./src/ShoesManager/images/Avatar/130_100/" + nv.getStrAnh() + ".png");
            lblAvatar.setIcon(avatarIcon);
            lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblAvatar.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2)); // Light grey border
            lblAvatar.setOpaque(false);
            profilePanel.add(lblAvatar);
            profilePanel.add(Box.createVerticalStrut(12));

            // Name
            JLabel lblName = new JLabel("Quản trị Viên");
            lblName.setFont(new Font("Segoe UI", Font.BOLD, 17));
            lblName.setForeground(Color.WHITE);
            lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
            profilePanel.add(lblName);
            profilePanel.add(Box.createVerticalStrut(6));

            // ID
            JLabel lblID = new JLabel("ID: " + nv.getstrMaNV());
            lblID.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblID.setForeground(new Color(200, 200, 200));
            lblID.setAlignmentX(Component.CENTER_ALIGNMENT);
            profilePanel.add(lblID);
            profilePanel.add(Box.createVerticalStrut(4));

            // Role
            JLabel lblRole = new JLabel(nv.getStrChucVu());
            lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblRole.setForeground(new Color(76, 175, 80)); // Green color
            lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);
            profilePanel.add(lblRole);

            // Wrap profilePanel in a centered container
            JPanel profileWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            profileWrapper.setBackground(new Color(44, 62, 80));
            profileWrapper.add(profilePanel);
            
            mainPanel.add(profileWrapper);
            mainPanel.add(Box.createVerticalStrut(10));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Menu Items
        String[] menuNames = { "Dashboard", "Hóa đơn", "Khuyến mãi", "Nhập hàng", "Hàng hóa", "Thống kê" };
        String[] menuIcons = { "📊", "📋", "🎉", "📦", "👟", "📈" };
        String currentMenu = "Hóa đơn"; // Current active menu

        for (int i = 0; i < strArr_Left.length; i++) {
            // Find matching menu name
            String menuName = strArr_Left[i];
            String icon = "📋";
            for (int j = 0; j < menuNames.length; j++) {
                if (menuNames[j].equals(menuName) || 
                    (menuName.equals("Hóa đơn") && menuNames[j].equals("Hóa đơn"))) {
                    icon = menuIcons[j];
                    break;
                }
            }

            // Main row panel with BoxLayout for better vertical centering
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
            rowPanel.setBackground(new Color(44, 62, 80));
            rowPanel.setPreferredSize(new Dimension(220, 50));
            rowPanel.setMaximumSize(new Dimension(220, 50));
            rowPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            rowPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            
            // Add horizontal glue to center content
            rowPanel.add(Box.createHorizontalGlue());
            
            JLabel lblIcon = new JLabel(icon);
            lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            lblIcon.setForeground(Color.WHITE);
            lblIcon.setVerticalAlignment(SwingConstants.CENTER);
            lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
            lblIcon.setAlignmentY(Component.CENTER_ALIGNMENT);
            rowPanel.add(lblIcon);
            rowPanel.add(Box.createHorizontalStrut(8));

            // Text label
            JLabel lblText = new JLabel(strArr_Left[i]);
            lblText.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            lblText.setForeground(Color.WHITE);
            lblText.setName(strArr_Left[i]);
            lblText.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lblText.setVerticalAlignment(SwingConstants.CENTER);
            lblText.setHorizontalAlignment(SwingConstants.CENTER);
            lblText.setAlignmentY(Component.CENTER_ALIGNMENT);
            lblText.addMouseListener(this);
            rowPanel.add(lblText);
            
            // Add horizontal glue to center content
            rowPanel.add(Box.createHorizontalGlue());

            // Highlight active menu
            final boolean isActive = strArr_Left[i].equals(currentMenu);
            if (isActive) {
                rowPanel.setBackground(new Color(52, 152, 219)); // Light blue for active
            }

            // Make entire row clickable and add hover effect
            final String menuItemName = strArr_Left[i];
            rowPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Trigger click on text label
                    for (MouseListener ml : lblText.getMouseListeners()) {
                        ml.mouseClicked(e);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!isActive) {
                        rowPanel.setBackground(new Color(60, 80, 100));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (isActive) {
                        rowPanel.setBackground(new Color(52, 152, 219));
                    } else {
                        rowPanel.setBackground(new Color(44, 62, 80));
                    }
                }
            });
            rowPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            mainPanel.add(rowPanel);
            listLblTop[i] = lblText;
        }

        // Logout Button at bottom
        mainPanel.add(Box.createVerticalGlue());
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        logoutPanel.setBackground(new Color(44, 62, 80));
        logoutPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogout.setBackground(new Color(231, 76, 60)); // Red color
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.setPreferredSize(new Dimension(180, 45));
        btnLogout.setMaximumSize(new Dimension(180, 45));
        btnLogout.setMinimumSize(new Dimension(180, 45));
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn đăng xuất?", 
                "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    new Login().setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogout.setBackground(new Color(192, 57, 43));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnLogout.setBackground(new Color(231, 76, 60));
            }
        });
        logoutPanel.add(btnLogout);
        mainPanel.add(logoutPanel);

        panel.add(mainPanel, BorderLayout.CENTER);
        return panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {}

@Override
    public void mouseReleased(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        String name = src.getName();
         
        SwingUtilities.invokeLater(() -> {
             try {
                JFrame newFrame = null;
                switch (name) {
                    case "Dashboard" -> newFrame = new Home();
                    case "Khuyến mãi" -> newFrame = new KhuyenMai();
                    case "Hóa đơn" -> newFrame = new HoaDon();
                    case "Nhập hàng" -> newFrame = new PhieuNhap();
                    case "Hàng hóa" -> newFrame = new Sanpham();
                    case "Thống kê" -> newFrame = new ThongKe();
                    default -> { return; }
                }
                if (newFrame != null) {
                    newFrame.setVisible(true);
                    setVisible(false);
                    dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi chuyển trang: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

   @Override
    public void mouseEntered(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        if (src.getParent() instanceof JPanel) {
            JPanel parent = (JPanel) src.getParent();
            if (!parent.getBackground().equals(new Color(52, 152, 219))) { // Not active menu
                parent.setBackground(new Color(60, 80, 100)); // Darker on hover
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        if (src.getParent() instanceof JPanel) {
            JPanel parent = (JPanel) src.getParent();
            String menuName = src.getName();
            if (menuName != null && menuName.equals("Hóa đơn")) {
                parent.setBackground(new Color(52, 152, 219)); // Active menu
            } else {
                parent.setBackground(new Color(44, 62, 80)); // Normal menu
            }
        }
    }

     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HoaDon().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
