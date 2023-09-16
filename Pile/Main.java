import java.util.*;

public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Dammi una stringa\n>");
		String s = sc.nextLine();
		Pila p = new Pila();
		System.out.println("\n--Pushando stringa--");
		
		for(int i = s.length()-1; i > -1; i--){
			char c = s.charAt(i);
			p.push(c);
			System.out.println("> Carattere caricato: " + c);
		}
		
		System.out.println("--Push finito--");
		System.out.println("\n--Poppando pila--");
		
		String r = "";
		for(int i = 0; i < s.length(); i++){
			r = r + p.pop();
			System.out.println("> " + r);
		}
		
		System.out.println("--Pop finito--");
	}
}