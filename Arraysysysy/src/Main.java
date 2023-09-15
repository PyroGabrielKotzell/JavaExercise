import java.io.*;

public class Main {
    public static void main(String[] args) {
        File in = new File("./src/input.txt");
        try {
            FileReader rd = new FileReader(in);
            BufferedReader brd = new BufferedReader(rd);
            if (brd.ready()) {
                int n = Integer.parseInt(brd.readLine());
                String[] tmp = brd.readLine().split(" ");
                int somma = 0;
                for (String s : tmp) somma = somma + Integer.parseInt(s);
                File out = new File("./src/output.txt");
                FileWriter fw = new FileWriter(out);
                BufferedWriter bfw = new BufferedWriter(fw);
                bfw.write(somma + " " + ((double)somma/n));
                bfw.flush();
                brd.close();
                rd.close();
                bfw.close();
                fw.close();
            }
        }catch (IOException e){
            System.out.println("File non trovato");
        }catch (NumberFormatException e){
            System.out.println("Numero/i non valido/i");
        }
    }
}