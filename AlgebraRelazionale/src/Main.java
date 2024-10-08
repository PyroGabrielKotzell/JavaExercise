import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        // P1 -> persone
        // P2 -> persone2
        // O -> ordini
        // Pr -> prodotti
        //
        // S -> slection
        // Pj -> projection
        // U -> union
        // D -> difference
        // CP -> cartesianProduct
        // J -> junction/join

        //es1();
        //es2();
        es3();
    }

    private static void es1() {
        Relation persone = new Relation("persone.csv");
        Relation persone2 = new Relation("persone2.csv");
        System.out.println("persone1\n" + persone);

        System.out.println("\npersone2\n" + persone2);

        System.out.println("\nSelection id=0\n" + Relation.selection(persone, "id", "0"));

        ArrayList<String> Pkeys = new ArrayList<>(Arrays.asList(new String[] { "id_utente", "nome", "penna" }));
        System.out.println("\nProjection keys={id, nome, penna}\n" + Relation.projection(persone, Pkeys));

        System.out.println("\nUnion between persone1 and persone2\n" + Relation.union(persone, persone2));

        System.out.println("\nDifference between persone1 and persone2\n" + Relation.difference(persone, persone2));
    }

    private static void es2() {
        Relation persone = new Relation("persone.csv");
        Relation ordini = new Relation("ordini.csv");
        Relation prodotti = new Relation("prodotti.csv");

        System.out.println(
                "\nCartesian product between ordini and prodotti\n" + Relation.cartesianProduct(ordini, prodotti));

        ArrayList<String> junctionField = new ArrayList<>(Arrays.asList(new String[] { "id_prodotto" }));
        Relation J_OPr = Relation.junction(ordini, prodotti, junctionField, junctionField);
        System.out.println("\nJoin between ordini and prodotti\n" + J_OPr);

        doPriceQueries(J_OPr);

        ArrayList<String> Pkeys = new ArrayList<>(Arrays.asList(new String[] { "id_utente", "prezzo_unitario" }));
        Relation Pj_OPr = Relation.projection(J_OPr, Pkeys);

        junctionField = new ArrayList<>(Arrays.asList(new String[] { "id_utente" }));
        Relation J_OPrP1 = Relation.junction(Pj_OPr, persone, junctionField, junctionField);

        int keyPrice = J_OPrP1.keyIndex("prezzo_unitario");

        ArrayList<Integer> prices = new ArrayList<>();

        for (Row row : J_OPrP1.getRows()) {
            prices.add(Integer.parseInt(row.getValue(keyPrice)));
        }

        int maxPrice = Collections.max(prices);

        Relation S_OPrP1 = Relation.selection(J_OPrP1, "prezzo_unitario", maxPrice + "");
        System.out.println("\nUsers who bought the highest priced product\n" + S_OPrP1);
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

    private static void es3() {
        Relation city = new Relation("city.csv");
        Relation country = new Relation("country.csv");
        Relation countryLang = new Relation("countrylanguage.csv");

        System.out.println("\nCountries in europe\n" + Relation.selection(country, "\"Continent\"", "\"Europe\""));

        System.out.println("\nAll French cities\n" + Relation.selection(city, "\"CountryCode\"", "\"FRA\""));

        Relation filteredCountries = Relation.selectionLessThan(Relation.selectionMoreThan(country, "\"Population\"", 100000000), "\"Population\"", 200000000);
        System.out.println("\nCountries with more than 100M and less than 200M of population\n" + filteredCountries);

        Relation southAmericanCountries = Relation.selection(country, "\"Continent\"", "\"South America\"");
        southAmericanCountries.setHeader("\"Name\"", "\"CountryName\"");
        southAmericanCountries.setHeader("\"Population\"", "\"CountryPopulation\"");
        ArrayList<String> JF1 = new ArrayList<>(Arrays.asList(new String[] { "\"Capital\"" }));
        ArrayList<String> JF2 = new ArrayList<>(Arrays.asList(new String[] { "\"ID\"" }));
        Relation J_SACCity = Relation.junction(southAmericanCountries, city, JF1, JF2);
        ArrayList<String> keys = new ArrayList<>(Arrays.asList(new String[] { "\"CountryName\"", "\"Name\"", "\"CountryPopulation\"" }));
        Relation Pj_SACCity = Relation.projection(J_SACCity, keys);
        System.out.println("\nCountry Name, Country Population, and City Name of South American Countries\n" + Pj_SACCity);
        
        Relation asianCountries = Relation.selection(country, "\"Continent\"", "\"Asia\"");
        Relation japan = Relation.selection(country, "\"Name\"", "\"Japan\"");
        long population = Integer.parseInt(japan.getValue(0, "\"Population\"").replaceAll("[^0-9]", ""));
        System.out.println("\nAsian countries with population bigger than Japan\n" + Relation.selectionMoreThan(asianCountries, "\"Population\"", population));

        keys = new ArrayList<>(Arrays.asList(new String[] { "\"CountryCode\"", "\"Name\"" }));
        Relation italianCities = Relation.selection(city, "\"CountryCode\"", "\"ITA\"");
        int populKey = italianCities.keyIndex("\"Population\"");
        ArrayList<Integer> populations = new ArrayList<>();
        for (Row row : italianCities.getRows()) {
            populations.add(Integer.parseInt(row.getValue(populKey).replaceAll("[^0-9]", "")));
        }
        int maxPopul = Collections.max(populations);
        int minPopul = Collections.min(populations);
        System.out.println("\nMax Population Italian cities: " + maxPopul + "\nMin Population Italian cities: " + minPopul);

        JF1 = new ArrayList<>(Arrays.asList(new String[] { "\"CountryCode\"" }));
        Relation interestedLang = Relation.selection(countryLang, "\"Language\"", "\"English\"");
        //"French"
        //Relation countryWithLang
    }
}
