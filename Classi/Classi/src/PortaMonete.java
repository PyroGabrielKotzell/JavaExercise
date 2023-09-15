public class PortaMonete {
    private int monCinqCent, monUnEur, monDueEur;

    public PortaMonete() {
        monCinqCent = 0;
        monDueEur = 0;
        monUnEur = 0;
    }

    public PortaMonete(int cent, int eur1, int eur2) {
        monCinqCent = cent;
        monDueEur = eur2;
        monUnEur = eur1;
    }

    public void inserisci(double valore) {
        if (valore == 0.5) monCinqCent++;
        else if (valore == 1) monUnEur++;
        else if (valore == 2) monDueEur++;
        else System.out.println("Non è una moneta");
    }

    public void inserisci(double valore, int n) {
        if (valore == 0.5) monCinqCent += n;
        else if (valore == 1) monUnEur += n;
        else if (valore == 2) monDueEur += n;
        else System.out.println("Non è una moneta");
    }

    public double denaro() {
        System.out.println("Centesimi: " + monCinqCent + " Un Euro: " + monUnEur + " Due Euro: " + monDueEur);
        double n = 0;
        for (int i = 0; i < monCinqCent; i++) {
            n += 0.5;
        }
        for (int i = 0; i < monDueEur; i++) {
            n += 1;
        }
        for (int i = 0; i < monUnEur; i++) {
            n += 2;
        }
        System.out.println("Monete: €" + n);
        return n;
    }

    public int getMonCinqCent() {
        return monCinqCent;
    }

    public int getMonDueEur() {
        return monDueEur;
    }

    public int getMonUnEur() {
        return monUnEur;
    }

    public void setMonCinqCent(int monCinqCent) {
        this.monCinqCent = monCinqCent;
    }

    public void setMonDueEur(int monDueEur) {
        this.monDueEur = monDueEur;
    }

    public void setMonUnEur(int monUnEur) {
        this.monUnEur = monUnEur;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
