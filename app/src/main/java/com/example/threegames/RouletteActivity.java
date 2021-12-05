package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RouletteActivity extends AppCompatActivity {
    private Button increaseButton, decreaseButton, playRoulette;
    private TextView rouletteCount;
    private int count = 2;

    public static Context rContext;

    private CircleManager circleManager;
    public RelativeLayout layoutRoulette;

    public ArrayList<String> STRINGS = new ArrayList<>();
    private float initAngle = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        rContext = this;

        increaseButton = (Button) findViewById(R.id.increaseButton);
        decreaseButton = (Button) findViewById(R.id.decreaseButton);
        playRoulette = (Button) findViewById(R.id.playRoulette);

        rouletteCount = (TextView) findViewById(R.id.rouletteCount);

        layoutRoulette = (RelativeLayout) findViewById(R.id.roulette);

        STRINGS = setOption(6);
        circleManager = new CircleManager(rContext, count, "Roulette");
        layoutRoulette.addView(circleManager);
    }

    public ArrayList<String> setOption(int num) {
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            strings.add("옵션"+(i+1));
        }

        return strings;
    }

    public void increaseCount(View v){
        EditText option;
        if(count == 6){
            Toast.makeText(getApplicationContext(), "최대 6명까지 할 수 있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        for(int i =0;i<count;i++) {
            option = (EditText) findViewById(i);
            STRINGS.set(i, option.getText().toString());
//            layoutRoulette.removeView(findViewById(i));
        }
        layoutRoulette.removeAllViewsInLayout();
        circleManager = new CircleManager(rContext, count, "Roulette");
        layoutRoulette.addView(circleManager);
        count++;
        this.rouletteCount.setText(count+"칸");
        circleManager.setNum(count);
    }

    public void decreaseCount(View v){
        EditText option;
        if(count == 2){
            Toast.makeText(getApplicationContext(), "최소 2명까지 할 수 있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        for(int i =0;i<count;i++) {
            option = (EditText) findViewById(i);
            STRINGS.set(i, option.getText().toString());
//            layoutRoulette.removeView(option);
        }
        layoutRoulette.removeAllViewsInLayout();
        circleManager = new CircleManager(rContext, count, "Roulette");
        layoutRoulette.addView(circleManager);
        count--;
        this.rouletteCount.setText(count+"칸");
        circleManager.setNum(count);
    }

    public void playRoulette(View v){
        EditText option;
        for(int i =0;i<count;i++){
            option = (EditText) findViewById(i);
            STRINGS.set(i, option.getText().toString());
        }
        Intent intent = new Intent(getApplicationContext(), RoulettePlayActivity.class);
        intent.putExtra("playCnt", count);
        intent.putStringArrayListExtra("optionList", STRINGS);
        startActivity(intent);
        finish();
    }
}