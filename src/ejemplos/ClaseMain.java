package ejemplos;

import java.util.Scanner;

public class ClaseMain {

	public static void main(String[] args) {
		
		System.out.print("Introduzca el numero 1: ");
		Scanner num = new Scanner(System.in);
		float n1 = num.nextFloat();
		
		System.out.print("Introduzca el numero 2: ");
		
		float n2 = num.nextFloat();
		
		
		System.out.print("Introduzca el numero 3: ");
		
		float n3 = num.nextFloat();
		
		
		float media = (n1+n2+n3) / 3;
		
		int mediaent = (int)media;
		
		System.out.print("La media es: " + mediaent);
		
		num.close();
	
	}
		
}
