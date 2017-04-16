package com.anwesome.ui.horizontalcollapsiblelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class CollapsibleItem extends View {
    private Bitmap bitmap;
    private String title;
    private int time = 0;
    private boolean isAnimated = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CollapsibleItem(Context context,Bitmap bitmap,String title) {
        super(context);
        this.bitmap = bitmap;
        this.title = title;
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth(),h = canvas.getHeight();
        if(time == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,w/10,w/10,true);
        }
        canvas.drawBitmap(bitmap,0,h/2-w/20,paint);
        time++;
        if(isAnimated) {
            try {
                Thread.sleep(100);
                invalidate();
            } catch (Exception ex) {

            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {

        }
        return true;
    }

}
