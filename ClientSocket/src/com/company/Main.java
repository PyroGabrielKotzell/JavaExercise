package com.company;

public class Main {
    public static void main(String[] args) {
        /*UserThread t = new UserThread();
        while (!t.userIn.equals("break")) {
            if (!t.isExecuting()) t.run();
            if (!t.userIn.equals("")) System.out.println(t.userIn);
        }
        t.terminate();*/
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
