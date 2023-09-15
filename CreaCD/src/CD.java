public class CD {
    String nome, autore, titolo, casa, proprietario;
    CD(String nome, String autore, String titolo, String casa, String proprietario){
        this.nome = nome;
        this.autore = autore;
        this.titolo = titolo;
        this.casa = casa;
        this.proprietario = proprietario;
    }
    void stampaCD(){
        System.out.println(getNome() + ", " + getAutore() + ", " + getCasa() + ", " + getTitolo());
    }

    public String getNome() {
        return nome;
    }

    public String getAutore() {
        return autore;
    }

    public String getCasa() {
        return casa;
    }

    public String getProprietario() {
        return proprietario;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }
}
