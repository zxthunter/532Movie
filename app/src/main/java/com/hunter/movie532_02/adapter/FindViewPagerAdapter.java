package com.hunter.movie532_02.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.hunter.movie532_02.R;

import java.util.ArrayList;

/**
 * Created by zxt on 2016/2/1.
 */
public class FindViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewContainer;


    public void add(View view){
        viewContainer.add(view);
        notifyDataSetChanged();
    }

    public FindViewPagerAdapter(ArrayList<View> viewContainer) {
        this.viewContainer = viewContainer;
    }

    @Override
    public int getCount() {
        return viewContainer.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    //滑动切换的时候销毁当前的组件
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        ((ViewPager) container).removeView(viewContainer.get(position));
    }

    //每次滑动的时候生成的组件
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(viewContainer.get(position));
        return viewContainer.get(position);
    }

    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
