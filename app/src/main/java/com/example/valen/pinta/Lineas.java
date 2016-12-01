package com.example.valen.pinta;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Valen on 30/11/2016.
 */

public class Lineas extends Path {
    private Paint paint;
    private Path path;

    public Lineas(int color, float grosor,Paint.Style estilo){
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(estilo);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(grosor);
    }

    public Paint getPaint(){
        return paint;
    }

    public Path getPath(){
        return path;
    }

    public void setPaint(Paint p){
        paint = p;
    }

    public void setPath(Path p){
        path = p;
    }

}
