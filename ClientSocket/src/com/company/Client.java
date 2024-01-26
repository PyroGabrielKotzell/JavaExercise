package com.company;

import java.net.Socket;

public class Client {
    private Socket s;
    private boolean n1 = false, n2 = false;

    public void write(String string) {
        try {
            SocketIO.pw.write(string);
        } catch (Exception e) {
            if (!n2) {
                e.printStackTrace();
                n2 = true;
            }
        }
    }

    public String read() {
        try {
            return SocketIO.br.readLine();
        } catch (Exception e) {
            if (!n2) {
                e.printStackTrace();
                n2 = true;
            }
            return "";
        }
    }

    public void init() {
        try {
            s = new Socket("192.168.8.27", 1069);
            SocketIO.init(s);
        } catch (Exception e) {
            if (!n1) {
                e.printStackTrace();
                n1 = true;
            }
        }
    }

    public void close() {
        try {
            s.close();
            SocketIO.close();
        } catch (Exception e) {
            if (!n1) {
                e.printStackTrace();
                n1 = true;
            }
        }
    }
}
