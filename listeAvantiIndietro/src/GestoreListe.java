import java.util.Objects;

public class GestoreListe<T extends Comparable<T>> {
    private ListElement<T> testa = new ListElement<>(null), nodoCorrente = testa;

    // costruttore inutile
    GestoreListe() {}

    // torno a capo
    public void indietro() {
        try {
            nodoCorrente = Objects.requireNonNull(nodoCorrente.getPrecedente());
            System.out.println("--Torno indietro--");
        } catch (NullPointerException e) {
            System.out.println("--Non c'è un nodo precedente--");
        }
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
    public void cambiaValore(T valore) {
        nodoCorrente.setData(valore);
        System.out.println("--Valore cambiato a: " + valore + "--");
    }

    // cerco il nodo da rimuovere con il numero
    public void rimuoviNodoNum(int numero) {
        int j = 0;
        // scorro con un nodo nuovo
        ListElement<T> nodoPerScorrere = new ListElement<>(null, testa, null);
        while (j != numero && nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            j++;
        }
        // controllo che l'abbia trovato
        if (j == numero) {
            if (numero == 0) {
                testa.getProssimo().setPrecedente(null);
                ListElement<T> tmp = testa.getProssimo();
                testa.setProssimo(null);
                testa = tmp;
            } else {
                nodoPerScorrere.getPrecedente().setProssimo(nodoPerScorrere.getProssimo());
                nodoPerScorrere.getProssimo().setPrecedente(nodoPerScorrere.getPrecedente());
                nodoPerScorrere.setPrecedente(null);
                nodoPerScorrere.setProssimo(null);
            }
            nodoCorrente = testa;
            System.out.println("--Nodo rimosso " + numero + "--");
        } else System.out.println("--Non ho trovato il nodo " + numero + "--");
    }

    // cerco con il valore
    public void rimuoviNodoVal(Object valore) {
        int j = 0;
        // scorro con un nodo nuovo
        ListElement<T> nodoPerScorrere = new ListElement<>(null, testa, null);
        while (!nodoPerScorrere.getData().equals(valore) && nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            j++;
        }
        // controllo che l'abbia trovato
        if (nodoPerScorrere.getData().equals(valore)) {
            if (j == 0) {
                testa.getProssimo().setPrecedente(null);
                ListElement<T> tmp = testa.getProssimo();
                testa.setProssimo(null);
                testa = tmp;
            } else {
                nodoPerScorrere.getPrecedente().setProssimo(nodoPerScorrere.getProssimo());
                nodoPerScorrere.getProssimo().setPrecedente(nodoPerScorrere.getPrecedente());
                nodoPerScorrere.setPrecedente(null);
                nodoPerScorrere.setProssimo(null);
            }
            nodoCorrente = testa;
            System.out.println("--Nodo rimosso " + j + "--");
        } else System.out.println("--Non ho trovato il nodo con data " + valore + "--");
    }
    // ultimamente perché i metodi per la rimozione sono simili si può convergere la seconda parte
    // (l'effettiva rimozione) in un metodo privato a parte


    // creo un nodo subito dopo quello corrente
    public void creaNodo(T valore) {
        nodoCorrente.setProssimo(new ListElement<T>(valore, nodoCorrente.getProssimo(), nodoCorrente));

        ListElement<T> nodoPerScorrere = new ListElement<>(null, testa, null);
        while (nodoPerScorrere.getProssimo() != null) {
            // crea per ordinare //
            nodoPerScorrere = nodoPerScorrere.getProssimo();
        }
        System.out.println("--Nodo creato " + (getNumNodo() + 1) + "--");
    }

    // ottengo la lunghezza della lista
    public int getLenght() {
        int i = 0;
        ListElement<T> nodoPerScorrere = new ListElement<>(null, testa, null);
        while (nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            i++;
        }
        return i;
    }

    // prendo il numero del nodo corrente
    public int getNumNodo() {
        int i = 0;
        ListElement<T> nodoPerScorrere = new ListElement<>(null, testa, null);
        while (!nodoPerScorrere.getProssimo().equals(nodoCorrente)) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            i++;
        }
        return i;
    }

    // cerco con il numero del nodo e ne ottengo la data
    public void getValNodoNum(int numero) {
        int j = 0;
        ListElement<T> nodoPerScorrere = new ListElement<>(null, testa, null);
        while (j != numero && nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            j++;
        }
        if (j == numero) {
            System.out.println("--Valore del nodo numero " + numero + " = " + nodoPerScorrere.getData());
        } else System.out.println("--Non ho trovato il nodo " + numero + "--");
    }

    // cerco con il valore del nodo e ne ottengo il numero
    public void getNumNodoVal(T valore) {
        int j = 0;
        ListElement<T> nodoPerScorrere = new ListElement<>(null, testa, null);
        while (!nodoPerScorrere.getData().equals(valore) && nodoPerScorrere.getProssimo() != null) {
            nodoPerScorrere = nodoPerScorrere.getProssimo();
            j++;
        }
        if (nodoPerScorrere.getData() == valore) {
            System.out.println("--Numero del nodo con valore " + nodoPerScorrere.getData() + " = " + j);
        } else System.out.println("--Non ho trovato il nodo con data " + valore + "--");
    }
}
