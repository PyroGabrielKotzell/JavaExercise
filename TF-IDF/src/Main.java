import java.io.File;

public class Main {

    public static void main(String[] args) {
        File[] files = new File("./src/files/").listFiles();

        assert files != null;
        for (File f : files) {
            GestoreFiles g = new GestoreFiles(f);
            System.out.println(tfIdf());
        }
    }

    private static int tfIdf(File doc, String termine) {
        float tf = calcTf();
        float idf = calcIdf();
        return (int) (Math.log10(idf) * tf);
    }

    private static float calcIdf() {
    }

    private static float calcTf() {
    }
}