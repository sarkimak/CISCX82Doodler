package com.example.ciscx82doodler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.slider.RangeSlider;

public class MainActivity extends AppCompatActivity {
    private DrawView draw;
    private ImageButton imgColor;
    private RangeSlider rangeSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        draw = (DrawView) findViewById(R.id.draw_view);
        rangeSlider = (RangeSlider) findViewById(R.id.brushSize);
        imgColor = (ImageButton) findViewById(R.id.chooseColor);

/*
        clear = findViewById(R.id.clearScreen);
        imgBrushSize = findViewById(R.id.brushSize);
        imgColor = findViewById(R.id.chooseColor);
        imgOpacity = findViewById(R.id.opacityTool);
*/


    }
}