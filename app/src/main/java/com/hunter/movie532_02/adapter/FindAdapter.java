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
import com.hunter.movie532_02.listener.OnMovieClickListener;

import java.util.ArrayList;

/**
 * Created by zxt on 2016/2/1.
 */
public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder>{
    private ArrayList<ShowMovie> list;
    private Context context ;
    public FindAdapter(ArrayList<ShowMovie> list) {
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showmovie,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTextView.setText(list.get(position).getTitle());
        holder.scoreTextView.setText(list.get(position).getScore()+"分");
        holder.hitsTextView.setText(list.get(position).getHits()+"℃");
        Uri uri = Uri.parse(list.get(position).getUrlString());
        holder.draweeView.setImageURI(uri);
        holder.setOnClickListener(new OnMovieClickListener(list.get(position).getId(), context));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearAll(){
        list.clear();
        notifyDataSetChanged();
    }
    public void addAll(ArrayList<ShowMovie> addList){
        for (ShowMovie showMovie : addList){
            list.add(showMovie);
            notifyItemInserted(list.size()-1);
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView scoreTextView;
        private TextView hitsTextView;
        private SimpleDraweeView draweeView;
        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.title);
            scoreTextView = (TextView) itemView.findViewById(R.id.score);
            hitsTextView = (TextView)itemView.findViewById(R.id.hits);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.image);
        }

        public void setOnClickListener (View.OnClickListener onClickListenr){
            itemView.setOnClickListener(onClickListenr);
        }
    }
}
