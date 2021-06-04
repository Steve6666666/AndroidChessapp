package com.example.structure;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */
import com.example.Chess.ChessBoard;
/**
 * can move straight up or straight down
 */
public class Rook extends chessPiece {

    /**
     * @param name Rook
     * @param present Which turn it belongs to. 0 for white, 1 for black
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Rook(String name, int present, int x, int y) {
        super(name, present, x, y);
    }

    @Override
    //can move straight up or straight down
    public boolean move( int x, int y) {
        if(this.x==x&&this.y ==y){
            return false;
        }else if(this.x==x){
            if(y>this.y){
                int cur=this.y+1;
                while (cur<y){
                    if(cango(ChessBoard.board[x][cur])){
                        cur++;
                    }else{
                        return false;
                    }
                }
                return eatPoint(ChessBoard.board[x][cur]);
            }else if(y<this.y){
                int cur=this.y-1;
                while (cur>y){
                    if(cango(ChessBoard.board[x][cur])){
                        cur--;
                    }else{
                        return false;
                    }
                }
                return eatPoint(ChessBoard.board[x][cur]);
            }

        }else if(this.y==y){
            if(x>this.x){
                int cur= this.x+1;
                while (cur<x){
                    if(cango(ChessBoard.board[cur][y])){
                        cur++;
                    }else{
                        return false;
                    }
                }
                return eatPoint(ChessBoard.board[cur][y]);
            }else if(x<this.x){
                int cur= this.x-1;
                while (cur>x){
                    if(cango(ChessBoard.board[cur][y])){
                        cur--;
                    }else{
                        return false;
                    }
                }
                return eatPoint(ChessBoard.board[cur][y]);
            }
        }
        return false;
    }

    /** Determine whether the point passed by the chess piece is legal.
     * @param chess The point in board where the chess piece passes before reaching the end
     * @return true if this position is empty chess
     */
    public boolean cango(chessPiece chess){
        if(chess.present==-1) return true;
        else return false;
    }
    /** Determine whether this chess can eat the chess at that point.
     * @param chess the point in board which is destination of this chess
     * @return true if this position is empty chess of chess with different side
     */
    public boolean eatPoint(chessPiece chess){
        return chess.present != this.present;
    }
}
