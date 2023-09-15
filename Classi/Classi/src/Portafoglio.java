public class Portafoglio extends PortaMonete {
    private int fogliCinq, fogliDiec, fogliVent;

    public Portafoglio() {
        super();
        fogliCinq = 0;
        fogliDiec = 0;
        fogliVent = 0;
    }

    public Portafoglio(int c50, int m1, int m2, int f5, int f10, int f20) {
        super(c50, m1, m2);
        fogliCinq = f5;
        fogliDiec = f10;
        fogliVent = f20;
    }

    public void banconote() {
        System.out.println("Cinque Euro: " + fogliCinq + " Dieci Euro: " + fogliDiec + " Venti Euro: " + fogliVent);
        int n = 0;
        for (int i = 0; i < fogliCinq; i++) {
            n += 5;
        }
        for (int i = 0; i < fogliDiec; i++) {
            n += 10;
        }
        for (int i = 0; i < fogliVent; i++) {
            n += 20;
        }
        System.out.println("Monete: € " + n);
    }

    public double denaro() {
        System.out.println("Centesimi: " + getMonCinqCent() + " Un Euro: " + getMonUnEur() + " Due Euro: " + getMonDueEur() + "\nCinque Euro: " + fogliCinq + " Dieci Euro: " + fogliDiec + " Venti Euro: " + fogliVent);
        double n = super.denaro();
        for (int i = 0; i < fogliCinq; i++) {
            n += 5;
        }
        for (int i = 0; i < fogliDiec; i++) {
            n += 10;
        }
        for (int i = 0; i < fogliVent; i++) {
            n += 20;
        }
        System.out.println("Monete: € " + n);

        return n;
    }

    public void inserisci(double valore) {
        if (valore == 0.5 || valore == 1 || valore == 2) super.inserisci(valore);
        else if (valore == 5) fogliCinq++;
        else if (valore == 10) fogliDiec++;
        else if (valore == 20) fogliVent++;
        else System.out.println("Non è un valore valido");
    }

    public void inserisci(double valore, int n) {
        if (valore == 0.5 || valore == 1 || valore == 2) super.inserisci(valore, n);
        else if (valore == 5) fogliCinq += n;
        else if (valore == 10) fogliDiec += n;
        else if (valore == 20) fogliVent += n;
        else System.out.println("Non è un valore valido");
    }

    public int getFogliCinq() {
        return fogliCinq;
    }

    public int getFogliDiec() {
        return fogliDiec;
    }

    public int getFogliVent() {
        return fogliVent;
    }

    public void setFogliCinq(int fogliCinq) {
        this.fogliCinq = fogliCinq;
    }

    public void setFogliDiec(int fogliDiec) {
        this.fogliDiec = fogliDiec;
    }

    public void setFogliVent(int fogliVent) {
        this.fogliVent = fogliVent;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
