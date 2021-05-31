/*
 * @SafiyeSenaMerdin
 * @SelcenFethiyeMersinli
 * Sıralama işlemi BINARY SEARCH
 * tabulation yöntemi
 * Bütün ihtimalleri(kombinasyon) göz önünde bulundurur.
 * baz istasyonu probleminde problemi en karlı şekilde çözülmesi beklenir.
 * istasyonların max kara en uygun olan yerleşim düzeni bulunur.  
 */

import java.io.BufferedReader;
import java.io.FileReader;

class DPStation{
    public int[] distance; //mesafeleri tutan dizi
    public int[] profit; //karları tutan dizi
    public int[] tourMax; //turdaki karları tutan dizi
 
    public int maxProfit;
    public int X;

    DPStation(int X ){
        this.X = X;
        maxProfit = 0;
    }

    //max karı hesaplayan method, max karı return eder.
    public int max(){  
        for(int i = 0;i < distance.length; i++){
            //her bir ihtimal(kombinasyon) için karı hesaplayan döngü
            for(int j = 0; j < distance.length;j++){ 
                if(X <= distance[j]){ //2 istasyon arası mesafe verilen değere eşit ya da verilen değerden büyük mü kontol eder.
                    maxProfit += profit[j]; //her turda kullanılan istasyon elemanının karını ekler.
                } 
            }    
            tourMax[i] = maxProfit; //her ihtimale(kombinasyon) karşı hesaplanan kar bir diziye eklenir.
            maxProfit = 0; //tekrar hesaplamak için max profit sıfırlanır.
        }

        //her ihtimalin(kombinasyon) karını kıyaslayıp max karı bulur.
        for(int i = 0;i < tourMax.length; i++){
            if(maxProfit < tourMax[i]){
                maxProfit = tourMax[i]; //max profite o turdaki profiti ekler.
            }
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
                    distanceOn[i] = Integer.parseInt(values[i]); //string'i integer'a cevirir.
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

public class DynamicProgramming {
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        DPStation station = new DPStation(1000); //nesne oluşturma

        //station.Onluk();
        //station.Yuzluk();
        //station.Binlik();
        //station.YuzBin();
        //station.BirMilyon();
        station.tourMax = new int[station.distance.length];
        for(int i = 0; i < station.tourMax.length; i++){
            station.tourMax[i] = 0;
        } 
        System.out.println("max profit from DP: " + station.max());

        long endTime = System.currentTimeMillis();
        long estimatedTime = endTime - startTime; 
        double seconds = (double)estimatedTime/1000;

        System.out.println("time second: " + seconds);
    }
} 

