import zuclib.*;

public class Poligono {
    int lati;
    double lunghezza;

    Poligono(double lunghezza, int lati){
        this.lunghezza = lunghezza;
        this.lati = lati;
    }

    double perimetro(){
        return (this.lunghezza*this.lati);
    }

    void disegna(Tartaruga t){
        (double)(360/this.lati)
    }
}
