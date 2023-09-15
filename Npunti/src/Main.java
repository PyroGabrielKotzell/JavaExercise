import java.util.InputMismatchException;
import java.util.Scanner;

import static zuclib.GraficaSemplice.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            System.out.print("Quanti punti disegnare?\n>");
            try {
                n = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Numero non valido.");
            }
        }
        setGrossezza(0.015);
        if (n == -1) {
            while (true) {
                double X = Math.random(), Y = Math.random();
                if (Math.random() < 0.51) {
                    setColore(ROSSO);
                    punto(X, Y);
                } else {
                    setColore(VERDE);
                    punto(X, Y);
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                double X = Math.random(), Y = Math.random();
                if (Math.random() < 0.51) {
                    setColore(ROSSO);
                    punto(X, Y);
                } else {
                    setColore(VERDE);
                    punto(X, Y);
                }
            }
        }
    }
}