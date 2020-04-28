package com.easyandroid.banner.transformer;

import android.view.View;

/**
 * package: com.easyandroid.banner.transformer.DefaultTransformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:38
 */
public class DefaultTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }
}
