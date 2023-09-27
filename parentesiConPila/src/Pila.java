import java.util.ArrayList;
public class Pila {
    private ArrayList<Character> caratteri = new ArrayList<>();

    public void push(char c){
        caratteri.add(c);
    }

    public char pop(){
        char c = caratteri.get(caratteri.size()-1);
        caratteri.remove(caratteri.size()-1);
        return c;
    }
}
