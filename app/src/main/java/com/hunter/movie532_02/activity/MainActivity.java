package com.hunter.movie532_02.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hunter.movie532_02.DB.UrlJson;
import com.hunter.movie532_02.R;
import com.hunter.movie532_02.bean.ShowMovie;
import com.hunter.movie532_02.bean.Tab_main;
import com.hunter.movie532_02.bean.UpdateBean;
import com.hunter.movie532_02.constants.NoInternetConstant;
import com.hunter.movie532_02.constants.UrlConstant;
import com.hunter.movie532_02.fragment.FindFragment;
import com.hunter.movie532_02.fragment.HomeFragment;
import com.hunter.movie532_02.fragment.SearchFragment;
import com.hunter.movie532_02.util.VersionUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends FragmentActivity {
    static private AsyncHttpClient client = new AsyncHttpClient();
    private LayoutInflater layoutInflater = null;
    private FragmentTabHost fragmentTabHost = null;
    private ArrayList<Tab_main> fragmentTabList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUpdate();
        setContentView(R.layout.activity_main);
        layoutInflater = getLayoutInflater();
        initFragmentTabHost();
    }
    void checkUpdate(){

        client.post(UrlConstant.apiBaseUrl+"ApkCheckUpdate", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                String s = new String(bytes);
                final Type type = new TypeToken<UpdateBean>() {
                }.getType();
                final UpdateBean updateBean = new Gson().fromJson(s, type);
                final String appVersion = VersionUtil.getVersionName(MainActivity.this);
                if (!appVersion.equals(updateBean.getVersion())) {
                    AlertDialog.Builder builer = new AlertDialog.Builder(MainActivity.this);
                    builer.setTitle("版本更新");
                    builer.setMessage("--新增特性--\n" + updateBean.getUpdateDesc());
                    builer.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent= new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(updateBean.getDownloadPath());
                            intent.setData(content_url);
                            startActivity(intent);
                        }
                    });
                    builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builer.create();
                    dialog.show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    void initFragmentTabHost(){
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),R.id.real_tabcontent);

        fragmentTabList = new ArrayList<Tab_main>();
        fragmentTabList.add(new Tab_main(R.string.home,HomeFragment.class,R.drawable.selector_home));
        fragmentTabList.add(new Tab_main(R.string.find,FindFragment.class,R.drawable.selector_find));
        fragmentTabList.add(new Tab_main(R.string.search,SearchFragment.class,R.drawable.selector_search));

        for (Tab_main tab : fragmentTabList){
            View view = layoutInflater.inflate(R.layout.tab_main,null);
            ((ImageView)view.findViewById(R.id.tab_image)).setBackgroundResource(tab.getImage());
            ((TextView)view.findViewById(R.id.tab_text)).setText(getString(tab.getName()));
            fragmentTabHost.addTab(fragmentTabHost.newTabSpec(getString(tab.getName())).setIndicator(view), tab.getFragmentClass(),null);
        }

        fragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }


}
