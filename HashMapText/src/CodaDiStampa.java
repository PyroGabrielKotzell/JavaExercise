import java.io.*;
import java.util.HashMap;
import java.util.Objects;

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

    @SuppressWarnings("all")
    public void print() {
        File f = new File("./src/print.txt");
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("*****INIZIO*****\n");
            bw.flush();
            for (int i = 0; i < queue.size()-1; i++) {
                File fileQueue = queue.getValue(i);
                FileReader fr = new FileReader(fileQueue);
                BufferedReader br = new BufferedReader(fr);
                String fileLine = br.readLine();
                bw.write(fileLine);
                bw.newLine();
                bw.flush();
                hm.put(fileLine + fileQueue.getName(), fileQueue.getName());
            }
            bw.write("***** FINE *****\n");
            bw.flush();
            fw.close();
        } catch (Exception ignored) {
        }
    }
}