import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] A, B, C;
        while(true) {
            try {
                System.out.print("dammi lunghezza S1: ");
                int aL = sc.nextInt();
                sc = new Scanner(System.in);
                System.out.print("\ndammi lunghezza array S2: ");
                int bL = sc.nextInt();
                A = new int[aL];
                B = new int[bL];
                sc = new Scanner(System.in);
                for (int i = 0; i < aL; i++) {
                    System.out.println("inserisci numero S1 pos: "+(i+1));
                    while (true) {
                        try {
                            A[i] = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("\nNON UN NUMERO");
                            sc = new Scanner(System.in);
                        }
                    }
                }
                for (int i = 0; i < bL; i++) {
                    System.out.println("inserisci numero S2 pos: "+(i+1));
                    while (true) {
                        try {
                            B[i] = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("\nNON UN NUMERO");
                            sc = new Scanner(System.in);
                        }
                    }
                }
                C = new int[Math.max(A.length, B.length)];
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nNON UN NUMERO");
                sc = new Scanner(System.in);
            }
        }
        System.out.println("somme:");
        for (int i = 0; i < Math.max(A.length,B.length); i++) {
            try {
                C[i] = A[i] + B[i];
            }catch (IndexOutOfBoundsException e){}
            System.out.println(A[i]+ " + "+B[i]+" = "+C[i]);
        }
    }
}