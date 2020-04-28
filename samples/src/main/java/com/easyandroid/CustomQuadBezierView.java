package com.easyandroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.easyandroid.utils.ColorUtils;


/**
 * package: com.css.video.widgets.CustomQuadBezierView
 * author: gyc
 * description:利用二阶贝塞尔曲线画带弧度的背景
 * time: create at 2019/6/12 16:24
 */
public class CustomQuadBezierView extends View {

    private Paint paint;
    private int width, height;
    private int controlWidth, controlHeight;
    private float percent = 0.111f;
    private int[] colors = new int[2];
    private Path path;

    public CustomQuadBezierView(Context context) {
        this(context, null, 0);
    }

    public CustomQuadBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomQuadBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QuadBezierView);
        int startColor = a.getColor(R.styleable.QuadBezierView_startColor2, ColorUtils.getColor(R.color.home_gradient_bg_start_color));
//        middleColor = a.getColor(R.styleable.QuadBezierView_middleColor, Color.parseColor("#d17cdb"));
        int endColor = a.getColor(R.styleable.QuadBezierView_endColor2, ColorUtils.getColor(R.color.home_gradient_bg_end_color));
        percent = a.getFloat(R.styleable.QuadBezierView_percent2, percent);
        a.recycle();

        colors[0] = startColor;
//        colors[1] = middleColor;
        colors[1] = endColor;

        paint = new Paint();
        paint.setAntiAlias(true);

        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        controlWidth = w / 2;
        controlHeight = (int) (height * percent);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LinearGradient gradient = new LinearGradient(
                0, 0,
                width, 0,
                colors, null,
                Shader.TileMode.CLAMP
        );
        paint.setShader(gradient);

        path = new Path();
        path.moveTo(0, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height - controlHeight);
        path.quadTo(controlWidth, height, 0, height - controlHeight);
        path.close();
        canvas.drawPath(path, paint);
    }
}
