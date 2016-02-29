package com.hunter.movie532_02.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hunter.movie532_02.DB.UrlJson;
import com.hunter.movie532_02.R;
import com.hunter.movie532_02.adapter.HomeAdapter;
import com.hunter.movie532_02.bean.ShowMovie;
import com.hunter.movie532_02.constants.UrlConstant;
import com.hunter.movie532_02.constants.HeaderConstant;
import com.hunter.movie532_02.constants.NoInternetConstant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zxt on 2016/1/31.
 */
public class HomeFragment extends Fragment {
    private static AsyncHttpClient client = new AsyncHttpClient();
    private Map<String,ArrayList<ShowMovie>> movieMap;
    private HomeAdapter homeAdapter ;
    private RecyclerView recyclerView ;
    private ArrayList<ShowMovie> movieList = new ArrayList<ShowMovie>();
    private View rootView;
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_home,null);
            ((MaterialRefreshLayout)rootView.findViewById(R.id.refresh)).setMaterialRefreshListener(new mRefreshListener());
            recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),3);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position % (HeaderConstant.NUMBER_OF_MOVIE_PER_COLUMN +1) == 0 ? 3 : 1;
                }
            });
            recyclerView.setLayoutManager(gridLayoutManager);

            homeAdapter = new HomeAdapter(movieList);
            recyclerView.setAdapter(homeAdapter);
            final String urlString = UrlConstant.apiBaseUrl + "index/count/"+HeaderConstant.NUMBER_OF_MOVIE_PER_COLUMN;
            client.post(urlString, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int index, Header[] headers, byte[] bytes) {
                    String s = new String(bytes);
                    UrlJson DbBean = new UrlJson(urlString,s);
                    DbBean.save();
                    initFragment(s);
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Toast.makeText(getActivity(), NoInternetConstant.WARNING_TEXT, Toast.LENGTH_SHORT).show();
                    UrlJson urlJson = new Select().from(UrlJson.class).where("urlString = ?", urlString).orderBy("RANDOM()").executeSingle();
                    if (urlJson!=null){
                        initFragment(urlJson.jsonString);
                    }
                }
            });
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void initFragment(String s){
        final Type type = new TypeToken<Map<String, ArrayList<ShowMovie>>>() {
        }.getType();
        movieMap = new Gson().fromJson(s, type);
        //TODO:Pass the test of android market
        if (movieMap.get("popular").size() < HeaderConstant.NUMBER_OF_MOVIE_PER_COLUMN)
            HeaderConstant.NUMBER_OF_MOVIE_PER_COLUMN = movieMap.get("popular").size();

        for (String key : movieMap.keySet()){
            homeAdapter.addHeader(HeaderConstant.parseChannelName(key));
            homeAdapter.addAll(movieMap.get(key));
        }
    }
    private class mRefreshListener extends MaterialRefreshListener {

        @Override
        public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
            final String urlString = UrlConstant.apiBaseUrl + "index/count/"+HeaderConstant.NUMBER_OF_MOVIE_PER_COLUMN;
            client.post(urlString, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int index, Header[] headers, byte[] bytes) {
                    String s = new String(bytes);
                    UrlJson DbBean = new UrlJson(urlString,s);
                    DbBean.save();
                    materialRefreshLayout.finishRefresh();
                    homeAdapter.clearAll();
                    initFragment(s);
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Toast.makeText(getActivity(), NoInternetConstant.WARNING_TEXT, Toast.LENGTH_SHORT).show();
                    materialRefreshLayout.finishRefresh();
                }
            });
        }
    }

}

