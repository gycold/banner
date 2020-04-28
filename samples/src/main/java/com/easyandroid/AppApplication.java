package com.easyandroid;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zxy.recovery.core.Recovery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * package: com.easyandroid.demos.AppApplication
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 23:02
 */
public class AppApplication extends Application {
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    public static int HEIGHT, WIDTH;
    public static AppApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        getScreen(this);
        Fresco.initialize(this);
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .init(this);
        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles = new ArrayList(list1);
    }

    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        HEIGHT = dm.heightPixels;
        WIDTH = dm.widthPixels;
    }
}
