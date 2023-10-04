public class ListElement {
    private int data;
    private ListElement prossimo;

    ListElement(int newData, ListElement newNext){
        data = newData;
        prossimo = newNext;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ListElement getProssimo() {
        return prossimo;
    }

    public void setProssimo(ListElement prossimo) {
        this.prossimo = prossimo;
    }
}