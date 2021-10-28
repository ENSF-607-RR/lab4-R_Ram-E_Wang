import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private Socket clientSocketX;
    private Socket clientSocketO;
    private ServerSocket serverSocket;
    private PrintWriter socketOutX;
    private BufferedReader socketInX;
    private PrintWriter socketOutO;
    private BufferedReader socketInO;
    private ExecutorService pool;

    // TODO add ExecutorService

    public GameServer(){
        try {
            serverSocket = new ServerSocket(9090);
            pool = Executors.newFixedThreadPool(5);
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
            while(true) {
                // waiting on client 1 to connect
                clientSocketX = serverSocket.accept();
                socketInX = new BufferedReader(new InputStreamReader(clientSocketX.getInputStream()));
                socketOutX = new PrintWriter(clientSocketX.getOutputStream(), true);

                // waiting on client 2 to connect
                clientSocketO = serverSocket.accept();
                socketInO = new BufferedReader(new InputStreamReader(clientSocketO.getInputStream()));
                socketOutO = new PrintWriter(clientSocketO.getOutputStream(), true);

                // both players connected
                socketOutX.println("Starting Game.");
                socketOutO.println("Starting Game.");

                Game game = new Game(socketOutX, socketInX, socketOutO, socketInO);
                pool.execute(game);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        pool.shutdown();
    }

    public static void main(String[] args){
        GameServer theServer = new GameServer();
        theServer.runServer();
    }
}
