import java.io.IOException;

/**
 * Referee represents the referee of a tic-tac-toe game.
 * The class data fields and methods allow the referee to initialize the game.
 *
 * @author      Rohinesh Ram
 * @version     1.0
 * @since       September 17, 2021
 */
public class Referee {

    private Player xPlayer;
    private Player oPLayer;
    private Board board;

    /**
     * Starts the game by setting the Player's and opponents as well as the board.
     * The game starts with the X player.
     */
    public void runTheGame() throws IOException {
        // set each player opponents
        xPlayer.setOpponent(oPLayer);
        oPLayer.setOpponent(xPlayer);

        // set the board for each player
        xPlayer.setBoard(board);
        oPLayer.setBoard(board);

        board.display();
        // player x starts
        xPlayer.play();

    }

    /**
     * Sets the Board for the referee to the specified Board object.
     * @param theBoard the Board
     */
    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    /**
     * Sets the Player using the 'O' mark to the specified Player
     * @param oPlayer - the Player using the 'O' mark
     */
    public void setoPlayer(Player oPlayer) {
        this.oPLayer = oPlayer;
    }

    /**
     * Sets the Player using the 'X' mark to the specified Player
     * @param xPlayer - the Player using the 'X' mar
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }
}
