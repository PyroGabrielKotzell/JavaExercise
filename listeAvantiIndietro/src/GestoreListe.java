public class GestoreListe<T extends Comparable<T>> {
    private ListElement<T> head = new ListElement<>(null);

    GestoreListe(){}

    GestoreListe(T value){
        head.setData(value);
    }

    /**
     * Cambia valore del nodo a posizione {@code i}
     *
     * @param var Valore
     * @param i   Numero del nodo
     */
    public void changeVal(T var, int i) {
        try {
            getNode(i).setData(var);
        } catch (NullPointerException ignored) {}
    }

    /**
     * Metodo per rimuovere un nodo da indice
     * @param num Indice del nodo
     */
    public void removeNum(int num) {
        int j = 0;
        ListElement<T> cNode = head;
        do {
            if (j == num) remove(cNode);
            cNode = cNode.getNext();
            j++;
        } while (cNode != null);
    }

    /**
     * Metodo per rimuovere un nodo da valore
     * @param valore Il valore del nodo
     */
    public void removeVal(Object valore) {
        int j = 0;
        ListElement<T> cNode = head;
        do{
            if (cNode.getData().equals(valore)) remove(cNode);
            cNode = cNode.getNext();
            j++;
        }while (cNode != null);
    }

    /**
     * Metodo privato per rimuovere il nodo dato
     * @param cNode Il nodo da rimuovere
     */
    private void remove(ListElement<T> cNode) {
        int i = getNumNode(cNode);
        if (i == 0) {
            try {
                head.getNext().setPrev(null);
            }catch (NullPointerException ignored){}
            ListElement<T> tmp = head.getNext();
            head.setNext(null);
            head = tmp;
        } else {
            cNode.getPrev().setNext(cNode.getNext());
            try {
                cNode.getNext().setPrev(cNode.getPrev());
            }catch (NullPointerException ignored){}
            cNode.setPrev(null);
            cNode.setNext(null);
        }
    }


    /**
     * Aggiungo un nodo scorrendo per crearlo alla giusta
     * posizione mantenendo ordinata la lista
     *
     * @param valore Valore del nodo che verrà creato
     */
    public void add(T valore) {
        int i = 0;
        ListElement<T> cNode = head;
        do {
            if (cNode.getData().compareTo(valore) >= 0) {
                cNode.setPrev(new ListElement<>(valore, cNode, cNode.getPrev()));
                cNode = cNode.getPrev();
                try {
                    cNode.getPrev().setNext(cNode);
                } catch (NullPointerException ignored) {}
                if (i == 0) head = cNode;
                break;
            } else if (cNode.getNext() == null) {
                cNode.setNext(new ListElement<>(valore, null, cNode));
                break;
            }
            cNode = cNode.getNext();
            i++;
        } while (cNode != null);
    }

    /**
     * Ottengo il nodo tramite indice
     *
     * @param i indice
     * @return {@link ListElement}. {@code Null} se non lo trova
     */
    private ListElement<T> getNode(int i) {
        int j = 0;
        ListElement<T> cNode = head;
        do {
            if (j == i) return cNode;
            cNode = cNode.getNext();
            j++;
        } while (cNode != null);
        return null;
    }

    /**
     * Metodo per ottenere la lunghezza della lista
     *
     * @return int
     */
    public int getLenght() {
        int i = 0;
        ListElement<T> cNode = head;
        do {
            cNode = cNode.getNext();
            i++;
        } while (cNode != null);
        return i;
    }

    /**
     * Metodo per ottenere il numero di un nodo
     *
     * @param tmp Il nodo in questione
     * @return int - l'indice del nodo
     */
    private int getNumNode(ListElement<T> tmp) {
        int j = 0;
        ListElement<T> cNode = head;
        do {
            if (cNode.equals(tmp)) return j;
            cNode = cNode.getNext();
            j++;
        } while (cNode != null);
        return -1;
    }

    /**
     * Metodo per ottenere la variabile del nodo di indice {@code i}
     *
     * @param numero Numero del nodo
     */
    public T getValNode(int numero) {
        int j = 0;
        ListElement<T> cNode = head;
        do {
            if (j == numero) return cNode.getData();
            cNode = cNode.getNext();
            j++;
        } while (cNode != null);
        return null;
    }

    /**
     * Metodo per ottenere l'indice del nodo con il valore passato
     *
     * @param valore Valore del nodo
     */
    public int getNumNodeVal(T valore) {
        int j = 0;
        ListElement<T> cNode = head;
        do {
            if (cNode.getData().equals(valore)) return j+1;
            cNode = cNode.getNext();
            j++;
        } while (cNode != null);
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("{");
        ListElement<T> cNode = head;
        do{
            r.append(cNode.getData().toString()).append(",");
            cNode = cNode.getNext();
        }while (cNode != null);
        r.append("}");
        return r.toString();
    }
}
