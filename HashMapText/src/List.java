class List<T> {
    private final Node<T> head = new Node<>(null, null);

    public void add(T value){
        Node<T> c = head;
        do{
            if (c.getNext() == null) {
                c.setNext(new Node<>(value, null));
                break;
            }
            c = c.getNext();
        }while (true);
    }

    public int size(){
        int i = 0;
        Node<T> c = head;
        do{
            c = c.getNext();
            i++;
        }while (c != null);
        return i;
    }

    public T getValue(int i){
        int j = -1;
        Node<T> c = head;
        do{
            if (j == i) return c.value;
            c = c.getNext();
            j++;
        }while (c != null);
        return null;
    }

    private static class Node<T>{
        private final T value;
        private Node<T> next;

        Node(T value, Node<T> next){
            this.value = value;
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
