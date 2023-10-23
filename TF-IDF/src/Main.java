import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static final HashMap<String, Integer> hm = new HashMap<>();
    private static final ArrayList<String> chiavi = new ArrayList<>();
    private static final File[] files = new File("./src/files/").listFiles();
    private static int paroleF = 0;

    public static void main(String[] args) {
        // prendo il testo dei file e lo salvo nell'hash map
        assert files != null;
        prendiTermini(files);

        // scorro i termini senza doppioni salvati nell'arraylist
        for (int i = 0; i < chiavi.size(); i++) {
            String s = chiavi.get(i);
            for (File f: files) {
                // se il termine è quello del file corrente entro
                if (s.contains(f.getName())){
                    String termine = s.substring(0, s.indexOf(f.getName()));

                    // calcolo il tf-idf
                    System.out.println(termine + " " + f.getName() + ": " + tfIdf(f, termine));

                    // ignorando l'eccezione se il termine è cambiato dal primo file al secondo separo l'output
                    // e metto a zero il numero delle parole del file
                    try {
                        if (!chiavi.get(i+1).contains(f.getName())){
                            System.out.println("--------------");
                            paroleF = 0;
                        }
                    }catch (Exception ignored){}

                    // poi vado al prossimo termine direttamente
                    break;
                }
            }
        }
    }

    // metodo per prendere i termini
    private static void prendiTermini(File[] files) {
        for (File f : files) {
            // apro il file
            GestoreFiles g = new GestoreFiles(f);
            g.open();

            // lo leggo
            while (g.readerReady()) {
                // qualche volta non cancella i caratteri speciali
                String[] termini = g.readln().replaceAll("[^A-Za-z0-9]", " ").split(" ");

                // per ogni termine nel file se c'è già nell'hash map ne alzo il conteggio
                for (String termine : termini) {
                    if (hm.containsKey(termine + f.getName()))
                        hm.put(termine + f.getName(), (hm.get(termine + f.getName()) + 1));
                    else {
                        // altrimenti lo aggiungo sia all'hash map che all'arraylist
                        hm.put(termine + f.getName(), 1);
                        chiavi.add(termine + f.getName());
                    }
                }
            }
        }
    }

    // divido il calcolo in due metodi, uno per il tf e uno per l'idf
    private static double tfIdf(File doc, String termine) {
        float tf = calcTf(doc, termine);
        float idf = calcIdf(termine);
        return (Math.log10(idf) * tf);
    }

    // calcolo del Tf
    private static float calcTf(File doc, String termine) {
        float tf = hm.get(termine + doc.getName());

        // se il contatore delle parole del file è zero, conta le parole del file
        if (paroleF == 0) {
            for (String key : chiavi) {
                if (key.contains(doc.getName())) paroleF++;
            }
        }
        return tf / paroleF;
    }

    // calcolo dell'idf
    private static float calcIdf(String termine) {
        assert files != null;
        float idf = files.length;
        int fConT = 0;
        
        // scorro i file e per ogniuno che ha il termine aumento di uno fConT (File con Termine)
        for (File f : files) {
            if (hm.containsKey(termine + f.getName())) fConT++;
        }
        return idf / fConT;
    }
}