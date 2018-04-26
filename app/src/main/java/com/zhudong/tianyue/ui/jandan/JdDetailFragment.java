package com.zhudong.tianyue.ui.jandan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhudong.tianyue.R;
import com.zhudong.tianyue.bean.FreshNewsBean;
import com.zhudong.tianyue.bean.JdDetailBean;
import com.zhudong.tianyue.component.ApplicationComponent;
import com.zhudong.tianyue.component.DaggerHttpComponent;
import com.zhudong.tianyue.net.JanDanApi;
import com.zhudong.tianyue.ui.base.BaseFragment;
import com.zhudong.tianyue.ui.jandan.contract.JanDanContract;
import com.zhudong.tianyue.ui.jandan.presenter.JanDanPresenter;
import com.zhudong.tianyue.widget.CustomLoadMoreView;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by zhudong on 2018/2/24.
 */
@SuppressLint("ValidFragment")
public class JdDetailFragment extends BaseFragment<JanDanPresenter> implements JanDanContract.View {
    public static final String TYPE = "type";

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mPtrFrameLayout)
    PtrFrameLayout mPtrFrameLayout;

    private BaseQuickAdapter mAdapter;
    private int pageNum = 1;
    private String type;

    public JdDetailFragment(BaseQuickAdapter adapter) {
        this.mAdapter = adapter;
    }

    public static JdDetailFragment newInstance(String type, BaseQuickAdapter baseQuickAdapter) {
        Bundle args = new Bundle();
        args.putCharSequence(TYPE, type);
        JdDetailFragment fragment = new JdDetailFragment(baseQuickAdapter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_jd_detail;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                mPresenter.getData(type, pageNum);

            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setPreLoadNumber(1);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getData(type, pageNum);
            }
        }, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (type.equals(JanDanApi.TYPE_FRESH)) {
                    ReadActivity.launch(getActivity(), (FreshNewsBean.PostsBean) adapter.getItem(position));
                }
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() == null)
            return;
        type = getArguments().getString(TYPE);
        mPresenter.getData(type, pageNum);
    }

    @Override
    public void onRetry() {
        initData();
    }

    @Override
    public void loadFreshNews(FreshNewsBean freshNewsBean) {
        if (freshNewsBean == null) {
            mPtrFrameLayout.refreshComplete();
            showFaild();
        } else {
            pageNum++;
            mAdapter.setNewData(freshNewsBean.getPosts());
            mPtrFrameLayout.refreshComplete();
            showSuccess();
        }
    }

    @Override
    public void loadDetailData(String type, JdDetailBean jdDetailBean) {
        if (jdDetailBean == null) {
            mPtrFrameLayout.refreshComplete();
            showFaild();
        } else {
            pageNum++;
            mAdapter.setNewData(jdDetailBean.getComments());
            mPtrFrameLayout.refreshComplete();
            showSuccess();
        }
    }

    @Override
    public void loadMoreFreshNews(FreshNewsBean freshNewsBean) {
        if (freshNewsBean == null) {
            mAdapter.loadMoreFail();
        } else {
            pageNum++;
            mAdapter.addData(freshNewsBean.getPosts());
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void loadMoreDetailData(String type, JdDetailBean jdDetailBean) {
        if (jdDetailBean == null) {
            mAdapter.loadMoreFail();
        } else {
            pageNum++;
            mAdapter.addData(jdDetailBean.getComments());
            mAdapter.loadMoreComplete();
        }
    }
}
