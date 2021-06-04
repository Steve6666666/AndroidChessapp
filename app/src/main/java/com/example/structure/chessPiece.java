package com.example.structure;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */
import java.util.ArrayList;
import java.util.List;
import static com.example.Chess.ChessBoard.board;
/**
 * Abstract class for chess pieces
 */
public abstract class chessPiece {
    /**
     * The name of the Piece
     */
    String name;
    /**
     * The x and y coordinate of the Piece
     */
    public int x, y;//mark position of chess in the board
    /**
     * Which turn the Piece belongs to
     */
    public int present;// decide this chess belong black or white


    /**
     * Decide whether the chess have moved
     * must be override in each subclass
     * @param x chess's target position x
     * @param y chess's target position y
     * @return true if the piece can go to new position
     */
    public abstract boolean move( int x ,int y);

    /**
     * decide whether the chess have moved
     */
    boolean move=false;
    List<chessPiece> possibleList=new ArrayList<>();

    /**
     * Check if a certain chess piece has moved or not
     * @return returns true if the chess has moved
     */
    public boolean isMoved() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
    /**
     * Find all possible moving positions for the chess piece.
     * For specific chess pieces,the moving style is different.
     * The target spot must be either empty or can be eaten by this chess piece.
     * There should not be any pieces in the way of moving.
     * The method should be overwritten by generic chess pieces.
     * @return returns a arraylist of all possible positions on the board that this piece can move to
     */
    public List<chessPiece> PossiblePosition(){
        possibleList.clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(this.move(i,j)){
                    possibleList.add(board[i][j]);
                }
            }
        }
        return possibleList;
    }

    /**
     * Check if a certain chess piece has never moved
     */
    public int firststep=0;

    public int getFirststep() {
        return firststep;
    }

    public void setFirststep(int firststep) {
        this.firststep = firststep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public chessPiece(String name, int present, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.present = present;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Check the turn of a certain chess piece
     * @return returns true if the chess is white, false if the chess is black
     */
    public boolean getTurn(){
        if(present==0){
            return true;
        }
        else return false;
    }
}
