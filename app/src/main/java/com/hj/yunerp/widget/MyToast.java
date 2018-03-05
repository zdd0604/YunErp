package com.hj.yunerp.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.yunerp.R;

/**
 * Created by zhangdongdong on 2018/3/5.
 * 提示框
 */

public class MyToast {
    private Toast toast;

    public MyToast(Context context, String text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.module_toast_center, null);
        TextView t = (TextView) view.findViewById(R.id.myToast_tv);
        t.setText(text);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
