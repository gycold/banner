package com.easyandroid.banner.loader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.easyandroid.banner.R;

/**
 * package: com.easyandroid.banner.loader.ImageLoader
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:33
 */
public abstract class ImageLoader implements ImageLoaderInterface<View> {

    @Override
    public View createView(Context context) {
//        ImageView imageView = new ImageView(context);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_img, null);
        return view;
    }
}
