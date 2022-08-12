package com.example.quiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

// Authors: Sofya Murzakova and Komola Benzinger

public class Level5 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //variable for the left image + text
    public int numRight; //variable for the right image + text
    Array array = new Array();
    Random random = new Random();
    public int count = 0; //counter of correct answers

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        // creating a variable (text_levels)
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level5); //set text

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //the code that rounds the corners (left)
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //the code that rounds the corners (right)
        img_right.setClipToOutline(true);

        //path to the left TextView
        final TextView text_left = findViewById(R.id.text_left);

        //path to the right TextView
        final TextView text_right = findViewById(R.id.text_right);

        //expand the game to full screen
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //opening a dialog box at the beginning of the game
        dialog = new Dialog(this);      //creating new dialog box
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);       //hiding the title
        dialog.setContentView(R.layout.previewdialog);      // the path to the layout of the dialog box
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));     // background of the dialog box is transparent
        dialog.setCancelable(false);        //the dialog box cannot be closed with the back button

        // set image into the dialog box
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg5);

        //set background for dialog box
        LinearLayout dialogfon = (LinearLayout) dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackground4);

        //set a task of the second level
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelfive);

        //the button that closes the dialog box
        TextView btnClose = (TextView) dialog.findViewById(R.id.btnclose);
        btnClose.setOnClickListener(v -> {
            //handle button click
            try{
                //go back to level selection
                Intent intent = new Intent(Level5.this, GameLevels.class);
                startActivity(intent);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }
            dialog.dismiss(); // close dialog box
        });

        //the button Continue
        Button btnContinue = (Button)dialog.findViewById(R.id.btncontinue);
        btnContinue.setOnClickListener(v -> {
            dialog.dismiss(); // close dialog box
        });

        dialog.show(); // show dialog box
        //______________________________________________

        //opening a dialog box at the ending of the game
        dialogEnd = new Dialog(this);       //creating new dialog box
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);        //hiding the title
        dialogEnd.setContentView(R.layout.dialogend);       // the path to the layout of the dialog box
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));      // background of the dialog box is transparent
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);         //the dialog box cannot be closed with the back button

        //set dialogfon
        LinearLayout dialogfonEnd = (LinearLayout)dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackground4);

        //interesting fact
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.levelfiveEnd);

        //the button that closes the dialog box
        TextView btnClose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnClose2.setOnClickListener(v -> {
            //handle button click
            try{
                //go back to level selection
                Intent intent = new Intent(Level5.this, GameLevels.class);
                startActivity(intent);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }
            dialog.dismiss(); // close dialog box
        });

        //the button Continue
        Button btnContinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btnContinue2.setOnClickListener(v -> {
            try{
                Intent intent = new Intent(Level5.this, Level6.class);
                startActivity(intent);
                finish();
            }catch(Exception e){
                e.printStackTrace();
            }
            dialog.dismiss(); // close dialog box
        });
        //______________________________________________

        //Back Button (top)
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Level5.this, GameLevels.class);
                startActivity(intent);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        //Array for the game's progress
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3,R.id.point4,R.id.point5,
                R.id.point6,R.id.point7,R.id.point8,R.id.point9,R.id.point10,
                R.id.point11, R.id.point12, R.id.point13,R.id.point14,R.id.point15,
                R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20,
        };

        //connect animation
        final Animation a = AnimationUtils.loadAnimation(Level5.this, R.anim.alpha);

        numLeft = random.nextInt(10);       // generating a random number from zero to nine
        img_left.setImageResource(array.images5[numLeft]);      // getting the image from the array
        text_left.setText(array.texts5[numLeft]);       //getting the text from the array

        numRight = random.nextInt(10); //generating a random number from zero to nine
        // while-, that checks the equality of numbers
        while (numLeft == numRight){
            numRight = random.nextInt(10);
        }
        img_right.setImageResource(array.images5[numRight]);        //getting the image from the array
        text_right.setText(array.texts5[numRight]);         //getting the text from the array

        //processing clicking on the left image
        img_left.setOnTouchListener((v, event) -> {
            // image touch condition
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                //if image is touched
                img_right.setEnabled(false);    //blocking the right image
                if(numLeft < numRight){
                    img_left.setImageResource(R.drawable.img_true);
                }else{
                    img_left.setImageResource(R.drawable.img_false);
                }
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                // if you let go of your finger
                if(numLeft < numRight){
                    //if image left is bigger than image right
                    if(count < 20){
                        count = count + 1;
                    }
                    //color progress in grey
                    for(int i = 0; i < 20; i++){
                       TextView tv = findViewById(progress[i]);
                       tv.setBackgroundResource(R.drawable.style_points);
                    }

                    //determining the correct answers and fill in green
                    for(int i = 0; i < count; i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }

                }else{
                    //if image left is smaller than image right
                    if(count > 0){
                        if(count == 1){
                            count = 0;
                        }else{
                            count = count - 2;
                        }
                    }
                    //color progress in grey
                    for(int i = 0; i < 19; i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }

                    //determining the correct answers and fill in green
                    for(int i = 0; i < count; i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }
                }
                if(count == 20){
                    //exit the level
                    SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                    final int level = save.getInt("Level", 1);
                    final int score = save.getInt("Score", 0);
                    if(level > 5){
                        // empty
                    } else {
                        SharedPreferences.Editor editor = save.edit();
                        editor.putInt("Level", 6);
                        editor.putInt("Score", 100);
                        editor.commit();
                    }
                    dialogEnd.show();
                }else{
                    numLeft = random.nextInt(10); //generating a random number from zero to nine
                    img_left.setImageResource(array.images5[numLeft]); //getting the image from the array
                    img_left.startAnimation(a);
                    text_left.setText(array.texts5[numLeft]); //getting the text from the array

                    numRight = random.nextInt(10); //generating a random number from zero to nine
                    //while-, that checks the equality of numbers
                    while (numLeft == numRight){
                        numRight = random.nextInt(10);
                    }
                    img_right.setImageResource(array.images5[numRight]); //getting the image from the array
                    img_left.startAnimation(a);
                    text_right.setText(array.texts5[numRight]); //getting the text from the array
                    img_right.setEnabled(true);//unblock the right image
                }
            }
            return true;
        });

        //processing clicking on the image right
        img_right.setOnTouchListener((v, event) -> {
            //image touch condition
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                //if image is touched
                img_left.setEnabled(false); //blocking the image left
                if(numLeft > numRight){
                    img_right.setImageResource(R.drawable.img_true);
                }else{
                    img_right.setImageResource(R.drawable.img_false);
                }
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                // if you let go of your finger
                if(numLeft > numRight){
                    //if image right is bigger than image left
                    if(count < 20){
                        count = count + 1;
                    }
                    //color progress in grey
                    for(int i = 0; i < 20; i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }

                    //determining the correct answers and fill in green
                    for(int i = 0; i < count; i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }
                }else{
                    //if image right is smaller than image left
                    if(count > 0){
                        if(count == 1){
                            count = 0;
                        }else{
                            count = count - 2;
                        }
                    }
                    //color progress in grey
                    for(int i = 0; i < 19; i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }

                    //determining the correct answers and fill in green
                    for(int i = 0; i < count; i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }
                }
                if(count == 20){
                    //exit the level
                    SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                    final int level = save.getInt("Level", 1);
                    final int score = save.getInt("Score", 0);
                    if(level > 5){
                        // empty
                    } else {
                        SharedPreferences.Editor editor = save.edit();
                        editor.putInt("Level", 6);
                        editor.putInt("Score", 100);
                        editor.commit();
                    }
                    dialogEnd.show();

                }else{
                    numLeft = random.nextInt(10); //generating a random number from zero to nine
                    img_left.setImageResource(array.images5[numLeft]); //getting the image from the array
                    img_left.startAnimation(a);
                    text_left.setText(array.texts5[numLeft]); //getting the text from the array

                    numRight = random.nextInt(10); //generating a random number from zero to nine
                    //while-, that checks the equality of numbers
                    while (numLeft == numRight){
                        numRight = random.nextInt(10);
                    }
                    img_right.setImageResource(array.images5[numRight]); //getting the image from the array
                    img_left.startAnimation(a);
                    text_right.setText(array.texts5[numRight]); //getting the text from the array
                    img_left.setEnabled(true);//unblock the image left
                }
            }
            return true;
        });
    }

    //System button "Back"  (at the bottom, symbol looks like<|), function - back to the mainActivity
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level5.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}