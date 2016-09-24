package com.mega.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shadow. on 2016/9/24 0024.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mImagesList;
    private Context mContext;

    public ViewPagerAdapter(List<View> mImages, Context mContext) {
        this.mImagesList = mImages;
        this.mContext = mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImagesList.get(position));

        return mImagesList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImagesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
