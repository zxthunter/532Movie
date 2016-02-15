package com.hunter.movie532_02.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hunter.movie532_02.R;
import com.hunter.movie532_02.adapter.EpisodeAdapter;
import com.hunter.movie532_02.bean.DetailVideo;
import com.hunter.movie532_02.constants.UrlConstant;
import com.hunter.movie532_02.constants.EpisodeListConstant;
import com.hunter.movie532_02.listener.OnEpisodeLoadMoreClickListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by zxt on 2016/1/13.
 */
public class ShowActivity extends Activity{
    private RecyclerView recyclerView = null;
    private ArrayList<String> episodeList = new ArrayList<String>();
    private EpisodeAdapter adapter;
    private AsyncHttpClient client = new AsyncHttpClient();
    private DetailVideo detailVideo= null;
    private View EpisodeMoreButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,EpisodeListConstant.EPISODE_NUMBER_PER_ROW));
        adapter = new EpisodeAdapter(episodeList,recyclerView);
        recyclerView.setAdapter(adapter);
        int id = getIntent().getIntExtra("id",-1);
        if (id == -1) return;
        client.post(UrlConstant.apiBaseUrl+"detail/id/"+id,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                ((RelativeLayout)findViewById(R.id.main)).setVisibility(View.VISIBLE);
                String s = new String(bytes);
                final Type type = new TypeToken<DetailVideo>() {
                }.getType();
                detailVideo = new Gson().fromJson(s, type);
                initPage();
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }
    public void initPage(){
        //System.out.println(detailVideo.getUrlString());
        Uri uri = Uri.parse(detailVideo.getUrlString());
        ((SimpleDraweeView)findViewById(R.id.image)).setImageURI(uri);
        ((TextView)findViewById(R.id.title)).setText(detailVideo.getTitle());
        String textString = "主演：";
        for (int j = 0; j < detailVideo.getActor().size(); j++) {
            textString = textString + detailVideo.getActor().get(j) + " ";
        }
        ((TextView)findViewById(R.id.actor)).setText(textString);

        textString = "导演：";
        for (int j = 0; j < detailVideo.getDirector().size(); j++) {
            textString = textString + detailVideo.getDirector().get(j) + " ";
        }
        ((TextView)findViewById(R.id.director)).setText(textString);

        textString = "编剧：";
        for (int j = 0; j < detailVideo.getWriter().size(); j++) {
            textString = textString + detailVideo.getWriter().get(j) + " ";
        }
        ((TextView)findViewById(R.id.writer)).setText(textString);

        ((TextView)findViewById(R.id.area)).setText("地区："+detailVideo.getArea());

        ((TextView)findViewById(R.id.language)).setText("语言："+detailVideo.getLanguage());

        ((TextView)findViewById(R.id.duration)).setText("片长："+detailVideo.getDuration());

        ((TextView)findViewById(R.id.pubdate)).setText("上映："+detailVideo.getPubdate());

        ((TextView)findViewById(R.id.profile)).setText(""+detailVideo.getContent());

        ((RatingBar)findViewById(R.id.ratingBar)).setRating(detailVideo.getScore()/2);
        ((TextView)findViewById(R.id.score)).setText(""+detailVideo.getScore());

        initEpisodeList();

    }

    public void initEpisodeList(){
        adapter.addAll(detailVideo.getHlsurl());
        if (detailVideo.getHlsurl().size() > EpisodeListConstant.EPISODE_NUMBER_FIRST_LOAD){
            EpisodeMoreButton = LayoutInflater.from(this).inflate(R.layout.episode_loadmore,null);
            ((LinearLayout)findViewById(R.id.episode_content)).addView(EpisodeMoreButton);
            EpisodeMoreButton.setOnClickListener(new OnEpisodeLoadMoreClickListener(recyclerView));

        }
    }

}
