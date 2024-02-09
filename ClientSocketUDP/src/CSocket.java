import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class CSocket {
    private DatagramSocket s;
    private final byte[] address = new byte[4];
    private int port;

    public void write(String string) {
        try {
            byte[] msg = string.getBytes();
            DatagramPacket packet = new DatagramPacket(msg, msg.length, InetAddress.getByAddress(address), port);
            s.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            byte[] buffer2 = new byte[65536];
            DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length);
            s.receive(packet2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTimeOut(int n) throws SocketException {
        s.setSoTimeout(n);
    }

    public boolean isConnected() {
        return s.isConnected() && !s.isClosed();
    }

    public void init(int[] addr, int port) {
        try {
            this.port = port;
            s = new DatagramSocket(port);
            address[0] = (byte) (addr[0] & 0xFF);
            address[1] = (byte) (addr[1] & 0xFF);
            address[2] = (byte) (addr[2] & 0xFF);
            address[3] = (byte) (addr[3] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
