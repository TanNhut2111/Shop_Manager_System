package ShoesManager.GUI;
import ShoesManager.BUS.LoaiBUS;
import ShoesManager.BUS.MauSacBUS;
import ShoesManager.BUS.NhanVienBUS;
import ShoesManager.BUS.XuatXuBUS;
import ShoesManager.BUS.SanPhamBUS;
import ShoesManager.BUS.ThuongHieuBUS;
import ShoesManager.DTO.LoaiDTO;
import ShoesManager.DTO.MauSacDTO;
import ShoesManager.DTO.NhanVienDTO;
import ShoesManager.DTO.SanPhamDTO;
import ShoesManager.DTO.ThuongHieuDTO;
import ShoesManager.DTO.XuatXuDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

public class Sanpham extends javax.swing.JFrame  implements MouseListener {

    private CacHamQuanTrong cachamquantrong;
    private javax.swing.JMenu menuTaiKhoan;
    private javax.swing.JMenu menuTroVe;
    private javax.swing.JMenuItem menuTaiKhoan_DangXuat, menuTaiKhoan_ThongTin;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JButton btnHuyTimKiem;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbThongtin_MaLoai;
    private javax.swing.JComboBox<String> cbbThongtin_MaMau;
    private javax.swing.JComboBox<String> cbbThongtin_MaThuongHieu;
    private javax.swing.JComboBox<String> cbbThongtin_MaXX;
    private javax.swing.JComboBox<String> cbbTimKiem_MaLoai;
    private javax.swing.JComboBox<String> cbbTimKiem_MaMau;
    private javax.swing.JComboBox<String> cbbTimKiem_MaThuongHieu;
    private javax.swing.JComboBox<String> cbbTimKiem_MaXX;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pCenter;
    private javax.swing.JPanel pTable;
    private javax.swing.JPanel pThongTin;
    private javax.swing.JPanel pTimKiem;
    private javax.swing.JPanel pTop;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTextField tfChatLieu;
    private javax.swing.JTextField tfDoiTuongSD;
    private javax.swing.JTextField tfGia;
    private javax.swing.JTextField tfMaGiay;
    private javax.swing.JTextField tfSize;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfTenGiay;
    private javax.swing.JTextField tfTimKiem_TenGiay;

    public Sanpham() throws Exception {
        this.setUndecorated(true);

        init();
        initComponents();
        
        
        // các hàm xử lý sau khi vào
        kiemTraCapBac();
        // hiển thị cbb
        nhapThongTin_MaLoai();
        nhapThongTin_MaXX();
        nhapThongTin_MauSac();
        nhapThongTin_MaThuongHieu();
        nhapTimKiem_MaLoai();
        nhapTimKiem_MaXX();
        nhapTimKiem_MauSac();
        nhapTimKiem_MaThuongHieu();
        
        // hiển thị các dữ liệu table
        actionButtondisplay();
    }
    
    private JLabel[] listLblTop;
    private String[] strArr_Top;
    private SanPhamBUS list_SP;
   
