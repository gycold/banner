package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.RotateDownTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:40
 */
public class RotateDownTransformer extends ABaseTransformer {

    private static final float ROT_MOD = -15f;

    @Override
    protected void onTransform(View view, float position) {
        final float width = view.getWidth();
        final float height = view.getHeight();
        final float rotation = ROT_MOD * position * -1.25f;

        view.setPivotX(width * 0.5f);
        view.setPivotY(height);
        view.setRotation(rotation);
    }

    @Override
    protected boolean isPagingEnabled() {
        return true;
    }

}
