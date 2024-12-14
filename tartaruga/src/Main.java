import java.util.InputMismatchException;
import java.util.Scanner;

import zuclib.*;

import static zuclib.GraficaSemplice.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 3;
        setFinestra(950, 950, "Disegni go brrrr");
        //GraficaSemplice.frame.setResizable(true);
        double grand = 0.5;
        boolean exit = false;
        double fgrand = grand;
        Tartaruga t = new Tartaruga(0.5 - grand / 2, 0.5 - grand / 2, 0, NERO);
        while (!exit) {
            System.out.print("""
                    1-set N
                    2-set grand
                    3-reset grand
                    4-clear screen
                    5-quadrato iterativo
                    6-quadrato ricorsivo
                    7-triangolo iterativo
                    8-triangolo ricorsivo
                    9-spirale iterativo
                    10-spirale ricorsivo
                    11-spirale cerchi
                    12-spirale cerchi pieni
                    13-curva di Von Koch
                    14-fiocco di Koch
                    15-triangolo Sierpinski
                    16-ramificazioni
                    17-ramificazioni colorate
                    q-exit
                    >""");
            String s = sc.nextLine();
            sc = new Scanner(System.in);
            switch (s) {
                case "1" -> {
                    while (true) {
                        System.out.print("mi dia N\n>");
                        try {
                            n = sc.nextInt();
                            sc = new Scanner(System.in);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Numero non valido");
                            sc = new Scanner(System.in);
                        }
                    }
                } // set N
                case "2" -> {
                    while (true) {
                        System.out.print("mi dia la grandezza\n>");
                        try {
                            grand = sc.nextDouble();
                            fgrand = grand;
                            sc = new Scanner(System.in);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Numero non valido");
                            sc = new Scanner(System.in);
                        }
                    }
                } // set grand
                case "3" -> fgrand = 0.5; // reset grand
                case "4" -> setFinestra(950, 950, "Disegni go brrrr"); // clear screen
                case "5" -> QuadratoIterativo(grand, t, n); // q iterativo
                case "6" -> QuadratoRicorsivo(grand, t, n); // q ricorsivo
                case "7" -> TriangoloIterativo(grand, t, n); // t iterativo
                case "8" -> TriangoloRicorsivo(grand, t, n); // t ricorsivo
                case "9" -> SpiraleIterativo(grand, t, n); // s iterativo
                case "10" -> {
                    t.destra(270);
                    SpiraleRicorsivo(grand, t, n);
                } // s ricorsivo
                case "11" -> {
                    t = new Tartaruga(0.5, 0.2, 0, NERO);
                    spiralecerchiricorsivo(grand, t, n);
                } // s cerchi ricorsivo
                case "12" -> {
                    t = new Tartaruga(0.5, 0.2, 0, NERO);
                    spiralecerchipieniricorsivo(grand, t, n);
                } // s cerchi pieni ricorsivo
                case "13" -> {
                    t = new Tartaruga(0, 0.5, 0, NERO);
                    grand = 1;
                    CurvadiKoch(grand, t, n);
                } // curva koch
                case "14" -> {
                    t = new Tartaruga(0.5 - grand / 2, 0.75, 0, NERO);
                    FioccodiKoch(grand, t, n);
                } // fiocco koch
                case "15" -> TriangoloSierpinski(grand/2, t, n); // triangolo sier
                case "16" -> {
                    t = new Tartaruga(0.5, 0.5 - grand / 2, 0, NERO);
                    t.sinistra(90);
                    rami(grand, t, n);
                } // rami
                case "17" -> {
                    t = new Tartaruga(0.5, 0.5 - grand / 2, 0, VERDE);
                    t.sinistra(90);
                    double g = 0.015;
                    ramic(grand/2, t, n, g);
                } // rami colorati
                case "q" -> exit = true;
            }
            grand = fgrand;
            t = new Tartaruga(0.5 - grand / 2, 0.5 - grand / 2, 0, NERO);
        }
    }

    private static void ramic(double grand, Tartaruga t, int n, double g) {
        if (g < 0.005) t.setColore(ROSSO);
        else t.setColore(VERDE);
        t.setGrossezza(g);
        if (n <= 1) {
            t.avanti(grand);
            t.indietro(grand);
        } else {
            t.avanti(grand);
            t.sinistra(30);
            ramic(grand / 2, t, n - 1, (g * 0.5));
            t.destra(30);
            ramic(grand / 2, t, n - 1, (g * 0.5));
            t.destra(30);
            ramic(grand / 2, t, n - 1, (g * 0.5));
            t.sinistra(30);
            t.pennaSu();
            t.indietro(grand);
            t.pennaGiu();
        }
    }

    private static void rami(double grand, Tartaruga t, int n) {
        if (n <= 1) {
            t.avanti(grand);
            t.indietro(grand);
        } else {
            t.avanti(grand);
            t.sinistra(30);
            rami(grand / 2, t, n - 1);
            t.destra(30);
            rami(grand / 2, t, n - 1);
            t.destra(30);
            rami(grand / 2, t, n - 1);
            t.sinistra(30);
            t.pennaSu();
            t.indietro(grand);
            t.pennaGiu();
        }
    }

    private static void TriangoloSierpinski(double grand, Tartaruga t, int n) {
        if (n <= 0) {
            TriangoloIterativo(grand, t, 1);
        } else {
            TriangoloSierpinski(grand / 2, t, n - 1);
            t.avanti(grand);
            TriangoloSierpinski(grand / 2, t, n - 1);
            t.indietro(grand);
            t.sinistra(60);
            t.avanti(grand);
            t.destra(60);
            TriangoloSierpinski(grand / 2, t, n - 1);
            t.sinistra(60);
            t.indietro(grand);
            t.destra(60);
        }
    }

    private static void FioccodiKoch(double grand, Tartaruga t, int n) {
        if (n <= 0) {
        } else {
            CurvadiKoch(grand, t, n - 1);
            t.destra(120);
            CurvadiKoch(grand, t, n - 1);
            t.destra(120);
            CurvadiKoch(grand, t, n - 1);

        }
    }

    private static void CurvadiKoch(double grand, Tartaruga t, int n) {
        if (n <= 0) {
            t.avanti(grand);
        } else {
            CurvadiKoch(grand / 3, t, n - 1);
            t.sinistra(60);
            CurvadiKoch(grand / 3, t, n - 1);
            t.destra(120);
            CurvadiKoch(grand / 3, t, n - 1);
            t.sinistra(60);
            CurvadiKoch(grand / 3, t, n - 1);
        }
    }

    private static void spiralecerchipieniricorsivo(double grand, Tartaruga t, int n) {
        if (n == 0) {
        } else {
            cerchio(t.getX(), t.getY(), grand);
            cerchioPieno(t.getX(), t.getY(), grand, coloreACaso());
            t.pennaSu();
            t.sinistra(25);
            t.avanti(grand + grand * 0.8);
            t.pennaGiu();
            spiralecerchipieniricorsivo(grand * 0.8, t, n - 1);
        }
    }

    private static void spiralecerchiricorsivo(double grand, Tartaruga t, int n) {
        if (n == 0) {
        } else {
            cerchio(t.getX(), t.getY(), grand);
            t.pennaSu();
            t.sinistra(25);
            t.avanti(grand + grand * 0.8);
            t.pennaGiu();
            spiralecerchiricorsivo(grand * 0.8, t, n - 1);
        }
    }

    private static void SpiraleRicorsivo(double grand, Tartaruga t, int n) {
        if (n == 0) {
        } else {
            t.avanti(grand);
            t.destra(90);
            SpiraleRicorsivo(grand * 0.8, t, n - 1);
        }
    }

    private static void SpiraleIterativo(double grand, Tartaruga t, int n) {
        t.destra(270);
        for (int j = 0; j < n; j++) {
            t.avanti(grand);
            t.destra(90);
            grand = grand * 0.8;
        }
    }

    private static void TriangoloRicorsivo(double grand, Tartaruga t, int n) {
        if (n == 0) {
        } else {
            for (int j = 0; j < 3; j++) {
                t.avanti(grand);
                t.destra(240);
            }
            grand = grand / 2;
            t.avanti(grand);
            t.destra(300);
            TriangoloRicorsivo(grand, t, n - 1);
        }
    }

    private static void TriangoloIterativo(double grand, Tartaruga t, int n) {
        for (int j = 0; j < 3; j++) {
            t.avanti(grand);
            t.sinistra(120);
        }
        for (int i = 0; i < n - 1; i++) {
            grand = grand / 2;
            t.avanti(grand);
            t.sinistra(60);
            for (int j = 0; j < 3; j++) {
                t.avanti(grand);
                t.sinistra(120);
            }
        }
    }

    private static void QuadratoRicorsivo(double grand, Tartaruga t, int n) {
        if (n == 0) {
        } else {
            for (int j = 0; j < 4; j++) {
                t.avanti(grand);
                t.destra(270);
            }
            grand = grand / 2;
            t.avanti(grand);
            t.destra(315);
            QuadratoRicorsivo(Math.sqrt(2 * (grand * grand)), t, n - 1);
        }
    }

    private static void QuadratoIterativo(double grand, Tartaruga t, int n) {
        for (int j = 0; j < 4; j++) {
            t.avanti(grand);
            t.destra(270);
        }
        for (int i = 0; i < n - 1; i++) {
            grand = grand / 2;
            t.avanti(grand);
            t.destra(315);
            grand = Math.sqrt(2 * (grand * grand));
            for (int j = 0; j < 4; j++) {
                t.avanti(grand);
                t.destra(270);
            }
        }
    }
}