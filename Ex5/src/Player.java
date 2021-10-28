import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Player represents a player playing a tic-tac-toe game.
 * The data fields and method of the class allow the Player to make moves
 * on the board, and determine if the player, or their opponent won.
 *
 * @author      Rohinesh Ram
 * @version     1.0
 * @since       September 19, 2021
 */
public class Player {

    private String name;
    private Board board;
    private char mark;
    private Player opponent;

    /**
     * Class constructor used to set the players name and
     * the mark they will use.
     * @param name name of the plauer
     * @param mark mark of the player
     */
    public Player(String name, char mark) {
        setName(name);
        setMark(mark);
    }

    /**
     * Method that allows a Player to make a full turn.
     * The Player makes a move and displays the Board. If an end game scenario is reached, the method returns.
     * If no end game is reached, the turn is passed to the Player's opponent.
     * @throw IOException if an input or output exception occurs
     */
    public void play() throws IOException {

        // player makes move
        this.makeMove();
        // show the board
        board.display();
        // game was a tie
        if(board.isFull()){
            System.out.println("Game is a Tie.");
            return;
        }
        // player o won
        if(board.oWins()){
            System.out.println("Player O wins!");
            return;
        }
        // player x won
        if(board.xWins()) {
            System.out.println("Player X wins");
            return;
        }
        // game still on, pass turn to next player
        this.opponent.play();
    }

    /**
     * The method that allows the Player to place a mark on the Board.
     */
    public void makeMove() throws IOException {

        System.out.println(this.getName() + "'s turn.");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter row: ");
        int row = Integer.parseInt(stdin.readLine());
        System.out.println("Enter column: ");
        int col = Integer.parseInt(stdin.readLine());

        board.addMark(row, col, this.mark);
    }

    /**
     * Sets the Player's opponent as the specified Player object.
     * @param player opponent of the Player
     */
    public void setOpponent(Player player){
        this.opponent = player;
    }

    /**
     * Sets the players board as the specified Board object.
     * @param theBoard - the board object for the Player
     */
    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    /**
     * Returns the name of the Player.
     * @return the Player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Player to the specified name.
     * @param name the name of the Player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the Player's mark as the specified mark.
     * @param mark the mark of the Player
     */
    public void setMark(char mark) {
        this.mark = mark;
    }
}
