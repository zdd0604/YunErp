package com.hj.yunerp.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hj.yunerp.MainActivity;
import com.hj.yunerp.R;
import com.hj.yunerp.common.ActivitySupport;
import com.hj.yunerp.common.Constant;
import com.hj.yunerp.db.SpUtils;
import com.hj.yunerp.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhangdongdong
 * 引导页
 */

public class GuideActivity extends ActivitySupport implements OnBannerListener,
        ViewPager.OnPageChangeListener ,
        View.OnClickListener{
    //轮播图
    @BindView(R.id.guideBanner)
    Banner guideBanner;
    @BindView(R.id.guideBtn)
    Button guideBtn;

    //图片集合
    List<String> imgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_guidance);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");

        guideBanner.setOnPageChangeListener(this);
        guideBtn.setOnClickListener(this);

        //是否自动轮播
        guideBanner.isAutoPlay(false);


        //简单使用
        guideBanner.setImages(imgList)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();

    }


    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        guideBtn.setVisibility( position == (imgList.size() - 1) ? View.VISIBLE : View.GONE );

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guideBtn:
                enterMainActivity();
                break;
        }
    }


    private void enterMainActivity() {
        intentActivity(LoginActivity.class);
        SpUtils.putBoolean(GuideActivity.this, Constant.FIRST_OPEN, true);
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();

    }

}
