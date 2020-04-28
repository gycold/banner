package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.FlipHorizontalTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:39
 */
public class FlipHorizontalTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        final float rotation = 180f * position;

        view.setAlpha(rotation > 90f || rotation < -90f ? 0 : 1);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(rotation);
    }

    @Override
    protected void onPostTransform(View page, float position) {
        super.onPostTransform(page, position);

        //resolve problem: new page can't handle click event!
        if (position > -0.5f && position < 0.5f) {
            page.setVisibility(View.VISIBLE);
        } else {
            page.setVisibility(View.INVISIBLE);
        }
    }
}
