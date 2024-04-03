package com.company;

public class Main {

    public static void main(String[] args) {
        // numero dati e consumatori
        int len = 1000;
        Consumer[] threads = new Consumer[len];

        // crea il buffer e rendi la grandezza massima di esso 100
        Buffer.instance().setMaxSize(100);

        // massimo numero che il dato creato dal produttore può avere
        int max = 31;
        Productor p = new Productor("Pino", len, max);

        // creo i thread
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Consumer("Mariozzo " + (i + 1));
        }

        // li faccio partire
        p.start();
        for (Consumer c : threads) {
            c.start();
        }

        // mi metto ad aspettare il loro termine
        try {
            p.join();
            for (Consumer t : threads) t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // scrivo i dati rimasti (se ce ne sono) su schermo
        System.out.println();
        System.out.println("data left: " + Buffer.instance().getDatas() + "\n");

        // scrivo il nome dei thread e i loro rispettivi dati che hanno preso dal buffer
        // lunghezza massima di una stringa
        int sLen = (len + "").length() + (max + "").length() + 12;

        // numero colonne
        int n = 50;

        int j = 0;
        for (int i = 0; i < threads.length; i++) {

            // if per il cambio di riga
            if (j > threads.length-1) {
                j++;
                j = j % threads.length;
                System.out.println();
            }

            // butto fuori la stringa in modo ordinato
            String s = threads[j].getName() + "=" + threads[j].getData() + ", ";
            while (s.length() < sLen) s += " ";
            System.out.print(s);
            j += n;
        }
    }
}