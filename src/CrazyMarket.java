import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/* EK METHODLAR:
 * public static int tekerlemeSayiBul(String text)
 * public static double sayiUret(double min, double max)
 * public static Customer musteriUret(int id)
 * public static void show()
 * void deleteNode(Node del)
 * public Customer chooseCustomer(int secilen)
 * public class CrazyMarketLinkedListIterator implements Iterator<Customer>
 */

public class CrazyMarket implements MyQueue<Customer>{
	
	public static Node head; //kuyruðun baþýndaki obje
	public static Node tail; //kuyruðun sonundaki obje
	public static Node current; //þuanki müþteri

	static int boyut; //size() da kullanýldý
	static double time=0; //o anki zaman tutan deðiþken
	static double kasaBosalmaZamani; //müþterinin kasadan ayrýlma zamaný
	
	//node classý: müþterileri iþaret etmek için
	class Node {
        
		Customer key; //iþaret edilen müþteriyi tutan, customer classýndan üretilen obje
        Node front = null; //müþterinin önündeki elemaný iþaret eder
        Node back = null; //müþterinin önündeki elemaný iþaret eder
        
        //Node class constructor
        public Node(Customer key) {
            this.key = key; 
        }
    }
	
	//muþteriler objesi(global)
	static CrazyMarket musteriler = new CrazyMarket();
	
	
	/**
	 * kasa listesi için constructor
	 * @param headData
	 */
    public CrazyMarket(){

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
	
    /**
	 * default tekerleme
	 */
	public static String tekerleme = "O piti piti karamela sepeti "
			+ "\nTerazi lastik jimnastik "
			+ "\nBiz size geldik bitlendik Hamama gittik temizlendik.";
	
	//tekerlemedeki hece sayýsýný bulan method
	public static int tekerlemeSayiBul(String text) {
		
		char karakter;	
		int sayi = 0;
		int sira;

		for(sira=0; sira<text.length(); sira++) {
			karakter = text.charAt(sira);
			if (karakter=='a'|| karakter=='e'|| karakter=='ý'|| karakter=='i'|| 
				karakter=='o'|| karakter=='ö'|| karakter=='u'|| karakter=='ü' ) 
				
				sayi++;
		}
		
		return sayi; //hece sayýsýný return eder
	}
	
	//random sayý üreten method (min-max aralýk gönderilir)
	public static double sayiUret(double min, double max) {
		
        double random = ThreadLocalRandom.current().nextDouble(min, max);
        
		return Math.round(random * 100.0) / 100.0;
	}
	
	//müþteri üretip onlara bilgi atayan method
	public static Customer musteriUret(int id) {
		
		//Customer tipinde obje üretilir, bu obje müþteri bilgilerini tutar.
		Customer musteriData = new Customer(); 
		
		musteriData.id = id; //parametreden gelen id'yi müþteri bilgisine koyar.
				
		/// *************** geliþ süresi üret ***************
		
		//eðer baþtaki eleman null ise yani kuyruk boþsa;
		if(head==null) { 
			musteriData.arrivalTime = 0; //ilk müþterinin geliþ zamaný sýfýrdan baþlar.
		}else { //eðer kuyruk boþ deðilse geliþ zamaný 0-2 arasýdýr.
			musteriData.arrivalTime = sayiUret(0.0, 2.0); 
		}
		
		// *************** iþlem süresi üret ***************
		
		musteriData.hizmetSuresi = sayiUret(0.1, 3.0); //müþterilerin hizmet süresini 0-3 arasý süreyle üretir
		
		//eðer id 0 ise yani kuyruðun baþýndaki eleman ise
		if(id==0) { 
			musteriData.waitingTime = 0;
		}else { //kuyruðun baþýndaki eleman deðilse
			//eðer o anki zaman kasa boþalma anýndan küçükse 
			if(time < kasaBosalmaZamani) {
				musteriData.waitingTime = kasaBosalmaZamani - time; //bekleme zamaný; ayrýlma zamanýyla zamanýn farkýna eþittir.
			}else { //eðer kasadan ayrýlma zamaný zamandan küçükse bekleme zamaný sýfýrdýr.
				musteriData.waitingTime = 0; 
			}
		}
		
		if(id==0) { //eðer ilk müþteriyse
			kasaBosalmaZamani = musteriData.hizmetSuresi; //kasayý boþaltma zamaný iþlem süresine eþittir
			time=0; //ve zamaný sýfýrlarýz
		}else { //ilk müþteri deðilse
			if(musteriData.waitingTime<=10) { //bekleme zamaný 10 sn den küçükse kuyrukta kalmaya devam eder		
				kasaBosalmaZamani += musteriData.hizmetSuresi; 
				time += musteriData.arrivalTime;
			}else { //eðer müþterinin bekleme zamaný 10 sn den büyükse
				System.out.println(id + " ID'li müþterinin bekleme süresi 10 dan büyük olduðu için kuyruktan çýkarýldý!!!" );
			}
		}
        
        return musteriData; //oluþturalan Customer objesini return eder.
	}
	
	//deneme methodu: parametre olarak kuyruða girecek müþteri sayýsýný alýr
	public static void testMethodu(int musteriSayisi) {

		for(int i=1; i <= musteriSayisi; i++) {
			//döngüyle müþteri sayýsý kadar obje üretir.
			Customer yeniMusteri = musteriUret(i);
			//eðer bekleme zamaný 10 sn den küçükse müþteriyi kuyrukta tutar
			if(yeniMusteri.waitingTime < 10) {
				musteriler.enqueue(yeniMusteri);
			}
		}
		
		//tekerlemeyle müþteri seçer.
		musteriler.dequeuWithCounting(tekerleme);
		 
		show();

	}
	
	/** 
	 * kuyrugun basindaki musteriyi ya da tekerleme 
	 *  ile buldugu musteriyi return eder
	 */
	public Customer chooseCustomer(int secilen) {
		
		//iteratör tanýmlandý
		Iterator<Customer> itr = musteriler.iterator();
		Customer element = null;

		if(secilen == 0) {
			return head.key;	
		}else {
			for(int i=1; i<secilen; i++) {
				element = itr.next();
			}
		}
			
		return element;		
	}
	
	//kuyruk listesinde gezebilmesi için iteratörü implement eden özel class tanýmladým
	public class CrazyMarketLinkedListIterator implements Iterator<Customer>{
		
		Node currentNode = head; //þimdiki node u baþta boþ yapýyoruz.
		
		@Override
		public boolean hasNext() {	//eðer nexti varsa true döndürür, yoksa false döndürür.	
			if(currentNode == null && head != null ) { //eðer þimdiki node null ise ve baþtaki eleman null deðilse			
				return true; //liste doludur.			
			}else if(currentNode != null) {	//			
				return currentNode.back != null;
			}			
			return false; //bunlarýn hiçbiri deðilse kuyruk boþtur.	
		}

		@Override
		public Customer next() { //sýrayý gezer
			if(currentNode == null && head != null) { //eðer kuyrukta yalnýz 1 kiþi varsa			
				currentNode = head; //þimdiki node baþtaki eleman olur
				return head.key;				
			
			}else if(currentNode != null) {
				currentNode = currentNode.back;
				return currentNode.key;
			}
			
			throw new NoSuchElementException(); //
		}
	}
	
	

	@Override
	public Iterator<Customer> iterator() {
		return new CrazyMarketLinkedListIterator(); //tanýmlanan classdan üretilen obje döndürür. 
	}
	
	@Override
	public int size() {
		boyut=0; //sayaç: boyutu tutmasý için
		Node currentNode = head; //þimdiki node u baþtaki eleman olarak belirliyoruz.
		
		
	    if(currentNode == null){ //eðer þimdiki node boþsa yani kuyrukta eleman yoksa 
	        return 0;  
	    }
	    else {
	        while(currentNode != null) { //þimdiki eleman boþ deðilse
	            currentNode = currentNode.back; //þimdiki elemaný, þimdiki elemanýn nexti yapýyoruz
	            boyut++;  //ve boyutu arttýrýyoruz
	        }  
	    }
		return boyut; //boyutu return ediyoruz
	}

	@Override
	public boolean isEmpty() { //baþtaki elemana null atýyoruz
		if(head == null)
			return true;
		else
			return false;
	}

	@Override
	public boolean enqueue(Customer item) {
        
        Node newCustomer = new Node(item); //oluþturulan nesneyi parametre olarak gelen item ile initialize ediyoruz

        if (isEmpty()) { //eðer baþtaki eleman null ise yeni baþ eleman, eklenen node olur
            head = tail = newCustomer;
            head.front = null;    
            tail.back = null;  
            return true;
        } 
        else { //eðer baþtaki eleman null deðilse yani kuyrukta müþteri varsa
    		tail.back = newCustomer;
    		newCustomer.front = tail;
    		tail = newCustomer;
    		newCustomer.back = null;
    		//System.out.println(tail.front.key.hizmetSuresi);
    		return true;
        }
        
      //  return false; //eðer baþtaki eleman null ise kuyruk boþtur ve isEmpty() false döndürür.
	}

	public Customer dequeuNext() {
		head = head.back; //baþtaki elemaný, baþtaki elemanýn next'i yapýlýr böylece baþtaki eleman çýkarýlmýþ olur.
		return null;
	}

	@Override
	public Customer dequeuWithCounting(String tekerleme) {
		
		int musteriSayisi = musteriler.size(); //kuyruktaki müþteri sayýsý hesaplanýr.
		int tekerlemeHeceSayi = tekerlemeSayiBul(tekerleme); //tekerlemedeki hece sayýsý hesaplanýr.
		
		System.out.println("\nMüþteri sayýsý: "+ musteriSayisi); //müþteri sayýsýnýný ve

		int mod = tekerlemeHeceSayi % musteriSayisi; //mod hesaplar, çýkan sonuç bize seçilen müþteriyi verir.
	
		//System.out.println("mod: " + (mod));
		System.out.println("Tekerlemeyle Seçilen Müþteri Id: " + musteriler.chooseCustomer(mod).id);
		
		//seçilen elemaný sil
		if(mod==0){
			dequeuNext();
		}
		
		Node current = new Node(null);
		current = head;		
		int count = 1;
		while(current.back != null) {
			if(count == mod) {
				deleteNode(current);
				return null;
			}
			current = current.back; //eleman sildiði için bir sonraki eleman þimdiki eleman olur.
			count++;
       }
		
		return null;
	}
	
	//node silme methodu
	void deleteNode(Node del){
 
        // Base case
        if (head == null || del == null) {
            return;
        }
 
        //eðer silinen node baþtaki eleman ise
        if (head == del){
            head = del.back; //baþtaki eleman, silinen elemanýn arkasýndaki eleman olur.
        }
        
        //silinecek elemanýn arkasý null deðilse
        if (del.back != null){
            del.back.front = del.front;
        }
        
        //silinecek elemanýn önü null deðilse
        if (del.front != null){
            del.front.back = del.back;
        }
 
    }
	
	//müþterileri ekranda gösteren method
	public static void show() {
		
		kasaBosalmaZamani=0;
		time=0;
		
		//iteratör üretilir, kuyrukta gezebilmek için
		Iterator<Customer> itr = musteriler.iterator();
		
		kasaBosalmaZamani = head.key.hizmetSuresi;
        System.out.println("ID: " + (head.key.id) 
        		+ "	||Geliþ: " + + Math.round(head.key.arrivalTime * 100.0) / 100.0
        		+ "	||Hizmet: " + + Math.round(head.key.hizmetSuresi * 100.0) / 100.0
        		+ "	||Bekleme: " + + Math.round(head.key.waitingTime * 100.0) / 100.0
        		+ "	||Kasa Boþalma: " + Math.round(kasaBosalmaZamani * 100.0) / 100.0
        		+ "	||Zaman: " + Math.round(time * 100.0) / 100.0); 
		
		//iteratörün sonraki elemaný varken
		while(itr.hasNext()) {
			
			//iteratörün iþaret ettiði obje oluþturulur
			Customer element = itr.next();
			
        	time += element.arrivalTime; //o anki zamana bir sonraki müþterinin geliþ zamaný eklenir.
        	if(kasaBosalmaZamani == 0) {
        		kasaBosalmaZamani = element.hizmetSuresi;
        	}else {
        		kasaBosalmaZamani += element.hizmetSuresi;
        	}
            
        	System.out.println("ID: " + (element.id) 
            		+ "	||Geliþ: " + + Math.round(element.arrivalTime * 100.0) / 100.0
            		+ "	||Hizmet: " + + Math.round(element.hizmetSuresi * 100.0) / 100.0
            		+ "	||Bekleme: " + + Math.round(element.waitingTime * 100.0) / 100.0
            		+ "	||Kasa Boþalma: " + Math.round(kasaBosalmaZamani * 100.0) / 100.0
            		+ "	||Zaman: " + Math.round(time * 100.0) / 100.0);        
		}
	}
	
	public static void main(String[] args) {

		new CrazyMarket(100);
		//new CrazyMarket(200, "O piti piti karamela sepeti ");
		
	}
	
}