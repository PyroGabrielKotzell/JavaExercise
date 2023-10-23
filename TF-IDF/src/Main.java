import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static final HashMap<String, Integer> hm = new HashMap<>();
    private static final ArrayList<String> chiavi = new ArrayList<>();
    private static final File[] files = new File("./src/files/").listFiles();
    /*private static int[] paroleF;

    static {
        assert files != null;
        paroleF = new int[files.length];
    }*/

    public static void main(String[] args) {
        assert files != null;
        prendiTermini(files);

        for (String s : chiavi) {
            for (File f: files) {
                if (s.contains(f.getName())){
                    String term = s.substring(0, s.indexOf(f.getName()));
                    System.out.println(term + " " + f.getName() + ": " + tfIdf(f, term));
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
        int paroleFile = 0;
        for (String key: chiavi) {
            if(key.contains(doc.getName())) paroleFile++;
        }
        return tf / paroleFile;
    }

    private static float calcIdf(String termine) {
        float idf = files.length;
        int fConT = 0;
        for (File f : files) {
            if (hm.containsKey(termine + f.getName())) fConT++;
        }
        return idf / fConT;
    }
}