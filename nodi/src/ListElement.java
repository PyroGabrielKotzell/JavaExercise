public class ListElement {
    private int data;
    private ListElement next;

    ListElement(int newData, ListElement newNext){
        data = newData;
        next = newNext;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ListElement getNext() {
        return next;
    }

    public void setNext(ListElement next) {
        this.next = next;
    }
}
