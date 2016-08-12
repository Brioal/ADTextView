package com.brioal.adtestview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.brioal.adtextviewlib.entity.AdEntity;
import com.brioal.adtextviewlib.view.ADTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AdTextViewActivity extends AppCompatActivity {


    @Bind(R.id.ad_textview)
    ADTextView mADTextView;
    @Bind(R.id.act_adtext_speed)
    Spinner mSpSpeed;
    @Bind(R.id.act_adtext_interval)
    Spinner mSpInterval;

    private List<AdEntity> mList = new ArrayList<>();
    Toast mToast;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        setContentView(R.layout.act_ad_text_view);
        ButterKnife.bind(this);
        mList.add(new AdEntity("前缀1", "内容1", "连接1"));
        mList.add(new AdEntity("前缀2", "内容2", "连接2"));
        mList.add(new AdEntity("前缀3", "内容3", "连接3"));
        mList.add(new AdEntity("前缀4", "内容4", "连接4"));
        mADTextView.setFrontColor(Color.RED);
        mADTextView.setBackColor(Color.BLACK);
        mADTextView.setmTexts(mList);
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

        mSpSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int speed = 1;
                switch (position) {
                    case 0:
                        speed = 1;
                        break;
                    case 1:
                        speed = 2;
                        break;
                    case 2:
                        speed = 3;
                        break;
                    case 3:
                        speed = 4;
                        break;
                    case 4:
                        speed = 5;
                        break;
                }
                mADTextView.setSpeed(speed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpInterval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int interval = 1;
                switch (position) {
                    case 0:
                        interval = 500;
                        break;
                    case 1:
                        interval = 1000;
                        break;
                    case 2:
                        interval = 1500;
                        break;
                    case 3:
                        interval = 2000;
                        break;
                    case 4:
                        interval = 2500;
                        break;
                }
                mADTextView.setInterval(interval);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
