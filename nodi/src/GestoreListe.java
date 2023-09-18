import java.io.File;
import java.util.Objects;

public class GestoreListe {
    private ListElement head = new ListElement(0, null), current = head;

    GestoreListe() {
    }

    public void aCapo() {
        current = head;
        System.out.println("Torno a head");
    }

    public void avanti() {
        try {
            current = Objects.requireNonNull(current.getNext());
        }catch (NullPointerException e){
            System.out.println("Non c'è un prossimo nodo");
        }
    }

    public void cambiaValore(int anInt) {
        current.setData(anInt);
        System.out.println("Valore cambiato a: " + anInt);
    }

    /*public void salvaLista() {
        File cartella = new File("./Salvataggio/");
        StringBuilder r = new StringBuilder();
        try {
            File save = new File(r.append("./Salvataggio/save").append(cartella.list().length).append(".txt").toString());
        } catch (NullPointerException e) {
            System.out.println();
        }
    }*/

    public void rimuoviNodo() {
        if (getNumNodo() == 0 && getLenght() != 0) {
            System.out.println("Rimosso nodo " + getNumNodo());
            head = head.getNext();
            current = getPrevNodo();
        } else if (getNumNodo() == getLenght()) {
            System.out.println("Rimosso nodo " + getNumNodo());
            getPrevNodo().setNext(null);
            current = getPrevNodo();
        } else {
            System.out.println("Rimosso nodo " + getNumNodo());
            getPrevNodo().setNext(current.getNext());
            current = getPrevNodo();
        }
    }

    public void creaNodo(int anInt) {
        if (getNumNodo() == 0) {
            head = new ListElement(anInt, head);
        } else if (getNumNodo() == getLenght()) {
            current.setNext(new ListElement(anInt, null));
        } else {
            getPrevNodo().setNext(new ListElement(anInt, current.getNext()));
        }
        System.out.println("Nodo creato");
    }

    private int getLenght() {
        int i = 0;
        while (current.getNext() != null) {
            i++;
        }
        return i;
    }

    public int getNumNodo() {
        int i = 0;
        ListElement e = new ListElement(0, head);
        while (!e.getNext().equals(current)) {
            e = e.getNext();
            i++;
        }
        return i;
    }

    public ListElement getPrevNodo() {
        ListElement e = new ListElement(0, head);
        while (!e.getNext().equals(current)) {
            e = e.getNext();
        }
        return e;
    }

    public int getData() {
        return current.getData();
    }
}
