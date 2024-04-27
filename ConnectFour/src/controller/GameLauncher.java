package controller;
import exceptions.ColumnIsFullException;
import exceptions.InvalidColumnException;
import view.GameView;
import model.Game;

public class GameLauncher {
    private GameView gameView;
    private Game game;

    public GameLauncher() {
        gameView = new GameView();
        game = gameView.makeGame();
        boolean didGameEnd = false;

        while (true) {
            if (game.checkIfGameIsOver()) {
                didGameEnd = true;
                break;
            }
            gameView.printBoard(game);
            int columnToInsertIn = -1;

            try {
                columnToInsertIn = gameView.playTurn(game.getTurnPlayer().getName());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            try {
                if (game.insertDisc(columnToInsertIn)) {
                    break;
                }
                game.endTurn();
            } catch (ColumnIsFullException e) {
                System.out.println("Column is full. Please enter a different column.");
            } catch (InvalidColumnException e) {
                System.out.println("Invalid column. Please enter a valid column.");
            }
        }

        if (didGameEnd) {
            System.out.println("Game Over!");
        } else {
            gameView.printBoard(game);
            System.out.println(game.getTurnPlayer().getName() + " wins!");
            System.out.println("Game Over!");
        }
    }

    public static void main(String[] args) {
        new GameLauncher();
    }
}