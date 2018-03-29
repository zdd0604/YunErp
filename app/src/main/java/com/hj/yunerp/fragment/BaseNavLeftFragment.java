package com.hj.yunerp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hj.yunerp.R;
import com.hj.yunerp.common.FragmentBase;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangdongdong on 2018/3/26.
 *
 *  侧滑界面
 */

public class BaseNavLeftFragment extends FragmentBase {

    private Unbinder butterKnife;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView  = inflater.inflate(R.layout.module_fragment_basenavleft,container,false);
        butterKnife = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        butterKnife.unbind();
    }
}
