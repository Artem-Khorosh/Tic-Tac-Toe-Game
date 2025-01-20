package TicTacToe.test;

import TicTacToe.Game;
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.testng.Assert.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = Game.getInstance();
        game.resetGame();
    }

    @Test
    public void testInitialBoardIsEmpty() {
        char[][] board = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', board[i][j], "Игровое поле должно быть пустым после сброса.");
            }
        }
    }

    @Test
    public void testMakeMove() {
        assertTrue(game.makeMove(0, 0), "Ход должен быть успешным на пустой клетке.");
        assertEquals('X', game.getBoard()[0][0], "Клетка должна содержать 'X' после хода игрока X.");

        assertFalse(game.makeMove(0, 0), "Ход должен быть неудачным на занятой клетке.");
    }

    @Test
    public void testSwitchPlayer() {
        assertEquals('X', game.getCurrentPlayer(), "Начальный игрок должен быть X.");
        game.switchPlayer();
        assertEquals('O', game.getCurrentPlayer(), "После переключения игрок должен быть O.");
        game.switchPlayer();
        assertEquals('X', game.getCurrentPlayer(), "После второго переключения игрок должен быть X.");
    }

    @Test
    public void testCheckWin() {
        // Проверка победы по строке
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(0, 2); // X
        assertTrue(game.checkWin(), "Игрок X должен выиграть по строке.");

        game.resetGame();

        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 1); // O
        game.makeMove(2, 0); // X
        assertTrue(game.checkWin(), "Игрок X должен выиграть по столбцу.");

        game.resetGame();

        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 1); // X
        game.makeMove(1, 0); // O
        game.makeMove(2, 2); // X
        assertTrue(game.checkWin(), "Игрок X должен выиграть по диагонали.");
    }

    @Test
    public void testCheckDraw() {
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 0); // O
        game.makeMove(1, 1); // X
        game.makeMove(1, 2); // O
        game.makeMove(2, 0); // X
        game.makeMove(2, 1); // O
        game.makeMove(2, 2); // X

        assertTrue(game.checkDraw(), "Игра должна закончиться ничьей.");
        assertEquals(0, game.getPlayerXScore(), "Счет игрока X не должен измениться при ничьей.");
        assertEquals(0, game.getPlayerOScore(), "Счет игрока O не должен измениться при ничьей.");
    }

    @Test
    public void testUpdateScore() {
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(0, 2); // X

        assertTrue(game.checkWin(), "Игрок X должен выиграть.");
        assertEquals(1, game.getPlayerXScore(), "Счет игрока X должен увеличиться на 1.");
        assertEquals(0, game.getPlayerOScore(), "Счет игрока O должен остаться 0.");

        // Сброс игры и проверка, что счет не изменился при ничьей
        game.resetGame();
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 0); // O
        game.makeMove(1, 1); // X
        game.makeMove(1, 2); // O
        game.makeMove(2, 0); // X
        game.makeMove(2, 1); // O
        game.makeMove(2, 2); // X

        assertTrue(game.checkDraw(), "Игра должна закончиться ничьей.");
        assertEquals(1, game.getPlayerXScore(), "Счет игрока X не должен измениться при ничьей.");
        assertEquals(0, game.getPlayerOScore(), "Счет игрока O не должен измениться при ничьей.");
    }
}