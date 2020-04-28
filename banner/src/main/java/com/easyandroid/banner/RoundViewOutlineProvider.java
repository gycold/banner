package com.easyandroid.banner;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.annotation.RequiresApi;

/**
 * package: com.easyandroid.banner.RoundViewOutlineProvider
 * author: gyc
 * description:圆角效果
 * time: create at 2020/4/28 15:20
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class RoundViewOutlineProvider extends ViewOutlineProvider {

    private float mRadius;//圆角弧度

    public RoundViewOutlineProvider(float radius) {
        this.mRadius = radius;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        Rect selfRect = new Rect(0, 0, view.getWidth(), view.getHeight());// 绘制区域
        outline.setRoundRect(selfRect, mRadius);
    }
}
