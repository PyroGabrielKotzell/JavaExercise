import java.util.InputMismatchException;
import java.util.Scanner;

import static zuclib.GraficaSemplice.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;
        while (true) {
            System.out.print("Griglia m x n,");
            try {
                System.out.print(" mi dia n.\n>");
                n = sc.nextInt();
                sc = new Scanner(System.in);
                System.out.print("\nmi dia m.\n>");
                m = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Numero non valido.");
            }
        }
        if (n > 50) {
            setGrossezza(0.0015);
            setFinestra(950, 950,"Applicazione Grafica Semplice Zuccante");
        } else setGrossezza(0.015);
        double xsp = (double) 1 / n, ysp = (double) 1 / m;
        for (int i = 0; i < n - 1; i++) {
            linea((0 + (xsp * (i + 1))), 1, (0 + (xsp * (i + 1))), 0);
        }
        for (int i = 0; i < m - 1; i++) {
            linea(0, (0 + (ysp * (i + 1))), 1, (0 + (xsp * (i + 1))));
        }
    }
}