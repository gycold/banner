package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.ScaleInOutTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:43
 */
public class ScaleInOutTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0 ? 0 : view.getWidth());
        view.setPivotY(view.getHeight() / 2f);
        float scale = position < 0 ? 1f + position : 1f - position;
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

}
