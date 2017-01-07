package com.brioal.adtextviewlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.brioal.adtextviewlib.R;
import com.brioal.adtextviewlib.entity.AdEntity;
import com.brioal.adtextviewlib.util.SizeUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 仿京东垂直滚动广告栏
 * Created by Brioal on 2016/7/22.
 */

public class ADTextView extends android.support.v7.widget.AppCompatTextView {
    private int mSpeed; //文字出现或消失的速度 建议1~5
    private int mInterval; //文字停留在中间的时长
    private int mFrontColor; //前缀颜色
    private int mContentColor; //内容的颜色
    private int mFrontTextSize; //前缀文字大小
    private int mContentTextSize; //内容文字大小
    private List<AdEntity> mTexts; //显示文字的数据源
    private int mY = 0; //文字的Y坐标
    private int mIndex = 0; //当前的数据下标
    private Paint mPaintContent; //绘制内容的画笔
    private Paint mPaintFront; //绘制前缀的画笔
    private boolean isMove = true; //文字是否移动
    private String TAG = "ADTextView";
    private boolean hasInit = false;
    private boolean isPaused = false;

    public interface OnItemClickListener {
        void onClick(String mUrl);
    }

    private OnItemClickListener OnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }

    public ADTextView(Context context) {
        this(context, null);
    }

    public ADTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttrs(attrs);
        init();
    }

    //获取资源文件
    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ADTextView);
        mSpeed = array.getInt(R.styleable.ADTextView_ad_text_view_speed, 1);
        mInterval = array.getInt(R.styleable.ADTextView_ad_text_view_interval, 2000);
        mFrontColor = array.getColor(R.styleable.ADTextView_ad_text_front_color, Color.RED);
        mContentColor = array.getColor(R.styleable.ADTextView_ad_text_content_color, Color.BLACK);
        mFrontTextSize = (int) array.getDimension(R.styleable.ADTextView_ad_text_front_size, SizeUtil.Sp2Px(getContext(), 15));
        mContentTextSize = (int) array.getDimension(R.styleable.ADTextView_ad_text_content_size, SizeUtil.Sp2Px(getContext(), 15));
        array.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (OnItemClickListener != null) {
                    OnItemClickListener.onClick(mTexts.get(mIndex).getmUrl());
                }

                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    //测量宽度
    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else { //高度至少为两倍字高
            int mfronTextHeight = (int) (mPaintFront.descent() - mPaintFront.ascent()); //前缀文字字高
            int mContentTextHeight = (int) (mPaintContent.descent() - mPaintContent.ascent()); //内容文字字高
            result = Math.max(mfronTextHeight, mContentTextHeight) * 2;

            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    //测量高度
    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else { //宽度最小十个字的宽度
            String text = "十个字十个字十个字字";
            Rect rect = new Rect();
            mPaintContent.getTextBounds(text, 0, text.length(), rect);
            result = rect.right - rect.left;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    //设置数据源
    public void setTexts(List<AdEntity> mTexts) {
        this.mTexts = mTexts;
    }

    //设置广告文字的停顿时间
    public void setInterval(int mInterval) {
        this.mInterval = mInterval;
    }

    //设置速度
    public void setSpeed(int speed) {
        this.mSpeed = speed;
    }

    //设置前缀的文字颜色
    public void setFrontColor(int mFrontColor) {
        mPaintFront.setColor(mFrontColor);
    }

    //设置正文内容的颜色
    public void setBackColor(int mBackColor) {
        mPaintContent.setColor(mBackColor);
    }

    //设置前缀文字大小
    public void setFrontTextSize(int frontTextSize) {
        mFrontTextSize = frontTextSize;
    }

    //设置内容文字大小
    public void setContentTextSize(int contentTextSize) {
        mContentTextSize = contentTextSize;
    }

    //初始化默认值
    private void init() {
        mIndex = 0;
        mPaintFront = new Paint();
        mPaintFront.setAntiAlias(true);
        mPaintFront.setDither(true);
        mPaintFront.setTextSize(mFrontTextSize);
        mPaintFront.setColor(mFrontColor);

        mPaintContent = new Paint();
        mPaintContent.setAntiAlias(true);
        mPaintContent.setDither(true);
        mPaintContent.setTextSize(mContentTextSize);
        mPaintContent.setColor(mContentColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTexts != null) {
            AdEntity model = mTexts.get(mIndex);
            String font = model.getmFront();
            String back = model.getmBack();
            //绘制前缀
            Rect indexBound = new Rect();
            mPaintFront.getTextBounds(font, 0, font.length(), indexBound);

            //绘制内容文字
            Rect contentBound = new Rect();
            mPaintContent.getTextBounds(back, 0, back.length(), contentBound);
            if (mY == 0 && hasInit == false) {
                mY = getMeasuredHeight() - indexBound.top;
                hasInit = true;
            }
            //移动到最上面
            if (mY <= 0 - indexBound.bottom) {
                mY = getMeasuredHeight() - indexBound.top;
                mIndex++;
                isPaused = false;
            }
            canvas.drawText(back, 0, back.length(), (indexBound.right - indexBound.left) + 20 + getTotalPaddingLeft(), mY, mPaintContent);
            canvas.drawText(font, 0, font.length(), 10 + getTotalPaddingLeft(), mY, mPaintFront);
            //移动到中间
            if (!isPaused && mY <= getMeasuredHeight() / 2 - (indexBound.top + indexBound.bottom) / 2) {
                isMove = false;
                isPaused = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        postInvalidate();
                        isMove = true;
                    }
                }, mInterval);
            }
            mY -= mSpeed;
            //循环使用数据
            if (mIndex == mTexts.size()) {
                mIndex = 0;
            }
            //如果是处于移动状态时的,则延迟绘制
            //计算公式为一个比例,一个时间间隔移动组件高度,则多少毫秒来移动1像素
            if (isMove) {
                postInvalidateDelayed(2);
            }
        }

    }
}
