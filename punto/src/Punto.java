public class Punto {
    float x, y;
    Punto(float x, float y){
        this.x = x;
        this.y = y;
    }
    Punto SommaPunto(Punto punto){
        return new Punto(this.x+punto.x, this.y + punto.y);
    }

    int Quadrante(){
        if (this.x >=0 && this.y >= 0) return 1;
        else if (this.x < 0 && this.y >= 0) return 2;
        else if (this.x >=0 && this.y < 0) return 4;
        else return 3;
    }

    float Distanza(Punto punto){
        return ((float)Math.sqrt(Math.pow(this.x - punto.x, 2) + Math.pow(this.y - punto.y, 2)));
    }

    Punto Opposto(){
        return new Punto(-(this.x), -(this.y));
    }

    void StampaPunto(){
        System.out.println("(" + this.x + ", " + this.y + ")");
    }
}
