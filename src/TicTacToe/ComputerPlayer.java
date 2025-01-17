package TicTacToe;

import java.util.Random;

public class ComputerPlayer implements Player {
    private Random random = new Random();

    @Override
    public void makeMove() {
        Game game = Game.getInstance();
        int row, col;

        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!game.makeMove(row, col));

        if (game.checkWin()) {
            game.updateScore();
        } else if (game.checkDraw()) {

        }
        if (!game.checkWin() && !game.checkDraw()) {
            game.switchPlayer();
        }
    }
}
