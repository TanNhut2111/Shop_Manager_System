package ShoesManager.GUI;

import ShoesManager.BUS.*;
import ShoesManager.DTO.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

public class KhuyenMai extends javax.swing.JFrame  implements MouseListener{

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.ButtonGroup buttonGroup_MauNen;
    private com.toedter.calendar.JDateChooser calendarThongTin;
    private com.toedter.calendar.JDateChooser calendarThongTin1;
    private javax.swing.JComboBox<String> cbbThongTin_DK;
    private javax.swing.JComboBox<String> cbbThongTin_Loai;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblChiTiet_3cham;
    private javax.swing.JLabel lblChiTiet_3cham1;
    private javax.swing.JLabel lblChiTiet_3cham2;
    private javax.swing.JLabel lblChiTiet_ChiTietHoaDon;
    private javax.swing.JLabel lblChiTiet_MaHD;
    private javax.swing.JLabel lblChiTiet_MaSP;
    private javax.swing.JLabel lblChiTiet_MaSP1;
    private javax.swing.JLabel lblChiTiet_SoLuong2;
    private javax.swing.JLabel lblChiTiet_Tatca;
    private javax.swing.JLabel lblChiTiet_TiLeKM;
    private javax.swing.JLabel lblMuiTen;
    private javax.swing.JLabel lblMuiTen1;
    private javax.swing.JLabel lblThongTin_MaHD;
    private javax.swing.JLabel lblThongTin_MaHD1;
    private javax.swing.JLabel lblThongTin_MaKH;
    private javax.swing.JLabel lblThongTin_MaKM;
    private javax.swing.JLabel lblThongTin_MaNV;
    private javax.swing.JLabel lblThongTin_MaNV1;
    private javax.swing.JLabel lblThongTin_NgayBan;
    private javax.swing.JLabel lblThongTin_NgayBan1;
    private javax.swing.JLabel lblThongTin_ThongTin;
    private javax.swing.JLabel lblThongTin_TimKiem;
    private javax.swing.JMenu menuTaiKhoan;
    private javax.swing.JMenuItem menuTaiKhoan_DangXuat;
    private javax.swing.JMenuItem menuTaiKhoan_ThongTin;
    private javax.swing.JMenu menuTroVe;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JPanel pCenter;
    private javax.swing.JPanel pChiTiet;
    private javax.swing.JPanel pChiTietSanPham;
    private javax.swing.JPanel pChiTietTimKiem;
    private javax.swing.JPanel pThongTin;
    private javax.swing.JPanel pThongTin_ThongTin;
    private javax.swing.JPanel pThongTin_TimKiem;
    private javax.swing.JPanel pTop;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTable tblThongTin1;
    private javax.swing.JTextField tfChiTiet_MaKM;
    private javax.swing.JTextField tfChiTiet_MaSP;
    private javax.swing.JTextField tfChiTiet_MaSP2;
    private javax.swing.JTextField tfChiTiet_MaSP3;
    private javax.swing.JTextField tfChiTiet_SL2;
    private javax.swing.JTextField tfChiTiet_SL3;
    private javax.swing.JTextField tfChiTiet_TiLeKM;
    private javax.swing.JTextField tfThongTin_MAKM;
    private javax.swing.JTextField tfThongTin_TenKM;
    private javax.swing.JTextField tfTimKiem_MaKM;
    private javax.swing.JTextField tfTimKiem_TenCT;
    // End of variables declaration//GEN-END:variables

    public KhuyenMai() throws Exception {
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
       
        // tfThongTin_TenKM.setText(Memory.maNV);
        
        tblThongTin.setAutoCreateRowSorter(true);
        tblThongTin1.setAutoCreateRowSorter(true);
    }
    
    private JLabel[] listLblTop;
    private String[] strArr_Top;
    private KhuyenMaiBUS list_KM;
    private KhuyenMaiDTO khuyenmai;
    private ChiTietKMBUS list_ChiTietKM;
    private SanPhamBUS list_SanPham;
    private DefaultTableModel model, modelChiTiet;
    private CacHamQuanTrong cachamquantrong;
            
