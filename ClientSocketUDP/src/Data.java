public class Data {
    private String nazione, capitale;

    public Data(String nazione, String capitale) {
        this.nazione = nazione;
        this.capitale = capitale;
    }

    public String getNazione() {
        return nazione;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public String getCapitale() {
        return capitale;
    }

    public String toString() {
        return nazione + ";" + capitale;
    }
}
