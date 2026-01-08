package ShoesManager.GUI;

import ShoesManager.BUS.*;
import ShoesManager.DTO.*;
import java.awt.*;
import java.awt.event.*;
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

import java.io.File;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class PhieuNhap extends javax.swing.JFrame  implements MouseListener{
    private javax.swing.JButton btnChiTiet_Sua, btnChiTiet_TaoMoi, btnChiTiet_Them, btnChiTiet_Xoa, btnThongTin_Sua, btnThongTin_Them, btnThongTin_TaoMoi, btnThongTin_Xoa, btnTimKiem_HuyTimKiem, btnTimKiem_TimKiem, btnTimKiem_Trong;
    private com.toedter.calendar.JDateChooser calendarThongTin;
    private javax.swing.JComboBox<String> cbbThongTin_MaNCC;
    private javax.swing.JButton jButton4, jButton5, jButton6;
    private javax.swing.JScrollPane jScrollPane2, jScrollPane3;
    private javax.swing.JLabel lblChiTiet_3cham, lblChiTiet_3cham1, lblChiTiet_3cham2, lblChiTiet_ChiTietHoaDon, lblChiTiet_Gia, lblChiTiet_Gia1, lblChiTiet_MaHD, lblChiTiet_MaSP, lblChiTiet_MaSP1, lblChiTiet_SL, lblChiTiet_SoLuong2, lblChiTiet_Tatca;
    private javax.swing.JLabel lblMuiTen, lblMuiTen1, lblThongTin_MaHD1, lblThongTin_MaKH1, lblThongTin_MaNCC, lblThongTin_MaNV, lblThongTin_MaNV1, lblThongTin_MaPN, lblThongTin_NgayNhap, lblThongTin_ThongTin, lblThongTin_TimKiem, lblThongTin_TongTien, lblThongTin_TongTien1;
    private javax.swing.JMenu menuTaiKhoan, menuTroVe;
    private javax.swing.JMenuItem menuTaiKhoan_DangXuat, menuTaiKhoan_ThongTin;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JPanel pCenter, pChiTiet, pChiTietSanPham, pChiTietTimKiem, pThongTin, pThongTin_ThongTin, pThongTin_TimKiem, pTop;
    private javax.swing.JTable tblThongTin, tblThongTin1;
    private javax.swing.JTextField tfChiTiet_Gia, tfChiTiet_Gia1, tfChiTiet_MaHD, tfChiTiet_MaSP, tfChiTiet_MaSP2, tfChiTiet_MaSP3, tfChiTiet_SL, tfChiTiet_SL2, tfChiTiet_SL3, tfThongTin_MaNV, tfThongTin_MaPN, tfThongTin_TongTien, tfTimKiem_MaNCC1, tfTimKiem_MaNV1, tfTimKiem_MaPN1, tfTimKiem_TongTien1;

    private javax.swing.JButton btnDocFile, btnGhiFile, btnDocDatabase;
    private Object[][] data_PN = { 
        { "MaPN", "MaNCC", "MaNV", "NgayNhap", "TongTien" }
    };
    private Object[][] data_CTPN = { 
        { "MaPN", "MaGiay", "SoLuong", "GiaNhap" }
    };

    private JLabel[] listLblTop;
    private String[] strArr_Top;
    private PhieuNhapBUS list_PN;
    private ChiTietPNBUS list_ChiTietPN;
    private SanPhamBUS list_SanPham;
    private DefaultTableModel model, modelChiTiet;
    private double TongTien;
    private PhieuNhapDTO phieunhap;
    private CacHamQuanTrong cachamquantrong;
    private String date1 = LocalDateTime.now().getDayOfMonth() + " " + LocalDateTime.now().getMonth() + " " + LocalDateTime.now().getYear();


    // writeFileExcel
    private void writeFileExcel_PN() {

        // Kiểm tra người dùng có chọn file hay không
        if (Memory.filechoose == null || Memory.filechoose.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Bạn chưa chọn file để ghi!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn ghi dữ liệu phiếu nhập ra file Excel không?",
                "Xác nhận ghi file", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(new File(Memory.filechoose));

            // create sheet
            WritableSheet sheet1 = workbook.createSheet("PhieuNhap", 0);
            WritableSheet sheet2 = workbook.createSheet("ChiTietPhieuNhap", 1);

            int rowBegin = 0;
            int colBegin = 0;

            // Ghi header phiếu nhập
            for (int row = rowBegin, i = 0; row < data_PN.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data_PN[0].length + colBegin; col++, j++) {
                    sheet1.addCell(new Label(col, row, (String) data_PN[i][j]));
                }
            }

            // Ghi header chi tiết phiếu nhập
            for (int row = rowBegin, i = 0; row < data_CTPN.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data_CTPN[0].length + colBegin; col++, j++) {
                    sheet2.addCell(new Label(col, row, (String) data_CTPN[i][j]));
                }
            }

            rowBegin = 1;

            // Ghi dữ liệu phiếu nhập
            for (int row = rowBegin, i = 0; row < list_PN.getNumb() + rowBegin; row++, i++) {
                PhieuNhapDTO pn = list_PN.getInfor(i);
                sheet1.addCell(new Label(0, row, pn.getStrMaPN()));
                sheet1.addCell(new Label(1, row, pn.getStrMaNCC()));
                sheet1.addCell(new Label(2, row, pn.getStrMaNV()));
                sheet1.addCell(new Label(3, row, pn.getStrNgayNhap()));
                sheet1.addCell(new Label(4, row, String.valueOf(pn.getTongTien())));
            }

            // Ghi dữ liệu chi tiết phiếu nhập
            for (int row = rowBegin, i = 0; row < list_ChiTietPN.getNumbChiTietPN() + rowBegin; row++, i++) {
                ChiTietPNDTO ctpn = list_ChiTietPN.getInfor(i);
                sheet2.addCell(new Label(0, row, ctpn.getStrMaGiay()));
                sheet2.addCell(new Label(1, row, ctpn.getStrMaPN()));
                sheet2.addCell(new Label(2, row, String.valueOf(ctpn.getiSoLuong())));
                sheet2.addCell(new Label(3, row, String.valueOf(ctpn.getiGiaNhap())));
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
    private void readFileExcel_PN() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn đọc dữ liệu từ file Excel không?",
                "Xác nhận đọc file", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        int duplicateCount = 0;
        int errorCount = 0;

        try {
            // Khởi tạo nếu null
            if (list_PN == null) list_PN = new PhieuNhapBUS();
            if (list_ChiTietPN == null) list_ChiTietPN = new ChiTietPNBUS();

            // Đọc dữ liệu hiện có trong DB để kiểm tra trùng
            list_PN.docDB();
            list_ChiTietPN.docDB();

            Workbook workbook = Workbook.getWorkbook(new File(Memory.filechoose));
            Sheet sheet = workbook.getSheet(0);
            Sheet sheet1 = workbook.getSheet(1);

            // Dùng HashSet để kiểm tra trùng trong file Excel
            Set<String> maPNTrongFile = new HashSet<>();

            // đọc phiếu nhập
            for (int row = 1; row < sheet.getRows(); row++) {
                String[] arr = new String[sheet.getColumns()];
                for (int col = 0; col < sheet.getColumns(); col++) {
                    arr[col] = sheet.getCell(col, row).getContents().trim();
                }

                String maPN = arr[0];
                if (maPN.isEmpty()) continue;

                boolean isDuplicate = false;

                // Kiểm tra trùng trong DB hiện có
                for (int i = 0; i < list_PN.getNumb(); i++) {
                    if (list_PN.getInfor(i).getStrMaPN().equalsIgnoreCase(maPN)) {
                        isDuplicate = true;
                        break;
                    }
                }

                // Kiểm tra trùng trong chính file Excel
                if (maPNTrongFile.contains(maPN)) {
                    isDuplicate = true;
                } else {
                    maPNTrongFile.add(maPN);
                }

                if (isDuplicate) {
                    duplicateCount++;
                    JOptionPane.showMessageDialog(null,
                            "Mã phiếu nhập '" + maPN + "' bị trùng. Bỏ qua dòng " + (row + 1),
                            "Lỗi trùng mã", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                try {
                    PhieuNhapDTO pn = new PhieuNhapDTO();
                    pn.setStrMaPN(maPN);
                    pn.setStrMaNCC(arr[1]);
                    pn.setStrMaNV(arr[2]);
                    pn.setStrNgayNhap(arr[3]);
                    pn.setTongTien(Double.parseDouble(arr[4]));
                    list_PN.them(pn);
                } catch (Exception ex) {
                    errorCount++;
                    JOptionPane.showMessageDialog(null,
                            "Lỗi khi thêm phiếu nhập dòng " + (row + 1) + ": " + ex.getMessage(),
                            "Lỗi thêm dữ liệu", JOptionPane.ERROR_MESSAGE);
                }
            }

            // đọc chi tiết phiếu nhập
            for (int row = 1; row < sheet1.getRows(); row++) {
                String[] arr = new String[sheet1.getColumns()];
                for (int col = 0; col < sheet1.getColumns(); col++) {
                    arr[col] = sheet1.getCell(col, row).getContents().trim();
                }

                ChiTietPNDTO ctpn = new ChiTietPNDTO();
                ctpn.setStrMaPN(arr[0]);
                ctpn.setStrMaGiay(arr[1]);
                ctpn.setiSoLuong(Integer.parseInt(arr[2]));
                ctpn.setiGiaNhap(Integer.parseInt(arr[3]));

                try {
                    list_ChiTietPN.them(ctpn);
                } catch (Exception ignored) {}
            }

            workbook.close();

            // Làm mới danh sách từ DB sau khi thêm
            list_PN.docDB();
            list_ChiTietPN.docDB();

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

    public PhieuNhap() throws Exception {
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
        nhapLuongNCC();
        
        // hiển thị rdbtn
        tfThongTin_MaNV.setText(Memory.maNV);     
        tblThongTin.setAutoCreateRowSorter(true);
        tblThongTin1.setAutoCreateRowSorter(true);

        try {
            list_ChiTietPN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_PN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void init() throws Exception {
        
        cachamquantrong = new CacHamQuanTrong();
        list_PN = new PhieuNhapBUS();
        list_ChiTietPN = new ChiTietPNBUS();
        list_SanPham = new SanPhamBUS();
        phieunhap = new PhieuNhapDTO();
        
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
        
        // Panels chính
        pCenter = new JPanel();
        pThongTin = new JPanel();
        pChiTiet = new JPanel();
        pTop = createPanel_LblLeft(strArr_Top);

        // Create header panel with logo
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        logoPanel.setBackground(new Color(173, 216, 230));
        
        JLabel lblLogoIcon = new JLabel("👟");
        lblLogoIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        
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
        // pCenter.setLayout(new BorderLayout());

        btnDocFile = new JButton();
        btnGhiFile = new JButton();
        btnDocDatabase = new JButton();


        // Panel Thông tin
        lblThongTin_ThongTin = new JLabel();
        lblThongTin_TimKiem = new JLabel();
        tblThongTin = new JTable();
        jScrollPane2 = new JScrollPane(tblThongTin);
        pThongTin_ThongTin = new JPanel();
        lblThongTin_MaPN = new JLabel();
        lblThongTin_MaNCC = new JLabel();
        lblThongTin_MaNV = new JLabel();
        lblThongTin_NgayNhap = new JLabel();
        lblThongTin_TongTien = new JLabel();
        tfThongTin_MaPN = new JTextField();
        tfThongTin_MaNV = new JTextField();
        tfThongTin_TongTien = new JTextField();
        cbbThongTin_MaNCC = new JComboBox<>();
        calendarThongTin = new com.toedter.calendar.JDateChooser();
        btnThongTin_Them = new JButton();
        btnThongTin_Xoa = new JButton();
        btnThongTin_Sua = new JButton();
        btnThongTin_TaoMoi = new JButton();

        // Panel Tìm kiếm
        pThongTin_TimKiem = new JPanel();
        lblThongTin_MaKH1 = new JLabel();
        lblThongTin_TongTien1 = new JLabel();
        lblThongTin_MaHD1 = new JLabel();
        lblThongTin_MaNV1 = new JLabel();
        tfTimKiem_MaPN1 = new JTextField();
        tfTimKiem_MaNCC1 = new JTextField();
        tfTimKiem_MaNV1 = new JTextField();
        tfTimKiem_TongTien1 = new JTextField();
        btnTimKiem_TimKiem = new JButton();
        btnTimKiem_HuyTimKiem = new JButton();
        btnTimKiem_Trong = new JButton();

        // Panel Chi tiết
        tblThongTin1 = new JTable();
        jScrollPane3 = new JScrollPane(tblThongTin1);
        lblChiTiet_ChiTietHoaDon = new JLabel();
        pChiTietSanPham = new JPanel();
        lblChiTiet_MaSP = new JLabel();
        lblChiTiet_MaHD = new JLabel();
        lblChiTiet_SL = new JLabel();
        lblChiTiet_Gia = new JLabel();
        lblChiTiet_Gia1 = new JLabel();
        lblChiTiet_3cham = new JLabel();
        tfChiTiet_MaSP = new JTextField();
        tfChiTiet_MaHD = new JTextField();
        tfChiTiet_SL = new JTextField();
        tfChiTiet_Gia = new JTextField();
        tfChiTiet_Gia1 = new JTextField();
        btnChiTiet_Them = new JButton();
        btnChiTiet_Xoa = new JButton();
        btnChiTiet_Sua = new JButton();
        btnChiTiet_TaoMoi = new JButton();

        // Panel Chi tiết tìm kiếm
        pChiTietTimKiem = new JPanel();
        lblChiTiet_SoLuong2 = new JLabel();
        lblChiTiet_MaSP1 = new JLabel();
        lblChiTiet_Tatca = new JLabel();
        lblChiTiet_3cham1 = new JLabel();
        lblChiTiet_3cham2 = new JLabel();
        lblMuiTen = new JLabel();
        lblMuiTen1 = new JLabel();
        tfChiTiet_MaSP2 = new JTextField();
        tfChiTiet_MaSP3 = new JTextField();
        tfChiTiet_SL2 = new JTextField();
        tfChiTiet_SL3 = new JTextField();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();

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


        // Cấu hình kích thước các panel chính
        pCenter.setPreferredSize(new java.awt.Dimension(1280, 600));    
        pThongTin.setPreferredSize(new java.awt.Dimension(340, 580));
        pThongTin.setVerifyInputWhenFocusTarget(false);

        // Cấu hình JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Hệ Thống Quản Lý Bán Giày - Dashboard");
        setSize(1430, 720);
        setMinimumSize(new Dimension(1280, 720));
        setResizable(true);
        setUndecorated(false);
        setLocationRelativeTo(null);

        // Sự kiện chuột để di chuyển form
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        Color xanhNen = new Color(245, 247, 250); // Modern light gray background
        getContentPane().setBackground(xanhNen);
        pThongTin.setBackground(xanhNen);
        pChiTiet.setBackground(xanhNen);
        pThongTin_ThongTin.setBackground(xanhNen);
        pThongTin_TimKiem.setBackground(xanhNen);
        pChiTietSanPham.setBackground(xanhNen);
        pChiTietTimKiem.setBackground(xanhNen);

        // Nút Thông tin
        lblThongTin_ThongTin.setBackground(new java.awt.Color(255, 255, 255));
        lblThongTin_ThongTin.setForeground(new java.awt.Color(255, 255, 255));
        lblThongTin_ThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/210_70/Tìm kiếm.png"))); // NOI18N
        lblThongTin_ThongTin.setMaximumSize(new java.awt.Dimension(210, 131));
        lblThongTin_ThongTin.setMinimumSize(new java.awt.Dimension(210, 131));
        lblThongTin_ThongTin.setName("pThongTin_ThongTin");
        lblThongTin_ThongTin.setPreferredSize(new java.awt.Dimension(210, 131));
        lblThongTin_ThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblThongTin_ThongTinMouseReleased(evt);
            }
        });

        // Nút Tìm kiếm
        lblThongTin_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/210_70/Thông tin.png"))); // NOI18N
        lblThongTin_TimKiem.setName("pThongTin_TiemKiem");
        lblThongTin_TimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblThongTin_TimKiemMouseReleased(evt);
            }
        });

        // Tạo bảng thông tin phiếu nhập
        tblThongTin = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Mã PN", "Mã NCC", "Mã NV", "Ngày Nhập", "Tổng tiền"}
        ) {
            final Class<?>[] types = {String.class, String.class, String.class, String.class, Double.class};
            final boolean[] canEdit = {true, false, false, false, false};
            @Override public Class<?> getColumnClass(int col) { return types[col]; }
            @Override public boolean isCellEditable(int row, int col) { return canEdit[col]; }
        });       
        tblThongTin.setGridColor(new Color(102, 255, 102));
        tblThongTin.setSelectionBackground(new Color(153, 153, 153));
        tblThongTin.setSelectionForeground(new Color(0, 0, 0));
        tblThongTin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tblThongTinMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThongTin);
        if (tblThongTin.getColumnModel().getColumnCount() > 0) {
            tblThongTin.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblThongTin.getColumnModel().getColumn(0).setHeaderValue("Mã PN");
            tblThongTin.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblThongTin.getColumnModel().getColumn(1).setHeaderValue("Mã NCC");
            tblThongTin.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblThongTin.getColumnModel().getColumn(2).setHeaderValue("Mã NV");
            tblThongTin.getColumnModel().getColumn(3).setPreferredWidth(90);
            tblThongTin.getColumnModel().getColumn(3).setHeaderValue("Ngày Nhập");
            tblThongTin.getColumnModel().getColumn(4).setPreferredWidth(90);
            tblThongTin.getColumnModel().getColumn(4).setHeaderValue("Tổng tiền");
        }

        //Form phiếu nhập
        pThongTin_ThongTin.setPreferredSize(new Dimension(405, 280));
        lblThongTin_MaPN.setFont(new Font("Arial", 1, 18)); 
        lblThongTin_MaPN.setText("Mã phiếu nhập");
        lblThongTin_MaNCC.setFont(new Font("Arial", 1, 18)); 
        lblThongTin_MaNCC.setText("Mã nhà cung cấp");
        lblThongTin_MaNV.setFont(new Font("Arial", 1, 18));
        lblThongTin_MaNV.setText("Mã nhân viên");
        lblThongTin_NgayNhap.setFont(new Font("Arial", 1, 18));
        lblThongTin_NgayNhap.setText("Ngày nhập");
        lblThongTin_TongTien.setFont(new Font("Arial", 1, 18));
        lblThongTin_TongTien.setText("Tổng tiền");

        // Nút chức năng Form phiếu nhập
        btnThongTin_Them.setFont(new Font("Arial", 1, 18));
        btnThongTin_Them.setText("Thêm");
        btnThongTin_Them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThongTin_ThemActionPerformed(evt);
            }
        });
        btnThongTin_Xoa.setFont(new Font("Arial", 1, 18));
        btnThongTin_Xoa.setText("Xóa");
        btnThongTin_Xoa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnThongTin_XoaMouseClicked(evt);
            }
        });
        btnThongTin_Sua.setFont(new Font("Arial", 1, 18));
        btnThongTin_Sua.setText("Sửa");
        btnThongTin_Sua.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnThongTin_SuaMouseClicked(evt);
            }
        });
        btnThongTin_TaoMoi.setFont(new Font("Arial", 1, 18));
        btnThongTin_TaoMoi.setText("Tạo Mới");
        btnThongTin_TaoMoi.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnThongTin_TaoMoiMouseClicked(evt);
            }
        });

        // Textfield Form phiếu nhập
        tfThongTin_MaPN.setEditable(false);
        tfThongTin_MaPN.setFont(new Font("Arial", 1, 18)); 
        tfThongTin_MaPN.setPreferredSize(new Dimension(170, 28));
        tfThongTin_MaPN.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tfThongTin_MaPNMouseClicked(evt);
            }
        });
        tfThongTin_MaPN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tfThongTin_MaPNActionPerformed(evt);
            }
        });
        cbbThongTin_MaNCC.setFont(new Font("Arial", Font.BOLD, 18));
        cbbThongTin_MaNCC.setPreferredSize(new Dimension(170, 28));
        tfThongTin_MaNV.setEditable(false);
        tfThongTin_MaNV.setFont(new Font("Arial", 1, 18));
        tfThongTin_MaNV.setPreferredSize(new Dimension(170, 28));
        tfThongTin_TongTien.setEditable(false);
        tfThongTin_TongTien.setFont(new Font("Arial", 1, 18)); 
        tfThongTin_TongTien.setPreferredSize(new Dimension(170, 28));
        calendarThongTin.setDate(new java.util.Date());
        calendarThongTin.setDateFormatString("dd / MM / yyyy");
        calendarThongTin.setFont(new Font("Arial", 1, 18));
        calendarThongTin.setPreferredSize(new Dimension(170, 28));
        btnThongTin_TaoMoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThongTin_TaoMoiActionPerformed(evt);
            }
        });

        // Cbb Form phiếu nhập
        GroupLayout layout = new GroupLayout(pThongTin_ThongTin);
        pThongTin_ThongTin.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblThongTin_MaPN)
                                .addComponent(lblThongTin_MaNCC)
                                .addComponent(lblThongTin_MaNV)
                                .addComponent(lblThongTin_NgayNhap)
                                .addComponent(lblThongTin_TongTien))
                            .addGap(42)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfThongTin_MaPN)
                                .addComponent(cbbThongTin_MaNCC)
                                .addComponent(tfThongTin_MaNV)
                                .addComponent(calendarThongTin)
                                .addComponent(tfThongTin_TongTien)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnThongTin_Them)
                            .addGap(18)
                            .addComponent(btnThongTin_Xoa)
                            .addGap(18)
                            .addComponent(btnThongTin_Sua)
                            .addGap(18)
                            .addComponent(btnThongTin_TaoMoi)))
                    .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThongTin_MaPN)
                        .addComponent(tfThongTin_MaPN))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThongTin_MaNCC)
                        .addComponent(cbbThongTin_MaNCC))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThongTin_MaNV)
                        .addComponent(tfThongTin_MaNV))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblThongTin_NgayNhap)
                        .addComponent(calendarThongTin))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThongTin_TongTien)
                        .addComponent(tfThongTin_TongTien))
                    .addGap(31)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThongTin_Them)
                        .addComponent(btnThongTin_Xoa)
                        .addComponent(btnThongTin_Sua)
                        .addComponent(btnThongTin_TaoMoi))
                    .addContainerGap(15, Short.MAX_VALUE))
        );

        //Form tìm kiếm phiếu nhập
        pThongTin_TimKiem.setPreferredSize(new Dimension(405, 280));
        lblThongTin_MaKH1.setFont(new Font("Arial", 1, 18)); 
        lblThongTin_MaKH1.setText("Mã nhà cung cấp");
        lblThongTin_TongTien1.setFont(new Font("Arial", 1, 18));
        lblThongTin_TongTien1.setText("Tổng tiền");
        lblThongTin_MaHD1.setFont(new Font("Arial", 1, 18)); 
        lblThongTin_MaHD1.setText("Mã phiếu nhập");
        lblThongTin_MaNV1.setFont(new Font("Arial", 1, 18));
        lblThongTin_MaNV1.setText("Mã nhân viên");

        // Nút chức năng Form tìm kiếm phiếu nhập
        btnTimKiem_TimKiem.setFont(new Font("Arial", 1, 18));
        btnTimKiem_TimKiem.setText("Tìm kiếm");
        btnTimKiem_TimKiem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnTimKiem_TimKiemMouseClicked(evt);
            }
        });
        btnTimKiem_TimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTimKiem_TimKiemActionPerformed(evt);
            }
        });
        btnTimKiem_HuyTimKiem.setFont(new Font("Arial", 1, 18));
        btnTimKiem_HuyTimKiem.setText("Hủy tìm kiếm");
        btnTimKiem_HuyTimKiem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnTimKiem_HuyTimKiemMouseClicked(evt);
            }
        });
        btnTimKiem_HuyTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTimKiem_HuyTimKiemActionPerformed(evt);
            }
        }); 
        btnTimKiem_Trong.setFont(new Font("Arial", 1, 18)); 
        btnTimKiem_Trong.setText("Trống");
        btnTimKiem_Trong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnTimKiem_TrongMouseClicked(evt);
            }
        });
        btnTimKiem_Trong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTimKiem_TrongActionPerformed(evt);
            }
        });

        // Textfield Form tìm kiếm phiếu nhập
        tfTimKiem_MaPN1.setFont(new Font("Arial", 1, 18)); 
        tfTimKiem_MaPN1.setPreferredSize(new Dimension(170, 28));
        tfTimKiem_MaNCC1.setFont(new Font("Arial", 1, 18)); 
        tfTimKiem_MaNCC1.setPreferredSize(new Dimension(170, 28));
        tfTimKiem_MaNCC1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tfTimKiem_MaNCC1ActionPerformed(evt);
            }
        });
        tfTimKiem_MaNV1.setFont(new Font("Arial", 1, 18)); 
        tfTimKiem_MaNV1.setPreferredSize(new Dimension(170, 28));
        tfTimKiem_TongTien1.setFont(new Font("Arial", 1, 18));
        tfTimKiem_TongTien1.setPreferredSize(new Dimension(170, 28));

        // Cbb Form tìm kiếm phiếu nhập
        GroupLayout pThongTin_TimKiemLayout = new GroupLayout(pThongTin_TimKiem);
        pThongTin_TimKiem.setLayout(pThongTin_TimKiemLayout);

        pThongTin_TimKiemLayout.setHorizontalGroup(
            pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                .addGap(16)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                        .addComponent(btnTimKiem_TimKiem)
                        .addGap(18)
                        .addComponent(btnTimKiem_HuyTimKiem)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem_Trong))
                    .addGroup(pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                            .addComponent(lblThongTin_TongTien1)
                            .addGap(74)
                            .addComponent(tfTimKiem_TongTien1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                            .addGroup(pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblThongTin_MaHD1)
                                .addComponent(lblThongTin_MaKH1)
                                .addComponent(lblThongTin_MaNV1))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfTimKiem_MaPN1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfTimKiem_MaNCC1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfTimKiem_MaNV1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pThongTin_TimKiemLayout.setVerticalGroup(
            pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                        .addComponent(lblThongTin_MaHD1)
                        .addGap(18)
                        .addComponent(lblThongTin_MaKH1))
                    .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                        .addComponent(tfTimKiem_MaPN1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfTimKiem_MaNCC1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblThongTin_MaNV1)
                            .addComponent(tfTimKiem_MaNV1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addGap(18)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblThongTin_TongTien1)
                    .addComponent(tfTimKiem_TongTien1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(48)
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem_TimKiem)
                    .addComponent(btnTimKiem_HuyTimKiem)
                    .addComponent(btnTimKiem_Trong))
                .addContainerGap())
        );

        // Bố cục Panel Phieu Nhập
        GroupLayout pThongTinLayout = new GroupLayout(pThongTin);
        pThongTin.setLayout(pThongTinLayout);

        pThongTinLayout.setHorizontalGroup(
            pThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addComponent(lblThongTin_ThongTin, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongTin_TimKiem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addComponent(pThongTin_ThongTin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0)
                .addComponent(pThongTin_TimKiem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        pThongTinLayout.setVerticalGroup(
            pThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addGroup(pThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblThongTin_ThongTin, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThongTin_TimKiem, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pThongTin_ThongTin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(pThongTin_TimKiem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
        );

        // Tạo bảng thông tin chi tiết phiếu nhập
        pChiTiet.setPreferredSize(new java.awt.Dimension(880, 580));
        tblThongTin1 = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Mã SP", "Mã HĐ", "Số Lượng", "Giá Nhập"}
        ) {
            final Class<?>[] types = {String.class, String.class, Integer.class, Integer.class};
            final boolean[] canEdit = {false, false, false, false};

            @Override public Class<?> getColumnClass(int col) { return types[col]; }
            @Override public boolean isCellEditable(int row, int col) { return canEdit[col]; }
        });
        tblThongTin1.setGridColor(new java.awt.Color(102, 255, 102));
        tblThongTin1.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblThongTin1.setSelectionForeground(new java.awt.Color(0, 0, 0));
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
        }

        // Form chi tiết phiếu nhập
        lblChiTiet_ChiTietHoaDon.setFont(new Font("Arial", 1, 36));
        lblChiTiet_ChiTietHoaDon.setText("Chi Tiết Phiếu Nhập");
        lblChiTiet_MaSP.setFont(new Font("Arial", 1, 18)); 
        lblChiTiet_MaSP.setText("Mã Sản Phẩm");
        lblChiTiet_MaHD.setFont(new Font("Arial", 1, 18)); 
        lblChiTiet_MaHD.setText("Mã Hóa Đơn");
        lblChiTiet_SL.setFont(new Font("Arial", 1, 18));
        lblChiTiet_SL.setText("Số Lượng");
        lblChiTiet_Gia.setFont(new Font("Arial", 1, 18)); 
        lblChiTiet_Gia.setText("Giá Nhập");
        tfChiTiet_Gia.setFont(new Font("Arial", 1, 18)); 
        lblChiTiet_Gia1.setFont(new Font("Arial", 1, 18)); 
        lblChiTiet_Gia1.setText("Giá Bán");
        tfChiTiet_Gia1.setFont(new Font("Arial", 1, 18)); 


        // Nút chức năng Form chi tiết phiếu nhập
        btnChiTiet_Them.setFont(new Font("Arial", 1, 18)); 
        btnChiTiet_Them.setText("Thêm");
        btnChiTiet_Them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChiTiet_ThemActionPerformed(evt);
            }
        });
        btnChiTiet_Xoa.setFont(new Font("Arial", 1, 18)); 
        btnChiTiet_Xoa.setText("Xóa");
        btnChiTiet_Xoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChiTiet_XoaActionPerformed(evt);
            }
        });
        btnChiTiet_Sua.setFont(new Font("Arial", 1, 18));
        btnChiTiet_Sua.setText("Sửa");
        btnChiTiet_Sua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChiTiet_SuaActionPerformed(evt);
            }
        });
        btnChiTiet_TaoMoi.setFont(new Font("Arial", 1, 18));
        btnChiTiet_TaoMoi.setText("Tạo mới");
        btnChiTiet_TaoMoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChiTiet_TaoMoiActionPerformed(evt);
            }
        });

        // Textfield Form chi tiết phiếu nhập
        tfChiTiet_MaSP.setFont(new Font("Arial", 1, 18));
        tfChiTiet_MaSP.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tfChiTiet_MaSPMouseClicked(evt);
            }
        });
        tfChiTiet_MaHD.setEditable(false);
        tfChiTiet_MaHD.setFont(new Font("Arial", 1, 18));
        tfChiTiet_SL.setFont(new Font("Arial", 1, 18)); 
        tfChiTiet_SL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tfChiTiet_SLActionPerformed(evt);
            }
        });
        lblChiTiet_3cham.setIcon(new ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/40_20/3 chấm đen.png"))); 
        lblChiTiet_3cham.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                lblChiTiet_3chamMouseClicked(evt);
            }
        });

        // Cbb Form chi tiết phiếu nhập
        GroupLayout pChiTietSanPhamLayout = new GroupLayout(pChiTietSanPham);
        pChiTietSanPham.setLayout(pChiTietSanPhamLayout);

        pChiTietSanPhamLayout.setHorizontalGroup(
            pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                    .addGap(22)
                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                            .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblChiTiet_MaSP)
                                .addComponent(lblChiTiet_MaHD)
                                .addComponent(lblChiTiet_SL)
                                .addComponent(lblChiTiet_Gia)
                                .addComponent(lblChiTiet_Gia1))

                            .addGap(38)
                            .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(tfChiTiet_MaHD, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tfChiTiet_MaSP)
                                        .addComponent(tfChiTiet_Gia)
                                        .addComponent(tfChiTiet_Gia1)
                                        .addComponent(tfChiTiet_SL, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addGap(18)
                                    .addComponent(lblChiTiet_3cham))))
                        .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                            .addComponent(btnChiTiet_Them)
                            .addGap(12)
                            .addComponent(btnChiTiet_Xoa)
                            .addGap(12)
                            .addComponent(btnChiTiet_Sua)
                            .addGap(12)
                            .addComponent(btnChiTiet_TaoMoi)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pChiTietSanPhamLayout.setVerticalGroup(
            pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                    .addGap(25)
                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChiTiet_MaSP)
                            .addComponent(tfChiTiet_MaSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblChiTiet_3cham, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                    .addGap(12)
                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChiTiet_MaHD)
                        .addComponent(tfChiTiet_MaHD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(14)
                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChiTiet_SL)
                        .addComponent(tfChiTiet_SL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChiTiet_Gia)
                        .addComponent(tfChiTiet_Gia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChiTiet_Gia1)
                        .addComponent(tfChiTiet_Gia1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(pChiTietSanPhamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnChiTiet_Them)
                        .addComponent(btnChiTiet_Xoa)
                        .addComponent(btnChiTiet_Sua)
                        .addComponent(btnChiTiet_TaoMoi))
                    .addContainerGap())
        );

        // Form tìm kiếm chi tiết phiếu nhập
        pChiTietTimKiem.setPreferredSize(new Dimension(380, 238));
        lblChiTiet_Tatca.setBackground(new Color(255, 51, 51));
        lblChiTiet_Tatca.setFont(new Font("Arial", Font.BOLD, 36));
        lblChiTiet_Tatca.setText("Tất cả");
        lblChiTiet_Tatca.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
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

        lblChiTiet_MaSP1.setFont(new Font("Arial", 1, 18));
        lblChiTiet_MaSP1.setText("Mã Sản Phẩm");
        lblMuiTen.setFont(new Font("Arial", 1, 18)); 
        lblMuiTen.setText("->");
        lblChiTiet_SoLuong2.setFont(new Font("Arial", 1, 18));
        lblChiTiet_SoLuong2.setText("Số Lượng");
        lblMuiTen1.setFont(new Font("Arial", 1, 18));
        lblMuiTen1.setText("->");
        tfChiTiet_MaSP2.setFont(new Font("Arial", 1, 18));
        tfChiTiet_MaSP3.setFont(new Font("Arial", 1, 18)); 
        tfChiTiet_SL2.setFont(new Font("Arial", 1, 18)); 
        tfChiTiet_SL3.setFont(new Font("Arial", 1, 18)); 

        // Nút chức năng Form tìm kiếm chi tiết phiếu nhập
        jButton6.setFont(new Font("Arial", 1, 18)); 
        jButton6.setText("Tìm Kiếm");
        jButton6.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton4.setFont(new Font("Arial", 1, 18)); 
        jButton4.setText("Hủy Tìm Kiếm");
        jButton4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton5.setFont(new Font("Arial", 1, 18)); 
        jButton5.setText("Trống");
        jButton5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        lblChiTiet_3cham1.setIcon(new ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/40_20/3 chấm đen.png"))); 
        lblChiTiet_3cham1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                lblChiTiet_3cham1MouseClicked(evt);
            }
        });

        lblChiTiet_3cham2.setIcon(new ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/40_20/3 chấm đen.png"))); 
        lblChiTiet_3cham2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                lblChiTiet_3cham2MouseClicked(evt);
            }
        });

        // Cbb Form tìm kiếm chi tiết phiếu nhập
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

       // Layout pChiTiet
        GroupLayout pChiTietLayout = new GroupLayout(pChiTiet);
        pChiTiet.setLayout(pChiTietLayout);

        pChiTietLayout.setHorizontalGroup(
            pChiTietLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pChiTietLayout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(lblChiTiet_ChiTietHoaDon)
                    .addGap(48)
                    .addComponent(lblChiTiet_Tatca)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnDocFile)
                    .addGap(5)
                    .addComponent(btnGhiFile)
                    .addGap(5)
                    .addComponent(btnDocDatabase)
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
                        .addComponent(btnDocDatabase))
                    .addGap(10)
                    .addGroup(pChiTietLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(pChiTietSanPham, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pChiTietTimKiem, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                    .addGap(20)
                    .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
        );

        // Layout pCenter
        GroupLayout pCenterLayout = new GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
            pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pCenterLayout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(pThongTin, 430, 430, 430)
                    .addGap(18)
                    .addComponent(pChiTiet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pCenterLayout.setVerticalGroup(
            pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pCenterLayout.createSequentialGroup()
                    .addGap(20)
                    .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pThongTin, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                        .addComponent(pChiTiet, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
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
    
    // Ẩn hiện các panel
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
            readFileExcel_PN();
            actionButtondisplay();
            actionButtondisplayChiTiet();
        } else {
            JOptionPane.showMessageDialog(this, "File không đúng, hãy chọn file Excel (.xls)", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnGhiFileActionPerformed(ActionEvent evt) {
        ChonFile cf = new ChonFile(this, true);
        cf.setVisible(true);
        writeFileExcel_PN();
    }

    private void btnDocDatabaseActionPerformed(ActionEvent evt) {
        try {
            list_PN.docDB();
            list_ChiTietPN.docDB();
            list_SanPham.docDB();
            actionButtondisplay();
            actionButtondisplayChiTiet();
            JOptionPane.showMessageDialog(this, "Đã đọc dữ liệu từ database thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc từ database: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void lblThongTin_ThongTinMouseReleased(MouseEvent evt) {
        actionVisiblePanel();
        pThongTin_ThongTin.setVisible(true);
    }

    private void lblThongTin_TimKiemMouseReleased(MouseEvent evt) {
        actionVisiblePanel();
        pThongTin_TimKiem.setVisible(true);
    }

    private void btnThongTin_TaoMoiActionPerformed(ActionEvent evt) {
    }

    private void btnTimKiem_TimKiemActionPerformed(ActionEvent evt) {
    }

    private void btnTimKiem_HuyTimKiemActionPerformed(ActionEvent evt) {
    }

    private void btnTimKiem_TrongActionPerformed(ActionEvent evt) {
    }

    private void tfTimKiem_MaNCC1ActionPerformed(ActionEvent evt) {
    }

    // Nhập liệu cho combobox nhà cung cấp
    public void nhapLuongNCC() throws Exception{
        NhaCungCapBUS bus =new NhaCungCapBUS();
        ArrayList<NhaCungCapDTO> arr =bus.getList_NV();
        String[] s = new String[bus.getNumb()];
        int i = 0;
        for (NhaCungCapDTO dto : arr) {
            s[i] = dto.getStrMaNCC();
            i++;
        }
        cbbThongTin_MaNCC.setModel(new DefaultComboBoxModel<>(s));
    }
      
    // Ẩn hiện các nút
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
        tfChiTiet_Gia1.setEditable(bl);

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
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    private void tfThongTin_MaPNMouseClicked(MouseEvent evt) {
    }    
    private void tfThongTin_MaPNActionPerformed(ActionEvent evt) {
    }
    // Hiển thị thông tin phiếu nhập
    private void tblThongTinMouseClicked(MouseEvent evt) {
        int  i=tblThongTin.getSelectedRow();
        
        tfThongTin_MaPN.setText(tblThongTin.getModel().getValueAt(i, 0).toString());
        cbbThongTin_MaNCC.setSelectedItem(tblThongTin.getModel().getValueAt(i, 1));
        tfThongTin_MaNV.setText(tblThongTin.getModel().getValueAt(i, 2).toString());         
        Date date = new Date((String) tblThongTin.getModel().getValueAt(i, 3));
        calendarThongTin.setDate(date);
        tfThongTin_TongTien.setText(tblThongTin.getModel().getValueAt(i, 4).toString());
        
        // lấy thông tin
        TongTien = Double.parseDouble(tfThongTin_TongTien.getText());   
        phieunhap.setStrMaPN(tfThongTin_MaPN.getText());
        phieunhap.setStrMaNCC((String) cbbThongTin_MaNCC.getSelectedItem());
        phieunhap.setStrMaNV(tfThongTin_MaNV.getText());
        phieunhap.setStrNgayNhap((String) tblThongTin.getModel().getValueAt(i, 3));
        phieunhap.setTongTien(Double.parseDouble(tfThongTin_TongTien.getText()));
        
        // hiển thị bên chi tiết
        actionButtondisplayChiTiet(tfThongTin_MaPN.getText());   
        anHienCacNut(false);
        
        if (TongTien == 0)
            btnThongTin_Xoa.setEnabled(true);
        
        if (cachamquantrong.kiemTraDate(date1,(String) tblThongTin.getModel().getValueAt(i, 3))) {
            btnThongTin_Sua.setEnabled(true);
            btnChiTiet_TaoMoi.setEnabled(true);
        }
        
        // không cho chỉnh sửa
        tfThongTin_MaPN.setEditable(false);     
        tfChiTiet_MaSP.setText("");
        tfChiTiet_MaHD.setText("");
        tfChiTiet_SL.setText("");
        tfChiTiet_Gia.setText("");
        tfChiTiet_Gia1.setText("");
    }
    // Tạo mới phiếu nhập
    private void btnThongTin_TaoMoiMouseClicked(MouseEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn tạo mới phiếu nhập không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {    
            TongTien = 0;           
            try {
                String newMaPN = list_PN.getNewMaPNFromDB();
                tfThongTin_MaPN.setText(newMaPN);
                JOptionPane.showMessageDialog(this, "Tạo mới phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                tfThongTin_MaPN.setText("PN001");
                JOptionPane.showMessageDialog(this, "Tạo mới phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            
            cbbThongTin_MaNCC.setSelectedIndex(0);
            tfThongTin_MaNV.setText(Memory.maNV);       
            tfThongTin_TongTien.setText("");  
            tfChiTiet_MaSP.setText("");
            tfChiTiet_MaHD.setText(tfThongTin_MaPN.getText());
            tfChiTiet_SL.setText("");
            tfChiTiet_Gia.setText("");
            tfChiTiet_Gia1.setText("");
            
            String s = LocalDateTime.now().getDayOfMonth() + " " + LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth() ;
            Date date = new Date(s);
            calendarThongTin.setDate(date);
            tfThongTin_MaPN.requestFocus();
            
            anHienCacNut(false);
            btnThongTin_Them.setEnabled(true);
            actionButtondisplayChiTiet(tfThongTin_MaPN.getText());
        }
    }
    // Thêm phiếu nhập
    private void btnThongTin_ThemActionPerformed(ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm phiếu nhập không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                list_PN.docDB();

                String[] s = String.valueOf(calendarThongTin.getDate()).split("\\ ");
                String NgayBan = s[2] + " " + s[1] + " " + s[5];
                
                phieunhap.setStrMaPN(tfThongTin_MaPN.getText());
                phieunhap.setStrMaNCC((String) cbbThongTin_MaNCC.getSelectedItem());
                phieunhap.setStrMaNV(tfThongTin_MaNV.getText());
                phieunhap.setStrNgayNhap(NgayBan);
                if (tfThongTin_TongTien.getText().equals("")) tfThongTin_TongTien.setText("0");
                phieunhap.setTongTien(Double.parseDouble(tfThongTin_TongTien.getText()));

                list_PN.them(phieunhap);
                list_PN.docDB();

                JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            tfChiTiet_MaHD.setText(phieunhap.getStrMaPN());
            tfChiTiet_MaSP.requestFocus();
            tfChiTiet_MaHD.setText(tfThongTin_MaPN.getText());
            tfThongTin_MaPN.setText("");
            tfThongTin_TongTien.setText("");
            cbbThongTin_MaNCC.setSelectedIndex(0);
            actionButtondisplay();
            anHienCacNut(false);
            btnChiTiet_Them.setEnabled(true);
        }
    }
    // Sửa phiếu nhập
    private void btnThongTin_SuaMouseClicked(MouseEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa phiếu nhập không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            if (!btnThongTin_Sua.isEnabled()) return; // Không cho chạy nếu nút bị disable
                try {
                String[] s = String.valueOf(calendarThongTin.getDate()).split("\\ ");
                String NgayBan = s[2] + " " + s[1] + " " + s[5];
                
                phieunhap.setStrMaPN(tfThongTin_MaPN.getText());
                phieunhap.setStrMaNCC((String) cbbThongTin_MaNCC.getSelectedItem());
                phieunhap.setStrMaNV(tfThongTin_MaNV.getText());
                phieunhap.setStrNgayNhap(NgayBan);
                phieunhap.setTongTien(Double.parseDouble(tfThongTin_TongTien.getText()));

                list_PN.sua(phieunhap);
                JOptionPane.showMessageDialog(this, "Sửa phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Sửa phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            actionButtondisplay();
            actionButtondisplayChiTiet(phieunhap.getStrMaPN());
            if (!tfThongTin_MaPN.getText().equals("")) {
                tfThongTin_MaPN.setText("");
                tfThongTin_TongTien.setText("");
                cbbThongTin_MaNCC.setSelectedIndex(0);
            }
        }
    }
    // Xóa phiếu nhập
    private void btnThongTin_XoaMouseClicked(MouseEvent evt) {
        if (!btnThongTin_Xoa.isEnabled()) return; // Không cho chạy nếu nút bị disable

        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa phiếu nhập không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            PhieuNhapDTO pn = new PhieuNhapDTO();
            pn.setStrMaPN(tfThongTin_MaPN.getText());
            
            try {
                list_PN.xoa(pn);
                list_PN.docDB();
                JOptionPane.showMessageDialog(this, "Xóa phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Xóa phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            actionButtondisplay();
            tfThongTin_MaPN.setText("");
            tfThongTin_TongTien.setText("");
            cbbThongTin_MaNCC.setSelectedIndex(0);
        }
    }

    private void tfChiTiet_SLActionPerformed(ActionEvent evt) {
    }
    // Hiển thị thông tin chi tiết phiếu nhập
    private void tblThongTin1MouseClicked(MouseEvent evt) {
        int  i=tblThongTin1.getSelectedRow();    
        tfChiTiet_MaSP.setText(tblThongTin1.getModel().getValueAt(i, 0).toString());
        tfChiTiet_MaHD.setText(tblThongTin1.getModel().getValueAt(i, 1).toString());
        tfChiTiet_SL.setText(tblThongTin1.getModel().getValueAt(i, 2).toString());
        tfChiTiet_Gia.setText(tblThongTin1.getModel().getValueAt(i, 3).toString());

        SanPhamDTO sp = list_SanPham.getInfor(tfChiTiet_MaSP.getText());
        if (sp != null && !sp.getStrMaGiay().equals("null")) {
            tfChiTiet_Gia1.setText(String.valueOf(sp.getiGia()));
        } else {
            tfChiTiet_Gia1.setText("");
        }
        
        anHienCacNut(false);
        // Nếu phiếu nhập chưa qua ngày thì mới cho hiển thị các nút chỉnh sửa
        if (cachamquantrong.kiemTraDate(date1, phieunhap.getStrNgayNhap())) {
            btnChiTiet_TaoMoi.setEnabled(true);
            btnChiTiet_Sua.setEnabled(true);
            btnChiTiet_Xoa.setEnabled(true);
            
            tfChiTiet_SL.setEditable(true);
            tfChiTiet_Gia.setEditable(true);
        }
    }
    // Tạo mới chi tiết phiếu nhập
    private void btnChiTiet_TaoMoiActionPerformed(ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn tạo mới chi tiết phiếu nhập không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                tfChiTiet_MaSP.setText("");
                tfChiTiet_MaHD.setText(phieunhap.getStrMaPN());
                tfChiTiet_Gia.setText("");
                tfChiTiet_SL.setText("");
                tfChiTiet_Gia1.setText("");

                anHienCacNut(false);
                btnChiTiet_Them.setEnabled(true);

                JOptionPane.showMessageDialog(this, "Tạo mới chi tiết phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Tạo mới chi tiết phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    // Thêm chi tiết phiếu nhập
    private void btnChiTiet_ThemActionPerformed(ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm chi tiết phiếu nhập không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            boolean thanhCong = false;

            try {
                CacHamQuanTrong chqt = new CacHamQuanTrong();

                // Kiểm tra số lượng
                String soLuongStr = tfChiTiet_SL.getText().trim();
                if (!chqt.kiemTraDuLieuSo(soLuongStr)) return;

                // Kiểm tra giá nhập và giá bán
                int giaNhap = Integer.parseInt(tfChiTiet_Gia.getText().trim());
                int giaBan = Integer.parseInt(tfChiTiet_Gia1.getText().trim());
                if (!chqt.kiemTraGiaHopLe(giaNhap, giaBan)) return;

                // Thực hiện thêm
                thanhCong = xulyThemChiTietPN();

                if (thanhCong) {
                    JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng giá!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            if (thanhCong) {
                anHienCacNut(false);
                btnChiTiet_Them.setEnabled(true);
            }
        }
    }

    // Xử lý thêm chi tiết phiếu nhập
    private boolean xulyThemChiTietPN() {    
        try {
            list_ChiTietPN.docDB();
            ChiTietPNDTO ctpn = new ChiTietPNDTO();
            ctpn.setStrMaGiay(tfChiTiet_MaSP.getText());
            ctpn.setStrMaPN(tfChiTiet_MaHD.getText());
            ctpn.setiGiaNhap(Integer.parseInt(tfChiTiet_Gia.getText()));
            ctpn.setiSoLuong(Integer.parseInt(tfChiTiet_SL.getText()));

            boolean flag = list_ChiTietPN.kiemTraKhoachinh(ctpn);
            if (flag) {
                JOptionPane.showMessageDialog(this, "Bị trùng mã SP!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false; // thất bại
            }

            // Nếu không trùng
            list_ChiTietPN.them(ctpn);
            chinhSuaSoLuong_SP(ctpn.getStrMaGiay(), ctpn.getiSoLuong(), ctpn.getiGiaNhap(), false);

            list_ChiTietPN.docDB();
            actionButtondisplayChiTiet(phieunhap.getStrMaPN());

            tinhTongTien(Integer.parseInt(tfChiTiet_SL.getText()), Integer.parseInt(tfChiTiet_Gia.getText()), true);
            phieunhap.setTongTien(TongTien);
            list_PN.sua(phieunhap);
            actionButtondisplay();

            // Xóa các ô nhập
            tfChiTiet_MaSP.setText("");
            tfChiTiet_SL.setText("");
            tfChiTiet_Gia.setText("");
            tfChiTiet_Gia1.setText("");
            tfChiTiet_MaSP.requestFocus();

            tfChiTiet_MaSP.setEditable(true);
            tfChiTiet_SL.setEditable(true);
            tfChiTiet_Gia.setEditable(true);
            tfChiTiet_Gia1.setEditable(true);

            return true; // thành công
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm chi tiết phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    // Sửa chi tiết phiếu nhập
    private void btnChiTiet_SuaActionPerformed(ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa chi tiết phiếu nhập không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                int i = tblThongTin1.getSelectedRow();

                CacHamQuanTrong chqt = new CacHamQuanTrong();

                // Kiểm tra số lượng
                String soLuongStr = tfChiTiet_SL.getText().trim();
                if (!chqt.kiemTraDuLieuSo(soLuongStr)) return;

                // Kiểm tra giá nhập và giá bán
                int giaNhap = Integer.parseInt(tfChiTiet_Gia.getText().trim());
                int giaBan = Integer.parseInt(tfChiTiet_Gia1.getText().trim());
                if (!chqt.kiemTraGiaHopLe(giaNhap, giaBan)) return;

                // Lấy dữ liệu cũ và cập nhật
                ChiTietPNDTO ctpn = new ChiTietPNDTO();
                int iSL_SP_LucDau = Integer.parseInt(tblThongTin1.getModel().getValueAt(i, 2).toString());

                ctpn.setStrMaGiay(tfChiTiet_MaSP.getText());
                ctpn.setStrMaPN(tfChiTiet_MaHD.getText());
                ctpn.setiGiaNhap(giaNhap);
                ctpn.setiSoLuong(Integer.parseInt(soLuongStr));

                // Cập nhật dữ liệu
                tinhTongTien(iSL_SP_LucDau, Integer.parseInt(tblThongTin1.getModel().getValueAt(i, 3).toString()), false);
                list_ChiTietPN.sua(ctpn);
                actionButtondisplayChiTiet(phieunhap.getStrMaPN());
                tinhTongTien(ctpn.getiSoLuong(), ctpn.getiGiaNhap(), true);

                // Cập nhật tồn kho
                int iCount = iSL_SP_LucDau - ctpn.getiSoLuong();
                chinhSuaSoLuong_SP(ctpn.getStrMaGiay(), iCount, ctpn.getiGiaNhap(), true);

                // Cập nhật tổng tiền phiếu nhập
                phieunhap.setTongTien(TongTien);
                list_PN.sua(phieunhap);
                actionButtondisplay();

                JOptionPane.showMessageDialog(this, "Sửa chi tiết phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

                // Xóa ô nhập
                tfChiTiet_Gia.setText("");
                tfChiTiet_MaSP.setText("");
                tfChiTiet_MaHD.setText("");
                tfChiTiet_SL.setText("");
                tfChiTiet_Gia1.setText("");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng giá!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Sửa chi tiết phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Xóa chi tiết phiếu nhập
    private void btnChiTiet_XoaActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chi tiết phiếu nhập không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                ChiTietPNDTO ctpn = new ChiTietPNDTO();
                ctpn.setStrMaGiay(tfChiTiet_MaSP.getText());
                ctpn.setStrMaPN(tfChiTiet_MaHD.getText());
                ctpn.setiSoLuong(Integer.parseInt(tfChiTiet_SL.getText()));
                ctpn.setiGiaNhap(Integer.parseInt(tfChiTiet_Gia.getText()));

                tinhTongTien(Integer.parseInt(tfChiTiet_SL.getText()), Integer.parseInt(tfChiTiet_Gia.getText()), false);
                phieunhap.setTongTien(TongTien);
                list_PN.sua(phieunhap);
                actionButtondisplay();

                list_ChiTietPN.xoa(ctpn);
                list_ChiTietPN.docDB();

                chinhSuaSoLuong_SP(ctpn.getStrMaGiay(), ctpn.getiSoLuong(), 0, true);
                actionButtondisplayChiTiet(phieunhap.getStrMaPN());

                JOptionPane.showMessageDialog(this, "Xóa chi tiết phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

                tfChiTiet_Gia.setText("");
                tfChiTiet_MaSP.setText("");
                tfChiTiet_MaHD.setText("");
                tfChiTiet_SL.setText("");
                tfChiTiet_Gia1.setText("");
            } catch (Exception ex) {
                Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Xóa chi tiết phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Tìm kiếm phiếu nhập
    private void btnTimKiem_TimKiemMouseClicked(MouseEvent evt) {
        try {
            list_PN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!tfTimKiem_MaPN1.getText().equals(""))
            list_PN.setList_PN( list_PN.timKiem_MaPN(tfTimKiem_MaPN1.getText()));
        if (!tfTimKiem_MaNCC1.getText().equals(""))
            list_PN.setList_PN( list_PN.timKiem_MaNCC(tfTimKiem_MaNCC1.getText()) );
        if (!tfTimKiem_MaNV1.getText().equals(""))
            list_PN.setList_PN( list_PN.timKiem_MaNV(tfTimKiem_MaNV1.getText()) );
        if (!tfTimKiem_TongTien1.getText().equals(""))
            list_PN.setList_PN( list_PN.timKiem_TongTien(tfTimKiem_TongTien1.getText()) );
        actionButtondisplay();
    }
    // Hủy tìm kiếm phiếu nhập
    private void btnTimKiem_HuyTimKiemMouseClicked(MouseEvent evt) {
        try {
            list_PN = new PhieuNhapBUS();
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_PN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        actionButtondisplay();
    }
    // Xóa trắng ô tìm kiếm phiếu nhập
    private void btnTimKiem_TrongMouseClicked(MouseEvent evt) {
        try{
            list_PN = new PhieuNhapBUS();
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tfTimKiem_MaPN1.setText("");
        tfTimKiem_MaNCC1.setText("");
        tfTimKiem_MaNV1.setText("");
        tfTimKiem_TongTien1.setText("");
    }

    // Tìm kiếm chi tiết phiếu nhập
    private void jButton6MouseClicked(MouseEvent evt) {
        try {
            list_ChiTietPN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!tfChiTiet_MaSP2.getText().equals(""))
               list_ChiTietPN.setList_PN(list_ChiTietPN.timKiem_MaSP(tfChiTiet_MaSP2.getText()
                    , tfChiTiet_MaSP3.getText()) );
        
        if (!tfChiTiet_SL2.getText().equals(""))
                list_ChiTietPN.setList_PN(list_ChiTietPN.timKiem_SoLuong(Integer.parseInt(tfChiTiet_SL2.getText())
                        , Integer.parseInt(tfChiTiet_SL3.getText())) );
        if (tfThongTin_MaPN.getText().equals(""))
            actionButtondisplayChiTiet();
        else
            actionButtondisplayChiTiet(tfThongTin_MaPN.getText());
    }
    // Hủy tìm kiếm chi tiết phiếu nhập
    private void jButton4MouseClicked(MouseEvent evt) {
        try {
            list_ChiTietPN.docDB();
            if (tfThongTin_MaPN.getText().equals(""))
                actionButtondisplayChiTiet();
            else
                actionButtondisplayChiTiet(tfThongTin_MaPN.getText());
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    // Xóa trắng ô tìm kiếm chi tiết phiếu nhập
    private void jButton5MouseClicked(MouseEvent evt) {
        tfChiTiet_MaSP2.setText("");
        tfChiTiet_MaSP3.setText("");
        tfChiTiet_SL2.setText("");
        tfChiTiet_SL3.setText("");
    }

    // Hiển thị danh sách chi tiết phiếu nhập
    private void lblChiTiet_TatcaMouseClicked(MouseEvent evt) {
        tfThongTin_MaPN.setText("");
        cbbThongTin_MaNCC.setSelectedIndex(0);
        tfThongTin_MaNV.setText(Memory.maNV); 
        tfThongTin_TongTien.setText("");
        
        
        String s = LocalDateTime.now().getDayOfMonth() + " " + LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth() ;
        
        Date date = new Date(s);
        calendarThongTin.setDate(date);
        
        actionButtondisplayChiTiet();
    }


    /**
     * tong tien = tong tien + So Luong * Gia Ban * ti le khuyen mai
     * @param iSoLuong
     * @param iGiaBan
     * @param hanhDong true nếu là xóa và false nếu là thêm
     */
    // Tính tổng tiền
    private double tinhTongTien(int iSoLuong, int iGiaBan, boolean hanhDong) {
        if (hanhDong)
            TongTien += iSoLuong * iGiaBan;
        else
             TongTien -= iSoLuong * iGiaBan;
        return TongTien;
    }
    
    // Kiểm tra mã sản phẩm
    private boolean kiemtraMaSP() {
        SanPhamDTO sp = new SanPhamDTO();
        sp = list_SanPham.getInfor(tfChiTiet_MaSP.getText());
        
        if (sp.getStrMaGiay().equals("null"))
            return false;
        
        tfChiTiet_SL.setText(String.valueOf(sp.getiSoLuong()));
        tfChiTiet_Gia.setText(String.valueOf(sp.getiGia()));
        
        return true;
    }
    
    // Chỉnh sửa số lượng sản phẩm
    private void chinhSuaSoLuong_SP(String strMaSP, int iSoLuong, int GiaNhap, boolean hanhdong) {
        SanPhamDTO sp = new SanPhamDTO();
        
        sp = list_SanPham.getInfor(strMaSP);
                        
        if (!hanhdong) {
            sp.setiSoLuong( sp.getiSoLuong() + iSoLuong );
        }
        else {
            sp.setiSoLuong( sp.getiSoLuong() - iSoLuong );
        }        
        try {
            list_SanPham.sua(sp);
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Xử lý sự kiện cho textfield chi tiết
    private void actionTFChiTiet() {
        tfChiTiet_MaSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key){
                if ( key.getKeyCode() == KeyEvent.VK_ENTER ) {
                    tfChiTiet_SL.requestFocus();
                    kiemtraMaSP();
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

    private void tfChiTiet_MaSPMouseClicked(java.awt.event.MouseEvent evt) {
        tfChiTiet_Gia.setText("");
        tfChiTiet_SL.setText("");
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
        tfChiTiet_Gia1.setText(String.valueOf(Memory.giaSP));

        tfChiTiet_SL.setEditable(true);
        tfChiTiet_Gia.setEditable(true);
    }

    private void lblChiTiet_3cham1MouseClicked(java.awt.event.MouseEvent evt) {
        ChonSanPham csp = new ChonSanPham(this, true);
        csp.setVisible(true);
        tfChiTiet_MaSP2.setText(Memory.maSP);
    }

    private void lblChiTiet_3cham2MouseClicked(java.awt.event.MouseEvent evt) {
        ChonSanPham csp = new ChonSanPham(this, true);
        csp.setVisible(true);
        tfChiTiet_MaSP3.setText(Memory.maSP);
    }

    
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
        Vector<String> header = new Vector<>();
        header.add("Mã PN");
        header.add("Mã NCC");
        header.add("Mã NV");
        header.add("Ngày Nhập");
        header.add("Tổng Tiền");

        model = new DefaultTableModel(header, 0) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }
    private void actionButtondisplay() {
        createVectorHeader();
        for (int i = 0; i < list_PN.getNumb(); i++) {
            PhieuNhapDTO pn = list_PN.getInfor(i);

            // Chỉ hiển thị phiếu chưa bị xóa
            if (pn.getDeleted() == 0) {
                Vector<Object> row = new Vector<>();
                row.add(pn.getStrMaPN());
                row.add(pn.getStrMaNCC());
                row.add(pn.getStrMaNV());
                row.add(pn.getStrNgayNhap());
                row.add(pn.getTongTien());
                model.addRow(row);
            }
        }
        tblThongTin.setModel(model);
    }

    private void createVectorHeaderChiTiet() {
        Vector<String> header = new Vector<>();
        header.add("Mã Sản Phẩm");
        header.add("Mã Phiếu Nhập");
        header.add("Số Lượng");
        header.add("Giá Nhập");
        
        modelChiTiet = new DefaultTableModel(header, 0) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }

    
    private void actionButtondisplayChiTiet() {
        actionButtondisplayChiTiet("null");
    }
    
    private void actionButtondisplayChiTiet(String strMaHD) {
        createVectorHeaderChiTiet();
        for (int i = 0; i < list_ChiTietPN.getNumbChiTietPN(); i++) {
            ChiTietPNDTO ctpn = list_ChiTietPN.getInfor(i);
            if (ctpn.getStrMaPN().equalsIgnoreCase(strMaHD) || "null".equals(strMaHD)) {
                Vector<Object> row = new Vector<>();
                row.add(ctpn.getStrMaGiay());
                row.add(ctpn.getStrMaPN());
                row.add(ctpn.getiSoLuong());
                row.add(ctpn.getiGiaNhap());
                modelChiTiet.addRow(row);
            }
        }
        tblThongTin1.setModel(modelChiTiet);
    }

    private JPanel createPanel_LblLeft(String[] strArr_Left) {
        JPanel panel = new JPanel();
        listLblTop = new JLabel[strArr_Left.length];

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(44, 62, 80));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(52, 73, 94))); // Right border

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

            JLabel lblAvatar = new JLabel();
            ImageIcon avatarIcon = new ImageIcon("./src/ShoesManager/images/Avatar/130_100/" + nv.getStrAnh() + ".png");
            lblAvatar.setIcon(avatarIcon);
            lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblAvatar.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
            lblAvatar.setOpaque(false);
            profilePanel.add(lblAvatar);
            profilePanel.add(Box.createVerticalStrut(12));

            JLabel lblName = new JLabel("Quản trị Viên");
            lblName.setFont(new Font("Segoe UI", Font.BOLD, 17));
            lblName.setForeground(Color.WHITE);
            lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
            profilePanel.add(lblName);
            profilePanel.add(Box.createVerticalStrut(6));

            JLabel lblID = new JLabel("ID: " + nv.getstrMaNV());
            lblID.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblID.setForeground(new Color(200, 200, 200));
            lblID.setAlignmentX(Component.CENTER_ALIGNMENT);
            profilePanel.add(lblID);
            profilePanel.add(Box.createVerticalStrut(4));

            JLabel lblRole = new JLabel(nv.getStrChucVu());
            lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblRole.setForeground(new Color(76, 175, 80));
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
        String currentMenu = "Nhập hàng";

        for (int i = 0; i < strArr_Left.length; i++) {
            String menuName = strArr_Left[i];
            String icon = "📋";
            for (int j = 0; j < menuNames.length; j++) {
                if (menuNames[j].equals(menuName)) {
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
                rowPanel.setBackground(new Color(52, 152, 219));
            }

            final String menuItemName = strArr_Left[i];
            rowPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
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

        // Logout Button
        mainPanel.add(Box.createVerticalGlue());
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        logoutPanel.setBackground(new Color(44, 62, 80));
        logoutPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogout.setBackground(new Color(231, 76, 60));
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
                    Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
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

        try {
            switch (name) {
                case "Khuyến mãi" -> new KhuyenMai().setVisible(true);
                case "Hóa đơn" -> new HoaDon().setVisible(true);
                case "Nhập hàng" -> new PhieuNhap().setVisible(true);
                case "Hàng hóa" -> new Sanpham().setVisible(true);
                case "Thống kê" -> new ThongKe().setVisible(true);
                default -> { return; }
            }
            setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   @Override
    public void mouseEntered(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        if (src.getParent() instanceof JPanel) {
            JPanel parent = (JPanel) src.getParent();
            if (!parent.getBackground().equals(new Color(52, 152, 219))) {
                parent.setBackground(new Color(60, 80, 100));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        if (src.getParent() instanceof JPanel) {
            JPanel parent = (JPanel) src.getParent();
            String menuName = src.getName();
            if (menuName != null && menuName.equals("Nhập hàng")) {
                parent.setBackground(new Color(52, 152, 219));
            } else {
                parent.setBackground(new Color(44, 62, 80));
            }
        }
    }

    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PhieuNhap().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}