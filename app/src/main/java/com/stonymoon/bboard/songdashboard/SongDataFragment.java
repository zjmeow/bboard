package com.stonymoon.bboard.songdashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stonymoon.bboard.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Stony on 2018/10/18.
 */

public class SongDataFragment extends Fragment {
    private Context mContext;
    private Unbinder mUnbinder;

    public static SongDataFragment getInstance() {
        return new SongDataFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_song_data, container, false);
        mUnbinder = ButterKnife.bind(this, root);
        return root;
    }
}