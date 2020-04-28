package com.easyandroid.loader;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.easyandroid.R;
import com.easyandroid.banner.loader.ImageLoader;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * package: com.easyandroid.loader.FrescoImageLoader
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:13
 */
public class FrescoImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, View view) {
        //用fresco加载图片
        Uri uri = Uri.parse((String) path);
        ImageView imageView = view.findViewById(R.id.iv_banner);
        imageView.setImageURI(uri);

    }

    //提供createImageView 方法，方便fresco自定义ImageView
    @Override
    public View createView(Context context) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
