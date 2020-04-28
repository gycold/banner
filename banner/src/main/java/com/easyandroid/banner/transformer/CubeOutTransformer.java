package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.CubeOutTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:38
 */
public class CubeOutTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0f ? view.getWidth() : 0f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(90f * position);
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }
}
