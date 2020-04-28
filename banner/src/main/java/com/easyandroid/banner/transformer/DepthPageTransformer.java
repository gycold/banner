package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.DepthPageTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:38
 */
public class DepthPageTransformer extends ABaseTransformer {

    private static final float MIN_SCALE = 0.75f;

    @Override
    protected void onTransform(View view, float position) {
        if (position <= 0f) {
            view.setTranslationX(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);
        } else if (position <= 1f) {
            final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setAlpha(1 - position);
            view.setPivotY(0.5f * view.getHeight());
            view.setTranslationX(view.getWidth() * -position);
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }
    }

    @Override
    protected boolean isPagingEnabled() {
        return true;
    }

}
