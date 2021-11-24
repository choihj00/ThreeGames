package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LadderActivity extends AppCompatActivity {

    private int number = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder);

        final TextView text = findViewById(R.id.ladderNumber);

        Button minusBtn = findViewById(R.id.minus);
        minusBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if(number<3){
                    Toast.makeText(getApplicationContext(), "최소 2명입니다.", Toast.LENGTH_SHORT).show();
                } else{
                    number--;
                }
                text.setText(String.valueOf(number));
            }
        });

        Button plusBtn = findViewById(R.id.plus);
        plusBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if(number>5){
                    Toast.makeText(getApplicationContext(), "최대 6명까지 할 수 있습니다.", Toast.LENGTH_SHORT).show();
                } else{
                    number++;
                }
                text.setText(String.valueOf(number));
            }
        });
    }
    public void onClickLadderPlay(View v){
        Intent intent = new Intent();
        intent.setAction("ladderPlay");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }
}