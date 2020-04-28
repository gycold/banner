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
 * package: com.easyandroid.demos.IndicatorPositionActivity
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:30
 */
public class IndicatorPositionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Banner banner;
    Spinner spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator_position);
        banner = findViewById(R.id.banner);
        spinnerPosition = findViewById(R.id.spinnerPosition);
        spinnerPosition.setOnItemSelectedListener(this);

        banner.setImages(AppApplication.images)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                banner.setIndicatorGravity(BannerConfig.LEFT);
                break;
            case 1:
                banner.setIndicatorGravity(BannerConfig.CENTER);
                break;
            case 2:
                banner.setIndicatorGravity(BannerConfig.RIGHT);
                break;
        }
        banner.start();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
