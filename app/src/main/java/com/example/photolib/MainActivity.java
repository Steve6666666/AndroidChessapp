package com.example.photolib;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.GridLayout;

import com.example.Chess.Chess;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void playNew(View view){
        Chess.initialize();
        Intent intent = new Intent(this, play_activity.class);
        startActivity(intent);
    }

    public void replayGame(View view){
        Chess.initialize();
        Intent intent = new Intent(this, pastgames.class);
        startActivity(intent);
    }
}
