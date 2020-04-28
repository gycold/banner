package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.AccordionTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:35
 */
public class AccordionTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0 ? 0 : view.getWidth());
        view.setScaleX(position < 0 ? 1f + position : 1f - position);
    }
}
