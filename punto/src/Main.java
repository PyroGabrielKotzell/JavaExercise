import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        List<Punto> Listapunti = new ArrayList<>();
        int NPunto = 0;
        while (!s.equals("q")){
            System.out.print("\n1-crea Punto, 2-stampa Punto, 3-stampa Quadrante, 4-crea Somma, 5-calcola Distanza\n6-crea Opposto, 7-Rimuovi Punto q-esci\n>");
            s = sc.nextLine();
            System.out.println();
            sc = new Scanner(System.in);
            if (s.equals("1")){
                System.out.println("Mi dia la X e la Y");
                float x, y;
                while (true) {
                    try {
                        x = sc.nextFloat();
                        y = sc.nextFloat();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("non valido");
                    }
                }
                Listapunti.add(NPunto, new Punto(x, y));
                sc = new Scanner(System.in);
                System.out.println("Punto salvato all'indice: " + (++NPunto));
            } else if (s.equals("2")) {
                System.out.print("Dichiara numero Punto: da 1 a " + Listapunti.size());
                try {
                    Listapunti.get(sc.nextInt() - 1).StampaPunto();
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("Punto non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (s.equals("3")) {
                System.out.print("Dichiara numero Punto: da 1 a " + Listapunti.size());
                try {
                    System.out.println(Listapunti.get(sc.nextInt() - 1).Quadrante());
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("Punto non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (s.equals("4")) {
                System.out.print("Dichiara due Punti: da 1 a " + Listapunti.size());
                try {
                    Listapunti.add(NPunto, Listapunti.get(sc.nextInt() - 1).SommaPunto(Listapunti.get(sc.nextInt() - 1)));
                    System.out.println("Punto salvato all'indice: " + (++NPunto));
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("Punto non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (s.equals("5")) {
                System.out.print("Dichiara due Punti: da 1 a " + Listapunti.size());
                try {
                    System.out.println("Distanza: " + Listapunti.get(sc.nextInt() - 1).Distanza(Listapunti.get(sc.nextInt() - 1)));
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("Punto non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (s.equals("6")) {
                System.out.print("Dichiara un Punto: da 1 a " + Listapunti.size());
                try {
                    Listapunti.add(NPunto, Listapunti.get(sc.nextInt() - 1).Opposto());
                    System.out.println("Punto salvato all'indice: " + (++NPunto));
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("Punto non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (s.equals("7")) {
                System.out.print("Dichiara un Punto: da 1 a " + Listapunti.size());
                try {
                    Listapunti.remove((sc.nextInt() - 1));
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("Punto non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (!s.equals("q")) {
                System.out.println("Comando non valido");
                System.out.println();
            }
        }
    }
}