/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoesManager.GUI;

import ShoesManager.BUS.ChiTietHDBUS;
import ShoesManager.BUS.ChiTietKMBUS;
import ShoesManager.BUS.KhachHangBUS;
import ShoesManager.BUS.KhuyenMaiBUS;
import ShoesManager.BUS.SanPhamBUS;
import ShoesManager.DTO.ChiTietHDDTO;
import ShoesManager.DTO.ChiTietKMDTO;
import ShoesManager.DTO.HoaDonDTO;
import ShoesManager.DTO.KhachHangDTO;
import ShoesManager.DTO.SanPhamDTO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author admin
 */
public class InHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form fPrintInHoaDon
     */
    public InHoaDon() {
        this.setUndecorated(true);

        initComponents();
        init();
    }

    private HoaDonDTO hoadon;
    private JLabel[] list_lbl;

    public InHoaDon(HoaDonDTO hoadon) {
        this.hoadon = hoadon;

        initComponents();
        init();
    }

    // Trả về BaseFont Unicode đã nhúng để hiển thị đúng tiếng Việt trong PDF
    private BaseFont loadUnicodeBaseFont() throws Exception {
        // Ưu tiên font đi kèm dự án
        String[] candidatePaths = new String[] {
                "./src/ShoesManager/font/arial.ttf",
                "./src/ShoesManager/font/arialuni.ttf",
                "./src/ShoesManager/font/tahoma.ttf",
                "./src/ShoesManager/font/segoeui.ttf",
                // Các vị trí hệ thống Windows phổ biến
                "C:/Windows/Fonts/arial.ttf",
                "C:/Windows/Fonts/arialuni.ttf",
                "C:/Windows/Fonts/tahoma.ttf",
                "C:/Windows/Fonts/segoeui.ttf"
        };
        for (String p : candidatePaths) {
            try {
                File f = new File(p);
                if (f.exists()) {
                    return BaseFont.createFont(p, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                }
            } catch (Exception ignore) {
                // thử đường dẫn kế tiếp
            }
        }
        // Phòng trường hợp tất cả thất bại, dùng font mặc định của iText với IDENTITY_H
        // (vẫn embed được nếu hệ thống có glyph phù hợp)
        return BaseFont.createFont(BaseFont.HELVETICA, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    }

    public void init() {
        KhachHangBUS bus = null;

        try {
            bus = new KhachHangBUS();
        } catch (Exception ex) {
            Logger.getLogger(InHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        KhachHangDTO dto = new KhachHangDTO();
        dto = bus.getInfor(hoadon.getStrMaKH());

        jLabelsophieunhap.setText("Mã hóa dơn: " + hoadon.getStrMaHD());
        jLabelnhanvien.setText(jLabelnhanvien.getText() + Memory.nhanvien.getStrHo() + " " +Memory.nhanvien.getStrTen());
        jLabelnhanvien1.setText(jLabelnhanvien1.getText() + dto.getStrHo() + " " +dto.getStrTen());
        jLabelthoigian.setText(jLabelthoigian.getText() + hoadon.getStrNgayBan());
        jLabelTenSP7.setText(jLabelTenSP7.getText() + String.valueOf(hoadon.getTongTien()));

    }

    private JPanel createPanel_CTHD() {
        JPanel panel = new JPanel();
        ChiTietHDBUS bus = null;
        SanPhamBUS spbus = null;
        ChiTietKMBUS kmbus = null;

        try {
            kmbus = new ChiTietKMBUS();
        } catch (Exception ex) {
            Logger.getLogger(InHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            spbus = new SanPhamBUS();
        } catch (Exception ex) {
            Logger.getLogger(InHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            bus = new ChiTietHDBUS();
        } catch (Exception ex) {
            Logger.getLogger(InHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }


        panel.setLayout(null);
        int iNumbSP = 0;
        int toadox = 0, toadoy = 0;

        for (int i=0 ; i < bus.getNumbChiTietHD() ; i++) {
            ChiTietHDDTO dto = new ChiTietHDDTO();

            dto = bus.getInfor(i);

            if (hoadon.getStrMaHD().equals(dto.getStrMaHD())) {
                SanPhamDTO sp = spbus.getInfor(dto.getStrMaGiay());
                ChiTietKMDTO km = kmbus.getInfor(dto.getStrMaGiay(), hoadon.getStrMaKM());
                JPanel p = createPanel_SP(dto, sp, km.getTiLeKM());
                iNumbSP++;
                p.setBounds(toadox, toadoy, 1050, 30);
                panel.add(p);
                toadoy += 30;
            }
        }

        panel.setSize(1050, 30 * iNumbSP);
        jPanel3.setSize(1050, 30 * iNumbSP);
        return panel;
    }

    private JPanel createPanel_SP(ChiTietHDDTO hd, SanPhamDTO sp, double tilekm) {
        JPanel panel = new JPanel();
        list_lbl = new JLabel[10];
        list_lbl[0] = new JLabel();
        list_lbl[1] = new JLabel();
        list_lbl[2] = new JLabel();
        list_lbl[3] = new JLabel();
        list_lbl[4] = new JLabel();
        list_lbl[5] = new JLabel();
        list_lbl[6] = new JLabel();
        list_lbl[7] = new JLabel();
        list_lbl[8] = new JLabel();
        list_lbl[9] = new JLabel();

        panel.setLayout(null);

        System.out.println(sp.toString());
        System.out.println(hd.toString());
        System.out.println("DEBUG - TiLeKM value: " + tilekm);

        list_lbl[0].setText(sp.getStrTenGiay());
        list_lbl[1].setText(String.valueOf(sp.getiSize()));
        list_lbl[2].setText(sp.getStrChatLieu());
        list_lbl[3].setText(sp.getStrMaxx());
        list_lbl[4].setText(sp.getStrMaMau());
        list_lbl[5].setText(sp.getStrMaThuongHieu());
        list_lbl[6].setText(String.valueOf(hd.getiSoLuong()));
        list_lbl[7].setText(String.valueOf(sp.getiGia()) + " đồng");
        // Đảm bảo hiển thị đúng phần trăm: nếu giá trị < 1 thì nhân 100, nếu >= 1 thì hiển thị trực tiếp
        double tileHienThi = (tilekm < 1) ? (tilekm * 100) : tilekm;
        list_lbl[8].setText(String.format("%.0f", tileHienThi) + "%");

        double giatien = Integer.valueOf(hd.getiSoLuong())
                * Integer.valueOf(hd.getiGiaBan())
                * (1 - tilekm);
        list_lbl[9].setText(String.valueOf(giatien));

        // Column widths and gaps aligned with header labels in jPanel3
        int gap = 8;
        int x = 0;
        list_lbl[0].setBounds(x, 0, 249, 30); x += 249 + gap; // Tên
        list_lbl[1].setBounds(x, 0, 60, 30);  x += 60 + gap;   // Size
        list_lbl[2].setBounds(x, 0, 100, 30); x += 100 + gap;  // Chất liệu
        list_lbl[3].setBounds(x, 0, 90, 30);  x += 90 + gap;   // Mã xuất xứ
        list_lbl[4].setBounds(x, 0, 80, 30);  x += 80 + gap;   // Mã màu
        list_lbl[5].setBounds(x, 0, 130, 30); x += 130 + gap;  // Mã TH
        list_lbl[6].setBounds(x, 0, 90, 30);  x += 90 + gap;   // Số lượng
        list_lbl[8].setBounds(x, 0, 110, 30); x += 110 + gap;  // KM (%)
        list_lbl[7].setBounds(x, 0, 100, 30); x += 100 + gap;  // Giá
        list_lbl[9].setBounds(x, 0, 140, 30);                  // Thành tiền

        for (JLabel j : list_lbl) {
            j.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        for (JLabel j : list_lbl) {
            j.setBackground(new Color(255,255,255));
        }

        for (JLabel j : list_lbl) {
            panel.add(j);
        }

        panel.setBackground(new Color(255,255,255));
        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelsophieunhap = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelnhanvien = new javax.swing.JLabel();
        jLabelthoigian = new javax.swing.JLabel();
        jLabelnhanvien1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanelControl = new javax.swing.JPanel();
        jButtonPrint = new javax.swing.JButton();
        jButtonThoat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelTenSP = new javax.swing.JLabel();
        jLabelTenSP1 = new javax.swing.JLabel();
        jLabelTenSP2 = new javax.swing.JLabel();
        jLabelTenSP3 = new javax.swing.JLabel();
        jLabelTenSP4 = new javax.swing.JLabel();
        jLabelTenSP5 = new javax.swing.JLabel();
        jLabelTenSP6 = new javax.swing.JLabel();
        jLabelTenSP8 = new javax.swing.JLabel();
        jLabelTenSP9 = new javax.swing.JLabel();
        jLabelTenSP10 = new javax.swing.JLabel();
        jPanel5 = createPanel_CTHD();
        jPanel4 = new javax.swing.JPanel();
        jLabelTenSP7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("In Thông Tin Phiếu Nhập");
        setResizable(false);

        jPanelHeader.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("HÓA ĐƠN");

        jLabelsophieunhap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabelsophieunhap.setText("Mã hóa đon:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel9.setText("Cửa hàng giày");

        jLabelnhanvien.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabelnhanvien.setText("Nhân viên:");

        jLabelthoigian.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabelthoigian.setText("Thời gian :");

        jLabelnhanvien1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabelnhanvien1.setText("Khách Hàng:");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
                jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                                .addGap(0, 434, Short.MAX_VALUE)
                                                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(48, 48, 48)))
                                                .addGap(162, 162, 162)))
                                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelthoigian, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelsophieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelnhanvien1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(159, 159, 159))
        );
        jPanelHeaderLayout.setVerticalGroup(
                jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                                .addComponent(jLabelsophieunhap)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabelnhanvien)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabelnhanvien1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelthoigian))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanelControl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonPrint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonPrint.setText("In");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        jButtonThoat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonThoat.setText("Thoát");
        jButtonThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelControlLayout = new javax.swing.GroupLayout(jPanelControl);
        jPanelControl.setLayout(jPanelControlLayout);
        jPanelControlLayout.setHorizontalGroup(
                jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelControlLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonThoat, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanelControlLayout.setVerticalGroup(
                jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelControlLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonPrint)
                                        .addComponent(jButtonThoat))
                                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Sản Phẩm");

        jLabelTenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP.setText("Tên :");

        jLabelTenSP1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP1.setText("Số Lượng:");

        jLabelTenSP2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP2.setText("Giá:");

        jLabelTenSP3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP3.setText("Thành tiền:");

        jLabelTenSP4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP4.setText("Tỉ lệ khuyến mãi:");

        jLabelTenSP5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP5.setText("Size:");

        jLabelTenSP6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP6.setText("Chất liệu:");

        jLabelTenSP8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP8.setText("Mã xuất xứ:");

        jLabelTenSP9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP9.setText("Mã màu:");

        jLabelTenSP10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSP10.setText("Mã thương hiệu:");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 256, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabelTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabelTenSP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabelTenSP)
                                        .addComponent(jLabelTenSP5)
                                        .addComponent(jLabelTenSP6)
                                        .addComponent(jLabelTenSP8)
                                        .addComponent(jLabelTenSP9)
                                        .addComponent(jLabelTenSP10)
                                        .addComponent(jLabelTenSP1)
                                        .addComponent(jLabelTenSP4)
                                        .addComponent(jLabelTenSP2)
                                        .addComponent(jLabelTenSP3))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTenSP7.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabelTenSP7.setText("Tổng tiền:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTenSP7, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTenSP7))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jPanelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn in hóa đơn này không?",
            "Xác nhận in",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) {
            return; // người dùng chọn Không thì thoát
        }
        
        jPanelControl.setVisible(false);
        try
        {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            this.paint(graphics2D);
            ImageIO.write(image,"png", new File("./src/ShoesManager/In ấn/hoadon_"
                    + hoadon.getStrMaHD() + "_" +hoadon.getStrNgayBan()+".png"));
        }
        catch(Exception exception)
        {
        }
        KhachHangBUS khbus = null;
        KhachHangDTO dtokh;
        try {
            khbus =new KhachHangBUS();
        } catch (Exception ex) {
            Logger.getLogger(InHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        dtokh = khbus.getInfor(hoadon.getStrMaKH());
        dtokh.setiTongChiTieu(hoadon.getTongTien() + dtokh.getiTongChiTieu());
        try {
            khbus.sua(dtokh);
        } catch (Exception ex) {
            Logger.getLogger(InHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Khởi tạo file PDF
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            String outputDir = "./src/ShoesManager/In ấn/";
            String outputPath = outputDir + "hoadon_" + hoadon.getStrMaHD() + "_" + hoadon.getStrNgayBan() + ".pdf";

            File dir = new File(outputDir);
            if (!dir.exists()) {
                dir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            // ✅ NẠP FONT UNICODE CÓ HỖ TRỢ TIẾNG VIỆT (thử nhiều nguồn khác nhau để chắc chắn)
            BaseFont baseFont = loadUnicodeBaseFont();
            com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(baseFont, 18f, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font infoFont = new com.itextpdf.text.Font(baseFont, 12f, com.itextpdf.text.Font.NORMAL);
            com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(baseFont, 12f, com.itextpdf.text.Font.BOLD, BaseColor.WHITE);
            com.itextpdf.text.Font thankFont = new com.itextpdf.text.Font(baseFont, 14f, com.itextpdf.text.Font.ITALIC, BaseColor.DARK_GRAY);

            // ========== LOGO TIỆM ==========
            try {
                Image logo = Image.getInstance("./src/ShoesManager/logo.png");
                logo.scaleToFit(80, 80);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            } catch (Exception ex) {
                System.out.println("⚠️ Không tìm thấy logo, bỏ qua phần logo.");
            }

            // ========== TIÊU ĐỀ ==========
            Paragraph title = new Paragraph("HÓA ĐƠN MUA HÀNG", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("============================================================"));
            document.add(new Paragraph(" "));

            // ========== THÔNG TIN CHUNG ==========
            document.add(new Paragraph("Mã hóa đơn: " + hoadon.getStrMaHD(), infoFont));
            document.add(new Paragraph("Ngày bán: " + hoadon.getStrNgayBan(), infoFont));
            document.add(new Paragraph("Khách hàng: " + dtokh.getStrTen(), infoFont));
            document.add(new Paragraph("Nhân viên: " + jLabelnhanvien.getText().replace("Nhân viên:", "").trim(), infoFont));
            document.add(new Paragraph(" "));

            // ========== BẢNG CHI TIẾT SẢN PHẨM ==========
            ChiTietHDBUS cthdBus = new ChiTietHDBUS();
            SanPhamBUS spbus = new SanPhamBUS();
            ChiTietKMBUS kmbus = new ChiTietKMBUS();

            PdfPTable table = new PdfPTable(10);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 1, 2, 2, 2, 2, 1, 1, 2, 2});

            BaseColor headerBg = new BaseColor(64, 64, 64);
            String[] headers = {"Tên sản phẩm", "Size", "Chất liệu", "Mã xuất xứ", "Mã màu", "Mã TH", "Số lượng", "KM (%)", "Giá", "Thành tiền"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setBackgroundColor(headerBg);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5);
                table.addCell(cell);
            }

            double tongTien = 0;
            for (int i = 0; i < cthdBus.getNumbChiTietHD(); i++) {
                ChiTietHDDTO dto = cthdBus.getInfor(i);
                if (hoadon.getStrMaHD().equals(dto.getStrMaHD())) {
                    SanPhamDTO sp = spbus.getInfor(dto.getStrMaGiay());
                    ChiTietKMDTO km = kmbus.getInfor(dto.getStrMaGiay(), hoadon.getStrMaKM());

                    double tilekm = km.getTiLeKM();
                    double thanhTien = dto.getiSoLuong() * dto.getiGiaBan() * (1 - tilekm);
                    tongTien += thanhTien;

                    table.addCell(new Phrase(sp.getStrTenGiay(), infoFont));
                    table.addCell(new Phrase(String.valueOf(sp.getiSize()), infoFont));
                    table.addCell(new Phrase(sp.getStrChatLieu(), infoFont));
                    table.addCell(new Phrase(sp.getStrMaxx(), infoFont));
                    table.addCell(new Phrase(sp.getStrMaMau(), infoFont));
                    table.addCell(new Phrase(sp.getStrMaThuongHieu(), infoFont));
                    table.addCell(new Phrase(String.valueOf(dto.getiSoLuong()), infoFont));
                    // Đảm bảo hiển thị đúng phần trăm: nếu giá trị < 1 thì nhân 100, nếu >= 1 thì hiển thị trực tiếp
                    double tileHienThiPDF = (tilekm < 1) ? (tilekm * 100) : tilekm;
                    table.addCell(new Phrase(String.format("%.0f", tileHienThiPDF), infoFont));
                    table.addCell(new Phrase(String.valueOf(sp.getiGia()), infoFont));
                    table.addCell(new Phrase(String.format("%.0f", thanhTien), infoFont));
                }
            }

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Tổng tiền: " + String.format("%,.0f VND", tongTien), infoFont));
            document.add(new Paragraph("============================================================"));
            document.add(new Paragraph(" "));

            // ========== LỜI CẢM ƠN ==========
            Paragraph thank = new Paragraph("Cảm ơn quý khách đã mua hàng!", thankFont);
            thank.setAlignment(Element.ALIGN_CENTER);
            document.add(thank);

            document.close();
            
            JOptionPane.showMessageDialog(
                this,
                "In hóa đơn thành công!\nĐường dẫn: " + new File(outputPath).getAbsolutePath(),
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            e.printStackTrace();
            // 🔹 Thêm thông báo lỗi
            JOptionPane.showMessageDialog(
                this,
                "Đã xảy ra lỗi khi in hóa đơn!\n" + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void jButtonThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThoatActionPerformed
        //dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonThoatActionPerformed

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
            java.util.logging.Logger.getLogger(InHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTenSP;
    private javax.swing.JLabel jLabelTenSP1;
    private javax.swing.JLabel jLabelTenSP2;
    private javax.swing.JLabel jLabelTenSP3;
    private javax.swing.JLabel jLabelTenSP4;
    private javax.swing.JLabel jLabelTenSP5;
    private javax.swing.JLabel jLabelTenSP6;
    private javax.swing.JLabel jLabelTenSP8;
    private javax.swing.JLabel jLabelTenSP9;
    private javax.swing.JLabel jLabelTenSP10;
    private javax.swing.JLabel jLabelTenSP7;
    private javax.swing.JLabel jLabelnhanvien;
    private javax.swing.JLabel jLabelnhanvien1;
    private javax.swing.JLabel jLabelsophieunhap;
    private javax.swing.JLabel jLabelthoigian;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
