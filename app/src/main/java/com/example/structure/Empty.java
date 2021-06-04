package com.example.structure;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */

/**
 * Empty point on chessboard
 */
public class Empty extends chessPiece {
    public Empty(String name, int present,int x, int y) {
        super(name, present, x, y);
    }

    @Override
    public boolean move(int x, int y) {
        return false;
    }


    @Override
    public String toString() {
        if(y%2==0){
            if(x%2==0){ return "##"; }
            else return "  ";
        }
        else{
            if (x%2==1) {return "##";}
            else return "  ";
        }
    }
}
