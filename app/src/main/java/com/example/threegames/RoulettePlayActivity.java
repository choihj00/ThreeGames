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

    Button restart, home;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_play);
        mContext = this;

        Intent intent = getIntent();
        int count = intent.getIntExtra("playCnt", 2);
        System.out.println(count);

        restart = (Button) findViewById(R.id.restartBtn);
        home = (Button) findViewById(R.id.homeBtn);
        result = (TextView) findViewById(R.id.resultText);
        layoutRoulette = (RelativeLayout) findViewById(R.id.roulette);

        STRINGS = intent.getStringArrayListExtra("optionList");
        circleManager = new CircleManager(mContext, count, "RoulettePlay");
        layoutRoulette.addView(circleManager);

        rotateLayout(layoutRoulette, count);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateLayout(layoutRoulette, count);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

    private int getRandom(int maxNumber) {
        double r = Math.random();
        return (int)(r * maxNumber);
    }

    private void getResult(float angle, int num_roulette) {
        String text = "";
        angle = angle % 360;

        Log.d("roulette", "getResult : " + angle);

        if (num_roulette == 2) {
            if (angle > 90 && angle <= 270)
                text = STRINGS.get(0);
            else if (angle > 270 || angle <= 90)
                text = STRINGS.get(1);
        } else if(num_roulette == 3) {
            if(angle > 150 && angle <= 270)
                text = STRINGS.get(0);
            else if(angle > 30 && angle <= 150)
                text = STRINGS.get(1);
            else if(angle > 270 || angle <= 30)
                text = STRINGS.get(2);
        } else if(num_roulette == 4) {
            if(angle > 180 && angle <= 270)
                text = STRINGS.get(0);
            else if(angle > 90 && angle <= 180)
                text = STRINGS.get(1);
            else if(angle > 0 && angle <= 90)
                text = STRINGS.get(2);
            else if (angle > 270 || angle == 0)
                text = STRINGS.get(3);
        } else if (num_roulette == 5) {
            if (angle > 342 || angle <= 54) {
                text = STRINGS.get(3);
            } else if (angle > 54 && angle <= 126) {
                text = STRINGS.get(2);
            } else if (angle > 126 && angle <= 198) {
                text = STRINGS.get(1);
            } else if (angle > 198 && angle <= 270) {
                text = STRINGS.get(0);
            } else if (angle > 270 && angle <= 342) {
                text = STRINGS.get(4);
            }
        } else if (num_roulette == 6) {
            if (angle > 330 || angle <= 30) {
                text = STRINGS.get(4);
            } else if (angle > 30 && angle <= 90) {
                text = STRINGS.get(3);
            } else if (angle > 90 && angle <= 150) {
                text = STRINGS.get(2);
            } else if (angle > 150 && angle <= 210) {
                text = STRINGS.get(1);
            } else if (angle > 210 && angle <= 270) {
                text = STRINGS.get(0);
            } else if (angle > 270 && angle <= 330) {
                text = STRINGS.get(5);
            }
        }
        result.setText("Result : " + text);
    }
}