package TicTacToe;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private JLabel statusLabel;

    public GameUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        initializeBoard(boardPanel);

        JPanel controlPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        controlPanel.add(statusLabel, BorderLayout.NORTH);

        JButton restartPanel = new JButton("New Game");
        restartPanel.addActionListener(e -> resetGame());
        controlPanel.add(restartPanel, BorderLayout.CENTER);

        JButton vsComputerButton = new JButton("Play vs Computer");
        vsComputerButton.addActionListener(e -> {
            Game.getInstance().setVsComputer(true);
            resetGame();
        });
        controlPanel.add(vsComputerButton, BorderLayout.SOUTH);

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializeBoard(JPanel boardPanel) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> handleButtonClick(finalI, finalJ));
                boardPanel.add(buttons[i][j]);
            }

        }
    }

    private void handleButtonClick(int row, int col) {
        Player player = GameFactory.createPlayer(row, col);
        player.makeMove();
        updateUI();

        Game game = Game.getInstance();
        if (game.isVsComputer() && game.getCurrentPlayer() == 'O' && !game.checkWin() && !game.checkDraw()) {
            player = new ComputerPlayer();
            player.makeMove();
            updateUI();
        }
    }

    private void updateUI() {
        Game game = Game.getInstance();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(game.getBoard()[i][j]));
            }
        }

        if (game.checkWin()) {
            statusLabel.setText("Игрок " + game.getCurrentPlayer() + " выиграл! Счет: X - " + game.getPlayerXScore() + ", O - " + game.getPlayerOScore());
            disableButtons();
        } else if (game.checkDraw()) {
            statusLabel.setText("Ничья!");
            disableButtons();
        } else {
            statusLabel.setText("Ход игрока " + game.getCurrentPlayer());
        }
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        Game.getInstance().resetGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }


}
