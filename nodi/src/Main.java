import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestoreListe gestore = new GestoreListe();
        while (true) {
            // inizio
            System.out.println("\n1:<- 2:-> 3:modifica val 4:rimuovi nodo 5:crea nodo 6:cerca val 7:nuova lista 8:esci");
            // scrivo il nodo corrente
            System.out.print("Nodo: " + gestore.getNumNodo() + " valore: " + gestore.getData() + "\n>");
            char scelta = sc.nextLine().charAt(0);
            sc = new Scanner(System.in);
            switch (scelta) {
                // torno indietro
                case '1' -> gestore.aCapo();
                // vado avanti
                case '2' -> gestore.avanti();
                // cambio valore del nodo corrente
                case '3' -> {
                    gestore.cambiaValore(getInt());
                    sc = new Scanner(System.in);
                }
                // rimuovo per valore o numero del nodo
                case '4' -> {
                    if (gestore.getLenght() != 1) {
                        System.out.print("1:per numero 2:per valore\n>");
                        scelta = sc.nextLine().charAt(0);
                        sc = new Scanner(System.in);
                        // prendo il valore o il numero
                        int i = getInt();
                        sc = new Scanner(System.in);
                        switch (scelta) {
                            case '1' -> gestore.rimuoviNodoNum(i);
                            case '2' -> gestore.rimuoviNodoVal(i);
                        }
                        // non posso rimuovere il nodo testa
                    } else System.out.println("--Non posso rimuovere il nodo--");
                }
                // creo un nodo
                case '5' -> {
                    gestore.creaNodo(getInt());
                    sc = new Scanner(System.in);
                }
                // cerco per valore o numero del nodo
                case '6' -> {
                    System.out.print("1:per numero 2:per valore\n>");
                    scelta = sc.nextLine().charAt(0);
                    sc = new Scanner(System.in);
                    // prendo il valore o il numero
                    int i = getInt();
                    sc = new Scanner(System.in);
                    switch (scelta) {
                        case '1' -> gestore.getValNodoNum(i);
                        case '2' -> gestore.getNumNodoVal(i);
                    }
                }
                // resetta la lista
                case '7' -> gestore = new GestoreListe();
                // esco
                case '8' -> System.exit(0);
            }
        }
    }

    // per ottenere interi senza problemi
    public static int getInt() {
        while (true) {
            System.out.print("Dammi un numero\n>");
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("NAN");
                sc = new Scanner(System.in);
            }
        }
    }
}