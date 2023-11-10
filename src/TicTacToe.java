import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";
    private static boolean gameEnded = false;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        playGame();
        scanner.close();
    }

    public static void playGame() {
        initializeBoard();

        while (!gameEnded) {
            display();
            int rowMove = getRangedInt(scanner, "Enter row (1-3): ", 1, 3) - 1;
            int colMove = getRangedInt(scanner, "Enter column (1-3): ", 1, 3) - 1;

            if (isValidMove(rowMove, colMove)) {
                board[rowMove][colMove] = currentPlayer;

                if (isWin(currentPlayer)) {
                    display();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            } else {
                System.out.println("Invalid move. That spot is already taken. Try again.");
            }
        }
    }

    public static void initializeBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    public static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("---------");
            }
        }
    }

    public static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    public static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    public static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    public static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return !isWin("X") && !isWin("O");
    }

    public static int getRangedInt(Scanner scanner, String prompt, int low, int high) {
        int num;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            num = scanner.nextInt();
            if (num < low || num > high) {
                System.out.println("Please enter a number between " + low + " and " + high + ".");
            }
        } while (num < low || num > high);
        return num;
    }
}
