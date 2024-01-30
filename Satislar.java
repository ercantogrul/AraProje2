package projects.AraProje2;

public class Satislar {
    private int id;
    private int musID;
    private String tarih;
    private String islem;
    private int tutar;
    private int tahsilat;
    private Durum durum;

    public Satislar(int id, int musID, String tarih, String islem, int tutar, int tahsilat, Durum durum) {
        this.id = id;
        this.musID = musID;
        this.tarih = tarih;
        this.islem = islem;
        this.tutar = tutar;
        this.tahsilat = tahsilat;
        this.durum = durum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMusID() {
        return musID;
    }

    public void setMusID(int musID) {
        this.musID = musID;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getIslem() {
        return islem;
    }

    public void setIslem(String islem) {
        this.islem = islem;
    }

    public int getTutar() {
        return tutar;
    }

    public void setTutar(int tutar) {
        this.tutar = tutar;
    }

    public int getTahsilat() {
        return tahsilat;
    }

    public void setTahsilat(int tahsilat) {
        this.tahsilat = tahsilat;
    }

    public Durum getDurum() {
        return durum;
    }

    public void setDurum(Durum durum) {
        this.durum = durum;
    }

    @Override
    public String toString() {
        String line = String.format("%6s", id);
        line = line + String.format("%10s", musID);
        line = line + String.format("%15s", tarih);
        line = line + String.format("%20s", islem);
        line = line + String.format("%12s", tutar);
        line = line + String.format("%12s", tahsilat);
        return line;
    }

}
