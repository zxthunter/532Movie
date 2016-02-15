package com.hunter.movie532_02.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hunter.movie532_02.activity.PlayActivity;

/**
 * Created by zxt on 2016/1/13.
 */
public class OnEpisodeClickListener implements View.OnClickListener{
    private String movieUrl;
    private Context context;
    public OnEpisodeClickListener(String movieUrl, Context context){
        this.movieUrl = movieUrl;
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,PlayActivity.class);
        intent.putExtra("url",movieUrl);
        context.startActivity(intent);
    }
}
