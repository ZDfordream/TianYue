package com.zhudong.tianyue.ui.video;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.zhudong.tianyue.R;
import com.zhudong.tianyue.bean.VideoChannelBean;
import com.zhudong.tianyue.bean.VideoDetailBean;
import com.zhudong.tianyue.component.ApplicationComponent;
import com.zhudong.tianyue.component.DaggerHttpComponent;
import com.zhudong.tianyue.ui.adapter.VideoPagerAdapter;
import com.zhudong.tianyue.ui.base.BaseFragment;
import com.zhudong.tianyue.ui.video.contract.VideoContract;
import com.zhudong.tianyue.ui.video.presenter.VideoPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhudong on 2018/3/5.
 */

public class VideoFragment extends BaseFragment<VideoPresenter> implements VideoContract.View {
    private static final String TAG = "VideoFragment";
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private VideoPagerAdapter mVideoPagerAdapter;

    public static VideoFragment newInstance() {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void loadVideoChannel(List<VideoChannelBean> channelBean) {
        Log.i(TAG, "loadVideoChannel: " + channelBean.toString());
        mVideoPagerAdapter = new VideoPagerAdapter(getChildFragmentManager(), channelBean.get(0));
        mViewpager.setAdapter(mVideoPagerAdapter);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setCurrentItem(0, false);
        mTablayout.setupWithViewPager(mViewpager, true);
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initData() {
        mPresenter.getVideoChannel();
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
    }

    @Override
    public void loadVideoDetails(List<VideoDetailBean> detailBean) {
    }

    @Override
    public void loadMoreVideoDetails(List<VideoDetailBean> detailBean) {
    }
}
