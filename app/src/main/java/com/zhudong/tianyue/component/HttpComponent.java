package com.zhudong.tianyue.component;

import com.zhudong.tianyue.ui.jandan.JdDetailFragment;
import com.zhudong.tianyue.ui.news.ArticleReadActivity;
import com.zhudong.tianyue.ui.news.DetailFragment;
import com.zhudong.tianyue.ui.news.ImageBrowseActivity;
import com.zhudong.tianyue.ui.news.NewsFragment;
import com.zhudong.tianyue.ui.video.VideoFragment;

import dagger.Component;

/**
 * Created by dzhu on 18-1-16.
 */
@Component(dependencies = ApplicationComponent.class)
public interface HttpComponent {
    void inject(VideoFragment videoFragment);

    void inject(com.zhudong.tianyue.ui.video.DetailFragment detailFragment);

    void inject(NewsFragment newsFragment);

    void inject(com.zhudong.tianyue.ui.news.DetailFragment detailFragment);

    void inject(ArticleReadActivity articleReadActivity);

    void inject(ImageBrowseActivity articleReadActivity);

    void inject(JdDetailFragment newsFragment);
}
