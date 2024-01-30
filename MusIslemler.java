package projects.AraProje2;

import java.util.Scanner;

public class MusIslemler implements InterIslem {
    @Override
    public void veriAdd() {
        // kullanıcıdan veri alınıp listeye konulacak
        // id yi siz hesaplayın

        // durum = true
        // dosyaya diziyi yazdırabiliriz
        System.out.println("\n\n\n - Yeni Müşteri Ekleme -  ");
        System.out.println(Runner.c_GRAY+"(vaz geçmek için telefon alanına * yazın)"+ Runner.c_RESET+ Runner.ITALIC);
        Scanner scan = new Scanner(System.in);
        System.out.print(" Ad Soyad ? : "); String adSoyad = scan.nextLine();
        System.out.print(" Adres    ? : "); String adres   = scan.nextLine();
        System.out.print(" E-Mail   ? : "); String eMail   = scan.nextLine();
        System.out.print(" Telefon  ? : "); String tel     = scan.nextLine();
        if (tel.charAt(0) != '*') {
            Runner.musSonID++;
            Musteriler kayit = new Musteriler(Runner.musSonID,adSoyad,adres,eMail,tel,0, Durum.AKTIF);
            System.out.print("Yeni kayıt ekliyor......... ");
            Runner.musList.put(Runner.musSonID,kayit);
            MusFileHelper.dosyayaYaz();
        } else System.out.println("İşlemden vaz geçildi..... \nx");

    }

    @Override
    public void veriUpdate(){
        // ID yi sor, bu ID array listte bul bu ID nın olduğu kayıtta durumu false yap
        Scanner scan = new Scanner(System.in);
        System.out.print(" Güncellemek istediğiniz Müşterinin ID si ? : ");
        try {
            int id=scan.nextInt();
            Musteriler kayit = Runner.musList.get(id);
            if (kayit == null) System.out.println("Aranılan ID bulunamadı");
            else if (kayit.getDurum()== Durum.SILINMIS) System.out.println("Kayıt silinmiş güncellenemez..");
            else upDate(kayit);
        } catch (Exception e) {
            System.out.println("Hatalı giriş");
        }

        System.out.println();
    }

    private void upDate(Musteriler kayitOld) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Değişiklik yapmak istemdiğiniz alanlara * yazarak geçin");
        String format = Runner.c_GRAY+" (%30s)"+ Runner.c_BLACK+": ";
        System.out.printf("Ad Soyad"+format,kayitOld.getAdSoyad());  String adSoyad = scan.nextLine();
        System.out.printf("Adres   "+format,kayitOld.getAdres());    String adres   = scan.nextLine();
        System.out.printf("E-Mail  "+format,kayitOld.geteMail());    String eMail   = scan.nextLine();
        System.out.printf("Telefon "+format,kayitOld.getTel());      String tel     = scan.nextLine();
        if (adSoyad.charAt(0) == '*') adSoyad = kayitOld.getAdSoyad();
        if (adres.charAt(0) == '*')   adres   = kayitOld.getAdres();
        if (eMail.charAt(0) == '*')   eMail   = kayitOld.geteMail();
        if (tel.charAt(0) == '*')     tel     = kayitOld.getTel();
        Musteriler kayitNew = new Musteriler(kayitOld.getId(),adSoyad,adres,eMail,tel,0, Durum.AKTIF );
        System.out.println("Kaydın Eski hali :"+kayitOld);
        System.out.println("Kaydın Yeni hali :"+kayitNew);
        System.out.println("Değişiklik kaydedilsin mi? (e/h)");
        String secim = scan.nextLine().toLowerCase();
        if (secim.charAt(0)=='e') {
            //kaydı güncelle
            Runner.musList.put(kayitNew.getId(),kayitNew);
            MusFileHelper.dosyayaYaz();
        } else {
            System.out.println("Değişikli iptal edilildi");
        }
        System.out.println();
    }

    @Override
    public void veriDelete(){

        Scanner scan = new Scanner(System.in);
        System.out.print("Silmek veya Silinmişten geri almak istediğiniz Müşterinin ID si ? : ");
        try {
            int id=scan.nextInt();
            Musteriler kayit = Runner.musList.get(id);

            if (kayit == null) System.out.println("Aranılan ID bulunamadı");
            else {
                if (kayit.getDurum()== Durum.SILINMIS) {
                    kayit.setDurum(Durum.AKTIF);
                    System.out.println("Kayıt başarı ile geri alındı");
                }  else {
                    kayit.setDurum(Durum.SILINMIS);
                    System.out.println("Kayıt başarı ile silindi");
                }
         //     Runner.musList.put(kayit.getId(), kayit); // mutable olduğundan buna gerek kalmadı
         //     üstte " Musteriler kayit = Runner.musList.get(id);" bu şekilde refaransları eşitlediğimizden,
         //       birinde yaptığımız değişiklik diğerinide etkiliyor.
              MusFileHelper.dosyayaYaz();
            }
        } catch (Exception e) {
            System.out.println("Hatalı id giriş");

        }
        System.out.println();
    }
    @Override
    public void veriList(){
        // verileri ekrana yaz ama durum  = true olanları
        String line = String.format("%5s","  ID ");
        line  = line + String.format("%30s","Ad Soyad");
        line  = line + String.format("%15s","Telefon");
        line  = line + String.format("%25s","eMail");
        line  = line + String.format("%30s","Adress");
        System.out.println(Runner.BOLD+line+ Runner.c_RESET+ Runner.ITALIC);
        line = String.format("%5s","=====");
        line  = line + String.format("%30s","=========");
        line  = line + String.format("%15s","=========");
        line  = line + String.format("%25s","=========");
        line  = line + String.format("%30s","=========");
        System.out.println(line);
        for (Musteriler val : Runner.musList.values()){
            if (val.getDurum()== Durum.AKTIF)System.out.println(val);
        }
        System.out.println();
    }



    @Override
    public void veriDeletedList(){
        // verileri ekrana yaz ama durum  = false olanları
        String line = String.format("%5s","  ID ");
        line  = line + String.format("%30s","Ad Soyad");
        line  = line + String.format("%15s","Telefon");
        line  = line + String.format("%25s","eMail");
        line  = line + String.format("%30s","Adress");
        System.out.println(Runner.BOLD+line+ Runner.c_RESET+ Runner.ITALIC);
        line = String.format("%5s","=====");
        line  = line + String.format("%30s","=========");
        line  = line + String.format("%15s","=========");
        line  = line + String.format("%25s","=========");
        line  = line + String.format("%30s","=========");
        System.out.println(line);
        for (Musteriler val : Runner.musList.values()){
            if (val.getDurum()== Durum.SILINMIS)System.out.println(val);
        }
        System.out.println();
    }




}
