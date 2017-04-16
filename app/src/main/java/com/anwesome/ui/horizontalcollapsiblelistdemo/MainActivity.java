package com.anwesome.ui.horizontalcollapsiblelistdemo;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.horizontalcollapsiblelist.HorizontalCollapsibleList;

public class MainActivity extends AppCompatActivity {
    private int images[] = {R.drawable.delivered,R.drawable.onway,R.drawable.order};
    private String titles[] = {"delivered","onway","order"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HorizontalCollapsibleList horizontalCollapsibleList = new HorizontalCollapsibleList(this);
        for(int i=0;i<images.length;i++) {
            horizontalCollapsibleList.addItem(BitmapFactory.decodeResource(getResources(),images[i]),titles[i]);
        }
        horizontalCollapsibleList.show();
    }
}
