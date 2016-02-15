package com.hunter.movie532_02.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hunter.movie532_02.R;
import com.hunter.movie532_02.constants.UrlConstant;
import com.hunter.movie532_02.constants.EpisodeListConstant;
import com.hunter.movie532_02.listener.OnEpisodeClickListener;
import com.hunter.movie532_02.util.DisplayUtil;

import java.util.ArrayList;

/**
 * Created by zxt on 2016/2/3.
 */
public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {
    private RecyclerView recyclerView = null ;
    private ArrayList<String> list;
    private ArrayList<String> realList;
    public EpisodeAdapter(ArrayList<String> list ,RecyclerView recyclerView) {
        this.list = list;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_button,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.button.setText("第"+(position+1)+"集");
        holder.setOnClickListener(new OnEpisodeClickListener(UrlConstant.playBaseUrl+list.get(position),recyclerView.getContext()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void loadMore(){
        ViewGroup.LayoutParams mParams = recyclerView.getLayoutParams();
        int size = realList.size();
        mParams.height = (size % EpisodeListConstant.EPISODE_NUMBER_PER_ROW == 0? size / EpisodeListConstant.EPISODE_NUMBER_PER_ROW :(size / EpisodeListConstant.EPISODE_NUMBER_PER_ROW +1))
                *(DisplayUtil.dip2px(recyclerView.getContext(), 50f));
        recyclerView.setLayoutParams(mParams);
        list.addAll(realList.subList(EpisodeListConstant.EPISODE_NUMBER_FIRST_LOAD,realList.size()));
        notifyDataSetChanged();
    }
    public void pullUp(){
        ViewGroup.LayoutParams mParams = recyclerView.getLayoutParams();
        int size = EpisodeListConstant.EPISODE_NUMBER_FIRST_LOAD;
        mParams.height = (size % EpisodeListConstant.EPISODE_NUMBER_PER_ROW == 0? size / EpisodeListConstant.EPISODE_NUMBER_PER_ROW :(size / EpisodeListConstant.EPISODE_NUMBER_PER_ROW +1))
                *(DisplayUtil.dip2px(recyclerView.getContext(), 50f));
        recyclerView.setLayoutParams(mParams);
        list.clear();
        list.addAll(realList.subList(0, EpisodeListConstant.EPISODE_NUMBER_FIRST_LOAD));
        notifyDataSetChanged();
    }
    public void addAll(ArrayList<String> tempList){
        ViewGroup.LayoutParams mParams = recyclerView.getLayoutParams();
        int size = tempList.size();
        if (size > EpisodeListConstant.EPISODE_NUMBER_FIRST_LOAD){
            size = EpisodeListConstant.EPISODE_NUMBER_FIRST_LOAD;
        }
        mParams.height = (size % EpisodeListConstant.EPISODE_NUMBER_PER_ROW == 0? size / EpisodeListConstant.EPISODE_NUMBER_PER_ROW :(size / EpisodeListConstant.EPISODE_NUMBER_PER_ROW +1))
                *(DisplayUtil.dip2px(recyclerView.getContext(), 50f));
        recyclerView.setLayoutParams(mParams);

        realList = tempList;
        if (realList.size()>= EpisodeListConstant.EPISODE_NUMBER_FIRST_LOAD){
            list.addAll(realList.subList(0,EpisodeListConstant.EPISODE_NUMBER_FIRST_LOAD));
        } else {
            list.addAll(realList);
        }
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private Button button;

        ViewHolder(View itemView) {
            super(itemView);
            button = (Button)itemView.findViewById(R.id.episode_button);
        }
        void setOnClickListener (View.OnClickListener onClickListener){
            button.setOnClickListener(onClickListener);
        }
    }
}
