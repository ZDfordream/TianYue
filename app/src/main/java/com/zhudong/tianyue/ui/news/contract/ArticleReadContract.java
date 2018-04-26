package com.zhudong.tianyue.ui.news.contract;


import com.zhudong.tianyue.bean.NewsArticleBean;
import com.zhudong.tianyue.ui.base.BaseContract;

/**
 * Created by dzhu on 18-1-16.
 */
public interface ArticleReadContract {

    interface View extends BaseContract.BaseView{

        void loadData(NewsArticleBean articleBean);

    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        void getData(String aid);

    }

}