package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.CubeInTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:37
 */
public class CubeInTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
// Rotate the fragment on the left or right edge
        view.setPivotX(position > 0 ? 0 : view.getWidth());
        view.setPivotY(0);
        view.setRotationY(-90f * position);
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }
}
