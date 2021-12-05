package com.example.threegames;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;
import java.util.Random;

public class RockActivity extends AppCompatActivity {

    private Button rockBtn, scissorsBtn, paperBtn;
    public int computer;
    public int user;
    private TextView result;
    ImageView imageView3;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock);

        computer = new Random().nextInt(3)+1;

        result = (TextView) findViewById(R.id.result);

        rockBtn = findViewById(R.id.rockbtn);
        scissorsBtn = findViewById(R.id.scissorsbtn);
        paperBtn = findViewById(R.id.paperbtn);
        imageView = findViewById(R.id.imageView);
        imageView3 = findViewById(R.id.imageView3);
        imageView3.setVisibility(View.INVISIBLE);

        final AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
        drawable.start();

        rockBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                drawable.stop();
                imageView.setVisibility(View.INVISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                user=0;
                computerhand(computer);
                checkresult(computer, user);
            }
        });

        scissorsBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                drawable.stop();
                imageView.setVisibility(View.INVISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                user=1;
                computerhand(computer);
                checkresult(computer, user);
            }
        });

        paperBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                drawable.stop();
                imageView.setVisibility(View.INVISIBLE);
                user = 2;
                computerhand(computer);
                checkresult(computer, user);
            }
        });

    }

    public void computerhand(int computer){


        if(computer==0){
            imageView3.setImageResource(R.drawable.rock);
        }
        else if(computer==1){
            imageView3.setImageResource(R.drawable.scissors);
        }
        else{
            imageView3.setImageResource(R.drawable.paper);
        }

    }

    public void checkresult(int computer, int user){
        int results=(3+user-computer)%3;


        switch(results){
            case 0:{

                result.setText("무승부");
                result.setTextColor(Color.BLACK);
                result.bringToFront();
                break;
            }
            case 1:{

                result.setText("패배");
                result.setTextColor(Color.BLACK);
                result.bringToFront();
                break;
            }
            case 2:{

                result.setText("승리");
                result.setTextColor(Color.BLACK);
                result.bringToFront();
                break;
            }
        }
    }




}
