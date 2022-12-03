package com.example.ciscx82doodler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawView extends View {
    private static final float TOUCH_TOLERANCE = 4;
    private float x, y;
    private Path p;
    private Paint brush;
    private ArrayList<Brush> paths = new ArrayList<>();
    private int color;
    private int brushSize;
    private int opa;
    private Bitmap b;
    private Canvas c;
    private Paint bPaint = new Paint();


    public DrawView(Context context) {
        this(context, null);
    }
    public DrawView(Context context,AttributeSet attrs) {
        super(context, attrs);
        brush = new Paint();

        brush.setColor(Color.RED);
        brush.setAlpha(255);
        brush.setStrokeWidth(10);
        brush.setStyle(Paint.Style.STROKE);

    }
    public void initial(int h, int w) {
        b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
        color = Color.RED;
        brushSize = 20;
        opa = 255;
    }
    public void setColor(int c) {
        color = c;
    }
    public void setBrushSize(int s) {
        brushSize = s;
    }
    public void setOpa(int o) {
        opa = o;
    }
    public void clear() {
        paths.clear();
        invalidate();
    }
    public void undo() {
        if (paths.size() != 0) {
            paths.remove(paths.size() - 1);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        c.drawColor(Color.WHITE);

        for (Brush d : paths) {
            brush.setColor(d.color);
            brush.setStrokeWidth(brushSize);
            brush.setAlpha(d.opa);
            c.drawPath(d.path, brush);
        }
        canvas.drawBitmap(b, 0, 0, bPaint);
        canvas.restore();
    }
    private void touchStart(float pX, float pY) {
        p = new Path();
        Brush d = new Brush(color, brushSize, opa, p);
        paths.add(d);
        p.reset();
        p.moveTo(pX, pY);
        x = pX;
        y = pY;
    }
    private void touchMove(float tX, float tY) {
        float dx = Math.abs(tX - x);
        float dy = Math.abs(tY - y);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            p.quadTo(x, y, (tX + x) / 2, (tY + y) / 2);
            x = tX;
            y = tY;
        }
    }
    private void touchUp() {
        p.lineTo(x, y);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                break;
        }
        return true;
    }

}