public class ListElement<T extends Comparable<T>> {
    private T data;
    private ListElement<T> prossimo, precedente;

    ListElement(T newData, ListElement<T> newNext, ListElement<T> newLast){
        data = newData;
        prossimo = newNext;
        precedente = newLast;
    }

    ListElement(T newData){
        data = newData;
        prossimo = null;
        precedente = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListElement<T> getProssimo() {
        return prossimo;
    }

    public void setProssimo(ListElement<T> prossimo) {
        this.prossimo = prossimo;
    }

    public ListElement<T> getPrecedente() {
        return precedente;
    }

    public void setPrecedente(ListElement<T> precedente) {
        this.precedente = precedente;
    }
}
