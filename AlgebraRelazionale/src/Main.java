import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        Relation persone = new Relation("persone.csv");
        Relation persone2 = new Relation("persone2.csv");
        Relation ordini = new Relation("ordini.csv");
        Relation prodotti = new Relation("prodotti.csv");

        System.out.println("persone1\n" + persone);

        System.out.println("\npersone2\n" + persone2);

        System.out.println("\nSelection id=0\n" + Relation.selection(persone, "id", "0"));

        ArrayList<String> Pkeys = new ArrayList<>(Arrays.asList(new String[] { "id_utente", "nome", "penna" }));
        System.out.println("\nProjection keys={id, nome, penna}\n" + Relation.projection(persone, Pkeys));

        System.out.println("\nUnion between persone1 and persone2\n" + Relation.union(persone, persone2));

        System.out.println("\nDifference between persone1 and persone2\n" + Relation.difference(persone, persone2));

        System.out.println(
                "\nCartesian product between ordini and prodotti\n" + Relation.cartesianProduct(ordini, prodotti));

        ArrayList<String> junctionField = new ArrayList<>(Arrays.asList(new String[] { "id_prodotto" }));
        Relation JordiniProdottiID_Prodotto = Relation.junction(ordini, prodotti, junctionField);
        System.out.println("\nJoin between ordini and prodotti\n" + JordiniProdottiID_Prodotto);

        doPriceQueries(JordiniProdottiID_Prodotto);

        Pkeys = new ArrayList<>(Arrays.asList(new String[] { "id_utente", "prezzo_unitario" }));
        Relation PprodottiID_Utente = Relation.projection(JordiniProdottiID_Prodotto, Pkeys);

        junctionField = new ArrayList<>(Arrays.asList(new String[] { "id_utente" }));
        Relation JordiniProdottiID_Utente = Relation.junction(PprodottiID_Utente, persone, junctionField);
        
        int keyPrice = JordiniProdottiID_Utente.keyIndex("prezzo_unitario");

        ArrayList<Integer> prices = new ArrayList<>();

        for (Row row : JordiniProdottiID_Utente.getRows()) {
            prices.add(Integer.parseInt(row.getValue(keyPrice)));
        }

        int maxPrice = Collections.max(prices);

        Relation SordiniProdottiID_UtentePrezzo_Unitario = Relation.selection(JordiniProdottiID_Utente, "prezzo_unitario", maxPrice + "");
        System.out.println("\nUsers who bought the highest priced product\n" + SordiniProdottiID_UtentePrezzo_Unitario);
    }

    private static void doPriceQueries(Relation relation) {
        ArrayList<String> keys = new ArrayList<>(Arrays.asList(new String[] { "qty", "prezzo_unitario" }));
        Relation projProds = Relation.projection(relation, keys);

        int keyQuantity = projProds.keyIndex("qty");
        int keyPrice = projProds.keyIndex("prezzo_unitario");
        int prezzoTotale = 0;
        int i = 1;

        System.out.println("\nTtotale per singolo ordine");
        for (Row row : projProds.getRows()) {
            int prezzoOrdine = Integer.parseInt(row.getValue(keyQuantity)) * Integer.parseInt(row.getValue(keyPrice));
            System.out.println("Ordine " + i + ": " + prezzoOrdine);
            prezzoTotale += prezzoOrdine;
            i++;
        }

        System.out.println("\nPrezzo totale di tutti gli ordini\n" + prezzoTotale);
    }
}
