package com.stonymoon.bboard.songdashboard;

import android.util.Log;

import com.stonymoon.bboard.api.BaseDataManager;
import com.stonymoon.bboard.api.services.RankService;
import com.stonymoon.bboard.bean.SongBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SongDashboardPresenter implements SongDashboardContract.Presenter {
    private SongDashboardContract.View mView;

    SongDashboardPresenter(SongDashboardContract.View view) {
        mView = view;
    }
    @Override
    public void start() {
        RankService rankService = BaseDataManager.getHttpManager().create(RankService.class);
        mView.showProgressBar(true);
        rankService.getRank("111437", "uk_singles_top_75", "automatic_first")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SongBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                        mView.showProgressBar(false);
                    }

                    @Override
                    public void onNext(SongBean songBean) {
                        mView.showProgressBar(false);
                        mView.showChart(songBean.getData().getData());
                        Log.e("SDP", songBean.toString());
                    }
                });

    }
}