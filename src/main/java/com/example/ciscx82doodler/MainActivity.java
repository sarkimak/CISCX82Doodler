package com.example.ciscx82doodler;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DrawView draw;
    private ImageButton imgColor, clear;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //draw = (DrawView) findViewById(R.id.draw_view);
        seekBar = (SeekBar) findViewById(R.id.brushSize);
        imgColor = (ImageButton) findViewById(R.id.chooseColor);
        clear = (ImageButton) findViewById(R.id.clearScreen);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.clear();
            }
        });

/*
        clear = findViewById(R.id.clearScreen);
        imgBrushSize = findViewById(R.id.brushSize);
        imgColor = findViewById(R.id.chooseColor);
        imgOpacity = findViewById(R.id.opacityTool);
*/


    }
}