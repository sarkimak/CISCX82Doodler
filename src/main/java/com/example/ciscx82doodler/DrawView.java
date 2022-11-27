package com.example.ciscx82doodler;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import java.util.ArrayList;
import android.util.AttributeSet;

public class DrawView extends View {

    public LayoutParams params;
    private float x, y;
    private Path p;
    private Paint brush;
    private ArrayList<Brush> paths = new ArrayList<>();
    private int currentColor;
    private int brushSize;
    private Bitmap b;
    private Canvas c;
    private Paint bPaint = new Paint(Paint.DITHER_FLAG);


    public DrawView(Context context) {
        this(context, null);
    }
    public DrawView(Context context,AttributeSet attr) {
        super(context, attr);
        brush = new Paint();

        brush.setAntiAlias(true);
        brush.setDither(true);
        brush.setColor(Color.RED);
        brush.setAlpha(0xff);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStyle(Paint.Style.STROKE);

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }
    public void initial(int h, int w) {
        b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
        currentColor = Color.RED;
        brushSize = 20;
    }
    public void setColor(int color) {
        currentColor = color;
    }
    public void setStrokeWidth(int w) {
        brushSize = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int backgroundColor = Color.WHITE;
        c.drawColor(backgroundColor);

        for (Brush fp : paths) {
            brush.setColor(fp.color);
            brush.setStrokeWidth(brushSize);
            c.drawPath(fp.path, brush);
        }
        canvas.drawBitmap(b, 0, 0, bPaint);
        canvas.restore();
    }
    private void touchStart(float pX, float pY) {
        p = new Path();
        Brush fp = new Brush(currentColor, brushSize, p);
        paths.add(fp);
        p.reset();
        p.moveTo(pX, pY);
        x = pX;
        y = pY;
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
