package com.example.math;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class Board extends android.support.v7.widget.AppCompatImageView {
    Path path = new Path();

    Paint paint, canvasPaint;
    int paintColor = 0xFFFFFFFF;
    int paintSize = 3;

    private Canvas drawCanvas;

    private Bitmap canvasBit;

    float x = 0;
    float y = 0;
    boolean erase = false;

    public Board(Context context) {
        super(context);
        init();
    }

    public Board(Context context, AttributeSet set){
        super(context, set);
        init();
    }

    public void borrar(boolean bor){
        erase = bor;
        if(erase){
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        else{
            paint.setXfermode(null);
        }
    }

    public void setSize(int s){
        paint.setStrokeWidth(s);
    }

    public void setColor(int color){
        invalidate();
        paint.setColor(color);
    }

    public void init()
    {
        path = new Path();
        paint = new Paint();
        paint.setColor(paintColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(paintSize);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBit = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasBit.eraseColor(Color.TRANSPARENT);
        drawCanvas = new Canvas(canvasBit);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas){
        canvas.drawBitmap(canvasBit,0,0, canvasPaint);
        canvas.drawPath(path, paint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event){
        int evento = event.getAction();
        x = event.getX();
        y = event.getY();

        if(evento == MotionEvent.ACTION_DOWN) {
            path.moveTo(x,y);
        }else if(evento == MotionEvent.ACTION_MOVE) {
            path.lineTo(x,y);
        }else if(evento == MotionEvent.ACTION_UP) {
            path.lineTo(x,y);
            drawCanvas.drawPath(path, paint);
            path.reset();
        }

        invalidate();

        return true;
    }
}
