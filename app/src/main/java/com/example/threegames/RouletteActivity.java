package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RouletteActivity extends AppCompatActivity {
    private Button increaseButton, decreaseButton, playRoulette;
    private TextView rouletteCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);

        increaseButton = (Button) findViewById(R.id.increaseButton);
        decreaseButton = (Button) findViewById(R.id.decreaseButton);
        playRoulette = (Button) findViewById(R.id.playRoulette);

        rouletteCount = (TextView) findViewById(R.id.rouletteCount);
    }

    public void increaseCount(View v){
        int count = Integer.parseInt(this.rouletteCount.getText().toString());
        if(count == 6)
            return;
        count++;
        this.rouletteCount.setText(count+"");
    }

    public void decreaseCount(View v){
        int count = Integer.parseInt(this.rouletteCount.getText().toString());
        System.out.println(count);
        if(count == 2)
            return;
        count--;
        this.rouletteCount.setText(count+"");
    }

    public void playRoulette(View v){
        Intent intent = new Intent(getApplicationContext(), RoulettePlayActivity.class);
        startActivity(intent);
    }
}