package projects.AraProje2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Runner {
    final static String c_BLACK  = "\u001B[30m";
    final static String c_RED    = "\u001B[31m";
    final static String c_GRAY   = "\u001B[37m";
    final static String c_RESET  = "\u001B[0m";
    final static String BOLD     = "\033[0;1m";
    final static String ITALIC   = "\033[3m";
    final static String UNDER_LINE = "\033[4m";
    final static String c_BLUE   = "\u001B[34m";

/*    final static String c_GREEN  = "\u001B[32m";
    final static String c_YELLOW = "\u001B[33m";
    final static String c_PURPLE = "\u001B[35m";
    final static String c_CYAN   = "\u001B[36m";
    final static String c_WHITE  = "\u001B[37m";
    final static String nITALIC  = "\033[0m";
    final static String bc_BLACK = "\u001B[40m"; // Siyah zemin
    final static String bc_RED   = "\u001B[41m"; // kırmızı zemin
*/


    static Map<Integer, Musteriler> musList = new HashMap<>();
    static Map<Integer, Satislar>   satList = new HashMap<>();
    static int musSonID = 0;
    static int satSonID = 0;
    public static void main(String[] args) {
        // read data from file and put it in map
        MusFileHelper.dosyadanOku();
        SatFileHelper.dosyadanOku();
        anaMenu();



    }
    private static void anaMenu() {
        Scanner scanner = new Scanner(System.in);
        String secim;
        do {
            anaMenuText();
            secim= scanner.next().substring(0,1);
            switch (secim) {
                case "1" -> musMenu();
                case "2" -> satMenu();


            }
        } while (!secim.equalsIgnoreCase("x"));
    }

    private static void anaMenuText() {
        //String str=STR."\{BOLD}\{UNDER_LINE}  - Ana Menü - \{c_RESET}";
       // System.out.println(str);
        /*
        Ustteki 2 satır kodu ile string tamplate örneği yaptık,
        eğer JDK' niz 21 den az ise bu kod çalışmaz bunun yerine aşağıdaki satırı kullanmalısınız
         */
      System.out.println(BOLD + UNDER_LINE + "- Ana Menü -" + c_RESET);

        String text= """
                1 - Müşteri Kayıtları
                2 - Satış İşlemleri
                x - Çıkış
                """;
        System.out.print(BOLD + ITALIC + text);
    }


    private static void musMenu() {
        InterIslem dbMus= new MusIslemler();
        Scanner scanner = new Scanner(System.in);
        String secim;
        do {
            musMenuText();
            secim= scanner.next().substring(0,1);
            switch (secim) {
                case "1" -> dbMus.veriAdd();
                case "2" -> dbMus.veriUpdate();
                case "3" -> dbMus.veriDelete();
                case "4" -> dbMus.veriList();
                case "5" -> dbMus.veriDeletedList();

            }
        } while (!secim.equalsIgnoreCase("x"));
    }

    private static void musMenuText() {
        // alt satır String tamplate e dönüştürülebilir ( Java nın tercihi budur, üstte örneği var)
        System.out.print("\t\t" + BOLD + UNDER_LINE + c_RED + "- Müşteriler Menü -" + c_RESET + " \n");
        System.out.print(c_RESET);
        
        String text= """
                        1 - Müşteri ekle
                        2 - Müşteri bilgisini güncelle
                        3 - Müşteri Kaydını sil
                        4 - Müşteriler listesi
                        5 - Silinmiş Kayıtlar1
                        x - Ana Menü
                """;
        System.out.print(ITALIC + c_RED + text + c_BLACK);
    }
    private static void satMenu() {
        InterIslem dbSat= new SatIslemler();
        // Usteki satır ile alttaki tamamen aynı işi görür ,
        // zira metotlar birebir aynı , bir "polimorfizm" örneği olsun diye böyle yaptık
         //Satislar dbSat= new SatIslemler();

        Scanner scanner = new Scanner(System.in);
        String secim;
        do {
            satMenuText();
            secim= scanner.next().substring(0,1);
            switch (secim) {
                case "1" -> dbSat.veriAdd();
                case "2" -> dbSat.veriUpdate();
                case "3" -> dbSat.veriDelete();
                case "4" -> dbSat.veriList();
                case "5" -> dbSat.veriDeletedList();
                case "6" -> raporlar();


            }
        } while (!secim.equalsIgnoreCase("x"));
    }



    private static void satMenuText() {
// alt satır String tamplate e dönüştürülebilir ( Java nın tercihi budur, üstte örneği var)
        System.out.print("\t\t" + BOLD + UNDER_LINE + c_RED + "- Satışlar Menü -" + c_RESET + " \n");
        System.out.print(c_RESET);
        String text = """
                        1 - Satış yap
                        2 - Önceki Satışta değişikli yap
                        3 - Satışı Sil
                        4 - Satışlar Listesi
                        5 - Silinmiş Satış Kayıtları
                        6 - Raporlar
                        x - Ana Menü
                """;
        System.out.print(ITALIC + c_RED + text+c_BLACK);
    }

    private static void raporlar() {
        Scanner scanner = new Scanner(System.in);
        String secim;
        do {
            rprMenuText();
            secim= scanner.next().substring(0,1);
            switch (secim) {
                case "1" -> SatRaporlar.birMusteriHesapDokumu();
                case "2" -> SatRaporlar.topSatisOzeti();
                case "3" -> SatRaporlar.musterilerBakiyeListesi();
                case "4" -> SatRaporlar.borcluMusteriler();


            }
        } while (!secim.equalsIgnoreCase("x"));
    }
    private static void rprMenuText(){
       // String str=STR."\t\t\t\t\{BOLD}\{c_BLUE}\{UNDER_LINE}  - Raporlar Menü - \{c_RESET}";
       // System.out.println(str);
        /*
        Ustteki 2 satır kodu ile string tamplate örneği yaptık,
        eğer JDK' niz 21 den az ise bu kod çalışmaz bunun yerine aşağıdaki satırı kullanmalısınız
         */
        System.out.println(BOLD + c_BLUE + UNDER_LINE + "- Ana Menü -" + c_RESET);

        String text= """
                                1 - Bir Müşteri Hesap Dökümü
                                2 - Tüm Satışlar Listesi
                                3 - Müsteri bazlı bakiye raporu
                                4 - Borçlu müşteriler raporu
                                x - Satışlar Menüsü
                """;
        System.out.print(BOLD + ITALIC + c_BLUE + text + c_RESET);
    }
}
