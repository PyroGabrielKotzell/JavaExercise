package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Statistica> stats = new ArrayList<>();
        int i = 0;
        while (true) {
            System.out.print("""
                    Menu:
                    -Crea Statistica
                    -Rimuovi Statistica
                    -Modifica Statistica
                    -Quit
                    >""");
            String s = sc.nextLine();
            sc = new Scanner(System.in);
            if (s.equalsIgnoreCase("quit")) break;
            else if (s.equalsIgnoreCase("crea statistica")) {
                System.out.print("Quanto grande vuoi la Statistica?\n>");
                stats.add(i, new Statistica(getint(sc)));
                System.out.println("Statistica salvata alla posizione: " + i);
                i++;
            } else if (s.equalsIgnoreCase("rimuovi statistica")) {
                int a = stats.size() - 1;
                if (a < 0) System.out.println("Non ci sono Statistiche salvate");
                else {
                    System.out.print("Seleziona Statistica: <Index: 0 - " + a + ">\n>");
                    stats.remove(getint(sc));
                }
            } else if (s.equalsIgnoreCase("modifica statistica")) {
                int a = stats.size() - 1;
                if (a < 0) System.out.println("Non ci sono Statistiche salvate");
                else {
                    System.out.print("Seleziona Statistica: <Index: 0 - " + a + ">\n>");
                    int b = getint(sc);
                    stats.set(b, modify(stats.get(b), sc));
                }
            }
            sc = new Scanner(System.in);
            System.out.println();
        }
    }

    private static Statistica modify(Statistica statistica, Scanner sc) {
        while (true) {
            System.out.print("""
                    Menu:
                    -Crea Dato
                    -Visualizza Dato
                    -Salva in File
                    -Carica File
                    -Quit
                    >""");
            String s = sc.nextLine();
            sc = new Scanner(System.in);
            if (s.equalsIgnoreCase("quit")) return statistica;
            else if (s.equalsIgnoreCase("crea dato")) {
                System.out.print("Mi dia il nome\n>");
                String nome = sc.nextLine();
                sc = new Scanner(System.in);
                System.out.print("Mi dia il cognome\n>");
                String cognome = sc.nextLine();
                sc = new Scanner(System.in);
                System.out.print("Mi dia l'altezza\n>");
                float altezza;
                while (true) {
                    try {
                        altezza = sc.nextFloat();
                        sc = new Scanner(System.in);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("NAN");
                        sc = new Scanner(System.in);
                    }
                }
                System.out.print("Salvando il dato...");
                statistica.addDato(cognome, nome, altezza);
                System.out.println("\nDato salvato");
            } else if (s.equalsIgnoreCase("visualizza dato")) {
                if (statistica.length() - 1 < 0) System.out.println("Questa Statistica è vuota!");
                else {
                    System.out.print("Seleziona Dato: <Index: 0 - " + (statistica.length() - 1) + ">\n>");
                    try {
                        System.out.println(statistica.getDato(getint(sc)).toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Dato non esistente");
                    }
                }
            }else if (s.equalsIgnoreCase("salva in file")){
                System.out.print("Mi dia il nome del file");
                statistica.save(sc.nextLine());
                sc = new Scanner(System.in);
            }else if (s.equalsIgnoreCase("carica file")){
                System.out.print("Mi dia il nome del file");
                statistica.load(sc.nextLine());
                sc = new Scanner(System.in);
            }
        }
    }

    private static int getint(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("NAN");
                sc = new Scanner(System.in);
            }
        }
    }
}
