package inlab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DateClient {

    private Socket dateSocket;
    private BufferedReader socketInput;
    private BufferedReader standardInput;
    private PrintWriter socketOutput;

    public DateClient(String serverName, int portNumber){

        try {
            dateSocket = new Socket(serverName, portNumber); // request connection to server
            socketInput = new BufferedReader(new InputStreamReader(dateSocket.getInputStream()));
            socketOutput = new PrintWriter(dateSocket.getOutputStream(), true);
            standardInput = new BufferedReader(new InputStreamReader(System.in)); // read from keyboard
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to open communicate with server.");
        }
    }

    public void communicate(){

        String line = "";
        String response = "";

        while(true){
            try{
                System.out.println("Please select an option (DATE/TIME)");
                line = standardInput.readLine();

                // user wants to quit application
                if (line.equals("QUIT")){
                    break;
                }

                // send line to server
                socketOutput.println(line);
                response = socketInput.readLine();
                System.out.println(response);
            }catch(IOException e){

            }
        }

        // closing the socket
        try {
            dateSocket.close();
            socketOutput.close();
            socketInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static void main(String[] args){
        DateClient dateClient = new DateClient("localhost", 9090);
        dateClient.communicate();
    }

}
