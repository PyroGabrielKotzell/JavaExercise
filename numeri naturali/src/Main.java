public class Main {
    public static void main(String[] args) {
        int n = 10;
        int p = -3;
        int a = 12, b = 4;
        double x = 1;
        int rad = 2;     // 1.414213562373095 6
        int livMigl = 2; // 1.4166666666666665 2
        System.out.println("somma dei primi " + n + " numeri naturali: " + somma(n));
        System.out.print("ricorsione di " + n + " alla " + p + ": ");
        System.out.println(pot(n, p) + ";");
        System.out.print("somma di " + a + " + " + b + ": " + sommadamatti(a, b));
        System.out.println("\nradice di " + rad + ": " + radice(x, rad, livMigl));
    }

    private static double radice(double x, int rad, int n) {
        if (n == 0) return x;
        return radice(((x + (rad / x)) / rad), rad, n - 1);
    }

    private static int sommadamatti(int a, int b) {
        if (a == 0) return sommadamatti(a + 1, b - 1);
        if (b == 0) return 1;
        return 1 + sommadamatti(a - 1, b);
    }

    private static double pot(double n, int p) {
        if (p < 0) {
            System.out.print(pot((1 / n), -p) + "; 1/");
            return pot(n, -p);
        }
        if (p == 0) return 1;
        return n * pot(n, p - 1);
    }

    private static int somma(int n) {
        if (n == 1) return 1;
        return (n + somma(n - 1));
    }
}