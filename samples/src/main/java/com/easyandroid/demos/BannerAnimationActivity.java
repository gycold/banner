package com.easyandroid.demos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.easyandroid.AppApplication;
import com.easyandroid.R;
import com.easyandroid.SampleAdapter;
import com.easyandroid.banner.Banner;
import com.easyandroid.banner.listener.OnBannerListener;
import com.easyandroid.banner.transformer.AccordionTransformer;
import com.easyandroid.banner.transformer.BackgroundToForegroundTransformer;
import com.easyandroid.banner.transformer.CubeInTransformer;
import com.easyandroid.banner.transformer.CubeOutTransformer;
import com.easyandroid.banner.transformer.DefaultTransformer;
import com.easyandroid.banner.transformer.DepthPageTransformer;
import com.easyandroid.banner.transformer.FlipHorizontalTransformer;
import com.easyandroid.banner.transformer.FlipVerticalTransformer;
import com.easyandroid.banner.transformer.ForegroundToBackgroundTransformer;
import com.easyandroid.banner.transformer.RotateDownTransformer;
import com.easyandroid.banner.transformer.RotateUpTransformer;
import com.easyandroid.banner.transformer.ScaleInOutTransformer;
import com.easyandroid.banner.transformer.StackTransformer;
import com.easyandroid.banner.transformer.TabletTransformer;
import com.easyandroid.banner.transformer.ZoomInTransformer;
import com.easyandroid.banner.transformer.ZoomOutSlideTransformer;
import com.easyandroid.banner.transformer.ZoomOutTranformer;
import com.easyandroid.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * package: com.easyandroid.demos.BannerAnimationActivity
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:12
 */
public class BannerAnimationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnBannerListener {

    Banner banner;
    List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();

    public void initData() {
        transformers.add(DefaultTransformer.class);
        transformers.add(AccordionTransformer.class);
        transformers.add(BackgroundToForegroundTransformer.class);
        transformers.add(ForegroundToBackgroundTransformer.class);
        transformers.add(CubeInTransformer.class);//兼容问题，慎用
        transformers.add(CubeOutTransformer.class);
        transformers.add(DepthPageTransformer.class);
        transformers.add(FlipHorizontalTransformer.class);
        transformers.add(FlipVerticalTransformer.class);
        transformers.add(RotateDownTransformer.class);
        transformers.add(RotateUpTransformer.class);
        transformers.add(ScaleInOutTransformer.class);
        transformers.add(StackTransformer.class);
        transformers.add(TabletTransformer.class);
        transformers.add(ZoomInTransformer.class);
        transformers.add(ZoomOutTranformer.class);
        transformers.add(ZoomOutSlideTransformer.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_animation);
        initData();
        banner = findViewById(R.id.banner);
        ListView listView = findViewById(R.id.list);
        String[] data = getResources().getStringArray(R.array.anim);
        listView.setAdapter(new SampleAdapter(this, data));
        listView.setOnItemClickListener(this);

        banner.setImages(AppApplication.images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        banner.setBannerAnimation(transformers.get(position));
    }

    @Override
    public void onBannerClick(int position) {
        Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }
}
