package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LadderPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder_play);

        Intent intent = getIntent();
        int number = intent.getIntExtra("playNum", 2);

        LinearLayout ViewTop = findViewById(R.id.layoutTop);
        LinearLayout ViewBottom = findViewById(R.id.layoutBottom);
        LinearLayout ViewCenter = findViewById(R.id.layoutCenter);

        for (int i = 0; i < number; i++) {
            EditText editTextTop = new EditText(this);
            EditText editTextBottom = new EditText(this);

            editTextTop.setId(i);
            editTextBottom.setId(10 + i);

            System.out.println(editTextTop.getId());
            System.out.println(editTextBottom.getId());

            editTextTop.setGravity(Gravity.CENTER);
            editTextBottom.setGravity(Gravity.CENTER);

            editTextTop.setLayoutParams(new LinearLayout.LayoutParams(200, 100));
            editTextBottom.setLayoutParams(new LinearLayout.LayoutParams(200, 100));

            ViewTop.addView(editTextTop);
            ViewBottom.addView(editTextBottom);
        }

        if(number==2){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ladder2);
            ViewCenter.addView(imageView);
        } else if(number==3){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ladder3);
            ViewCenter.addView(imageView);
        } else if(number==4){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ladder4);
            ViewCenter.addView(imageView);
        } else if(number==5){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ladder5);
            ViewCenter.addView(imageView);
        }

    }
    public void onClickLadderResult(View v){
        Intent intent = new Intent();
        intent.setAction("ladderResult");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }
}