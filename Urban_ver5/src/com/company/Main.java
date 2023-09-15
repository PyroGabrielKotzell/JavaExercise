package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static BufferedReader bif1 = null;
    private static BufferedWriter bof1 = null;
    public static File out = null, sel = new File("./");
    public static int pref = 0;

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

            while (pref == 1 && bif1.ready()) {
                bof1.write(bif1.readLine().toLowerCase(Locale.ROOT));
                bof1.newLine();
                bof1.flush();
            }

            while (pref == 2 && bif1.ready()) {
                bof1.write(bif1.readLine().toUpperCase(Locale.ROOT));
                bof1.newLine();
                bof1.flush();
            }

            boolean b = Math.random() * 101 < 50;

            while (pref == 3 && bif1.ready()) {
                String s = (char) bif1.read() + "";
                if (b) bof1.write(s.toUpperCase(Locale.ROOT));
                else bof1.write(s.toLowerCase(Locale.ROOT));
                b = !b;
                bof1.flush();
            }

            bof1.close();
            bif1.close();

        } catch (Exception e) {
            System.out.println("Qualcosa è andato storto: " + e);
        }
        System.out.print("\nFile scelto depositato in output.txt");
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
            if (!(sel.isDirectory() || sel.isFile())) {
                System.out.println("File o Directory non esistente");
                sel = new File(sel.getPath().substring(0, sel.getPath().indexOf(s) - 1));
            }
        }
        s = "";
        String[] res = {"maiuscole", "minuscole", "alternato"};
        System.out.print("Preferisce il testo in Maiuscole, Minuscole o Alternato?\n\\> ");
        while (!(s.equalsIgnoreCase(res[0]) || s.equalsIgnoreCase(res[1]) || s.equalsIgnoreCase(res[2]))) {
            Scanner sc = new Scanner(System.in);
            s = sc.nextLine();
            if (s.equalsIgnoreCase(res[0])) pref = 2;
            else if(s.equalsIgnoreCase(res[1])) pref = 1;
            else if(s.equalsIgnoreCase(res[2])) pref = 3;
            else System.out.println("Non è un'opzione");
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
