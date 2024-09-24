import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Relation r = new Relation("persone.csv");
        Relation r2 = new Relation("persone2.csv");

        System.out.println("Relation 1");
        System.out.println(r);
        System.out.println("\nRelation 2");
        System.out.println(r2);
        System.out.println("\nSelection id=0");
        System.out.println(r.selection(r, "id", "0"));
        System.out.println("\nProjection keys={id, nome, penna}");
        ArrayList<String> keys = new ArrayList<>(Arrays.asList(new String[] { "id", "nome", "penna" }));
        System.out.println(r.projection(r, keys));
        System.out.println("\nUnion between r 1 and r 2");
        System.out.println(r.union(r, r2));
        System.out.println("\nDifference between r 1 and r 2");
        System.out.println(r.difference(r, r2));
    }
}
