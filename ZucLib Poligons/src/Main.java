import java.awt.*;
import zuclib.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import static zuclib.GraficaSemplice.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Poligono[] arrayP = new Poligono[4];
        Color[] colori = {ROSSO, VERDE, GIALLO, ARANCIONE};
        double lunghezza;
        System.out.println("Disegnamo 4 poligoni!");
        for (int i = 0; i < 4; i++) {
            System.out.print("Poligono num. " + (i+1));
            while (true)
                try{
                    System.out.print("mi dia la lunghezza\n>");
                    lunghezza = sc.nextDouble();
                    sc = new Scanner(System.in);
                    System.out.print("mi dia il numero di lati\n>");
                    int lati = sc.nextInt();
                    sc = new Scanner(System.in);
                    arrayP[i] = new Poligono(lunghezza, lati);
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Poligono non valido");
                    sc = new Scanner(System.in);
                }
            double[] quadrante = new double[2];
            if (i == 0) {
                quadrante[0] = 0.25 - ((lunghezza / 2) / 10);
            }
            Tartaruga t = new Tartaruga(quadrante[0], quadrante[1], 90, colori[i]);
            arrayP[i].disegna(t);
        }
    }
}