package com.zhudong.tianyue.net;

import android.support.annotation.StringDef;


import com.zhudong.tianyue.bean.NewsArticleBean;
import com.zhudong.tianyue.bean.NewsDetail;
import com.zhudong.tianyue.bean.VideoChannelBean;
import com.zhudong.tianyue.bean.VideoDetailBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Created by dzhu on 18-1-16.
 */
public class NewsApi {

    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";

    @StringDef({ACTION_DEFAULT,ACTION_DOWN,ACTION_UP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Actions{

    }

    public static NewsApi sInstance;

    private NewsApiService mService;

    public NewsApi(NewsApiService newsApiService) {
        this.mService = newsApiService;
    }

    public static NewsApi getInstance(NewsApiService newsApiService) {
        if (sInstance == null)
            sInstance = new NewsApi(newsApiService);
        return sInstance;
    }

    /**
     * 获取新闻详情
     *
     * @param id      频道ID值
     * @param action  用户操作方式
     *                1：下拉 down
     *                2：上拉 up
     *                3：默认 default
     * @param pullNum 操作次数 累加
     * @return
     */
    public Observable<NewsDetail> getNewsDetail(String id, @Actions String action, int pullNum) {
        return mService.getNewsDetail(id, action, pullNum)
                .flatMap(new Function<List<NewsDetail>, ObservableSource<NewsDetail>>() {
                    @Override
                    public ObservableSource<NewsDetail> apply(@NonNull List<NewsDetail> newsDetails) throws Exception {
                        return Observable.fromIterable(newsDetails);
                    }
                });
    }

    /**
     * 获取新闻文章详情
     * @param aid 文章aid  此处baseurl可能不同，需要特殊处理
     *          1：aid 以 cmpp 开头则调用 getNewsArticleWithCmpp
     * @return
     */
    public Observable<NewsArticleBean> getNewsArticle(String aid){
        if (aid.startsWith("sub")){
            return mService.getNewsArticleWithSub(aid);
        }else {
            return mService.getNewsArticleWithCmpp(ApiConstants.sGetNewsArticleCmppApi + ApiConstants.sGetNewsArticleDocCmppApi,aid);
        }
    }

    /**
     * 获取视频频道列表
     *
     * @return
     */
    public Observable<List<VideoChannelBean>> getVideoChannel(){
        return mService.getVideoChannel(1);
    }

    /**
     * 获取
     *
     * @param page
     * @param listtype
     * @param typeid
     * @return
     */
    public Observable<List<VideoDetailBean>> getVideoDetail(int page, String listtype, String typeid){
        return mService.getVideoDetail(page,listtype,typeid);
    }

}
