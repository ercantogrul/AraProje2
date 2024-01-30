package projects.AraProje2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


    public class SatisIslemler2 implements InterIslem {
        @Override
        public void veriAdd() {
            // kullanıcıdan veri alınıp listeye konulacak
            // id yi siz hesaplayın

            // durum = true
            // dosyaya diziyi yazdırabiliriz
            MusIslemler dbMus = new MusIslemler();
            dbMus.veriList();
            System.out.println("\n\n\n - Yeni Hizmet Satis Ekleme -  ");
            System.out.println(Runner.c_GRAY+"(vaz geçmek için işlem alanına * yazın)"+ Runner.c_RESET+Runner.ITALIC);
            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            try {
                System.out.print("İşlem yapılacak musteri ID si ? : ");int musID = scan2.nextInt();
                System.out.print("Yapılan İşlem ? : "); String islem = scan.nextLine();
                System.out.print("İşlem Tutarı  ? : "); int tutar    = scan2.nextInt();
                System.out.print("Tahsilat Tutarı?: "); int tahsilat = scan2.nextInt();
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String tarih = dtf.format(now);

                Musteriler musKayit = Runner.musList.get(musID);
                if (musKayit == null) {
                    System.out.println("Kayıt bulunamadı");
                } else {
                    if (musKayit.getDurum() == Durum.SILINMIS) System.out.println("Müşteri Kaydı Silinmiş İşlem Yapılamaz");
                    else {
                        if (islem.charAt(0) != '*') {
                            Runner.satSonID++;
                            Satislar kayit = new Satislar(Runner.satSonID,musID,tarih,islem,tutar,tahsilat,Durum.AKTIF);
                            System.out.print("Yeni kayıt ekliyor......... ");
                            Runner.satList.put(Runner.satSonID,kayit);
                            SatFileHelper.dosyayaYaz();
                        } else System.out.println("İşlemden vaz geçildi..... \nx");
                    }
                }
            } catch (Exception e) {
                System.out.println("Hatalı giriş...");
                veriAdd();
            }

        }

        @Override
        public void veriUpdate(){
            veriList();
            // ID yi sor, bu ID array listte bul bu ID nın olduğu kayıtta durumu false yap
            Scanner scan = new Scanner(System.in);
            System.out.print(" Güncellemek istediğiniz Satış İşleminin ID si ? : ");
            try {
                int id=scan.nextInt();
                Satislar kayit = Runner.satList.get(id);
                if (kayit == null) System.out.println("Aranılan ID bulunamadı");
                else if (kayit.getDurum()==Durum.SILINMIS) System.out.println("Kayıt silinmiş güncellenemez..");
                else upDate(kayit);
            } catch (Exception e) {
                System.out.println("Hatalı giriş");
            }

            System.out.println();
        }

        private void upDate(Satislar kayitOld) {

            Scanner scan = new Scanner(System.in);
            System.out.println("Değişiklik yapmak istemediğiniz alanlara * yazarak geçin");
            String format = Runner.c_GRAY+" (%30s)"+Runner.c_BLACK+": ";
            try {
                System.out.printf("Yapılan İş"+format,kayitOld.getIslem());         String islem = scan.nextLine();
                System.out.printf("İşlem Tutarı   "+format,kayitOld.getTutar());    String tutarS = scan.nextLine();
                System.out.printf("Tahsiat Tutarı "+format,kayitOld.getTahsilat()); String tahsilatS = scan.nextLine();
                if (islem.charAt(0) == '*')     islem   = kayitOld.getIslem();
                if (tutarS.charAt(0) == '*')     tutarS   = kayitOld.getTutar()+"";
                if (tahsilatS.charAt(0) == '*')  tahsilatS= kayitOld.getTahsilat()+"";
                int tutar = Integer.parseInt(tutarS);
                int tahsilat = Integer.parseInt(tahsilatS);
                Satislar kayitNew = new Satislar(kayitOld.getId(), kayitOld.getMusID(), kayitOld.getTarih(), islem,tutar,tahsilat,Durum.AKTIF);

                System.out.println("Kaydın Eski hali :"+kayitOld);
                System.out.println("Kaydın Yeni hali :"+kayitNew);
                System.out.println("Değişiklik kaydedilsin mi? (e/h)");
                String secim = scan.nextLine().toLowerCase();
                if (secim.charAt(0)=='e') {
                    //kaydı güncelle
                    Runner.satList.put(kayitNew.getId(),kayitNew);
                    SatFileHelper.dosyayaYaz();
                } else {
                    System.out.println("Değişikli iptal edilildi");
                }
            } catch (Exception e) {
                System.out.println("Hatalı giriş");
            }

            System.out.println();
        }

        @Override
        public void veriDelete(){

            Scanner scan = new Scanner(System.in);
            System.out.print("Silmek veya Silinmişten geri almak istediğiniz Müşterinin ID si ? : ");
            try {
                int id=scan.nextInt();
                Satislar kayit = Runner.satList.get(id);
                if (kayit == null) System.out.println("Aranılan ID bulunamadı");
                else {
                    if (kayit.getDurum()==Durum.SILINMIS) {
                        kayit.setDurum(Durum.AKTIF);
                        System.out.println("Kayıt başarı ile geri alındı");
                    }  else {
                        kayit.setDurum(Durum.SILINMIS);
                        System.out.println("Kayıt başarı ile silindi");
                    }
                    // Runner.satList.put(kayit.getId(), kayit); // mutuble olduğunda , yukardaki eşitleem nedeni ile buna gerek kalmıyor
                    // kafa karışıyorsa         Runner.satList.put(kayit.getId(), kayit); eklenebilir
                    SatFileHelper.dosyayaYaz();
                }
            } catch (Exception e) {
                System.out.println("Hatalı giriş");
            }
            System.out.println();
        }
        @Override
        public void veriList(){
            // verileri ekrana yaz ama durum  = true olanları
            baslikPrint();
            for (Satislar val : Runner.satList.values()){
                if (val.getDurum()==Durum.AKTIF)System.out.println(val);
            }
            System.out.println();
        }



        @Override
        public void veriDeletedList(){
            // verileri ekrana yaz ama durum  = false olanları
            baslikPrint();
            for (Satislar val : Runner.satList.values()){
                if (val.getDurum()==Durum.SILINMIS)System.out.println(val);
            }
            System.out.println();
        }
        protected static void baslikPrint(){
            String line = String.format("%6s","ID");
            line  = line + String.format("%10s","Mus ID");
            line  = line + String.format("%15s","Tarih");
            line  = line + String.format("%20s","İşlem");
            line  = line + String.format("%12s","Tutar");
            line  = line + String.format("%12s","Tahsilat");
            System.out.println(Runner.BOLD+line+Runner.c_RESET+Runner.ITALIC);
            line = String.format("%6s" ,"=====");
            line  = line + String.format("%10s","======");
            line  = line + String.format("%15s","==========");
            line  = line + String.format("%20s","=============");
            line  = line + String.format("%12s","==========");
            line  = line + String.format("%12s","==========");
            System.out.println(line);
        };

    }

