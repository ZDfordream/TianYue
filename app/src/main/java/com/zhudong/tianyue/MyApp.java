package com.zhudong.tianyue;

import com.zhudong.tianyue.component.ApplicationComponent;

import com.zhudong.tianyue.component.DaggerApplicationComponent;
import com.zhudong.tianyue.module.ApplicationModule;
import com.zhudong.tianyue.module.HttpModule;
import com.zhudong.tianyue.utils.ContextUtils;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by dzhu on 18-1-15.
 */

public class MyApp extends LitePalApplication {
    private ApplicationComponent mApplicationComponent;
    private static MyApp sMyApp;
    public static int width = 0;
    public static int height =0;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
        BGASwipeBackManager.getInstance().init(this);
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .build();
        LitePal.initialize(this);
        width = ContextUtils.getSreenWidth(MyApp.getContext());
        height = ContextUtils.getSreenHeight(MyApp.getContext());
    }

    public static MyApp getInstance(){
        return sMyApp;
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }
}
