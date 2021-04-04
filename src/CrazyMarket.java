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
	
	public static Node head; //kuyru�un ba��ndaki obje
	public static Node tail; //kuyru�un sonundaki obje
	public static Node current; //�uanki m��teri

	static int boyut; //size() da kullan�ld�
	static double time=0; //o anki zaman tutan de�i�ken
	static double kasaBosalmaZamani; //m��terinin kasadan ayr�lma zaman�
	
	//node class�: m��terileri i�aret etmek i�in
	class Node {
        
		Customer key; //i�aret edilen m��teriyi tutan, customer class�ndan �retilen obje
        Node front = null; //m��terinin �n�ndeki eleman� i�aret eder
        Node back = null; //m��terinin �n�ndeki eleman� i�aret eder
        
        //Node class constructor
        public Node(Customer key) {
            this.key = key; 
        }
    }
	
	//mu�teriler objesi(global)
	static CrazyMarket musteriler = new CrazyMarket();
	
	
	/**
	 * kasa listesi i�in constructor
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
	
	//tekerlemedeki hece say�s�n� bulan method
	public static int tekerlemeSayiBul(String text) {
		
		char karakter;	
		int sayi = 0;
		int sira;

		for(sira=0; sira<text.length(); sira++) {
			karakter = text.charAt(sira);
			if (karakter=='a'|| karakter=='e'|| karakter=='�'|| karakter=='i'|| 
				karakter=='o'|| karakter=='�'|| karakter=='u'|| karakter=='�' ) 
				
				sayi++;
		}
		
		return sayi; //hece say�s�n� return eder
	}
	
	//random say� �reten method (min-max aral�k g�nderilir)
	public static double sayiUret(double min, double max) {
		
        double random = ThreadLocalRandom.current().nextDouble(min, max);
        
		return Math.round(random * 100.0) / 100.0;
	}
	
	//m��teri �retip onlara bilgi atayan method
	public static Customer musteriUret(int id) {
		
		//Customer tipinde obje �retilir, bu obje m��teri bilgilerini tutar.
		Customer musteriData = new Customer(); 
		
		musteriData.id = id; //parametreden gelen id'yi m��teri bilgisine koyar.
				
		/// *************** geli� s�resi �ret ***************
		
		//e�er ba�taki eleman null ise yani kuyruk bo�sa;
		if(head==null) { 
			musteriData.arrivalTime = 0; //ilk m��terinin geli� zaman� s�f�rdan ba�lar.
		}else { //e�er kuyruk bo� de�ilse geli� zaman� 0-2 aras�d�r.
			musteriData.arrivalTime = sayiUret(0.0, 2.0); 
		}
		
		// *************** i�lem s�resi �ret ***************
		
		musteriData.hizmetSuresi = sayiUret(0.1, 3.0); //m��terilerin hizmet s�resini 0-3 aras� s�reyle �retir
		
		//e�er id 0 ise yani kuyru�un ba��ndaki eleman ise
		if(id==0) { 
			musteriData.waitingTime = 0;
		}else { //kuyru�un ba��ndaki eleman de�ilse
			//e�er o anki zaman kasa bo�alma an�ndan k���kse 
			if(time < kasaBosalmaZamani) {
				musteriData.waitingTime = kasaBosalmaZamani - time; //bekleme zaman�; ayr�lma zaman�yla zaman�n fark�na e�ittir.
			}else { //e�er kasadan ayr�lma zaman� zamandan k���kse bekleme zaman� s�f�rd�r.
				musteriData.waitingTime = 0; 
			}
		}
		
		if(id==0) { //e�er ilk m��teriyse
			kasaBosalmaZamani = musteriData.hizmetSuresi; //kasay� bo�altma zaman� i�lem s�resine e�ittir
			time=0; //ve zaman� s�f�rlar�z
		}else { //ilk m��teri de�ilse
			if(musteriData.waitingTime<=10) { //bekleme zaman� 10 sn den k���kse kuyrukta kalmaya devam eder		
				kasaBosalmaZamani += musteriData.hizmetSuresi; 
				time += musteriData.arrivalTime;
			}else { //e�er m��terinin bekleme zaman� 10 sn den b�y�kse
				System.out.println(id + " ID'li m��terinin bekleme s�resi 10 dan b�y�k oldu�u i�in kuyruktan ��kar�ld�!!!" );
			}
		}
        
        return musteriData; //olu�turalan Customer objesini return eder.
	}
	
	//deneme methodu: parametre olarak kuyru�a girecek m��teri say�s�n� al�r
	public static void testMethodu(int musteriSayisi) {

		for(int i=1; i <= musteriSayisi; i++) {
			//d�ng�yle m��teri say�s� kadar obje �retir.
			Customer yeniMusteri = musteriUret(i);
			//e�er bekleme zaman� 10 sn den k���kse m��teriyi kuyrukta tutar
			if(yeniMusteri.waitingTime < 10) {
				musteriler.enqueue(yeniMusteri);
			}
		}
		
		//tekerlemeyle m��teri se�er.
		musteriler.dequeuWithCounting(tekerleme);
		 
		show();

	}
	
	/** 
	 * kuyrugun basindaki musteriyi ya da tekerleme 
	 *  ile buldugu musteriyi return eder
	 */
	public Customer chooseCustomer(int secilen) {
		
		//iterat�r tan�mland�
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
	
	//kuyruk listesinde gezebilmesi i�in iterat�r� implement eden �zel class tan�mlad�m
	public class CrazyMarketLinkedListIterator implements Iterator<Customer>{
		
		Node currentNode = head; //�imdiki node u ba�ta bo� yap�yoruz.
		
		@Override
		public boolean hasNext() {	//e�er nexti varsa true d�nd�r�r, yoksa false d�nd�r�r.	
			if(currentNode == null && head != null ) { //e�er �imdiki node null ise ve ba�taki eleman null de�ilse			
				return true; //liste doludur.			
			}else if(currentNode != null) {	//			
				return currentNode.back != null;
			}			
			return false; //bunlar�n hi�biri de�ilse kuyruk bo�tur.	
		}

		@Override
		public Customer next() { //s�ray� gezer
			if(currentNode == null && head != null) { //e�er kuyrukta yaln�z 1 ki�i varsa			
				currentNode = head; //�imdiki node ba�taki eleman olur
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
		return new CrazyMarketLinkedListIterator(); //tan�mlanan classdan �retilen obje d�nd�r�r. 
	}
	
	@Override
	public int size() {
		boyut=0; //saya�: boyutu tutmas� i�in
		Node currentNode = head; //�imdiki node u ba�taki eleman olarak belirliyoruz.
		
		
	    if(currentNode == null){ //e�er �imdiki node bo�sa yani kuyrukta eleman yoksa 
	        return 0;  
	    }
	    else {
	        while(currentNode != null) { //�imdiki eleman bo� de�ilse
	            currentNode = currentNode.back; //�imdiki eleman�, �imdiki eleman�n nexti yap�yoruz
	            boyut++;  //ve boyutu artt�r�yoruz
	        }  
	    }
		return boyut; //boyutu return ediyoruz
	}

	@Override
	public boolean isEmpty() { //ba�taki elemana null at�yoruz
		if(head == null)
			return true;
		else
			return false;
	}

	@Override
	public boolean enqueue(Customer item) {
        
        Node newCustomer = new Node(item); //olu�turulan nesneyi parametre olarak gelen item ile initialize ediyoruz

        if (isEmpty()) { //e�er ba�taki eleman null ise yeni ba� eleman, eklenen node olur
            head = tail = newCustomer;
            head.front = null;    
            tail.back = null;  
            return true;
        } 
        else { //e�er ba�taki eleman null de�ilse yani kuyrukta m��teri varsa
    		tail.back = newCustomer;
    		newCustomer.front = tail;
    		tail = newCustomer;
    		newCustomer.back = null;
    		//System.out.println(tail.front.key.hizmetSuresi);
    		return true;
        }
        
      //  return false; //e�er ba�taki eleman null ise kuyruk bo�tur ve isEmpty() false d�nd�r�r.
	}

	public Customer dequeuNext() {
		head = head.back; //ba�taki eleman�, ba�taki eleman�n next'i yap�l�r b�ylece ba�taki eleman ��kar�lm�� olur.
		return null;
	}

	@Override
	public Customer dequeuWithCounting(String tekerleme) {
		
		int musteriSayisi = musteriler.size(); //kuyruktaki m��teri say�s� hesaplan�r.
		int tekerlemeHeceSayi = tekerlemeSayiBul(tekerleme); //tekerlemedeki hece say�s� hesaplan�r.
		
		System.out.println("\nM��teri say�s�: "+ musteriSayisi); //m��teri say�s�n�n� ve

		int mod = tekerlemeHeceSayi % musteriSayisi; //mod hesaplar, ��kan sonu� bize se�ilen m��teriyi verir.
	
		//System.out.println("mod: " + (mod));
		System.out.println("Tekerlemeyle Se�ilen M��teri Id: " + musteriler.chooseCustomer(mod).id);
		
		//se�ilen eleman� sil
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
			current = current.back; //eleman sildi�i i�in bir sonraki eleman �imdiki eleman olur.
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
 
        //e�er silinen node ba�taki eleman ise
        if (head == del){
            head = del.back; //ba�taki eleman, silinen eleman�n arkas�ndaki eleman olur.
        }
        
        //silinecek eleman�n arkas� null de�ilse
        if (del.back != null){
            del.back.front = del.front;
        }
        
        //silinecek eleman�n �n� null de�ilse
        if (del.front != null){
            del.front.back = del.back;
        }
 
    }
	
	//m��terileri ekranda g�steren method
	public static void show() {
		
		kasaBosalmaZamani=0;
		time=0;
		
		//iterat�r �retilir, kuyrukta gezebilmek i�in
		Iterator<Customer> itr = musteriler.iterator();
		
		kasaBosalmaZamani = head.key.hizmetSuresi;
        System.out.println("ID: " + (head.key.id) 
        		+ "	||Geli�: " + + Math.round(head.key.arrivalTime * 100.0) / 100.0
        		+ "	||Hizmet: " + + Math.round(head.key.hizmetSuresi * 100.0) / 100.0
        		+ "	||Bekleme: " + + Math.round(head.key.waitingTime * 100.0) / 100.0
        		+ "	||Kasa Bo�alma: " + Math.round(kasaBosalmaZamani * 100.0) / 100.0
        		+ "	||Zaman: " + Math.round(time * 100.0) / 100.0); 
		
		//iterat�r�n sonraki eleman� varken
		while(itr.hasNext()) {
			
			//iterat�r�n i�aret etti�i obje olu�turulur
			Customer element = itr.next();
			
        	time += element.arrivalTime; //o anki zamana bir sonraki m��terinin geli� zaman� eklenir.
        	if(kasaBosalmaZamani == 0) {
        		kasaBosalmaZamani = element.hizmetSuresi;
        	}else {
        		kasaBosalmaZamani += element.hizmetSuresi;
        	}
            
        	System.out.println("ID: " + (element.id) 
            		+ "	||Geli�: " + + Math.round(element.arrivalTime * 100.0) / 100.0
            		+ "	||Hizmet: " + + Math.round(element.hizmetSuresi * 100.0) / 100.0
            		+ "	||Bekleme: " + + Math.round(element.waitingTime * 100.0) / 100.0
            		+ "	||Kasa Bo�alma: " + Math.round(kasaBosalmaZamani * 100.0) / 100.0
            		+ "	||Zaman: " + Math.round(time * 100.0) / 100.0);        
		}
	}
	
	public static void main(String[] args) {

		new CrazyMarket(100);
		//new CrazyMarket(200, "O piti piti karamela sepeti ");
		
	}
	
}