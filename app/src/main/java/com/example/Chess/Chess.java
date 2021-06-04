package com.example.Chess;
/**
 * @author XiYue Zhang
 * @author ChuXu Song
 */

import com.example.structure.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import static com.example.Chess.ChessBoard.board;
import static com.example.Chess.ChessBoard.print;

/**
 * Start the game
 */
public class Chess {
    public static boolean turn;
    static boolean draw;
    public static boolean ifcheck;
   // static List<chessPiece> possible;
    public static void initialize(){
        chessPiece[][] board= ChessBoard.initialize();
        //print(board);
        turn=true;
        draw=false;
    }
//    public static void main(String[] args) throws IOException {
//        chessPiece[][] board= ChessBoard.initialize();
//        //print(board);
//        boolean turn=true;
//        boolean draw=false;
//        int i,j;
//        while(true){
//            String start;
//            String end;
//            String extra="";
//            if(turn){
//                System.out.println();
//                System.out.print("White's move: ");
//            }
//            else {
//                System.out.println();
//                System.out.print("Black's move: ");
//            }
//            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
//            String in=read.readLine();
//            String[] input=in.split(" ");
//            start=input[0];
//            if (draw==true&&start.equals("draw")) {
//                endGame(turn, true);
//            }
//            else if(start.equals("resign")){
//                endGame(!turn,false);
//            }
//            end=input[1];
//            int curX=start.charAt(1)-49;
//            int curY=start.charAt(0)-97;
//            int tarX=end.charAt(1)-49;
//            int tarY=end.charAt(0)-97;
//            //check for the third input
//
//
//            //step1:check if x and y is out of bound
//            //step2: check if the move puts the king in check
//            //step3: check if the pass is valid
//            //step4: check if turn is correct
//            while(curX>8||curY>8||tarX>8||tarY>8||!board[curX][curY].move(tarX,tarY)||isChecked(board,turn, curX, curY,tarX,tarY)||!CheckTurn(board,turn,curX,curY)){
//                System.out.print("Illegal move, try again: ");
//                in=read.readLine();
//                input=in.split(" ");
//                start=input[0];
//                if(start.equals("resign")){
//                    endGame(!turn,false);
//                }
//                end=input[1];
//                curX=start.charAt(1)-49;
//                curY=start.charAt(0)-97;
//                tarX=end.charAt(1)-49;
//                tarY=end.charAt(0)-97;
//            }
//            //ask for draw
//            if(input.length==3){
//                extra=input[2];
//                if(extra.equals("draw?")){
//                    endGame(turn, true);
//                }
//                else
//                    System.out.println(extra);
//            }
//
//
//
//            //check for enpassent and promotion
//            if(board[curX][curY] instanceof Pawn){
//                if(board[curX][curY].firststep==0){
//                    board[curX][curY].setFirststep(1);
//                }
// //               System.out.println(board[curX][curY].move(tarX,tarY));
////                System.out.println(board[tarX - 1][tarY].getName()+board[tarX - 1][tarY].firststep);
////                System.out.println(board[tarX - 1][tarY].getName()+board[tarX + 1][tarY].firststep);
//                //enpassent
//                if(board[curX][curY].move(tarX,tarY)&&board[tarX][tarY].present==-1){
//                    //move to up-right or up-left
//                    if((tarY-curY<0&&tarX-curX>0)||(tarY-curY>0&&tarX-curX>0)) {
//                        board[tarX - 1][tarY] = new Empty("empty", -1, tarX - 1, tarY);
//                    }
//                    else if ((tarY-curY>0&&tarX-curX<0)||(tarY-curY<0&&tarX-curX<0))
//                        board[tarX+1][tarY]=new Empty("empty",-1,tarX+1,tarY);
//                }
//                //reach the edge of opponents'
//                if(turn==true) {
//                    if (tarX == 7) {
//                        switch (extra) {
//                            case "N":
//                                board[curX][curY] = new Knight("wN", 0, curX, curY);
//                                break;
//                            case "B":
//                                board[curX][curY] = new Bishop("wB", 0, curX, curY);
//                                break;
//                            case "R":
//                                board[curX][curY] = new Rook("wR", 0, curX, curY);
//                                break;
//                            default:
//                                board[curX][curY] = new Queen("wQ", 0, curX, curY);
//                        }
//                    }
//                }
//                else{
//                    if(tarX == 0){
//                    switch (extra) {
//                            case "N":
//                                board[curX][curY] = new Knight("bN", 1, curX, curY);
//                                break;
//                            case "B":
//                                board[curX][curY] = new Bishop("bB", 1, curX, curY);
//                                break;
//                            case "R":
//                                board[curX][curY] = new Rook("bR", 1, curX, curY);
//                                break;
//                            default:
//                                board[curX][curY] = new Queen("bQ", 1, curX, curY);
//                    }
//                }
//                }
//            }
//            //change status for Pawn when whatever move happens
//            for (i = 0; i <= 7; i++) {
//                    for (j = 0; j<= 7; j++) {
//                        if (board[i][j] instanceof Pawn && (board[i][j].firststep==1 ||board[i][j].firststep==2)&& board[i][j]!=board[curX][curY]) {
//                            board[i][j].firststep++;
//                        }
//                    }
//                }
//            if(board[curX][curY] instanceof King){
//                //move to the right 2 steps
//                if (board[curX][curY].move(tarX,tarY)&&tarY-curY==2){
//                    update(board,curX,7,tarX,tarY-1);
//                }
//                else if (board[curX][curY].move(tarX,tarY)&&tarY-curY==-2){
//                    update(board,curX,0,tarX,tarY+1);
//                }
//            }
//
//            update(board,curX,curY,tarX,tarY);
//            board[curX][curY].setMove(true);
//
//            //check if opponents' king is in check
//            if(CheckMate(board,turn)){
//                endGame(turn,false);
//            }
//           if(KingChecked(board,turn)){
//                System.out.println("Check");
//            }
//            System.out.println();
//            //print(board);
//            turn=!turn;
//        }
//    }

