import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static final HashMap<String, Integer> hm = new HashMap<>();
    private static final ArrayList<String> chiavi = new ArrayList<>();
    private static final File[] files = new File("./src/files/").listFiles();
    private static int paroleF = 0;

    public static void main(String[] args) {
        assert files != null;
        prendiTermini(files);

        for (int i = 0; i < chiavi.size(); i++) {
            String s = chiavi.get(i);
            for (File f: files) {
                if (s.contains(f.getName())){
                    String term = s.substring(0, s.indexOf(f.getName()));
                    System.out.println(term + " " + f.getName() + ": " + tfIdf(f, term));
                    try {
                        if (!chiavi.get(i+1).contains(f.getName())){
                            System.out.println("--------------");
                            paroleF = 0;
                        }
                    }catch (Exception ignored){}
                    break;
                }
            }
        }
    }

    private static void prendiTermini(File[] files) {
        for (File f : files) {
            GestoreFiles g = new GestoreFiles(f);
            g.open();
            while (g.readerReady()) {
                // controlla perché non cancella caratteri speciali
                String[] termini = g.readln().replaceAll("[^A-Za-z0-9]", " ").split(" ");
                for (String termine : termini) {
                    if (hm.containsKey(termine + f.getName()))
                        hm.put(termine + f.getName(), (hm.get(termine + f.getName()) + 1));
                    else {
                        hm.put(termine + f.getName(), 1);
                        chiavi.add(termine + f.getName());
                    }
                }
            }
        }
    }

    private static double tfIdf(File doc, String termine) {
        float tf = calcTf(doc, termine);
        float idf = calcIdf(termine);
        return (Math.log10(idf) * tf);
    }

    private static float calcTf(File doc, String termine) {
        float tf = hm.get(termine + doc.getName());
        if (paroleF == 0) {
            for (String key : chiavi) {
                if (key.contains(doc.getName())) paroleF++;
            }
        }
        return tf / paroleF;
    }

    private static float calcIdf(String termine) {
        assert files != null;
        float idf = files.length;
        int fConT = 0;
        for (File f : files) {
            if (hm.containsKey(termine + f.getName())) fConT++;
        }
        return idf / fConT;
    }
}