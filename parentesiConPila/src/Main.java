import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pila p = new Pila();
        String s = new Scanner(System.in).nextLine();
        String parAp = "{[(", parCh = "}])";

        boolean sbagliata = false;

        int i = 0;

        for (char c : s.toCharArray()) {
            boolean contiene = parAp.contains(c + "");
            if (contiene) {
                p.push(c);
                i++;
            }

            contiene = parCh.contains(c + "");
            if (contiene && i != 0) {
                if (parAp.indexOf(p.pop()) != parCh.indexOf(c)) {
                    sbagliata = true;
                    break;
                } else i--;
                sbagliata = i != 0;
            } else if (contiene) {
                sbagliata = true;
                break;
            }
        }

        if (sbagliata) System.out.println("sintassi sbagliata");
        else System.out.println("sintassi giusta");
    }
}