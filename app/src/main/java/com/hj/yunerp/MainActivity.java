package com.hj.yunerp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.hj.yunerp.adapter.TabPagerAdapter;
import com.hj.yunerp.common.ActivityMainHeader;
import com.hj.yunerp.common.ActivitySupport;
import com.hj.yunerp.fragment.HomeFragment;
import com.lzy.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ActivityMainHeader {
    @BindView(R.id.mainViewpager)
    ViewPager mainViewpager;
    @BindView(R.id.alphaIndicator)
    AlphaIndicator alphaIndicator;

    //首页页面
    private List<Fragment> fragments;
    //标题
    private List<String> tabTitles;

    //首页
    private HomeFragment homeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moudule_activity_main);

        ButterKnife.bind(this);

        //添加页面
        addFragment();

    }

    /**
     * 添加页面
     */
    private void addFragment() {
        if (fragments == null)
            fragments = new ArrayList<>();

        if (tabTitles == null)
            tabTitles = new ArrayList<>();

        homeFragment = new HomeFragment();
        fragments.add(homeFragment);
        tabTitles.add(getString(R.string.tab_home));

        homeFragment = new HomeFragment();
        fragments.add(homeFragment);
        tabTitles.add(getString(R.string.tab_business));

        homeFragment = new HomeFragment();
        fragments.add(homeFragment);
        tabTitles.add(getString(R.string.tab_finance));

        homeFragment = new HomeFragment();
        fragments.add(homeFragment);
        tabTitles.add(getString(R.string.tab_basics));

        init();
    }


    private void init() {

        mainViewpager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),fragments));

        alphaIndicator.setViewPager(mainViewpager);

        // 设置预加载数为3
        mainViewpager.setOffscreenPageLimit(fragments.size());

        mainViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                setMainTitle(tabTitles.get(position));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
