public class Main {
    public static void main(String[] args) {
        SocketThread socket = new SocketThread();
        socket.start();
        while (socket.isAlive()) {}
    }
}