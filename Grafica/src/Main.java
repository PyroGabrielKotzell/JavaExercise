package zuclib;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public final class GraficaSemplice implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
    static final String VERSIONE = "3.2";
    public static final Color BIANCO;
    public static final Color NERO;
    public static final Color GRIGIO_CHIARO;
    public static final Color GRIGIO;
    public static final Color GRIGIO_SCURO;
    public static final Color CELESTE;
    public static final Color BLU;
    public static final Color CIANO;
    public static final Color VERDE_CHIARO;
    public static final Color VERDE;
    public static final Color VERDE_SCURO;
    public static final Color GIALLO;
    public static final Color ROSA;
    public static final Color ROSSO;
    public static final Color MAGENTA;
    public static final Color ARANCIONE;
    public static final Color VIOLA;
    public static final Color INDACO;
    public static final Color MARRONE_CHIARO;
    public static final Color MARRONE;
    public static final Color MARRONE_SCURO;
    private static final Color[] colori;
    public static final int FRECCIA_SU = 38;
    public static final int FRECCIA_GIU = 40;
    public static final int FRECCIA_SX = 37;
    public static final int FRECCIA_DX = 39;
    public static final Tartaruga TARTA;
    static boolean modoXor;
    static final Color DEFAULT_PEN_COLOR;
    static final Color DEFAULT_CLEAR_COLOR;
    static Color penColor;
    static final int DEFAULT_SIZE = 512;
    static int width;
    static int height;
    static final double DEFAULT_PEN_RADIUS = 0.002;
    static double penRadius;
    static boolean defer;
    static final double BORDER = 0.0;
    static final String TITOLO = "Applicazione Grafica Semplice Zuccante";
    static final double DEFAULT_XMIN = 0.0;
    static final double DEFAULT_XMAX = 1.0;
    static final double DEFAULT_YMIN = 0.0;
    static final double DEFAULT_YMAX = 1.0;
    static double xmin;
    static double ymin;
    static double xmax;
    static double ymax;
    private static Object mouseLock;
    private static Object keyLock;
    static final Font DEFAULT_FONT;
    static Font font;
    static BufferedImage offscreenImage;
    static BufferedImage onscreenImage;
    static Graphics2D offscreen;
    static Graphics2D onscreen;
    private static GraficaSemplice std;
    public static JFrame frame;
    static boolean mousePressed;
    static double mouseX;
    static double mouseY;
    static int mouseIntX;
    static int mouseIntY;
    static LinkedList<Character> keysTyped;
    static TreeSet<Integer> keysDown;

    public static boolean isXorMode() {
        return modoXor;
    }

    public static void setXor() {
        modoXor = true;
        offscreen.setXORMode(BIANCO);
    }

    public static void setNonXor() {
        modoXor = false;
        offscreen.setPaintMode();
    }

    public static Color coloreACaso() {
        return colori[(int)(Math.random() * (double)colori.length)];
    }

    public static Color coloreRGB(int r, int g, int b) {
        return new Color(r % 255, g % 255, b % 255);
    }

    private GraficaSemplice() {
    }

    public static int larghezza() {
        return width;
    }

    public static int altezza() {
        return height;
    }

    public static void setFinestra() {
        setFinestra(512, 512, "Applicazione Grafica Semplice Zuccante");
    }

    public static void setFinestra(int larghezza, int altezza, String titolo) {
        if (larghezza >= 1 && altezza >= 1) {
            width = larghezza;
            height = altezza;
            init(titolo);
        } else {
            throw new IllegalArgumentException("larghezza e/o altezza non positive");
        }
    }

    static void init(String titolo) {
        if (frame != null) {
            frame.setVisible(false);
        }

        frame = new JFrame();
        offscreenImage = new BufferedImage(width, height, 2);
        onscreenImage = new BufferedImage(width, height, 2);
        offscreen = offscreenImage.createGraphics();
        onscreen = onscreenImage.createGraphics();
        setXscale();
        setYscale();
        offscreen.setColor(DEFAULT_CLEAR_COLOR);
        offscreen.fillRect(0, 0, width, height);
        setColore();
        setGrossezza();
        setFont();
        pulisci();
        ImageIcon icon = new ImageIcon(onscreenImage);
        JLabel draw = new JLabel(icon);
        draw.addMouseListener(std);
        draw.addMouseMotionListener(std);
        frame.setContentPane(draw);
        frame.addKeyListener(std);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setTitle(titolo);
        frame.setJMenuBar(createMenuBar());
        frame.pack();
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem1 = new JMenuItem(" Salva Immagine  ");
        menuItem1.addActionListener(std);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(83, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItem1);
        return menuBar;
    }

    static void setXscale() {
        setXscale(0.0, 1.0);
    }

    static void setYscale() {
        setYscale(0.0, 1.0);
    }

    static void setXscale(double min, double max) {
        double size = max - min;
        synchronized(mouseLock) {
            xmin = min - 0.0 * size;
            xmax = max + 0.0 * size;
        }
    }

    static void setYscale(double min, double max) {
        double size = max - min;
        synchronized(mouseLock) {
            ymin = min - 0.0 * size;
            ymax = max + 0.0 * size;
        }
    }

    static void setScale(double min, double max) {
        setXscale(min, max);
        setYscale(min, max);
    }

    static double scaleX(double x) {
        return (double)width * (x - xmin) / (xmax - xmin);
    }

    static double scaleY(double y) {
        return (double)height * (ymax - y) / (ymax - ymin);
    }

    static double factorX(double w) {
        return w * (double)width / Math.abs(xmax - xmin);
    }

    static double factorY(double h) {
        return h * (double)height / Math.abs(ymax - ymin);
    }

    static double userX(double x) {
        return xmin + x * (xmax - xmin) / (double)width;
    }

    static double userY(double y) {
        return ymax - y * (ymax - ymin) / (double)height;
    }

    public static void pulisci() {
        pulisci(DEFAULT_CLEAR_COLOR);
    }

    public static void pulisci(Color c) {
        offscreen.setColor(c);
        offscreen.fillRect(0, 0, width, height);
        offscreen.setColor(penColor);
        draw();
    }

    public static double getGrossezza() {
        return penRadius;
    }

    public static void setGrossezza() {
        setGrossezza(0.002);
    }

    public static void setGrossezza(double r) {
        if (r < 0.0) {
            throw new IllegalArgumentException("grossezza < 0");
        } else {
            penRadius = r;
            float scaledPenRadius = (float)(r * 512.0);
            BasicStroke stroke = new BasicStroke(scaledPenRadius, 1, 1);
            offscreen.setStroke(stroke);
        }
    }

    public static Color getColore() {
        return penColor;
    }

    public static void setColore() {
        setColore(DEFAULT_PEN_COLOR);
    }

    public static void setColore(Color c) {
        penColor = c;
        offscreen.setColor(penColor);
    }

    public static Font getFont() {
        return font;
    }

    public static void setFont() {
        setFont(DEFAULT_FONT);
    }

    public static void setFont(Font f) {
        font = f;
    }

    public static void linea(double x0, double y0, double x1, double y1) {
        offscreen.draw(new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
        draw();
    }

    public static void linea(double x0, double y0, double x1, double y1, Color col, double gros) {
        Color savedColor = penColor;
        double savedPenRadius = penRadius;
        setColore(col);
        setGrossezza(gros);
        offscreen.draw(new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
        draw();
        setColore(savedColor);
        penRadius = savedPenRadius;
    }

    private static void pixel(int x, int y) {
        offscreen.fillRect((int)Math.round(scaleX((double)x)), (int)Math.round(scaleY((double)y)), 1, 1);
    }

    public static void setPixel(int x, int y) {
        offscreen.fillRect(x, y, 1, 1);
        draw();
    }

    public static int getRGBcode(Color c) {
        return 65536 * c.getRed() + 256 * c.getGreen() + c.getBlue();
    }

    public static int getPixelRGBcode(int x, int y) {
        return offscreenImage.getRGB(x, y) & 16777215;
    }

    public static int getMouseRGBcode() {
        return getPixelRGBcode(mouseIntX, mouseIntY);
    }

    private static void pixel(double x, double y) {
        offscreen.fillRect((int)Math.round(scaleX(x)), (int)Math.round(scaleY(y)), 1, 1);
    }

    public static void punto(double x, double y) {
        double xs = scaleX(x);
        double ys = scaleY(y);
        double r = penRadius;
        float scaledPenRadius = (float)(r * 512.0);
        if (scaledPenRadius <= 1.0F) {
            pixel(x, y);
        } else {
            offscreen.fill(new Ellipse2D.Double(xs - (double)(scaledPenRadius / 2.0F), ys - (double)(scaledPenRadius / 2.0F), (double)scaledPenRadius, (double)scaledPenRadius));
        }

        draw();
    }

    public static void cerchio(double x, double y, double r) {
        if (r < 0.0) {
            throw new IllegalArgumentException("il raggio di un cerchio non può essere negativo");
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0 * r);
            double hs = factorY(2.0 * r);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.draw(new Ellipse2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs));
            }

            draw();
        }
    }

    public static void cerchioPieno(double x, double y, double r, Color c) {
        if (r < 0.0) {
            throw new RuntimeException("raggio < 0");
        } else {
            Color savedColor = penColor;
            setColore(c);
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0 * r);
            double hs = factorY(2.0 * r);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.fill(new Ellipse2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs));
            }

            draw();
            setColore(savedColor);
        }
    }

    public static void ellissePiena(double x, double y, double semiAsseMaggiore, double semiAsseMinore, Color c) {
        if (semiAsseMaggiore < 0.0) {
            throw new IllegalArgumentException("semiasse maggiore < 0");
        } else if (semiAsseMinore < 0.0) {
            throw new IllegalArgumentException("semiasse minore < 0");
        } else {
            Color savedColor = penColor;
            setColore(c);
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0 * semiAsseMaggiore);
            double hs = factorY(2.0 * semiAsseMinore);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.fill(new Ellipse2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs));
            }

            draw();
            setColore(savedColor);
        }
    }

    public static void ellisse(double x, double y, double semiAsseMaggiore, double semiAsseMinore) {
        if (semiAsseMaggiore < 0.0) {
            throw new IllegalArgumentException("semiasse maggiore < 0");
        } else if (semiAsseMinore < 0.0) {
            throw new IllegalArgumentException("semiasse minore < 0");
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0 * semiAsseMaggiore);
            double hs = factorY(2.0 * semiAsseMinore);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.fill(new Ellipse2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs));
            }

            draw();
        }
    }

    public static void arco(double x, double y, double r, double angolo1, double angolo2) {
        if (r < 0.0) {
            throw new IllegalArgumentException("raggio dell'arco < 0 ");
        } else {
            while(angolo2 < angolo1) {
                angolo2 += 360.0;
            }

            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(2.0 * r);
            double hs = factorY(2.0 * r);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.draw(new Arc2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs, angolo1, angolo2 - angolo1, 0));
            }

            draw();
        }
    }

    public static void quadrato(double x, double y, double lato) {
        if (lato < 0.0) {
            throw new IllegalArgumentException("La misura del lato è  < 0");
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(lato);
            double hs = factorY(lato);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.draw(new Rectangle2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs));
            }

            draw();
        }
    }

    public static void quadratoPieno(double x, double y, double lato, Color c) {
        if (lato < 0.0) {
            throw new IllegalArgumentException("misura lato < 0");
        } else {
            Color savedColor = penColor;
            setColore(c);
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(lato);
            double hs = factorY(lato);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.fill(new Rectangle2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs));
            }

            draw();
            setColore(savedColor);
        }
    }

    public static void rettangolo(double x, double y, double larghezza, double altezza) {
        if (larghezza < 0.0) {
            throw new IllegalArgumentException("la larghezza non può essere < 0");
        } else if (altezza < 0.0) {
            throw new IllegalArgumentException("l'altezza non può essere < 0");
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(larghezza);
            double hs = factorY(altezza);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.draw(new Rectangle2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs));
            }

            draw();
        }
    }

    public static void rettangoloPieno(double x, double y, double larghezza, double altezza, Color c) {
        if (larghezza < 0.0) {
            throw new IllegalArgumentException("la larghezza non può essere < 0");
        } else if (altezza < 0.0) {
            throw new IllegalArgumentException("l'altezza non può essere < 0");
        } else {
            Color savedColor = penColor;
            setColore(c);
            double xs = scaleX(x);
            double ys = scaleY(y);
            double ws = factorX(larghezza);
            double hs = factorY(altezza);
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            } else {
                offscreen.fill(new Rectangle2D.Double(xs - ws / 2.0, ys - hs / 2.0, ws, hs));
            }

            draw();
            setColore(savedColor);
        }
    }

    public static void poligono(double[] x, double[] y) {
        int N = x.length;
        GeneralPath path = new GeneralPath();
        path.moveTo((float)scaleX(x[0]), (float)scaleY(y[0]));

        for(int i = 0; i < N; ++i) {
            path.lineTo((float)scaleX(x[i]), (float)scaleY(y[i]));
        }

        path.closePath();
        offscreen.draw(path);
        draw();
    }

    public static void poligonoPieno(double[] x, double[] y, Color c) {
        Color savedColor = penColor;
        setColore(c);
        int N = x.length;
        GeneralPath path = new GeneralPath();
        path.moveTo((float)scaleX(x[0]), (float)scaleY(y[0]));

        for(int i = 0; i < N; ++i) {
            path.lineTo((float)scaleX(x[i]), (float)scaleY(y[i]));
        }

        path.closePath();
        offscreen.fill(path);
        draw();
        setColore(savedColor);
    }

    static Image getImage(String filename) {
        ImageIcon icon = new ImageIcon(filename);
        URL url;
        if (icon == null || icon.getImageLoadStatus() != 8) {
            try {
                url = new URL(filename);
                icon = new ImageIcon(url);
            } catch (Exception var3) {
            }
        }

        if (icon == null || icon.getImageLoadStatus() != 8) {
            url = GraficaSemplice.class.getResource("/" + filename);
            if (url == null) {
                throw new RuntimeException("immagine " + filename + " non trovata");
            }

            icon = new ImageIcon(url);
        }

        return icon.getImage();
    }

    public static void disegnaImmagine(double x, double y, String s, double gradi) {
        Image image = getImage(s);
        double xs = scaleX(x);
        double ys = scaleY(y);
        int ws = image.getWidth((ImageObserver)null);
        int hs = image.getHeight((ImageObserver)null);
        if (ws >= 0 && hs >= 0) {
            offscreen.rotate(Math.toRadians(-gradi), xs, ys);
            offscreen.drawImage(image, (int)Math.round(xs - (double)ws / 2.0), (int)Math.round(ys - (double)hs / 2.0), (ImageObserver)null);
            offscreen.rotate(Math.toRadians(gradi), xs, ys);
            draw();
        } else {
            throw new RuntimeException("l'immagine" + s + " è corrotta");
        }
    }

    public static void disegnaImmagineRidimensionata(double x, double y, String percorso, double gradi, double largh, double alt) {
        Image image = getImage(percorso);
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(largh);
        double hs = factorY(alt);
        if (!(ws < 0.0) && !(hs < 0.0)) {
            if (ws <= 1.0 && hs <= 1.0) {
                pixel(x, y);
            }

            offscreen.rotate(Math.toRadians(-gradi), xs, ys);
            offscreen.drawImage(image, (int)Math.round(xs - ws / 2.0), (int)Math.round(ys - hs / 2.0), (int)Math.round(ws), (int)Math.round(hs), (ImageObserver)null);
            offscreen.rotate(Math.toRadians(gradi), xs, ys);
            draw();
        } else {
            throw new RuntimeException("l'immagine " + percorso + "è corrotta");
        }
    }

    public static void testo(double x, double y, String s) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            offscreen.setFont(font);
            FontMetrics metrics = offscreen.getFontMetrics();
            double xs = scaleX(x);
            double ys = scaleY(y);
            int ws = metrics.stringWidth(s);
            int hs = metrics.getDescent();
            offscreen.drawString(s, (float)(xs - (double)ws / 2.0), (float)(ys + (double)hs));
            draw();
        }
    }

    public static void testo(double x, double y, String s, double gradi, Color c) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            double xs = scaleX(x);
            double ys = scaleY(y);
            Color savedColor = penColor;
            setColore(c);
            offscreen.rotate(Math.toRadians(-gradi), xs, ys);
            testo(x, y, s);
            offscreen.rotate(Math.toRadians(gradi), xs, ys);
            setColore(savedColor);
        }
    }

    public static void testoSinistra(double x, double y, String s) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            offscreen.setFont(font);
            FontMetrics metrics = offscreen.getFontMetrics();
            double xs = scaleX(x);
            double ys = scaleY(y);
            int hs = metrics.getDescent();
            offscreen.drawString(s, (float)xs, (float)(ys + (double)hs));
            draw();
        }
    }

    public static void testoDestra(double x, double y, String s) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            offscreen.setFont(font);
            FontMetrics metrics = offscreen.getFontMetrics();
            double xs = scaleX(x);
            double ys = scaleY(y);
            int ws = metrics.stringWidth(s);
            int hs = metrics.getDescent();
            offscreen.drawString(s, (float)(xs - (double)ws), (float)(ys + (double)hs));
            draw();
        }
    }

    static void draw() {
        onscreen.drawImage(offscreenImage, 0, 0, (ImageObserver)null);
        frame.repaint();
    }

    public static void salvaDisegno(String nomefile) {
        File file = new File(nomefile);
        String suffix = nomefile.substring(nomefile.lastIndexOf(46) + 1);
        if (suffix.toLowerCase().equals("png")) {
            try {
                ImageIO.write(onscreenImage, suffix, file);
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        } else if (suffix.toLowerCase().equals("jpg")) {
            WritableRaster raster = onscreenImage.getRaster();
            WritableRaster newRaster = raster.createWritableChild(0, 0, width, height, 0, 0, new int[]{0, 1, 2});
            DirectColorModel cm = (DirectColorModel)onscreenImage.getColorModel();
            DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(), cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());
            BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster, false, (Hashtable)null);

            try {
                ImageIO.write(rgbBuffer, suffix, file);
            } catch (IOException var9) {
                var9.printStackTrace();
            }
        } else {
            System.out.println("Invalid image file type: " + suffix);
        }

    }

    public void actionPerformed(ActionEvent e) {
        FileDialog chooser = new FileDialog(frame, "Scelgliere estensione .png o .jpg ", 1);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            salvaDisegno(chooser.getDirectory() + File.separator + chooser.getFile());
        }

    }

    public static boolean mousePremuto() {
        synchronized(mouseLock) {
            return mousePressed;
        }
    }

    public static double mouseX() {
        synchronized(mouseLock) {
            return mouseX;
        }
    }

    public static double mouseY() {
        synchronized(mouseLock) {
            return mouseY;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        synchronized(mouseLock) {
            mouseIntX = e.getX();
            mouseIntY = e.getY();
            mousePressed = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        synchronized(mouseLock) {
            mousePressed = false;
        }
    }

    public void mouseDragged(MouseEvent e) {
        synchronized(mouseLock) {
            mouseX = userX((double)e.getX());
            mouseY = userY((double)e.getY());
        }
    }

    public void mouseMoved(MouseEvent e) {
        synchronized(mouseLock) {
            mouseX = userX((double)e.getX());
            mouseY = userY((double)e.getY());
        }
    }

    public static boolean tastoPremuto() {
        synchronized(keyLock) {
            return !keysTyped.isEmpty();
        }
    }

    public static char tasto() {
        synchronized(keyLock) {
            if (keysTyped.isEmpty()) {
                throw new RuntimeException("il programma ha gia` elaborato tutti le pressioni dei tasti");
            } else {
                return (Character)keysTyped.removeLast();
            }
        }
    }

    public static boolean premutoTasto(int keycode) {
        synchronized(keyLock) {
            return keysDown.contains(keycode);
        }
    }

    public void keyTyped(KeyEvent e) {
        synchronized(keyLock) {
            keysTyped.addFirst(e.getKeyChar());
        }
    }

    public void keyPressed(KeyEvent e) {
        synchronized(keyLock) {
            keysDown.add(e.getKeyCode());
        }
    }

    public void keyReleased(KeyEvent e) {
        synchronized(keyLock) {
            keysDown.remove(e.getKeyCode());
        }
    }

    static {
        BIANCO = Color.WHITE;
        NERO = Color.BLACK;
        GRIGIO_CHIARO = Color.LIGHT_GRAY;
        GRIGIO = Color.GRAY;
        GRIGIO_SCURO = Color.DARK_GRAY;
        CELESTE = new Color(153, 203, 255);
        BLU = Color.BLUE;
        CIANO = Color.CYAN;
        VERDE_CHIARO = new Color(127, 255, 0);
        VERDE = Color.GREEN;
        VERDE_SCURO = new Color(0, 128, 0);
        GIALLO = Color.YELLOW;
        ROSA = Color.PINK;
        ROSSO = Color.RED;
        MAGENTA = Color.MAGENTA;
        ARANCIONE = new Color(255, 165, 0);
        VIOLA = new Color(143, 0, 255);
        INDACO = new Color(75, 0, 130);
        MARRONE_CHIARO = new Color(205, 133, 63);
        MARRONE = new Color(150, 75, 0);
        MARRONE_SCURO = new Color(101, 67, 33);
        colori = new Color[]{BIANCO, NERO, GRIGIO_CHIARO, GRIGIO, GRIGIO_SCURO, CELESTE, BLU, CIANO, VERDE_CHIARO, VERDE, VERDE_SCURO, GIALLO, ROSA, ROSSO, MAGENTA, ARANCIONE, VIOLA, INDACO, MARRONE_CHIARO, MARRONE, MARRONE_SCURO};
        TARTA = new Tartaruga();
        modoXor = false;
        DEFAULT_PEN_COLOR = NERO;
        DEFAULT_CLEAR_COLOR = BIANCO;
        width = 512;
        height = 512;
        defer = false;
        mouseLock = new Object();
        keyLock = new Object();
        DEFAULT_FONT = new Font("Times", 0, 16);
        std = new GraficaSemplice();
        mousePressed = false;
        mouseX = 0.0;
        mouseY = 0.0;
        mouseIntX = 0;
        mouseIntY = 0;
        keysTyped = new LinkedList();
        keysDown = new TreeSet();
        init("Applicazione Grafica Semplice Zuccante");
    }
}