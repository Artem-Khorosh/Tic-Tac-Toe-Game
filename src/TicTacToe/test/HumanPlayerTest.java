package TicTacToe.test;

import TicTacToe.Game;
import TicTacToe.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HumanPlayerTest {

    private Game game;
    private HumanPlayer humanPlayer;

    @BeforeEach
    public void setUp() {
        game = Game.getInstance();
        game.resetGame();
        humanPlayer = new HumanPlayer(0, 0);
    }

    @Test
    public void testHumanPlayerMakesValidMove() {
        humanPlayer.makeMove();
        assertEquals('X', game.getBoard()[0][0], "Клетка (0, 0) должна содержать 'X' после хода игрока.");
    }

    @Test
    public void testHumanPlayerCannotMakeInvalidMove() {
        game.makeMove(0, 0);
        humanPlayer.makeMove();
        assertEquals('X', game.getBoard()[0][0], "Клетка (0, 0) должна остаться 'X' после попытки сделать ход в занятую клетку.");
    }
}