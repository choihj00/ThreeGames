package com.example.threegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LadderResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder_result);

//        LinearLayout view = findViewById(R.id.resultLayout);
//
//        for (int i = 0; i < number; i++) {
//            TextView textView = new TextView(this);
//
//            textView.setId(20+i);
//
////            System.out.println(editTextTop.getId());
//
//            view.addView(textView);
//        }

    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}