package com.hunter.movie532_02.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hunter.movie532_02.activity.ShowActivity;

/**
 * Created by zxt on 2016/1/12.
 */
public class OnMovieClickListener implements View.OnClickListener {
    private int id;
    private Context context;
    public OnMovieClickListener(int id,Context context) {
        this.context = context;
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,ShowActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
}
