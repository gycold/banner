package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.RotateUpTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:42
 */
public class RotateUpTransformer extends ABaseTransformer {

    private static final float ROT_MOD = -15f;

    @Override
    protected void onTransform(View view, float position) {
        final float width = view.getWidth();
        final float rotation = ROT_MOD * position;

        view.setPivotX(width * 0.5f);
        view.setPivotY(0f);
        view.setTranslationX(0f);
        view.setRotation(rotation);
    }

    @Override
    protected boolean isPagingEnabled() {
        return true;
    }

}
