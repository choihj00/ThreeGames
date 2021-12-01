package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class random extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        Intent intent = getIntent();
        int number = intent.getIntExtra("playNum", 2);
        int bangNum = intent.getIntExtra("bangNum", 1);

        ArrayList<Integer> ranArray = new ArrayList<>();

        LinearLayout randomLayout = findViewById(R.id.randomLayout);

        randomLayout.setGravity(Gravity.CENTER);

        for(int i=0; i<bangNum; i++){
            ranArray.add((int)(Math.random()*bangNum));
            for(int j=0; j<i; j++){
                if(ranArray.get(i) == ranArray.get(j)){
                    i--;
                }
            }
        }

//        String a = "";
//
//        for(int i=0; i<ranArray.size(); i++){
//            a += ranArray.get(i).toString() + " ";
//        }

        System.out.println(ranArray);

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

        for(int i=0; i<number; i++){
            Button rBtn = (Button) randomLayout.getChildAt(i);
            rBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int j=0; j<ranArray.size(); j++){
                        System.out.println(j);
                        System.out.println(ranArray.get(j));

                        if(rBtn.getId() == ranArray.get(0)){
                            rBtn.setBackgroundResource(R.drawable.bang);
                        } else if(rBtn.getId() == ranArray.get(1)){
                            rBtn.setBackgroundResource(R.drawable.bang);
                        } else {
                            rBtn.setBackgroundResource(R.drawable.pass);
                        }
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