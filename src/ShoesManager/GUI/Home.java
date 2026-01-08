package ShoesManager.GUI;

import ShoesManager.BUS.HoaDonBUS;
import ShoesManager.BUS.NhanVienBUS;
import ShoesManager.BUS.SanPhamBUS;
import ShoesManager.DTO.HoaDonDTO;
import ShoesManager.DTO.NhanVienDTO;
import ShoesManager.DTO.SanPhamDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home extends JFrame implements MouseListener {
    private NhanVienBUS list_nv;
    private NhanVienDTO nhanvien;
    private JPanel pnTop, pnBottom;
    private JLabel[] listLblTop;

    public Home() throws Exception {
        init();
    }

    public void init() throws Exception {
        // JFrame
        this.setUndecorated(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Trang chủ - Hệ thống Quản lý Cửa hàng Giày");
        this.setSize(1500, 800);
        this.setMinimumSize(new Dimension(1350, 750));
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        list_nv = new NhanVienBUS();
        nhanvien = list_nv.getNhanVien_MaNV(Memory.maNV);
        Memory.nhanvien = nhanvien;

        // Header - Matching other forms
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
        getContentPane().setBackground(new Color(245, 247, 250));

        // Sidebar menu
        String[] menuItems = getMenuItemsByRole();
        pnTop = createPanel_LblLeft(menuItems);
        pnTop.setPreferredSize(new Dimension(220, 0)); 
        getContentPane().add(pnTop, BorderLayout.WEST);

        // Main content area - Dashboard
        pnBottom = new JPanel();
        pnBottom.setBackground(new Color(245, 247, 250));
        pnBottom.setLayout(new BorderLayout());
        pnBottom.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Scroll pane for main content
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 247, 250));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Summary Cards Row
        JPanel summaryCardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        summaryCardsPanel.setBackground(new Color(245, 247, 250));
        summaryCardsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryCardsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        try {
            // Get statistics
            SanPhamBUS spBUS = new SanPhamBUS();
            HoaDonBUS hdBUS = new HoaDonBUS();
            
            int totalProducts = spBUS.getNumbSanPham();
            int totalInvoices = hdBUS.getNumbHoaDon();
            
            // Calculate total revenue
            double totalRevenue = 0;
            for (HoaDonDTO hd : hdBUS.getList_HD()) {
                totalRevenue += hd.getTongTien();
            }
            
            // Calculate total inventory
            int totalInventory = 0;
            for (SanPhamDTO sp : spBUS.getList_SP()) {
                totalInventory += sp.getiSoLuong();
            }

            // Create summary cards
            summaryCardsPanel.add(createSummaryCard("Sản phẩm", String.valueOf(totalProducts), new Color(52, 152, 219), "📦"));
            summaryCardsPanel.add(createSummaryCard("Tổng hóa đơn", String.valueOf(totalInvoices), new Color(46, 204, 113), "📋"));
            
            DecimalFormat df = new DecimalFormat("#,###");
            summaryCardsPanel.add(createSummaryCard("Doanh thu", df.format(totalRevenue) + " ₫", new Color(241, 196, 15), "💰"));
            summaryCardsPanel.add(createSummaryCard("Tồn kho", String.valueOf(totalInventory), new Color(155, 89, 182), "📊"));
        } catch (Exception e) {
            e.printStackTrace();
            // Default values if error
            summaryCardsPanel.add(createSummaryCard("Sản phẩm", "0", new Color(52, 152, 219), "📦"));
            summaryCardsPanel.add(createSummaryCard("Tổng hóa đơn", "0", new Color(46, 204, 113), "📋"));
            summaryCardsPanel.add(createSummaryCard("Doanh thu", "0 ₫", new Color(241, 196, 15), "💰"));
            summaryCardsPanel.add(createSummaryCard("Tồn kho", "0", new Color(155, 89, 182), "📊"));
        }

        contentPanel.add(summaryCardsPanel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Content Blocks - Split into 2 rows: 3 blocks on top, 2 blocks on bottom
        int iKey = Memory.iCapBac;
        if (iKey == 1) {
            // Admin - show all blocks in 2 rows
            // First row: 3 blocks
            JPanel contentBlocksRow1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
            contentBlocksRow1.setBackground(new Color(245, 247, 250));
            contentBlocksRow1.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentBlocksRow1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
            
            contentBlocksRow1.add(createContentBlock("HÓA ĐƠN", 
                "Quản lý hóa đơn bán hàng, tạo hóa đơn và áp dụng khuyến mãi", 
                new Color(52, 152, 219), "📋", () -> {
                    try { new HoaDon().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                }));
            contentBlocksRow1.add(createContentBlock("KHUYẾN MÃI", 
                "Quản lý chương trình khuyến mãi, tạo và cấu hình tỷ lệ giảm giá", 
                new Color(155, 89, 182), "🎉", () -> {
                    try { new KhuyenMai().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                }));
            contentBlocksRow1.add(createContentBlock("NHẬP HÀNG", 
                "Quản lý phiếu nhập hàng, tạo phiếu nhập từ nhà cung cấp và cập nhật tồn kho tự động", 
                new Color(241, 196, 15), "📦", () -> {
                    try { new PhieuNhap().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                }));
            
            // Second row: 2 blocks
            JPanel contentBlocksRow2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
            contentBlocksRow2.setBackground(new Color(245, 247, 250));
            contentBlocksRow2.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentBlocksRow2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
            
            contentBlocksRow2.add(createContentBlock("HÀNG HÓA", 
                "Quản lý sản phẩm và tồn kho, thêm, sửa, xóa thông tin sản phẩm và quản lý giá/ số lượng", 
                new Color(46, 204, 113), "👟", () -> {
                    try { new Sanpham().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                }));
            contentBlocksRow2.add(createContentBlock("THỐNG KÊ", 
                "Báo cáo và phân tích dữ liệu, thống kê doanh thu theo ngày và tính toán lợi nhuận/biểu đồ", 
                new Color(231, 76, 60), "📈", () -> {
                    try { new ThongKe().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                }));
            
            contentPanel.add(contentBlocksRow1);
            contentPanel.add(Box.createVerticalStrut(15));
            contentPanel.add(contentBlocksRow2);
        } else {
            // Other roles - show only allowed blocks
            JPanel contentBlocksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
            contentBlocksPanel.setBackground(new Color(245, 247, 250));
            contentBlocksPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            switch (iKey) {
                case 2 -> contentBlocksPanel.add(createContentBlock("THỐNG KÊ", 
                    "Báo cáo và phân tích dữ liệu, thống kê doanh thu theo ngày và tính toán lợi nhuận/biểu đồ", 
                    new Color(231, 76, 60), "📈", () -> {
                        try { new ThongKe().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                    }));
                case 3 -> contentBlocksPanel.add(createContentBlock("HÀNG HÓA", 
                    "Quản lý sản phẩm và tồn kho, thêm, sửa, xóa thông tin sản phẩm và quản lý giá/ số lượng", 
                    new Color(46, 204, 113), "👟", () -> {
                        try { new Sanpham().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                    }));
                case 4 -> contentBlocksPanel.add(createContentBlock("NHẬP HÀNG", 
                    "Quản lý phiếu nhập hàng, tạo phiếu nhập từ nhà cung cấp và cập nhật tồn kho tự động", 
                    new Color(241, 196, 15), "📦", () -> {
                        try { new PhieuNhap().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                    }));
                case 5 -> contentBlocksPanel.add(createContentBlock("KHUYẾN MÃI", 
                    "Quản lý chương trình khuyến mãi, tạo và cấu hình tỷ lệ giảm giá", 
                    new Color(155, 89, 182), "🎉", () -> {
                        try { new KhuyenMai().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                    }));
                case 6 -> contentBlocksPanel.add(createContentBlock("HÓA ĐƠN", 
                    "Quản lý hóa đơn bán hàng, tạo hóa đơn và áp dụng khuyến mãi", 
                    new Color(52, 152, 219), "📋", () -> {
                        try { new HoaDon().setVisible(true); setVisible(false); } catch (Exception ignored) {}
                    }));
            }
            contentPanel.add(contentBlocksPanel);
        }
        scrollPane.setViewportView(contentPanel);
        pnBottom.add(scrollPane, BorderLayout.CENTER);
        this.add(pnBottom, BorderLayout.CENTER);
    }

    private JPanel createSummaryCard(String title, String value, Color color, String icon) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 12));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(300, 150));
        card.setMaximumSize(new Dimension(300, 150));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Top section with icon and title
        JPanel topSection = new JPanel(new BorderLayout(10, 0));
        topSection.setOpaque(false);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel iconCircle = new JPanel();
        iconCircle.setLayout(new BorderLayout());
        iconCircle.setPreferredSize(new Dimension(50, 50));
        iconCircle.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20));
        iconCircle.setOpaque(true);
        iconCircle.add(iconLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(127, 140, 141));
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);

        topSection.add(iconCircle, BorderLayout.WEST);
        topSection.add(titleLabel, BorderLayout.CENTER);

        // Value panel with more space - expanded vertically
        JPanel valuePanel = new JPanel(new BorderLayout());
        valuePanel.setOpaque(false);
        valuePanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        valuePanel.setPreferredSize(new Dimension(0, 50));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        valueLabel.setForeground(new Color(44, 62, 80));
        valueLabel.setVerticalAlignment(SwingConstants.CENTER);
        valueLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        valuePanel.add(valueLabel, BorderLayout.CENTER);

        // Progress bar (thin line)
        JPanel progressBar = new JPanel();
        progressBar.setPreferredSize(new Dimension(0, 3));
        progressBar.setBackground(color);
        progressBar.setOpaque(true);

        card.add(topSection, BorderLayout.NORTH);
        card.add(valuePanel, BorderLayout.CENTER);
        card.add(progressBar, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createContentBlock(String title, String description, Color headerColor, String icon, Runnable onClick) {
        JPanel block = new JPanel();
        block.setLayout(new BorderLayout());
        block.setBackground(Color.WHITE);
        block.setPreferredSize(new Dimension(360, 150));
        block.setMaximumSize(new Dimension(360, 150));
        block.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        block.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Header
        JPanel header = new JPanel(new BorderLayout(10, 0));
        header.setBackground(headerColor);
        header.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel iconCircle = new JPanel();
        iconCircle.setLayout(new BorderLayout());
        iconCircle.setPreferredSize(new Dimension(40, 40));
        iconCircle.setBackground(new Color(255, 255, 255, 30));
        iconCircle.setOpaque(true);
        iconCircle.add(iconLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);

        header.add(iconCircle, BorderLayout.WEST);
        header.add(titleLabel, BorderLayout.CENTER);

        // Content
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));

        JLabel descLabel = new JLabel("<html><div style='text-align: left;'>" + description + "</div></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(new Color(127, 140, 141));
        descLabel.setVerticalAlignment(SwingConstants.TOP);

        content.add(descLabel, BorderLayout.CENTER);

        block.add(header, BorderLayout.NORTH);
        block.add(content, BorderLayout.CENTER);

        // Add click listener
        block.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.run();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                block.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(headerColor, 2),
                    BorderFactory.createEmptyBorder(0, 0, 0, 0)
                ));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                block.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                    BorderFactory.createEmptyBorder(0, 0, 0, 0)
                ));
            }
        });

        return block;
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

    private JPanel createPanel_LblLeft(String[] strArr_Left) {
        JPanel panel = new JPanel();
        listLblTop = new JLabel[strArr_Left.length];

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(44, 62, 80));
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
            lblAvatar.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
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
        String currentMenu = "Dashboard"; // Current active menu

        for (int i = 0; i < strArr_Left.length; i++) {
            // Find matching menu name
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

            // Make entire row clickable and add hover effect
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
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
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
            if (menuName != null && menuName.equals("Dashboard")) {
                parent.setBackground(new Color(52, 152, 219));
            } else {
                parent.setBackground(new Color(44, 62, 80));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Home().setVisible(true);
    }
}
