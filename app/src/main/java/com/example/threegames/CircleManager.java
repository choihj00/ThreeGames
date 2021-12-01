package com.example.threegames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class CircleManager extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.GRAY};
    private int num;
    private Context cContext;
    private String className;
    private ArrayList<String> options = null;

    public CircleManager(Context context, int num, String className) {
        super(context);
        cContext = context;
        this.num = num;
        this.className = className;
    }

    public void setNum(int num) {
        this.num = num;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RelativeLayout layoutRoulette = null;
        if(className.equals("Roulette")){
            layoutRoulette = ((RouletteActivity)RouletteActivity.rContext).layoutRoulette;
            options = ((RouletteActivity)RouletteActivity.rContext).STRINGS;
        }
        else if(className.equals("RoulettePlay")){
            layoutRoulette = ((RoulettePlayActivity)RoulettePlayActivity.mContext).layoutRoulette;
            options = ((RoulettePlayActivity)RoulettePlayActivity.mContext).STRINGS;
        }

        int width = layoutRoulette.getWidth();
        int height = layoutRoulette.getHeight();
        int sweepAngle = 360 / num;

        RectF rectF = new RectF(0, 0, width, height);
        Rect rect = new Rect(0, 0, width, height);

        int centerX = (rect.left + rect.right) / 2;
        int centerY = (rect.top + rect.bottom) / 2;
        int radius = (rect.right - rect.left) / 2;

        int temp = 0;

        for (int i = 0; i < num; i++) {
            paint.setColor(COLORS[i]);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawArc(rectF, temp, sweepAngle, true, paint);

            float medianAngle = (temp + (sweepAngle / 2f)) * (float) Math.PI / 180f;

            paint.setColor(Color.BLACK);
            paint.setTextSize(64);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);

            float arcCenterX = (float) (centerX + (radius * Math.cos(medianAngle))); // Arc's center X
            float arcCenterY = (float) (centerY + (radius * Math.sin(medianAngle))); // Arc's center Y

            // put text at middle of Arc's center point and Circle's center point
            float textX = (centerX + arcCenterX) / 2;
            float textY = (centerY + arcCenterY) / 2;

            if(className.equals("Roulette")){
                EditText et = new EditText(cContext);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                et.setLayoutParams(lp);
                et.setText(options.get(i));
                et.setId(i);
                float x = textX+lp.width*30;
                float y = textY+lp.height*25;
                et.setX(x);
                et.setY(y);
                ((RelativeLayout)this.getParent()).addView(et);
            }
            else if(className.equals("RoulettePlay")){
                canvas.drawText(((RouletteActivity)RouletteActivity.rContext).STRINGS.get(i), textX, textY, paint);
            }
            temp += sweepAngle;
        }
    }
}
