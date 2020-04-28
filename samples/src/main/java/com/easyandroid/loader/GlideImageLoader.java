package com.easyandroid.loader;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.easyandroid.R;
import com.easyandroid.banner.loader.ImageLoader;

/**
 * package: com.easyandroid.loader.GlideImageLoader
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:15
 */
public class GlideImageLoader extends ImageLoader {

    private RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.shape_loading)
//            .dontAnimate()
            .error(R.drawable.shape_loading)
            .centerCrop();

    @Override
    public void displayImage(Context context, Object url, View view) {
        ImageView imageView = view.findViewById(R.id.iv_banner);
//        Glide.with(context).load(url)
//                .apply(options)
//                .into(imageView);
        GlideUtil.getInstance()
                .loadRoundPicture((String) url, imageView);

    }
}
