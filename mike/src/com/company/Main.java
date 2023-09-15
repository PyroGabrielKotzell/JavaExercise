package com.company;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        FileReader f;
        try {
            String c;
            f = new FileReader("testo.txt");
            FileWriter w = new FileWriter("dabussy.txt");
            BufferedReader Bf = new BufferedReader(f);
            BufferedWriter Bw = new BufferedWriter(w);
            while (Bf.ready()) {
                c = Bf.readLine();
                System.out.print(c + "\n");
                Bw.write(c);
                Bw.newLine();
                Bw.flush();
                Thread.sleep(100);
            }
            f.close();
            w.close();
        }catch (Exception e){
            System.out.println("errore" + e);
        }
    }
}
