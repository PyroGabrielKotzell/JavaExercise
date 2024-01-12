package com.company;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket s;
    private InputStreamReader isr;
    private BufferedReader br;

    public void write(String sa) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(s.getOutputStream());
        }catch (Exception e){e.printStackTrace();}
        if (osw == null) {
            return;
        }
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw, true);
        System.out.println(sa);
        pw.write(sa);
    }

    public String read() {
        try {
            return br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            init();
            return "";
        }
    }

    public boolean init() {
        try {
            s = new Socket("192.168.8.27", 1069);
            isr = new InputStreamReader(s.getInputStream());
            br = new BufferedReader(isr);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            br.close();
            isr.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
