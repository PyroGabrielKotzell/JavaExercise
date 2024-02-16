import java.net.SocketException;

public class SocketThread extends Thread{
    private final CSocket cSocket = new CSocket();
    public boolean isReading = false;

    SocketThread(){
        super();
    }

    @Override
    public void run() {
        // apro il socket
        cSocket.init(new int[]{192, 168, 8, 27}, 1069);
        String s = "";
        do {
            // se non sta scrivendo copia il contenuto della stringa
            if (!Main.isWriting) {
                isReading = true;
                s = Main.s.substring(0);
                isReading = false;
            }
            // se il contenuto non è vuoto entra
            if (!s.isEmpty()){
                // sout che non viene mai eseguito a quanto pare
                System.out.println("a");

                // tutta roba che non centra
                s = s.toLowerCase();
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
                if (!s.equals("break")) Main.s = "";

                // fine roba che non centra
            }
        }while(!s.equals("break"));
        cSocket.close();
    }
}
