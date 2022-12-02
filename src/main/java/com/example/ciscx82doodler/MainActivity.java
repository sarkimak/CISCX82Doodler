package com.example.ciscx82doodler;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawView drawView;
    private Button color, size, opa, clear;
    private SeekBar colorBar, sizeBar, opacityBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DrawView) findViewById(R.id.draw_view);
        color = (Button) findViewById(R.id.colorB);
        colorBar = (SeekBar) findViewById(R.id.colorBar);
        colorBar.getProgressDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        colorBar.getThumb().setTint(Color.BLACK);
        size = (Button) findViewById(R.id.sizeB);
        sizeBar = (SeekBar) findViewById(R.id.sizeBar);
        opa = (Button) findViewById(R.id.opacityB);
        opacityBar = (SeekBar) findViewById(R.id.opacityBar);
        clear = (Button) findViewById(R.id.clearB);

        colorBar.setMax(256*7-120);
        colorBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    int r = 0;
                    int g = 0;
                    int b = 0;

                    if(progress < 256){
                        b = progress;
                    } else if(progress < 256*2) {
                        g = progress%256;
                        b = 256 - progress%256;
                    } else if(progress < 256*3) {
                        g = 255;
                        b = progress%256;
                    } else if(progress < 256*4) {
                        r = progress%256;
                        g = 256 - progress%256;
                        b = 256 - progress%256;
                    } else if(progress < 256*5) {
                        r = 255;
                        g = 0;
                        b = progress%256;
                    } else if(progress < 256*6) {
                        r = 255;
                        g = progress%256;
                        b = 256 - progress%256;
                    } else if(progress < 256*7) {
                        r = 255;
                        g = 255;
                        b = progress%256;
                    }

                    drawView.setColor(Color.argb(255, r, g, b));
                    colorBar.getProgressDrawable().setColorFilter(Color.argb(255, r, g, b), PorterDuff.Mode.SRC_IN);
                    colorBar.getThumb().setTint(Color.argb(255, r, g, b));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) { }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) { }
        });

        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar arg0, int value, boolean arg2) { drawView.setBrushSize(value); }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) { }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) { }
        });

        opacityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar arg0, int value, boolean arg2) { drawView.setOpa(value); }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) { }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) { }
        });

        // pass the height and width of the custom view
        // to the init method of the DrawView object
        ViewTreeObserver vto = drawView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                drawView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int w = drawView.getMeasuredWidth();
                int h = drawView.getMeasuredHeight();
                drawView.initial(h, w);
            }
        });
    }

    public void clear(View view){
        drawView.clear();
    }

    //toggles color slider
    public void changeColor(View view){
        if (colorBar.getVisibility() == View.VISIBLE)
            colorBar.setVisibility(View.GONE);
        else
            colorBar.setVisibility(View.VISIBLE);
        sizeBar.setVisibility(View.GONE);
        opacityBar.setVisibility(View.GONE);
    }

    //toggles size slider
    public void changeSize(View view){
        if (sizeBar.getVisibility() == View.VISIBLE)
            sizeBar.setVisibility(View.GONE);
        else
            sizeBar.setVisibility(View.VISIBLE);
        colorBar.setVisibility(View.GONE);
        opacityBar.setVisibility(View.GONE);
    }

    //toggles opacity slider
    public void changeOpacity(View view){
        if (opacityBar.getVisibility() == View.VISIBLE)
            opacityBar.setVisibility(View.GONE);
        else
            opacityBar.setVisibility(View.VISIBLE);
        colorBar.setVisibility(View.GONE);
        sizeBar.setVisibility(View.GONE);
    }
}