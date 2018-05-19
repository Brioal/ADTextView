package com.brioal.adtestview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brioal.adtextviewlib.view.ADTextView;
import com.brioal.adtextviewlib.view.OnAdChangeListener;

import java.util.ArrayList;
import java.util.List;


public class AdTextViewActivity extends Activity {


    ADTextView mADTextView;

    Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        setContentView(R.layout.act_ad_text_view);
        mADTextView = findViewById(R.id.ad_textview);
        final List<String> texts = new ArrayList<>();
        texts.add("11 111111111111111");
        texts.add("22 2222222222222222");
        texts.add("33 3333333333333333");
        texts.add("44 44444444444444444444");
        mADTextView.setInterval(2000);
        mADTextView.init(texts, new OnAdChangeListener() {
            @Override
            public void DiyTextView(TextView textView, final int index) {
                textView.setTextSize(20);
                textView.setTextColor(Color.WHITE);
                SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText());
                builder.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(builder);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AdTextViewActivity.this, "点击了第" + (index + 1) + "个TextView", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }

}
