package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class LadderResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder_result);

        TextView resultTextView = findViewById(R.id.resultTextView);

        Intent intent = getIntent();
        ArrayList<String> arrayTop = intent.getStringArrayListExtra("arrayTop");
        ArrayList<String> arrayBottom = intent.getStringArrayListExtra("arrayBottom");

        StringBuffer sb = new StringBuffer();
        for (int i=0;i<arrayTop.size();i++) {
            sb.append(arrayTop.get(i).toString() + "->");
            int random = (int)(Math.random()*arrayBottom.size());
            sb.append(arrayBottom.get(random).toString() + "\r\n");
            arrayBottom.remove(random);
        }
        resultTextView.setText(sb);
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}