package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int turnNum = 0;
        //sets up 6 by 6 board then sets all values to '-'
	    char[][] board = new char[6][6];
	    for (int x = 0; x < board.length; x++){
	        for (int y = 0; y<board.length; y++){
	            board[x][y] = '-';
            }
        }
	    int state = turn(turnNum, board);
	    if (state == 1){
	        System.out.println("End");
        }
    }
    public static int turn(int turnNum, char[][] board){
        printBoard(board);
        Scanner readLine = new Scanner(System.in);
        if (turnNum % 2 == 0){
            // player 1
            System.out.println("Player 1, it's your turn");
            int spot = readLine.nextInt();
            if (spot <= 0 || spot >= 7){
                System.out.println("Not valid, try again");
                turn(turnNum, board);
            } else {
                spot -= 1;
                int[] place = fall(spot, board);
                if (place[1] != -1){
                    board[spot][place[1]] = '@';
                    turnNum ++;
                    if (winCheck(place, board)){
                        System.out.println("Player 1 has won");
                        printBoard(board);
                        return 1;
                    }
                } else {
                    System.out.println("Not valid, try again");
                    turn(turnNum, board);
                }
            }
        } else {
            //player 2
            System.out.println("Player 2, it's your turn");
            int spot = readLine.nextInt();
            if (spot <= 0 || spot >= 7){
                System.out.println("Not valid, try again");
                turn(turnNum, board);
            } else {
                spot -= 1;
                int[] place = fall(spot, board);
                if (place[1] != -1){
                    board[spot][place[1]] = '#';
                    turnNum++;
                    if (winCheck(place, board)){
                        printBoard(board);
                        System.out.println("Player 2 has won");
                        return 1;
                    }
                } else {
                    System.out.println("Not valid, try again");
                    turn(turnNum, board);
                }
            }
        }
        turn(turnNum, board);
        return 0;
    }
    public static boolean winCheck(int[] place, char[][] board){
        // aaaaaaaahhhhhhhhhhhhhhh
        char player = board[place[0]][place[1]];
        int rowCount = 0;
        for (int x = 0; x < board.length; x++){
            for (char[] chars : board) {
                if (chars[x] == player){
                    rowCount ++;
                    if (rowCount >= 4){
                        return true;
                    }
                }
            }
            rowCount = 0;
        }




        return false;
    }
    public static void printBoard(char[][] board){
        // Prints out the board, row by row
        System.out.println("123456");
        for (int y = 0; y < board.length; y++){
            for (char[] chars : board) {
                System.out.print(chars[y]);
            }
            System.out.println();
        }
    }
    public static int[] fall(int spot, char[][] board){
        //basically this just goes to the max(lowest) value then sees if it's full, if it is, go up one, if not cool return it so we can place it. Column not good? return -1
        int[] place = new int[2];
        for (int c = board.length - 1; c >= 0; c--){
            if (board[spot][c] == '-'){
                place[0] = spot;
                place[1] = c;
                return place;
            }
        }
        place[0] = spot;
        place[1] = -1;
        return place;
    }
}
