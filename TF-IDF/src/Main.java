import java.io.File;

public class Main {

    public static void main(String[] args) {
        // prendo il testo dei file //
        // gestore files
        String[] files = new File("./src/files").list();
        for (String nome: files) {
            System.out.println(tfIdf(new File(""), gestore));
        }
    }

    private static int tfIdf(File doc, String ter) {

        return (int)(Math.log10(idf)*tf);
    }
}