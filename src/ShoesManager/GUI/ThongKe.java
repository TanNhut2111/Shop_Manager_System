package ShoesManager.GUI;

import ShoesManager.BUS.HoaDonBUS;
import ShoesManager.BUS.NhanVienBUS;
import ShoesManager.BUS.PhieuNhapBUS;
import ShoesManager.DTO.HoaDonDTO;
import ShoesManager.DTO.NhanVienDTO;
import ShoesManager.DTO.PhieuNhapDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import java.util.logging.Logger;

// Các class Java chuẩn
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.title.TextTitle;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;

// Cho biểu đồ cột
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

// Cho biểu đồ đường
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

// Cho biểu đồ tròn
import org.jfree.data.general.DefaultPieDataset;

public class ThongKe extends javax.swing.JFrame implements MouseListener {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser ThongKe_dateFrom;
    private com.toedter.calendar.JDateChooser ThongKe_dateTo;
    private com.toedter.calendar.JYearChooser YearChooser_Nam;
    private com.toedter.calendar.JYearChooser YearChooser_NamQuy;
    private com.toedter.calendar.JYearChooser YearChooser_NamThang;
    private javax.swing.JButton btnThongKe;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbMonth;
    private javax.swing.JComboBox<String> cbbQuy;
    // private javax.swing.JButton jButton4;
    // private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menuTaiKhoan;
    private javax.swing.JMenuItem menuTaiKhoan_DangXuat;
    private javax.swing.JMenuItem menuTaiKhoan_ThongTin;
    private javax.swing.JMenu menuTroVe;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblChiTiet_ChiTietHoaDon;
    private javax.swing.JLabel lblMuiTen2;
    private javax.swing.JPanel pCenter;
    private javax.swing.JPanel pChiTiet;
    private javax.swing.JPanel pChiTietTimKiem;
    private javax.swing.JPanel pTop;
    private javax.swing.JRadioButton rdbThongKe_Nam;
    private javax.swing.JRadioButton rdbThongKe_Ngay;
    private javax.swing.JRadioButton rdbThongKe_Quy;
    private javax.swing.JRadioButton rdbThongKe_Thang;
    private javax.swing.JTable tblThongKe_HoaDon;
    private javax.swing.JTable tblThongKe_PhieuNhap;

    private javax.swing.JButton btnBieuDoCot;
    private javax.swing.JButton btnBieuDoDuong;
    private javax.swing.JButton btnBieuDoTron;
    // End of variables declaration//GEN-END:variables

    public ThongKe() throws Exception {
        this.setUndecorated(true);

        init();
        initComponents();
        // các hàm xử lý sau khi vào
        kiemTraCapBac();
        actionButtondisplayHoaDon();
        actionButtondisplayPhieuNhap();
    }

    private JLabel[] listLblTop;
    private String[] strArr_Top;
    private DefaultTableModel model;
    private HoaDonBUS list_HD;
    private PhieuNhapBUS list_PN;

