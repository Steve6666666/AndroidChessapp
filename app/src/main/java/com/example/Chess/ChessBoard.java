package com.example.Chess;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */
import com.example.structure.*;
/**
 * <p>An 8 by 8 Piece Array that holds the value of chess pieces.
 * Each position on the board represents a chess.
 * <p>
 * Board's top left to top right = a8-h8
 * Board's lower left to lower right = a1-h1
 */
public class ChessBoard {


    /**
     * 2D chess array
     */
    public static chessPiece[][] board;

    /**
     * The initial state of a chess board.
     * Builds the chess board with chess pieces in their respective locations.
     * @return A 2D chess array object with all the chess in their location
     */
    public static chessPiece[][] initialize(){
        //white is 0,black is 1,blank is null
        board=new chessPiece[8][8];
        board[0][0]=new Rook("wR", 0, 0, 0);
        board[0][1]=new Knight("wN", 0, 0, 1);
        board[0][2]=new Bishop("wB", 0, 0, 2);
        board[0][3]=new Queen("wQ", 0, 0, 3);
        board[0][4]=new King("wK", 0, 0, 4);
        board[0][5]=new Bishop("wB", 0, 0, 5);
        board[0][6]=new Knight("wN", 0, 0, 6);
        board[0][7]=new Rook("wR", 0, 0, 7);

        board[7][0]=new Rook("bR", 1, 7, 0);
        board[7][1]=new Knight( "bN", 1,7, 1);
        board[7][2]=new Bishop( "bB", 1,7, 2);
        board[7][3]=new Queen( "bQ", 1,7, 3);
        board[7][4]=new King( "bK", 1,7, 4);
        board[7][5]=new Bishop( "bB", 1,7, 5);
        board[7][6]=new Knight( "bN", 1,7, 6);
        board[7][7]=new Rook( "bR", 1,7, 7);

        for(int i=0;i<8;i+=2) {
			board[1][i]=new Pawn("wP", 0, 1, i);
			board[6][i]=new Pawn("bP", 1, 6, i);
		}
		for(int i=1;i<8;i+=2) {
			board[1][i]=new Pawn("wP", 0, 1, i);
			board[6][i]=new Pawn("bP", 1, 6, i);
		}

        for(int i=0;i<8;i+=2) {
            board[2][i]=new Empty("empty", -1, 2, i);
            board[3][i]=new Empty( "empty", -1, 3, i);
            board[4][i]=new Empty( "empty", -1, 4, i);
            board[5][i]=new Empty("empty", -1, 5, i);
        }
        for(int i=1;i<8;i+=2) {
            board[2][i]=new Empty( "empty", -1, 2, i);
            board[3][i]=new Empty( "empty", -1, 3, i);
            board[4][i]=new Empty( "empty", -1, 4, i);
            board[5][i]=new Empty("empty", -1, 5, i);
        }
        return board;
    }


    /**
     * Prints the board with chess pieces in their current location.
     * @param board A 2D chess array object
     */
    public static void print(chessPiece[][] board){
        for (int i = 7; i >=0; i--) {
            for (int j=0;j< board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println(i+1);
        }
        System.out.print(" a ");
        System.out.print(" b ");
        System.out.print(" c ");
        System.out.print(" d ");
        System.out.print(" e ");
        System.out.print(" f ");
        System.out.print(" g ");
        System.out.print(" h");
        System.out.println();
    }

}
