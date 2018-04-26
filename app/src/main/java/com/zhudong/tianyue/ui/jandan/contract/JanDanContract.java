package com.zhudong.tianyue.ui.jandan.contract;

import com.zhudong.tianyue.bean.FreshNewsBean;
import com.zhudong.tianyue.bean.JdDetailBean;
import com.zhudong.tianyue.ui.base.BaseContract;

/**
 * Created by zhudong on 2018/2/24.
 */

public interface JanDanContract {
    interface View extends BaseContract.BaseView {
        void loadFreshNews(FreshNewsBean freshNewsBean);

        void loadMoreFreshNews(FreshNewsBean freshNewsBean);

        void loadDetailData(String type, JdDetailBean jdDetailBean);

        void loadMoreDetailData(String type, JdDetailBean jdDetailBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getData(String type, int page);

        void getFreshNews(int page);

        void getDetailData(String type, int page);
    }
}
