package com.hunter.movie532_02.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hunter.movie532_02.R;
import com.hunter.movie532_02.bean.Video;
import com.hunter.movie532_02.listener.OnMovieClickListener;

import java.util.ArrayList;

/**
 * Created by zxt on 2016/2/1.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private ArrayList<Video> list = null;
    private Context context ;
    public SearchAdapter(ArrayList<Video> list) {
        this.list = list;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchmovie,parent,false);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }
    public void clear(){
        list.clear();
        notifyItemRangeChanged(0,list.size());
    }
    public void refreshAll(ArrayList<Video> movieList){
        list.clear();
        list.addAll(movieList);
        notifyItemRangeChanged(0,list.size());
    }
    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Video video = list.get(position);
        holder.titleTextView.setText(video.getTitle());

        String textString = "演员：";
        for (int j = 0; j < video.getActor().size(); j++) {
            textString = textString + video.getActor().get(j) + " ";
        }
        holder.actorTextView.setText(textString);

        textString = "导演：";
        for (int j = 0; j < video.getDirector().size(); j++) {
            textString = textString + video.getDirector().get(j) + " ";
        }
        holder.directorTextView.setText(textString);

        textString = "编剧：";
        for (int j = 0; j < video.getWriter().size(); j++) {
            textString = textString + video.getWriter().get(j) + " ";
        }
        holder.writerTextView.setText(textString);
        Uri uri = Uri.parse(list.get(position).getUrlString());
        holder.draweeView.setImageURI(uri);
        holder.setOnClickListener(new OnMovieClickListener(list.get(position).getId(), context));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SearchViewHolder extends  RecyclerView.ViewHolder{
        private SimpleDraweeView draweeView;
        private TextView titleTextView;
        private TextView actorTextView;
        private TextView directorTextView;
        private TextView writerTextView;
        SearchViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.image);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            actorTextView = (TextView) itemView.findViewById(R.id.actor);
            directorTextView = (TextView) itemView.findViewById(R.id.director);
            writerTextView = (TextView) itemView.findViewById(R.id.writer);
        }
        public void setOnClickListener (View.OnClickListener onClickListenr){
            itemView.setOnClickListener(onClickListenr);
        }
    }
}
