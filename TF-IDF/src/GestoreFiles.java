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
            fw = new FileWriter(file);
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

    public String readln() throws IOException {
        return br.readLine();
    }

    public void write(String string) throws IOException {
        bw.write(string);
        bw.flush();
    }

    public File getFile() {
        return file;
    }
}
