import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    private static final JFrame frame = new JFrame();
    private static final JPanel p = new JPanel();
    public static void main(String[] args) throws Exception {
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setName("Labirinto");
        frame.setSize(900, 900);
        frame.setBounds(1, 1, 900, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(p);
        p.setVisible(true);
        File fileLab = new File("src/labirinto.txt");
        FileReader fr = new FileReader(fileLab);
        BufferedReader bfr = new BufferedReader(fr);
        String[][] labirinto = {};
        int height = 0, width = 0;
        if (bfr.ready()) {
            height = countLines(fileLab);
            width = countWidth(fileLab);
            System.out.println("h: " + height + "\nw: " + width);
            labirinto = new String[height][width];
            for (int i = 0; i < labirinto.length; i++) {
                String tmp = bfr.readLine();
                for (int j = 0; j < tmp.length(); j++) {
                    labirinto[i][j] = tmp.charAt(j)+"";
                    System.out.print(labirinto[i][j]);
                }
                System.out.println();
            }
        }
        if (labirinto.length != 0) disegnaLabirinto(labirinto, height, width);
        else System.out.println("Non c'è nessun labirinto da esplorare!");
    }

    private static void disegnaLabirinto(String[][] labirinto, int height, int width) {
        Graphics g = p.getGraphics();
        int lenght = 900/height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (labirinto[i][j].charAt(0)){
                    case 'X' -> {
                        g.setColor(Color.BLACK);
                        g.fillRect(i*lenght, j*lenght, lenght, lenght);
                    }
                    case 'E' -> {
                        g.setColor(Color.GREEN);
                        g.fillRect(i*lenght, j*lenght, lenght, lenght);
                    }
                    case 'U' -> {
                        g.setColor(Color.MAGENTA);
                        g.fillRect(i*lenght, j*lenght, lenght, lenght);
                    }
                }
            }
        }
    }

    private static int countWidth(File f) throws Exception {
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String s = "";
        if (bfr.ready()) s = bfr.readLine();
        while (bfr.ready()) {
            String s2 = bfr.readLine();
            if (s2.length() > s.length()) s = s2;
        }
        return s.length();
    }

    private static int countLines(File f) throws Exception {
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        int n = 0;
        while (bfr.ready()) {
            bfr.readLine();
            n++;
        }
        return n;
    }
}