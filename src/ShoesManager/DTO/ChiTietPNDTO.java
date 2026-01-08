package ShoesManager.DTO;


public class ChiTietPNDTO {
    String strMaPN, strMaGiay;
    int iSoLuong, iGiaNhap;
    int deleted;

    public ChiTietPNDTO(String strMaPN, String strMaGiay, int iSoLuong, int iGiaNhap, int deleted) {
        this.strMaPN = strMaPN;
        this.strMaGiay = strMaGiay;
        this.iSoLuong = iSoLuong;
        this.iGiaNhap = iGiaNhap;
        this.deleted = deleted;
    }

    public ChiTietPNDTO() {
    }

    @Override
    public String toString() {
        return "ChiTietPNDTO{" + "strMaPN=" + strMaPN + ", strMaGiay=" + strMaGiay + ", iSoLuong=" + iSoLuong + ", iGiaNhap=" + iGiaNhap + '}';
    }

    public String getStrMaPN() {
        return strMaPN;
    }

    public void setStrMaPN(String strMaPN) {
        this.strMaPN = strMaPN;
    }

    public String getStrMaGiay() {
        return strMaGiay;
    }

    public void setStrMaGiay(String strMaGiay) {
        this.strMaGiay = strMaGiay;
    }

    public int getiSoLuong() {
        return iSoLuong;
    }

    public void setiSoLuong(int iSoLuong) {
        this.iSoLuong = iSoLuong;
    }

    public int getiGiaNhap() {
        return iGiaNhap;
    }

    public void setiGiaNhap(int iGiaNhap) {
        this.iGiaNhap = iGiaNhap;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    public static int maSPTangdan(ChiTietPNDTO a, ChiTietPNDTO b){
        return a.getStrMaGiay().compareTo(b.getStrMaGiay());
    }
}
