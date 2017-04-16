package com.anwesome.ui.horizontalcollapsiblelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class ListLayout extends ViewGroup {
    private int w,h;
    public ListLayout(Context context) {
        super(context);
    }
    public void initDimension(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display!=null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void addItem(Bitmap bitmap,String title) {
        CollapsibleItem collapsibleItem = new CollapsibleItem(getContext(),bitmap,title);
        addView(collapsibleItem,new LayoutParams(9*w/10,Math.min(w,h)/10));
        requestLayout();
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int y = h/40;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.layout(w/20,y,w/20+child.getMeasuredWidth(),y+child.getMeasuredHeight());
            y+=h/40+child.getMeasuredHeight();
        }
    }
    public void onMeasure(int wspec,int hspec) {
        int newH = h/40;
        for(int i=0;i<getChildCount();i++) {
            View view = getChildAt(i);
            measureChild(view,wspec,hspec);
            newH+=h/40+view.getMeasuredHeight();
        }
        setMeasuredDimension(w,Math.max(h,newH));
    }
}
