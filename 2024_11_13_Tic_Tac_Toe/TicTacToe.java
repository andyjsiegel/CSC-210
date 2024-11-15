import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {

    private ArrayList<ArrayList<Character>> board;
    private int turn = 0;
    private boolean gameOver = false;
    private Scanner input = new Scanner(System.in);

    public TicTacToe() {
        board = new ArrayList<ArrayList<Character>>();
        // Create a 3x3 board filled with '.' characters
        for (int i = 0; i < 3; i++) {
            ArrayList<Character> row = new ArrayList<Character>();
            for (int j = 0; j < 3; j++) {
                row.add('.'); // Fill with '.'
            }
            board.add(row); // Add the row to the board
        }
    }

    private void doTurn() {
        System.out.println("Enter move in coordinate form (x,y)");
        String move = input.nextLine();
        int[] coords = parseCoords(move);
        if (board.get(coords[1]).get(coords[0]) == '.') {
            board.get(coords[1]).set(coords[0], turn == 0 ? 'X' : 'O');
            turn = 1 - turn;
            System.out.println(this.toString());
        } else {
            System.out.println("Invalid move");
        }
        char winner = checkWinner();
        if (checkWinner() != '.') {
            gameOver = true;
            System.out.println("Game Over: Player " + winner + " wins!");
        }
    }

    public void play() {
        while (!gameOver) {
            doTurn();
        }
        input.close();
    }

    // check if the player has won
    private char checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board.get(i).get(0) != '\0' && board.get(i).get(0) == board.get(i).get(1) && board.get(i).get(1) == board.get(i).get(2)) {
                return board.get(i).get(0); // Return the winning character
            }
            // Check columns
            if (board.get(0).get(i) != '\0' && board.get(0).get(i) == board.get(1).get(i) && board.get(1).get(i) == board.get(2).get(i)) {
                return board.get(0).get(i);
            }
        }

        // Check diagonals
        if (board.get(0).get(0) != '\0' && board.get(0).get(0) == board.get(1).get(1) && board.get(1).get(1) == board.get(2).get(2)) {
            return board.get(0).get(0);
        }
        if (board.get(0).get(2) != '\0' && board.get(0).get(2) == board.get(1).get(1) && board.get(1).get(1) == board.get(2).get(0)) {
            return board.get(0).get(2);
        }

        return '.'; // No winner yet
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Character> row : board) {
            for (Character c : row) {
                sb.append(c);
            }
            sb.append(System.lineSeparator()); // Adds a new line after each row
        }
        return sb.toString(); // Convert StringBuilder to String
    }

    private int[] parseCoords(String coordinates) {
        int x_coord = Integer.parseInt(coordinates.split(",")[0].replace('(', ' ').trim());
        int y_coord = Integer.parseInt(coordinates.split(",")[1].replace(')', ' ').trim());
        int[] coordsTuple = { x_coord, y_coord };
        return coordsTuple;
    }
}
