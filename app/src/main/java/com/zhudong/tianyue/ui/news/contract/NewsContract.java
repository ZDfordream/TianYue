package com.zhudong.tianyue.ui.news.contract;

import com.zhudong.tianyue.bean.Channel;
import com.zhudong.tianyue.ui.base.BaseContract;

import java.util.List;

/**
 * Created by dzhu on 18-1-16.
 */

public interface NewsContract {

    interface View extends BaseContract.BaseView{
        /**
         * 加载数据
         * @param channels
         * @param otherChannels
         */
        void loadData(List<Channel> channels, List<Channel> otherChannels);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        /**
         * 初始化频道
         */
        void getChannel();
    }
}
