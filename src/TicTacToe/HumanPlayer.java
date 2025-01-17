package TicTacToe;

public class HumanPlayer implements Player{
    private int row, col;

    public HumanPlayer(int row, int col) {
        this.row = row;
        this.col = col;
    }


    @Override
    public void makeMove() {
        Game game = Game.getInstance();
        if (game.makeMove(row, col)) {
            if (game.checkWin()) {
                game.updateScore();
            } else if (game.checkDraw()) {

            }
            if (!game.checkWin() && !game.checkDraw()) {
                game.switchPlayer();
            }
        }
    }
}
