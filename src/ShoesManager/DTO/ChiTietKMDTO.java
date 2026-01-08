package ShoesManager.DTO;

public class ChiTietKMDTO {
    String strMaKM, strMaGiay;
    double TiLeKM;
    int deleted;

    public String getStrMaKM() {
        return strMaKM;
    }

    public void setStrMaKM(String strMaKM) {
        this.strMaKM = strMaKM;
    }

    public String getStrMaGiay() {
        return strMaGiay;
    }

    public void setStrMaGiay(String strMaGiay) {
        this.strMaGiay = strMaGiay;
    }

    public double getTiLeKM() {
        return TiLeKM;
    }

    public void setTiLeKM(double TiLeKM) {
        this.TiLeKM = TiLeKM;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ChiTietKMDTO{" + "strMaKM=" + strMaKM + ", strMaGiay=" + strMaGiay + ", TiLeKM=" + TiLeKM + '}';
    }

    public ChiTietKMDTO(String strMaKM, String strMaGiay, double TiLeKM, int deleted) {
        this.strMaKM = strMaKM;
        this.strMaGiay = strMaGiay;
        this.TiLeKM = TiLeKM;
        this.deleted = deleted;
    }

    public ChiTietKMDTO() {
    }
    
    public static int maSPTangdan(ChiTietKMDTO a, ChiTietKMDTO b){
        return a.getStrMaGiay().compareTo(b.getStrMaGiay());
    }
    
}
