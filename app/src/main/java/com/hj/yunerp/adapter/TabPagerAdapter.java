package com.hj.yunerp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页适配器
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> list;

        // 构造函数
        public TabPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            if (list == null)
                list = new ArrayList<>();
            this.list = list;
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getCount() {
            return list.size();
        }

    }