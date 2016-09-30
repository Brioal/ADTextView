package com.brioal.adtestview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.brioal.adtextviewlib.entity.AdEntity;
import com.brioal.adtextviewlib.view.ADTextView;

import java.util.ArrayList;
import java.util.List;


public class AdTextViewActivity extends Activity {


    ADTextView mADTextView;

    private List<AdEntity> mList = new ArrayList<>();
    Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        setContentView(R.layout.act_ad_text_view);
        mADTextView = (ADTextView) findViewById(R.id.ad_textview);
        mList.add(new AdEntity("前缀1", "内容1", "连接1"));
        mList.add(new AdEntity("前缀2", "内容2", "连接2"));
        mList.add(new AdEntity("前缀3", "内容3", "连接3"));
        mList.add(new AdEntity("前缀4", "内容4", "连接4"));
        mADTextView.setSpeed(3);
        mADTextView.setInterval(1500);
        mADTextView.setFrontColor(Color.RED);
        mADTextView.setBackColor(Color.BLACK);
        mADTextView.setTexts(mList);
        mADTextView.setOnItemClickListener(new ADTextView.OnItemClickListener() {
            @Override
            public void onClick(String mUrl) {
                if (mToast == null) {
                    mToast = Toast.makeText(AdTextViewActivity.this, mUrl, Toast.LENGTH_LONG);
                } else {
                    mToast.setText(mUrl);
                }
                mToast.show();
            }
        });

    }

}
