package com.easyandroid;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.easyandroid.banner.Banner;
import com.easyandroid.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * package: com.easyandroid.TestActivity
 * author: gyc
 * description:
 * time: create at 2020/4/27 15:56
 */
public class TestActivity extends AppCompatActivity {

    private Banner banner;
    private View view;
    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;
    private static final int CYAN = 0xff80ffff;
    boolean isFirst = true;
    int size;
    int previousPosition;

    List<String> urls = new ArrayList<>();
    public static List<Integer> rgbs = new ArrayList<>();

    ValueAnimator colorAnim;
    ArgbEvaluator evaluator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        banner = findViewById(R.id.banner);
        view = findViewById(R.id.view);

        rgbs.add(RED);
        rgbs.add(BLUE);
        rgbs.add(CYAN);
        size = rgbs.size();
        colorAnim = ObjectAnimator.ofInt(view, "backgroundColor", rgbs.get(0), rgbs.get(1));
        evaluator = new ArgbEvaluator();
        colorAnim.setDuration(200);
        colorAnim.setEvaluator(evaluator);
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(0);

        urls.add("http://seopic.699pic.com/photo/40006/5893.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/40112/8505.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/40111/6048.jpg_wh1200.jpg");


        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(urls);
        banner.start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (size > 0) {
                    if (previousPosition != position) {
                        colorGradient(rgbs.get(previousPosition), rgbs.get(position));
                    }
                }

                previousPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private void colorGradient(int colorStart, int colorEnd) {
        colorAnim.setIntValues(colorStart, colorEnd);
        colorAnim.start();
    }
}
