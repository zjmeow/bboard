package com.stonymoon.bboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.stonymoon.bboard.itunes.ItunesActivity;
import com.stonymoon.bboard.rank.RankActivity;
import com.stonymoon.bboard.search.SearchActivity;
import com.stonymoon.bboard.singer.SingerActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @OnClick({R.id.btn_main_itunes, R.id.btn_main_rank, R.id.btn_main_song_dashboard, R.id.btn_main_search, R.id.btn_main_singer})
    void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_itunes:
                ItunesActivity.startActivity(this);
                break;
            case R.id.btn_main_rank:
                RankActivity.startActivity(this);
                break;
            case R.id.btn_main_song_dashboard:

                break;
            case R.id.btn_main_search:
                SearchActivity.startActivity(this);
                break;
            case R.id.btn_main_singer:
                SingerActivity.startActivity(this);
                break;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
