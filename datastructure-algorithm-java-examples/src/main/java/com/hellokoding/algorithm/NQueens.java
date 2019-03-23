package com.hellokoding.algorithm;

public class NQueens {
    private void printBoard(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.printf(" %d", board[i][j]);
            }

            System.out.println();
        }
    }

    private boolean isValid(int[][] board, int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) return false;
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        for (int i = row, j = col; i < board.length && j >= 0 ; i++, j--) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    public boolean enumerate(int[][] board, int col) {
        if (col == board.length) return true;

        for (int i = 0; i < board.length; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 1;

                if (enumerate(board, col+1)) return true;

                board[i][col] = 0; //backtracking
            }
        }

        return false;
    }

    public static void main(String[] args){
        NQueens nQueens = new NQueens();

        int[][] board = new int[8][8];
        if (!nQueens.enumerate(board, 0)) {
            System.out.println("Solution not found!");
        }

        nQueens.printBoard(board);
    }
}
