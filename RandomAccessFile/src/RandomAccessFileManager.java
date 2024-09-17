import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileManager {
    private RandomAccessFile raf;

    public RandomAccessFileManager(String file, String permissions) {
        try {
            raf = new RandomAccessFile(file, permissions);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found - " + e.getMessage());
        }
    }

    public long length() {
        try {
            return raf.length();
        } catch (IOException e) {
            System.out.println("File was not instantiated - " + e.getMessage());
        }
        return -1;
    }

    public void index(long index) {
        try {
            raf.seek(index);
        } catch (IOException e) {
            System.out.println("File was not instantiated - " + e.getMessage());
        }
    }

    public byte[] read(int length) {
        try {
            byte[] b = new byte[length];
            raf.read(b);
            return b;
        } catch (IOException e) {
            System.out.println("File was not instantiated - " + e.getMessage());
        }
        return null;
    }

    public void write(String string) {
        try {
            raf.write(string.getBytes());
        } catch (IOException e) {
            System.out.println("File was not instantiated - " + e.getMessage());
        }
    }

    public void transfer(String file, long start, long end) {
        if (start >= end)
            return;
        RandomAccessFileManager file1 = new RandomAccessFileManager(file, "rw");
        index(start);
        String s = new String(read((int) (end - start)));
        file1.write(s);
    }

    public long find(String key, int lineLength) {
        index(0);
        int index = 1;
        String s = new String(read(lineLength));
        while (s != null) {
            if (s.contains(key)) {
                return index;
            }
            index++;
            s = new String(read(lineLength));
        }
        return -1;
    }

    public String findLine(String key, int lineLength) {
        index(0);
        String s = new String(read(lineLength));
        while (s != null) {
            if (s.contains(key)) {
                return s;
            }
            s = new String(read(lineLength));
        }
        return "";
    }

    public void close() {
        try {
            raf.close();
        } catch (IOException e) {
            System.out.println("File was not instantiated - " + e.getMessage());
        }
    }
}
