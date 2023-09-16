import java.io.*;
import java.util.Scanner;
class esadecimale{
	public static void main(String[] args){
		//Scanner scanner=new Scanner(System.in);
		System.out.println("inserisci numero decimale");
		String Line = System.console().readLine();
		//int s = scanner.nextInt();
		int s = Integer.parseInt(Line);
		String esadecimale = "";
		String[] ex={"A","B","C","D","E","F"};
		while(s!=0){
			if(s%16>9){
				esadecimale=ex[s%16-10]+esadecimale;
			}else{
				esadecimale=s%16+esadecimale;
			}
			System.out.println(String.format("%5d", s)+"|16 = "+s%16);
			s=s/16;
		}
		System.out.println("Stringa in esadecimale e': "+esadecimale);
		//StringBuffer sb = new StringBuffer(binario);
		//System.out.println(sb.reverse().toString());
	}
}