    public void init() throws Exception {
        
        list_KM = new KhuyenMaiBUS();
        list_ChiTietKM = new ChiTietKMBUS();
        list_SanPham = new SanPhamBUS();
        khuyenmai = new KhuyenMaiDTO();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        pCenter = new javax.swing.JPanel();
        pThongTin = new javax.swing.JPanel();
        lblThongTin_ThongTin = new javax.swing.JLabel();
        lblThongTin_TimKiem = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        pThongTin_ThongTin = new javax.swing.JPanel();
        lblThongTin_MaHD = new javax.swing.JLabel();
        lblThongTin_MaKH = new javax.swing.JLabel();
        lblThongTin_MaNV = new javax.swing.JLabel();
        lblThongTin_MaKM = new javax.swing.JLabel();
        lblThongTin_NgayBan = new javax.swing.JLabel();
        btnThongTin_Them = new javax.swing.JButton();
        btnThongTin_Xoa = new javax.swing.JButton();
        tfThongTin_MAKM = new javax.swing.JTextField();
        tfThongTin_TenKM = new javax.swing.JTextField();
        calendarThongTin = new com.toedter.calendar.JDateChooser();
        btnThongTin_Sua = new javax.swing.JButton();
        btnThongTin_Trong = new javax.swing.JButton();
        cbbThongTin_Loai = new javax.swing.JComboBox<>();
        cbbThongTin_DK = new javax.swing.JComboBox<>();
        lblThongTin_NgayBan1 = new javax.swing.JLabel();
        calendarThongTin1 = new com.toedter.calendar.JDateChooser();
        pThongTin_TimKiem = new javax.swing.JPanel();
        lblThongTin_MaHD1 = new javax.swing.JLabel();
        lblThongTin_MaNV1 = new javax.swing.JLabel();
        btnTimKiem_TimKiem = new javax.swing.JButton();
        btnTimKiem_HuyTimKiem = new javax.swing.JButton();
        tfTimKiem_MaKM = new javax.swing.JTextField();
        tfTimKiem_TenCT = new javax.swing.JTextField();
        btnTimKiem_Trong1 = new javax.swing.JButton();
        pChiTiet = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThongTin1 = new javax.swing.JTable();
        lblChiTiet_ChiTietHoaDon = new javax.swing.JLabel();
        pChiTietSanPham = new javax.swing.JPanel();
        btnChiTiet_Xoa = new javax.swing.JButton();
        btnChiTiet_Sua = new javax.swing.JButton();
        tfChiTiet_MaSP = new javax.swing.JTextField();
        tfChiTiet_MaKM = new javax.swing.JTextField();
        lblChiTiet_MaSP = new javax.swing.JLabel();
        lblChiTiet_MaHD = new javax.swing.JLabel();
        btnChiTiet_Them = new javax.swing.JButton();
        lblChiTiet_3cham = new javax.swing.JLabel();
        btnChiTiet_TaoMoi = new javax.swing.JButton();
        lblChiTiet_TiLeKM = new javax.swing.JLabel();
        tfChiTiet_TiLeKM = new javax.swing.JTextField();
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
        pTop = createPanel_LblLeft(strArr_Top);

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

        pCenter.setPreferredSize(new java.awt.Dimension(1280, 600));
        pThongTin.setPreferredSize(new java.awt.Dimension(340, 580));
        pThongTin.setVerifyInputWhenFocusTarget(false);

        lblThongTin_ThongTin.setForeground(new java.awt.Color(255, 255, 255));
        lblThongTin_ThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ShoesManager/images/Button Menu/210_70/Tìm kiếm.png"))); // NOI18N
        lblThongTin_ThongTin.setMaximumSize(new java.awt.Dimension(210, 131));
        lblThongTin_ThongTin.setMinimumSize(new java.awt.Dimension(210, 131));
        lblThongTin_ThongTin.setName("pThongTin_ThongTin"); // NOI18N
        lblThongTin_ThongTin.setPreferredSize(new java.awt.Dimension(210, 131));
        lblThongTin_ThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
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
                "MAKM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTin.setGridColor(new java.awt.Color(102, 255, 102));
        tblThongTin.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblThongTin.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThongTin);

        pThongTin_ThongTin.setPreferredSize(new java.awt.Dimension(405, 280));

        lblThongTin_MaHD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaHD.setText("Mã khuyến mãi");

        lblThongTin_MaKH.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaKH.setText("Loại chương trình");

        lblThongTin_MaNV.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaNV.setText("Tên chương trình");

        lblThongTin_MaKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaKM.setText("Điều kiện");

        lblThongTin_NgayBan.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_NgayBan.setText("Ngày bắt đầu");

