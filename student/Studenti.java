public class Studenti{
	String nome;
	String cognome;
	int eta;
	float altezza;
	float peso;
	Studenti(String nome, String cognome, int eta, float altezza, float peso){
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.altezza = altezza;
		this.peso = peso;
	}
	public void ToString(){
		System.out.println("nome: " + nome + "\ncognome: " + cognome + "\neta: " + eta + "\naltezza: " + altezza + "\npeso: " + peso);
	}
}