package com.example.structure;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */
import static com.example.Chess.ChessBoard.board;
/**
 * bishop can move along diagonal
 */
public class Bishop extends chessPiece{
    public Bishop(String name, int present, int x, int y) {
        super(name, present, x, y);
    }


    @Override
    //bishop can move diagonal way
    public boolean move( int x, int y) {
        int curx =this.x;
        int cury = this.y;
        int dx= Math.abs(x-curx);
        int dy =Math.abs(y-cury);



        if(dx==dy){//go diagonal
            int accx = curx - x;
            int accy = cury - y;
            if (accx > 0 && accy < 0) { // top left  2 down right
                return pathclear(board, curx, cury, x, y, 3);
            } else if (accx < 0 && accy < 0) {//top right     2 top  right
                return pathclear(board, curx, cury, x, y, 2);
            } else if (accx < 0 && accy > 0) {//down right      2 top left
                return pathclear(board, curx, cury, x, y, 1);
            } else if (accx > 0 && accy > 0) {//down left        2 down left
                return pathclear(board, curx, cury, x, y, 4);
            }
        }

        return false;
    }


    /**
     * determine the direction of the move
     * @param board current boar
     * @param oldx chess's current position x
     * @param oldy chess's current position y
     * @param x chess's target position x
     * @param y chess's target position y
     * @param dir bishop will go 1 top left  2 top right 3 down right 4 down left
     * @return true if bishop can go to new position
     */
    public boolean pathclear(chessPiece[][] board, int oldx, int oldy, int x, int y, int dir){
        //1 top left  2 top right 3 down right 4 down left
        if(dir==1){
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
        }else if(dir==2){//got top right
            int newx= oldx+1;
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
        }else if(dir==3){//go down right
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
        }else if(dir==4){//go down left
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



    @Override
    public String toString() {
        return this.name ;
    }
}
