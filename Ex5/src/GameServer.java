import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class GameServer {

    private Socket clientSocketX;
    private Socket clientSocketY;
    private ServerSocket serverSocket;
    private PrintWriter socketOutX;
    private BufferedReader socketInX;
    private PrintWriter socketOutY;
    private BufferedReader socketInY;

    // TODO add ExecutorService

    public GameServer(){
        try {
            serverSocket = new ServerSocket(9090);
            // TODO add ExecutorService
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * runServer is responsible for accepting client requests and
     * adding and running the game on new threads as needed.
     */
    public void runServer(){
        try{
            // waiting on client 1 to connect
            clientSocketX = serverSocket.accept();
            socketInX = new BufferedReader(new InputStreamReader(clientSocketX.getInputStream()));
            socketOutX = new PrintWriter(clientSocketX.getOutputStream(), true);

            // waiting on client 2 to connect
            clientSocketY = serverSocket.accept();
            socketInY = new BufferedReader(new InputStreamReader(clientSocketY.getInputStream()));
            socketOutY = new PrintWriter(clientSocketY.getOutputStream(), true);
            socketOutX.println("GO");
            socketOutY.println("GO");

            Game g1 = new Game(socketOutX, socketInX, socketOutY, socketInY );
            Thread t1 = new Thread(g1);
            t1.start();


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        GameServer theServer = new GameServer();
        theServer.runServer();
    }
}
