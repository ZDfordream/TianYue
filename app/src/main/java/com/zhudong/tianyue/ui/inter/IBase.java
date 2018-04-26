package com.zhudong.tianyue.ui.inter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhudong.tianyue.component.ApplicationComponent;

/**
 * Created by dzhu on 18-1-15.
 */

public interface IBase {

    /**
     * 创建布局，获取setContentView中的参数布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 获取布局
     * @return
     */
    View getView();

    /**
     * 获取布局资源ｉｄ
     * @return
     */
    int getContentLayout();

    /**
     * 注入
     * @param appComponent
     */
    void initInjector(ApplicationComponent appComponent);

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化布局
     * @param view
     * @param savedInstanceState
     */
    void bindView(View view,Bundle savedInstanceState);
}
