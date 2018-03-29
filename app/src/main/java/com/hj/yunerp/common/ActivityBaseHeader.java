package com.hj.yunerp.common;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hj.yunerp.R;
import com.hj.yunerp.utils.BitmapUtils;
import com.hj.yunerp.widget.MyToast;


/**
 * @author zhangdongdong
 *         返回上级菜单、中间标题、右边标题(默认隐藏)
 */
public class ActivityBaseHeader extends ActivitySupport{
    Toolbar base_toolbar;
    TextView base_leftTv;
    TextView base_centerTv;
    TextView base_rightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.module_activity_base_header);

        init();
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View.inflate(this, layoutResID, (ViewGroup) findViewById(R.id.base_content));
    }


    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.base_toolbar));
        base_toolbar = findViewById(R.id.base_toolbar);
        base_leftTv = findViewById(R.id.base_leftTv);
        base_centerTv = findViewById(R.id.base_centerTv);
        base_rightTv = findViewById(R.id.base_rightTv);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);


        //设置返回按钮图片
        setLeftTvImg(R.mipmap.module_tolbar_backup);

        base_leftTv.setOnClickListener(onClickListener);
        base_rightTv.setOnClickListener(onClickListener);

    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case R.id.base_leftTv:
                   onLeftClick();
                   break;
               case R.id.base_rightTv:
                   onRightClick();
                   break;
           }
        }
    };


    /**
     * 设置资源文件的图片
     * @param imgID
     */
    protected void setTolbarResourcesDw(int imgID){
        base_toolbar.setBackground(BitmapUtils.getResourcesDw(mContext,imgID));
    }


    /**
     * 设置左边标题 图片适用于返回
     * @param imgID
     */
    protected void setLeftTvImg(int imgID) {
        Drawable drawable = getResources().getDrawable(imgID);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        base_leftTv.setCompoundDrawables(drawable, null, null, null);//画在右边
    }

    /**
     * 设置中间标题
     *
     * @param tvText
     */
    public void setBaseCenterTv(String tvText) {
        if (tvText != null) {
            if (base_centerTv != null) {
                base_centerTv.setText(tvText);
            }
        }
    }


    /**
     * 左边标题的点击事件
     */
    protected void onLeftClick() {
        finish();
    }

    /**
     * 右标题点击事件
     */
    protected void onRightClick() {

    }

}
