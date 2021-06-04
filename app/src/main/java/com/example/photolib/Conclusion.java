package com.example.photolib;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.example.photolib.play_activity.process;

public class Conclusion extends AppCompatActivity {

    public static final String RESULT="result";

    private EditText dir;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion);
        res=findViewById(R.id.result);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            res.setText(bundle.getString(RESULT));
        }


    }

    public void cancelHandler(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveHandler(View view){
        dir=findViewById(R.id.gameName);
        if(dir.getText().toString().trim().length() == 0){
            Bundle bundle1 = new Bundle();
            bundle1.putString(errorDialog_fragment.MESSAGE_KEY,
                    "Please Enter a Name!");
            DialogFragment newFragment1 = new errorDialog_fragment();
            newFragment1.setArguments(bundle1);
            newFragment1.show(getSupportFragmentManager(), "badfields");
            return;
        }
        String DIR=dir.getText().toString();
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();

        String filename = "games.txt";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(DIR);
        stringBuilder.append("|");
        stringBuilder.append(date);
        stringBuilder.append("|");
        for(String s:process){
            stringBuilder.append(s);
            stringBuilder.append("|");
        }
        String input=stringBuilder.toString();


        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename,MODE_APPEND);
            fos.write(input.getBytes());
            fos.write('\n');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}