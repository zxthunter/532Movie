package com.hunter.movie532_02.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hunter.movie532_02.adapter.EpisodeAdapter;
import com.hunter.movie532_02.constants.EpisodeListConstant;
import com.hunter.movie532_02.util.DisplayUtil;

/**
 * Created by zxt on 2016/2/4.
 */
public class OnEpisodeLoadMoreClickListener implements View.OnClickListener {
    private RecyclerView recyclerView;
    private int tag;
    private static final int UP = 0;
    private static final int DOWN = 1;
    public OnEpisodeLoadMoreClickListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.tag = UP;
    }

    @Override
    public void onClick(View v) {
        switch (tag){
            case UP:
                ((TextView)v.findViewWithTag("text")).setText("收起");
                ((EpisodeAdapter)recyclerView.getAdapter()).loadMore();
                tag = DOWN;
                break;
            case DOWN:
                ((TextView)v.findViewWithTag("text")).setText("加载更多");
                ((EpisodeAdapter)recyclerView.getAdapter()).pullUp();
                tag = UP;
                break;
            default:
                return;
        }
    }
}
