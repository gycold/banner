package com.easyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.easyandroid.banner.Banner;
import com.easyandroid.banner.listener.OnBannerListener;
import com.easyandroid.demos.BannerAnimationActivity;
import com.easyandroid.demos.BannerLocalActivity;
import com.easyandroid.demos.BannerStyleActivity;
import com.easyandroid.demos.CustomBannerActivity;
import com.easyandroid.demos.CustomViewPagerActivity;
import com.easyandroid.demos.IndicatorPositionActivity;
import com.easyandroid.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, OnBannerListener {

    static final int REFRESH_COMPLETE = 0X1112;
    SuperSwipeRefreshLayout mSwipeLayout;
    ListView listView;
    Banner banner;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    String[] urls = getResources().getStringArray(R.array.url4);
                    List list = Arrays.asList(urls);
                    List arrayList = new ArrayList(list);
                    banner.update(arrayList);
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeLayout = findViewById(R.id.swipe);
        mSwipeLayout.setOnRefreshListener(this);
        listView = findViewById(R.id.list);
        View header = LayoutInflater.from(this).inflate(R.layout.header, null);
        banner = header.findViewById(R.id.banner);
        banner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppApplication.HEIGHT / 4));
        listView.addHeaderView(banner);

        String[] data = getResources().getStringArray(R.array.demo_list);
        listView.setAdapter(new SampleAdapter(this, data));
        listView.setOnItemClickListener(this);

        //简单使用
        banner.setImageLoader(new GlideImageLoader())
                .setImages(AppApplication.images)
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void onBannerClick(int position) {
        Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
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


    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                startActivity(new Intent(this, BannerAnimationActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, BannerStyleActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, IndicatorPositionActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, CustomBannerActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, BannerLocalActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, CustomViewPagerActivity.class));
                break;
        }
    }

}
