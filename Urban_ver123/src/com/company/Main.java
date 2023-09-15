package com.company;

import java.io.*;
import java.util.Locale;

public class Main {
    private static BufferedReader bif1 = null;
    private static BufferedWriter bof1 = null;
    public static File out = null;

    public static void main(String[] args) {
        try {

            initReader();
            while (bif1.ready()) {
                System.out.println(bif1.readLine());
            }

            System.out.println();
            initReader();

            while (bif1.ready()) {
                System.out.println(bif1.readLine().toUpperCase(Locale.ROOT));
            }

            initWriter();
            initReader();

            while (bif1.ready()) {
                bof1.write(bif1.readLine());
                bof1.newLine();
                bof1.flush();
            }

            bof1.close();
            bif1.close();

        } catch (Exception e) {
            System.out.println("Qualcosa è andato storto: " + e);
        }
    }

    private static void initWriter() throws Exception {
        out = new File("src/com/company/output.txt");
        FileWriter of1 = new FileWriter(out);
        bof1 = new BufferedWriter(of1);
    }

    private static void initReader() throws Exception {
        FileReader if1 = new FileReader("src/com/company/documento.txt");
        bif1 = new BufferedReader(if1);
    }
}
