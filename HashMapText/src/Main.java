import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        creaFiles();
        CodaDiStampa coda = new CodaDiStampa(new File("./src/files/"));
        coda.print();
        Scanner sc = new Scanner(System.in);
        System.out.print("Dammi una o più chiavi:\n>");
        coda.printHM(sc.nextLine().split(" "));
    }

    @SuppressWarnings("all")
    private static void creaFiles() {
        String[] args;
        try {
            FileReader r = new FileReader("./src/args.txt");
            BufferedReader br = new BufferedReader(r);
            String tmp = "";
            while (br.ready()) {
                tmp = tmp + br.readLine();
            }
            r.close();
            args = tmp.split(" ");
            int i = 0;
            File path = new File("./src/files/");
            for (File d : path.listFiles()) d.delete();
            for (String s : args) {
                File text = new File("./src/files/TEXT" + i + ".txt");
                FileWriter fw = new FileWriter(text);
                text.createNewFile();
                fw.write(s);
                fw.close();
                i++;
            }
        } catch (Exception ignored) {
        }
    }
}