import java.io.*;

public class GestoreFiles {
    private final File file;
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;

    GestoreFiles(String file) {
        this.file = new File(file);
    }

    GestoreFiles(File file) {
        this.file = file;
    }

    public void createFile() {
        if (file.exists()) file.delete();
        try {
            file.createNewFile();
        } catch (Exception ignored) {
        }
    }

    public boolean readerReady(){
        try {
            return br.ready();
        }catch (Exception ignored){
        }
        return false;
    }

    public void open() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
        } catch (Exception ignored) {
        }
    }

    public void close() {
        try {
            fr.close();
            br.close();
            fw.close();
            bw.close();
        } catch (Exception ignored) {
        }
    }

    public String readln() {
        try {
            return br.readLine();
        }catch (Exception ignored){}
        return null;
    }

    public void write(String string) {
        try {
            bw.write(string);
            bw.flush();
        }catch (Exception ignored){}
    }

    public File getFile() {
        return file;
    }
}
