package com.example.ciscx82doodler;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DrawView draw;
    private ImageButton imgColor;
    private SeekBar rangeSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        draw = (DrawView) findViewById(R.id.draw_view);
        rangeSlider = (SeekBar) findViewById(R.id.brushSize);
        imgColor = (ImageButton) findViewById(R.id.chooseColor);

 /*
         clear = findViewById(R.id.clearScreen);
         imgBrushSize = findViewById(R.id.brushSize);
         imgColor = findViewById(R.id.chooseColor);
         imgOpacity = findViewById(R.id.opacityTool);
 */


    }
}