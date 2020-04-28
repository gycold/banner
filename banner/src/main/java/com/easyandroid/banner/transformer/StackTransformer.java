package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.StackTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:44
 */
public class StackTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setTranslationX(position < 0 ? 0f : -view.getWidth() * position);
    }

}
