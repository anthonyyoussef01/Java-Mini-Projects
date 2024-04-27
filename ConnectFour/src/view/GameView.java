package view;

import model.Disc;
import model.Game;
import model.Player;
import model.PlayerType;

import java.util.Scanner;
import java.util.StringJoiner;

public class GameView {
    Scanner scanner;

    public GameView() {
        scanner = new Scanner(System.in);
    }

    public Game makeGame() {
        System.out.println("Welcome to Connect Four!");
        System.out.println("Enter the first player's name: ");
        String playerOneName = scanner.nextLine();
        System.out.println("Enter the second player's name: ");
        String playerTwoName = scanner.nextLine();
        Player playerOne = new Player(PlayerType.PLAYER_ONE, playerOneName.toUpperCase()),
                playerTwo = new Player(PlayerType.PLAYER_TWO, playerTwoName.toUpperCase());
        Game game = new Game(playerOne, playerTwo);
        return game;
    }

    public void printBoard(Game game) {
        System.out.println("*** Connect Four ***");
        for (Disc[] row : game.getBoard()) {
            StringJoiner sj = new StringJoiner(" | ");
            for (Disc disc : row) {
                if (disc == null) {
                    sj.add(" ");
                } else {
                    sj.add(disc.toString());
                }
            }
            System.out.println(sj.toString());
        }
    }

    public int playTurn(String playerName) {
        System.out.println(playerName + ", enter a column number: ");
        int columnToInsertIn;
        String input = scanner.nextLine();
        try {
            columnToInsertIn = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input. Please enter a number.");
        }
        return columnToInsertIn;
    }
}
