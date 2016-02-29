package com.hunter.movie532_02.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hunter.movie532_02.R;
import com.hunter.movie532_02.bean.ShowMovie;
import com.hunter.movie532_02.constants.HeaderConstant;
import com.hunter.movie532_02.listener.OnMovieClickListener;

import java.util.ArrayList;

/**
 * Created by zxt on 2016/1/31.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    private Context context ;
    private ArrayList<ShowMovie> movieList ;
    public HomeAdapter(ArrayList<ShowMovie> list) {
        movieList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if  (viewType == ITEM_VIEW_TYPE_HEADER){
            if (context == null) context = parent.getContext();
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_title,parent,false);
            ViewHolder holder = new ViewHolder(view,viewType);
            return holder;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showmovie,parent,false);
        ViewHolder holder = new ViewHolder(view,viewType);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return position % (HeaderConstant.NUMBER_OF_MOVIE_PER_COLUMN +1) == 0 ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.getViewType() == ITEM_VIEW_TYPE_ITEM){
            holder.titleTextView.setText(movieList.get(position).getTitle());
            holder.scoreTextView.setText(movieList.get(position).getScore()+"分");
            holder.hitsTextView.setText(movieList.get(position).getHits()+"℃");
            Uri uri = Uri.parse(movieList.get(position).getUrlString());
            holder.draweeView.setImageURI(uri);
            holder.setOnclickListener(new OnMovieClickListener(movieList.get(position).getId(),context));
        } else if (holder.getViewType() == ITEM_VIEW_TYPE_HEADER){
            holder.headerTextView.setText(movieList.get(position).getHeaderName());
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void addHeader(String headerName){
        ShowMovie header = new ShowMovie();
        header.setHeaderName(headerName);
        movieList.add(header);
        notifyItemInserted(movieList.size()-1);
    }
    public void clearAll(){
        movieList.clear();
        notifyDataSetChanged();
    }
    public void addAll(ArrayList<ShowMovie> list){
        for (ShowMovie showMovie : list){
            movieList.add(showMovie);
            notifyItemInserted(movieList.size()-1);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private int viewType;

        private TextView headerTextView;
        private TextView titleTextView;
        private TextView scoreTextView;
        private TextView hitsTextView;
        private SimpleDraweeView draweeView;
        ViewHolder(View itemView,int viewType) {
            super(itemView);
            this.viewType = viewType;
            if (viewType == ITEM_VIEW_TYPE_ITEM){
                titleTextView = (TextView)itemView.findViewById(R.id.title);
                scoreTextView = (TextView) itemView.findViewById(R.id.score);
                hitsTextView = (TextView)itemView.findViewById(R.id.hits);
                draweeView = (SimpleDraweeView) itemView.findViewById(R.id.image);
            } else if (viewType == ITEM_VIEW_TYPE_HEADER){
               headerTextView = (TextView)itemView.findViewById(R.id.headerText);
            }
        }
        public int getViewType() {
            return viewType;
        }

        public void setOnclickListener (View.OnClickListener onclickListener){
            itemView.setOnClickListener(onclickListener);
        }
    }


}