    private DefaultTableModel model;
    
    
    public void init() throws Exception {
        
        list_SP = new SanPhamBUS();
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
        pTop = createPanel_LblLeft(strArr_Top);
        pCenter = new javax.swing.JPanel();
        pTable = new javax.swing.JPanel();
        
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


        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        pThongTin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfMaGiay = new javax.swing.JTextField();
        tfTenGiay = new javax.swing.JTextField();
        tfSoLuong = new javax.swing.JTextField();
        tfGia = new javax.swing.JTextField();
        tfSize = new javax.swing.JTextField();
        tfDoiTuongSD = new javax.swing.JTextField();
        tfChatLieu = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        cbbThongtin_MaLoai = new javax.swing.JComboBox<>();
        cbbThongtin_MaXX = new javax.swing.JComboBox<>();
        cbbThongtin_MaMau = new javax.swing.JComboBox<>();
        cbbThongtin_MaThuongHieu = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        pTimKiem = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tfTimKiem_TenGiay = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbbTimKiem_MaLoai = new javax.swing.JComboBox<>();
        cbbTimKiem_MaXX = new javax.swing.JComboBox<>();
        cbbTimKiem_MaMau = new javax.swing.JComboBox<>();
        cbbTimKiem_MaThuongHieu = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        btnHuyTimKiem = new javax.swing.JButton();

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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Hệ Thống Quản Lý Bán Giày - Dashboard");
        setSize(1430, 720);
        setMinimumSize(new Dimension(1280, 720));
        setResizable(true);
        setUndecorated(false);
        setLocationRelativeTo(null);

        pCenter.setPreferredSize(new java.awt.Dimension(1280, 600));

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
        pThongTin.setBackground(xanhNen);
        pTimKiem.setBackground(xanhNen);
        
        

        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Giày", "Tên Giày", "Số lượng", "Giá", "Size", "Đối Tượng sử dụng","Chất liệu","Mã Loại","Mã Xuất xứ","Mã màu","Mã thương hiệu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
                , java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false,false,false,false,false,false
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
        tblThongTin.setAutoCreateRowSorter(true);

        javax.swing.GroupLayout pTableLayout = new javax.swing.GroupLayout(pTable);
        pTable.setLayout(pTableLayout);
        pTableLayout.setHorizontalGroup(
            pTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        pTableLayout.setVerticalGroup(
            pTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );

        pThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 16), new java.awt.Color(52, 73, 94))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Tên Giày");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Mã Giày");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Số Lượng ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Giá");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Size");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Đối Tượng SD");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Chất Liệu");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Mã Loại");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Mã Xuất Xứ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Mã Màu");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Mã Thương Hiệu");

        tfMaGiay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfMaGiay.setToolTipText("Không thể sửa Mã sản phẩm!!");
        tfMaGiay.setEnabled(false);
        tfMaGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaGiayActionPerformed(evt);
            }
        });

        tfTenGiay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfTenGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTenGiayActionPerformed(evt);
            }
        });

        tfSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfSoLuong.setToolTipText("Không thể sửa Số lượng sản phẩm!!");
        tfSoLuong.setEnabled(false);
        tfSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSoLuongActionPerformed(evt);
            }
        });

        tfGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfGiaActionPerformed(evt);
            }
        });

        tfSize.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSizeActionPerformed(evt);
            }
        });

        tfDoiTuongSD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfDoiTuongSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDoiTuongSDActionPerformed(evt);
            }
        });

        tfChatLieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfChatLieuActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        btnSua.setText("SỬA");
        btnSua.setEnabled(false);
        btnSua.setBackground(new Color(52, 73, 94));
        btnSua.setForeground(Color.WHITE);
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (btnSua.isEnabled()) {
                    btnSua.setBackground(new Color(44, 62, 80));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (btnSua.isEnabled()) {
                    btnSua.setBackground(new Color(52, 73, 94));
                }
            }
        });
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        btnReset.setText("RESET");
        btnReset.setBackground(new Color(108, 117, 125));
        btnReset.setForeground(Color.WHITE);
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReset.setBackground(new Color(90, 98, 104));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReset.setBackground(new Color(108, 117, 125));
            }
        });
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        cbbThongtin_MaLoai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbThongtin_MaLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbThongtin_MaLoai.setSelectedItem("selected");
        cbbThongtin_MaLoai.setName(""); // NOI18N
        cbbThongtin_MaLoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbbThongtin_MaLoaiFocusLost(evt);
            }
        });

        cbbThongtin_MaXX.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbThongtin_MaXX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThongtin_MaMau.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbThongtin_MaMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThongtin_MaThuongHieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbThongtin_MaThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThem.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        btnThem.setText("THÊM");
        btnThem.setBackground(new Color(40, 167, 69));
        btnThem.setForeground(Color.WHITE);
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThem.setBackground(new Color(33, 136, 56));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThem.setBackground(new Color(40, 167, 69));
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        btnLuu.setText("LƯU");
        btnLuu.setEnabled(false);
        btnLuu.setBackground(new Color(0, 123, 255));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setBorderPainted(false);
        btnLuu.setFocusPainted(false);
        btnLuu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (btnLuu.isEnabled()) {
                    btnLuu.setBackground(new Color(0, 105, 217));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (btnLuu.isEnabled()) {
                    btnLuu.setBackground(new Color(0, 123, 255));
                }
            }
        });
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.setEnabled(false);
        btnXoa.setBackground(new Color(220, 53, 69));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (btnXoa.isEnabled()) {
                    btnXoa.setBackground(new Color(200, 35, 51));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (btnXoa.isEnabled()) {
                    btnXoa.setBackground(new Color(220, 53, 69));
                }
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pThongTinLayout = new javax.swing.GroupLayout(pThongTin);
        pThongTin.setLayout(pThongTinLayout);
        pThongTinLayout.setHorizontalGroup(
            pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTinLayout.createSequentialGroup()
                        .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfTenGiay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfMaGiay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfSize, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfGia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pThongTinLayout.createSequentialGroup()
                                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfChatLieu)
                                    .addComponent(cbbThongtin_MaLoai, 0, 118, Short.MAX_VALUE)
                                    .addComponent(cbbThongtin_MaXX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pThongTinLayout.createSequentialGroup()
                                .addComponent(cbbThongtin_MaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                            .addGroup(pThongTinLayout.createSequentialGroup()
                                .addComponent(cbbThongtin_MaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(tfDoiTuongSD, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pThongTinLayout.setVerticalGroup(
            pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMaGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfChatLieu)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfTenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbThongtin_MaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThongtin_MaXX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThongtin_MaMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThongtin_MaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDoiTuongSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", Font.BOLD, 16), new java.awt.Color(52, 73, 94))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Tên Giày");

        tfTimKiem_TenGiay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfTimKiem_TenGiay.setToolTipText("");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Mã Loại");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Mã Xuất Xứ");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Mã Màu");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Mã Thương Hiệu");

        cbbTimKiem_MaLoai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbTimKiem_MaLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTimKiem_MaXX.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbTimKiem_MaXX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTimKiem_MaMau.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbTimKiem_MaMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTimKiem_MaThuongHieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbTimKiem_MaThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.setBackground(new Color(0, 123, 255));
        btnTimKiem.setForeground(Color.WHITE);
        btnTimKiem.setBorderPainted(false);
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTimKiem.setBackground(new Color(0, 105, 217));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTimKiem.setBackground(new Color(0, 123, 255));
            }
        });

        btnHuyTimKiem.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        btnHuyTimKiem.setText("Hủy Tìm Kiếm");
        btnHuyTimKiem.setBackground(new Color(108, 117, 125));
        btnHuyTimKiem.setForeground(Color.WHITE);
        btnHuyTimKiem.setBorderPainted(false);
        btnHuyTimKiem.setFocusPainted(false);
        btnHuyTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuyTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimKiemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHuyTimKiem.setBackground(new Color(90, 98, 104));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHuyTimKiem.setBackground(new Color(108, 117, 125));
            }
        });

        javax.swing.GroupLayout pTimKiemLayout = new javax.swing.GroupLayout(pTimKiem);
        pTimKiem.setLayout(pTimKiemLayout);
        pTimKiemLayout.setHorizontalGroup(
            pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTimKiemLayout.createSequentialGroup()
                        .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTimKiemLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(tfTimKiem_TenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pTimKiemLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbTimKiem_MaXX, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbTimKiem_MaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbTimKiem_MaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbTimKiem_MaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTimKiemLayout.createSequentialGroup()
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTimKiemLayout.createSequentialGroup()
                                .addComponent(btnHuyTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))))
                    .addGroup(pTimKiemLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pTimKiemLayout.setVerticalGroup(
            pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTimKiemLayout.createSequentialGroup()
                .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTimKiemLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTimKiem_TenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pTimKiemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTimKiemLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnHuyTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pTimKiemLayout.createSequentialGroup()
                        .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbTimKiem_MaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTimKiem_MaXX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTimKiem_MaMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTimKiem_MaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(137, 137, 137))
        );

        javax.swing.GroupLayout pCenterLayout = new javax.swing.GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
            pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pCenterLayout.createSequentialGroup()
                        .addComponent(pThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pCenterLayout.setVerticalGroup(
            pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCenterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
            Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Hiển thị thông tin sản phẩm
    private void tblThongTinMouseClicked(java.awt.event.MouseEvent evt) {
        int  i=tblThongTin.getSelectedRow();
          
          tfMaGiay.setText(tblThongTin.getModel().getValueAt(i, 0).toString());
          tfTenGiay.setText(tblThongTin.getModel().getValueAt(i, 1).toString());
          tfSoLuong.setText(tblThongTin.getModel().getValueAt(i, 2).toString());
          tfGia.setText(tblThongTin.getModel().getValueAt(i, 3).toString());
          tfSize.setText(tblThongTin.getModel().getValueAt(i, 4).toString());
          tfDoiTuongSD.setText(tblThongTin.getModel().getValueAt(i, 5).toString());
          tfChatLieu.setText(tblThongTin.getModel().getValueAt(i, 6).toString());
          cbbThongtin_MaLoai.setSelectedItem(tblThongTin.getModel().getValueAt(i, 7));
          cbbThongtin_MaXX.setSelectedItem(tblThongTin.getModel().getValueAt(i, 8));
          cbbThongtin_MaMau.setSelectedItem(tblThongTin.getModel().getValueAt(i, 9));
          cbbThongtin_MaThuongHieu.setSelectedItem(tblThongTin.getModel().getValueAt(i, 10));
  
        // không cho thêm
        tfMaGiay.setEditable(false);
        tfMaGiay.setEnabled(false);
        tfSoLuong.setEditable(false);
        btnSua.setEnabled(true);
        btnThem.setEnabled(false);
        btnLuu.setEnabled(false);
        btnXoa.setEnabled(true);

        // Kiểm tra nếu sản phẩm đang được dùng ở 3 bảng => ẩn nút Xóa
        try {
            String maSP = tfMaGiay.getText().trim();
            if (cachamquantrong.isSanPhamDangSuDung(maSP)) {
                btnXoa.setEnabled(false);
            } else {
                btnXoa.setEnabled(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tfMaGiayActionPerformed(java.awt.event.ActionEvent evt) {}

    private void tfTenGiayActionPerformed(java.awt.event.ActionEvent evt) {}

    private void tfSoLuongActionPerformed(java.awt.event.ActionEvent evt) {}

    private void tfGiaActionPerformed(java.awt.event.ActionEvent evt) {}

    private void tfSizeActionPerformed(java.awt.event.ActionEvent evt) {}

    private void tfDoiTuongSDActionPerformed(java.awt.event.ActionEvent evt) {}

    private void tfChatLieuActionPerformed(java.awt.event.ActionEvent evt) {}

    private void btnSuaMouseEntered(java.awt.event.MouseEvent evt) {}

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {}

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {}

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            list_SP.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!tfTimKiem_TenGiay.getText().equals(""))
            list_SP.setList_SP( list_SP.timKiem_TenGiay(tfTimKiem_TenGiay.getText()) );
        if (!cbbTimKiem_MaLoai.getSelectedItem().equals("--Chọn--"))
            list_SP.setList_SP( list_SP.timKiem_MaLoai((String) cbbTimKiem_MaLoai.getSelectedItem()) );
        if (!cbbTimKiem_MaXX.getSelectedItem().equals("--Chọn--"))
            list_SP.setList_SP( list_SP.timKiem_MaXX((String) cbbTimKiem_MaXX.getSelectedItem()) );
        if (!cbbTimKiem_MaMau.getSelectedItem().equals("--Chọn--"))
            list_SP.setList_SP( list_SP.timKiem_MaMau((String) cbbTimKiem_MaMau.getSelectedItem()) );
        if (!cbbTimKiem_MaThuongHieu.getSelectedItem().equals("--Chọn--"))
            list_SP.setList_SP( list_SP.timKiem_MaThuongHieu((String) cbbTimKiem_MaThuongHieu.getSelectedItem()) );
        
        actionButtondisplay();
    }

    private void cbbThongtin_MaLoaiFocusLost(java.awt.event.FocusEvent evt) {}

    private void btnHuyTimKiemMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            list_SP = new SanPhamBUS();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_SP.docDB();
        } catch (Exception ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfTimKiem_TenGiay.setText("");
        cbbTimKiem_MaLoai.setSelectedIndex(0);
        cbbTimKiem_MaXX.setSelectedIndex(0);
        cbbTimKiem_MaMau.setSelectedIndex(0);
        cbbTimKiem_MaThuongHieu.setSelectedIndex(0);
        actionButtondisplay();
    }

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {
    }

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {}

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {}

    // Thêm sản phẩm
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) { 
        try {
            // Lấy mã sản phẩm mới từ DB
            String newMaSP = list_SP.getNewMaSPFromDB();
            tfMaGiay.setText(newMaSP);
            JOptionPane.showMessageDialog(this, "Tạo mới sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            tfMaGiay.setText("SP1");
            JOptionPane.showMessageDialog(this, "Tạo mới sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        tfTenGiay.setText("");
        tfSoLuong.setText("");
        tfGia.setText("");
        tfSize.setText("");
        tfDoiTuongSD.setText("");
        tfChatLieu.setText("");
        cbbThongtin_MaLoai.setSelectedIndex(0);
        cbbThongtin_MaXX.setSelectedIndex(0);
        cbbThongtin_MaMau.setSelectedIndex(0);
        cbbThongtin_MaThuongHieu.setSelectedIndex(0);

        tfSoLuong.setEnabled(true);
        tfSoLuong.setEditable(true);
        tfTenGiay.requestFocus();
        btnThem.setEnabled(false);
        btnLuu.setEnabled(true);
    }

    // Sửa sản phẩm
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {
        int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa chứ!!");
        if (a == JOptionPane.YES_OPTION) {
            try {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setStrMaGiay(tfMaGiay.getText());
                sp.setStrTenGiay(tfTenGiay.getText());
                sp.setiSoLuong(Integer.parseInt(tfSoLuong.getText()));
                sp.setiGia(Integer.parseInt(tfGia.getText()));
                sp.setiSize(Integer.parseInt(tfSize.getText()));
                sp.setStrDoiTuongSD(tfDoiTuongSD.getText());
                sp.setStrChatLieu(tfChatLieu.getText());
                sp.setStrMaLoai((String) cbbThongtin_MaLoai.getSelectedItem());
                sp.setStrMaxx((String) cbbThongtin_MaXX.getSelectedItem());
                sp.setStrMaMau((String) cbbThongtin_MaMau.getSelectedItem());
                sp.setStrMaThuongHieu((String) cbbThongtin_MaThuongHieu.getSelectedItem());

                if (cbbThongtin_MaLoai.getSelectedItem() == "--Chọn--" || 
                    cbbThongtin_MaMau.getSelectedItem() == "--Chọn--" ||
                    cbbThongtin_MaXX.getSelectedItem() == "--Chọn--" || 
                    cbbThongtin_MaThuongHieu.getSelectedItem() == "--Chọn--") {

                    JOptionPane.showMessageDialog(null, 
                        "Sửa không thành công, vui lòng chọn hết các thông tin!", 
                        "Cảnh báo", JOptionPane.ERROR_MESSAGE);

                } else {
                    list_SP.sua(sp);
                    list_SP.docDB();

                    JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    actionButtondisplay();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Sửa sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Reset sản phẩm
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            tfMaGiay.setText("");
            tfTenGiay.setText("");
            tfSoLuong.setText("");
            tfGia.setText("");
            tfSize.setText("");
            tfDoiTuongSD.setText("");
            tfChatLieu.setText("");
            cbbThongtin_MaLoai.setSelectedIndex(0);
            cbbThongtin_MaXX.setSelectedIndex(0);
            cbbThongtin_MaMau.setSelectedIndex(0);
            cbbThongtin_MaThuongHieu.setSelectedIndex(0);
            tfTenGiay.requestFocus();
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnThem.setEnabled(true);
            btnLuu.setEnabled(false);
            tblThongTin.clearSelection();

            JOptionPane.showMessageDialog(null, "Đặt lại thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thể reset sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Lưu sản phẩm
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {
        int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn lưu chứ!!");
        if (a == JOptionPane.YES_OPTION) {
            try {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setStrMaGiay(tfMaGiay.getText());
                sp.setStrTenGiay(tfTenGiay.getText());
                if (tfGia.getText().equals("")) tfGia.setText("0");
                sp.setiGia(Integer.parseInt(tfGia.getText()));
                if (tfSize.getText().equals("")) tfSize.setText("0");
                sp.setiSize(Integer.parseInt(tfSize.getText()));
                if (tfSoLuong.getText().equals("")) tfSoLuong.setText("0");
                sp.setiSoLuong(Integer.parseInt(tfSoLuong.getText()));
                sp.setStrChatLieu(tfChatLieu.getText());
                sp.setStrDoiTuongSD(tfDoiTuongSD.getText());
                sp.setStrMaLoai((String) cbbThongtin_MaLoai.getSelectedItem());
                sp.setStrMaxx((String) cbbThongtin_MaXX.getSelectedItem());
                sp.setStrMaMau((String) cbbThongtin_MaMau.getSelectedItem());
                sp.setStrMaThuongHieu((String) cbbThongtin_MaThuongHieu.getSelectedItem());

                if (cbbThongtin_MaLoai.getSelectedItem() == "--Chọn--" || 
                    cbbThongtin_MaMau.getSelectedItem() == "--Chọn--" ||
                    cbbThongtin_MaXX.getSelectedItem() == "--Chọn--" || 
                    cbbThongtin_MaThuongHieu.getSelectedItem() == "--Chọn--" ||
                    tfTenGiay.getText().equals("") || 
                    tfDoiTuongSD.getText().equals("") || 
                    tfChatLieu.getText().equals("") || 
                    tfGia.getText().equals("") || 
                    tfSize.getText().equals("") || 
                    tfSoLuong.getText().equals("")) {

                    JOptionPane.showMessageDialog(null, 
                        "Lưu không thành công, vui lòng nhập đầy đủ thông tin!", 
                        "Cảnh báo", JOptionPane.ERROR_MESSAGE);

                } else {
                    list_SP.them(sp);
                    list_SP.docDB();

                    JOptionPane.showMessageDialog(null, "Lưu sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    actionButtondisplay();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lưu sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xóa sản phẩm
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chứ!!");
        if (a == JOptionPane.YES_OPTION) {
            try {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setStrMaGiay(tfMaGiay.getText());

                list_SP.xoa(sp);
                list_SP.docDB();

                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                actionButtondisplay();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    /**
     * Combobox Thông Tin
     */
    public void nhapThongTin_MaLoai() throws Exception{
        LoaiBUS bus =new LoaiBUS();
        ArrayList<LoaiDTO> arr =bus.getList_Loai();
        String[] s = new String[bus.getNumbLoai()+1];
        s[0]="--Chọn--";
        int i = 1;
        for (LoaiDTO dto : arr) {
            
            s[i] = dto.getStrMaloai();
            i++;
           
        }
        cbbThongtin_MaLoai.setModel(new DefaultComboBoxModel<>(s));
    }

    public void nhapThongTin_MaXX() throws Exception{
        XuatXuBUS xx =new XuatXuBUS();
        ArrayList<XuatXuDTO> arr =xx.getList_XuatXu();
        String[] s = new String[xx.getNumbXuatXu()+1];
        s[0]="--Chọn--";
        int i = 1;
        for (XuatXuDTO dto : arr) {
            s[i] = dto.getStrMaxuatxu();
            i++;
           
        }
        cbbThongtin_MaXX.setModel(new DefaultComboBoxModel<>(s));
    }
    
    public void nhapThongTin_MauSac() throws Exception{
        MauSacBUS ms =new MauSacBUS();
        ArrayList<MauSacDTO> arr =ms.getList_MauSac();
        String[] s = new String[ms.getNumbMauSac()+1];
        s[0]="--Chọn--";
        int i = 1;
        for (MauSacDTO dto : arr) {
            s[i] = dto.getStrMamau();
            i++;
           
        }
        cbbThongtin_MaMau.setModel(new DefaultComboBoxModel<>(s));
    }
    public void nhapThongTin_MaThuongHieu() throws Exception{
        ThuongHieuBUS th =new ThuongHieuBUS();
        ArrayList<ThuongHieuDTO> arr =th.getList_ThuongHieu();
        String[] s = new String[th.getNumbThuongHieu()+1];
        s[0]="--Chọn--";
        int i = 1;
        for (ThuongHieuDTO dto : arr) {
            s[i] = dto.getStrMathuonghieu();
            i++;
           
        }
        cbbThongtin_MaThuongHieu.setModel(new DefaultComboBoxModel<>(s));
    }
    /**
     * Combobox Tìm Kiếm
     */
    public void nhapTimKiem_MaLoai() throws Exception{
        LoaiBUS bus =new LoaiBUS();
        ArrayList<LoaiDTO> arr =bus.getList_Loai();
        String[] s = new String[bus.getNumbLoai()+1];
        s[0]="--Chọn--";
        int i = 1;
        for (LoaiDTO dto : arr) {
            s[i] = dto.getStrMaloai();
            i++;
           
        }
        cbbTimKiem_MaLoai.setModel(new DefaultComboBoxModel<>(s));
    }

    public void nhapTimKiem_MaXX() throws Exception{
        XuatXuBUS xx =new XuatXuBUS();
        ArrayList<XuatXuDTO> arr =xx.getList_XuatXu();
        String[] s = new String[xx.getNumbXuatXu()+1];
        s[0]="--Chọn--";
        int i = 1;
        for (XuatXuDTO dto : arr) {
            s[i] = dto.getStrMaxuatxu();
            i++;
           
        }
        cbbTimKiem_MaXX.setModel(new DefaultComboBoxModel<>(s));
    }
    
    public void nhapTimKiem_MauSac() throws Exception{
        MauSacBUS ms =new MauSacBUS();
        ArrayList<MauSacDTO> arr =ms.getList_MauSac();
        String[] s = new String[ms.getNumbMauSac()+1];
        s[0]="--Chọn--";
        int i = 1;
        for (MauSacDTO dto : arr) {
            s[i] = dto.getStrMamau();
            i++;
           
        }
        cbbTimKiem_MaMau.setModel(new DefaultComboBoxModel<>(s));
    }
    public void nhapTimKiem_MaThuongHieu() throws Exception{
        ThuongHieuBUS th =new ThuongHieuBUS();
        ArrayList<ThuongHieuDTO> arr =th.getList_ThuongHieu();
        String[] s = new String[th.getNumbThuongHieu()+1];
        s[0]="--Chọn--";
        int i = 1;
        for (ThuongHieuDTO dto : arr) {
            s[i] = dto.getStrMathuonghieu();
            i++;
           
        }
        cbbTimKiem_MaThuongHieu.setModel(new DefaultComboBoxModel<>(s));
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
            header.add("Mã Giày");
            header.add("Tên Giày");
            header.add("Số lượng");
            header.add("Giá");
            header.add("Size");
            header.add("Đối tượng sd");
            header.add("Chất liệu");
            header.add("Mã Loại");
            header.add("Mã Xuất Xứ");
            header.add("Mã Màu");
            header.add("Mã Thương hiệu");
            
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
        for (int i = 0; i < list_SP.getNumbSanPham(); i++) {
            SanPhamDTO sp = list_SP.getInfor(i);
                        
            if (sp.getDeleted() == 0) {
                Vector row = new Vector();
                row.add(sp.getStrMaGiay());
                row.add(sp.getStrTenGiay());
                row.add(sp.getiSoLuong());
                row.add(sp.getiGia());
                row.add(sp.getiSize());
                row.add(sp.getStrDoiTuongSD());
                row.add(sp.getStrChatLieu());
                row.add(sp.getStrMaLoai());
                row.add(sp.getStrMaxx());
                row.add(sp.getStrMaMau());
                row.add(sp.getStrMaThuongHieu());

                model.addRow(row);
            }
        }

        tblThongTin.setModel(model);
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
        String currentMenu = "Hàng hóa";

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
                    Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
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
            if (menuName != null && menuName.equals("Hàng hóa")) {
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
            java.util.logging.Logger.getLogger(Sanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Sanpham().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
