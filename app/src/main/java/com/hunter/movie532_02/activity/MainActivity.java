package com.hunter.movie532_02.activity;

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

import com.hunter.movie532_02.R;
import com.hunter.movie532_02.bean.Tab_main;
import com.hunter.movie532_02.fragment.FindFragment;
import com.hunter.movie532_02.fragment.HomeFragment;
import com.hunter.movie532_02.fragment.SearchFragment;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {

    private LayoutInflater layoutInflater = null;
    private FragmentTabHost fragmentTabHost = null;
    private ArrayList<Tab_main> fragmentTabList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInflater = getLayoutInflater();
        initFragmentTabHost();
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
