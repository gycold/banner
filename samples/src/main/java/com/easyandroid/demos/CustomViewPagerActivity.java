package com.easyandroid.demos;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.easyandroid.AppApplication;
import com.easyandroid.R;
import com.easyandroid.banner.Banner;
import com.easyandroid.banner.listener.OnBannerListener;
import com.easyandroid.loader.GlideImageLoader;

/**
 * package: com.easyandroid.demos.CustomViewPagerActivity
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:29
 */
public class CustomViewPagerActivity extends AppCompatActivity implements OnBannerListener {
    Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);

        banner = findViewById(R.id.banner);
        banner.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppApplication.HEIGHT / 4));
        //简单使用
        banner.setImages(AppApplication.images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void onBannerClick(int position) {

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
