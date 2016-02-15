package com.hunter.movie532_02.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hunter.movie532_02.R;
import com.hunter.movie532_02.adapter.SearchAdapter;
import com.hunter.movie532_02.bean.SearchBean;
import com.hunter.movie532_02.bean.Video;
import com.hunter.movie532_02.constants.UrlConstant;
import com.hunter.movie532_02.constants.NoInternetConstant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by zxt on 2016/1/31.
 */
public class SearchFragment extends Fragment{
    private View rootView;
    private RecyclerView recyclerView = null;
    private SearchAdapter searchAdapter;
    private ArrayList<Video> movieList = new ArrayList<Video>();
    private EditText findOldEdit = null;
    private EditText findNewEdit = null;
    private View findNewView = null;
    private View findOldView = null;
    private static AsyncHttpClient client = new AsyncHttpClient();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_search,null);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            searchAdapter = new SearchAdapter(movieList);
            recyclerView.setAdapter(searchAdapter);

            ((Button)rootView.findViewById(R.id.findOldButton)).setOnClickListener(new mOnClickListener());
            ((Button)rootView.findViewById(R.id.findNewButton)).setOnClickListener(new mOnClickListener());

            findOldEdit = (EditText)rootView.findViewById(R.id.findOldEdit);
            findNewEdit = (EditText)rootView.findViewById(R.id.findNewEdit);
            findOldView = rootView.findViewById(R.id.findPreView);
            findNewView = rootView.findViewById(R.id.findNewView);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }
    private void doHttpPost(String textString){
        try {
            textString = URLEncoder.encode(textString, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.post(UrlConstant.apiBaseUrl+"search/keyword/"+textString,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                String s = new String(bytes);
                if (s.equals("{}")) return;
                final Type type = new TypeToken<SearchBean>() {
                }.getType();
                SearchBean searchBean = new Gson().fromJson(s, type);
                searchAdapter.refreshAll(searchBean.getVideos());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getActivity(), NoInternetConstant.WARNING_TEXT, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class mOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            InputMethodManager imm = (InputMethodManager)getActivity().getApplicationContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            if (v.getId() == R.id.findOldButton){
                String textString = findOldEdit.getText().toString();
                if (!textString.equals("")) {
                    findNewEdit.setText(textString);
                    findOldView.setVisibility(View.GONE);
                    findNewView.setVisibility(View.VISIBLE);
                    doHttpPost(textString);
                }
            } else if (v.getId() == R.id.findNewButton){
                searchAdapter.clear();
                String textString = findNewEdit.getText().toString();
                if (!textString.equals("")) {
                    doHttpPost(textString);
                }
            }
        }
    }
}
