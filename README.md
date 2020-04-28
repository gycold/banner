# Android图片轮播控件
[![banner](https://api.bintray.com/packages/easyandroid/maven/banner/images/download.svg)](https://bintray.com/easyandroid/maven/banner/_latestVersion)  [![Platform](https://img.shields.io/badge/platform-Android-green.svg)](https://developer.android.google.cn/) [![SDK](https://img.shields.io/badge/minSdkVersion-15%2B-green.svg)](https://developer.android.com/about/)

项目基于[banner](https://github.com/youth5201314/banner)升级改造，扩展了指示器的形状，可动态添加指示器等，sample基于Glide框架最新版本，后期会持续更新

## 使用步骤

#### Step 1.依赖banner
Gradle
```groovy
dependencies{
    implementation 'com.easyandroid:banner:1.0.2'  //最新版本
}
```

#### Step 2.添加权限到你的 AndroidManifest.xml
```xml
<!-- if you want to load images from the internet -->
<uses-permission android:name="android.permission.INTERNET" />
<!-- if you want use storage permission -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
+ 注意，Android9.0以后，如果使用http协议，需要在```<application>```节点下添加```android:usesCleartextTraffic="true"```属性，或配置xml文件，并在```<application>```节点引用

#### Step 3.在布局文件中添加Banner，可以设置自定义属性
+ 注意，此步骤可以省略，直接在Activity或者Fragment中new Banner();
```xml
<com.easyandroid.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度" />
```

#### Step 4.重写图片加载器
```java
public class GlideImageLoader extends ImageLoader {

  private RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.shape_loading)
            .error(R.drawable.shape_loading)
            .centerCrop();

    @Override
    public void displayImage(Context context, Object url, ImageView imageView) {
        /**
          注意：
          1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
          2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
          传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
          切记不要胡乱强转！
         */
        eg：

        //Glide 加载图片简单用法
        Glide.with(context).load(url)
                .apply(options)
                .into(imageView);

        //Picasso 加载图片简单用法
        Picasso.with(context).load(path).into(imageView);

        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        Uri uri = Uri.parse((String) url);
        imageView.setImageURI(uri);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
```

#### Step 5.在Activity或者Fragment中配置Banner

+ 注意：start()方法必须放到最后执行，点击事件请放到start()前

```java
--------------------------简单使用-------------------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = findViewById(R.id.banner);
    //设置图片加载器
    banner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    banner.setImages(images);
    //banner设置方法全部调用完毕时最后调用
    banner.start();
}
--------------------------详细使用-------------------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = findViewById(R.id.banner);
    //设置banner样式
    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
    //设置图片加载器
    banner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    banner.setImages(images);
    //设置banner动画效果
    banner.setBannerAnimation(Transformer.DepthPage);
    //设置标题集合（当banner样式有显示title时）
    banner.setBannerTitles(titles);
    //设置自动轮播，默认为true
    banner.isAutoPlay(true);
    //设置轮播时间
    banner.setDelayTime(1500);
    //设置指示器位置（当banner模式中有指示器时）
    banner.setIndicatorGravity(BannerConfig.CENTER);
    //banner设置方法全部调用完毕时最后调用
    banner.start();
}
-----------------极简使用--------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = findViewById(R.id.banner);
    banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
}
```

#### Step 6.（可选）增加体验
```java
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
```

## 混淆代码
```java
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.easyandroid.banner.** {
    *;
 }

```

## TODO LIST
  + Indicator移动动画
  + Indicator移动动画模式：
      + INSIDE：movingItem在Indicator阵列之内；
      + OUTSIDE：movingItem在Indicator阵列之外；
      + DEFAULT：默认没有动画;


## 效果图

|模式|图片|
|---|---|
|无指示器模式|![效果示例](http://pt2zaqk0u.bkt.clouddn.com/banner0.jpg)|
|指示器模式|![效果示例](http://pt2zaqk0u.bkt.clouddn.com/banner1.jpg)|
|数字模式|![效果示例](http://pt2zaqk0u.bkt.clouddn.com/banner2.jpg)|
|数字加标题模式|![效果示例](http://pt2zaqk0u.bkt.clouddn.com/banner3.jpg)|
|指示器加标题模式<br>垂直显示|![效果示例](http://pt2zaqk0u.bkt.clouddn.com/banner4.jpg)|
|指示器加标题模式<br>水平显示|![效果示例](http://pt2zaqk0u.bkt.clouddn.com/banner5.jpg)|
|自定义指示器样式|![效果示例](http://pt2zaqk0u.bkt.clouddn.com/banner6.jpg)|

## 常量
|常量名称|描述|所属方法|
|---|---|---|
|BannerConfig.NOT_INDICATOR| 不显示指示器和标题|setBannerStyle|
|BannerConfig.CIRCLE_INDICATOR| 显示圆形指示器|setBannerStyle|
|BannerConfig.NUM_INDICATOR| 显示数字指示器|setBannerStyle|
|BannerConfig.NUM_INDICATOR_TITLE| 显示数字指示器和标题|setBannerStyle|
|BannerConfig.CIRCLE_INDICATOR_TITLE| 显示圆形指示器和标题（垂直显示）|setBannerStyle|
|BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE| 显示圆形指示器和标题（水平显示）|setBannerStyle|
|BannerConfig.LEFT| 指示器居左|setIndicatorGravity|
|BannerConfig.CENTER| 指示器居中|setIndicatorGravity|
|BannerConfig.RIGHT| 指示器居右|setIndicatorGravity|
|BannerConfig.PADDING_LEFT| banner左边距|setBannerPaddingLeft|
|BannerConfig.PADDING_RIGHT| banner右边距|setBannerPaddingRight|

## 方法
|方法名|描述|版本限制
|---|---|---|
|setBannerStyle(int bannerStyle)| 设置轮播样式（默认为CIRCLE_INDICATOR）|无
|setIndicatorGravity(int type)| 设置指示器位置（没有标题默认为右边,有标题时默认左边）|无
|setBannerPaddingLeft(int type)| 设置banner左边距|1.0.2
|setBannerPaddingRight(int type)| 设置banner右边距|1.0.2
|setSelectedDrawable(GradientDrawable selectedDrawable)| 设置指示器选中drawable，可配合ShapeUtil使用，设置后xml指定的indicator_drawable_selected无效|无
|setUnSelectedDrawable(GradientDrawable unSelectedDrawable)| 设置指示器未选中drawable，可配合ShapeUtil使用，设置后xml指定的indicator_drawable_unselected无效|无
|isAutoPlay(boolean isAutoPlay)| 设置是否自动轮播（默认自动）|无
|setViewPagerIsScroll(boolean isScroll)| 设置是否允许手动滑动轮播图（默认true）无
|update(List<?> imageUrls,List<String> titles)| 更新图片和标题 |无
|update(List<?> imageUrls)| 更新图片 |无
|startAutoPlay()|开始轮播|无，此方法只作用于banner加载完毕-->需要在start()后执行
|stopAutoPlay()|结束轮播|无，此方法只作用于banner加载完毕-->需要在start()后执行
|start()|开始进行banner渲染（必须放到最后执行）|无
|setOffscreenPageLimit(int limit)|同viewpager的方法作用一样|无
|setBannerTitles(List<String> titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）|无
|setDelayTime(int time)| 设置轮播图片间隔时间（单位毫秒，默认为2000）|无
|setImages(Object[]/List<?> imagesUrl)| 设置轮播图片(所有设置参数方法都放在此方法之前执行)|无
|setOnBannerListener(this)|设置点击事件，下标是从0开始|无
|setImageLoader(Object implements ImageLoader)|设置图片加载器|无
|setOnPageChangeListener(this)|设置viewpager的滑动监听|无
|setBannerAnimation(Class<? extends PageTransformer> transformer)|设置viewpager的默认动画,传值见动画表|无
|setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer)|设置viewpager的自定义动画|无

## Attributes属性（banner布局文件中调用）
|Attributes|forma|describe
|---|---|---|
|delay_time| integer|轮播间隔时间，默认2000
|scroll_time| integer|轮播滑动执行时间，默认800
|is_auto_play| boolean|是否自动轮播，默认true
|title_background| color|reference|标题栏的背景色
|title_textcolor| color|标题字体颜色
|title_textsize| dimension|标题字体大小
|title_height| dimension|标题栏高度
|indicator_selected_width| dimension|指示器选中的宽度
|indicator_unselected_width| dimension|指示器未选中的宽度（如果和选中一样则是圆形，默认是一样的）
|indicator_height| dimension|指示器的高度
|indicator_margin| dimension|指示器之间的间距
|indicator_drawable_selected| reference|指示器选中效果（需注意，受指示器宽度和高度的限制）
|indicator_drawable_unselected| reference|指示器未选中效果（需注意，受指示器宽度和高度的限制）
|image_scale_type| enum |和imageview的ScaleType作用一样
|banner_default_image| reference | 当banner数据为空是显示的默认图片
|banner_layout| reference |自定义banner布局文件，但是必须保证id的名称一样（你可以将banner的布局文件复制出来进行修改）
|banner_padding_left| dimension |banner左边距
|banner_padding_right| dimension |banner右边距
|banner_background_resource| color/reference |整个banner布局背景，可以配合mLayoutResId实现自定义背景，注意使用引用资源，不可直接使用"#ffffff"这种形式


## 动画常量类（setBannerAnimation方法调用）
[ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms) `可参考动画时集成的第三方库，大致原理是集成ViewPager.PageTransformer，在onTransform()方法内部实现具体动画逻辑`

|常量类名|动画效果|
|---|---|
|Transformer.Default|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif1_default.gif)|
|Transformer.Accordion|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif2_accordion.gif)|
|Transformer.BackgroundToForeground|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif3_backgroundtf.gif)|
|Transformer.ForegroundToBackground|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif4_foregroundtb.gif)|
|Transformer.CubeIn|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif5_cubeIn.gif)|
|Transformer.CubeOut|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif6_cubeOut.gif)|
|Transformer.DepthPage|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif7_depthPage.gif)|
|Transformer.FlipHorizontal|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif8_filpH.gif)|
|Transformer.FlipVertical|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif9_flipV.gif)|
|Transformer.RotateDown|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif10_rotateDown.gif)|
|Transformer.RotateUp|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif11_rotateUp.gif)|
|Transformer.ScaleInOut|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif12_scaleInOut.gif)|
|Transformer.Stack|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif13_stack.gif)|
|Transformer.Tablet|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif14_tablet.gif)|
|Transformer.ZoomIn|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif15_zoomIn.gif)|
|Transformer.ZoomOut|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif16_zoomOut.gif)|
|Transformer.ZoomOutSlide|![动画效果](http://pt4hvjrdd.bkt.clouddn.com/banner_gif17_zoomOutSlide.gif)|

## Thanks

+ [ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms)

## 更新说明

#### v1.0.2
 * 添加`banner_padding_left`属性，使得banner可单独设置左边距
 * 添加`banner_padding_right`属性，使得banner可单独设置右边距
 * 添加`banner_background_resource`属性，使得banner可设置背景资源（color或图片）

#### v1.0.1
 * 去掉`indicator_width`属性，添加`indicator_selected_width`和`indicator_unselected_width`属性，使得指示器宽度可单独设置
 * 添加方法`setSelectedDrawable`、`setUnSelectedDrawable`，配合ShapeUtil工具类，动态添加指示器shape
