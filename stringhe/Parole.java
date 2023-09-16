import java.io.*;

class Parole
{
/* DEVO indicare le ecezioni laciate dall'interno del metodo main */
   public static void main(String[] args) throws IOException
     {
	String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
/* is e` il flusso in lettura ricavato dal file il cui nome e` in args[0] */	
        FileInputStream is = new FileInputStream(args[0]);

/* os e` il flusso in scrittura ricavato dal file il cui nome e` in args[1] */
	PrintStream os = new PrintStream(args[1]);

	boolean fine = true; /* NB: il valore iniziale NON e` irrilevante */

	int parole = 0;
	
	int c = is.read (); /* carattere che leggo */
	while (c != -1)
	  {
	     if (caratteri.indexOf(c) != -1) /* ritorna la posizione del carattere */
	       {
		  os.write(c); /* os = System.out */
		  fine = false;
	       }
	     else
	       {
		  if (fine) 
		    {
		    }
		  else
		    {
		  os.println(); /* oppure os.print(" "); */
		  fine = true;
		  parole++;
		    }
	       }
	     c = is.read (); /* leggo il prossimo carattere */
	  }
	if (!fine) /* non sono alla fine */ 
	  {
	     os.println();
	     parole++;
	  };
	
        System.out.println(parole);
     }
}
