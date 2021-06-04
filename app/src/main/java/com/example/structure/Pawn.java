package com.example.structure;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */
import com.example.Chess.ChessBoard;

/**
 * <p>Pawn can move forward to the unoccupied square immediately in front of it on the same file, or on its first move it can advance two squares along the same file,
 * or the pawn can capture an opponent's piece on a square diagonally in front of it on an adjacent file.
 */
public class Pawn extends chessPiece {


    /**
     * @param name Pawn
     * @param present Which turn it belongs to. 0 for white, 1 for black
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Pawn(String name, int present, int x, int y) {
        super(name, present, x, y);
    }

    @Override
    //Pawn can move forward to the unoccupied square immediately in front of it on the same file, or on its first move it can advance two squares along the same file,
    //or the pawn can capture an opponent's piece on a square diagonally in front of it on an adjacent file,
    public boolean move(int x, int y) {

        if(this.present==0){
            if(x-this.x<0){
                return false;
            }
        }else if(this.present==1){
            if(x-this.x>0){
                return false;
            }
        }

        int dx= Math.abs(x-this.x);
        int dy = Math.abs(y - this.y);

        if(firststep==0){
            //first move can be two step
            if(this.y==y){
                if(dx==1){
                    return cango(ChessBoard.board[x][y]);
                }if(dx==2){
                    if(this.present==1){
                        //System.out.println("passed 1");
                        return cango(ChessBoard.board[x][y]) && cango(ChessBoard.board[x+1][y]);
                    }
                    //System.out.println("passed 1");
                    return cango(ChessBoard.board[x][y]) && cango(ChessBoard.board[x-1][y]);
                }
                //System.out.println("passed 2");
                return false;
            }else if((dy==1||dy==-1) && dx==1){
                return eatPoint(ChessBoard.board[x][y]);
            }
            return false;
        }else {
            if(dy==0 && dx ==1){
                return cango(ChessBoard.board[x][y]);
            }else if((dy==1||dy==-1) && dx==1){

                if(this.present==0) {
                    if (ChessBoard.board[x - 1][y] instanceof Pawn) {
                        if (ChessBoard.board[x - 1][y].firststep == 1 &&  x == 5) {
                            return true;
                        }
                    }
                }else if(this.present==1){
                    if (ChessBoard.board[x + 1][y] instanceof Pawn) {
                        if (ChessBoard.board[x +1][y].firststep == 1 && x == 2 ) {
                            return true;
                        }
                    }
                }
                return eatPoint(ChessBoard.board[x][y]);

            }
            return false;
        }
    }


    /** Determine whether the point passed by the chess piece is legal.
     * @param chess The point in board where the chess piece passes before reaching the end
     * @return true if this position is empty chess
     */
    public boolean cango(chessPiece chess){
        return chess.present == -1;
    }

    /**
     * Determine whether this chess can eat the chess at that point.
     * @param chess the point in board which is destination of this chess
     * @return true only if that point have chess with different side
     */
    public boolean eatPoint(chessPiece chess){
        return chess.present != this.present && chess.present != -1;
    }
}
