import java.util.Scanner;

public class Main {
    private CSocket cSocket;

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
                    
            String s = sc.nextLine();
            s = s.toLowerCase();
            
            cSocket = new CSocket();
            cSocket.init(new int[]{192, 168, 8, 27}, 1069);
            
            if (s.indexOf("write ") == 0) {
                System.out.println("Writing: " + s);
                cSocket.write(s.substring(6));
            }
            if (s.indexOf("try ") == 0) {
                System.out.println("Trying: " + s);
                cSocket.write(s.substring(4));
                String n = "";
                try {
                    cSocket.setTimeOut(3000);
                    n = cSocket.listen();
                }catch (SocketException ignored){
                    System.out.println("Server didn't respond");
                }
                if (n.equals("null")) System.out.println("Can't find nation's capital");
                else System.out.println("Nation's capital is: " + n);
            }
            cSocket.close();
        }
    }
}