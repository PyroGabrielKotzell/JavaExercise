package com.company;

import java.net.Socket;
import java.net.SocketException;

public class Client {
    private Socket s;

    public void write(String string) {
        try {
            SocketIO.pw.write(string + '\n');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String read() {
        try {
            String s = SocketIO.br.readLine();
            System.out.println(s);
            return (s == null ? "" : s);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setTimeOut(int n) throws SocketException {
        s.setSoTimeout(n);
    }

    public boolean isConnected() {
        return s.isConnected();
    }

    public void init() {
        try {
            s = new Socket("192.168.8.27", 1069);
            if (s.isConnected()) System.out.println("Socket connected");
            SocketIO.init(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            s.close();
            SocketIO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
