package com.hj.yunerp.common;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hj.yunerp.widget.MyDialog;
import com.hj.yunerp.widget.MyToast;
import com.hj.yunerp.widget.WaitDialogRectangle;
import com.lzy.okgo.OkGo;

/**
 * fragment 懒加载
 * http://www.jianshu.com/p/104be7cd72b6
 */

public class FragmentBase extends Fragment {
    public WaitDialogRectangle waitDialogRectangle;
    public Gson mGson;
    //是否可见
    protected boolean isVisble;
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared = false;
    private boolean hasLoadData = false;
    //带有取消确定的弹框
    public MyDialog myDialog;

    public Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        mContext = getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisble = true;
            onVisible();
        } else {
            isVisble = false;
            onInVisible();
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisble = true;
            onVisible();
        } else {
            isVisble = false;
            onInVisible();
        }
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
        if (isPrepared && isVisble && !hasLoadData) {
            loadData();
        }
    }

    protected void loadData() {
        hasLoadData = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
//        //取消所有请求
//        OkGo.getInstance().cancelAll();
    }


    /**
     * 单纯跳转界面
     *
     * @param toClass
     */
    public void intentActivity(Class toClass) {
        Intent intent = new Intent(mContext, toClass);
        startActivity(intent);
    }

    /**
     * 跳转页面带参数
     *
     * @param to
     * @param bundle
     */
    public void intentBdActivity(Class to, Bundle bundle) {
        Intent intent = new Intent(mContext, to);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 无返回数据
     *
     * @param toClass
     * @param requestCode
     */
    public void intentActivityForResult(Class toClass, int requestCode) {
        Intent intent = new Intent(mContext, toClass);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 带参数返回
     *
     * @param toClass
     * @param requestCode
     * @param bundle
     */
    public void intentBdActivityForResult(Class toClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(mContext, toClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }


    /**
     * 长toast
     *
     * @param content
     */
    public void toastLONG(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_LONG).show();
    }

    /**
     * 短toast
     *
     * @param content
     */
    public void toastSHORT(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义toast
     *
     * @param content
     */
    public void myToastShow(String content) {
        new MyToast(mContext, content);
    }


    /**
     * Log 输出
     *
     * @param content
     */
    public void logShow(String content) {
        if (Constant.IS_LOG_SHOW_VIEW)
            Log.e("YERP" , this.getClass().getSimpleName()+" -- "+content);
    }

}
