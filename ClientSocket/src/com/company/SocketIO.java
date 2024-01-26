package com.company;

import java.io.*;
import java.net.Socket;

public abstract class SocketIO {
    private static InputStreamReader isr;
    public static BufferedReader br;
    private static OutputStreamWriter osw;
    private static BufferedWriter bw;
    public static PrintWriter pw;

    public static void init(Socket s) throws IOException {
        isr = new InputStreamReader(s.getInputStream());
        br = new BufferedReader(isr);
        osw = new OutputStreamWriter(s.getOutputStream());
        bw = new BufferedWriter(osw);
        pw = new PrintWriter(bw, true);
    }

    public static void close() throws IOException {
        br.close();
        isr.close();
        pw.close();
        bw.close();
        osw.close();
    }
}
