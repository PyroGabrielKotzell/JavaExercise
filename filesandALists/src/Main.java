import java.io.*;
import java.util.ArrayList;

public class Main {
    public static File txt = new File("src/txt.txt"), out = new File("src/output.txt");
    public static FileWriter fo;
    public static FileReader fi;
    public static BufferedWriter bfo;
    public static BufferedReader bfi;

    public static void main(String[] args) {
        try {
            fo = new FileWriter(out);
            fi = new FileReader(txt);
            bfo = new BufferedWriter(fo);
            bfi = new BufferedReader(fi);

            ArrayList<String> linee = new ArrayList<>();
            while (bfi.ready()){
                linee.add(bfi.readLine());
            }
            linee.sort(String::compareToIgnoreCase);
            for (String i : linee){
                bfo.write(i);
                bfo.newLine();
                bfo.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}