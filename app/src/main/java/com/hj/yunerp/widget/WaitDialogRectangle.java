package com.hj.yunerp.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hj.yunerp.R;


/**
 * Created by zhangdongdong on 2018/3/7.
 *  加载过度弹框
 */

public class WaitDialogRectangle extends Dialog {
    private Context context;
    private View customView;
    private TextView tv;

    public WaitDialogRectangle(@NonNull Context context) {
        super(context, R.style.Module_WaitDialog_HzStyle);
        this.context = context;
    }

    public WaitDialogRectangle(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater= LayoutInflater.from(context);
        customView = inflater.inflate(R.layout.module_waitdialog_horizontal, null);

        setContentView(customView);
        tv = customView.findViewById(R.id.tv_waitdialog);
        LinearLayout linearLayout = customView.findViewById(R.id.ly_waitdialog);
        linearLayout.getBackground().setAlpha(180);
        tv.setText("正在加载数据");

        setCanceledOnTouchOutside(false);
    }

        public View getCustuoView(){
        return customView;
    }

    public void setText(String text)
    {
        this.tv.setText(text);
    }
}
