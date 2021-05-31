/*
 * @SafiyeSenaMerdin
 * @SelcenFethiyeMersinli
 * GREEDY algoritması aç gözlü algoritmadır.
 * İstenilen şeyi aldığı anda probleme çözüm aramayı bırakır.
 * baz istasyonu probleminde problemi en karlı şekilde çözülmesi beklenir. 
 * ilk ihtimalden(kombinasyon) başlayarak pivot bulmaya çalışır, pivotu bulduğu an max karı elde etmiş olur
 * pivot: kendinden önceki ve sonraki elemanlardan büyüktür
 */

import java.io.BufferedReader;
import java.io.FileReader;

class GStation{
    public int[] distance;
    public int[] profit;
    public int maxProfit; //tüm ihtimaller(kombinasyon) sonucunda elde edilen max kar 
    public int tourProfit = 0;
    public int X; //istasyonlar arası min uzaklık
    
    //constructor
    GStation(int X ){
        this.X = X;
        maxProfit = 0;
    }

    //max karı hesaplamak için kulllanılan yardımcı fonksiyon
    public int kiyaslama(int temp){
        if(maxProfit < temp){
            maxProfit = temp;
        }
        return maxProfit;
    }

    //max karı hesaplayan fonksiyon, max karı return eder
    public int max(){
        int sayac = 0; //i'nin değişime uğramadan önceki değerini tutar.
        int maxProfit = 0; //max karı tutar.
        
        //farklı ihtimalleri(kombinasyon) bulmak için kullanılan döngü
        for(int i = 0; i < distance.length; i++){
            sayac = i; //i'nin değişime uğramadan önceki değeri
            
            //her bir ihtimal(kombinasyon) için karı hesaplayan döngü
            for(int j = i + 1; j < distance.length; j++){
                if(X <= distance[j]){ //2 istasyon arası mesafe verilen değere eşit ya da verilen değerden büyük mü kontol eder.
                    tourProfit += profit[j]; //her turda kullanılan istasyon elemanının karını ekler.
                    i = j;  //örüntüyü sağlamak içsin gerekli.
                } 
            }
            i = sayac; //değişen i'yi tekrar eski haline döndürür.
            maxProfit = kiyaslama(tourProfit); //bir ihtimalin(kombinasyon) karı ile max karı kıyaslayıp max karı return eder.
            tourProfit = 0;
        }
        return maxProfit;
    }


    //10 icin****************************************************************************************
    public void Onluk(){
            
        String line = "";  
        String line2 = "";  
        String pathDist = "C:\\Users\\ali\\Desktop\\proje3\\Dist_on.csv";
        String pathKar = "C:\\Users\\ali\\Desktop\\proje3\\Kar_on.csv";

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(pathDist));
            BufferedReader br2 = new BufferedReader(new FileReader(pathKar));

            while(((line = br1.readLine()) != null) && ((line2 = br2.readLine()) != null)){
                String[] values = line.split(",");
                String[] values2 = line2.split(",");

                int[] distanceOn = new int[values.length];
                int[] profitOn = new int[values.length];
                for(int i = 0; i < values.length; i++) {
                    distanceOn[i] = Integer.parseInt(values[i]);
                    profitOn[i] = Integer.parseInt(values2[i]);
                }
                //Arrays.sort(distance);
                distance = distanceOn;
                profit = profitOn;
                
            }
            br1.close();
            br2.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //100 için**************************************************************************************
    public void Yuzluk(){
        
        String line = "";  
        String line2 = "";  
        String pathDist = "C:\\Users\\ali\\Desktop\\proje3\\Dist_yuz.csv";
        String pathKar = "C:\\Users\\ali\\Desktop\\proje3\\Kar_yuz.csv";

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(pathDist));
            BufferedReader br2 = new BufferedReader(new FileReader(pathKar));

