package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class random extends AppCompatActivity {

    int ranNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        Intent intent = getIntent();
        int number = intent.getIntExtra("playNum", 2);
//        int bangNum = intent.getIntExtra("bangNum", 1);
        ranNum = (int)(Math.random()*number);

        Button shakeBtn = findViewById(R.id.shakeBtn);

        final Animation animTransShake = AnimationUtils.loadAnimation(
                this,R.anim.anim_translate_shake);

        LinearLayout randomLayout = findViewById(R.id.randomLayout);

        randomLayout.setGravity(Gravity.CENTER);

//        ArrayList<Integer> ranArray = new ArrayList<>();
//
//        for(int i=0; i<bangNum; i++){
//            ranArray.add((int)(Math.random()*bangNum));
//            for(int j=0; j<i; j++){
//                if(ranArray.get(i) == ranArray.get(j)){
//                    i--;
//                }
//            }
//        }
//
//        System.out.println(ranArray);

        for (int i = 0; i < number; i++) {
            final Button btn = new Button(this);
            btn.setBackgroundResource(R.drawable.random);
            btn.setId(i);

            btn.setGravity(Gravity.CENTER);
            btn.setLayoutParams(new LinearLayout.LayoutParams(200, 200));

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btn.getLayoutParams();
            layoutParams.topMargin = 30;
            btn.setLayoutParams(layoutParams);

            randomLayout.addView(btn);
        }

        shakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ranNum = (int)(Math.random()*number);
                randomLayout.startAnimation(animTransShake);
            }
        });

        for(int i=0; i<number; i++){
            Button rBtn = (Button) randomLayout.getChildAt(i);
            rBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if(rBtn.getId() == ranNum){
                            rBtn.setBackgroundResource(R.drawable.bang);
                        } else {
                            rBtn.setBackgroundResource(R.drawable.pass);
                        }
                    }

                });
        }
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}