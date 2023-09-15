public class Main {
    public static void main(String[] args) {
        int[] N = new int[10];
        for (int i = 0; i < N.length; i++) {
            N[i] = (int)(Math.random()*21);
        }
        System.out.print("Array animali: ");
        for (int j : N) {
            System.out.print(j + " ");
        }
        System.out.println();
        int num = 0;
        int max = N.length-1;
        for (int i = N.length-1; i > -1; i--) {
            if (N[i] < N[max]){
                num++;
            }else{
                max = i;
            }
        }
        System.out.println("\nNumero animali: " + (N.length-num));
    }
}