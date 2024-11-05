import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Arrays;

public abstract class FileHandler {
    private static File f = null;
    private static BufferedReader br = null;

    public static void grabFile(String filepath) {
        f = new File(filepath);
        if (!f.exists() || f.isDirectory()) {
            System.out.println("File not found");
            f = null;
            return;
        }
    }

    public static boolean checkFileFields(String[] fields) {
        if (f == null) {
            System.out.println("No file instantiated");
            return false;
        }
        try {
            if (br == null)
                br = new BufferedReader(new FileReader(f));
            String[] fFields = br.readLine().split(",");
            if (Arrays.asList(fields).containsAll(Arrays.asList(fFields)) && fields.length == fFields.length)
                return true;
            else {
                System.out.println("This table is not compatible with this file's values");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occourred " + e.getMessage());
            return false;
        }
    }

    public static String getNextValues() {
        try {
            return br.readLine();
        } catch (Exception e) {
            System.out.println("An error occourred " + e.getMessage());
            return null;
        }
    }

    public static boolean hasNext() {
        try {
            return br.ready();
        } catch (Exception e) {
            System.out.println("An error occourred " + e.getMessage());
            return false;
        }
    }
}
