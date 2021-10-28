
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
	private PrintWriter socketOutY;
	private BufferedReader socketInY;

	/**
	 * Class constructor.
	 */
    public Game(PrintWriter socketOutX, BufferedReader socketInX, PrintWriter socketOutY, BufferedReader socketInY ) {
        theBoard  = new Board();
		this.socketInX = socketInX;
		this.socketInY = socketInY;
		this.socketOutX = socketOutX;
		this.socketOutY = socketOutY;
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

			nameX = socketInX.readLine();
			while (nameX == null) {
				socketOutX.println("Please try again: ");
				nameX = socketInX.readLine();
			}
			socketOutX.println("VALID");
			socketOutX.println("You are player X");

			xPlayer = new Player(nameX, LETTER_X);
			xPlayer.setBoard(this.theBoard);

			nameY = socketInY.readLine();
			while (nameY == null) {
				System.out.print("Please try again: ");
				nameY = socketInY.readLine();
			}

			oPlayer = new Player(nameY, LETTER_O);
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
