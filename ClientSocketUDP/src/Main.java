import java.util.Scanner;
import java.net.SocketException;

public class Main {
    private static CSocket cSocket;

    public static void main(String[] args) {
        String s = "";
        
        while (!s.equals("break")) {
            Scanner sc = new Scanner(System.in);
            System.out.print(
                    """
                    
                    Write [message]
                    Try [nation name]
                    break
                    >\s""");
                    
            s = sc.nextLine();
            String n = s.toLowerCase();
            
            cSocket = new CSocket();
            cSocket.init(new int[]{192, 168, 8, 27}, 1069);
            
            if (n.indexOf("write ") == 0) {
                System.out.println("Writing: " + s);
                cSocket.write(s.substring(6));
            }
            if (n.indexOf("try ") == 0) {
                System.out.println("Trying: " + s);
                cSocket.write(s.substring(4));
                String answ = "";
                try {
                    cSocket.setTimeOut(3000);
                    answ = cSocket.listen();
                }catch (SocketException ignored){
                    System.out.println("Server didn't respond");
                }
                if (answ.equals("null")) System.out.println("Can't find nation's capital");
                else System.out.println("Nation's capital is: " + answ);
            }
            cSocket.close();
        }
    }
}