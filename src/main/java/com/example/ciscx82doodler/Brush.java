package com.example.ciscx82doodler;
import android.graphics.Path;
public class Brush {
    public int color;
    public int brushSize;
    public int opa;
    public Path path;
    public Brush(int color, int brushSize, int opa, Path path) {
        this.color = color;
        this.brushSize = brushSize;
        this.opa = opa;
        this.path = path;
    }
}
