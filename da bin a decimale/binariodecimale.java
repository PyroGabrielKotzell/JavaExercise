import java.io.*;
import java.util.Scanner;
class binariodecimale{
	public static void main(String[] args){
		Scanner scanner=new Scanner(System.in);
		System.out.println("inserisci numero binario");
		
		int s = scanner.nextInt();
		int decimale = 0, i = 0;
		
		while (s!=0) {
			decimale=decimale+((s%10)*(2^i));
			i++;
			s=s/10;
		}
		System.out.println(decimale);
	}
}