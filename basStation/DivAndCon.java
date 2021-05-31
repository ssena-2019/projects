/*
 * @SafiyeSenaMerdin
 * @SelcenFethiyeMersinli
 * böl ve fethet algoritmasıdır, recursive kullanılarak yapıldı
 * Diziler kullanılarak yapıldı
 */

import java.io.BufferedReader;
import java.io.FileReader;

class DC1Station {

    public int[] distance; //mesafeleri tutan dizi
    public int[] profit; //karları tutan dizi
    public int[] tourMax; //bir turdaki max kar miktari
    public int maxProfit; //tüm ihtimaller sonucunda elde edilen max kar 
    public int X; //istasyonlar arası min uzaklık
    public int sayac; //i'nin değişime uğramadan önceki değerini tutar.
    
    //constructors
    DC1Station(int X ){
        this.X = X;
        maxProfit = 0;
        sayac = 0;
    }

    public void divide(int arr[], int l, int r)
    {
        if (l < r) {
            int m =l+ (r-l)/2; //orta kısmı bul
            if(arr[m] >= X){
                maxProfit += profit[m]; //max profite o anki profiti ekler
            }
            // 1 ve 2.parçaya ayırır.
            divide(arr, l, m); //recursive
            divide(arr, m + 1, r); //recursive
        }
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
                    distanceOn[i] = Integer.parseInt(values[i]); //stringi intiger'a cevirme
                    profitOn[i] = Integer.parseInt(values2[i]);
                }
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

public class DivAndCon {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();

        DC1Station station = new DC1Station(1000);
        station.Onluk();
        //station.Yuzluk();
        //station.Binlik();
        //station.YuzBin();
        //station.BirMilyon();
       
        station.divide(station.distance, 0, station.distance.length);
        System.out.println("Max Profit: " + station.maxProfit);

        long endTime = System.currentTimeMillis();
        long estimatedTime = endTime - startTime; 
        double seconds = (double)estimatedTime/1000;

        System.out.println("time second: " + seconds);

    }
}
