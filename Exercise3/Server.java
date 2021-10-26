import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket clientSocket;
    private ServerSocket serverSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    public Server(int port)
    {
        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isPalindrome(String s){

        String reversed = new StringBuilder(s).reverse().toString();
        return reversed.equals(s);
    }

    public void runServer()
    {

        try
        {
            // accept client request -> blocking call
            clientSocket = serverSocket.accept();
            // Connection accepted by server
            System.out.println("Console at server side says: Connection accepted by server.");
            // open input stream from client
            socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // open output stream to client
            socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        // client-server communicate
        while(true){
            String line = null;
            try{
                line = socketIn.readLine();

                // client disconnected
                if(line.equals("QUIT")){
                    socketOut.println("GoodBye");
                    break;
                }

                // check if word is palindrome
                if(isPalindrome(line)){
                    socketOut.println(line + " is a Palindrome.");
                }else{
                    socketOut.println(line + " is not a Palindrome.");
                }

            }catch(IOException e){
                e.printStackTrace();
            }
        }

        // close connection at the end
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main (String[] args){
        Server theServer = new Server(8099);
        theServer.runServer();
    }
}
