package projects.AraProje2;

public class Musteriler {
    //(örnek alanlar, ID, AD soyad, *tel , adres, eMail, ***statu=true)/
    private int id;
    private String adSoyad;
    private String adres;
    private String eMail;
    private String tel;
    private int bakiye; // kaydedilmeyecek sadece raporlama amacı ile kullanılacak
    private Durum durum; // true ise kayıt aktiftir, false ise silinmiş demektir
    // silinmiş kayıtlara tekrar erişmek gerekebilir diye yaptık bunu


    public Musteriler(int id, String adSoyad, String adres, String eMail, String tel, int bakiye, Durum durum) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.adres = adres;
        this.eMail = eMail;
        this.tel = tel;
        this.bakiye = bakiye;
        this.durum = durum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Durum getDurum() {
        return durum;
    }

    public void setDurum(Durum durum) {
        this.durum = durum;
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

    @Override
    public String toString() {
        String line = String.format("%5s",id);
        line  = line + String.format("%30s",adSoyad);
        line  = line + String.format("%15s",tel);
        line  = line + String.format("%25s",eMail);
        line  = line + String.format("%30s",adres);

        return line;
    }
}
