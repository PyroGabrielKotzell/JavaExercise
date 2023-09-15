public class Trasferimento {
    private final int anno;
    private final double Denaro;
    private final Cliente cliente;

    public Trasferimento(int anno, double denaro, Cliente cliente) {
        this.anno = anno;
        Denaro = denaro;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getDenaro() {
        return Denaro;
    }

    public int getAnno() {
        return anno;
    }
}
