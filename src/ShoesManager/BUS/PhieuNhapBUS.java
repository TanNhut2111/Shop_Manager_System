package ShoesManager.BUS;

import ShoesManager.DAO.PhieuNhapDAO;
import ShoesManager.DTO.PhieuNhapDTO;
import java.util.ArrayList;

public class PhieuNhapBUS {
    private ArrayList<PhieuNhapDTO> list_PN;
    /**
     * Xử lý các lệnh trong SQL
     */
    private PhieuNhapDAO pnDAO;
    
    public PhieuNhapBUS() throws Exception {
        list_PN = new ArrayList<>();
        pnDAO = new PhieuNhapDAO();
        list_PN = pnDAO.docDB();
    }
    
    public void add(PhieuNhapDTO pn) {
        list_PN.add(pn);
    }
    
    public void deleteAll() {
        list_PN.removeAll(list_PN);
    }
    
    public void docDB() throws Exception {
        list_PN = new ArrayList<>();
        pnDAO = new PhieuNhapDAO();
        list_PN = pnDAO.docDB();
    }

    public ArrayList<PhieuNhapDTO> getList_PN() {
        return list_PN;
    }

    public void setList_PN(ArrayList<PhieuNhapDTO> list_PN) {
        this.list_PN = list_PN;
    }
    
    public int getNumb() {
        return list_PN.size();
    }
    
    public PhieuNhapDTO getInfor(int i){
        int iCount =0;
        for (PhieuNhapDTO phieunhap : list_PN) {
            if (iCount == i)
                return phieunhap;
            iCount++;
        }
        return null;
    }
    
    public int demSoChuSo(int nInput){
	if (nInput < 10) {
		return 1;
	}
	return 1 + demSoChuSo(nInput / 10);
    }
    
    public String getDefaultMaPN() {
        if (list_PN.size() == 0)
            return "PN001";
        else {
            String s = "PN";
            int iNumb = 0;
            
            for (PhieuNhapDTO phieunhap : list_PN) {
                String[] maPN = phieunhap.getStrMaPN().split("PN");
                iNumb = Integer.parseInt( maPN[1] );
                iNumb++;
            }
            switch (demSoChuSo(iNumb)) {
                case 1:
                    s +="0";
                case 2:
                    s +="0";
                case 3:  
            }
            s += iNumb;
            return s;
        }
    }
    
     public ArrayList<PhieuNhapDTO> timKiem_MaPN(String strMaPN) {
        ArrayList<PhieuNhapDTO> arr = new ArrayList<>();
        for (PhieuNhapDTO phieunhap : list_PN) {
            if ( phieunhap.getStrMaPN().indexOf(strMaPN) != -1 ) {
                System.out.println("Tim thay " + phieunhap.getStrMaPN() );
                arr.add(phieunhap);
            }
        }
        return arr;
    }
    
    public ArrayList<PhieuNhapDTO> timKiem_MaNV(String strMaNV) {
        ArrayList<PhieuNhapDTO> arr = new ArrayList<>();
        for (PhieuNhapDTO phieunhap : list_PN) {
            if ( phieunhap.getStrMaNV().indexOf(strMaNV) != -1 ) {
                System.out.println("Tim thay " + phieunhap.getStrMaPN() );
                arr.add(phieunhap);
            }
        }
        
        return arr;
    }
    
    public ArrayList<PhieuNhapDTO> timKiem_MaNCC(String strMaNCC) {
        ArrayList<PhieuNhapDTO> arr = new ArrayList<>();
        for (PhieuNhapDTO phieunhap : list_PN) {
            if ( phieunhap.getStrMaNCC().indexOf(strMaNCC) != -1 ) {
                System.out.println("Tim thay " + phieunhap.getStrMaPN() );
                arr.add(phieunhap);
            }
        }
        
        return arr;
    }
    
    public ArrayList<PhieuNhapDTO> timKiem_TongTien(String strTT) {
        ArrayList<PhieuNhapDTO> arr = new ArrayList<>();
        for (PhieuNhapDTO phieunhap : list_PN) {
            if ( String.valueOf(phieunhap.getTongTien()).indexOf(strTT) != -1 ) {
                System.out.println("Tim thay " + phieunhap.getStrMaPN() );
                arr.add(phieunhap);
            }
        }
        
        return arr;
    }
    
    
    /**
     * thêm 1 tài khoản vào danh sách và database
     * @return true nếu thành công
     */
    public Boolean them(PhieuNhapDTO pn) throws Exception{
        if ( pnDAO.them(pn) ) {
            list_PN.add(pn);
        }
        return false;
    }
    
    /**
     * xóa 1 tài khoản khỏi danh sách và database
     * @return true nếu thành công
     */
    public Boolean xoa(PhieuNhapDTO pn) throws Exception {
        if ( pnDAO.xoa(pn) ) {
            
            for (PhieuNhapDTO phieunhap : list_PN) {
                if (phieunhap.getStrMaPN().equals(pn.getStrMaPN())) {
                    phieunhap.setDeleted(1); // cập nhật lại flag trong bộ nhớ
                    break;
                }
            }
        }
        
        return false;
    }
    
    /**
     * sửa thông tin của 1 tài khoản <br>
     * - Trừ thông tin đăng nhập của tài khoản đó
     * @return true nếu thực hiện thành công
     */
    public Boolean sua(PhieuNhapDTO pn) throws Exception {
        if ( pnDAO.sua(pn) ) {
            
            // duyệt từng phẩn tử
            for ( PhieuNhapDTO phieunhap : list_PN ) {
                if (phieunhap.getStrMaPN().equals(pn.getStrMaPN())){
                    phieunhap.setStrMaNCC(pn.getStrMaNCC());
                    phieunhap.setStrMaNV(pn.getStrMaNV());
                    phieunhap.setStrMaPN(pn.getStrMaPN());
                    phieunhap.setStrNgayNhap(pn.getStrNgayNhap());
                    phieunhap.setTongTien(pn.getTongTien());
                    return true;
                }
            }
        }     
        return false;
    }

    public String getNewMaPNFromDB() throws Exception {
        return pnDAO.getNewMaPN();
    }

}
