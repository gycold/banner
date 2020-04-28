package com.easyandroid;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * package: com.easyandroid.GallyPageTransformer
 * author: gyc
 * description:
 * 自定义动画效果
 * 调用 banner.setPageTransformer()方法设置
 * time: create at 2019/6/13 0013 下午 23:23
 */
public class GallyPageTransformer implements ViewPager.PageTransformer {
    private static final float min_scale = 0.85f;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor = Math.max(min_scale, 1 - Math.abs(position));
        float rotate = 20 * Math.abs(position);
        if (position < -1) {

        } else if (position < 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(rotate);
        } else if (position >= 0 && position < 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        } else if (position >= 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        }
    }
}
