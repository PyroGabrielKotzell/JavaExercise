import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Relation persone = new Relation("persone.csv");
        Relation persone2 = new Relation("persone2.csv");
        Relation ordini = new Relation("ordini.csv");
        Relation prodotti = new Relation("prodotti.csv");


        System.out.println("persone1");
        System.out.println(persone);
        System.out.println("\npersone2");
        System.out.println(persone2);
        System.out.println("\nSelection id=0");
        System.out.println(Relation.selection(persone, "id", "0"));
        System.out.println("\nProjection keys={id, nome, penna}");
        ArrayList<String> keys = new ArrayList<>(Arrays.asList(new String[] { "id", "nome", "penna" }));
        System.out.println(Relation.projection(persone, keys));
        System.out.println("\nUnion between persone1 and persone2");
        System.out.println(Relation.union(persone, persone2));
        System.out.println("\nDifference between persone1 and persone2");
        System.out.println(Relation.difference(persone, persone2));
        System.out.println("\nCartesian product between ordini and prodotti");
        System.out.println(Relation.cartesianProduct(ordini, prodotti));
        System.out.println("\nJoin between ordini and prodotti");
        ArrayList<String> junctionField = new ArrayList<>(Arrays.asList(new String[] { "id_prodotto" }));
        System.out.println(Relation.junction(ordini, prodotti, junctionField));
        //System.out.println("\nCartesian product between ordini and prodotti");
        //System.out.println(Relation.cartesianProduct(ordini, prodotti));
    }
}
