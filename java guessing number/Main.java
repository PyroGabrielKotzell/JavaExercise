import java.util.*;
public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n, in, l = 11;
		String s;
		System.out.print("Benvenuto nel Gioco dell'indovina Numero,\nQuesto e' il Menu:\n-Inizia\n\n-Impostazioni\n\n-Esci\n\n>");
		while (true){
			s = sc.nextLine();
			if(s.equalsIgnoreCase("esci")) break;
			else if(s.equalsIgnoreCase("Inizia")){
				n = (int)(Math.random()*l);
			}
		}
	}
}