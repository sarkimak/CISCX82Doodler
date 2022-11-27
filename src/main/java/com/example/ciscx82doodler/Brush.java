package com.example.ciscx82doodler;
import android.graphics.Path;
public class Brush {
    public int color;
    public int brushSize;
    public Path path;
    public Brush(int color, int brushSize, Path path) {
        this.color = color;
        this.brushSize = brushSize;
        this.path = path;
    }
}
