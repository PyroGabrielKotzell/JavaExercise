import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("dammi stringa!\n>");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc = new Scanner(System.in);
        System.out.println("lunghezza stringa: " + calcolaLung(s));
        System.out.println("ultimo carattere: " + ultimoChar(s));
        System.out.println("stringa al contrario: " + stringaContrario(s));
        System.out.print("dammi numero carattere!\n>");
        int n;
        while (true) {
            try {
                n = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input Non Valido!");
                sc = new Scanner(System.in);
            }
        }
        sc = new Scanner(System.in);
        System.out.println("carattere alla pos. " + n + ": " + trovaChar(s, n));
        System.out.print("dammi numero!\n>");
        while (true) {
            try {
                n = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input Non Valido!");
                sc = new Scanner(System.in);
            }
        }
        if (n < 0) System.out.println("numero negativo, ora non lo faccio >:(");
        else System.out.println("stringa in binario: " + stringaBinario(n));
    }

    private static String stringaBinario(int n) {
        if (n == 0) return "0";
        return concatena(stringaBinario(n / 2), n % 2 + "");
    }

    private static char trovaChar(String s, int n) {
        if (n == 0) return primoChar(s);
        return trovaChar(restoStr(s), n - 1);
    }

    private static String stringaContrario(String s) {
        try {
            return concatena(stringaContrario(restoStr(s)), primoChar(s) + "");
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    private static char ultimoChar(String s) {
        try {
            return ultimoChar(restoStr(s));
        } catch (IndexOutOfBoundsException e) {
            return primoChar(s);
        }
    }

    private static int calcolaLung(String s) {
        try {
            return 1 + calcolaLung(restoStr(s));
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    private static String restoStr(String s) {
        return s.substring(1);
    }

    private static String concatena(String s, String a) {
        return s + a;
    }

    private static boolean uguali(String s, String a) {
        return s.equals(a);
    }

    private static char primoChar(String s) {
        return s.charAt(0);
    }
}