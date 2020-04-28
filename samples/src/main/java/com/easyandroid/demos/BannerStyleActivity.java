package com.easyandroid.demos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.easyandroid.AppApplication;
import com.easyandroid.R;
import com.easyandroid.banner.Banner;
import com.easyandroid.banner.BannerConfig;
import com.easyandroid.loader.GlideImageLoader;

/**
 * package: com.easyandroid.demos.BannerStyleActivity
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:28
 */
public class BannerStyleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Banner banner;
    Spinner spinnerStyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_style);
        banner = findViewById(R.id.banner);
        spinnerStyle = findViewById(R.id.spinnerStyle);
        spinnerStyle.setOnItemSelectedListener(this);

        //默认是CIRCLE_INDICATOR
        banner.setImages(AppApplication.images)
                .setBannerTitles(AppApplication.titles)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                banner.updateBannerStyle(BannerConfig.NOT_INDICATOR);
                break;
            case 1:
                banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                break;
            case 2:
                banner.updateBannerStyle(BannerConfig.NUM_INDICATOR);
                break;
            case 3:
                banner.updateBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                break;
            case 4:
                banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                break;
            case 5:
                banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
