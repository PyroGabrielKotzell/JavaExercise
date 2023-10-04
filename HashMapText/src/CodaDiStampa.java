import java.io.*;

public class CodaDiStampa {
    private final List<File> queue = new List<>();

    CodaDiStampa(File path) {
        for (File f : path.listFiles()) {
            queue.add(f);
        }
    }

    public void print(String path) {
        File text = new File(path + "/print.txt");
        printFile(text);
    }

    public void print() {
        File text = new File("./src/print.txt");
        printFile(text);
    }

    @SuppressWarnings("all")
    private void printFile(File f) {
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("*****INIZIO*****");
            bw.newLine();
            bw.flush();
            for (int i = 0; i < queue.size(); i++) {
                FileReader fr = new FileReader(queue.getValue(i));
                BufferedReader br = new BufferedReader(fr);
                bw.write(br.readLine());
                bw.newLine();
                bw.flush();
                fr.close();
            }
            bw.write("***** FINE *****");
            bw.flush();
            fw.close();
        } catch (Exception ignored) {
        }
    }
}