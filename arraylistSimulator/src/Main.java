import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> l = new MyArrayList<>();
        System.out.println("add 1");
        l.add(1);
        System.out.print("size: ");
        System.out.println(l.size());
        System.out.println("add 3");
        l.add(3);
        System.out.print("to array: ");
        System.out.println(Arrays.toString(l.toArray()));
        System.out.println("remove n = 0");
        l.remove(0);
        System.out.println("add 5");
        l.add(5);
        System.out.print("check if contains 3: ");
        System.out.println(l.contains(3));
        System.out.print("index of 3: ");
        System.out.println(l.indexOf(3));
        System.out.print("to array: ");
        System.out.println(Arrays.toString(l.toArray()));
        System.out.println("remove object = 3");
        l.remove((Object) 3);
        System.out.print("to array: ");
        System.out.println(Arrays.toString(l.toArray()));
        System.out.println("add 10");
        l.add(10);
        System.out.print("to array: ");
        System.out.println(Arrays.toString(l.toArray()));
        System.out.println("clear");
        l.clear();
        System.out.print("to array: ");
        System.out.println(Arrays.toString(l.toArray()));
        System.out.print("is empty: ");
        System.out.println(l.isEmpty());
        System.out.println("add 9, 3, 5, 3, 4");
        l.add(9);
        l.add(3);
        l.add(5);
        l.add(3);
        l.add(4);
        System.out.print("to array: ");
        System.out.println(Arrays.toString(l.toArray()));
        System.out.print("index of 3: ");
        System.out.println(l.indexOf(3));
        System.out.print("last index of 3: ");
        System.out.println(l.lastIndexOf(3));
        System.out.println("set n = 1 to object = 6");
        l.set(1, 6);
        System.out.print("to array: ");
        System.out.println(Arrays.toString(l.toArray()));
        System.out.print("get n = 1: ");
        System.out.println(l.get(1));
    }
}