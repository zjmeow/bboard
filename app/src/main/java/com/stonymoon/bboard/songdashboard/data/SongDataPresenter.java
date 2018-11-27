package com.stonymoon.bboard.songdashboard.data;

import com.stonymoon.bboard.api.BaseDataManager;
import com.stonymoon.bboard.api.services.BillboardService;
import com.stonymoon.bboard.bean.SongBean;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Stony on 2018/10/20.
 */

public class SongDataPresenter implements SongDataContract.Presenter {
    private SongDataContract.View mView;
    private String mSongId;

    public SongDataPresenter(SongDataContract.View mView, String mSongId) {
        this.mView = mView;
        this.mSongId = mSongId;
    }

    public void start() {
        BillboardService rankService = BaseDataManager.getHttpManager().create(BillboardService.class);
        mView.showProgressBar(true);
        rankService.getRank(mSongId)
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
                        List<Integer> data = new ArrayList<>(100);
                        for (SongBean.DataBean.BillboardsBean billboard : songBean.getData().getBillboards()) {
                            data.add(billboard.getRank());
                        }
                        mView.initData(data);
                    }
                });
    }
}
