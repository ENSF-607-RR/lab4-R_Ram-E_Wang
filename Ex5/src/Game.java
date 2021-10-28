
import java.io.*;

/**
 *	Game repesents a tic-tac-toe game.
 * 	Game implements the Constants interface.
 * 	@author		MM
 * 	@version 	1.0
 * 	@since		September 17, 2021
 */
public class Game implements Constants, Runnable {

	private Board theBoard;
	private Referee theRef;
	private PrintWriter socketOutX;
	private BufferedReader socketInX;
	private PrintWriter socketOutO;
	private BufferedReader socketInO;

	/**
	 * Class constructor.
	 */
    public Game(PrintWriter socketOutX, BufferedReader socketInX, PrintWriter socketOutO, BufferedReader socketInO) {
        theBoard  = new Board();
		this.socketInX = socketInX;
		this.socketInO = socketInO;
		this.socketOutX = socketOutX;
		this.socketOutO = socketOutO;
	}

	/**
	 * The method used to set a specified Referee object for the game.
	 * @param r the referee to be appointed
	 * @throws IOException if an input or output exception occurs
	 */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }

	@Override
	public void run() {
		String nameX = null;
		String nameY = null;
		String line = null;

		try {
			Referee theRef;
			Player xPlayer, oPlayer;

			// get player names
			socketOutX.println("Please enter your name: ");
			socketOutO.println("Please enter your name: ");
			nameX = socketInX.readLine();
			nameY = socketInO.readLine();

			// TODO handle malicious user inputs later
//			while (nameX == null) {
//				socketOutX.println("Please try again: ");
//				nameX = socketInX.readLine();
//			}

			socketOutX.println("You are player X");
			xPlayer = new Player(nameX, LETTER_X, socketInX, socketOutX);
			xPlayer.setBoard(this.theBoard);

			// TODO handle malicious user inputs later
//			while (nameY == null) {
//				System.out.print("Please try again: ");
//				nameY = socketInY.readLine();
//			}

			socketOutO.println("You are player O");
			oPlayer = new Player(nameY, LETTER_O, socketInO, socketOutO);
			oPlayer.setBoard(this.theBoard);

			theRef = new Referee();
			theRef.setBoard(this.theBoard);
			theRef.setoPlayer(oPlayer);
			theRef.setxPlayer(xPlayer);
			this.appointReferee(theRef);

		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
