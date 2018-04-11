package com.stonymoon.bboard.singer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stonymoon.bboard.R;
import com.stonymoon.bboard.bean.SingerBean;
import com.stonymoon.bboard.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;


public class SingerFragment extends Fragment implements SingerContract.View {
    @BindView(R.id.pb_singer)
    ProgressBar pbSinger;
    @BindView(R.id.tv_singer_area)
    TextView tvArea;
    @BindView(R.id.tv_singer_born)
    TextView tvBorn;
    @BindView(R.id.tv_singer_info)
    TextView tvInfo;
    @BindView(R.id.tv_singer_name)
    TextView tvName;
    @BindView(R.id.tv_singer_type)
    TextView tvType;
    @BindView(R.id.iv_singer_avatar)
    ImageView ivAvatar;


    private Unbinder mUnbinder;
    private SingerContract.Presenter mPresenter;
    private Context mContext;


    public SingerFragment() {

    }

    public static SingerFragment getInstance() {
        return new SingerFragment();
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void setPresenter(SingerContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showError() {
        ToastUtil.show(mContext, getString(R.string.error_message));
    }

    @Override
    public void showProgressBar(boolean show) {
        pbSinger.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showInfo(SingerBean bean) {
        SingerBean.ResourceBean singer = bean.getResource();
        tvArea.setText(singer.getArea());
        tvBorn.setText(singer.getBorn());
        tvInfo.setText(singer.getInfo());
        tvName.setText(singer.getName());
        tvType.setText(singer.getType());
        Glide.with(mContext)
                .load(singer.getImage())
                .into(ivAvatar);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_singer, container, false);
        mUnbinder = ButterKnife.bind(this, root);

        mPresenter.start();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}