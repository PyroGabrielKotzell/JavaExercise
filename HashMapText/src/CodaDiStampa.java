import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class CodaDiStampa {
    private final List<File> queue = new List<>();
    private final HashMap<String, String> hm = new HashMap<>();

    CodaDiStampa(File path) {
        for (File f : Objects.requireNonNull(path.listFiles())) {
            queue.add(f);
        }
    }

    public void printHM(String[] keys) {
        GestoreFiles g = new GestoreFiles("./src/FilteredPrintQueue.txt");
        try {
            g.createFile();
            g.open();
            for (String key : keys) {
                for (int i = 0; i < queue.size() - 1; i++) {
                    String tmp = hm.get(key + queue.getValue(i).getName());
                    if (tmp != null) {
                        g.write(tmp + "\n");
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

    public void print() {
        GestoreFiles g = new GestoreFiles("./src/print.txt");
        try {
            g.createFile();
            g.open();
            for (int i = 0; i < queue.size() - 1; i++) {
                GestoreFiles fq = new GestoreFiles(queue.getValue(i));
                fq.open();
                g.write("*****INIZIO*****\n");
                while (fq.readerReady()) {
                    String[] line = fq.readln().replaceAll("[^a-zA-Z0-9_-]", " ").split(" ");
                    for (String word : line) {
                        g.write(word + "\n");
                        hm.put(word + fq.getFile().getName(), fq.getFile().getName());
                    }
                }
                g.write("***** FINE *****\n");
            }
        } catch (Exception ignored) {
        }
    }
}