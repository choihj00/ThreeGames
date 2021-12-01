package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LadderActivity extends AppCompatActivity {

    public int number = 2, bangNum=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder);

        final TextView text = findViewById(R.id.ladderNumber);
        final TextView bangText = findViewById(R.id.bangNum);

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
                if(number>4){
                    Toast.makeText(getApplicationContext(), "최대 5명까지 할 수 있습니다.", Toast.LENGTH_SHORT).show();
                } else{
                    number++;
                }
                text.setText(String.valueOf(number));
            }
        });

        Button bangMinusBtn = findViewById(R.id.bangMinus);
        bangMinusBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if(bangNum<2){
                    Toast.makeText(getApplicationContext(), "최소 1개 폭탄이 있어야 합니다.", Toast.LENGTH_SHORT).show();
                } else{
                    bangNum--;
                }
                bangText.setText(String.valueOf(bangNum));
            }
        });

        Button bangPlusBtn = findViewById(R.id.bangPlus);
        bangPlusBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if(bangNum>number-2){
                    Toast.makeText(getApplicationContext(), "불가능합니다.", Toast.LENGTH_SHORT).show();
                } else{
                    bangNum++;
                }
                bangText.setText(String.valueOf(bangNum));
            }
        });
    }
    public void onClickLadderPlay(View v){
        Intent intent = new Intent();
        intent.setAction("random");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("playNum", number);
        intent.putExtra("bangNum", bangNum);
        startActivity(intent);
    }
}