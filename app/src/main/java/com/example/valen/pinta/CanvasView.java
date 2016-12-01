package com.example.valen.pinta;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Valen on 28/11/2016.
 */

public class CanvasView extends View {
    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;
    private Lineas linea;
    private Cuadrados cuadrado;
    private Circulos circulo;
    private float mX,mY,rX,rY;
    private float grosor;
    private int color;
    private static final float TOLERANCE = 5;
    private String tipo;
    private Paint.Style estilo;

    private ArrayList lista;

    public CanvasView(Context c,AttributeSet attrs) {
        super(c,attrs);
        context = c;
        grosor = 4f;
        color = Color.BLACK;
        estilo = Paint.Style.STROKE;
        linea = new Lineas(color,grosor,estilo);
        cuadrado = new Cuadrados(color,grosor,estilo);
        circulo = new Circulos(color,grosor,estilo);
        tipo = "lapiz";

        lista = new ArrayList();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<lista.size();i++){
            if(lista.get(i) instanceof Circulos){
                canvas.drawOval(((Circulos)lista.get(i)).getRect(),((Circulos)lista.get(i)).getPaint());
            }else if(lista.get(i) instanceof Cuadrados){
                canvas.drawRect(((Cuadrados)lista.get(i)).getRect(),((Cuadrados)lista.get(i)).getPaint());
            }else{
                canvas.drawPath(((Lineas)lista.get(i)).getPath(), ((Lineas)lista.get(i)).getPaint());
            }
        }
        if(tipo.equals("lapiz")) {
            canvas.drawPath(linea.getPath(), linea.getPaint());
        }else if(tipo.equals("cuadrado")) {
            canvas.drawRect(cuadrado.getRect(),cuadrado.getPaint());
        }else if(tipo.equals("circulo")){
            canvas.drawOval(circulo.getRect(),circulo.getPaint());
        }
        mCanvas = canvas;

    }

    private void startTouch(float x, float y) {
        if(tipo.equals("lapiz")) {
            linea.getPath().moveTo(x, y);
            mX = x;
            mY = y;
        }
        else if (tipo.equals("cuadrado")) {
            cuadrado.getRect().set(x, y, y, x);
            rX = x;
            rY = y;
        }
        else if (tipo.equals("circulo")) {
            circulo.getRect().set(x,y,y,x);
            rX = x;
            rY = y;
        }
    }

    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            if(tipo.equals("lapiz")) {
                linea.getPath().quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            }else if(tipo.equals("cuadrado")){
                cuadrado.getRect().set(rX,rY,(mX+x-mX),(mY+y - mY));
            }else if(tipo.equals("circulo")){
                circulo.getRect().set(rX,rY,(mX+x-mX),(mY+y - mY));
            }
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas() {
        linea.getPath().reset();
        lista.clear();
        invalidate();
    }

    private void upTouch() {
        if(tipo.equals("lapiz")) {
            linea.getPath().lineTo(mX, mY);
            lista.add(linea);
            linea = new Lineas(color, grosor,estilo);
        }else if(tipo.equals("cuadrado")){
            lista.add(cuadrado);
            cuadrado = new Cuadrados(color,grosor,estilo);
        }else if(tipo.equals("circulo")){
            lista.add(circulo);
            circulo = new Circulos(color,grosor,estilo);
        }
    }

    public void aumentarAnchoTrazo(){
        grosor +=5;
        linea.getPaint().setStrokeWidth(grosor);
        cuadrado.getPaint().setStrokeWidth(grosor);
        circulo.getPaint().setStrokeWidth(grosor);
    }

    public void disminuirAnchoTrazo(){
        if(grosor>5) {
            grosor -= 5;
        }
        linea.getPaint().setStrokeWidth(grosor);
        cuadrado.getPaint().setStrokeWidth(grosor);
        circulo.getPaint().setStrokeWidth(grosor);
    }

    public void cambiarColor(int col){
        color = col;
        linea.getPaint().setColor(color);
        cuadrado.getPaint().setColor(color);
        circulo.getPaint().setColor(color);
    }


    public void cambiarTipo(String t){
        tipo = t;
    }
    public void cambiarEstilo(String t){
        if(t.equals("lleno")){
            estilo = Paint.Style.FILL_AND_STROKE;
            circulo.getPaint().setStyle(estilo);
            cuadrado.getPaint().setStyle(estilo);
        }else{
            estilo = Paint.Style.STROKE;
            circulo.getPaint().setStyle(estilo);
            cuadrado.getPaint().setStyle(estilo);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }
}
