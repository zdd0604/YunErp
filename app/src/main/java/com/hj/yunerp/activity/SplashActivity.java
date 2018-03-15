package com.hj.yunerp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hj.yunerp.MainActivity;
import com.hj.yunerp.R;
import com.hj.yunerp.common.ActivitySupport;
import com.hj.yunerp.common.Constant;
import com.hj.yunerp.db.SpUtils;

import butterknife.ButterKnife;

/**
 * 启动屏
 *
 */
public class SplashActivity extends ActivitySupport {
    ImageView splashImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(this, Constant.FIRST_OPEN);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.moudule_activity_splash);

        splashImg = findViewById(R.id.splashImg);

        //Glide 加载图片简单用法
        Glide.with(this).
                load("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg")
                .into(splashImg);


        //执行登录操作

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);
    }

    private void enterHomeActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
