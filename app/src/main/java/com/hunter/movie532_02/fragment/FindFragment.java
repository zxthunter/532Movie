package com.hunter.movie532_02.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hunter.movie532_02.DB.UrlJson;
import com.hunter.movie532_02.R;
import com.hunter.movie532_02.adapter.FindAdapter;
import com.hunter.movie532_02.adapter.FindViewPagerAdapter;
import com.hunter.movie532_02.bean.Channel;
import com.hunter.movie532_02.bean.InfoBean;
import com.hunter.movie532_02.bean.ShowMovie;
import com.hunter.movie532_02.constants.UrlConstant;
import com.hunter.movie532_02.constants.NoInternetConstant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.lang.reflect.Type;
import java.util.ArrayList;



/**
 * Created by zxt on 2016/1/31.
 */
public class FindFragment extends Fragment{
    private LayoutInflater layoutInflater = null;
    private FindViewPagerAdapter pagerAdapter = null;
    private View rootView;
    private TabHost tabHost = null;
    private TabWidget tabWidget = null;
    private ViewPager viewPager =null;
    private ArrayList<Channel> channelList;
    private ArrayList<FindAdapter> adapterArrayList = new ArrayList<FindAdapter>();
    private ArrayList<Boolean> isInitList = new ArrayList<Boolean>();
    private ArrayList<View> viewContainer = new ArrayList<View>();
    private ArrayList<Long> earlistTimeList = new ArrayList<Long>();
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            layoutInflater = inflater;
            rootView = inflater.inflate(R.layout.fragment_find, null);
            viewPager = (ViewPager)rootView.findViewById(R.id.viewPager);
            pagerAdapter = new FindViewPagerAdapter(viewContainer);
            viewPager.setAdapter(pagerAdapter);
            tabHost = (TabHost)rootView.findViewById(android.R.id.tabhost);
            tabHost.setup();
            tabWidget = tabHost.getTabWidget();
            tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String tabId) {
                    loadCurrentPage();
                }
            });
            getHttpChannel();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    public void getHttpChannel() {
        final String urlString = UrlConstant.apiBaseUrl + "channel";
        UrlJson urlJson = new Select().from(UrlJson.class).where("urlString = ?", urlString).orderBy("RANDOM()").executeSingle();
        if (urlJson!=null){
            initTabHost(urlJson.jsonString);
            return;
        }
        client.post(urlString, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                String s = new String(bytes);
                UrlJson DbBean = new UrlJson(urlString,s);
                DbBean.save();
                initTabHost(s);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getActivity(), NoInternetConstant.WARNING_TEXT,Toast.LENGTH_SHORT).show();
                UrlJson urlJson = new Select().from(UrlJson.class).where("urlString = ?", urlString).orderBy("RANDOM()").executeSingle();
                if (urlJson!=null){
                    initTabHost(urlJson.jsonString);
                }
            }
        });
    }

    private void initTabHost(String s){
        final Type type = new TypeToken<ArrayList<Channel>>() {
        }.getType();
        channelList = new Gson().fromJson(s, type);
        for (int i = 0; i<channelList.size(); i++){
            isInitList.add(false);
            earlistTimeList.add(0L);
            Channel channel = channelList.get(i);
            RelativeLayout tab = (RelativeLayout)layoutInflater.inflate(R.layout.tab_find,null);
            ((TextView)tab.findViewById(R.id.text)).setText(channel.getCname());
            tabHost.addTab(tabHost.newTabSpec("tab" + (i)).setContent(R.id.content).setIndicator(tab));
            RelativeLayout content = (RelativeLayout)layoutInflater.inflate(R.layout.content_find,null);
            pagerAdapter.add(content);
            tabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
            ((MaterialRefreshLayout)content.findViewById(R.id.refresh)).setMaterialRefreshListener(new mRefreshListener());

            initRecyclerView(content);
        }
    }
    public void initRecyclerView(View content){
        RecyclerView recyclerView = (RecyclerView) content.findViewById(R.id.recycler_view);
        FindAdapter adapter = new FindAdapter(new ArrayList<ShowMovie>());
        adapterArrayList.add(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),3));
    }
    public void loadCurrentPage() {
        final int tabIndex = tabHost.getCurrentTab();
        viewPager.setCurrentItem(tabIndex,true);
        if (isInitList.get(tabIndex)) return;
        isInitList.set(tabIndex,true);
        int cid = channelList.get(tabIndex).getId();
        final String urlString = UrlConstant.apiBaseUrl+"videoinfos/cid/" + cid + "/addtime/0/count/18";
        client.post(urlString, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                String s = new String(bytes);
                UrlJson DbBean = new UrlJson(urlString,s);
                DbBean.save();
                initCurrentPage(s);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getActivity(), NoInternetConstant.WARNING_TEXT,Toast.LENGTH_SHORT).show();
                UrlJson urlJson = new Select().from(UrlJson.class).where("urlString = ?", urlString).orderBy("RANDOM()").executeSingle();
                if (urlJson!=null){
                    initCurrentPage(urlJson.jsonString);
                }
            }

            private void initCurrentPage(String s){
                final Type type = new TypeToken<InfoBean>() {
                }.getType();
                InfoBean infoBean = new Gson().fromJson(s, type);
                ArrayList<ShowMovie> videos = infoBean.getVideos();
                adapterArrayList.get(tabIndex).addAll(videos);
                earlistTimeList.set(tabIndex, videos.get(videos.size() - 1).getAddtime());
            }
        });
    }


    private class mRefreshListener extends MaterialRefreshListener{

        public mRefreshListener(){
        }

        @Override
        public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
            final int tabIndex = tabHost.getCurrentTab();
            int cid = channelList.get(tabIndex).getId();
            final String urlString = UrlConstant.apiBaseUrl+"videoinfos/cid/" + cid + "/addtime/0/count/18";
            client.post(urlString, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int index, Header[] headers, byte[] bytes) {
                    String s = new String(bytes);
                    UrlJson DbBean = new UrlJson(urlString,s);
                    DbBean.save();
                    materialRefreshLayout.finishRefresh();
                    refreshCurrentPage(s);
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Toast.makeText(getActivity(), NoInternetConstant.WARNING_TEXT, Toast.LENGTH_SHORT).show();
                    materialRefreshLayout.finishRefresh();
                }
                private void refreshCurrentPage(String s){
                    final Type type = new TypeToken<InfoBean>() {
                    }.getType();
                    InfoBean infoBean = new Gson().fromJson(s, type);
                    ArrayList<ShowMovie> videos = infoBean.getVideos();
                    adapterArrayList.get(tabIndex).clearAll();
                    adapterArrayList.get(tabIndex).addAll(videos);
                    earlistTimeList.set(tabIndex, videos.get(videos.size() - 1).getAddtime());
                }
            });

        }

        @Override
        public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);
            final int tabIndex = tabHost.getCurrentTab();
            Long startTime = earlistTimeList.get(tabIndex);
            int cid = channelList.get(tabIndex).getId();
            final FindAdapter adapter = adapterArrayList.get(tabIndex);
            final String urlString = UrlConstant.apiBaseUrl+"videoinfos/cid/" + cid + "/addtime/"+startTime+"/count/12";
            client.post(urlString, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int iii, Header[] headers, byte[] bytes) {
                    final String s = new String(bytes);
                    UrlJson DbBean = new UrlJson(urlString,s);
                    DbBean.save();
                    loadMorePage(s);
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    UrlJson urlJson = new Select().from(UrlJson.class).where("urlString = ?", urlString).orderBy("RANDOM()").executeSingle();
                    if (urlJson!=null){
                        loadMorePage(urlJson.jsonString);
                    } else {
                        materialRefreshLayout.finishRefreshLoadMore();
                        Toast.makeText(getActivity(), NoInternetConstant.WARNING_TEXT,Toast.LENGTH_SHORT).show();
                    }
                }
                private void loadMorePage(String s){
                    final Type type = new TypeToken<InfoBean>() {
                    }.getType();
                    if (s.equals("{}")) {
                        materialRefreshLayout.setLoadMore(false);
                        materialRefreshLayout.finishRefreshLoadMore();
                        return;
                    }
                    InfoBean infoBean = new Gson().fromJson(s, type);
                    ArrayList<ShowMovie> videos = infoBean.getVideos();
                    earlistTimeList.set(tabIndex, videos.get(videos.size() - 1).getAddtime());
                    adapter.addAll(videos);
                    materialRefreshLayout.finishRefreshLoadMore();
                }
            });

        }
    }
}
