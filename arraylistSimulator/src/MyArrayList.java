public class MyArrayList<T> {
    private Object[] objects = new Object[0];

    public void add(T t) {
        Object[] tmp = new Object[objects.length + 1];
        System.arraycopy(objects, 0, tmp, 0, objects.length);
        objects = new Object[tmp.length];
        System.arraycopy(tmp, 0, objects, 0, tmp.length);
        objects[objects.length - 1] = t;
    }

    public void remove(int n) {
        Object[] tmp = new Object[objects.length - 1];
        int j = 0;
        for (int i = 0; i < objects.length; i++)
            if (i != n) {
                tmp[j] = objects[i];
                j++;
            }
        objects = new Object[tmp.length];
        System.arraycopy(tmp, 0, objects, 0, tmp.length);
    }

    public void set(int n, T t) {
        objects[n] = t;
    }

    public int size() {
        return objects.length;
    }

    @SuppressWarnings("unchecked")
    public T get(int n) {
        return (T) objects[n];
    }

    public void remove(Object o) {
        Object[] tmp = new Object[objects.length - 1];
        int j = 0;
        boolean found = false;
        for (Object object : objects) {
            if (object.equals(o) && !found) found = true;
            else {
                tmp[j] = object;
                j++;
            }
        }
        objects = new Object[tmp.length];
        System.arraycopy(tmp, 0, objects, 0, tmp.length);
    }

    public void clear() {
        objects = new Object[0];
    }

    public int indexOf(Object o) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(o)) return (i + 1);
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = objects.length - 1; i > -1; i--) {
            if (objects[i].equals(o)) return (i + 1);
        }
        return -1;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public Object[] toArray() {
        return objects;
    }

    public boolean isEmpty() {
        return objects.length == 0;
    }
}
