package com.easyandroid.banner.loader;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * package: com.easyandroid.banner.loader.ImageLoaderInterface
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:32
 */
public interface ImageLoaderInterface<T extends View> extends Serializable {
    void displayImage(Context context, Object path, T imageView);

    T createView(Context context);
}
