import java.io.RandomAccessFile;

public class Main {

    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("file1", "rw");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}