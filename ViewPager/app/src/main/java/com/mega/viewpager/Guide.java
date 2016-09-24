package com.mega.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadow. on 2016/9/24 0024.
 */

public class Guide extends Activity implements ViewPager.OnPageChangeListener{
    private List<ImageView> imageViewList;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int[] imagesIds = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private int[] layouts = {R.layout.one, R.layout.two, R.layout.three};
    private List<View> mViews;
    private ImageView[] imageViews;
    private Button startButton;
    private LinearLayout pointGroup;
    private int lastPoint;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        viewPager = (ViewPager) findViewById(R.id.id_vp);
        pointGroup = (LinearLayout) findViewById(R.id.id_LinearLayout);
        init();

        startButton = (Button) mViews.get(2).findViewById(R.id.id_bt);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Guide.this, MainActivity.class));
                finish();
            }
        });
        adapter = new ViewPagerAdapter(mViews, this);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        /**
         * 自动轮播
         */
        handler.sendEmptyMessageDelayed(0, 2000);
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = viewPager.getCurrentItem() + 1;
            Log.i("TAG", "handleMessage: "+viewPager.getCurrentItem());
            if (item==3){
                item = 0;
            }
            viewPager.setCurrentItem( item );
            if (true) {
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    private void init() {
//        imageViewList = new ArrayList<>();
//        for (int i = 0; i < imagesIds.length; i++) {
//            ImageView imageView = new ImageView(this);
//            imageView.setBackgroundResource(imagesIds[i]);
//            imageViewList.add(imageView);
//        }
        mViews = new ArrayList<>();
        LayoutInflater in = LayoutInflater.from(this);
        for (int i = 0; i < layouts.length; i++) {
            View view = in.inflate(layouts[i], null);
            mViews.add(view);
            ImageView point = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 10;
            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointGroup.addView(point,lp);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pointGroup.getChildAt(position).setEnabled(true);
        pointGroup.getChildAt(lastPoint).setEnabled(false);
        lastPoint = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
