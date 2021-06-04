package com.example.photolib;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

class term{
    Date date;
    String name;
    String[] moves;
    public term(String name,String date,String[] moves) throws ParseException {
        this.name=name;
        this.moves=moves;
        DateFormat fmt =new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
        Date dd=fmt.parse(date);
        this.date=dd;
    }
    public String toString() {   // used by ListView
        return "GAME:"+name;
    }
    public String getString() {
        return name ;
    }
}

public class pastgames extends AppCompatActivity {

    private ListView listView;
    private ArrayList<term> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastgames);

        try {
            FileInputStream fis = openFileInput("games.txt");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(fis));
            String play = null;
            games = new ArrayList<term>();
            while ((play = br.readLine()) != null) {
                String[] tokens = play.split("\\|");
                String[] moves=new String[tokens.length-2];
                for(int i=0;i<moves.length;i++){
                    moves[i]=tokens[i+2];
                    //System.out.print(moves[i]);
                }
                term tmp=new term(tokens[0],tokens[1],moves);
                games.add(tmp);
                games.sort(new Comparator<term>() {
                    @Override
                    public int compare(term o1, term o2) {
                        if(o1.name.equalsIgnoreCase(o2.name))
                            return o1.date.compareTo(o2.date);
                        return o1.name.compareTo(o2.name);
                    }
                });
            }
        } catch (IOException | ParseException e) {
            File a=new File("games.txt");
            games = new ArrayList<term>();
        }
        listView = findViewById(R.id.games_list);
        listView.setAdapter(
                new ArrayAdapter<term>(this, R.layout.chess_item, games));

        // show movie for possible edit when tapped
        listView.setOnItemClickListener((p, V, pos, id) ->
                replay(pos));
    }

    //replay the game
    public void replay(int pos){
        Bundle bundle = new Bundle();
        term game = games.get(pos);
        System.out.println("game name is:"+game.name);
        bundle.putStringArray(replay.GAME,game.moves);
        Intent intent = new Intent(this, replay.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}