package com.zhudong.tianyue.ui.news.presenter;

import android.util.Log;

import com.zhudong.tianyue.bean.NewsDetail;
import com.zhudong.tianyue.net.BaseObserver;
import com.zhudong.tianyue.net.NewsApi;
import com.zhudong.tianyue.net.NewsUtils;
import com.zhudong.tianyue.net.RxSchedulers;
import com.zhudong.tianyue.ui.base.BasePresenter;
import com.zhudong.tianyue.ui.news.contract.DetailContract;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by dzhu on 18-1-16.
 */

public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {
    private static final String TAG = "DetailPresenter";
    NewsApi mNewsApi;

    @Inject
    public DetailPresenter(NewsApi newsApi) {
        this.mNewsApi = newsApi;
    }

    @Override
    public void getData(String id, String action, int pullNum) {
        mNewsApi.getNewsDetail(id, action, pullNum)
                .compose(RxSchedulers.<NewsDetail>applySchedulers())
                .filter(newsDetail -> {
                    if (NewsUtils.isBannerNews(newsDetail)) {
                        mView.loadBannerData(newsDetail);
                    }
                    if (NewsUtils.isTopNews(newsDetail)) {
                        mView.loadTopNewsData(newsDetail);
                    }
                    return NewsUtils.isListNews(newsDetail);
                })
                .map(newsDetail -> {
                    Iterator<NewsDetail.ItemBean> iterator = newsDetail.getItem().iterator();
                    while (iterator.hasNext()) {
                        try {
                            NewsDetail.ItemBean bean = iterator.next();
                            if (bean.getType().equals(NewsUtils.TYPE_DOC)) {if (bean.getStyle().getView() != null) {
                                if (bean.getStyle().getView().equals(NewsUtils.VIEW_TITLEIMG)) {
                                    bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                } else {
                                    bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                }
                            }
                            } else if (bean.getType().equals(NewsUtils.TYPE_ADVERT)) {
                                if (bean.getStyle() != null) {
                                    if (bean.getStyle().getView().equals(NewsUtils.VIEW_TITLEIMG)) {
                                        bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                    } else if (bean.getStyle().getView().equals(NewsUtils.VIEW_SLIDEIMG)) {
                                        bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG;
                                    } else {
                                        bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG;
                                    }
                                } else {
                                    //bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                    iterator.remove();
                                }
                            } else if (bean.getType().equals(NewsUtils.TYPE_SLIDE)) {
                                if (bean.getLink().getType().equals("doc")){
                                    if (bean.getStyle().getView().equals(NewsUtils.VIEW_SLIDEIMG)) {
                                        bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                    } else {
                                        bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                    }
                                }else {
                                    bean.itemType = NewsDetail.ItemBean.TYPE_SLIDE;
                                }
                            } else if (bean.getType().equals(NewsUtils.TYPE_PHVIDEO)) {
                                bean.itemType = NewsDetail.ItemBean.TYPE_PHVIDEO;
                            } else {
                                // 凤凰新闻 类型比较多，目前只处理能处理的类型
                                iterator.remove();
                            }
                        } catch (Exception e) {
                            iterator.remove();
                            e.printStackTrace();
                        }
                    }
                    return newsDetail.getItem();
                })
                .compose(mView.<List<NewsDetail.ItemBean>>bindToLife())
                .subscribe(new BaseObserver<List<NewsDetail.ItemBean>>() {
                    @Override
                    public void onSucess(List<NewsDetail.ItemBean> itemBeen) {
                        if (!action.equals(NewsApi.ACTION_UP)) {
                            mView.loadData(itemBeen);
                        } else {
                            mView.loadMoreData(itemBeen);
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Log.i(TAG, "onFail: " + e.getMessage().toString());
                        if (!action.equals(NewsApi.ACTION_UP)) {
                            mView.loadData(null);
                        } else {
                            mView.loadMoreData(null);
                        }
                    }
                });
    }
}
