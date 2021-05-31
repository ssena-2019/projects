/*
 * @SafiyeSenaMerdin
 * @SelcenFethiyeMersinli
 * Sıralama işlemi BINARY SEARCH
 * BruteForce kaba kuvvet algoritmasıdır.
 * Problemi en basit şekilde çözmeye çalışır.
 * Bütün ihtimalleri(kombinasyon) göz önünde bulundurur.
 * baz istasyonu probleminde problemi en karlı şekilde çözülmesi beklenir.
 * istasyonların max kara en uygun olan yerleşim düzeni bulunur.  
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class BFStation{
    
    public List<Integer> distanceList = new ArrayList<Integer>();
    public List<Integer> profitList = new ArrayList<Integer>();
    public ArrayList<ArrayList<Integer>> subSets = new ArrayList<ArrayList<Integer>>(); 
     
    public int tourProfit; //her ihtimalin(kombinasyon) toplam karını tutan değişken
    public int X; //istasyonlar arası min uzaklık
    public int max; //max karı tutar
 
    //constructor
    BFStation(int X){
        this.X = X;
        tourProfit = 0;
        max = 0;
    }
    //Toplam max karı bulan fonksiyon
    public void maxProfit(){
        //Alt kümelerimizin sayısını buldurur ve altküme listi oluşturur.
        int altKumeSayisi = (int) Math.pow(2, (distanceList.size())); 
        int sayi = 0; 
        for (int i = 0; i < altKumeSayisi; i++) {
            subSets.add(new ArrayList<Integer>()); 
            sayi = i; 
            for (int j = 0; j < distanceList.size(); j++) { 
                //gelinen sayının 2 ye modu alınır böylece binary sistemde varlığı kontrol edilir 
                if (sayi % 2 == 1) { 
                    subSets.get(i).add(distanceList.get(j)); 
                    if(X <= distanceList.get(j)){
                        tourProfit += profitList.get(j);
                    }
                } //sayı 2 den küçük ise artık kontrole gerek kalmaz döngüden çıkılır 
                if (sayi == 1) { 
                    break; 
                } 
                // burada bilinen decimal sayıyı binary' e çevirme işlemi yapılır(2 ye bölme) 
                if (sayi >= 2) { 
                    sayi = sayi / 2; 
                } 
            } 
            if(max < tourProfit){
                max = tourProfit;
            }
            tourProfit = 0;
        } 

        System.out.println(max);

    }

}
public class BruteForce{
    //dosya okuma işlemleri
    public static List<Integer> okuyucu(String filepath)throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = br.readLine();
            String[] values = line.split(",");
            List<String> arrayList=new ArrayList<>();
            arrayList = Arrays.asList(values);
            List<Integer> newList = arrayList.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());

            return newList;
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        BFStation station = new BFStation(1000);
        station.distanceList = okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Dist_on.csv");
        station.profitList= okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Kar_on.csv");

        //station.distanceList = okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Dist_yuz.csv");
        //station.profitList= okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Kar_yuz.csv");

        /*station.distanceList = okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Dist_bin.csv");
        station.profitList= okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Kar_bin.csv");*/

        /*station.distanceList = okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Dist_yuzbin.csv");
        station.profitList= okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Kar_yuzbin.csv");*/

        /*station.distanceList = okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Dist_birmilyon.csv");
        station.profitList= okuyucu("C:\\Users\\ali\\Desktop\\proje3\\Kar_birmilyon.csv");*/
        
        station.maxProfit();

        long endTime = System.currentTimeMillis();
        long estimatedTime = endTime - startTime; 
        double seconds = (double)estimatedTime/1000;

        System.out.println("time second: " + seconds);
    }
}