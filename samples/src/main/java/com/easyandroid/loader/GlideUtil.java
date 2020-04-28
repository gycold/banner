package com.easyandroid.loader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.easyandroid.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * package: com.easyandroid.loader.GlideUtil
 * author: gyc
 * description:Glide封装类
 * time: create at 2018/3/19 16:27
 */

public class GlideUtil {

    public static GlideUtil getInstance() {
        return GlideUtilHolder.sInstance;
    }

    private static class GlideUtilHolder {
        private static final GlideUtil sInstance = new GlideUtil();
    }

    //圆形头像占位图
    private RequestOptions optionsHead = new RequestOptions()
            .placeholder(R.mipmap.ic_launcher)
            .centerCrop()
            .error(R.mipmap.ic_launcher)
            .circleCrop()
            .dontAnimate();

    //圆形头像占位图，当控件使用CircleImageView时，使用此占位图设置，避免重复设置圆形
    private RequestOptions optionsHead2 = new RequestOptions()
            .placeholder(R.mipmap.ic_launcher)
            .centerCrop()
            .error(R.mipmap.ic_launcher)
            .dontAnimate();

    //默认占位图
    private RequestOptions optionsDefault = new RequestOptions()
            .placeholder(R.drawable.shape_loading)
            .error(R.drawable.shape_loading)
            .centerCrop()
            .dontAnimate();

    //圆角占位图
    private int defaultRadius = 24;//默认圆角大小
    private RequestOptions optionsRound;

    public RequestOptions getRoundOptions() {
        if (optionsRound == null) {
            optionsRound = RequestOptions.bitmapTransform(new MultiTransformation<>(
                    new CenterCrop(),
                    new RoundedCornersTransformation(defaultRadius, 0,
                            RoundedCornersTransformation.CornerType.ALL)))
                    .placeholder(R.drawable.shape_loading_round)
                    .error(R.drawable.shape_loading_round);
        }
        return optionsRound;
    }

    /**
     * 加载一张图片到ImageView
     *
     * @param url
     * @param img
     */
    public void loadPicture(String url, ImageView img) {
        Glide.with(img.getContext())
                .load(url)
                .apply(optionsDefault)
                .transition(withCrossFade())
                .into(img);
    }

    /**
     * 加载gif
     *
     * @param url
     * @param img
     */
    public void loadGif(String url, ImageView img) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.shape_loading)
                .priority(Priority.HIGH);
        Glide.with(img.getContext())
                .load(url)
                .apply(options)
                .into(img);
    }

    /**
     * 加载一张图片到ImageView
     *
     * @param url
     * @param img
     */
    public void loadPicture(String url, ImageView img, RequestOptions options) {
        Glide.with(img.getContext())
                .load(url)
                .apply(options)
                .transition(withCrossFade())
                .into(img);
    }

    /**
     * 加载圆形头像
     *
     * @param url
     * @param img
     */
    public void loadHeadPicture(String url, ImageView img) {
        Glide.with(img.getContext())
                .load(url)
                .apply(optionsHead)
                .into(img);
    }

    /**
     * 加载圆形头像，当ImageView对象时CircleImageView时使用此方法
     *
     * @param url
     * @param img
     */
    public void loadHeadNoCircle(String url, ImageView img) {
        Glide.with(img.getContext())
                .load(url)
                .apply(optionsHead2)
                .into(img);
    }

    /**
     * 加载圆角图片，圆角大小默认为5dp
     *
     * @param url
     * @param img
     */
    public void loadRoundPicture(String url, ImageView img) {
        Glide.with(img.getContext())
                .load(url)
                .apply(getRoundOptions())
                .transition(withCrossFade())
                .into(img);
    }

    /**
     * 加载自定义圆角大小圆角图片
     *
     * @param url
     * @param img
     * @param options
     */
    public void loadRoundPicture(String url, ImageView img, RequestOptions options) {
        Glide.with(img.getContext())
                .load(url)
                .apply(options)
                .transition(withCrossFade())
                .into(img);
    }

    /**
     * 给定固定尺寸宽度，等比算出高度，并重新赋值LayoutParams
     *
     * @param url
     * @param fixedWidth
     * @param img
     */
    public void loadScaledImg(String url, final int fixedWidth, final ImageView img) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.shape_loading)
                .priority(Priority.HIGH);
        Glide.with(img.getContext())
                .load(url)
//                .apply(optionsDefault)
//                .transition(withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        float width = resource.getIntrinsicWidth();
                        float height = resource.getIntrinsicHeight();
                        float scale = height / width;
                        int fixedHeight = (int) (fixedWidth * scale);
                        ViewGroup.LayoutParams params = img.getLayoutParams();
                        params.width = fixedWidth;
                        params.height = fixedHeight;
                        img.setLayoutParams(params);
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        img.setImageDrawable(resource);
                        return false;
                    }
                }).apply(options)
                .into(img);
    }

    /**
     * 给定固定尺寸宽度，等比算出高度，并重新赋值LayoutParams
     *
     * @param resId
     * @param fixedWidth
     * @param img
     */
    public void loadScaledImg(int resId, final int fixedWidth, final ImageView img) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.shape_loading)
                .priority(Priority.HIGH);
        Glide.with(img.getContext())
                .load(resId)
//                .apply(optionsDefault)
//                .transition(withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        float width = resource.getIntrinsicWidth();
                        float height = resource.getIntrinsicHeight();
                        float scale = height / width;
                        int fixedHeight = (int) (fixedWidth * scale);
                        ViewGroup.LayoutParams params = img.getLayoutParams();
                        params.width = fixedWidth;
                        params.height = fixedHeight;
                        img.setLayoutParams(params);
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        img.setImageDrawable(resource);
                        return false;
                    }
                }).apply(options)
                .into(img);
    }

    /**
     * 设置模糊度为23(在0.0到25.0之间)，默认”25";
     *
     * @param url
     * @param img
     */
    public void loadBlurImg(String url, ImageView img) {
        RequestOptions options = bitmapTransform(new BlurTransformation(23));
        Glide.with(img.getContext())
                .load(url)
                .apply(options)
                .transition(withCrossFade())
                .into(img);
    }

    /**
     * 暂停加载
     *
     * @param context
     */
    public void pauseLoad(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 恢复加载
     *
     * @param context
     */
    public void resumeLoad(Context context) {
        Glide.with(context).resumeRequests();
    }

}
