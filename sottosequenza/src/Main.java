public class Main {
    public static void main(String[] args) {
        int[] S1 = {6, 0, 7, 3, 7}, S2={7, 3};
        for (int j : S1) {
            System.out.print(j + " ");
        }
        System.out.println();
        for (int j : S2) {
            System.out.print(j + " ");
        }
        boolean vero = true;
        boolean trovatoAssoluto = false;
        System.out.println("\nsottosequenza:");
        for (int i = 0; i < (S1.length-(S2.length-1)); i++) {
            for (int j = 0; j < S2.length; j++) {
                vero = S1[i + j] == S2[j];
            }
            if (vero){
                System.out.println("sequenza vera a indice: "+i);
                trovatoAssoluto = true;
            }
        }
        if (!trovatoAssoluto) System.out.println("nessuna sottosequenza");
    }
}