package com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Authors: Sofya Murzakova and Komola Benzinger

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);
        final int score = save.getInt("Score", 0);

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back =(Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(GameLevels.this, MainActivity.class);
                startActivity(intent);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        //the button to go to the first level
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(v -> {
            try {
                if(level >= 1){
                    Intent intent = new Intent(GameLevels.this,Level1.class);
                    startActivity(intent);
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        //the button to go to the second level
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(v -> {
            try {
                if(level >= 2){
                    Intent intent = new Intent(GameLevels.this,Level2.class);
                    startActivity(intent);
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });


        //the button to go to the third level
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(v -> {
            try {
                if(level >= 3){
                    Intent intent = new Intent(GameLevels.this,Level3.class);
                    startActivity(intent);
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        //the button to go to the forth level
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setOnClickListener(v -> {
            try {
                if(level >= 4){
                    Intent intent = new Intent(GameLevels.this,Level4.class);
                    startActivity(intent);
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        //the button to go to the fifth level
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setOnClickListener(v -> {
            try {
                if(level >= 5){
                    Intent intent = new Intent(GameLevels.this, Level5.class);
                    startActivity(intent);
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setOnClickListener(v -> {
            try {
                if(level >= 6){
                    Intent intent = new Intent(GameLevels.this, Level6.class);
                    startActivity(intent);
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        final int [] x = {
                R.id.textView1,
                R.id.textView2,
                R.id.textView3,
                R.id.textView4,
                R.id.textView5,
                R.id.textView6
        };

        for(int i = 1; i < level; i++){
            TextView tv = findViewById(x[i]);
            TextView sv = findViewById(R.id.scoreText);
            tv.setText("" + (i + 1));
            tv.setEnabled(true);
            sv.setText("Score: " + score + "/" + (x.length * 20));
        }
    }

    //System button "Back"  (at the bottom, symbol looks like<|), function - back to the mainActivity
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}