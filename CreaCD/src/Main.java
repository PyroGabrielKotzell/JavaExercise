import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "", nome, autore, titolo, casa, proprietario;
        int numeroCD = 0;
        CD[] arrayCD;
        System.out.print("Quanti CD fare?\n>");
        while (true){
            try {
                arrayCD = new CD[sc.nextInt()];
                break;
            }catch (InputMismatchException e){
                System.out.println("non un numero");
                sc = new Scanner(System.in);
            }
        }
        while (!s.equals("q")){
            System.out.print("\n1-crea CD, 2-stampa CD, 3-stampa proprietario CD, 4-edita proprietario CD, q-esci\n>");
            s = sc.nextLine();
            System.out.println();
            sc = new Scanner(System.in);
            if (s.equals("1")){
                System.out.println("CD numero: " + (numeroCD+1));
                System.out.print("nome CD> ");
                nome = sc.nextLine();
                sc = new Scanner(System.in);
                System.out.print("autore CD> ");
                autore = sc.nextLine();
                sc = new Scanner(System.in);
                System.out.print("titolo CD> ");
                titolo = sc.nextLine();
                sc = new Scanner(System.in);
                System.out.print("casa CD> ");
                casa = sc.nextLine();
                sc = new Scanner(System.in);
                System.out.print("proprietario CD> ");
                proprietario = sc.nextLine();
                sc = new Scanner(System.in);
                arrayCD[numeroCD] = new CD(nome, autore, titolo, casa, proprietario);
                numeroCD++;
            } else if (s.equals("2")) {
                System.out.print("Dichiara numero CD: da 1 a" + arrayCD.length);
                try {
                    arrayCD[(sc.nextInt() - 1)].stampaCD();
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("CD non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (s.equals("3")) {
                System.out.print("Dichiara numero CD: da 1 a" + arrayCD.length);
                try {
                    System.out.println(arrayCD[(sc.nextInt() - 1)].getProprietario());
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("CD non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (s.equals("4")) {
                System.out.print("Dichiara numero CD: da 1 a" + arrayCD.length);
                try {
                    int cd = sc.nextInt();
                    System.out.print("Dichiara nuovo proprietario");
                    arrayCD[cd].setProprietario(sc.nextLine());
                    sc = new Scanner(System.in);
                }catch (InputMismatchException | IndexOutOfBoundsException e){
                    System.out.println("CD non valido o non presente");
                    sc = new Scanner(System.in);
                }
            } else if (!s.equals("q")) {
                System.out.println("Comando non valido");
                System.out.println();
            }
        }
    }
}