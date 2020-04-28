package com.easyandroid.demos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.easyandroid.AppApplication;
import com.easyandroid.R;
import com.easyandroid.banner.Banner;
import com.easyandroid.banner.BannerConfig;
import com.easyandroid.loader.GlideImageLoader;

/**
 * package: com.easyandroid.demos.CustomBannerActivity
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:28
 */
public class CustomBannerActivity extends AppCompatActivity {

    Banner banner1,banner2,banner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        banner1 = findViewById(R.id.banner1);
        banner2 = findViewById(R.id.banner2);
        banner3 = findViewById(R.id.banner3);

        banner1.setImageLoader(new GlideImageLoader());
        banner1.setImages(AppApplication.images);
        banner1.start();

        banner2.setImages(AppApplication.images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner3.setImages(AppApplication.images)
                .setBannerTitles(AppApplication.titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();
    }
}
