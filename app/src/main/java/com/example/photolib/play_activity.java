package com.example.photolib;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.Chess.Chess;
import com.example.Chess.ChessBoard;
import com.example.structure.King;
import com.example.structure.Pawn;
import com.example.structure.Rook;
import com.example.structure.chessPiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.Chess.Chess.KingChecked;
import static com.example.Chess.Chess.isChecked;
import static com.example.Chess.Chess.turn;
import static com.example.Chess.Chess.undo;
import static com.example.Chess.Chess.update;
import static com.example.Chess.ChessBoard.board;
import static com.example.Chess.ChessBoard.print;



public class play_activity extends AppCompatActivity {
    private GridLayout GY;
    static int a=0;
    static String lastinput;
    static ImageButton prev;
    static ImageButton cur;
    static ArrayList<String> process;
    static int status;
    static TextView textView;
    static String pawnto;
    static boolean canUndo =true;
    static List<chessPiece> possible;
    static boolean flag2=false;
    int i,j;

    public static final int RESIGN_CODE = 1;
    public static final int DRAW_CODE = 2;
    public static final int WIN_CODE = 3;

    private  static ImageButton[][] list=new ImageButton[8][8];//x:a   y:num
    //private static chessPiece[][] pre=board;
    private  static  chessPiece[][]pre=new chessPiece[8][8];
    private  static  chessPiece[][]prepre=new chessPiece[8][8];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chess_board);
        //GV=(GridView)findViewById(R.id.board);
        GY = (GridLayout) findViewById(R.id.board);
        process=new ArrayList<>();
        for (i = 1; i < 9; i++) {
            int id;
            switch (i) {
                case 1:
                    for(j=1;j<9;j++) {
                        id = getResources().getIdentifier("a" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 2:
                    for(j=1;j<9;j++) {
                        id = getResources().getIdentifier("b" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 3:
                    for(j=1;j<9;j++) {
                        id = getResources().getIdentifier("c" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 4:
                    for(j=1;j<9;j++) {
                        id = getResources().getIdentifier("d" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 5:
                    for(j=1;j<9;j++) {
                        id = getResources().getIdentifier("e" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 6:
                    for(j=1;j<9;j++) {
                        id = getResources().getIdentifier("f" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 7:
                    for(j=1;j<9;j++) {
                        id = getResources().getIdentifier("g" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 8:
                    for(j=1;j<9;j++) {
                        id = getResources().getIdentifier("h" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
            }
        }
    }


    public void pieceClick(View view){
        FragmentManager ft=getSupportFragmentManager();
        DialogFragment newFragment1 = new errorDialog_fragment();
        a++;
        if(a==2){
            a=0;
            canUndo=true;
            String currentInput =getResources().getResourceEntryName(view.getId());
            cur=(ImageButton)findViewById(view.getId());
            status=Chess.start(lastinput,currentInput);
            textView=(TextView) findViewById(R.id.turnView);
           // System.out.println(board);
            //0 is false, 1 is true, 2 is enpassant, 3 is promotion,4 is castling
            switch (status) {
                //illegal move
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putString(errorDialog_fragment.MESSAGE_KEY,
                            "Illegal Move!");
                    DialogFragment newFragment = new errorDialog_fragment();
                    newFragment.setArguments(bundle);
                    newFragment.show(ft, "badfields");
                    pre=prepre;
                    break; // does not quit activity, just returns from method
                //normal move
                case 1:
                    cur.setImageDrawable(prev.getDrawable());
                    cur.setTag(prev.getTag());
                    prev.setImageResource(R.drawable.transparent);
                    prev.setTag("empty");
                    turn=!turn;
                    if (turn) textView.setText("White Turn");
                    else {
                        textView.setText("Black Turn");
                    }
                    //save
                    process.add(lastinput);
                    process.add(currentInput);
                    break;
                case 2://2 is enpassant
                    String a=getResources().getResourceEntryName(view.getId());
                    int c=0,d=0;
                    for (i=0;i<8;i++){
                        for (j=0;j<8;j++){
                            if(list[i][j]==cur){
                                c=i;
                                d=j;
                                break;
                            }
                        }
                    }
                    if(prev.getTag().equals("wpawn")){
                        list[c-1][d].setImageResource(R.drawable.transparent);
                        list[c-1][d].setTag("empty");
                      //  System.out.println(prev.getTag());
                    }else{
                        list[c+1][d].setImageResource(R.drawable.transparent);
                        list[c+1][d].setTag("empty");
                    }

                    cur.setImageDrawable(prev.getDrawable());
                    cur.setTag(prev.getTag());
                    prev.setImageResource(R.drawable.transparent);
                    prev.setTag("empty");

                    if(Chess.ifcheck) {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString(errorDialog_fragment.MESSAGE_KEY,
                                "CHECK!");
                        newFragment1.setArguments(bundle1);
                        newFragment1.show(ft, "badfields");
                    }
                    turn=!turn;
                    if (turn) textView.setText("White Turn");
                    else {
                        textView.setText("Black Turn");
                    }
                    //save
                    process.add(lastinput);
                    process.add(currentInput);
                    break;

                case 3:// 3 is promotion
                    if(!flag2) {
                        Bundle bundle3 = new Bundle();
//                    String abc= new String();
//                    if(turn)abc="white";
//                    else abc="black";
                        bundle3.putBoolean("turn", turn);

                        //bundle3.putString("turn",abc);
                        bundle3.putString("currentPosition", currentInput);
                        bundle3.putString("lastPosition", lastinput);
                        Intent intent2 = new Intent(this, promotion.class);
                        intent2.putExtras(bundle3);
                        startActivity(intent2);
                    }


                    turn=!turn;
                    if (turn) textView.setText("White Turn");
                    else {
                        textView.setText("Black Turn");
                    }
                    //save
                    process.add(lastinput);
                    process.add(currentInput);
                    break;


                case 4:// 4 is castling
                    int w=0,x=0,y=0,z=0;
                    for (i=0;i<8;i++){
                        for (j=0;j<8;j++){
                            if(list[i][j]==cur){
                                w=i;
                                x=j;
                            }
                            if(list[i][j]==prev){
                                y=i;
                                z=j;
                            }
                        }
                    }
                    if(x>z){//cur zai prev youbian,xaingyou castling
                        list[w][x-1].setImageDrawable(list[w][x+1].getDrawable());
                        list[w][x-1].setTag(list[w][x+1].getTag());
                        list[w][x+1].setImageResource(R.drawable.transparent);
                        list[w][x+1].setTag("empty");

                        cur.setImageDrawable(prev.getDrawable());
                        cur.setTag(prev.getTag());
                        prev.setImageResource(R.drawable.transparent);
                        prev.setTag("empty");
                    }else{//you 1 car,zuo 2 kong, cur king, prev kong
                        list[w][x+1].setImageDrawable(list[w][x-2].getDrawable());
                        list[w][x+1].setTag(list[w][x-2].getTag());
                        list[w][x-2].setImageResource(R.drawable.transparent);
                        list[w][x-2].setTag("empty");

                        cur.setImageDrawable(prev.getDrawable());
                        cur.setTag(prev.getTag());
                        prev.setImageResource(R.drawable.transparent);
                        prev.setTag("empty");
                    }
                    if(Chess.ifcheck) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString(errorDialog_fragment.MESSAGE_KEY,
                                "CHECK!");
                        newFragment1.setArguments(bundle2);
                        newFragment1.show(ft, "badfields");
                    }
                    turn=!turn;
                    if (turn) textView.setText("White Turn");
                    else {
                        textView.setText("Black Turn");
                    }
                    //save
                    process.add(lastinput);
                    process.add(currentInput);
                    break;

                case 5://5 is checkmate
                    Bundle bundle3 = new Bundle();
                    //Movie movie = movies.get(pos);
                    String win;
                    //save
                    process.add(lastinput);
                    process.add(currentInput);
                    process.add("checkmate");
                    if(!turn) win="Black wins";
                    else win="White wins";

                    bundle3.putString(Conclusion.RESULT, win);
                    Intent intent = new Intent(this, Conclusion.class);
                    intent.putExtras(bundle3);
                    startActivityForResult(intent,WIN_CODE);

                    break;
                case 6://6 is checked
                    Bundle bundle4 = new Bundle();
                    bundle4.putString(errorDialog_fragment.MESSAGE_KEY,
                            "CHECK!");
                    newFragment1.setArguments(bundle4);
                    newFragment1.show(ft, "badfields");
                    ft.popBackStack();
                    cur.setImageDrawable(prev.getDrawable());
                    cur.setTag(prev.getTag());
                    prev.setImageResource(R.drawable.transparent);
                    prev.setTag("empty");
                    turn=!turn;
                    if (turn) textView.setText("White Turn");
                    else {
                        textView.setText("Black Turn");
                    }
                    //save
                    process.add(lastinput);
                    process.add(currentInput);
                    break;
            }
        }
        else {
            lastinput = getResources().getResourceEntryName(view.getId());
            prev=(ImageButton)findViewById(view.getId());
            prepre=pre;
            pre=new chessPiece[8][8];
            for(i=0;i<8;i++){
                for(j=0;j<8;j++){
                    pre[i][j]=board[i][j];
                }
            }
           // System.out.println(getResources().getResourceEntryName(view.getId()));
        }
    }

    public void resignListener(View  view) throws InterruptedException {
        Bundle bundle = new Bundle();
        //Movie movie = movies.get(pos);
        String win;
        process.add("resign");
        if(turn) win="Black wins";
        else win="White wins";
        bundle.putString(Conclusion.RESULT, win);
        Intent intent = new Intent(this, Conclusion.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,RESIGN_CODE);
    }

    public void drawListener(View  view)  {
        Bundle bundle = new Bundle();
        //Movie movie = movies.get(pos);
        process.add("draw");

        bundle.putString(Conclusion.RESULT, "DRAW");
        Intent intent = new Intent(this, Conclusion.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,DRAW_CODE);
    }

    public void aiListener(View view)  {
        flag2=true;
        Random r=new Random();
        int x,y;
        int present;
        int tarx,tary;
        if(turn==true) present=0;
        else present=1;
        List<chessPiece> allPossible=new ArrayList<>();
        for (i = 0; i <= 7; i++) {
            for (j = 0; j <= 7; j++) {
                if (board[i][j].getTurn() == turn) {
                    allPossible.add(board[i][j]);
                }
            }
        }
        while(true){
            int a=r.nextInt(allPossible.size());
            x=allPossible.get(a).getX();
            y=allPossible.get(a).getY();
            possible=board[x][y].PossiblePosition();
            if(board[x][y].present != present||possible.isEmpty()) continue;
            else {
                tarx = possible.get(0).getX();
                tary = possible.get(0).getY();
                if(!isChecked(board, turn, x, y, tarx, tary))
                    break;

            }
        }


        if(status==6) {

                for (i = 0; i <= 7; i++) {
                    for (j = 0; j <= 7; j++) {
                        if (board[i][j].getTurn() == turn) {
                            possible = board[i][j].PossiblePosition();
                            if(board[i][j] instanceof King && !possible.isEmpty()){
                                x = i;
                                y = j;
                                tarx = possible.get(0).getX();
                                tary = possible.get(0).getY();
                                findViewById(list[x][y].getId()).performClick();
                                findViewById(list[tarx][tary].getId()).performClick();
                                return;
                            }
                            for (chessPiece c : possible) {
                                if (!isChecked(board, turn, i, j, c.getX(), c.getY())) {
                                    x = i;
                                    y = j;
                                    tarx = c.getX();
                                    tary = c.getY();
                                    findViewById(list[x][y].getId()).performClick();
                                    findViewById(list[tarx][tary].getId()).performClick();
                                    return;
                                }
                            }
                        }
                    }
                }
        }
       // System.out.println(x+","+y+"and"+tarx+","+tary);
        findViewById(list[x][y].getId()).performClick();
      //  System.out.println(getResources().getResourceEntryName(list[x][y].getId()));
        findViewById(list[tarx][tary].getId()).performClick();
       // System.out.println(getResources().getResourceEntryName(list[tarx][tary].getId()));

        if(status==3){
           // System.out.println(x+",y="+y);
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(Character.toChars(y+97));
            stringBuilder.append(Character.toChars(x+49));
            String lastinput=stringBuilder.toString();
            StringBuilder stringBuilder2=new StringBuilder();
            stringBuilder2.append(Character.toChars(tary+97));
            stringBuilder2.append(Character.toChars(tarx+49));
            String currentInput=stringBuilder2.toString();
          //  System.out.println("last input="+lastinput+" current="+currentInput);
            Bundle bundle3 = new Bundle();
            bundle3.putBoolean("turn",turn);
            //bundle3.putString("turn",abc);
            bundle3.putString("currentPosition",currentInput);
            bundle3.putString("lastPosition",lastinput);
            bundle3.putBoolean("flag",true);
            bundle3.putBoolean("flag2",true);
            Intent intent2 = new Intent(this, promotion.class);
            intent2.putExtras(bundle3);
            startActivity(intent2);
            turn=!turn;
            flag2=false;
        }
        return;
    }

    public void undoListener(View  view)  {
        if(canUndo==false){
            return;
        }
        board=pre;
        for(i=0;i<8;i++){
            for(j=0;j<8;j++){
                if((board[i][j].y!=j && board[i][j] instanceof King) || (board[i][j].y!=j && board[i][j] instanceof Rook)){
                    board[i][j].setMove(false);
                    //System.out.println("set move to true");
                    //System.out.println(board[i][j].getName()+board[i][j].isMoved());
                }
                if(board[i][j].x!=i && board[i][j] instanceof Pawn){
                    board[i][j].firststep--;
                }
                board[i][j].x=i;
                board[i][j].y=j;
                if (board[i][j] instanceof Pawn && (board[i][j].firststep==2 ||board[i][j].firststep==3)) {
                    board[i][j].firststep--;
                }
            }
        }
        //board=pre;
        for(i=0;i<8;i++){
            for(j=0;j<8;j++){
                String name=pre[i][j].getName();
                switch (name){
                    case "wR":
                        list[i][j].setImageResource(R.drawable.wwrook);
                        list[i][j].setTag("wrook");
                        break;
                    case "wB":
                        list[i][j].setImageResource(R.drawable.wbishop);
                        list[i][j].setTag("wbishop");
                        break;
                    case "wQ":
                        list[i][j].setImageResource(R.drawable.wqueen);
                        list[i][j].setTag("wqueen");
                        break;
                    case "wK":
                        list[i][j].setImageResource(R.drawable.wking);
                        list[i][j].setTag("wking");
                        break;
                    case "wN":
                        list[i][j].setImageResource(R.drawable.whorse);
                        list[i][j].setTag("whorse");
                        break;
                    case "wP":
                        list[i][j].setImageResource(R.drawable.wpawn);
                        list[i][j].setTag("wpawn");
                        break;
                    case "bR":
                        list[i][j].setImageResource(R.drawable.bbrook);
                        list[i][j].setTag("brook");
                        break;
                    case "bB":
                        list[i][j].setImageResource(R.drawable.bbishop);
                        list[i][j].setTag("bbishop");
                        break;
                    case "bQ":
                        list[i][j].setImageResource(R.drawable.bqueen);
                        list[i][j].setTag("bqueen");
                        break;
                    case "bK":
                        list[i][j].setImageResource(R.drawable.bking);
                        list[i][j].setTag("bking");
                        break;
                    case "bP":
                        list[i][j].setImageResource(R.drawable.bpawn);
                        list[i][j].setTag("bpawn");
                        break;
                    case "bN":
                        list[i][j].setImageResource(R.drawable.bhorse);
                        list[i][j].setTag("bhorse");
                        break;
                    case "empty":
                        list[i][j].setImageResource(R.drawable.transparent);
                        list[i][j].setTag("empty");
                        break;
                }
            }
        }
        for (i = 0; i <= 7; i++) {
            for (j = 0; j<= 7; j++) {
                if (board[i][j] instanceof Pawn && (board[i][j].firststep==2 ||board[i][j].firststep==3)) {
                    board[i][j].setFirststep((board[i][j].firststep-1));
                }
            }
        }
        turn=!turn;
        if (turn) textView.setText("White Turn");
        else {
            textView.setText("Black Turn");
        }
        ChessBoard.print(board);
        ChessBoard.print(pre);
        process.remove(process.size()-1);
        process.remove(process.size()-1);
        canUndo=false;
        return;
    }

    public void showCheckDialog(){
        DialogFragment newFragment1 = new errorDialog_fragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString(errorDialog_fragment.MESSAGE_KEY,
                "CHECK!");
        newFragment1.setArguments(bundle1);
        newFragment1.show(getSupportFragmentManager(), "badfields");
    }

}
