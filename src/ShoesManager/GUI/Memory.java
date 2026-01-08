package ShoesManager.GUI;

import ShoesManager.DTO.*;

/**
 * Lưu trữ cách biến dự liệu cơ bản
 */
public class Memory {
    //Tai khoan
    static String maNV;
    static int iCapBac;
    
    //NHan vien
    static NhanVienDTO nhanvien;

    // color - Modern color scheme
    static java.awt.Color colorThemes = new java.awt.Color(52, 73, 94);      // Modern dark blue-gray
    static java.awt.Color colorThemes_2 = new java.awt.Color(245, 247, 250); // Light gray background
    static java.awt.Color colorText = new java.awt.Color(44, 62, 80);        // Dark gray text
    
    // an hien menu
    static boolean flag_Menu;
    
    // quesion yes or no
    static boolean yesno_Q;
    
    // sản phẩm
    static String maSP;
    static int giaSP;
    
    // link file
    static String filechoose;
}
