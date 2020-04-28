package com.easyandroid.demos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.easyandroid.R;
import com.easyandroid.banner.Banner;
import com.easyandroid.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * package: com.easyandroid.demos.BannerLocalActivity
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:27
 */
public class BannerLocalActivity extends AppCompatActivity {

    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_local);
        initView();
    }

    private void initView() {
        banner = findViewById(R.id.banner);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.b1);
        list.add(R.mipmap.b2);
        list.add(R.mipmap.b3);

        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();

    }
}
