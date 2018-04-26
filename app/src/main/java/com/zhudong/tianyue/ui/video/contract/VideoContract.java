package com.zhudong.tianyue.ui.video.contract;

import com.zhudong.tianyue.bean.VideoChannelBean;
import com.zhudong.tianyue.bean.VideoDetailBean;
import com.zhudong.tianyue.ui.base.BaseContract;

import java.util.List;

/**
 * Created by zhudong on 2018/3/5.
 */

public interface VideoContract {
    interface View extends BaseContract.BaseView {
        void loadVideoChannel(List<VideoChannelBean> channelBean);

        void loadVideoDetails(List<VideoDetailBean> detailBean);

        void loadMoreVideoDetails(List<VideoDetailBean> detailBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        /**
         * 获取视频频道列表
         */
        void getVideoChannel();

        /**
         * 获取视频列表
         *
         * @param page     页码
         * @param listType 默认list
         * @param typeId   频道id
         */
        void getVideoDetails(int page, String listType, String typeId);

    }
}
