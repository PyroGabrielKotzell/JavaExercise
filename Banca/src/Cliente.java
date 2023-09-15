import java.util.ArrayList;

public class Cliente {
    private final String nome, cognome, codiceFiscale;
    private final ArrayList<Trasferimento> listaMovimenti = new ArrayList<>();

    public Cliente(String nome, String cognome, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }

    public void nuovoTrasferimento(Trasferimento t) {
        listaMovimenti.add(t);
    }

    public void eliminaTrasferimento(int n) {
        listaMovimenti.remove(n);
    }

    public double calcBilancio() {
        double denaro = 0;
        for (Trasferimento trasferimento : listaMovimenti) {
            denaro = denaro + trasferimento.getDenaro();
        }
        return denaro;
    }

    public void printIstogramma() {
        int[][] mat = new int[11][11];
        int denaromin = (int) listaMovimenti.get(0).getDenaro(), denaromax = (int) listaMovimenti.get(0).getDenaro();
        int annomin = listaMovimenti.get(0).getAnno(), annomax = listaMovimenti.get(0).getAnno();
        for (Trasferimento trasferimento : listaMovimenti) {
            if (trasferimento.getDenaro() < denaromin) denaromin = (int) trasferimento.getDenaro();
            else if (trasferimento.getDenaro() > denaromax) denaromax = (int) trasferimento.getDenaro();
            if (trasferimento.getAnno() < annomin) annomin = trasferimento.getAnno();
            else if (trasferimento.getAnno() > annomax) annomax = trasferimento.getAnno();
        }
        mat[0][mat.length / 2] = 0;
        int denarosum = (denaromax - denaromin) / 10;
        if (denarosum < 0) denarosum = -denarosum;
        System.out.println("denarosum: " + denarosum);
        int annosum = (annomax - annomin) / 10;
        if (annosum < 0) annosum = -annosum;
        System.out.println("annosum: " + annosum);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (i != (mat.length / 2) && j == 0) {
                    mat[i][j] = denaromax - (denarosum * i);
                } else if (i == (mat.length / 2) && j != 0) {
                    mat[i][j] = annomin + (annosum * j);
                }
            }
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                for (Trasferimento t : listaMovimenti) {
                    mat[isDenaroNearest(t, mat)][isAnnoNearest(t, mat)] = 1;
                }
                if (mat[i][j] == 0 && (j != 0 || i != (mat.length / 2))) System.out.print("     ");
                else if (mat[i][j] == 1 && (j != 0 || i != (mat.length / 2))) System.out.print("  X  ");
                else System.out.print(mat[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private int isAnnoNearest(Trasferimento t, int[][] mat) {
        int distance = Math.abs(mat[0][0] - t.getAnno());
        int idx = 0;
        for (int c = 1; c < mat.length; c++) {
            for (int j = 0; j < mat[c].length; j++) {
                int cdistance = Math.abs(mat[mat.length/2][j] - t.getAnno());
                if (cdistance < distance) {
                    idx = j;
                    distance = cdistance;
                }
            }
        }
        return idx;
    }

    private int isDenaroNearest(Trasferimento t, int[][] mat) {
        int den = (int)t.getDenaro();
        int distance = Math.abs(mat[0][0] - den);
        int idx = 0;
        for (int c = 1; c < mat.length; c++) {
            for (int j = 0; j < mat[c].length; j++) {
                int cdistance = Math.abs(mat[c][0] - den);
                if (cdistance < distance) {
                    idx = c;
                    distance = cdistance;
                    break;
                }
            }
        }
        return idx;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public ArrayList<Trasferimento> getListaMovimenti() {
        return listaMovimenti;
    }
}
