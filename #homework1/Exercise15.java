public class Exercise15 {
    public static void printBoard(int[] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solveNQueens(int[] board, int row) {
        int n = board.length;
        if (row == n) {
            printBoard(board);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                solveNQueens(board, row + 1);
                board[row] = -1;
            }
        }
    }

    public static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || board[i] - i == col - row || board[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 8;
        int[] board = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }
        solveNQueens(board, 0);
    }
}
