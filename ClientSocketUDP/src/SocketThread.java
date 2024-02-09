public class SocketThread extends Thread{
    SocketThread(){
        super();
    }

    @Override
    public void run() {
        CSocket cSocket = new CSocket();
        cSocket.init(new int[]{192, 168, 8, 27}, 1069);
        cSocket.write("babaab");
        cSocket.close();
    }
}
