import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = 0;
        while (true) {
            System.out.print("dammi un numero minore di dieci\n>");
            try {
                n = sc.nextInt();
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Not a number: NAN");
                sc = new Scanner(System.in);
            }
        }
        int[][] arr = new int[n + 1][n + 1];
        if (n > 0) {
            int a = 1;
            for (int i = 0; i < n + 1; i++) {

                for (int j = n + 1; j >= i; j--) {
                    System.out.print(" ");
                }
                for (int j = 0; j < i; j++) {
                    if (j == 0 || j == i - 1) {
                        System.out.print(a);
                        arr[i][j] = a;
                    } else {
                        arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                        System.out.print(arr[i][j]);
                    }
                    System.out.printf("%8d", arr[i][j]);
                }
                System.out.println();
            }
        }
    }
}