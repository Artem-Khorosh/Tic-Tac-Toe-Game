package TicTacToe;

public class GameFactory {
    public static Player createPlayer(int row, int col) {
        Game game = Game.getInstance();
        if (game.isVsComputer() && game.getCurrentPlayer() == 'O') {
            return new ComputerPlayer();
        }
        return new HumanPlayer(row, col);
    }
}
