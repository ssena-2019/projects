package lab3_oop;

public class Person {
	String ID;
	String gender;
	double totalCholesterol;
	double HDL;
	
	Person(String id, String gender, double tChol, double hdl){
		this.ID = id;
		this.gender = gender;
		this.totalCholesterol = tChol;
		this.HDL = hdl;
	}
	//Toplam kolesterol�n  HDL de�erine oran�n� hesaplay�p return etmektedir.
	public double getCholesterolRatio(){ 
		double oran = totalCholesterol / HDL;
		return oran;
	}
	//E�er kolesterol sa�l�kl� aralkta ise true, de�ilse false return etmektedir.
	public boolean hasGoodTotalCholesterol(){ 
		if(totalCholesterol < 200)
			return true;
		else 
			return false;
	}
	//E�er HDL sa�l�kl� aral�kta ise true de�lse false return etmektedir.
	public boolean hasGoodHDL(){
		String strK = "female";
		
		if((strK.equalsIgnoreCase(gender))){
			if(HDL >= 45) {
				return true;
			}
			else 
				return false;
		}
		else{
			if(HDL >= 40) {
				return true;
			}
			else 
				return false;
		}
	}
	//Kolesterol oran� sa�l�kl� aral�kta ise true de�ilse false return etmektedir.
	public boolean hasGoodCholesterolRatio(){
		if(getCholesterolRatio() <= 4.5)
			return true;
		else 
			return false;
	}
	
	public void printReport(){
		System.out.println(ID + "'s" + "Report:");
		System.out.println("******************");
		System.out.println("Gender: " + gender);
		
		System.out.print( "Total Cholesterol: " + totalCholesterol);
		if(hasGoodTotalCholesterol() == true)
			System.out.print(" (Good)");
		else
			System.out.print(" (Bad)");

		System.out.print( "\nHDL: " + HDL);
		if(hasGoodHDL() == true)
			System.out.print(" (Good)");
		else
			System.out.print(" (Bad)");
		
		System.out.print( "\nTC/HDL ratio:  " + getCholesterolRatio());
		if(hasGoodCholesterolRatio() == true)
			System.out.print(" (Good)");
		else
			System.out.print(" (Bad)");
		
		System.out.println("\n******************");
	
	}
	
	public static void main(String[] args){
		
		Person p = new Person("0022212345", "male", 156, 30);
		p.printReport();
	}
}
