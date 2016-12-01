package com.example.valen.pinta;

import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Valen on 30/11/2016.
 */

public class Cuadrados {
    private RectF rect;
    private Paint paint;

    public Cuadrados(int color,float grosor,Paint.Style estilo){
        rect = new RectF();
        paint = new Paint();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(estilo);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(grosor);
    }

    public RectF getRect(){
        return rect;
    }

    public Paint getPaint(){
        return paint;
    }
}
