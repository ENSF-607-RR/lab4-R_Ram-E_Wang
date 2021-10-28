import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private BufferedReader socketInput;
    private PrintWriter socketOutput;
    private BufferedReader standardInput;

    public Client(String serverName, int portNumber){
        try {
            clientSocket = new Socket(serverName, portNumber);

            // keyboard reader
            standardInput = new BufferedReader(new InputStreamReader(System.in));

            socketOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            socketInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void runClient() {

        String line = "";
        String response = null;
        try {
            System.out.println("Connected to server, waiting on other player.");
            // waits for other player to connect

            response = socketInput.readLine();
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        while(true){
//            // read from server
//            // display in termianl
//            // write
//            // until game is over
//        }

        // user to enter name
//        try {
//            response = socketInput.readLine();
//            System.out.println(response);
//            line = standardInput.readLine();
//            socketOutput.println(line);
//        } catch (IOException e) {
//            System.err.println("Couldnt get response");
//        }

        while(true){
            try {
                response = socketInput.readLine();
                System.out.println(response);
                line = standardInput.readLine();
                socketOutput.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args){
        Client client = new Client("localhost", 9090);

        client.runClient();
    }

}
