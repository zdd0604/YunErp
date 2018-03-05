package com.hj.yunerp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.hj.yunerp.common.ActivitySupport;
import com.hj.yunerp.fragment.HomeFragment;
import com.lzy.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ActivitySupport {
    @BindView(R.id.mainViewpager)
    ViewPager mainViewpager;
    @BindView(R.id.alphaIndicator)
    AlphaIndicator alphaIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moudule_activity_main);

        ButterKnife.bind(this);

        init();

    }

    private void init() {

        mainViewpager.setAdapter(new MainViewAdapter(getSupportFragmentManager()));

        alphaIndicator.setViewPager(mainViewpager);
    }


    private class MainViewAdapter extends FragmentPagerAdapter{

        private List<Fragment> fragments = new ArrayList<>();

        public MainViewAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new HomeFragment());
            fragments.add(new HomeFragment());
            fragments.add(new HomeFragment());
            fragments.add(new HomeFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
