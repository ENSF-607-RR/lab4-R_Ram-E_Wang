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

    private ServerSocket serverSocket;
    private ExecutorService pool;


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

                // each player connects to the server
                Player xPlayer = new Player(serverSocket.accept(), 'X');
                Player oPlayer = new Player(serverSocket.accept(), 'O');

                Referee theRef = new Referee();
                theRef.setxPlayer(xPlayer);
                theRef.setoPlayer(oPlayer);

                Game game = new Game();
                game.appointReferee(theRef);
                pool.execute(game);
            }
        }catch (IOException e){
            e.printStackTrace();
            pool.shutdown();
        }

        pool.shutdown();
    }

    public static void main(String[] args){
        GameServer theServer = new GameServer();
        System.out.println("Server is running.");
        theServer.runServer();
    }
}
