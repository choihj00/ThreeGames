package com.example.threegames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RoulettePlayActivity extends AppCompatActivity {
    public static Context mContext;

    private CircleManager circleManager;
    public RelativeLayout layoutRoulette;

    public ArrayList<String> STRINGS;
    private float initAngle = 0.0f;

    Button spin;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_play);
        mContext = this;

        Intent intent = getIntent();
        int count = intent.getIntExtra("playCnt", 2);
        System.out.println(count);

        spin = (Button) findViewById(R.id.spinButton);
        result = (TextView) findViewById(R.id.resultText);
        layoutRoulette = (RelativeLayout) findViewById(R.id.roulette);

        STRINGS = setRandom(1000, count);
        circleManager = new CircleManager(mContext, count);
        layoutRoulette.addView(circleManager);

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateLayout(layoutRoulette, count);
            }
        });
    }

    public void rotateLayout(final RelativeLayout layout, final int num) {
        final float fromAngle = getRandom(360) + 3600 + initAngle;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getResult(fromAngle, num); // start when animation complete
            }
        }, 3000);

        RotateAnimation rotateAnimation = new RotateAnimation(initAngle, fromAngle,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.anim.accelerate_decelerate_interpolator));
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillEnabled(true);
        rotateAnimation.setFillAfter(true);
        layout.startAnimation(rotateAnimation);
    }

    public ArrayList<String> setRandom(int maxNumber, int num) {
        ArrayList<String> strings = new ArrayList<>();

        double r = Math.random();

        for (int i = 0; i < num; i++) {
            int rand = (int) (r * maxNumber);
            strings.add(String.valueOf(rand));
            r = Math.random();
        }

        return strings;
    }

    private int getRandom(int maxNumber) {
        double r = Math.random();
        return (int)(r * maxNumber);
    }

    private void getResult(float angle, int num_roulette) {
        String text = "";
        angle = angle % 360;

        Log.d("roulette", "getResult : " + angle);

        if (num_roulette == 5) {
            if (angle > 342 || angle <= 54) { // 11   2
                text = STRINGS.get(3);
                buildAlert(text);
            } else if (angle > 54 && angle <= 126) { // 333   3
                text = STRINGS.get(2);
                buildAlert(text);
            } else if (angle > 126 && angle <= 198) { // 222   4
                text = STRINGS.get(1);
                buildAlert(text);
            } else if (angle > 198 && angle <= 270) { // 111    0
                text = STRINGS.get(0);
                buildAlert(text);
            } else if (angle > 270 && angle <= 342) { // 22     1
                text = STRINGS.get(4);
                buildAlert(text);
            }
        } else if (num_roulette == 6) {
            if (angle > 330 || angle <= 30) { // 22
                text = STRINGS.get(4);
                buildAlert(text);
            } else if (angle > 30 && angle <= 90) { // 11
                text = STRINGS.get(3);
                buildAlert(text);
            } else if (angle > 90 && angle <= 150) { // 333
                text = STRINGS.get(2);
                buildAlert(text);
            } else if (angle > 150 && angle <= 210) { // 222
                text = STRINGS.get(1);
                buildAlert(text);
            } else if (angle > 210 && angle <= 270) { // 111
                text = STRINGS.get(0);
                buildAlert(text);
            } else if (angle > 270 && angle <= 330) { // 3
                text = STRINGS.get(5);
                buildAlert(text);
            }
        }
        result.setText("Result : " + text);
    }

    private void buildAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations")
                .setMessage("You have earned " + text + " points!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        layoutRoulette.setRotation(360 - initAngle);
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}