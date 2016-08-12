package com.brioal.adtestview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class LauncherActivity extends AppCompatActivity {

    @Bind(R.id.title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionbar);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //2秒后进入MainActivity
        Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Intent intent = new Intent(LauncherActivity.this, AdTextViewActivity.class);
                Explode explode = new Explode();
                explode.setDuration(1000);
                explode.setInterpolator(new AccelerateDecelerateInterpolator());
                getWindow().setExitTransition(explode);
                getWindow().setExitTransition(explode);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(LauncherActivity.this);
                startActivity(intent, options.toBundle());
                finish();
            }
        });
    }
}
