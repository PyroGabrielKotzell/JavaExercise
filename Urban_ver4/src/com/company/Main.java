package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static BufferedReader bif1 = null;
    private static BufferedWriter bof1 = null;
    public static File out = null, sel = new File("./");

    public static void main(String[] args) {
        try {
            selectFile();
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

    private static void selectFile() {
        String s;
        System.out.println("Seleziona un File, ti puoi muovere tra le Cartelle");
        while (sel == null || sel.isDirectory()) {
            Scanner sc = new Scanner(System.in);
            System.out.println(Arrays.toString(sel.list()));
            System.out.print("\\> ");
            s = sc.nextLine();
            sel = new File(sel.getPath() + "/" + s);
            if (!(sel.isDirectory() || sel.isFile())){
                System.out.println("File o Directory non esistente");
                sel = new File(sel.getPath().substring(0, sel.getPath().indexOf(s)-1));
            }
        }
    }

    private static void initWriter() throws Exception {
        out = new File("src/com/company/output.txt");
        FileWriter of1 = new FileWriter(out);
        bof1 = new BufferedWriter(of1);
    }

    private static void initReader() throws Exception {
        FileReader if1 = new FileReader(sel);
        bif1 = new BufferedReader(if1);
    }
}