    //return 0 is false, 1 is true, 2 is enpassant, 3 is promotion,4 is castling
    public static int start(String prev,String cur){
        int i,j;
        int flag=1;
        int curX=prev.charAt(1)-49;
        int curY=prev.charAt(0)-97;
        int tarX=cur.charAt(1)-49;
        int tarY=cur.charAt(0)-97;
     //   System.out.println(curX+","+curY+"and "+tarX+","+tarY);
        if(curX>8||curY>8||tarX>8||tarY>8||!board[curX][curY].move(tarX,tarY)||isChecked(board,turn, curX, curY,tarX,tarY)||!CheckTurn(board,turn,curX,curY)){
           // System.out.println(!board[curX][curY].move(tarX,tarY)+","+board[curX][curY]+","+board[tarX][tarY]+",x="+board[curX][curY].x+",y="+board[curX][curY]);
           // System.out.println(isChecked(board,turn, curX, curY,tarX,tarY));
           // System.out.println(!CheckTurn(board,turn,curX,curY));
            return 0;
        }
        //check for enpassent and promotion
        if(board[curX][curY] instanceof Pawn) {
            if (board[curX][curY].firststep == 0) {
                board[curX][curY].setFirststep(1);
            }
            //               System.out.println(board[curX][curY].move(tarX,tarY));
//                System.out.println(board[tarX - 1][tarY].getName()+board[tarX - 1][tarY].firststep);
//                System.out.println(board[tarX - 1][tarY].getName()+board[tarX + 1][tarY].firststep);
            //enpassent
            if (board[curX][curY].move(tarX, tarY) && board[tarX][tarY].present == -1) {
                //move to up-right or up-left
                if ((tarY - curY < 0 && tarX - curX > 0) || (tarY - curY > 0 && tarX - curX > 0)) {
                    board[tarX - 1][tarY] = new Empty("empty", -1, tarX - 1, tarY);
                    flag = 2;
                } else if ((tarY - curY > 0 && tarX - curX < 0) || (tarY - curY < 0 && tarX - curX < 0)) {
                    board[tarX + 1][tarY] = new Empty("empty", -1, tarX + 1, tarY);
                    flag = 2;
                }
            }
            //reach the edge of opponents'
            if ( tarX == 7 || tarX == 0) {
                flag = 3;
            }
            //change status for Pawn when whatever move happens
            for (i = 0; i <= 7; i++) {
                for (j = 0; j <= 7; j++) {
                    if (board[i][j] instanceof Pawn && (board[i][j].firststep == 1 || board[i][j].firststep == 2) && board[i][j] != board[curX][curY]) {
                        board[i][j].firststep++;
                    }
                }
            }
        }
        if(board[curX][curY] instanceof King){
            //move to the right 2 steps
            if (board[curX][curY].move(tarX,tarY)&&tarY-curY==2){
                board[curX][curY].setMove(true);
                update(board,curX,7,tarX,tarY-1);
                flag=  4;
            }
            else if (board[curX][curY].move(tarX,tarY)&&tarY-curY==-2){
                board[curX][curY].setMove(true);
                update(board,curX,0,tarX,tarY+1);
                flag=  4;
            }
        }

        board[curX][curY].setMove(true);
        update(board,curX,curY,tarX,tarY);

        if(CheckMate(board,turn)){
            return 5;
            //endGame(turn,false);
        }

        if(KingChecked(board,turn)){
            if(flag!=1){
                ifcheck=true;
                return flag;
            }
            return 6;
            //System.out.println("Check");
        }

        //check if opponents' king is in check


        //System.out.println();
        //print(board);
        //turn=!turn;
        //print(board);
        return flag;
    }


