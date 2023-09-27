public class GestoreListe<T extends Comparable<T>> {
    private ListElement<T> testa = new ListElement<>(null);

    // costruttore inutile
    GestoreListe() {
    }

    // cambio valore del nodo corrente: 'nodoCorrente'
    public void changeVal(T var, int i) {
        try {
            getNode(i).setData(var);
            System.out.println("--Valore cambiato a: " + var + "--");
        }catch (NullPointerException e){
            System.out.println("Nessun nodo a pos: " + i);
        }
    }

    // cerco il nodo da rimuovere con il numero
    public void removeNum(int num) {
        int j = 0;
        // scorro con un nodo nuovo
        ListElement<T> cNode = new ListElement<>(null, testa, null);
        while (j != num && cNode.getNext() != null) {
            cNode = cNode.getNext();
            j++;
        }
        // controllo che l'abbia trovato
        if (j == num) {
            if (num == 0) {
                testa.getNext().setPrev(null);
                ListElement<T> tmp = testa.getNext();
                testa.setNext(null);
                testa = tmp;
            } else {
                cNode.getPrev().setNext(cNode.getNext());
                cNode.getNext().setPrev(cNode.getPrev());
                cNode.setPrev(null);
                cNode.setNext(null);
            }
            System.out.println("--Nodo rimosso " + num + "--");
        } else System.out.println("--Non ho trovato il nodo " + num + "--");
    }

    // cerco con il valore
    public void removeVal(Object valore) {
        int j = 0;
        // scorro con un nodo nuovo
        ListElement<T> cNode = new ListElement<>(null, testa, null);
        while (!cNode.getData().equals(valore) && cNode.getNext() != null) {
            cNode = cNode.getNext();
            j++;
        }
        // controllo che l'abbia trovato
        if (cNode.getData().equals(valore)) {
            if (j == 0) {
                testa.getNext().setPrev(null);
                ListElement<T> tmp = testa.getNext();
                testa.setNext(null);
                testa = tmp;
            } else {
                cNode.getPrev().setNext(cNode.getNext());
                cNode.getNext().setPrev(cNode.getPrev());
                cNode.setPrev(null);
                cNode.setNext(null);
            }
            System.out.println("--Nodo rimosso " + j + "--");
        } else System.out.println("--Non ho trovato il nodo con data " + valore + "--");
    }
    // ultimamente perché i metodi per la rimozione sono simili si può convergere la seconda parte
    // (l'effettiva rimozione) in un metodo privato a parte


    // aggiungo nodo
    public void add(T valore) {
        int i = 0;
        ListElement<T> cNode = testa;
        while (cNode.getNext() != null) {
            if (cNode.getData().compareTo(valore) < 0) cNode = cNode.getNext();
            else {
                getNode(i).setNext(new ListElement<>(valore, getNode(i).getNext(), getNode(i)));
                break;
            }
            i++;
        }
        System.out.println("--Nodo creato " + (i + 1) + "--");
    }

    private ListElement<T> getNode(int i) {
        int j = 0;
        ListElement<T> cNode = new ListElement<>(null, testa, null);
        while (j != i && cNode.getNext() != null) {
            cNode = cNode.getNext();
            j++;
        }
        if (j == i) return cNode;
        return null;
    }

    // ottengo la lunghezza della lista
    public int getLenght() {
        int i = 0;
        ListElement<T> cNode = new ListElement<>(null, testa, null);
        while (cNode.getNext() != null) {
            cNode = cNode.getNext();
            i++;
        }
        return i;
    }

    // prendo il numero del nodo corrente
    public int getNumNode(ListElement<T> tmp) {
        int j = 0;
        ListElement<T> cNode = new ListElement<>(null, testa, null);
        while (!cNode.getNext().equals(tmp)) {
            cNode = cNode.getNext();
            j++;
        }
        return j;
    }

    // cerco con il numero del nodo e ne ottengo la data
    public void getValNode(int numero) {
        int j = 0;
        ListElement<T> cNode = new ListElement<>(null, testa, null);
        while (j != numero && cNode.getNext() != null) {
            cNode = cNode.getNext();
            j++;
        }
        if (j == numero) {
            System.out.println("--Valore del nodo numero " + numero + " = " + cNode.getData());
        } else System.out.println("--Non ho trovato il nodo " + numero + "--");
    }

    // cerco con il valore del nodo e ne ottengo il numero
    public void getNumNodeVal(T valore) {
        int j = 0;
        ListElement<T> cNode = new ListElement<>(null, testa, null);
        while (!cNode.getData().equals(valore) && cNode.getNext() != null) {
            cNode = cNode.getNext();
            j++;
        }
        if (cNode.getData().equals(valore)) {
            System.out.println("--Numero del nodo con valore " + cNode.getData() + " = " + j);
        } else System.out.println("--Non ho trovato il nodo con data " + valore + "--");
    }
}
