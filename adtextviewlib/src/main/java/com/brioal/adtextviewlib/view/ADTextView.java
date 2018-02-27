package com.brioal.adtextviewlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.brioal.adtextviewlib.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 仿京东垂直滚动广告栏
 * Created by Brioal on 2016/7/22.
 */

public class ADTextView extends TextSwitcher {
    private int mInterval=3000; //文字停留在中间的时长
    private int mSizeCount;//内容数量大小
    private Handler mHandler = new Handler();
    private int mAnimationIn = R.anim.anim_in_default;//进入的动画
    private int mAnimationOut = R.anim.anim_out_default;//出去的动画
    private OnAdChangeListener mChangeListener;//返回Listener
    private Context mContext;
    private int mCurrentIndex = 0;//当前的下表
    private TextView mDefaultTextView;//默认的文字
    private List<String> mTexts = new ArrayList<>();

    public ADTextView(Context context) {
        this(context,null);
    }

    public ADTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 设置进入动画
     * @param animationIn
     * @return
     */
    public ADTextView setAnimationIn(int animationIn) {
        mAnimationIn = animationIn;
        return this;
    }

    /**
     * 设置间隔时间
     * @param interval
     */
    public ADTextView setInterval(int interval) {
        mInterval = interval;
        return this;
    }


    /**
     * 设置退出动画
     * @return
     */
    public ADTextView setAnimationOut(int animationOut) {
        mAnimationOut = animationOut;
        return this;
    }

    public void init(final List<String> texts, OnAdChangeListener listener) {
        if (texts == null || texts.size() == 0) {
            return;
        }
        mSizeCount = texts.size();
        mTexts.clear();
        mTexts.addAll(texts);
        mChangeListener = listener;

        //设置进入动画
        if (mAnimationIn != -1) {
            setInAnimation(AnimationUtils.loadAnimation(mContext, mAnimationIn));
        }
        //设置出去动画
        if (mAnimationOut != -1) {
            setOutAnimation(AnimationUtils.loadAnimation(mContext, mAnimationOut));
        }
        //设置Factory
        setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                    mDefaultTextView = new TextView(mContext);
                    mDefaultTextView.setTextSize(14);
                    mDefaultTextView.setTextColor(Color.BLACK);
                    return mDefaultTextView;

            }
        });
        //开始滚动
        //设置文字
        setText(mTexts.get(mCurrentIndex));
        mChangeListener.DiyTextView((TextView) getCurrentView());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //递增
                mCurrentIndex++;
                if (mCurrentIndex >= mSizeCount) {
                    mCurrentIndex = 0;
                }
                //设置i文字
                setText(mTexts.get(mCurrentIndex));
                mChangeListener.DiyTextView((TextView) getCurrentView());
                //进行下一次
                mHandler.postDelayed(this, mInterval);
            }
        }, mInterval);
    }

}