    /**
     * A check to determine if the player's turn is correct
     * @param board Current board
     * @param x Chess's current x-position
     * @param y Chess's current y-position
     * @param turn True for white's turn, false for black's turn
     * @return True if the user's turn is correct
     */
    public static boolean CheckTurn(chessPiece[][] board,boolean turn,int x, int y){
        if((board[x][y].present==0&&turn==true)||(board[x][y].present==1&&turn==false)){
            return true;
        }
        return false;
    }


    public static void empassant(String prev,String cur,String extra) {
        int curX=prev.charAt(1)-49;
        int curY=prev.charAt(0)-97;
        int tarX=cur.charAt(1)-49;
        int tarY=cur.charAt(0)-97;
        if (tarX == 7) {
            switch (extra) {
                case "N":
                    board[curX][curY] = new Knight("wN", 0, curX, curY);
                    break;
                case "B":
                    board[curX][curY] = new Bishop("wB", 0, curX, curY);
                    break;
                case "R":
                    board[curX][curY] = new Rook("wR", 0, curX, curY);
                    break;
                default:
                    board[curX][curY] = new Queen("wQ", 0, curX, curY);
            }
        }

        else{
                    if(tarX == 0){
                    switch (extra) {
                    case "N":
                    board[curX][curY] = new Knight("bN", 1, curX, curY);
                    break;
                    case "B":
                    board[curX][curY] = new Bishop("bB", 1, curX, curY);
                    break;
                    case "R":
                    board[curX][curY] = new Rook("bR", 1, curX, curY);
                    break;
                    default:
                        board[curX][curY] = new Queen("bQ", 1, curX, curY);
        }
        }
        }
    }

