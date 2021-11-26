package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RouletteActivity extends AppCompatActivity {
    private Button spin, increaseCount, decreaseCount;
    private TextView resultText, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);

        spin = (Button) findViewById(R.id.spinButton);
        increaseCount = (Button) findViewById(R.id.decreaseCount);
        decreaseCount = (Button) findViewById(R.id.increaseCount);

        resultText = (TextView) findViewById(R.id.resultText);
        count = (TextView) findViewById(R.id.count);
    }

    public void increaseCount(View v){
        int count = Integer.parseInt(this.count.getText().toString());
        System.out.println(count);
        if(count == 6)
            return;
        count++;
        this.count.setText(count+"");
    }

    public void decreaseCount(View v){
        int count = Integer.parseInt(this.count.getText().toString());
        System.out.println(count);
        if(count == 2)
            return;
        count--;
        this.count.setText(count+"");
    }
}