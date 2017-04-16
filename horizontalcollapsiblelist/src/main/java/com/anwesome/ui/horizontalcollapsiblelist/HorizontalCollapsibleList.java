package com.anwesome.ui.horizontalcollapsiblelist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class HorizontalCollapsibleList {
    private Activity activity;
    private ScrollView scrollView;
    private ListLayout listLayout;
    public HorizontalCollapsibleList(Activity activity) {
        this.activity = activity;
        listLayout = new ListLayout(activity);
    }
    public void addItem(Bitmap bitmap,String title) {
        if(listLayout!=null && scrollView == null) {
            listLayout.addItem(bitmap,title);
        }
    }
    public void show() {
        if(listLayout!=null && scrollView == null) {
            scrollView = new ScrollView(activity);
            scrollView.addView(listLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            activity.setContentView(scrollView);

        }
    }
}
