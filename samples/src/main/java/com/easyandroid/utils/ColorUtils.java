package com.easyandroid.utils;


import androidx.core.content.ContextCompat;

import com.easyandroid.AppApplication;


/**
 * package: com.css.video.utils.ColorUtils
 * author: gyc
 * description:
 * time: create at 2019/6/17 0017 下午 14:48
 */
public class ColorUtils {

    public static int getColor(int resId) {
        return ContextCompat.getColor(AppApplication.app.getApplicationContext(), resId);
    }

}
