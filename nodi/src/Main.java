import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        GestoreListe g = new GestoreListe();
        while (true){
            System.out.println("1:<- 2:-> 3:mod val 4:rim nodo 5:crea nodo 6:nuova lista 7:esci");
            System.out.print("Nodo: " + g.getNumNodo() + " valore: " + g.getData() + "\n>");
            char s = sc.nextLine().charAt(0);
            sc = new Scanner(System.in);
            switch (s){
                case '1'-> g.aCapo();
                case '2'-> g.avanti();
                case '3'->{
                    g.cambiaValore(getInt());
                    sc = new Scanner(System.in);
                }
                case '4'-> g.rimuoviNodo();
                case '5'-> {
                    g.creaNodo(getInt());
                    sc = new Scanner(System.in);
                }
                case '6'->{
                    //g.salvaLista();
                    g = new GestoreListe();
                }
                case '7'-> System.exit(0);
            }
        }
    }

    public static int getInt(){
        while (true){
            System.out.print("Dammi un numero\n>");
            try{
                return sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("NAN");
                sc = new Scanner(System.in);
            }
        }
    }
}