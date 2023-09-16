import java.io.*;
import java.util.Scanner;
class main{
	public static void main(String[] args){
		//Scanner scanner=new Scanner(System.in);
		System.out.println("inserisci numero decimale");
		String Line = System.console().readLine();
		//int s = scanner.nextInt();
		int s = Integer.parseInt(Line);
		String binario = "";
		while(s!=0){
			//binario=binario+s%2;
			binario=s%2+binario;
			System.out.println(String.format("%5d", s)+"|2 = "+s%2);
			s=s/2;
		}
		System.out.println("Stringa in binario e':"+binario);
		//StringBuffer sb = new StringBuffer(binario);
		//System.out.println(sb.reverse().toString());
	}
}