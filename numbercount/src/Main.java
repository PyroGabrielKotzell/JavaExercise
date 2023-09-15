import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*101);
        }
        ArrayList list = new ArrayList();
        for (int j:array){
            if (list.get(0).equals(j)){
                list.set(1,list.get(1)+1);
            }
        }
    }
}