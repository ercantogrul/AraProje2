package projects.AraProje2;

import java.io.*;

public class SatFileHelper {
    final static String filePath=System.getProperty("user.dir")+"/Satislar.txt";

    public static void dosyayaYaz() {
        File file = new File(filePath); // dosya objesini path+isim ile oluşturuyoruz

        try {
            FileWriter fWriter = new FileWriter(file,false);
            //  FileWriter fWriter = new FileWriter(file,true) eski veriler gitmesin istersek boyle olur
            // apand false ise dosyadaki önceki verileri siler
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            for (Satislar val : Runner.satList.values()) {
                String line="";
                line += val.getId()+"*";
                line += val.getMusID()+"*";
                line += val.getTarih()+"*";
                line += val.getIslem()+"*";
                line += val.getTutar()+"*";
                line += val.getTahsilat()+"*";
                line += val.getDurum().ordinal()  ;

                bWriter.write(line);  // ekleme yapıyorsak bWriter.append(str1)
                bWriter.newLine();
            }

            bWriter.close();
            fWriter.close();
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println("İşlem Tamamlandı \n\n");
        // listeyi dosyaya yaz
    }


    public static void dosyadanOku() {

        // dosyadan oku, musList e  aktar

        // dosyayı açalım
        File file = new File(filePath); // dosya objesini path+isim ile oluşturuyoruz
        if (!file.exists()) { // file yoksa oluşturalım
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bReader = new BufferedReader(fileReader);
            String line;
            while ((line=bReader.readLine()) !=null){

                String[] arr = line.split("\\*");
                int id    = Integer.parseInt(arr[0]);
                int musID = Integer.parseInt(arr[1]);
                int tutar = Integer.parseInt(arr[4]);
                int tahsilat = Integer.parseInt(arr[5]);
                int durum = Integer.parseInt(arr[6]);
                Satislar kayit;
                // id,arr[1],arr[2],arr[3],arr[4],0,Durum.values()[durum]);
                kayit = new Satislar(id,musID,arr[2],arr[3],tutar, tahsilat, Durum.values()[durum] );
                Runner.satList.put(id,kayit);
                Runner.satSonID = id; // son Id nin burada saklanmasını saglıyoruz
            }
            bReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }




        // listenin son elemanının  ID sini  Runner.adresSonID ye alalım

    }
}
