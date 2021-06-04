package com.example.structure;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */
import com.example.Chess.ChessBoard;

import com.example.Chess.ChessBoard;
import com.example.Chess.Chess;

/**
 * King can move one step in any direction
 */
public class King extends chessPiece {


    /**
     * @param name King
     * @param present Which turn it belongs to. 0 for white, 1 for black
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public King(String name, int present, int x, int y) {
        super(name, present, x, y);
    }

    @Override
    //King can moves one square in any direction
    public boolean move(int x, int y) {
        int dx = Math.abs(x - this.x);
        int dy = Math.abs(y - this.y);

        if (dx == dy && dx == 1) {
            return eatPoint(ChessBoard.board[x][y]);
        } else if (dx == 0 && dy == 1) {
            return eatPoint(ChessBoard.board[x][y]);
        } else if (dx == 1 && dy == 0) {
            return eatPoint(ChessBoard.board[x][y]);
        }else if(dx==0 && dy==2){
            return castling(x,y);
        }
        return false;
    }


    /** Determine whether this chess can eat the chess at that point.
     * @param chess the point in board which is destination of this chess
     * @return true if this position is empty chess of chess with different side
     */
    public boolean eatPoint(chessPiece chess) {
        return chess.present != this.present;
    }


    /**
     * King can move two step left or right and swich with rook when
     * 1.Neither the king nor the rook has previously moved during the game.
     * 2.There are no pieces between the king and the rook.
     * 3.The king is not in check, and will not pass through or land on any square attacked by an enemy piece.
     * @param x king's target position x
     * @param y king's target position y
     * @return true if king move two step left or right and castling which rook
     */
    public boolean castling(int x, int y) {
        if (this.move == true) {
            return false;
        }
        if (y == 2) {
            if(x==0) {
                if (!ChessBoard.board[0][0].isMoved() && ChessBoard.board[0][0] instanceof Rook) {
                    if (cango(ChessBoard.board[0][this.y - 3]) && cango(ChessBoard.board[0][this.y - 1]) && cango(ChessBoard.board[0][this.y - 2])) {
                        try {
                            return Chess.checkAttack(0, this.y, transfer()) && Chess.checkAttack(0, this.y - 1, transfer()) && Chess.checkAttack(0, this.y - 2, transfer());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }if(x==7){
                if (!ChessBoard.board[7][0].isMoved() && ChessBoard.board[7][0] instanceof Rook) {
                    if (cango(ChessBoard.board[7][this.y - 3]) && cango(ChessBoard.board[7][this.y - 1]) && cango(ChessBoard.board[7][this.y - 2])) {
                        try {
                            return Chess.checkAttack(7, this.y, transfer()) && Chess.checkAttack(7, this.y - 1, transfer()) && Chess.checkAttack(7, this.y - 2, transfer());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }


        } else if (y == 6) {
            if (x == 0) {
                if (!ChessBoard.board[0][7].isMoved() && ChessBoard.board[0][7] instanceof Rook) {
                    if (cango(ChessBoard.board[0][this.y + 1]) && cango(ChessBoard.board[0][this.y + 2])) {
                        try {
                            return Chess.checkAttack(0, this.y, transfer()) && Chess.checkAttack(0, this.y + 1, transfer()) && Chess.checkAttack(0, this.y + 2, transfer());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }else if(x==7){
                if (!ChessBoard.board[7][7].isMoved() && ChessBoard.board[7][7] instanceof Rook) {
                    if (cango(ChessBoard.board[7][this.y +1]) && cango(ChessBoard.board[7][this.y +2])) {
                        try {
                            return Chess.checkAttack(7, this.y, transfer()) && Chess.checkAttack(7, this.y +1, transfer()) && Chess.checkAttack(7, this.y + 2, transfer());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }

        }
        return false;
    }

    /** Determine whether the point passed by the chess piece is legal.
     * @param chess The point in board where the chess piece passes before reaching the end
     * @return true if this position is empty chess
     */
    public boolean cango(chessPiece chess) {
        return chess.present == -1;
    }

    /**
     * transfer present to boolean
     * @return true if present is equal 0 which mean this is black chess
     * @throws Exception when this.present is not (1 and 0)
     */
    public boolean transfer() throws Exception {
        if (this.present == 0) {
            return true;
        } else if (this.present == 1) {
            return false;
        }else {
            throw new Exception("wrong side input");
        }
    }
}