package ShoesManager.GUI;

import ShoesManager.BUS.TaiKhoanBUS;
import ShoesManager.DTO.TaiKhoanDTO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Login extends JFrame {

    private Font Arial;
    private TaiKhoanBUS list_TK;
    private JPanel pRight;
    private ImageIcon iconLogo;
    private JLabel lblLogo, lblName, lblPass, lblLogin;
    private JTextField tfName;
    private JPasswordField pfPass;

    public Login() throws Exception {
        initComponents();
    }

    public void initComponents() throws Exception {
        list_TK = new TaiKhoanBUS();

        Arial = new Font("Segoe UI", Font.PLAIN, 14);
        Memory.colorText = new Color(44, 62, 80);
        Memory.colorThemes = new Color(255, 255, 255); // nền trắng

        iconLogo = new ImageIcon("./src/ShoesManager/images/Logo.png");

        pRight = new JPanel();
        lblLogo = new JLabel(iconLogo, SwingConstants.CENTER);

        // giữ lại icon user & pass
        lblName = new JLabel(new ImageIcon("./src/ShoesManager/images/user.png"));
        lblPass = new JLabel(new ImageIcon("./src/ShoesManager/images/password.png"));

        tfName = new JTextField();
        pfPass = new JPasswordField();
        lblLogin = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);

        // JFrame (GIỮ THANH TIÊU ĐỀ MẶC ĐỊNH CỦA WINDOWS)
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Đăng nhập");
        this.setSize(420, 550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(245, 247, 250));
        this.add(pRight);

        // Panel
        pRight.setLayout(null);
        pRight.setBackground(new Color(255, 255, 255));
        pRight.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Logo
        lblLogo.setBounds(100, 40, 200, 120);
        pRight.add(lblLogo);

        // User icon
        lblName.setBounds(50, 200, 30, 30);
        pRight.add(lblName);

        tfName.setBounds(90, 200, 240, 35);
        tfName.setFont(Arial);
        tfName.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        tfName.setBackground(new Color(250, 250, 250));
        tfName.setForeground(new Color(44, 62, 80));
        tfName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionButton_Login();
            }
        });
        pRight.add(tfName);

        // Pass icon
        lblPass.setBounds(50, 250, 30, 30);
        pRight.add(lblPass);

        pfPass.setBounds(90, 250, 240, 35);
        pfPass.setFont(Arial);
        pfPass.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        pfPass.setBackground(new Color(250, 250, 250));
        pfPass.setForeground(new Color(44, 62, 80));
        pfPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionButton_Login();
            }
        });
        pRight.add(pfPass);

        // Login button
        lblLogin.setBounds(90, 310, 240, 45);
        lblLogin.setOpaque(true);
        lblLogin.setBackground(new Color(52, 73, 94));
        lblLogin.setForeground(Color.WHITE);
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                actionButton_Login();
            }
            @Override
            public void mouseEntered(MouseEvent me) {
                lblLogin.setBackground(new Color(44, 62, 80));
            }
            @Override
            public void mouseExited(MouseEvent me) {
                lblLogin.setBackground(new Color(52, 73, 94));
            }
            @Override
            public void mousePressed(MouseEvent me) {
                lblLogin.setBackground(new Color(35, 50, 65));
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                lblLogin.setBackground(new Color(52, 73, 94));
            }
        });

        pRight.add(lblLogin);
    }

    // Xử lý đăng nhập
    public Boolean actionButton_Login() {
        String tenDN = tfName.getText().trim();
        String matKhau = new String(pfPass.getPassword()).trim();

        // ====== KIỂM TRA RỖNG ======
        if (tenDN.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập tên đăng nhập!",
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE);
            tfName.requestFocus();
            return false;
        }

        if (matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập mật khẩu!",
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE);
            pfPass.requestFocus();
            return false;
        }

        // ====== KIỂM TRA ĐĂNG NHẬP ======
        TaiKhoanDTO tk = new TaiKhoanDTO();
        tk.setStrTenDangNhap(tenDN);
        tk.setStrMatKhau(matKhau);

        if (list_TK.kiemTraDangNhap(tk)) {
            // Đăng nhập thành công
            Memory.maNV = tenDN;
            Memory.iCapBac = list_TK.getCapBac_Ten(tenDN);
            Memory.flag_Menu = true;

            try {
                Home home = new Home();
                home.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                System.out.println("Lỗi " + e.getMessage());
            }
            return true;
        }

        // Sai tài khoản hoặc mật khẩu
        JOptionPane.showMessageDialog(this,
                "Tên đăng nhập hoặc mật khẩu không đúng!",
                "Đăng nhập thất bại",
                JOptionPane.ERROR_MESSAGE);

        pfPass.setText("");
        pfPass.requestFocus();
        return false;

    }

    public static void main(String[] args) throws Exception {
        new Login().setVisible(true);
    }
}
