import java.util.Scanner;

/*public class Main {
    public static String s = "";
    public static boolean isWriting = false;

    public static void main(String[] args) {
        // apro il thread
        SocketThread socket = new SocketThread();
        // lo starto
        socket.start();
        while (socket.isAlive()) {
            Scanner sc = new Scanner(System.in);
            System.out.print(
                    """
                    
                    Write [message]
                    Try [nation name]
                    break
                    >\s""");
            // prendo n
            String n = sc.nextLine();
            // butto fuori n
            System.out.println("n:: " + n);
            // 's' è una copia memorizzata in un posto diverso di n
            if(!socket.isReading) {
                isWriting = true;
                s = n.substring(0);
                isWriting = false;
            }
            // butto fuori s
            System.out.println("s:: " + s);
        }
    }
}*/