    /**
     * Check if the move place the opponents' King in check
     * @param board Current board
     * @param turn True for white's turn, false for black's turn
     * @return true if opponents' King is in check, false otherwise
     */
    public static boolean KingChecked(chessPiece[][] board,boolean turn){
        King king = null;
        int i,j;
        for (i = 0; i <= 7; i++) {
            for (j = 0; j<= 7; j++) {
                if (board[i][j].getTurn() == !turn && board[i][j] instanceof King) {
                    king = (King) board[i][j];
                }
            }
        }
        for (i = 0; i <= 7; i++) {
            for (j = 0; j<= 7; j++) {
                if (board[i][j].getTurn() == turn && (board[i][j].present!=-1)) {
                 //   possible=board[i][j].PossiblePosition();
                    for (chessPiece c:board[i][j].PossiblePosition()) {
                        if((c.getX() == king.getX()) && (c.getY()== king.getY())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the move will place my King in check, which is a illegal move
     * @param board Current board
     * @param turn True for white's turn, false for black's turn
     * @param x Chess's current x-position
     * @param y Chess's current y-position
     * @param tarX Chess's target x-position
     * @param tarY Chess's target y-position
     * @return True if my King is in check, false otherwise
     */
    public static boolean isChecked(chessPiece[][] board,boolean turn, int x, int y,int tarX,int tarY) {
        chessPiece prev=board[tarX][tarY];
        //System.out.println(tmp.getName());
        update(board,x,y,tarX,tarY);
        int i,j;
        King king = null;
        for (i = 0; i <= 7; i++) {
            for (j = 0; j<= 7; j++) {
                if (board[i][j].getTurn() == turn && board[i][j] instanceof King) {
                    king = (King) board[i][j];
                }
            }
            if(king!=null) break;
        }
        for (i = 0; i <= 7; i++) {
            for (j = 0; j<= 7; j++) {
                if (board[i][j].getTurn() == !turn && board[i][j].present!=-1) {
         //           possible=board[i][j].PossiblePosition();
//                    System.out.println(board[i][j].getClass().getName()+":");
                    for (chessPiece c:board[i][j].PossiblePosition()) {
//                        System.out.println(c.getX()+","+c.getY());
                        if(c.getX()== king.getX()&&c.getY()== king.getY()){
                            undo(board,x,y,tarX,tarY,prev);
                            return true;
                        }
                    }
                }
            }
        }
        undo(board,x,y,tarX,tarY,prev);
        return false;
    }


    /**
     * Check if the king has any possible moves to avoid from being captured
     * @param board Current board
     * @param turn True for white's turn, false for black's turn
     * @return True if checkmate occurs, false otherwise
     */
    public static boolean CheckMate(chessPiece[][] board,boolean turn) {
        int i,j;
        King king = null;
        for (i = 0; i <= 7; i++) {
            for (j = 0; j <= 7; j++) {
                if (board[i][j].getTurn() == turn && board[i][j] instanceof King) {
                    king = (King) board[i][j];
                }
            }
        }
        if (KingChecked(board, turn)) {
            for (i = 0; i <= 7; i++) {
                for (j = 0; j <= 7; j++) {
                    if (board[i][j].getTurn() != turn && board[i][j].present!=-1) {
                        for (int i2 = 0; i2 <= 7; i2++) {
                            for (int j2 = 0; j2 <= 7; j2++) {
                                if(board[i][j].move(i2,j2)){
                                    chessPiece prev=board[i2][j2];
                                    update(board,i,j,i2,j2);
                                    if(!KingChecked(board, turn)){
                                        undo(board,i,j,i2,j2,prev);
                                        return false;
                                    }
                                    undo(board,i,j,i2,j2,prev);
                                }
                            }
                        }
                    }
                }
            }
           // System.out.println("Checkmate");
            return true;
        }
        return false;
    }

    /**
     * Move the current chess point to target position
     * @param board Current board
     * @param curX Chess's current x-position
     * @param curY Chess's current y-position
     * @param tarX Chess's target x-position
     * @param tarY Chess's target y-position
     */
    public static void update(chessPiece[][] board, int curX,int curY, int tarX, int tarY){
        chessPiece old = board[curX][curY];
        board[tarX][tarY]= old;
        board[tarX][tarY].x=tarX;
        board[tarX][tarY].y=tarY;
        board[curX][curY]=new Empty("empty",-1,curX,curY);
        /*board[tarX][tarY]=board[curX][curY];
        board[tarX][tarY].setX(tarX);
        board[tarX][tarY].setY(tarY);
        board[curX][curY]=new Empty("empty",-1,curX,curY);*/
    }

    /**
     * Move the chess point back to previous position
     * @param board Current board
     * @param curX Chess's current x-position
     * @param curY Chess's current y-position
     * @param tarX Chess's target x-position
     * @param tarY Chess's target y-position
     * @param tmp Previous information of the chess at this position
     */
    public static void undo(chessPiece[][] board, int curX,int curY, int tarX, int tarY,chessPiece tmp){
        chessPiece newc= board[tarX][tarY];
        board[curX][curY]=newc;
        board[curX][curY].x=curX;
        board[curX][curY].y=curY;
        board[tarX][tarY]=tmp;
        newc=null;
        /*board[curX][curY]=board[tarX][tarY];
        board[curX][curY].setX(curX);
        board[curX][curY].setY(curY);
        board[tarX][tarY]=tmp;*/
    }

    /**
     * determine if the point can be attack by other side chess
     * @param x chess's x position
     * @param y chess's y position
     * @param turn turn true if
     * @return no other chess can attack this point
     */
    public static boolean checkAttack(int x, int y, boolean turn){
        int i,j;
        for (i = 0; i <8 ; i++) {
            for (j = 0; j <8 ; j++) {
                if(board[i][j].getTurn()!=turn && board[i][j].present!=-1 && (!(board[i][j] instanceof King)||((board[i][j] instanceof King)&& board[i][j].isMoved()))){
                 //   possible = board[i][j].PossiblePosition();
                    for(chessPiece c: board[i][j].PossiblePosition()
                    ){
                        if(c.getX()==x && c.getY()==y){
                            return false;//is attacked
                        }
                    }
                }
            }
        }
        return true;//no attack at this point
    }


    /**
     * Determines if it is the end of a game
     * @param team True is white, false is black
     * @param draw True if outcome is a draw
     */
    public static void endGame(boolean team, boolean draw) { // input is winner, draw is true if draw
        if (draw) System.out.println("draw");
        else if (team) System.out.println("White wins");
        else System.out.println("Black wins");
        System.exit(0);
    }

    public static void promotion(String a, int tx,int ty,int cx,int cy){
        board[cx][cy]=new Empty("empty",-1,cx,cy);
        if(!turn) {
            switch (a) {
                case "queen":
                    board[tx][ty]= new Queen("wQ", 0, tx, ty);
                    break;
                case "knight":
                    board[tx][ty]= new Knight("wN", 0, tx, ty);
                    break;
                case "bishop":
                    board[tx][ty]= new Bishop("wB", 0, tx, ty);
                    break;
                case "rook":
                    board[tx][ty]= new Rook("wR", 0, tx, ty);
                    break;
            }
        }else{
            switch (a) {
                case "queen":
                    board[tx][ty]= new Queen("bQ", 1, tx, ty);
                    break;
                case "knight":
                    board[tx][ty]= new Knight("bN", 1, tx, ty);
                    break;
                case "bishop":
                    board[tx][ty]= new Bishop("bB", 1, tx, ty);
                    break;
                case "rook":
                    board[tx][ty]= new Rook("bR", 1, tx, ty);
                    break;
            }
        }


    }

}
