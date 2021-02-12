import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class CrazyMarket implements MyQueue<Customer>{
	static Node head;
	Node tail;
	Node current;
	Node front;
	Node back;
	static double time=0;
	//static double waitingTime=0;
	public int currentSize;
	static double kasaBosalmaZamani;
	
	/**
	 * kasa listesi
	 * @param headData
	 */
    public CrazyMarket() {

    }
    
	/**
	 * numberOfCustumer ile verilen sayida  
	 * musteri hizmet gorene kadar calismaya devam eder*/
	public CrazyMarket(int numberOfCustomer) {

		testMethodu(numberOfCustomer);
		
	}

	/**
	 * numberOfCustumer ile verilen sayida  
	 * musteri hizmet gorene kadar calismaya devam eder, 
	 * calisirken verilen tekerlemeyi kullanir
	 * */
	public CrazyMarket(int numberOfCustomer, String text) {
		
		tekerleme = text;
		testMethodu(numberOfCustomer);
		
	}
	
    class Node {
        Customer key;
        Node prev = null;
        Node next = null;

        public Node(Customer key) {
            this.key = key;
            
        }
    }

	/**
	 * default tekerleme
	 */
	public static String tekerleme = "O piti piti karamela sepeti "
			+ "\nTerazi lastik jimnastik "
			+ "\nBiz size geldik bitlendik Hamama gittik temizlendik.";
	
	/**
	 * 
	 * @param text
	 * @return tekerlemedeki sesli harf sayýsý
	 */
	public static int tekerlemeSayiBul(String text) {
		
		char karakter;	
		int sayi = 0;
		int sira;

		for(sira=0; sira<text.length(); sira++) {
			karakter = text.charAt(sira);
			if (karakter=='a'|| karakter=='e'|| karakter=='ý'|| karakter=='i'|| 
					karakter=='o'|| karakter=='ö'|| karakter=='u'|| karakter=='ü' ) sayi++;
		}
		
		return sayi;
	}
	
	/**
	 * 
	 * @param üretilecek sayýnýn aralýk baþlangýcý
	 * @param üretilecek sayýnýn aralýk sonu
	 * @return üretilen sayý
	 */
	public static double sayiUret(double min, double max) {
		
        double random = ThreadLocalRandom.current().nextDouble(min, max);
        
		return Math.round(random * 100.0) / 100.0;
	}
			
	
	/**
	 * Müþteri üret metodu
	 * @return yeni müþteri
	 */
	public static Customer musteriUret(int id) {

		Customer musteriData = new Customer();
		
		musteriData.id = id;
				
		///     ***************      geliþ süresi üret      ***************
		if(head==null) {
			musteriData.arrivalTime = 0;
		}else {
			musteriData.arrivalTime = sayiUret(0.0, 2.0);
		}
		
		//     ***************      iþlem süresi üret      ***************
		musteriData.hizmetSuresi = sayiUret(0.1, 3.0);

		if(id==0) {
			musteriData.waitingTime = 0;
		}else {
			if(time < kasaBosalmaZamani) {
				musteriData.waitingTime = kasaBosalmaZamani - time;
			}else {
				musteriData.waitingTime = 0;
			}
		}
		
		if(id==0) {
			kasaBosalmaZamani = musteriData.hizmetSuresi;
			time=0;
		}else {
			kasaBosalmaZamani += musteriData.hizmetSuresi;
			time += musteriData.arrivalTime;
		}
		
        System.out.println("ID: " + (musteriData.id + 1)
        		+ " Geliþ: " + + Math.round(musteriData.arrivalTime * 100.0) / 100.0
        		+ " Hizmet: " + + Math.round(musteriData.hizmetSuresi * 100.0) / 100.0
        		+ " Bekleme: " + + Math.round(musteriData.waitingTime * 100.0) / 100.0
        		+ " Kasa Boþalma: " + Math.round(kasaBosalmaZamani * 100.0) / 100.0
        		+" Zaman: " + Math.round(time * 100.0) / 100.0);

		return musteriData;
	}
	
	/**
	 * deneme metodu
	 * @param musteriSayisi
	 */
	public static void testMethodu(int musteriSayisi) {
		
		CrazyMarket musteriler = new CrazyMarket();
		
		for(int i=0; i<musteriSayisi; i++) {
			
				Customer yeniMusteri = musteriUret(i);
				if(yeniMusteri.waitingTime<10) {
					musteriler.enqueue(yeniMusteri);
				}else {
					kasaBosalmaZamani -= yeniMusteri.hizmetSuresi;
					time -= yeniMusteri.arrivalTime;
				}
				

		}
		
	}
	
	/** 
	 * kuyrugun basindaki musteriyi ya da tekerleme 
	 *  ile buldugu musteriyi return eder
	 */
	public Customer chooseCustomer() {
		return null;		
	}

	@Override
	public Iterator<Customer> iterator() {
	
        return null;
	}
	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean enqueue(Customer item) {
        
        Node newCustomer = new Node(item); 
        newCustomer.next = null; 
  
        if (isEmpty()) { 
            head = newCustomer;
            
        } 
        else { 
            Node last = head; 
            while (last.next != null) { 
                last = last.next; 
            } 
  
            last.next = newCustomer;
            current = newCustomer;
        }
    	//time+= current.key.arrivalTime;

        //currentNode = currentNode.next; 
        return false;
	}

	@Override
	public Customer dequeuNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer dequeuWithCounting(String tekerleme) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {

		
		// TODO Auto-generated method stub
		new CrazyMarket(100);
		//new CrazyMarket(200, "O piti piti karamela sepeti ");
		show();
	}
	
	public static void show() {
	    Node currentNode = head;
	    time=0;
	    kasaBosalmaZamani=0;
	    if(currentNode == null){
	        System.out.println("Linked list is empty");
	    }
	    else {
	    	
	        while(currentNode != null) {
	        	time += currentNode.key.arrivalTime;
	        	if(kasaBosalmaZamani==0) {
	        		kasaBosalmaZamani = currentNode.key.hizmetSuresi;
	        	}else {
	        		kasaBosalmaZamani += currentNode.key.hizmetSuresi;
	        	}
	            System.out.println("ID: " + (currentNode.key.id + 1)
	            		+ " Geliþ: " + + Math.round(currentNode.key.arrivalTime * 100.0) / 100.0
	            		+ " Hizmet: " + + Math.round(currentNode.key.hizmetSuresi * 100.0) / 100.0
	            		+ " Bekleme: " + + Math.round(currentNode.key.waitingTime * 100.0) / 100.0
	            		+ " Kasa Boþalma: " + Math.round(kasaBosalmaZamani * 100.0) / 100.0
	            		+" Zaman: " + Math.round(time * 100.0) / 100.0);
	            
	            currentNode = currentNode.next;   
	        }
	        
	    }
	}
}