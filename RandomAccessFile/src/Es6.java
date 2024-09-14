import java.io.*;

public class Es6 {

  public static void main(String args[]) throws Exception {
    RandomAccessFile raf = new RandomAccessFile("dati.dat", "rw");
    RandomAccessFile raf2 = new RandomAccessFile("copia.dat", "rw");

    byte[] b = new byte[15]; // 15 codice + nome
    byte[] c = new byte[60]; // 60 resto

    raf.seek(0);

    int s = raf.read(b);
    int j = raf.read(c);
    while (s >= 0 && j >= 0) {
      String str = new String(b);
      String codice = str.substring(0, 5);
      String nome = str.substring(5);
      System.out.println(nome + " " + codice);
      raf2.write((nome + " " + codice).getBytes());
      if (!codice.equals("00020"))
        raf2.write("\n".getBytes());
      s = raf.read(b);
      j = raf.read(c);
    }
    raf.close();
    raf2.close();
  }
}