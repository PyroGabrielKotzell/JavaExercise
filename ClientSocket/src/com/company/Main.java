package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client c = new Client();
        c.init();
        c.write("lollers");
        c.close();
        /*while (true){
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (s.contains("write")) {
                System.out.println("writing " + s.substring(6));
                c.write(s.substring(6));
            }
            if (s.equals("close")) {
                c.close();
                break;
            }
        }*/
    }
}
