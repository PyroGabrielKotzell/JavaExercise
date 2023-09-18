import java.util.Objects;

public class GestoreListe {
    private ListElement testa = new ListElement(0, null), nodoCorrente = testa;

    // costruttore inutile
    GestoreListe() {
    }

    // torno a capo
    public void aCapo() {
        nodoCorrente = testa;
        System.out.println("--Torno a nodo 0--");
    }

    // vado avanti, se non c'è un nodo avanti gestisce l'errore
    public void avanti() {
        try {
            nodoCorrente = Objects.requireNonNull(nodoCorrente.getProssimo());
            System.out.println("--Vado avanti--");
        } catch (NullPointerException e) {
            System.out.println("--Non c'è un prossimo nodo--");
        }
    }

    // cambio valore del nodo corrente: 'nodoCorrente'
    public void cambiaValore(int valore) {
        nodoCorrente.setData(valore);
        System.out.println("--Valore cambiato a: " + valore + "--");
    }

    // cerco il nodo da rimuovere con il numero
    public void rimuoviNodoNum(int numero) {
        int j = 0;
        // scorro con un nodo nuovo
        ListElement nodoPerScorrere = new ListElement(0, testa);
        while (j != numero && nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            j++;
        }
        // controllo che l'abbia trovato
        if (j == numero) {
            if (numero == 0) {
                testa = testa.getProssimo();
            } else {
                getPrevNodo(nodoPerScorrere).setProssimo(nodoPerScorrere.getProssimo());
            }
            this.nodoCorrente = testa;
            System.out.println("--Nodo rimosso " + numero + "--");
        } else System.out.println("--Non ho trovato il nodo " + numero + "--");
    }

    // cerco con il valore
    public void rimuoviNodoVal(int valore) {
        int j = 0;
        // scorro con un nodo nuovo
        ListElement nodoPerScorrere = new ListElement(0, testa);
        while (nodoPerScorrere.getData() != valore && nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            j++;
        }
        // controllo che l'abbia trovato
        if (nodoPerScorrere.getData() == valore) {
            if (j == 0) {
                testa = testa.getProssimo();
            } else {
                getPrevNodo(nodoPerScorrere).setProssimo(nodoPerScorrere.getProssimo());
            }
            nodoCorrente = testa;
            System.out.println("--Nodo rimosso " + j + "--");
        } else System.out.println("--Non ho trovato il nodo con data " + valore + "--");
    }
    // ultimamente perché i metodi per la rimozione sono simili si può convergere la seconda parte
    // (l'effettiva rimozione) in un metodo privato a parte

    // creo un nodo subito dopo quello corrente
    public void creaNodo(int valore) {
        nodoCorrente.setProssimo(new ListElement(valore, nodoCorrente.getProssimo()));
        System.out.println("--Nodo creato " + (getNumNodo() + 1) + "--");
    }

    // ottengo la lunghezza della lista
    public int getLenght() {
        int i = 0;
        ListElement nodoPerScorrere = new ListElement(0, testa);
        while (nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            i++;
        }
        return i;
    }

    // prendo il numero del nodo corrente
    public int getNumNodo() {
        int i = 0;
        ListElement nodoPerScorrere = new ListElement(0, testa);
        while (!nodoPerScorrere.getProssimo().equals(nodoCorrente)) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            i++;
        }
        return i;
    }

    // prendo e ritorno il nodo precedente a quello specificato
    private ListElement getPrevNodo(ListElement nodoSpecifico) {
        ListElement nodoPerScorrere = new ListElement(0, testa);
        while (!nodoPerScorrere.getProssimo().equals(nodoSpecifico)) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
        }
        return nodoPerScorrere;
    }

    // prendo la data del nodo corrente
    public int getData() {
        return nodoCorrente.getData();
    }

    // cerco con il numero del nodo e ne ottengo la data
    public void getValNodoNum(int numero) {
        int j = 0;
        ListElement nodoPerScorrere = new ListElement(0, testa);
        while (j != numero && nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            j++;
        }
        if (j == numero) {
            System.out.println("--Valore del nodo numero " + numero + " = " + nodoPerScorrere.getData());
        } else System.out.println("--Non ho trovato il nodo " + numero + "--");
    }

    // cerco con il valore del nodo e ne ottengo il numero
    public void getNumNodoVal(int valore) {
        int j = 0;
        ListElement nodoPerScorrere = new ListElement(0, testa);
        while (nodoPerScorrere.getData() != valore && nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            j++;
        }
        if (nodoPerScorrere.getData() == valore) {
            System.out.println("--Numero del nodo con valore " + nodoPerScorrere.getData() + " = " + j);
        } else System.out.println("--Non ho trovato il nodo con data " + valore + "--");
    }
}
