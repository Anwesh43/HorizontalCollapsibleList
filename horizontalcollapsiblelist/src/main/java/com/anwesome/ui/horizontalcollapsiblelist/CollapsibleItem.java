package com.anwesome.ui.horizontalcollapsiblelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class CollapsibleItem extends View {
    private Bitmap bitmap;
    private String title;
    private int time = 0;
    private int dir =0;
    private float scale = 0;
    private boolean isAnimated = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private CollapsibleButton collapsibleButton;
    public CollapsibleItem(Context context,Bitmap bitmap,String title) {
        super(context);
        this.bitmap = bitmap;
        this.title = title;
    }
    public void onDraw(Canvas canvas) {

        int w = canvas.getWidth(),h = canvas.getHeight();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(0,0,w/5,h),paint);
        if(time == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,w/20,w/20,true);
            collapsibleButton = new CollapsibleButton(w/10+w/20+w/40,h/2,w/20);
        }
        paint.setTextSize(h/3);
        canvas.save();
        Path path = new Path();
        path.addCircle(w/20,h/2,w/30, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,w/20-w/40,h/2-w/40,paint);
        canvas.restore();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path,paint);
        paint.setStyle(Paint.Style.FILL);
        collapsibleButton.draw(canvas,paint);
        canvas.save();
        canvas.translate(w/5,h/2);
        paint.setColor(Color.WHITE);
        Path rectPath = new Path();
        rectPath.addRect(new RectF(0,-h/2,(4*w/5)*scale,h/2), Path.Direction.CCW);
        canvas.clipPath(rectPath);
        canvas.drawPath(rectPath,paint);
        paint.setColor(Color.BLACK);
        String newTitle = adjustString(w/2);
        canvas.drawText(newTitle,w/2-paint.measureText(newTitle)/2,paint.getTextSize()/6,paint);
        canvas.restore();
        time++;
        if(isAnimated) {
            scale+=0.1f*dir;
            collapsibleButton.move();
            if(collapsibleButton.stop()) {
                isAnimated = false;
            }
            try {
                Thread.sleep(50);
                invalidate();
            } catch (Exception ex) {

            }
        }
    }
    private String adjustString(float w) {
        String msg = "";
        for(int i=0;i<title.length();i++) {
            if(paint.measureText(msg+title.charAt(i))>w) {
                msg = msg+"..";
            }
            else {
                msg = msg+title.charAt(i);
            }
        }
        return msg;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && collapsibleButton.handleTap(event.getX(),event.getY())) {
            isAnimated = true;
            postInvalidate();
        }
        return true;
    }
    private class CollapsibleButton {
        private float x,y,w,deg = 0;
        public CollapsibleButton(float x,float y,float w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.BLACK);
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            paint.setStrokeWidth(3);
            canvas.drawLine(-w/2,0,w/2,0,paint);
            for(int i=0;i<2;i++) {
                canvas.drawLine(w/2,0,w/2-w/6,(i*2-1)*w/6,paint);
            }
            canvas.restore();
        }
        public void move() {
            deg+=dir*18;
            if(deg>=180 || deg<=0) {
                dir = 0;
            }
        }
        public boolean stop() {
            return dir == 0;
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x-w && x<=this.x+w && y>=this.y-w && y<=this.y+w;
            if(condition) {
                dir = deg == 0?1:-1;
            }
            return condition;
        }
    }
}
