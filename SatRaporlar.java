package projects.AraProje2;

import java.util.Scanner;

public class SatRaporlar {

    protected static void birMusteriHesapDokumu() {
        MusIslemler dbMus = new MusIslemler();
        dbMus.veriList();
        Scanner scan = new Scanner(System.in);
        System.out.print("Raporlamak istediğiniz Müsterinin ID sini giriniz : ");
        try {
            int mID = scan.nextInt();
            Musteriler mus = Runner.musList.get(mID);
            if (mus != null) {
                if (mus.getDurum() == Durum.AKTIF) {
                    SatIslemler.baslikPrint();
                    int topTutar = 0;
                    int topTahsilat = 0;
                    for (Satislar val : Runner.satList.values()) {
                        if (val.getDurum() == Durum.AKTIF && val.getMusID() == mID) {
                            System.out.println(val);
                            topTutar += val.getTutar();
                            topTahsilat += val.getTahsilat();
                        }
                    }
                    System.out.println("\nMüşteriye yapılan  ");
                    System.out.println("Toplam satış    : " + topTutar);
                    System.out.println("Toplam tahsilat : " + topTahsilat);
                    System.out.println("Kalan Bakiye    : " + (topTutar - topTahsilat));
                } else {
                    System.out.println("Bu kayıt silinmiş...");
                }
            } else {
                System.out.println(mID + " : nolu ID kayıtlarda yok  ");
            }
        } catch (Exception e) { // dikkat "_" kullanımı JDK 21 ile geldi, hiç Exception objesi oluşmadı
            System.out.println("Hatalı ID girişi");
        }
        System.out.println("\n");
    }

    protected static void topSatisOzeti() {
        SatIslemler.baslikPrint();
        int topTutar = 0;
        int topTahsilat = 0;
        for (Satislar val : Runner.satList.values()) {
            if (val.getDurum() == Durum.AKTIF) {
                System.out.println(val);
                topTutar += val.getTutar();
                topTahsilat += val.getTahsilat();
            }
        }
        System.out.println("\n  ");
        System.out.println("Toplam satış    : " + topTutar);
        System.out.println("Toplam tahsilat : " + topTahsilat);
        System.out.println("Kalan Bakiye    : " + (topTutar - topTahsilat));
        System.out.println("\n");
    }


    protected static void musterilerBakiyeListesi() {
        bakiyeleriDoldur();
        putBaslik();
        for (Musteriler mus : Runner.musList.values()) {
            System.out.printf("%5s%20s%10s\n", mus.getId(), mus.getAdSoyad(), mus.getBakiye());
        }
        System.out.println("\n");

    }

    protected static void borcluMusteriler() {
        bakiyeleriDoldur();
        putBaslik();
        for (Musteriler mus : Runner.musList.values()) {
            if (mus.getBakiye() > 0)
                System.out.printf("%5s%20s%10s\n", mus.getId(), mus.getAdSoyad(), mus.getBakiye());
        }
        System.out.println("\n");
    }

    protected static void putBaslik() {
        System.out.printf("%5s%20s%10s\n", "ID", "Isim", "Bakiye");
        System.out.printf("%5s%20s%10s\n", "=====", "==========", "======");
    }

    protected static void bakiyeleriDoldur() {
        for (Musteriler mus : Runner.musList.values()) {
            int bakiye = 0;
            for (Satislar sat : Runner.satList.values()) {
                if (sat.getMusID() == mus.getId()) {
                    bakiye += sat.getTutar() - sat.getTahsilat();
                }
            }
            mus.setBakiye(bakiye);
            /*
            Eger seter kullanmasaydik asagidaki gibi yapardik
            Musteriler kayit = new Musteriler(mus.getId(), mus.getAdSoyad(), mus.getAdres(), mus.geteMail(), mus.getTel(), bakiye,mus.getDurum());
            Runner.musList.put(mus.getId(), kayit);
             */

        }

    }


}


