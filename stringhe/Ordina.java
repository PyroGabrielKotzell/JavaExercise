import java.io.*;

class Ordina
{
   private static void stampa_scambi() {
      System.out.println("scambio terminato " + scambi);
   }

   private static int scambi = 0;
   
   private static void leggi(FileInputStream is, String[] parole) throws IOException
     {
	int i = 0; /* prima posizione dell'array */

/* aggiungere caratteri se necessario */	
	String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
        boolean fine = true; /* NB: il valore iniziale NON e` irrilevante */

	int c = is.read (); /* carattere che leggo */
	while (c != -1)
	  {
	     if (caratteri.indexOf(c) != -1) /* ritorna la posizione del carattere */
	       {
                  if (fine) parole[i] = "";
		  parole[i] = parole[i].concat(Character.toString((char)c));
		  fine = false;
	       }
	     else
	       {
		  if (!fine)
		    {
		       i++;
		       fine = true;
		    }
	       }
	     c = is.read (); /* leggo il prossimo carattere */
	  }
     }

   private static void scrivi(PrintStream os, String[] parole)   
     {
	String parola = "";
	
	for (int i=0; i < parole.length; i++) {
	     if (!parole[i].equals(parola)) { /* parole[i] != parola */
		os.println(parole[i]);
		parola = parole[i];
	     }
	}
     }

   private static void scambia(int i, int j, String[] parole)
     {
	String c;
	
	scambi++;
	c = parole[i]; parole[i] = parole[j]; parole[j] = c;
/* oppure: c = parole[j]; parole[j] = parole[i]; parole[i] = c; */
     }

   /* Fa una passata e ritorna il numero di scambi fatti */   
   private static int bubble_sort_1(String[] parole)
     {
	int scambi = 0; /* all'inizio gli scambi fatti sono 0 */
	
	String pp = "";
	
	for(int i = 0; i < parole.length-1; i++)
	    if (parole[i].compareTo(parole[i+1])>0) /* parole[i] > parole[i+1] */
	    {
	       scambia(i, i+1, parole);
	       scambi++; /* conto uno scambio quando lo faccio */
	    };
	return scambi;
     }

private static void bubble_sort(String[] parole)
     {
        while (bubble_sort_1(parole)>0);
/* OPPURE
        for(int i = 0; i < parole.length; i++) {
	  int k = bubble_sort_1(parole);
	  if(k==0) break;
	}
*/ 
     }

   private static int partizione(String[] parole, int inizio, int fine) {
     int i = inizio, j = fine;
     while (i < j) {
	int confronta = parole[i].compareTo(parole[i+1]);
	if (confronta>0) { /* parole[i] > parole[i+1] */
	   scambia(i+1, i, parole); i++;
	}
	else if (confronta==0) { /* parole[i] == parole[i+1] */
           i++;
	}
	else { /* parole[i] < parole[i+1] */
	   scambia(i+1, j, parole); j--;
	}
     }      
     return i;
   }

   private static void quick_sort(String[] parole, int inizio, int fine) {
      if (inizio < fine) {
	int i = partizione(parole, inizio, fine);
	quick_sort(parole, inizio, i-1);
	quick_sort(parole, i+1, fine);
      }
/* OPPURE
      while (inizio < fine) {
	int i = partizione(parole, inizio, fine);
	quick_sort(parole, inizio, i-1);
	inizio = i+1;
      }
*/
   }

/* DEVO indicare le ecezioni laciate dall'interno del metodo main */
   public static void main(String[] args) throws IOException
     {
	int lunghezza = /* 184360 */ 125 ;
	String[] parole = new String[lunghezza];
	
/* is e` il flusso in lettura ricavato dal file il cui nome e` in args[0] */	
        FileInputStream is = new FileInputStream(args[0]);

	leggi(is, parole);

/* ordina */
/*
	bubble_sort(parole);
*/
	quick_sort(parole, 0, lunghezza-1);
	
        stampa_scambi();
	
/* os e` il flusso in scrittura ricavato dal file il cui nome e` in args[1] */
	PrintStream os = new PrintStream(args[1]);
	
	scrivi(os, parole);
     }
}
