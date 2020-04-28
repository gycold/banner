package com.easyandroid.banner;

import androidx.viewpager.widget.ViewPager.PageTransformer;

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

/**
 * package: com.easyandroid.banner.Transformer
 * author: gyc
 * description:
 * time: create at 2019/6/13 0013 下午 22:49
 */
public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
