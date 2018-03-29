package com.hj.yunerp.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hj.yunerp.R;
import com.hj.yunerp.common.FragmentBase;
import com.hj.yunerp.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends FragmentBase implements OnBannerListener{

    Unbinder unbinder;
    private View view;

    @BindView(R.id.homeBanner)
    Banner homeBanner;

    //图片集合
    List<String> imgList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.moudule_fragment_home,container,false);

        unbinder = ButterKnife.bind(this,view);

        initView();
        return view;
    }

    private void initView() {
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");

        //简单使用
        homeBanner.setImages(imgList)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void OnBannerClick(int position) {
        myToastShow("点击了");
    }
}
