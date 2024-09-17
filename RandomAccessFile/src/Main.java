public class Main {

    public static void main(String[] args) {
        try {
            RandomAccessFileManager file1 = new RandomAccessFileManager("dati.dat", "rw");
            RandomAccessFileManager file2 = new RandomAccessFileManager("result.dat", "rw");

            for (int i = 0; i < 20; i++) {
                file1.index(i * 75);
                String str = new String(file1.read(25));
                String codiceStringa = str.substring(0, 5);
                String codice = String.format("%05d", (Integer.parseInt(codiceStringa) - 1) * 75);
                String nome = str.substring(15, 25);

                file2.write(nome + "" + codice);
                if (i < 19)
                    file2.write("\n");
            }

            file1.index(0);
            file2.index(0);

            String line = file2.findLine("aaaa", 16);
            if (line == "") {
                System.out.println("Non ho trovato x");
                return;
            }

            long j = Integer.parseInt(line.substring(10, 15));

            file1.index(j);
            String datas = new String(file1.read(75));
            System.out.print(datas);

            file1.close();
            file2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}