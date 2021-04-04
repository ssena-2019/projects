public class Musteri {
	
	String isim;
	int hesapNo;
	int bakiye;
	
	Musteri(String name, int hN, int bak){
		isim = name;
		hesapNo = hN;
		bakiye = bak;
	}
	

	public static void main(String []args) {
		Musteri musteri1 = new Musteri("Ayse", 111, 3000);
		Musteri musteri2 = new Musteri("Ali", 222, 2000);
		
		System.out.println("musteri1 isim: " + musteri1.isim + "\nmusteri1 hesap no:" + musteri1.hesapNo 
				+"\nmusteri1 bakiye:" + musteri1.bakiye);
		
		System.out.println("musteri2 isim: " + musteri2.isim + "\nmusteri2 hesap no:" + musteri2.hesapNo 
				+"\nmusteri2 bakiye:" + musteri2.bakiye);
		
		System.out.println("müsterilerin bakiyeleri arasındaki fark: " + Math.abs(musteri1.bakiye-musteri2.bakiye));
		
		
	}
}

