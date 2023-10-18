import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static final HashMap<String, Integer> hm = new HashMap<>();
    private static final ArrayList<String> chiavi = new ArrayList<>();
    private static final File[] files = new File("./src/files/").listFiles();

    public static void main(String[] args) {
        assert files != null;
        prendiTermini(files);

        for (String s : chiavi) {
            for (File f: files) {
                if (s.contains(f.getName())){
                    System.out.println(tfIdf(f, s.substring(0, s.indexOf(f.getName()))));
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
        GestoreFiles g = new GestoreFiles(doc);
        int paroleFile = 0;
        while (g.readerReady()) {
            paroleFile = paroleFile + g.readln().replaceAll("[^A-Za-z0-9]", " ").split(" ").length;
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