            while(((line = br1.readLine()) != null) && ((line2 = br2.readLine()) != null)){
                String[] values = line.split(",");
                String[] values2 = line2.split(",");

                int[] distanceYuz = new int[values.length];
                int[] profitYuz = new int[values.length];
                
                for(int i = 0; i < values.length; i++) {
                    distanceYuz[i] = Integer.parseInt(values[i]);
                    profitYuz[i] = Integer.parseInt(values2[i]);
                }
                //Arrays.sort(distance);
                distance = distanceYuz;
                profit = profitYuz;
            }
            br1.close();
            br2.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //1000 icin********************************************************************************************
    public void Binlik(){
        
        String line = "";  
        String line2 = "";  
        String pathDist = "C:\\Users\\ali\\Desktop\\proje3\\Dist_bin.csv";
        String pathKar = "C:\\Users\\ali\\Desktop\\proje3\\Kar_bin.csv";

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(pathDist));
            BufferedReader br2 = new BufferedReader(new FileReader(pathKar));

            while(((line = br1.readLine()) != null) && ((line2 = br2.readLine()) != null)){
                String[] values = line.split(",");
                String[] values2 = line2.split(",");

                int[] distanceBin = new int[values.length];
                int[] profitBin = new int[values.length];                
                
                for(int i = 0; i < values.length; i++) {
                    distanceBin[i] = Integer.parseInt(values[i]);
                    profitBin[i] = Integer.parseInt(values2[i]);
                }
                //Arrays.sort(distance);
                distance = distanceBin;
                profit = profitBin;
            }
            br1.close();
            br2.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //yuz bin icin*************************************************************************************
    public void YuzBin(){
        
        String line = "";  
        String line2 = "";  
        String pathDist = "C:\\Users\\ali\\Desktop\\proje3\\Dist_yuzbin.csv";
        String pathKar = "C:\\Users\\ali\\Desktop\\proje3\\Kar_yuzbin.csv";

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(pathDist));
            BufferedReader br2 = new BufferedReader(new FileReader(pathKar));

            while(((line = br1.readLine()) != null) && ((line2 = br2.readLine()) != null)){
                String[] values = line.split(",");
                String[] values2 = line2.split(",");

                int[] distanceYuzBin = new int[values.length];
                int[] profitYuzBin = new int[values.length];
                for(int i = 0; i < values.length; i++) {
                    distanceYuzBin[i] = Integer.parseInt(values[i]);
                    profitYuzBin[i] = Integer.parseInt(values2[i]);
                }
                //Arrays.sort(distance);
                distance = distanceYuzBin;
                profit = profitYuzBin;
                
            }
            br1.close();
            br2.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Bir Milyon icin*******************************************************************************
    public void BirMilyon(){
        
        String line = "";  
        String line2 = "";  
        String pathDist = "C:\\Users\\ali\\Desktop\\proje3\\Dist_birmilyon.csv";
        String pathKar = "C:\\Users\\ali\\Desktop\\proje3\\Kar_birmilyon.csv";

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(pathDist));
            BufferedReader br2 = new BufferedReader(new FileReader(pathKar));

            while(((line = br1.readLine()) != null) && ((line2 = br2.readLine()) != null)){
                String[] values = line.split(",");
                String[] values2 = line2.split(",");

                int[] distanceBirMilyon = new int[values.length];
                int[] profitBirMilyon = new int[values.length];
                for(int i = 0; i < values.length; i++) {
                    distanceBirMilyon[i] = Integer.parseInt(values[i]);
                    profitBirMilyon[i] = Integer.parseInt(values2[i]);
                }
                //Arrays.sort(distance);
                distance = distanceBirMilyon;
                profit = profitBirMilyon;
                
            }
            br1.close();
            br2.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class Greedy {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis(); 

        GStation station = new GStation(1000);  
        station.Onluk();
        //station.Yuzluk();
        //station.Binlik();
        //station.YuzBin();
        //station.BirMilyon();
        
        System.out.println("Max Profit: " + station.max());

        long endTime = System.currentTimeMillis();
        long estimatedTime = endTime - startTime; 
        double seconds = (double)estimatedTime/1000;

        System.out.println("time second: " + seconds);
        
       
       

    }
}
