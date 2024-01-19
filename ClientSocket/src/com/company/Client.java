package com.company;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket s;

    public void write(String string) {
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            osw = new OutputStreamWriter(s.getOutputStream());
            bw = new BufferedWriter(osw);
            pw = new PrintWriter(bw, true);
            pw.write(string);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) pw.close();
            try {
                if (bw != null) bw.close();
                if (osw != null) osw.close();
            } catch (Exception ignored) {
            }
        }
    }

    public String read() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(s.getInputStream());
            br = new BufferedReader(isr);
            return br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }finally {
            try {
                if (isr != null) isr.close();
                if (br != null) br.close();
            } catch (Exception ignored) {}
        }
    }

    public void init() {
        try {
            s = new Socket("192.168.8.27", 1069);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
