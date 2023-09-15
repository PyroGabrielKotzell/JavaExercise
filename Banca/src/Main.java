import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cliente> clienti = new ArrayList<>();
        String s;
        Scanner sc = new Scanner(System.in);
        while (true){
            // menu
            System.out.print("""
                    \nMenu:
                    nuovo cliente
                    rimuovi cliente
                    guarda cliente
                    esci
                    \\>""");
            // prendo input
            s = sc.nextLine();
            sc = new Scanner(System.in);
            // inizio menu
            if (s.equalsIgnoreCase("nuovo cliente")){
                // inizio nuovo cliente
                System.out.print("nome: \n\\>");
                // prendo nome
                String nome = sc.nextLine();
                sc = new Scanner(System.in);

                System.out.print("cognome: \n\\>");
                // prendo cognome
                String conome = sc.nextLine();
                sc = new Scanner(System.in);

                System.out.print("codice fiscale: \n\\>");
                // prendo codice fiscale
                String codiceF = sc.nextLine();
                sc = new Scanner(System.in);
                // creo cliente
                Cliente tmp = new Cliente(nome, conome, codiceF);
                // aggiungo cliente
                clienti.add(tmp);
                // fine nuovo cliente
            } else if (s.equalsIgnoreCase("rimuovi cliente")){
                // inizio rimuovi cliente
                System.out.print("Scegli cliente tra 0 a " + (clienti.size()-1) + "\n\\>");
                // chiedo numero cliente
                int n = getint(sc);
                sc = new Scanner(System.in);
                // provo a cancellarlo
                try {
                    clienti.remove(n);
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Cliente non esiste");
                }
                // fine rimuovi cliente
            }else if (s.equalsIgnoreCase("guarda cliente")){
                // inizio guarda cliente
                System.out.print("Scegli cliente tra 0 a " + (clienti.size()-1) + "\n\\>");
                // chiedo cliente
                int n = getint(sc);
                sc = new Scanner(System.in);
                // se non esiste
                if (n < 0 || n > clienti.size()-1){
                    System.out.println("Cliente non esiste");
                }else {
                    // se esiste lo copio
                    Cliente c = clienti.get(n);
                    while (true) {
                        // menu
                        System.out.print("""
                                \nMenu:
                                nome
                                cognome
                                codice fiscale
                                aggiungi transazione
                                rimuovi transazione
                                calcola bilancio
                                lista movimenti
                                ottieni istogramma
                                esci
                                \\>""");
                        // prendo input
                        s = sc.nextLine();
                        sc = new Scanner(System.in);
                        // inizio menu
                        if (s.equalsIgnoreCase("nome")){
                            // ritorno nome
                            System.out.println("nome: " + c.getNome());
                        }else if (s.equalsIgnoreCase("cognome")){
                            // ritorno cognome
                            System.out.println("cognome: " + c.getCognome());
                        }else if (s.equalsIgnoreCase("codice fiscale")){
                            // ritorno codice fiscale
                            System.out.println("codice fiscale: " + c.getCodiceFiscale());
                        }else if (s.equalsIgnoreCase("aggiungi transazione")){
                            // aggiungo movimento
                            System.out.print("anno: \n\\>");
                            // chiedo anno
                            int anno = getint(sc);
                            sc = new Scanner(System.in);

                            System.out.print("denaro: \n\\>");
                            // chiedo denaro
                            double denaro = getdouble(sc);
                            sc = new Scanner(System.in);

                            System.out.print("num cliente: \n\\>");
                            // chiedo cliente interessato
                            int m = getint(sc);
                            sc = new Scanner(System.in);
                            // controllo se esiste
                            if (m < 0 || m > clienti.size()-1) {
                                System.out.println("Cliente non esiste");
                            }else{
                                Trasferimento t = new Trasferimento(anno, denaro, clienti.get(m));
                                c.nuovoTrasferimento(t);
                            }
                            // fine aggiunta movimento
                        }else if (s.equalsIgnoreCase("rimuovi transazione")){
                            // inizio rimozione movimento
                            System.out.print("Scegli transazione tra 0 a " + (c.getListaMovimenti().size()-1) + "\n\\>");
                            // chiedo movimento
                            int m = getint(sc);
                            sc = new Scanner(System.in);
                            // provo a cancellare
                            try {
                                c.eliminaTrasferimento(m);
                            }catch (IndexOutOfBoundsException e){
                                System.out.println("Trasferimento non esiste");
                            }
                            // fine rimozione movimento
                        }else if (s.equalsIgnoreCase("calcola bilancio")){
                            // calcolo bilancio
                            System.out.println("bilancio: " + c.calcBilancio());
                            // fine calcolo bilancio
                        }else if (s.equalsIgnoreCase("lista movimenti")){
                            // lista movimenti
                            System.out.println("lista: \n" + c.getListaMovimenti());
                            // fine lista movimenti
                        }else if (s.equalsIgnoreCase("ottieni istogramma")){
                            // printo istogramma
                            c.printIstogramma();
                            // fine print
                        }else if (s.equalsIgnoreCase("esci")){
                            // salvo cliente ed esco
                            clienti.set(n, c);
                            break;
                        }
                    }
                }
                //fine guarda cliente
            }else if (s.equalsIgnoreCase("esci")) break; // esco
        }
    }

    private static int getint(Scanner sc) { // prendo int gestendo eccezioni
        while(true){
            try{
                return sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("NAN");
                sc = new Scanner(System.in);
            }
        }
    }
    private static double getdouble(Scanner sc) { // prendo double gestendo eccezioni
        while(true){
            try{
                return sc.nextDouble();
            }catch (InputMismatchException e){
                System.out.println("NAN");
                sc = new Scanner(System.in);
            }
        }
    }
}