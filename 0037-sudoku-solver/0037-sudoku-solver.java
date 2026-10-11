class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (isValid(board, row, col, digit)) {
                            board[row][col] = digit;

                            if (solve(board)) {
                                return true;
                            }

                            // Backtrack if this choice fails
                            board[row][col] = '.';
                        }
                    }

                    return false;
                }
            }
        }

        return true; // All cells are filled
    }

    private boolean isValid(char[][] board, int row, int col, char digit) {
        for (int i = 0; i < 9; i++) {
            // Check row and column
            if (board[row][i] == digit || board[i][col] == digit) {
                return false;
            }

            // Check 3x3 sub-box
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;

            if (board[boxRow][boxCol] == digit) {
                return false;
            }
        }

        return true;
    }
}