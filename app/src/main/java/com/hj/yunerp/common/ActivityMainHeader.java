package com.hj.yunerp.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.hj.yunerp.R;


/**
 * @author zhangdongdong
 *  MainActivity 头部
 */
public class ActivityMainHeader extends ActivitySupport {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.module_activity_base_header);

    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View.inflate(this, layoutResID, (ViewGroup) findViewById(R.id.base_content));
    }

}
