package com.hj.yunerp.common;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hj.yunerp.R;
import com.hj.yunerp.fragment.BaseNavLeftFragment;
import com.hj.yunerp.utils.BitmapUtils;


/**
 * @author zhangdongdong
 *  MainActivity 头部
 *  带侧滑
 */
public class ActivityMainHeader extends ActivitySupport {

    protected FrameLayout mainLeftMenu;
    protected DrawerLayout main_header_layout;

    protected Toolbar base_toolbar;
    protected ImageView main_leftImg;
    protected TextView main_centerTitle;
    protected ImageView main_rightImg;

    protected BaseNavLeftFragment leftFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.module_activity_main_header);

        init();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View.inflate(this, layoutResID, (ViewGroup) findViewById(R.id.mainFrameLayout));
    }

    private void init() {
        leftFragment = new BaseNavLeftFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mainLeftMenu, leftFragment).commit();
        setSupportActionBar((Toolbar) findViewById(R.id.main_headerToolbar));
        base_toolbar = findViewById(R.id.main_headerToolbar);
        main_leftImg = findViewById(R.id.main_leftImg);
        main_centerTitle = findViewById(R.id.main_centerTitle);
        main_rightImg = findViewById(R.id.main_rightImg);
        mainLeftMenu = findViewById(R.id.mainLeftMenu);
        main_header_layout = findViewById(R.id.main_header_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);


        main_leftImg.setOnClickListener(onClickListener);
        main_rightImg.setOnClickListener(onClickListener);


        //设置左边标题图片
        setTbLeftResourcesDw(R.mipmap.module_mydialog_fault);
        //设置右边标题图片
        setTbRightResourcesDw(R.mipmap.module_mydialog_fault);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.main_leftImg:
                    onNavMenuClick();
                    break;
                case R.id.main_rightImg:
                    onTbRightClick();
                    break;
            }
        }
    };

    /**
     * 设置标题
     * @param tvText
     */
    protected void setMainTitle(String tvText){
        if (tvText != null) {
            if (main_centerTitle != null) {
                main_centerTitle.setText(tvText);
            }
        }
    }


    /**
     * toolbar 设置左边标题图片
     * @param imgID
     */
    protected void setTbLeftResourcesDw(int imgID){
        main_leftImg.setImageDrawable(BitmapUtils.getResourcesDw(mContext,imgID));
    }
    /**
     * toolbar 左边菜单打开
     */
    protected void onNavMenuClick() {
        if (main_header_layout.isDrawerOpen(Gravity.LEFT)) {
            closeNavMenu();
        } else {
            main_header_layout.openDrawer(Gravity.LEFT, true);
        }
    }

    /**
     * 关闭左边菜单
     */
    protected void closeNavMenu() {
        main_header_layout.closeDrawer(Gravity.LEFT, true);
    }

    /**
     * toolbar 右边图片点击时间
     */
    protected void onTbRightClick() {

    }

    /**
     * toolbar 设置右边标题图片
     * @param imgID
     */
    protected void setTbRightResourcesDw(int imgID){
        main_rightImg.setImageDrawable(BitmapUtils.getResourcesDw(mContext,imgID));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (main_header_layout.isDrawerOpen(Gravity.LEFT)) {
                onNavMenuClick();
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
