import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean cont = true;

        while (cont) { // loop per farlo molteplici volte
            Scanner sc = new Scanner(System.in);

            System.out.print("Dammi operando 1.\n\\>");
            int n1 = getint(sc);
            sc = new Scanner(System.in);

            System.out.print("Dammi operando 2.\n\\>");
            int n2 = getint(sc);
            sc = new Scanner(System.in);

            System.out.print("Dammi operazione.\n\\>");
            boolean op = true;

            while (op) { // loop se operazione non giusta
                op = false;
                char c = sc.nextLine().charAt(0); // prendo operazione qui!
                sc = new Scanner(System.in);

                if (c == '+') System.out.println(n1 + " + " + n2 + " = " + (n1 + n2)); // le operazioni // '+'
                else if (c == '-') System.out.println(n1 + " - " + n2 + " = " + (n1 - n2)); // '-'
                else if (c == '*')
                    System.out.println(n1 + " * " + n2 + " = " + (n1 * n2)); // '*' (per, moltiplicazione)
                else if (c == '/')
                    System.out.println(n1 + " / " + n2 + " = " + (int) ((float) n1 / n2) + " resto: " + (int) ((float) n1 % n2)); // '/' (diviso con resto)
                else if (c == '\\')
                    System.out.println(n1 + " \\ " + n2 + " = " + ((float) n1 / n2)); // '\' (diviso con virgola)
                else {
                    System.out.print("operazione non valida.\n\\>"); // operazione non valida, continuo il loop
                    op = true;
                }
            }

            System.out.print("continuare? [y , n].\n\\>"); // chiedo se continuare

            while (true) {
                char x = sc.nextLine().charAt(0);
                sc = new Scanner(System.in);
                if (x == 'y') break;
                else if (x == 'n') cont = false;
                else System.out.print("non è un comando valido.\n\\>");
            }

        } // fine loop
    }

    private static int getint(Scanner sc) { // per ottenere intero
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) { // input sbagliato
                System.out.print("NAN!\n\\>");
                sc = new Scanner(System.in);
            }
        }
    }
}