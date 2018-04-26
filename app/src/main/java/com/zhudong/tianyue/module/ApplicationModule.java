package com.zhudong.tianyue.module;

import android.content.Context;

import com.zhudong.tianyue.MyApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dzhu on 18-1-15.
 */
@Module
public class ApplicationModule {
    private Context mContext;

    public ApplicationModule(Context context){
        this.mContext = context;
    }

    @Provides
    MyApp provideApplication(){
        return (MyApp) mContext.getApplicationContext();
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
