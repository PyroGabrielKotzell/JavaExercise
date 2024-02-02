package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Main {
    private static Client c;
    private static HashMap<String, Data> hm;

    public static void main(String[] args) {
        //String pr = "";
        hm = new HashMap<>();
        getUsersData();
        //UserThread t = new UserThread();
        c = new Client();
        c.init();
        // thread IO to do
        /*
        while (!t.userIn.equals("break")) {
            if (!t.isExecuting()) t.run();
            if (!pr.equals(t.userIn)){
                pr = t.userIn;
                if (pr.indexOf("write") == 0) c.write(pr.substring(5));
                else if (pr.indexOf("read") == 0) System.out.println(c.read());
                else if (pr.indexOf("request") == 0) {
                    System.out.println("request");
                    request();
                }
                t.userIn = "";
                pr = "";
            }
        }
        */
        request();
        for (Data d: hm.values()) System.out.println(d);
        //t.terminate();
        c.close();
    }

    private static void getUsersData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/com/company/DatiClient.csv"));
            while (br.ready()) {
                String[] s = br.readLine().split(";");
                hm.put(s[0], new Data(s[0], s[1], s[2]));
            }
        } catch (Exception ignored) {
        }
    }

    private static void request() {
        try {
            c.setTimeOut(30000);
            for (Data e : hm.values()) {
                if (!c.isConnected() && !c.inputCheck() && !c.outputCheck()) break;
                c.write(e.getId());
                String s = c.listen();
                e.setNumber(s);
            }
        } catch (Exception e) {
            System.out.println("Server closed, breaking");
        }
    }
}
