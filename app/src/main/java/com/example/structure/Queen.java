package com.example.structure;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */
import com.example.Chess.ChessBoard;
/**
 * Queen can move straight up or straight down or diagonal
 */
public class Queen extends chessPiece {

    /**
     * @param name Queen
     * @param present Which turn it belongs to. 0 for white, 1 for black
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Queen(String name, int present, int x, int y) {
        super(name, present, x, y);
    }

    @Override
    public boolean move( int x, int y) {
        int curx = this.x;
        int cury = this.y;

        int absx = Math.abs(curx-x);
        int absy = Math.abs(cury-y);

        int dx= x -curx;
        int dy = y-cury;
        if(absx==0 && absy != 0){//horizontal
            if(dy>0){//move right
                return pathclear(ChessBoard.board,curx,cury,x,y,1);
            }else if(dy<0){//move left
                return pathclear(ChessBoard.board,curx,cury,x,y,2);
            }
        }else if(absx!=0 && absy==0){//vertical
            if(dx>0){//move up
                return pathclear(ChessBoard.board,curx,cury,x,y,3);
            }else if(dx<0){
                return pathclear(ChessBoard.board,curx,cury,x,y,4);
            }
        }
        if(absx==absy){//move diagonal
            if(dx>0 && dy>0){//to top right
                return pathclear(ChessBoard.board,curx,cury,x,y,5);
            }else if(dx>0 && dy<0){//to top left
                return pathclear(ChessBoard.board,curx,cury,x,y,7);//exchange
            }else if(dx<0 && dy>0){//to down right
                return pathclear(ChessBoard.board,curx,cury,x,y,6);
            }else if(dx<0  && dy<0){ //to down left
                return pathclear(ChessBoard.board,curx,cury,x,y,8);
            }
        }
        return false;
    }
    /**
     * determine the direction of the move
     * @param board current board
     * @param oldx chess's current position x
     * @param oldy chess's current position y
     * @param x chess's target position x
     * @param y chess's target position y
     * @param dir Queen will go 1 top left  2 top right 3 down right 4 down left
     * @return true if bishop can go to new position
     */
    public boolean pathclear(chessPiece[][] board, int oldx, int oldy, int x, int y, int dir){
        switch (dir) {
//move right
            case 1 : {
                int newy = oldy+1;
                while (newy<y){
                    if(cango(board[oldx][newy])){
                        newy++;
                    }else {
                        return false;
                    }
                }
                return eatPoint(board[oldx][newy]);
                /*while (oldy < y) {
                    if (cango(board[oldx][oldy])) {
                        oldy++;
                    }else {
                        return false;
                    }

                }
                return eatPoint(board[oldx][oldy]);*/
            }
//move left
            case 2 : {
                int newy = oldy-1;
                while (newy>y){
                    if(cango(board[oldx][newy])){
                        newy--;
                    }else {
                        return false;
                    }
                }
                return eatPoint(board[oldx][newy]);
            }
//move up
            case 3 : {
                int newx = oldx+1;
                while (newx<x){
                    if(cango(board[newx][oldy])){
                        newx++;
                    }else {
                        return false;
                    }
                }
                return eatPoint(board[newx][oldy]);
            }
//move down
            case 4 : {
                int newx = oldx-1;
                while (newx>x){
                    if(cango(board[newx][oldy])){
                        newx--;
                    }else {
                        return false;
                    }
                }
                return eatPoint(board[newx][oldy]);
            }
//top right
            case 5 : {
                int newx=oldx+1;
                int newy = oldy+1;
                while(x!=newx && y!= newy){
                    if(cango(board[newx][newy])){
                        newx++;
                        newy++;
                    }else {
                        return false;
                    }
                }
                return eatPoint(board[newx][newy]);
                /*while (x != oldx && y != oldy) {
                    oldx++;
                    oldy++;
                    if (!cango(ChessBoard.board[oldx][oldy])) {
                        return false;
                    }
                }
                return eatPoint(ChessBoard.board[oldx][oldy]);*/
            }
//down right
            case 6 : {
                int newx=oldx-1;
                int newy = oldy+1;
                while(x!=newx && y!= newy){
                    if(cango(board[newx][newy])){
                        newx--;
                        newy++;
                    }else {
                        return false;
                    }
                }
                return eatPoint(board[newx][newy]);
            }
//top left
            case 7 : {
                int newx=oldx+1;
                int newy = oldy-1;
                while(x!=newx && y!= newy){
                    if(cango(board[newx][newy])){
                        newx++;
                        newy--;
                    }else {
                        return false;
                    }
                }
                return eatPoint(board[newx][newy]);
            }
//down left
            case 8 : {
                int newx=oldx-1;
                int newy = oldy-1;
                while(x!=newx && y!= newy){
                    if(cango(board[newx][newy])){
                        newx--;
                        newy--;
                    }else {
                        return false;
                    }
                }
                return eatPoint(board[newx][newy]);
            }
        }
        return false;
    }


    /** Determine whether this chess can eat the chess at that point.
     * @param chess the point in board which is destination of this chess
     * @return true if this position is empty chess or chess with different side
     */
    public boolean eatPoint(chessPiece chess){
        return chess.present != this.present;
    }

    /** Determine whether the point passed by the chess piece is legal.
     * @param chess The point in board where the chess piece passes before reaching the end
     * @return true if this position is empty chess
     */
    public boolean cango(chessPiece chess){
        return chess.present == -1;
    }
}
