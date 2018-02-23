package com.hj.yunerp.common;

import android.app.ProgressDialog;
import android.content.Context;

public interface IActivitySupport {
    /**
     * 开启服务.
     */
    public abstract void startService();

    /**
     * 终止服务.
     */
    public abstract void stopService();

    /**
     * 校验网络-如果没有网络就弹出设置,并返回true.
     */
    public abstract boolean validateInternet();

    /**
     * 校验网络-如果没有网络就返回true.
     */
    public abstract boolean hasInternetConnected();

    /**
     * 退出应用.
     */
    public abstract void isExit();

    /**
     * 判断GPS是否已经开启.
     */
    public abstract boolean hasLocationGPS();

    /**
     * 判断基站是否已经开启.
     */
    public abstract boolean hasLocationNetWork();

    /**
     * 检查内存卡.
     */
    public abstract void checkMemoryCard();

    /**
     * 获取进度条.
     */
    public abstract ProgressDialog getProgressDialog();


    /**
     * 返回当前Activity上下文.
     */
    public abstract Context getContext();


    /**
     * 发出Notification的method.
     *
     * @param iconId       图标
     * @param contentTitle 标题
     * @param contentText  你内容
     * @param activity
     */
    public void setNotiType(int iconId, String contentTitle,
                            String contentText, Class activity, String from);


}
