package com.example.photolib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.Chess.Chess;
import com.example.Chess.ChessBoard;

import java.util.ArrayList;

import static com.example.Chess.Chess.turn;
import static com.example.Chess.ChessBoard.board;

public class replay extends AppCompatActivity {
    static int a=0;
    static String lastinput;
    static ImageButton prev;
    static ImageButton cur;
    static int status;
    private  static ImageButton[][] list=new ImageButton[8][8];//x:a   y:num
    //from previous page
    public static String GAME;
    String[] game;
    static int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);
        pos=0;
        Chess.initialize();
        //read from input
        //TextView name = findViewById(R.id.gameName);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            game=bundle.getStringArray(GAME);
            //System.out.println(game[0]+","+game[1]);
        }
        for (int i = 1; i < 9; i++) {
            int id;
            switch (i) {
                case 1:
                    for(int j=1;j<9;j++) {
                        id = getResources().getIdentifier("a" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 2:
                    for(int j=1;j<9;j++) {
                        id = getResources().getIdentifier("b" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 3:
                    for(int j=1;j<9;j++) {
                        id = getResources().getIdentifier("c" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 4:
                    for(int j=1;j<9;j++) {
                        id = getResources().getIdentifier("d" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 5:
                    for(int j=1;j<9;j++) {
                        id = getResources().getIdentifier("e" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 6:
                    for(int j=1;j<9;j++) {
                        id = getResources().getIdentifier("f" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 7:
                    for(int j=1;j<9;j++) {
                        id = getResources().getIdentifier("g" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
                case 8:
                    for(int j=1;j<9;j++) {
                        id = getResources().getIdentifier("h" + j, "id", getPackageName());
                        list[j-1][i-1]= (ImageButton) findViewById(id);
                    }
                    break;
            }
        }
    }

    public void Click(View view){
        a++;
        if(a==2){
            a=0;
            String currentInput =getResources().getResourceEntryName(view.getId());
            cur=(ImageButton)findViewById(view.getId());
            status= Chess.start(lastinput,currentInput);
           // System.out.println(status);
            //0 is false, 1 is true, 2 is enpassant, 3 is promotion,4 is castling
            switch (status) {
                //illegal move
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putString(errorDialog_fragment.MESSAGE_KEY,
                            "Illegal Move!");
                    DialogFragment newFragment = new errorDialog_fragment();
                    newFragment.setArguments(bundle);
                    newFragment.show(getSupportFragmentManager(), "badfields");
                    break; // does not quit activity, just returns from method
                //normal move
                case 1:
                    cur.setImageDrawable(prev.getDrawable());
                    cur.setTag(prev.getTag());
                    prev.setImageResource(R.drawable.transparent);
                    prev.setTag("empty");
                    turn=!turn;
                    break;
                case 2://2 is enpassant
                    String a=getResources().getResourceEntryName(view.getId());
                    int c=0,d=0;
                    for (int i=0;i<8;i++){
                        for (int j=0;j<8;j++){
                            if(list[i][j]==cur){
                                c=i;
                                d=j;
                                break;
                            }
                        }
                    }
                    if(prev.getTag()=="wpawn"){
                        list[c+1][d].setImageResource(R.drawable.transparent);
                        list[c+1][d].setTag("empty");
                    }else{
                        list[c-1][d].setImageResource(R.drawable.transparent);
                        list[c-1][d].setTag("empty");
                    }

                    cur.setImageDrawable(prev.getDrawable());
                    cur.setTag(prev.getTag());
                    prev.setImageResource(R.drawable.transparent);
                    prev.setTag("empty");
                    if(Chess.ifcheck) {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString(errorDialog_fragment.MESSAGE_KEY,
                                "CHECK!");
                        DialogFragment newFragment1 = new errorDialog_fragment();
                        newFragment1.setArguments(bundle1);
                        newFragment1.show(getSupportFragmentManager(), "badfields");
                    }
                    turn=!turn;
                    break;

                case 3:// 3 is promotion
                    Bundle bundle3 = new Bundle();
                    bundle3.putBoolean("turn",turn);

                    //bundle3.putString("turn",abc);
                    bundle3.putString("currentPosition",currentInput);
                    bundle3.putString("lastPosition",lastinput);
                    bundle3.putBoolean("flag",true);
                    bundle3.putString("Changeto",game[pos]);
                    System.out.println("first pos:"+game[pos]); pos++;
                    Intent intent2 = new Intent(this, promotion.class);
                    intent2.putExtras(bundle3);
                    startActivity(intent2);

                    turn=!turn;
                    break;


                case 4:// 4 is castling
                    int w=0,x=0,y=0,z=0;
                    for (int i=0;i<8;i++){
                        for (int j=0;j<8;j++){
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
                        Bundle bundle4 = new Bundle();
                        bundle4.putString(errorDialog_fragment.MESSAGE_KEY,
                                "CHECK!");
                        DialogFragment newFragment2 = new errorDialog_fragment();
                        newFragment2.setArguments(bundle4);
                        newFragment2.show(getSupportFragmentManager(), "badfields");
                    }
                    turn=!turn;
                 //   System.out.println("now is: "+turn);
                    break;

                case 5://5 is checkmate
                    //Movie movie = movies.get(pos);
                    String win;
                    if(!turn) win="Black wins";
                    else win="White wins";
                    Bundle bundle5 = new Bundle();
                    bundle5.putString(errorDialog_fragment.MESSAGE_KEY,
                            win);
                    DialogFragment newFragment5 = new errorDialog_fragment();
                    newFragment5.setArguments(bundle5);
                    newFragment5.show(getSupportFragmentManager(), "badfields");

                    break;
                case 6://6 is checked
                    Bundle bundle6 = new Bundle();
                    bundle6.putString(errorDialog_fragment.MESSAGE_KEY,
                            "CHECK!");
                    DialogFragment newFragment6 = new errorDialog_fragment();
                    newFragment6.setArguments(bundle6);
                    newFragment6.show(getSupportFragmentManager(), "badfields");
                    cur.setImageDrawable(prev.getDrawable());
                    cur.setTag(prev.getTag());
                    prev.setImageResource(R.drawable.transparent);
                    prev.setTag("empty");
                    turn=!turn;
                    break;
            }
        }
        else {
            lastinput = getResources().getResourceEntryName(view.getId());
            prev=(ImageButton)findViewById(view.getId());
           // System.out.println(getResources().getResourceEntryName(view.getId()));
        }
    }

    public void NextAction(View view){
        String from,to;
        if(pos<game.length) {
            from=game[pos];
            pos++;
            if(from.equalsIgnoreCase("resign")){
                Bundle bundle = new Bundle();
                String win;
                if(turn) win="Black Wins!";
                else win="White Wins!";
                bundle.putString(errorDialog_fragment.MESSAGE_KEY,
                        "RESIGN:"+win);
                DialogFragment newFragment = new errorDialog_fragment();
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "badfields");
            }
            else if(from.equalsIgnoreCase("draw")){
                Bundle bundle = new Bundle();
                bundle.putString(errorDialog_fragment.MESSAGE_KEY,
                        "GameEnds:DRAW");
                DialogFragment newFragment = new errorDialog_fragment();
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "badfields");
            }
            else if(from.equalsIgnoreCase("checkmate")){
                Bundle bundle = new Bundle();
                String win;
                if(turn) win="White Wins!";
                else win="Black Wins!";
                bundle.putString(errorDialog_fragment.MESSAGE_KEY,
                        "CHECKMATE:"+win);
                DialogFragment newFragment = new errorDialog_fragment();
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "badfields");
            }
            else{
                int x=from.charAt(1)-49;
                int y=from.charAt(0)-97;
                findViewById(list[x][y].getId()).performClick();
            //    System.out.println("from:"+list[x][y].getId());
            }
        }
        if(pos<game.length) {
            to=game[pos];
            pos++;
            if(to.equalsIgnoreCase("resign")){
                Bundle bundle = new Bundle();
                String win;
                if(turn) win="Black Wins!";
                else win="White Wins!";
                bundle.putString(errorDialog_fragment.MESSAGE_KEY,
                        "RESIGN:"+win);
                DialogFragment newFragment = new errorDialog_fragment();
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "badfields");
            }
            else if(to.equalsIgnoreCase("draw")){
                Bundle bundle = new Bundle();
                bundle.putString(errorDialog_fragment.MESSAGE_KEY,
                        "GameEnds:DRAW");
                DialogFragment newFragment = new errorDialog_fragment();
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "badfields");
            }
            else if(to.equalsIgnoreCase("checkmate")){
                Bundle bundle = new Bundle();
                String win;
                if(turn) win="White Wins!";
                else win="Black Wins!";
                bundle.putString(errorDialog_fragment.MESSAGE_KEY,
                        "CHECKMATE:"+win);
                DialogFragment newFragment = new errorDialog_fragment();
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "badfields");
            }
            else{
                int x=to.charAt(1)-49;
                int y=to.charAt(0)-97;
                ChessBoard.print(board);
               // System.out.println(x+"and "+y);
                findViewById(list[x][y].getId()).performClick();
            }
        }
        return;
    }

    public void BackAction(View view){
        finish();
    }


}