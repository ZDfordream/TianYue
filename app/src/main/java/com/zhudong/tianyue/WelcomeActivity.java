package com.zhudong.tianyue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhudong.tianyue.component.ApplicationComponent;
import com.zhudong.tianyue.ui.base.BaseActivity;
import com.zhudong.tianyue.utils.ImageLoaderUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;


public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.iv_ad)
    ImageView ivAd;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    @BindView(R.id.fl_ad)
    FrameLayout flAd;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public int getContentLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        ImageLoaderUtil.LoadImage(this, AppConstants.WALLPAPER_URL, ivAd);
        countDown(3).doOnSubscribe(disposable -> {
            mCompositeDisposable.add(disposable);
            tvSkip.setText("跳过 3");})
                .doFinally(() -> toMain())
                .subscribe(integer -> tvSkip.setText("跳过 " + integer));
    }


    @Override
    protected void onDestroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        super.onDestroy();
    }

    private void toMain() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public Observable<Integer> countDown(int time) {
        if (time < 0) time = 0;
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(aLong -> countTime - aLong.intValue())
                .take(countTime + 1);
    }


    @OnClick(R.id.fl_ad)
    public void onViewClicked() {
        toMain();
    }

    @Override
    public void initData() {
    }

    @Override
    public void onRetry() {
    }

}
