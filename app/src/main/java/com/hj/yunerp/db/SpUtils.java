package com.hj.yunerp.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangdongdong on 2018/2/2.
 * 用于存储引导页、公司名称、登录退出等一下简单的数据
 */

public class SpUtils {

    private static final String spFileName = "app";


    /**
     * 获取是否第一次进入app
     * @param context
     * @param strKey
     * @return
     */
    public static Boolean getBoolean(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        Boolean result = setPreferences.getBoolean(strKey, false);
        return result;
    }

    /**
     * 获取是否第一次进入app
     *
     * @param context
     * @param strKey
     * @param strDefault
     * @return
     */
    public static Boolean getBoolean(Context context, String strKey,
                                     Boolean strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        Boolean result = setPreferences.getBoolean(strKey, strDefault);
        return result;
    }


    /**
     * 设置app启动标示
     * @param context
     * @param strKey
     * @param strData
     *          是否第一次启动
     */
    public static void putBoolean(Context context, String strKey,
                                  Boolean strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putBoolean(strKey, strData);
        editor.commit();
    }



}