    public void init() throws Exception {
        list_HD = new HoaDonBUS();
        list_PN = new PhieuNhapBUS();
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

        btnBieuDoCot = new javax.swing.JButton();
        btnBieuDoDuong = new javax.swing.JButton();
        btnBieuDoTron = new javax.swing.JButton();

        buttonGroup2 = new javax.swing.ButtonGroup();
        pCenter = new javax.swing.JPanel();
        pChiTiet = new javax.swing.JPanel();
        lblChiTiet_ChiTietHoaDon = new javax.swing.JLabel();
        pChiTietTimKiem = new javax.swing.JPanel();
        // jButton4 = new javax.swing.JButton();
        // jButton5 = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        lblMuiTen2 = new javax.swing.JLabel();
        ThongKe_dateFrom = new com.toedter.calendar.JDateChooser();
        ThongKe_dateTo = new com.toedter.calendar.JDateChooser();
        cbbQuy = new javax.swing.JComboBox<>();
        YearChooser_Nam = new com.toedter.calendar.JYearChooser();
        rdbThongKe_Ngay = new javax.swing.JRadioButton();
        rdbThongKe_Quy = new javax.swing.JRadioButton();
        rdbThongKe_Thang = new javax.swing.JRadioButton();
        rdbThongKe_Nam = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        YearChooser_NamQuy = new com.toedter.calendar.JYearChooser();
        jLabel2 = new javax.swing.JLabel();
        YearChooser_NamThang = new com.toedter.calendar.JYearChooser();
        cbbMonth = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThongKe_HoaDon = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThongKe_PhieuNhap = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Hệ Thống Quản Lý Bán Giày - Dashboard");
        setSize(1650, 850);
        setMinimumSize(new Dimension(1550, 800));
        setResizable(true);
        setUndecorated(false);
        setLocationRelativeTo(null);

        Color xanhNen = new Color(245, 247, 250); // Modern light gray background
        getContentPane().setBackground(xanhNen);
        // pCenter.setBackground(xanhNen);
        pChiTiet.setBackground(xanhNen);
        // pTop.setBackground(xanhNen);
        pChiTietTimKiem.setBackground(xanhNen);

        pCenter.setPreferredSize(new java.awt.Dimension(1280, 600));
        pChiTiet.setPreferredSize(new java.awt.Dimension(1239, 569));
        
        // lblChiTiet_ChiTietHoaDon.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        // lblChiTiet_ChiTietHoaDon.setText("Thống Kê");

        pChiTietTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh Thu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18))); // NOI18N
        pChiTietTimKiem.setPreferredSize(new java.awt.Dimension(450, 238));
        jPanel1.setPreferredSize(new java.awt.Dimension(670, jPanel1.getPreferredSize().height)); 


        // jButton4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        // jButton4.setText("Hủy Thống Kê");
        // jButton4.addActionListener(new java.awt.event.ActionListener() {
        //     public void actionPerformed(java.awt.event.ActionEvent evt) {
        //         jButton4ActionPerformed(evt);
        //     }
        // });

        // jButton5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        // jButton5.setText("Trống");

        btnThongKe.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThongKe.setText("Thống Kê");
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
        });

        lblMuiTen2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblMuiTen2.setText("->");

        ThongKe_dateFrom.setDateFormatString("dd / MM / yyyy");

        ThongKe_dateTo.setDateFormatString("dd / MM / yyyy");

        cbbQuy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quý 1", "Quý 2", "Quý 3", "Quý 4" }));
        cbbQuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbQuyActionPerformed(evt);
            }
        });

        Color radio = new Color(131, 189, 246); // Xanh dương nhẹ

        buttonGroup2.add(rdbThongKe_Ngay);
        rdbThongKe_Ngay.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        rdbThongKe_Ngay.setSelected(true);
        rdbThongKe_Ngay.setText("Từ Ngày");

        buttonGroup2.add(rdbThongKe_Quy);
        rdbThongKe_Quy.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        rdbThongKe_Quy.setText("Theo Quý");

        buttonGroup2.add(rdbThongKe_Thang);
        rdbThongKe_Thang.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        rdbThongKe_Thang.setText("Theo Tháng");

        buttonGroup2.add(rdbThongKe_Nam);
        rdbThongKe_Nam.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        rdbThongKe_Nam.setText("Theo Năm");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Năm");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Năm");

        cbbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Tổng Thu:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Tổng Chi:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setText("Lợi nhuận:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnBieuDoCot.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnBieuDoCot.setText("Biểu Đồ Cột");
        btnBieuDoCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoCotActionPerformed(evt);
            }
        });
        btnBieuDoDuong.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnBieuDoDuong.setText("Biểu Đồ Đường");
        btnBieuDoDuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoDuongActionPerformed(evt);
            }
        });
        btnBieuDoTron.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnBieuDoTron.setText("Biểu Đồ Tròn");
        btnBieuDoTron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoTronActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pChiTietTimKiemLayout = new javax.swing.GroupLayout(pChiTietTimKiem);
        pChiTietTimKiem.setLayout(pChiTietTimKiemLayout);

        pChiTietTimKiemLayout.setHorizontalGroup(
            pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                    .addGap(24)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                            .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdbThongKe_Ngay)
                                .addComponent(rdbThongKe_Quy)
                                .addComponent(rdbThongKe_Thang)
                                .addComponent(rdbThongKe_Nam))
                            .addGap(23)
                            .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                                    .addComponent(ThongKe_dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(lblMuiTen2)
                                    .addGap(18)
                                    .addComponent(ThongKe_dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                                    .addComponent(cbbQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(jLabel1)
                                    .addGap(18)
                                    .addComponent(YearChooser_NamQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                                    .addComponent(cbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(jLabel2)
                                    .addGap(18)
                                    .addComponent(YearChooser_NamThang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(YearChooser_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(40, Short.MAX_VALUE))
                // Căn giữa nút Thống kê
                .addGroup(javax.swing.GroupLayout.Alignment.CENTER,
                    pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(btnThongKe)
                )
                // Căn giữa 3 nút biểu đồ
                .addGroup(javax.swing.GroupLayout.Alignment.CENTER,
                    pChiTietTimKiemLayout.createSequentialGroup()
                        .addComponent(btnBieuDoCot)
                        .addGap(18)
                        .addComponent(btnBieuDoDuong)
                        .addGap(18)
                        .addComponent(btnBieuDoTron)
                )
                // Nhóm Tổng thu – Tổng chi – Lợi nhuận
                .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                    .addGap(98)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(16)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(16)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(10)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pChiTietTimKiemLayout.setVerticalGroup(
            pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pChiTietTimKiemLayout.createSequentialGroup()
                    .addGap(40)
                    // --- Các lựa chọn thời gian ---
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbThongKe_Ngay)
                            .addComponent(lblMuiTen2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ThongKe_dateFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(ThongKe_dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbThongKe_Quy)
                        .addComponent(cbbQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(YearChooser_NamQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbThongKe_Thang)
                        .addComponent(cbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(YearChooser_NamThang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbThongKe_Nam)
                        .addComponent(YearChooser_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    // --- Nút Thống kê ---
                    .addGap(25)
                    .addComponent(btnThongKe)
                    // --- 3 nút biểu đồ ---
                    .addGap(20)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBieuDoCot)
                        .addComponent(btnBieuDoDuong)
                        .addComponent(btnBieuDoTron))
                    // --- Hiển thị Tổng thu – Tổng chi – Lợi nhuận ---
                    .addGap(50)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGap(40)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGap(40)
                    .addGroup(pChiTietTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGap(40))
        );

        tblThongKe_HoaDon.setAutoCreateRowSorter(true);
        tblThongKe_HoaDon.setBackground(new java.awt.Color(204, 204, 204));
        tblThongKe_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã HĐ", "Số Lượng", "Giá Bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongKe_HoaDon.setGridColor(new java.awt.Color(102, 255, 102));
        tblThongKe_HoaDon.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblThongKe_HoaDon.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(tblThongKe_HoaDon);
        if (tblThongKe_HoaDon.getColumnModel().getColumnCount() > 0) {
            tblThongKe_HoaDon.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblThongKe_HoaDon.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblThongKe_HoaDon.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblThongKe_HoaDon.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Bảng Hóa Đơn (Tiền Thu)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Bảng Phiếu Nhập (Tiền Chi)");

        tblThongKe_PhieuNhap.setBackground(new java.awt.Color(204, 204, 204));
        tblThongKe_PhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã HĐ", "Số Lượng", "Giá Bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongKe_PhieuNhap.setGridColor(new java.awt.Color(102, 255, 102));
        tblThongKe_PhieuNhap.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblThongKe_PhieuNhap.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tblThongKe_PhieuNhap);
        if (tblThongKe_PhieuNhap.getColumnModel().getColumnCount() > 0) {
            tblThongKe_PhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblThongKe_PhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblThongKe_PhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblThongKe_PhieuNhap.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Tiền HD cao nhất:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Tiền HD thấp nhất:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Trung Bình HD:");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Tiền PN cao nhất:");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Tiền PN thấp nhất:");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Trung Bình PN:");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(jLabel5)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(jLabel20))))))
                                        .addContainerGap())
            );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout pChiTietLayout = new javax.swing.GroupLayout(pChiTiet);
            pChiTiet.setLayout(pChiTietLayout);
                pChiTietLayout.setHorizontalGroup(
                pChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pChiTietTimKiem, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 670, GroupLayout.PREFERRED_SIZE)
                .addContainerGap()
        );

        pChiTietLayout.setVerticalGroup(
            pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pChiTietLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pChiTietTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );

        javax.swing.GroupLayout pCenterLayout = new javax.swing.GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
            pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pCenterLayout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(pChiTiet, 670, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(20))
        );
        pCenterLayout.setVerticalGroup(
            pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pCenterLayout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(pChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
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
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked
        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list_PN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rdbThongKe_Ngay.isSelected()) {
            String[] s = String.valueOf(ThongKe_dateFrom.getDate()).split("\\ ");
            System.out.println(s[2] + " " + s[1] + " " + s[5]);
            System.out.println(ThongKe_dateFrom.getDate());
            String date1 = s[2] + " " + s[1] + " " + s[5];

            String[] s1 = String.valueOf(ThongKe_dateTo.getDate()).split("\\ ");
            System.out.println(s1[2] + " " + s1[1] + " " + s1[5]);
            System.out.println(ThongKe_dateFrom.getDate());
            String date2 = s1[2] + " " + s1[1] + " " + s1[5];
            thongke(date1, date2);
         
        }
        if (rdbThongKe_Quy.isSelected()) {
            String Start_quy1 = null;
            String End_quy1 = null;
            int NamQuy;
            if (cbbQuy.getSelectedItem().equals("Quý 1")) {
                Start_quy1 = "1 Jan";
                End_quy1 = "31 Mar";

            } else {
                if (cbbQuy.getSelectedItem().equals("Quý 2")) {
                    Start_quy1 = "1 Apr";
                    End_quy1 = "30 Jun";
                } else {
                    if (cbbQuy.getSelectedItem().equals("Quý 3")) {
                        Start_quy1 = "1 Jul";
                        End_quy1 = "30 Sep";
                    } else {
                        Start_quy1 = "1 Oct";
                        End_quy1 = "31 Dec";
                    }
                }
            }
            NamQuy = YearChooser_NamQuy.getYear();
            System.out.println(NamQuy);
            String date1 = Start_quy1 + " " + NamQuy;
            String date2 = End_quy1 + " " + NamQuy;
            System.out.println(date1);
            System.out.println(date2);
            thongke(date1, date2);

        }
        if (rdbThongKe_Thang.isSelected()) {
            int NamThang;
            String month;
            if (cbbMonth.getSelectedItem().equals("Jan")) {
                month = "Jan";
            } else {
                if (cbbMonth.getSelectedItem().equals("Feb")) {
                    month = "Feb";
                } else {
                    if (cbbMonth.getSelectedItem().equals("Mar")) {
                        month = "Mar";
                    } else {
                        if (cbbMonth.getSelectedItem().equals("Apr")) {
                            month = "Apr";
                        } else {
                            if (cbbMonth.getSelectedItem().equals("May")) {
                                month = "May";
                            } else {
                                if (cbbMonth.getSelectedItem().equals("Jun")) {
                                    month = "Jun";
                                } else {
                                    if (cbbMonth.getSelectedItem().equals("Jul")) {
                                        month = "Jul";
                                    } else {
                                        if (cbbMonth.getSelectedItem().equals("Aug")) {
                                            month = "Aug";
                                        } else {
                                            if (cbbMonth.getSelectedItem().equals("Sep")) {
                                                month = "Sep";
                                            } else {
                                                if (cbbMonth.getSelectedItem().equals("Oct")) {
                                                    month = "Oct";
                                                } else {
                                                    if (cbbMonth.getSelectedItem().equals("Nov")) {
                                                        month = "Nov";
                                                    } else {
                                                        month = "Dec";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            NamThang = YearChooser_NamThang.getYear();
            System.out.println(NamThang);
            String monthyear = month + " " + NamThang;
            System.out.println(monthyear);
            thongkethang(monthyear);
        }

        if (rdbThongKe_Nam.isSelected()) {
            int nam = YearChooser_Nam.getYear();
            String year = "" + nam;
            System.out.println(year);
            thongkenam(year);
        }

        System.out.println("ưhatups");
        actionButtondisplayHoaDon();
        actionButtondisplayPhieuNhap();
    }//GEN-LAST:event_btnThongKeMouseClicked

    private void thongke(String date1, String date2) {
        CacHamQuanTrong ok = new CacHamQuanTrong();
        if (ok.kiemTraDateTK(date1, date2)) {
            ArrayList<HoaDonDTO> arr = new ArrayList<>();
            ArrayList<PhieuNhapDTO> arr1 = new ArrayList<>();
            HoaDonDTO hd = new HoaDonDTO();
            PhieuNhapDTO pn = new PhieuNhapDTO();
            try {
                list_HD.docDB();
            } catch (Exception ex) {
                Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                list_PN.docDB();
            } catch (Exception ex) {
                Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
            double tongtienThu = 0;
            double maxtien=0;
            double mintien=0;
            int dem=0;
            for (int i = 0; i < list_HD.getNumbHoaDon(); i++) {
                hd = list_HD.getInfor(i);
                String sXuat = hd.getStrNgayBan();

                System.out.println("ngay bat dau" + date1);
                System.out.println("ngay hoa don" + hd.getStrNgayBan());
                System.out.println("ngay ket thuc" + date2);
                if (ok.kiemTraDateTK(date1, sXuat)
                        && ok.kiemTraDateTK(sXuat, date2)) {
                    double tien = hd.getTongTien();
                    tongtienThu += tien;
                    
                    if (dem == 0) {
                        maxtien = mintien = tien;
                    } else {
                        if (tien > maxtien) maxtien = tien;
                        if (tien < mintien) mintien = tien;
                    }

                    dem++;
                    arr.add(hd);
                }
            }
            double tongtienChi = 0;
            double maxtien1=0;
            double mintien1=0;
            int dem1=0;
            for (int i = 0; i < list_PN.getNumb(); i++) {
                pn = list_PN.getInfor(i);
                String sNhap = pn.getStrNgayNhap();
                System.out.println("ngay bat dau" + date1);
                System.out.println("ngay hoa don" + pn.getStrNgayNhap());
                System.out.println("ngay ket thuc" + date2);
                if (ok.kiemTraDateTK(date1, sNhap)
                        && ok.kiemTraDateTK(sNhap, date2)) {
                    double tien = pn.getTongTien();
                    tongtienChi = tongtienChi + tien;
                    if(tien>maxtien1) maxtien1=tien; else mintien1=tien;
                    dem1=dem1+1;
                    arr1.add(pn);
                    System.out.println(arr1);
                }
            }
            String tien2 = "" + tongtienThu;
            String tien3 = "" + tongtienChi;
            double ln = tongtienThu - tongtienChi;
            String loinhuan = "" + ln;
            jLabel3.setText(tien2);
            jLabel9.setText(tien3);
            jLabel10.setText(loinhuan);
            jLabel13.setText(String.valueOf(maxtien));
            jLabel15.setText(String.valueOf(mintien));
            jLabel17.setText(String.valueOf(Math.round((tongtienThu/dem)*1000.0)/1000.0));
            jLabel19.setText(String.valueOf(maxtien1));
            jLabel21.setText(String.valueOf(mintien1));
            jLabel23.setText(String.valueOf(Math.round((tongtienChi/dem1)*1000.0)/1000.0));
            list_HD.setList_HD(arr);
            list_PN.setList_PN(arr1);
        } else {
            System.out.println("ko dc");
        }
    }

    private void thongkethang(String monthyear) {
        CacHamQuanTrong ok = new CacHamQuanTrong();
        ArrayList<HoaDonDTO> arr = new ArrayList<>();
        ArrayList<PhieuNhapDTO> arr1 = new ArrayList<>();
        HoaDonDTO hd = new HoaDonDTO();
        PhieuNhapDTO pn = new PhieuNhapDTO();
        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list_PN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        double tongtienThu = 0;
        double maxtien=0;
            double mintien=0;
            int dem=0;
        for (int i = 0; i < list_HD.getNumbHoaDon(); i++) {
            hd = list_HD.getInfor(i);
            String sXuat = hd.getStrNgayBan();
            if (ok.kiemtrathangTK(monthyear, sXuat)) {
                double tien = hd.getTongTien();
                tongtienThu += tien;
        
                if (dem == 0) {
                    maxtien = mintien = tien;
                } else {
                    if (tien > maxtien) maxtien = tien;
                    if (tien < mintien) mintien = tien;
                }

                dem++;
                arr.add(hd);
            }
        }
        double tongtienChi = 0;
        double maxtien1=0;
            double mintien1=0;
            int dem1=0;
        for (int i = 0; i < list_PN.getNumb(); i++) {
            pn = list_PN.getInfor(i);
            String sNhap = pn.getStrNgayNhap();
            if (ok.kiemtrathangTK(monthyear, sNhap)) {
                double tien = pn.getTongTien();
                tongtienChi = tongtienChi + tien;
                if(tien>maxtien1) maxtien1=tien; else mintien1=tien;
                    dem1=dem1+1;
                arr1.add(pn);
                System.out.println(arr1);
            }
        }
        String tien2 = "" + tongtienThu;
        String tien3 = "" + tongtienChi;
        double ln = tongtienThu - tongtienChi;
        String loinhuan = "" + ln;
        jLabel3.setText(tien2);
        jLabel9.setText(tien3);
        jLabel10.setText(loinhuan);
        jLabel13.setText(String.valueOf(maxtien));
        jLabel15.setText(String.valueOf(mintien));
        jLabel17.setText(String.valueOf(Math.round((tongtienThu/dem)*1000.0)/1000.0));
        jLabel19.setText(String.valueOf(maxtien1));
        jLabel21.setText(String.valueOf(mintien1));
        jLabel23.setText(String.valueOf(Math.round((tongtienChi/dem1)*1000.0)/1000.0));
        list_HD.setList_HD(arr);
        list_PN.setList_PN(arr1);
    }

    private void thongkenam(String year) {
        CacHamQuanTrong ok = new CacHamQuanTrong();
        ArrayList<HoaDonDTO> arr = new ArrayList<>();
        ArrayList<PhieuNhapDTO> arr1 = new ArrayList<>();
        HoaDonDTO hd = new HoaDonDTO();
        PhieuNhapDTO pn = new PhieuNhapDTO();
        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list_PN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        double tongtienThu = 0;
        double maxtien=0;
            double mintien=0;
            int dem=0;
        for (int i = 0; i < list_HD.getNumbHoaDon(); i++) {
            hd = list_HD.getInfor(i);
            String sXuat = hd.getStrNgayBan();
            if (ok.kiemtranamTK(year, sXuat)) {
                double tien = hd.getTongTien();
                tongtienThu += tien;
        
                if (dem == 0) {
                    maxtien = mintien = tien;
                } else {
                    if (tien > maxtien) maxtien = tien;
                    if (tien < mintien) mintien = tien;
                }

                dem++;
                arr.add(hd);
            }
        }
        double tongtienChi = 0;
        double maxtien1=0;
            double mintien1=0;
            int dem1=0;
        for (int i = 0; i < list_PN.getNumb(); i++) {
            pn = list_PN.getInfor(i);
            String sNhap = pn.getStrNgayNhap();
            if (ok.kiemtranamTK(year, sNhap)) {
                double tien = pn.getTongTien();
                tongtienChi = tongtienChi + tien;
                if(tien>maxtien1) maxtien1=tien; else mintien1=tien;
                    dem1=dem1+1;
                arr1.add(pn);
                System.out.println(arr1);
            }
        }
        String tien2 = "" + tongtienThu;
        String tien3 = "" + tongtienChi;
        double ln = tongtienThu - tongtienChi;
        String loinhuan = "" + ln;
        jLabel3.setText(tien2);
        jLabel9.setText(tien3);
        jLabel10.setText(loinhuan);
        jLabel13.setText(String.valueOf(maxtien));
        jLabel15.setText(String.valueOf(mintien));
        jLabel17.setText(String.valueOf(Math.round((tongtienThu/dem)*1000.0)/1000.0));
        jLabel19.setText(String.valueOf(maxtien1));
        jLabel21.setText(String.valueOf(mintien1));
        jLabel23.setText(String.valueOf(Math.round((tongtienChi/dem1)*1000.0)/1000.0));
        list_HD.setList_HD(arr);
        list_PN.setList_PN(arr1);
    }
    // private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    //     try {
    //         // TODO add your handling code here:
    //         list_HD.docDB();
    //     } catch (Exception ex) {
    //         Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
    //     }
    //     actionButtondisplayHoaDon();
    // }//GEN-LAST:event_jButton4ActionPerformed

    private void cbbQuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbQuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbQuyActionPerformed

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
        if (model.getRowCount() == 0) {
            Vector<String> header = new Vector();
            header.add("Mã HĐ");
            header.add("Mã KH");
            header.add("Mã NV");
            //header.add("Mã KM");
            header.add("Ngày Bán");
            header.add("Tổng Tiền");

            model = new DefaultTableModel(header, 0) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };;
        }
    }

    private void actionButtondisplayHoaDon() {
        model = new DefaultTableModel();

        createVectorHeader();
        for (int i = 0; i < list_HD.getNumbHoaDon(); i++) {
            HoaDonDTO hd = new HoaDonDTO();

            hd = list_HD.getInfor(i);

            createVectorHeader();

            Vector row = new Vector();
            row.add(hd.getStrMaHD());
            row.add(hd.getStrMaKH());
            row.add(hd.getStrMaNV());
            row.add(hd.getStrNgayBan());
            row.add(hd.getTongTien());

            model.addRow(row);
        }

        tblThongKe_HoaDon.setModel(model);
    }

    private void createVectorHeaderPN() {
        if (model.getRowCount() == 0) {
            Vector<String> header = new Vector();
            header.add("Mã PN");
            header.add("Mã NV");
            header.add("Mã NCC");
            header.add("Ngày Nhap");
            header.add("Tổng Tiền");

            model = new DefaultTableModel(header, 0) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };;
        }
    }

    private void actionButtondisplayPhieuNhap() {
        model = new DefaultTableModel();

        createVectorHeaderPN();
        for (int i = 0; i < list_PN.getNumb(); i++) {
            PhieuNhapDTO pn = new PhieuNhapDTO();

            pn = list_PN.getInfor(i);

            createVectorHeaderPN();

            Vector row = new Vector();
            row.add(pn.getStrMaPN());
            row.add(pn.getStrMaNV());
            row.add(pn.getStrMaNCC());
            row.add(pn.getStrNgayNhap());
            row.add(pn.getTongTien());

            model.addRow(row);
        }

        tblThongKe_PhieuNhap.setModel(model);
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
        String currentMenu = "Thống kê";

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
                    Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
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

    private void btnBieuDoCotActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            double tongThu = Double.parseDouble(jLabel3.getText());
            double tongChi = Double.parseDouble(jLabel9.getText());
            double loiNhuan = Double.parseDouble(jLabel10.getText());

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(tongThu, "Tổng Thu", "Doanh Thu");
            dataset.addValue(tongChi, "Tổng Chi", "Doanh Thu");
            dataset.addValue(loiNhuan, "Lợi Nhuận", "Doanh Thu");

            JFreeChart chart = ChartFactory.createBarChart(
                    "Biểu đồ cột - Tổng Thu / Chi / Lợi Nhuận",
                    "", "Giá trị (VNĐ)",
                    dataset, PlotOrientation.VERTICAL,
                    true, true, false
            );

            // Cải thiện font
            Font fontAxis = new Font("Segoe UI", Font.PLAIN, 14);
            Font fontLegend = new Font("Segoe UI", Font.PLAIN, 13);

             TextTitle title = new TextTitle("Biểu đồ cột - Tổng Thu / Chi / Lợi Nhuận");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setPaint(Color.BLUE);
            chart.setTitle(title);
            chart.getLegend().setItemFont(fontLegend);

            CategoryPlot plot = chart.getCategoryPlot();
            plot.getDomainAxis().setLabelFont(fontAxis);
            plot.getRangeAxis().setLabelFont(fontAxis);
            plot.getDomainAxis().setTickLabelFont(fontAxis);
            plot.getRangeAxis().setTickLabelFont(fontAxis);
            plot.setRangeGridlinePaint(Color.GRAY);

            ChartPanel chartPanel = new ChartPanel(chart);
            JFrame frame = new JFrame("Biểu đồ cột - Doanh Thu & Chi Phí");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(chartPanel);
            frame.pack();
            frame.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng thống kê trước khi xem biểu đồ!");
        }
    }

    private void btnBieuDoDuongActionPerformed(java.awt.event.ActionEvent evt) {
        XYSeries seriesThu = new XYSeries("Tổng Thu");
        XYSeries seriesChi = new XYSeries("Tổng Chi");

        ArrayList<HoaDonDTO> dsHD = list_HD.getList_HD();
        ArrayList<PhieuNhapDTO> dsPN = list_PN.getList_PN();

        for (int i = 0; i < dsHD.size(); i++) {
            seriesThu.add(i + 1, dsHD.get(i).getTongTien());
        }
        for (int i = 0; i < dsPN.size(); i++) {
            seriesChi.add(i + 1, dsPN.get(i).getTongTien());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesThu);
        dataset.addSeries(seriesChi);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Biểu đồ xu hướng - Doanh Thu & Chi Phí",
                "Số thứ tự giao dịch", "Giá trị (VNĐ)",
                dataset, PlotOrientation.VERTICAL,
                true, true, false
        );

        // 🎨 Chỉnh font
        Font fontAxis = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontLegend = new Font("Segoe UI", Font.PLAIN, 13);

        TextTitle title = new TextTitle("Biểu đồ xu hướng - Doanh Thu & Chi Phí");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setPaint(Color.BLUE);
        chart.setTitle(title);
        chart.getLegend().setItemFont(fontLegend);

        XYPlot plot = chart.getXYPlot();
        plot.getDomainAxis().setLabelFont(fontAxis);
        plot.getRangeAxis().setLabelFont(fontAxis);
        plot.getDomainAxis().setTickLabelFont(fontAxis);
        plot.getRangeAxis().setTickLabelFont(fontAxis);
        plot.setRangeGridlinePaint(Color.GRAY);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Biểu đồ xu hướng");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnBieuDoTronActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            double tongThu = Double.parseDouble(jLabel3.getText());
            double tongChi = Double.parseDouble(jLabel9.getText());
            double loiNhuan = Double.parseDouble(jLabel10.getText());

            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("Tổng Thu", tongThu);
            dataset.setValue("Tổng Chi", tongChi);
            dataset.setValue("Lợi Nhuận", loiNhuan);

            JFreeChart chart = ChartFactory.createPieChart(
                    "Biểu đồ tròn - Tỷ lệ Thu / Chi / Lợi Nhuận",
                    dataset, true, true, false
            );

            // 🎨 Font đẹp và màu nổi bật
            Font fontLegend = new Font("Segoe UI", Font.PLAIN, 13);

            TextTitle title = new TextTitle("Biểu đồ tròn - Tỷ lệ Thu / Chi / Lợi Nhuận");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setPaint(Color.BLUE);
            chart.setTitle(title);
            chart.getLegend().setItemFont(fontLegend);

            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelFont(new Font("Segoe UI", Font.PLAIN, 14));
            plot.setBackgroundPaint(Color.WHITE);
            plot.setSectionOutlinesVisible(false);

            ChartPanel chartPanel = new ChartPanel(chart);
            JFrame frame = new JFrame("Biểu đồ tròn - Tỷ lệ Thu/Chi");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(chartPanel);
            frame.pack();
            frame.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng thống kê trước khi xem biểu đồ!");
        }
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
            if (menuName != null && menuName.equals("Thống kê")) {
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
                    new ThongKe().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