        btnThongTin_Them.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThongTin_Them.setText("Thêm");
        btnThongTin_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTin_ThemActionPerformed(evt);
            }
        });

        btnThongTin_Xoa.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThongTin_Xoa.setText("Xóa");
        btnThongTin_Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongTin_XoaMouseClicked(evt);
            }
        });

        tfThongTin_MAKM.setEditable(false);
        tfThongTin_MAKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfThongTin_MAKM.setPreferredSize(new java.awt.Dimension(170, 28));
        tfThongTin_MAKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfThongTin_MAKMMouseClicked(evt);
            }
        });
        tfThongTin_MAKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfThongTin_MAKMActionPerformed(evt);
            }
        });

        tfThongTin_TenKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfThongTin_TenKM.setPreferredSize(new java.awt.Dimension(170, 28));

        calendarThongTin.setDate(new java.util.Date(1590147174000L));
        calendarThongTin.setDateFormatString("dd / MM / yyyy");
        calendarThongTin.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        btnThongTin_Sua.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThongTin_Sua.setText("Sửa");
        btnThongTin_Sua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongTin_SuaMouseClicked(evt);
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

        cbbThongTin_Loai.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbbThongTin_Loai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loại 1", "Loại 2", "Loại 3" }));

        cbbThongTin_DK.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbbThongTin_DK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bình thường", "đặc biệt", "vip" }));

        lblThongTin_NgayBan1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_NgayBan1.setText("Ngày kết thúc");

        calendarThongTin1.setDate(new java.util.Date(1590147174000L));
        calendarThongTin1.setDateFormatString("dd / MM / yyyy");
        calendarThongTin1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout pThongTin_ThongTinLayout = new javax.swing.GroupLayout(pThongTin_ThongTin);
        pThongTin_ThongTin.setLayout(pThongTin_ThongTinLayout);
        pThongTin_ThongTinLayout.setHorizontalGroup(
            pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                        .addComponent(btnThongTin_Them)
                        .addGap(18, 18, 18)
                        .addComponent(btnThongTin_Xoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnThongTin_Sua)
                        .addGap(18, 18, 18)
                        .addComponent(btnThongTin_Trong))
                    .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                        .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThongTin_NgayBan)
                            .addComponent(lblThongTin_MaHD)
                            .addComponent(lblThongTin_MaKH)
                            .addComponent(lblThongTin_MaNV)
                            .addComponent(lblThongTin_MaKM)
                            .addComponent(lblThongTin_NgayBan1))
                        .addGap(42, 42, 42)
                        .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfThongTin_TenKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfThongTin_MAKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calendarThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbThongTin_Loai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbThongTin_DK, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calendarThongTin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pThongTin_ThongTinLayout.setVerticalGroup(
            pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_ThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaHD)
                    .addComponent(tfThongTin_MAKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaKH)
                    .addComponent(cbbThongTin_Loai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaNV)
                    .addComponent(tfThongTin_TenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTin_MaKM)
                    .addComponent(cbbThongTin_DK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblThongTin_NgayBan)
                    .addComponent(calendarThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblThongTin_NgayBan1)
                    .addComponent(calendarThongTin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pThongTin_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThongTin_Them)
                    .addComponent(btnThongTin_Xoa)
                    .addComponent(btnThongTin_Sua)
                    .addComponent(btnThongTin_Trong))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pThongTin_TimKiem.setPreferredSize(new java.awt.Dimension(405, 280));

        lblThongTin_MaHD1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaHD1.setText("Mã khuyến mãi");

        lblThongTin_MaNV1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblThongTin_MaNV1.setText("Tên chương trình");

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

        tfTimKiem_MaKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfTimKiem_MaKM.setPreferredSize(new java.awt.Dimension(170, 28));

        tfTimKiem_TenCT.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfTimKiem_TenCT.setPreferredSize(new java.awt.Dimension(170, 28));

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

        javax.swing.GroupLayout pThongTin_TimKiemLayout = new javax.swing.GroupLayout(pThongTin_TimKiem);
        pThongTin_TimKiem.setLayout(pThongTin_TimKiemLayout);
        pThongTin_TimKiemLayout.setHorizontalGroup(
            pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnTimKiem_TimKiem)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem_HuyTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem_Trong1))
                    .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThongTin_MaHD1)
                            .addComponent(lblThongTin_MaNV1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTimKiem_TenCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTimKiem_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pThongTin_TimKiemLayout.setVerticalGroup(
            pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tfTimKiem_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTimKiem_TenCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThongTin_MaNV1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE))
                    .addGroup(pThongTin_TimKiemLayout.createSequentialGroup()
                        .addComponent(lblThongTin_MaHD1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pThongTin_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem_TimKiem)
                    .addComponent(btnTimKiem_HuyTimKiem)
                    .addComponent(btnTimKiem_Trong1))
                .addContainerGap())
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
        );

        pChiTiet.setPreferredSize(new java.awt.Dimension(880, 580));

        tblThongTin1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã Khuyến Mãi", "Tỉ Lệ KM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTin1.setGridColor(new java.awt.Color(102, 255, 102));
        tblThongTin1.setSelectionBackground(new java.awt.Color(204, 204, 204));
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
        }

        lblChiTiet_ChiTietHoaDon.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_ChiTietHoaDon.setText("Chi Tiết Khuyến Mãi");


        btnChiTiet_Xoa.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnChiTiet_Xoa.setText("Xóa");
        btnChiTiet_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTiet_XoaActionPerformed(evt);
            }
        });

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

        tfChiTiet_MaKM.setEditable(false);
        tfChiTiet_MaKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblChiTiet_MaSP.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_MaSP.setText("Mã Sản Phẩm");

        lblChiTiet_MaHD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_MaHD.setText("Mã Khuyến Mãi");

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

        lblChiTiet_TiLeKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblChiTiet_TiLeKM.setText("Tỉ lệ Khuyến Mãi");

        tfChiTiet_TiLeKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfChiTiet_TiLeKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfChiTiet_TiLeKMMouseClicked(evt);
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
                            .addComponent(lblChiTiet_TiLeKM))
                        .addGap(38, 38, 38)
                        .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfChiTiet_TiLeKM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfChiTiet_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pChiTietSanPhamLayout.createSequentialGroup()
                                .addComponent(tfChiTiet_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(tfChiTiet_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChiTiet_TiLeKM)
                    .addComponent(tfChiTiet_TiLeKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        lblChiTiet_SoLuong2.setText("Tỉ lệ KM");

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

        javax.swing.GroupLayout pChiTietTimKiemLayout = new javax.swing.GroupLayout(pChiTietTimKiem);
        pChiTietTimKiem.setLayout(pChiTietTimKiemLayout);
        pChiTietTimKiemLayout.setHorizontalGroup(
            pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pChiTietTimKiemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                        .addComponent(tfChiTiet_MaSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChiTiet_3cham1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMuiTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfChiTiet_MaSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblChiTiet_3cham2))
                    .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                        .addComponent(lblChiTiet_SoLuong2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfChiTiet_SL2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMuiTen1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfChiTiet_SL3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblChiTiet_MaSP1))
                .addGap(171, 171, 171))
        );
        pChiTietTimKiemLayout.setVerticalGroup(
            pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblChiTiet_MaSP1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfChiTiet_MaSP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMuiTen)
                        .addComponent(tfChiTiet_MaSP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblChiTiet_3cham1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChiTiet_3cham2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChiTiet_SoLuong2)
                    .addComponent(lblMuiTen1)
                    .addComponent(tfChiTiet_SL2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfChiTiet_SL3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        lblChiTiet_Tatca.setBackground(new java.awt.Color(255, 51, 51));
        lblChiTiet_Tatca.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_Tatca.setText("Tất cả");
        lblChiTiet_Tatca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChiTiet_TatcaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pChiTietLayout = new javax.swing.GroupLayout(pChiTiet);
        pChiTiet.setLayout(pChiTietLayout);
        pChiTietLayout.setHorizontalGroup(
            pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChiTietLayout.createSequentialGroup()
                .addGroup(pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pChiTietLayout.createSequentialGroup()
                        .addComponent(pChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pChiTietTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addGap(5, 5, 5))
            .addGroup(pChiTietLayout.createSequentialGroup()
                .addGap(20)
                .addComponent(lblChiTiet_ChiTietHoaDon)
                .addGap(170)
                .addComponent(lblChiTiet_Tatca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pChiTietLayout.setVerticalGroup(
            pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pChiTietLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChiTiet_ChiTietHoaDon)
                    .addComponent(lblChiTiet_Tatca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pChiTietTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
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
                    .addComponent(pThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addComponent(pChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
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
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void actionVisiblePanel(){
        pThongTin_ThongTin.setVisible(false);
        pThongTin_TimKiem.setVisible(false);
    }
    
    private void lblThongTin_ThongTinMouseReleased(java.awt.event.MouseEvent evt) {
        actionVisiblePanel();
        pThongTin_ThongTin.setVisible(true);
    }

    private void btnThongTin_TrongActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void tfThongTin_MAKMMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("Click here PLae");
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
        tfChiTiet_TiLeKM.setEditable(bl);
    }
    
    // Hiển thị thông tin khuyến mãi
    private void tblThongTinMouseClicked(java.awt.event.MouseEvent evt) {
        int i = tblThongTin.getSelectedRow();

        tfThongTin_MAKM.setText(tblThongTin.getModel().getValueAt(i, 0).toString());
        cbbThongTin_Loai.setSelectedItem(tblThongTin.getModel().getValueAt(i, 1));
        tfThongTin_TenKM.setText(tblThongTin.getModel().getValueAt(i, 2).toString());
        cbbThongTin_DK.setSelectedItem(tblThongTin.getModel().getValueAt(i, 3));
        Date date = new Date((String) tblThongTin.getModel().getValueAt(i, 4));
        calendarThongTin.setDate(date);
        date = new Date(tblThongTin.getModel().getValueAt(i, 5).toString());
        calendarThongTin1.setDate(date);

        khuyenmai.setStrMaKM(tfThongTin_MAKM.getText());
        khuyenmai.setStrLoaiChuongTrinh((String) cbbThongTin_Loai.getSelectedItem());
        khuyenmai.setStrDieuKien((String) cbbThongTin_DK.getSelectedItem());
        khuyenmai.setStrTenChuongTrinh(tfThongTin_TenKM.getText());
        khuyenmai.setStrNgayBatDau((String) tblThongTin.getModel().getValueAt(i, 4));
        khuyenmai.setStrNgayKetThuc((String) tblThongTin.getModel().getValueAt(i, 5));

        actionButtondisplayChiTiet(tfThongTin_MAKM.getText());

        try {
            HoaDonBUS hoaDonBUS = new HoaDonBUS();
            CacHamQuanTrong helper = new CacHamQuanTrong();

            boolean daSuDung = hoaDonBUS.isMaKMDaSuDung(tfThongTin_MAKM.getText());

            // Kiểm tra hạn khuyến mãi
            boolean conHan = helper.isKhuyenMaiDangApDung(khuyenmai.getStrNgayBatDau(), khuyenmai.getStrNgayKetThuc());

            // Nếu đã hết hạn hoặc đã sử dụng -> ẩn tất cả nút
            if (!conHan || daSuDung) {
                anHienCacNut(false);
            }else{
                btnThongTin_Sua.setEnabled(true);
                btnThongTin_Xoa.setEnabled(true);
                btnChiTiet_TaoMoi.setEnabled(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tfThongTin_MAKM.setEditable(false);
        tfChiTiet_MaSP.setText("");
        tfChiTiet_MaKM.setText("");
        tfChiTiet_TiLeKM.setText("");
    }

    // Tạo mới khuyến mãi
    private void btnThongTin_TrongMouseClicked(java.awt.event.MouseEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn tạo mới khuyến mãi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {    
            try {
                String newMaPN = list_KM.getNewMaKMFromDB();
                tfThongTin_MAKM.setText(newMaPN);
                JOptionPane.showMessageDialog(null, "Khởi tạo khuyến mãi mới thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                tfThongTin_MAKM.setText("KM1");
                JOptionPane.showMessageDialog(null, "Không thể khởi tạo khuyến mãi mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            cbbThongTin_Loai.setSelectedIndex(0);
            tfThongTin_TenKM.setText("");         
            cbbThongTin_DK.setSelectedIndex(0);
            
            tfChiTiet_MaSP.setText("");
            tfChiTiet_MaKM.setText(tfThongTin_MAKM.getText());
            
            String s = LocalDateTime.now().getDayOfMonth() + " " +
                    LocalDateTime.now().getYear() + " " +
                    LocalDateTime.now().getMonth() ;
            
            Date date = new Date(s);
            calendarThongTin.setDate(date);
            calendarThongTin1.setDate(date);
            
            tfThongTin_MAKM.requestFocus();
            anHienCacNut(false);
            btnThongTin_Them.setEnabled(true);
            tfChiTiet_TiLeKM.setEditable(true);
            actionButtondisplayChiTiet(tfThongTin_MAKM.getText());
        }
    }

    // Thêm khuyến mãi
    private void btnThongTin_ThemActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm khuyến mãi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {  
            try {
                list_KM.docDB();
            } catch (Exception ex) {
                Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (tfThongTin_TenKM.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập Tên chương trình khuyến mãi!", 
                    "Thiếu thông tin", 
                    JOptionPane.WARNING_MESSAGE);
                tfThongTin_TenKM.requestFocus();
                return;
            }

            String[] s = String.valueOf(calendarThongTin.getDate()).split("\\ ");
            String NgayBan = s[2] + " " + s[1] + " " + s[5];

            String[] s1 = String.valueOf(calendarThongTin1.getDate()).split("\\ ");
            String Ngay = s1[2] + " " + s1[1] + " " + s1[5];

            if (!cachamquantrong.kiemTraDate(NgayBan, Ngay)) {
                JOptionPane.showMessageDialog(null, "Lỗi ngày tháng năm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                khuyenmai.setStrMaKM(tfThongTin_MAKM.getText());
                khuyenmai.setStrLoaiChuongTrinh((String) cbbThongTin_Loai.getSelectedItem());
                khuyenmai.setStrDieuKien((String) cbbThongTin_DK.getSelectedItem());
                khuyenmai.setStrTenChuongTrinh(tfThongTin_TenKM.getText());
                khuyenmai.setStrNgayBatDau(NgayBan);
                khuyenmai.setStrNgayKetThuc(Ngay);

                try {
                    list_KM.them(khuyenmai);
                    JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    list_KM.docDB();
                } catch (Exception ex) {
                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }

                tfChiTiet_MaKM.setText(khuyenmai.getStrMaKM());
                tfChiTiet_MaSP.requestFocus();
                tfChiTiet_MaKM.setText(tfThongTin_MAKM.getText());

                tfThongTin_MAKM.setText("");
                cbbThongTin_Loai.setSelectedIndex(0);
                cbbThongTin_DK.setSelectedIndex(0);

                actionButtondisplay();
                anHienCacNut(false);
                btnChiTiet_Them.setEnabled(true);
                tfChiTiet_TiLeKM.setEditable(true); 
            }
        }
    }
    // Sửa khuyến mãi
    private void btnThongTin_SuaMouseClicked(java.awt.event.MouseEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa khuyến mãi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            // Kiểm tra tên chương trình khuyến mãi rỗng
            if (tfThongTin_TenKM.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập Tên chương trình khuyến mãi!", 
                    "Thiếu thông tin", 
                    JOptionPane.WARNING_MESSAGE);
                tfThongTin_TenKM.requestFocus();
                return;
            }
            // Lấy ngày bắt đầu và ngày kết thúc
            String[] s = String.valueOf(calendarThongTin.getDate()).split("\\ ");
            String NgayBan = s[2] + " " + s[1] + " " + s[5];

            String[] s1 = String.valueOf(calendarThongTin1.getDate()).split("\\ ");
            String Ngay = s1[2] + " " + s1[1] + " " + s1[5];

            khuyenmai.setStrMaKM(tfThongTin_MAKM.getText());
            khuyenmai.setStrLoaiChuongTrinh((String) cbbThongTin_Loai.getSelectedItem());
            khuyenmai.setStrDieuKien((String) cbbThongTin_DK.getSelectedItem());
            khuyenmai.setStrTenChuongTrinh(tfThongTin_TenKM.getText());
            khuyenmai.setStrNgayBatDau(NgayBan);
            khuyenmai.setStrNgayKetThuc(Ngay);

            try {
                list_KM.sua(khuyenmai);
                JOptionPane.showMessageDialog(null, "Cập nhật khuyến mãi thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Cập nhật khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

            actionButtondisplay();
            actionButtondisplayChiTiet(khuyenmai.getStrMaKM());

            if (!tfThongTin_MAKM.getText().equals("")) {
                tfThongTin_MAKM.setText("");
                cbbThongTin_Loai.setSelectedIndex(0);
                cbbThongTin_DK.setSelectedIndex(0);
                tfThongTin_TenKM.setText("");
            }
        }
    }

    // Xóa khuyến mãi
    private void btnThongTin_XoaMouseClicked(java.awt.event.MouseEvent evt) {
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khuyến mãi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            KhuyenMaiDTO hd = new KhuyenMaiDTO();
            hd.setStrMaKM(tfThongTin_MAKM.getText());

            try {
                // Kiểm tra xem còn chi tiết khuyến mãi không
                boolean hasChiTiet = false;
                for (ChiTietKMDTO ct : list_ChiTietKM.getList_KM()) {
                    if (ct.getStrMaKM().equals(hd.getStrMaKM())) {
                        hasChiTiet = true;
                        break;
                    }
                }

                if (hasChiTiet) {
                    JOptionPane.showMessageDialog(null, "Không thể xóa khuyến mãi vì vẫn còn chi tiết khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng lại, không xóa
                }

                // Nếu không có chi tiết nào => cho phép xóa
                list_KM.xoa(hd);
                JOptionPane.showMessageDialog(null, "Xóa khuyến mãi thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                list_KM.docDB();
            } catch (Exception ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

            actionButtondisplay();

            tfThongTin_MAKM.setText("");
            cbbThongTin_Loai.setSelectedIndex(0);
            cbbThongTin_DK.setSelectedIndex(0);
        }
    }

    // Hiển thị thông tin chi tiết khuyến mãi
    private void tblThongTin1MouseClicked(java.awt.event.MouseEvent evt) {
        int  i=tblThongTin1.getSelectedRow();
        
        tfChiTiet_MaSP.setText(tblThongTin1.getModel().getValueAt(i, 0).toString());
        tfChiTiet_MaKM.setText(tblThongTin1.getModel().getValueAt(i, 1).toString());
        tfChiTiet_TiLeKM.setText(tblThongTin1.getModel().getValueAt(i, 2).toString());
        
        try {
            HoaDonBUS hoaDonBUS = new HoaDonBUS(); // constructor đã tự docDB()

            boolean daSuDung = hoaDonBUS.isMaKMDaSuDung(tfChiTiet_MaKM.getText());

            anHienCacNut(false);

            if (!daSuDung) {
                // Chỉ bật 3 nút: Sửa, Xóa, Tạo mới
                btnChiTiet_Sua.setEnabled(true);
                btnChiTiet_Xoa.setEnabled(true);
                btnChiTiet_TaoMoi.setEnabled(true);
                tfChiTiet_TiLeKM.setEditable(true);
                // Không bật nút Thêm
                btnChiTiet_Them.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // không cho chỉnh sửa
        tfChiTiet_MaSP.setEditable(false);
    }

    // Hiển thị chi tiết khuyến mãi
    private void lblChiTiet_TatcaMouseClicked(java.awt.event.MouseEvent evt) {
        tfThongTin_MAKM.setText("");
        cbbThongTin_Loai.setSelectedIndex(0);
        // tfThongTin_TenKM.setText(Memory.maNV);         
        cbbThongTin_DK.setSelectedItem(0);
        
        String s = LocalDateTime.now().getDayOfMonth() + " " +
                LocalDateTime.now().getYear() + " " +
                LocalDateTime.now().getMonth() ;
        System.out.println(s);
        
        Date date = new Date(s);
        calendarThongTin.setDate(date);
        
        actionButtondisplayChiTiet();
    }

    //Tạo mới chi tiết khuyến mãi
    private void btnChiTiet_TaoMoiActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn tạo mới chi tiết khuyến mãi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) { 
            try {
                tfChiTiet_MaSP.setText("");
                tfChiTiet_TiLeKM.setText("");
                tfChiTiet_MaKM.setText(khuyenmai.getStrMaKM());
                
                anHienCacNut(false);
                btnChiTiet_Them.setEnabled(true);
                tfChiTiet_TiLeKM.setEditable(true);
                JOptionPane.showMessageDialog(null, "Khởi tạo chi tiết khuyến mãi mới thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Khởi tạo chi tiết khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Thêm chi tiết khuyến mãi
    private void btnChiTiet_ThemActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm chi tiết khuyến mãi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) { 
            try {
                // Kiểm tra tỉ lệ khuyến mãi hợp lệ trước
                if (!cachamquantrong.kiemTraTiLeKhuyenMai(tfChiTiet_TiLeKM.getText())) {
                    return;
                }

                if (kiemtraKhoaChinh(tfChiTiet_MaSP.getText(), tfChiTiet_MaKM.getText())) {
                    xulyThemChiTietHD();
                    tfChiTiet_TiLeKM.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Trùng mã sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE); 
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Thêm chi tiết khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xulyThemChiTietHD(){
        try {
            list_ChiTietKM.docDB();
        } catch (Exception ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChiTietKMDTO km = new ChiTietKMDTO();
        km.setStrMaGiay(tfChiTiet_MaSP.getText());
        km.setStrMaKM(tfChiTiet_MaKM.getText());
        km.setTiLeKM(Double.parseDouble(tfChiTiet_TiLeKM.getText()));

        boolean flag = list_ChiTietKM.kiemTraKhoachinh(km);

        try {
            if (!flag) {
                list_ChiTietKM.them(km);
                JOptionPane.showMessageDialog(null, "Thêm chi tiết khuyến mãi thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Chi tiết khuyến mãi đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Thêm chi tiết khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            list_ChiTietKM.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        actionButtondisplayChiTiet(khuyenmai.getStrMaKM());

        if (!flag) {
            try {
                list_KM.sua(khuyenmai);
                actionButtondisplay();
            } catch (Exception ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        tfChiTiet_MaSP.setText("");
        tfChiTiet_MaSP.requestFocus();

        tfChiTiet_MaSP.setEditable(true);
        tfChiTiet_TiLeKM.setEditable(true); 
    }

    // Sửa chi tiết khuyến mãi
    private void btnChiTiet_SuaActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa chi tiết khuyến mãi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) { 
            try {
                // Kiểm tra tỉ lệ khuyến mãi hợp lệ trước
                if (!cachamquantrong.kiemTraTiLeKhuyenMai(tfChiTiet_TiLeKM.getText())) {
                    return;
                }

                ChiTietKMDTO hd = new ChiTietKMDTO();
                hd.setStrMaGiay(tfChiTiet_MaSP.getText());
                hd.setStrMaKM(tfChiTiet_MaKM.getText());
                hd.setTiLeKM(Double.parseDouble(tfChiTiet_TiLeKM.getText()));

                list_ChiTietKM.sua(hd);
                JOptionPane.showMessageDialog(null, "Cập nhật chi tiết khuyến mãi thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

                list_ChiTietKM.docDB();
                actionButtondisplayChiTiet(khuyenmai.getStrMaKM());
                tfChiTiet_MaSP.setText("");
                tfChiTiet_TiLeKM.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Cập nhật chi tiết khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xóa chi tiết khuyến mãi
    private void btnChiTiet_XoaActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chi tiết khuyến mãi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ChiTietKMDTO hd = new ChiTietKMDTO();
            hd.setStrMaGiay(tfChiTiet_MaSP.getText());
            hd.setStrMaKM(tfChiTiet_MaKM.getText());

            try {
                list_ChiTietKM.xoa(hd);
                list_ChiTietKM.docDB();
                JOptionPane.showMessageDialog(null, "Xóa chi tiết khuyến mãi thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa chi tiết khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                list_KM.sua(khuyenmai);
                actionButtondisplay();
            } catch (Exception ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

            actionButtondisplayChiTiet(khuyenmai.getStrMaKM());
            tfChiTiet_MaSP.setText("");
            tfChiTiet_MaKM.setText("");
        }
    }
       
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {
        try {
            list_ChiTietKM.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!tfChiTiet_MaSP2.getText().equals(""))
                list_ChiTietKM.setList_KM(list_ChiTietKM.timKiem_MaSP(tfChiTiet_MaSP2.getText()
                        , tfChiTiet_MaSP3.getText()) );
            
        if (!tfChiTiet_SL2.getText().equals(""))
                list_ChiTietKM.setList_KM(list_ChiTietKM.timKiem_TiLeKM(Double.parseDouble(tfChiTiet_SL2.getText())
                        , Double.parseDouble(tfChiTiet_SL3.getText())) );
          
        if (tfThongTin_MAKM.getText().equals(""))
            actionButtondisplayChiTiet();
        else
            actionButtondisplayChiTiet(tfThongTin_MAKM.getText());
        System.out.println("Đang tìm kiếm");
    }

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
        try {
            list_ChiTietKM.docDB();
            if (tfThongTin_MAKM.getText().equals(""))
                actionButtondisplayChiTiet();
            else
                actionButtondisplayChiTiet(tfThongTin_MAKM.getText());
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

    private void tfThongTin_MAKMActionPerformed(java.awt.event.ActionEvent evt) {
    }
    
    private boolean kiemtraMaSP() {
        SanPhamDTO sp = new SanPhamDTO();
        sp = list_SanPham.getInfor(tfChiTiet_MaSP.getText());
        
        if (sp.getStrMaGiay().equals("null"))
            return false;
        
        
        return true;
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
                        tfChiTiet_TiLeKM.requestFocus();
                }
            }
        });

    }
    
    private boolean kiemtraKhoaChinh(String strMaGiay, String strMaKM) {
        ChiTietKMDTO km = new ChiTietKMDTO();
        km = list_ChiTietKM.getInfor(strMaGiay, strMaKM);
        if (km.getStrMaGiay().equals("null"))
            return true;
        return false;
    }

    private void tfChiTiet_MaSPMouseClicked(java.awt.event.MouseEvent evt) {
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

    private void tfChiTiet_TiLeKMMouseClicked(java.awt.event.MouseEvent evt) {
    }

    private void btnTimKiem_Trong1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnTimKiem_Trong1MouseClicked(java.awt.event.MouseEvent evt) {
        try{
            list_KM = new KhuyenMaiBUS();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        tfTimKiem_MaKM.setText("");
        tfTimKiem_TenCT.setText("");
    }

    private void btnTimKiem_HuyTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnTimKiem_HuyTimKiemMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            list_KM = new KhuyenMaiBUS();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            list_KM.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        actionButtondisplay();
    }

    private void btnTimKiem_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnTimKiem_TimKiemMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            list_KM.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!tfTimKiem_MaKM.getText().equals(""))
            list_KM.setList_KM(list_KM.timKiem_MaKM(tfTimKiem_MaKM.getText()) );
        if (!tfTimKiem_TenCT.getText().equals(""))
            list_KM.setList_KM(list_KM.timKiem_TenCT(tfTimKiem_TenCT.getText()));
        
        actionButtondisplay();
        System.out.println("Đang tìm kiếm");
    }

    private void lblThongTin_TimKiemMouseReleased(java.awt.event.MouseEvent evt) {
        actionVisiblePanel();
        pThongTin_TimKiem.setVisible(true);
    }

    private void lblChiTiet_3chamMouseClicked(java.awt.event.MouseEvent evt) {
        ChonSanPham csp = new ChonSanPham(this, true);
        csp.setVisible(true);
        tfChiTiet_MaSP.setText(Memory.maSP);
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
        if (model.getRowCount()==0) {
            Vector<String> header = new Vector();
            header.add("Mã KM");
            header.add("Loại CT");
            header.add("Tên CT");
            header.add("Điều Kiện");
            header.add("Ngày BĐ");
            header.add("Ngày KT");
            
            model = new DefaultTableModel(header, 0){
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };;
        }
    }
    
    private void actionButtondisplay() {
        model = new DefaultTableModel(); 
        createVectorHeader();
        for (int i=0 ; i < list_KM.getNumb() ; i++) {
            KhuyenMaiDTO hd = list_KM.getInfor(i);        
            // hd = list_KM.getInfor(i);
            
            if (hd.getDeleted() == 0) {
                Vector row=new Vector();
                row.add(hd.getStrMaKM());
                row.add(hd.getStrLoaiChuongTrinh());
                row.add(hd.getStrTenChuongTrinh());
                row.add(hd.getStrDieuKien());
                row.add(hd.getStrNgayBatDau());
                row.add(hd.getStrNgayKetThuc());
                
                model.addRow(row);
            }
        }
       
        
        tblThongTin.setModel(model);
        
    }
    private void createVectorHeaderChiTiet() {
        if (modelChiTiet.getRowCount()==0) {
            Vector<String> header = new Vector();
            header.add("Mã Sản Phẩm");
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
    
    private void actionButtondisplayChiTiet(String strMaKM) {
        modelChiTiet = new DefaultTableModel();
        
        createVectorHeaderChiTiet();
        for (int i=0 ; i < list_ChiTietKM.getNumbKM(); i++) {
            ChiTietKMDTO hd = new ChiTietKMDTO();
            
            hd = list_ChiTietKM.getInfor(i);
            
            createVectorHeaderChiTiet();
            
            if ( hd.getStrMaKM().equalsIgnoreCase(strMaKM) || 
                    strMaKM.equals("null")) {
                
                Vector row=new Vector();
                row.add(hd.getStrMaGiay());
                row.add(hd.getStrMaKM());
                row.add(hd.getTiLeKM());
                
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
        String currentMenu = "Khuyến mãi";

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
                    Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
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
            if (menuName != null && menuName.equals("Khuyến mãi")) {
                parent.setBackground(new Color(52, 152, 219));
            } else {
                parent.setBackground(new Color(44, 62, 80));
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
                    new KhuyenMai().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}