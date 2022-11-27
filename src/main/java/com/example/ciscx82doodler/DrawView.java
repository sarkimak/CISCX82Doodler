package com.example.ciscx82doodler;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class DrawView extends View {

    public LayoutParams params;
    private Path p = new Path();
    private Paint brush = new Paint();

    public DrawView(Context context) {
        super(context);
        init(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        brush.setAntiAlias(true);
        brush.setColor(Color.RED);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStyle(Paint.Style.STROKE);

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                p.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                p.lineTo(x, y);
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }
}
