public class Main {

    public static void main(String[] args) {
        try {
            RandomAccessFileManager file1 = new RandomAccessFileManager("dati.dat", "rw");
            RandomAccessFileManager file2 = new RandomAccessFileManager("result.dat", "rw");

            for (int i = 0; i < 20; i++) {
                file1.index(i * 75);
                String str = new String(file1.read(25));

                String codice = str.substring(0, 5);
                String nome = str.substring(15, 25);

                file2.write(nome + "" + codice);
                if (i < 19)
                    file2.write("\n");
            }

            file1.index(0);
            file2.index(0);

            long j = file2.find("LUCIA", 16);

            file1.index((j - 1) * 75);
            String datas = new String(file1.read(75));
            System.out.print(datas);

            file1.close();
            file2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}