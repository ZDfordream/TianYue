package com.zhudong.tianyue.ui.personal;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zhudong.tianyue.R;
import com.zhudong.tianyue.component.ApplicationComponent;
import com.zhudong.tianyue.ui.base.BaseFragment;
import com.zhudong.tianyue.ui.news.AdvertActivity;
import com.zhudong.tianyue.utils.ImageLoaderUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhudong on 2018/3/5.
 */

public class PersonalFragment extends BaseFragment {

    @BindView(R.id.ivAuthor)
    ImageView mIvAuthor;
    @BindView(R.id.tvContacts)
    TextView mTvContacts;
    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.tvDescription)
    TextView mTvDescription;
    @BindView(R.id.tvGithub)
    TextView mTvGithub;
    @BindView(R.id.tvEmail)
    TextView mTvEmail;
    @BindView(R.id.tvUrl)
    TextView mTvUrl;
    @BindView(R.id.tvGithubUrl)
    TextView mTvGithubUrl;
    @BindView(R.id.tvEmailUrl)
    TextView mTvEmailUrl;

    public static PersonalFragment newInstance() {
        Bundle args = new Bundle();
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        ImageLoaderUtil.LoadImage(this, "http://ovuc1rhny.bkt.clouddn.com/15833.jpg", mIvAuthor,
                new RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL));
        Typeface mtypeface = Typeface.createFromAsset(getActivity().getAssets(), "font/consolab.ttf");
        mTvContacts.setTypeface(mtypeface);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/consola.ttf");
        mTvName.setTypeface(typeface);
        mTvDescription.setTypeface(typeface);
        mTvGithub.setTypeface(typeface);
        mTvEmail.setTypeface(typeface);
        mTvGithubUrl.setTypeface(typeface);
        mTvUrl.setTypeface(typeface);
        mTvEmailUrl.setTypeface(typeface);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tvUrl, R.id.tvGithubUrl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvUrl:
                break;
            case R.id.tvGithubUrl:
                AdvertActivity.launch(getContext(),getResources().getString(R.string.githubUrl));
              //  toWeb(getResources().getString(R.string.githubUrl));
                break;

        }
    }

    private void toWeb(String url){
        Uri weburl = Uri.parse(url);
        Intent web_Intent = new Intent(Intent.ACTION_VIEW, weburl);
        getActivity().startActivity(web_Intent);
    }
}
