package com.zhudong.tianyue.net;


import com.zhudong.tianyue.bean.FreshNewsArticleBean;
import com.zhudong.tianyue.bean.FreshNewsBean;
import com.zhudong.tianyue.bean.JdDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by dzhu on 18-1-16.
 */
public interface JanDanApiService {

    @GET
    Observable<FreshNewsBean> getFreshNews(@Url String url, @Query("oxwlxojflwblxbsapi") String oxwlxojflwblxbsapi,
                                           @Query("include") String include,
                                           @Query("page") int page,
                                           @Query("custom_fields") String custom_fields,
                                           @Query("dev") String dev
    );


    @GET
    Observable<JdDetailBean> getDetailData(@Url String url, @Query("oxwlxojflwblxbsapi") String oxwlxojflwblxbsapi,
                                           @Query("page") int page
    );

    @GET
    Observable<FreshNewsArticleBean> getFreshNewsArticle(@Url String url, @Query("oxwlxojflwblxbsapi") String oxwlxojflwblxbsapi,
                                                         @Query("include") String include,
                                                         @Query("id") int id
    );

}
