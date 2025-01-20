package TicTacToe.test;

import TicTacToe.ComputerPlayer;
import TicTacToe.Game;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ComputerPlayerTest {

    private Game game;
    private ComputerPlayer computerPlayer;

    @BeforeEach
    public void setUp() {
        game = Game.getInstance();
        game.resetGame();
        computerPlayer = new ComputerPlayer();
    }

    @Test
    public void testComputerPlayerMakesValidMove() {
        computerPlayer.makeMove();
        char[][] board = game.getBoard();
        boolean moveMade = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'O') {
                    moveMade = true;
                    break;
                }
            }
        }

        assertTrue(moveMade, "Компьютер должен сделать ход на пустую клетку.");
    }
}
