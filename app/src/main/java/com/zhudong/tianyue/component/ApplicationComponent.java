package com.zhudong.tianyue.component;

import android.content.Context;

import com.zhudong.tianyue.MyApp;
import com.zhudong.tianyue.module.ApplicationModule;
import com.zhudong.tianyue.module.HttpModule;
import com.zhudong.tianyue.net.JanDanApi;
import com.zhudong.tianyue.net.NewsApi;

import dagger.Component;

/**
 * Created by dzhu on 18-1-15.
 */
@Component(modules = {ApplicationModule.class,HttpModule.class})
public interface ApplicationComponent {
    MyApp getApplication();

    Context getContext();

    NewsApi getNetEaseApi();

    JanDanApi getJanDanApi();
}
