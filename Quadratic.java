/**
* BIL122 - Lab 02
* This class calculates .
* @author Sena Merdin, <safiyesenam@gmail.com>
*/

import java.util.Scanner;

public class Quadratic {
	double a;
	double b;
	double c;
	double x1;
	double x2;
	
	//*****************************
	public double getX1() {
        return x1;
    }
	public void setX1(double x1) {
        this.x1 = x1;
    }
	//********************************
	public double getX2() {
        return x2;
    }
	public void setX2(double x2) {
        this.x2 = x2;
    }
	//*******************************
	public double getDiscriminant() {
		double d;
		d = (b*b)-4*a*c;  
		return d;
	}
	
	//constructor
	public Quadratic(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
		
		x1 = (-b + Math.sqrt(getDiscriminant()))/(2*a); //d discriminant
		x2 = (-b - Math.sqrt(getDiscriminant()))/(2*a); //d discriminant
	
	}
	
	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in); // creates a scanner object
		
		System.out.print("a sayisini giriniz:");
		double a = scanner.nextDouble(); // reads a double from the standard input

		System.out.print("b sayisini giriniz:");
		double b = scanner.nextDouble(); // reads b double from the standard input
		
		System.out.print("c sayisini giriniz:");
		double c = scanner.nextDouble(); // reads c double from the standard input

		Quadratic q = new Quadratic(a, b, c);
		
		System.out.println("\nx1:" + q.getX1());
		System.out.println("x2:" + q.getX2());
	}
	
}

