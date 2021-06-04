package com.example.structure;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */
import com.example.Chess.ChessBoard;

/**
 * knight can move to any of the closest squares that are not on the same rank, file, or diagonal.
 */
public class Knight extends chessPiece {
    /**
     * @param name Knight
     * @param present Which turn it belongs to. 0 for white, 1 for black
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Knight(String name, int present, int x, int y) {
        super(name, present, x, y);
    }

    @Override
    // knight can moves to any of the closest squares that are not on the same rank, file, or diagonal.
    public boolean move(int x, int y) {
        int curx= Math.abs(this.x-x);
        int cury = Math.abs(this.y-y);
        if((curx==1&&cury==2)||(curx==2&&cury==1)){
            return eatPoint(ChessBoard.board[x][y]);
        }
        //if((x==this.x+1&&y==this.y+2)||())
        return false;
    }

    /** Determine whether this chess can eat the chess at that point.
     * @param chess the point in board which is destination of this chess
     * @return true if this position is empty chess or chess with different side
     */
    public boolean eatPoint(chessPiece chess){
        if(chess.present==this.present){
            return false;
        }
        return true;
    }

}
