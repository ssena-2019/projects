package lab04_oop;

import java.util.Scanner;
public class MyMath {
	
	long factorial(int n) {
		long fact = 1;
		int i = 1;
		while(n >= 0 && i<= n) {	
			if(n == 0 && n == 1)
				fact = 1;
			else
				fact *= i;
		i++;
		}		
		return fact;
	}
	
	 long fibonacci(int n){
		 long n1 = 0;
		 long n2 = 1;
		 long top = 0;
		 for(int i = 0; i <= n-2; i++){
			 if(n == 0)
				top = 0;
			 else 
				top = n1 + n2;
	            n1 = n2;
	            n2 = top;
		}
		
		 return top;
	}
	 
	void printPyramidNumbers(int n){

		for (int i = 1; i <= n; ++i) {
		      for (int j = 1; j <= i; ++j) {
		        System.out.print(j);
		      }
		      System.out.println();
		}
	}
	
	int gcd(int a, int b) {
			if(a != 0 && b == 0)
				return a;
			if(a == 0 && b != 0)
				return b;
			else if(a == b) 
				return a;
			else if(b > a) 
				return gcd(a, b-a);
			else if(a > b)
				return gcd(a-b, b);
			else
				return 0;
	}
	
	int lengthOfCollatzSeq(int n){
		int i = 1;
		while(n != 1) {
			if(n % 2 == 0) {
				n = n / 2;
				i++;
			}
			if(n == 1)
				break;
			if(n % 2 == 1) {
				n = (3 * n) + 1;
				i++;
			}
		}
		return i;
	}
	
	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		MyMath object = new MyMath();
		System.out.println("Enter a number:");
		int n = myObj.nextInt();
		myObj.close();
		/*System.out.println("a:");
		int a = myObj.nextInt();
		System.out.println("b:");
		int b = myObj.nextInt();*/
		//myObj.close();
		
		System.out.println("girilen sayinin faktoriyeli: " + object.factorial(n));
		System.out.println( n + ". fibonacci sayisi: "+ object.fibonacci(n) + "\n");
		object.printPyramidNumbers(n); 
		System.out.println( "ebob: "+ object.gcd(a, b)); 
		System.out.println( "length of queue: " + object.lengthOfCollatzSeq(n));
		
		
	}

}










