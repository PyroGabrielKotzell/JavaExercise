import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class CodaDiStampa {
    private final List<File> queue = new List<>();
    private HashMap<String, String> hm = new HashMap<>();

    CodaDiStampa(File path) {
        for (File f : Objects.requireNonNull(path.listFiles())) {
            queue.add(f);
        }
    }

    @SuppressWarnings("all")
    public void printHM(String[] s) {
        File f = new File("./src/FilteredPrintQueue.txt");
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key : s) {
                for (int i = 0; i < queue.size(); i++) {
                    String tmp = hm.get(key + queue.getValue(i).getName());
                    if (tmp != null) {
                        bw.write(tmp);
                        bw.newLine();
                        bw.flush();
                    }
                }
            }
            fw.close();
        } catch (Exception ignored) {
        }
    }

    public void print() {
        GestoreFiles g = new GestoreFiles("./src/print.txt");
        try {
            g.createFile();
            g.open();
            for (int i = 0; i < queue.size()-1; i++) {
                GestoreFiles fq = new GestoreFiles(queue.getValue(i));
                fq.open();
                System.out.println(fq.readln());
                g.write("*****INIZIO*****\n");
                while(fq.readerReady()){
                    Pattern p = Pattern.compile("[a-zA-Z0-9]");
                    String[] s = fq.readln().replaceAll(p.pattern(), " ").split(" ");
                    System.out.println(p.pattern());
                }
                g.write("***** FINE *****\n");
                //hm.put(fileLine + fileQueue.getName(), fileQueue.getName());
            }
        } catch (Exception ignored) {
        }
    }
}