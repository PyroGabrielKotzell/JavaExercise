import java.io.*;
import java.util.ArrayList;

public class Main {
    public static File txt = new File("src/csvfile");
    public static FileReader fi;
    public static BufferedReader bfi;

    public static void main(String[] args) {
        try {
            fi = new FileReader(txt);
            bfi = new BufferedReader(fi);

            ArrayList<ArrayList<String>> csv = new ArrayList<>();
            ArrayList<String> c = new ArrayList<>();
            String[] s;
            while (bfi.ready()){
                s = bfi.readLine().split(",");
                for (int i = 1; i < s.length; i++) {
                    csv.add(new ArrayList<>().add(s[i]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}