package com.mega.percentrelativelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class PercentRelativeLayout extends RelativeLayout {
    public PercentRelativeLayout(Context context) {
        super(context);
    }
    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 只需要重新测量child的宽高就行了
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //计算父容器的宽和高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height =MeasureSpec.getSize(heightMeasureSpec);
//        int width1 = MeasureSpec.getSize(widthMeasureSpec);
//        int width = getWidth();
//        int height = getHeight();
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            float widthPercent = 0;
            float heightPercent = 0;
            if (lp instanceof PercentRelativeLayout.LayoutParams) {
                //支持百分比
                widthPercent = ((LayoutParams) lp).widthPercent;
                heightPercent = ((LayoutParams) lp).HeightPercent;
            }
            if (widthPercent != 0) {
                lp.width = (int) (widthPercent * width);
            }
            if (heightPercent != 0) {
                lp.height = (int) (heightPercent * height);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams{
        private float widthPercent;
        private float HeightPercent;
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);
            widthPercent = a.getFloat(R.styleable.PercentRelativeLayout_widthLayoutPercent, widthPercent);
            HeightPercent = a.getFloat(R.styleable.PercentRelativeLayout_heightLayoutPercent, HeightPercent);
            a.recycle();
        }
    }
}
