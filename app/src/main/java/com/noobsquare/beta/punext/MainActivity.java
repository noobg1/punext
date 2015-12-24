package com.noobsquare.beta.punext;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils.*;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
//import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity implements MaterialTabListener{

    private android.support.v7.widget.Toolbar toolbar;
    private ViewPager mPager;
    private ViewPager viewPager;
    private SlidingTabLayout mTabs;
    private TextView textView;
    private MaterialTabHost tabHost;
    private ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_tab_lib);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
               getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setup(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

       /* mPager = (ViewPager) findViewById(R.id.pager);// sliding tabs wic didiin work
        mPager.setAdapter(new MypagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setViewPager(mPager);*/

        //using lib
        tabHost = (MaterialTabHost)findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });
        for(int i = 0;i < adapter.getCount();i++){
            tabHost.addTab(tabHost.newTab()
                    .setIcon(adapter.getIcon(i))
                                    .setTabListener(this));
           /* MaterialTab materialTab = tabHost.newTab();
            materialTab.setText("Test tab");
            materialTab.setTabListener(this);
            tabHost.addTab(materialTab);*/
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"hey yo hit"+item.getTitle(),Toast.LENGTH_LONG).show();
            return true;
        }

        if(id==R.id.navigate){
            startActivity(new Intent(this,Subactivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    private class ViewPageAdapter extends FragmentPagerAdapter{


        int icons[] = {R.drawable.ic_action_articles,R.drawable.ic_action_home,R.drawable.ic_action_personal};
        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }
        private Drawable getIcon(int position){
            return getResources().getDrawable(icons[position]);
        }
    }

/*
    class MypagerAdapter extends FragmentPagerAdapter{

        String[] tabs;
        public MypagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment myFragment = new MyFragment();
            myFragment.getInstance(position);
            return myFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }*/



}
