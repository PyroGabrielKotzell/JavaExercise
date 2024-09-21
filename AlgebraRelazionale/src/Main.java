import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Relation r = new Relation("persone.csv");
        System.out.println(r.toString());
        System.out.println();
        System.out.println();
        System.out.println(r.selection(r, "id", "0"));
        System.out.println();
        System.out.println();
        ArrayList<String> keys = new ArrayList<>(Arrays.asList(new String[]{"id", "nome", "penna"}));
        System.out.println(r.projection(r, keys));
    }
}
