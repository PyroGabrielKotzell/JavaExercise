package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client c = new Client();
        c.init();
        String s = "";
        while (!s.equals("break")) {
            s = c.read();
            if(!s.equals("")) System.out.println(s);
        }
        c.close();
    }
}
