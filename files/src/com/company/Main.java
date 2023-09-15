package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String root = "src/com/company/";
        File dir = new File(root+"A&B");
        String string1 = "", string2 = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Scegli file:");
        System.out.print(Arrays.toString(dir.list()) + "\n>");
        string1 = sc.nextLine();
        sc = new Scanner(System.in);
        System.out.print(">");
        string2 = sc.nextLine();
        scrivifile(string1, string2);
    }

    private static void scrivifile(String string1, String string2) {
        try {
            String root = "src/com/company/";
            FileReader leggi1 = new FileReader(root + "A&B/" + string1);
            FileReader leggi2 = new FileReader(root + "A&B/" + string2);
            FileWriter ScriviOut = new FileWriter(root + "output.txt");
            BufferedReader leggiPiu1 = new BufferedReader(leggi1);
            BufferedReader leggiPiu2 = new BufferedReader(leggi2);
            BufferedWriter scriviPiuOut = new BufferedWriter(ScriviOut);
            while (leggiPiu1.ready() && leggiPiu2.ready()) {
                String[] parole1 = leggiPiu1.readLine().split(" ");
                String[] parole2 = leggiPiu2.readLine().split(" ");
                int i = 0, j = 0;
                while (i < parole1.length || j < parole2.length){
                    if (i < parole1.length) {
                        scriviPiuOut.write(parole1[i] + " ");
                        i++;
                    }else if (leggiPiu1.ready()){
                        parole1 = leggiPiu1.readLine().split(" ");
                        scriviPiuOut.newLine();
                        i = 0;
                    }
                    if (j< parole2.length) {
                        scriviPiuOut.write(parole2[j] + " ");
                        j++;
                    }else if (leggiPiu2.ready()){
                        parole2 = leggiPiu2.readLine().split(" ");
                        scriviPiuOut.newLine();
                        j = 0;
                    }
                    scriviPiuOut.flush();
                }
            }
        } catch (Exception e) {
            System.out.println("file non trovato");
        }
    }
}
