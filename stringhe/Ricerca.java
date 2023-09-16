import java.io.*;

class Ricerca
{
   private static String[] leggi(String nome) throws IOException
   {
        Leggi leggi = new Leggi(nome);

        int lunghezza = Integer.valueOf(leggi.parola(leggi.cifre));

	String[] parole = new String[lunghezza];

	int i = 0; /* prima posizione dell'array */

	String parola = leggi.parola(leggi.lettere);
      
        while (!parola.equals("") && i < lunghezza) {      
	   parole[i] = parola; i++;
	   parola = leggi.parola(leggi.lettere);
	}
      return parole;
   }
   
   private static boolean ricerca_lineare(String[] parole, String parola) { 
      int i = 0;
      boolean trovato = false;
      while (!trovato && i < parole.length) {
         trovato = parole[i].equals(parola);
/* OPPURE
	 if (parole[i].equals(parola)) {
	    trovato = true;
	 }
*/
	 confronti++;
	 i++;
      }
      return trovato;
   }
   
   private static boolean ricerca_binaria(String[] parole, String parola) {
      int inizio = 0;
      int fine = parole.length-1;
      boolean trovato = false;
      
      while (!trovato && inizio <= fine) {
	 int i = (inizio + fine)/2;
	 int confronta = parole[i].compareTo(parola);
	 confronti++;
	 if (confronta>0) { /* parole[i] > parola */
	    fine = i-1;
	 }
	 else if (confronta==0) { /* parole[i] == parola */
            trovato=true;
	 } else { /* parole[i] < parola */
	    inizio=i+1;
	 }
      }
      return trovato;
   }

   private static RisultatoRicerca ricerca_binaria_if(String[] parole, String prefisso) {
      RisultatoRicerca r = new RisultatoRicerca(0, parole.length-1);
      boolean trovato = false;
      
      while (!trovato && r.inizio <= r.fine) {
	 int i = (r.inizio + r.fine)/2;
	 int confronta = parole[i].compareTo(prefisso);
	 confronti++;
	 if (confronta>0) { /* parole[i] > prefisso */
	    r.fine = i-1;
	 }
	 else if (confronta==0) { /* parole[i] == prefisso */
            r.inizio=i;
	    trovato=true;
	 } else { /* parole[i] < prefisso */
	    r.inizio=i+1;
	 }
      }
      return r;
   }

   private static void stampa(String[] parole, int inizio, int fine) {
      for (int i=inizio; i <= fine; i++)
	   System.out.println(parole[i]);
   }

   private static int confronti = 0;
   
   public static void main(String[] args) throws IOException
     {	
	String[] parole = leggi(args[0]);
/*
	boolean risultato = ricerca_lineare(parole, args[1]);

	System.out.println("risultato: "+risultato);
*/
        RisultatoRicerca r = ricerca_binaria_if(parole, args[1]);
        RisultatoRicerca s = ricerca_binaria_if(parole, args[1]+"~"); /* ~ sta dopo tutti i simboli sulla tastiera */
/*	
	System.out.println("inizio: "+r.inizio);
	System.out.println("fine: "+s.fine);
*/
	stampa(parole, r.inizio, s.fine);
	System.out.println("confronti: "+confronti);
     }
 }
