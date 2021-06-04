package com.example.photolib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ChainHead;
import androidx.fragment.app.DialogFragment;

import com.example.Chess.Chess;
import com.example.Chess.ChessBoard;

import static com.example.Chess.Chess.turn;
import static com.example.photolib.play_activity.pawnto;
import static com.example.photolib.play_activity.process;

public class promotion extends AppCompatActivity {

    private static final int WIN_CODE = 1;
    private static boolean turn= false;
   private static String cur;
   private static String prev;
   private static boolean flag=false;
   private static String Changeto;
    private static boolean flag2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        turn = bundle.getBoolean("turn");
        cur = bundle.getString("currentPosition");
        prev= bundle.getString("lastPosition");
        flag=bundle.getBoolean("flag");
        Changeto=bundle.getString("Changeto");
        flag2=bundle.getBoolean("flag2");
        setContentView(R.layout.activity_promotion);

       // System.out.println("flag:"+flag);
        //System.out.println("second pos:"+Changeto);
        if(flag==true&&Changeto!=null){
            RadioGroup rg = (RadioGroup)findViewById(R.id.chesschoose);
            rg.clearCheck();
            //System.out.println(rg);
            if(Changeto.equalsIgnoreCase("queen"))
                rg.check(R.id.queen);
            else if(Changeto.equalsIgnoreCase("rook"))
                rg.check(R.id.rook);
            else if(Changeto.equalsIgnoreCase("bishop"))
                rg.check(R.id.bishop);
            else if(Changeto.equalsIgnoreCase("horse"))
                rg.check(R.id.knight);
            Button commit=(Button)findViewById(R.id.commit);
            findViewById(commit.getId()).performClick();
            finish();
        }
        else if(flag==true&&flag2==true){
            flag=!flag;
            RadioGroup rg = (RadioGroup)findViewById(R.id.chesschoose);
            rg.clearCheck();
            rg.check(R.id.queen);
            Button commit=(Button)findViewById(R.id.commit);
            findViewById(commit.getId()).performClick();
            finish();
        }



    }
    public void commit(View view){
        RadioGroup rg = (RadioGroup)findViewById(R.id.chesschoose);
        int check= rg.getCheckedRadioButtonId();

        int curX=prev.charAt(1)-49;
        int curY=prev.charAt(0)-97;
        int tarX=cur.charAt(1)-49;
        int tarY=cur.charAt(0)-97;

        //System.out.println("curX:"+curX+"curY:"+curY);
        if(flag){
            replay.prev.setImageResource(R.drawable.transparent);
            replay.prev.setTag("empty");
            switch (check) {
                case R.id.queen:

                    if(turn){
                        replay.cur.setImageResource(R.drawable.wqueen);
                        replay.cur.setTag("wqueen");
                    }else {
                        replay.cur.setImageResource(R.drawable.bqueen);
                        replay.cur.setTag("bqueen");
                    }
                    Chess.promotion("queen",tarX,tarY,curX,curY);

                    if(Chess.KingChecked(ChessBoard.board,turn)) showCheckDialog();
                    break;
                case R.id.rook:
                    if(turn){
                        replay.cur.setImageResource(R.drawable.wwrook);
                        replay.cur.setTag("wrook");

                    }else {
                        replay.cur.setImageResource(R.drawable.bbrook);
                        replay.cur.setTag("brook");
                    }
                    Chess.promotion("rook",tarX,tarY,curX,curY);

                    if(Chess.KingChecked(ChessBoard.board,turn)) showCheckDialog();
                    break;
                case R.id.knight:
                    if(turn){
                        replay.cur.setImageResource(R.drawable.whorse);
                        replay.cur.setTag("whorse");
                    }else {
                        replay.cur.setImageResource(R.drawable.bhorse);
                        replay.cur.setTag("bhorse");
                    }
                    Chess.promotion("knight",tarX,tarY,curX,curY);

                    if(Chess.KingChecked(ChessBoard.board,turn)) showCheckDialog();
                    break;
                case R.id.bishop:
                    if(turn){
                        replay.cur.setImageResource(R.drawable.wbishop);
                        replay.cur.setTag("wbishop");
                    }else {
                        replay.cur.setImageResource(R.drawable.bbishop);
                        replay.cur.setTag("bbishop");
                    }
                    Chess.promotion("bishop",tarX,tarY,curX,curY);

                    if(Chess.KingChecked(ChessBoard.board,turn)) showCheckDialog();
                    break;
            }
            if(Chess.CheckMate(ChessBoard.board,!turn)) Checkmate2();
            finish();
        }
        else {
            play_activity.prev.setImageResource(R.drawable.transparent);
            play_activity.prev.setTag("empty");
            switch (check) {
                case R.id.queen:

                    if (turn) {
                        play_activity.pawnto = "queen";
                        play_activity.cur.setImageResource(R.drawable.wqueen);
                        play_activity.cur.setTag("wqueen");
                    } else {
                        play_activity.pawnto = "queen";
                        play_activity.cur.setImageResource(R.drawable.bqueen);
                        play_activity.cur.setTag("bqueen");
                    }
                    Chess.promotion("queen", tarX, tarY, curX, curY);

                    if(Chess.KingChecked(ChessBoard.board,turn)) showCheckDialog();
                    break;
                case R.id.rook:
                    if (turn) {
                        play_activity.cur.setImageResource(R.drawable.wwrook);
                        play_activity.cur.setTag("wrook");
                        play_activity.pawnto = "rook";
                    } else {
                        play_activity.pawnto = "rook";
                        play_activity.cur.setImageResource(R.drawable.bbrook);
                        play_activity.cur.setTag("brook");
                    }
                    Chess.promotion("rook", tarX, tarY, curX, curY);

                    if(Chess.KingChecked(ChessBoard.board,turn)) showCheckDialog();
                    break;
                case R.id.knight:
                    if (turn) {
                        play_activity.pawnto = "horse";
                        play_activity.cur.setImageResource(R.drawable.whorse);
                        play_activity.cur.setTag("whorse");
                    } else {
                        play_activity.pawnto = "knight";
                        play_activity.cur.setImageResource(R.drawable.bhorse);
                        play_activity.cur.setTag("bhorse");
                    }
                    Chess.promotion("knight", tarX, tarY, curX, curY);

                    if(Chess.KingChecked(ChessBoard.board,turn)) showCheckDialog();
                    break;
                case R.id.bishop:
                    if (turn) {
                        play_activity.pawnto = "bishop";
                        play_activity.cur.setImageResource(R.drawable.wbishop);
                        play_activity.cur.setTag("wbishop");
                    } else {
                        play_activity.pawnto = "bishop";
                        play_activity.cur.setImageResource(R.drawable.bbishop);
                        play_activity.cur.setTag("bbishop");
                    }
                    Chess.promotion("bishop", tarX, tarY, curX, curY);

                    if(Chess.KingChecked(ChessBoard.board,turn)) showCheckDialog();
                    break;
            }
            process.add(pawnto);
            if(Chess.CheckMate(ChessBoard.board,!turn)) Checkmate();
            finish();
        }
    }
    public void showCheckDialog(){
        DialogFragment newFragment1 = new errorDialog_fragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString(errorDialog_fragment.MESSAGE_KEY,
                "CHECK!");
        newFragment1.setArguments(bundle1);
        newFragment1.show(getSupportFragmentManager(), "badfields");
    }

    public void cancel(View view){
        finish();
    }
    public void Checkmate(){
        Bundle bundle3 = new Bundle();
        //Movie movie = movies.get(pos);
        String win;
        if(!turn) win="Black wins";
        else win="White wins";

        bundle3.putString(Conclusion.RESULT, win);
        Intent intent = new Intent(this, Conclusion.class);
        intent.putExtras(bundle3);
        startActivityForResult(intent,WIN_CODE);
    }
    public void Checkmate2(){
        String win;
        if(!turn) win="Black wins";
        else win="White wins";
        Bundle bundle5 = new Bundle();
        bundle5.putString(errorDialog_fragment.MESSAGE_KEY,
                win);
        DialogFragment newFragment5 = new errorDialog_fragment();
        newFragment5.setArguments(bundle5);
        newFragment5.show(getSupportFragmentManager(), "badfields");
    }
}