package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class LadderPlay extends AppCompatActivity {

    public ArrayList<String> arrayTop;
    public ArrayList<String> arrayBottom;
    LinearLayout ViewTop;
    LinearLayout ViewBottom;
    LinearLayout ViewCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder_play);

        ViewTop = findViewById(R.id.layoutTop);
        ViewBottom = findViewById(R.id.layoutBottom);
        ViewCenter = findViewById(R.id.layoutCenter);

        Intent intent = getIntent();
        int number = intent.getIntExtra("playNum", 2);
        arrayTop = new ArrayList<String>();
        arrayBottom = new ArrayList<String>();

        Button startBtn = findViewById(R.id.startBtn);

        for (int i = 0; i < number; i++) {
            final EditText editTextTop = new EditText(this);
            final EditText editTextBottom = new EditText(this);

            editTextTop.setId(i);
            editTextBottom.setId(10 + i);

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

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("ladderResult");
                intent.addCategory(Intent.CATEGORY_DEFAULT);

                for(int i=0; i<number; i++){
                    EditText etTop = (EditText) ViewTop.getChildAt(i);
                    EditText etBottom = (EditText) ViewBottom.getChildAt(i);
                    arrayTop.add(etTop.getText().toString());
                    arrayBottom.add(etBottom.getText().toString());
                }
                intent.putStringArrayListExtra("arrayTop", arrayTop);
                intent.putStringArrayListExtra("arrayBottom", arrayBottom);
                startActivity(intent);
            }
        });
    }
}