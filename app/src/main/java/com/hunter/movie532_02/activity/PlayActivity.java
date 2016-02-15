package com.hunter.movie532_02.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hunter.movie532_02.R;

/**
 * Created by zxt on 2016/1/14.
 */
public class PlayActivity extends Activity {
    private VideoView myVideoView;
    private int position = 0;
    private MediaController mediaControls;
    private String url;
    private ImageView showPlayerLoading;
    Handler handler = new Handler();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        // Get the layout from video_main.xml
        setContentView(R.layout.activity_play);

        if (mediaControls == null) {
            mediaControls = new MediaController(PlayActivity.this);
        }

        // Find your VideoView in your video_main.xml layout
        myVideoView = (VideoView) findViewById(R.id.videoView);

        showPlayerLoading = (ImageView)findViewById(R.id.showPlayerLoading);
       Runnable runnable = new Runnable() {
            public void run() {
                if(!myVideoView.isPlaying() && myVideoView.getCurrentPosition()==0){
                    showPlayerLoading.setVisibility(View.VISIBLE);
                    if (showPlayerLoading.getAnimation()==null)
                        showPlayerLoading.startAnimation(AnimationUtils.loadAnimation(PlayActivity.this,R.anim.loading_rotate_animation));
                } else{
                    showPlayerLoading.clearAnimation();
                    showPlayerLoading.setVisibility(View.GONE);}
                handler.postDelayed(this, 500);
            }};
        new Thread(runnable).start();

        try {
            myVideoView.setMediaController(mediaControls);
            myVideoView.setVideoURI(Uri.parse(url));
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();

        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    myVideoView.pause();
                    System.out.println("开始");
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }
}
