public class ListElement<T extends Comparable<T>> {
    private T data;
    private ListElement<T> next, prev;

    ListElement(T newData, ListElement<T> newNext, ListElement<T> newLast){
        data = newData;
        next = newNext;
        prev = newLast;
    }

    ListElement(T newData){
        data = newData;
        next = null;
        prev = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListElement<T> getNext() {
        return next;
    }

    public void setNext(ListElement<T> next) {
        this.next = next;
    }

    public ListElement<T> getPrev() {
        return prev;
    }

    public void setPrev(ListElement<T> prev) {
        this.prev = prev;
    }
}
