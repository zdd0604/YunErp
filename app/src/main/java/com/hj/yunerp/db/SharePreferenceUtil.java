package com.hj.yunerp.db;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static SharePreferenceUtil instance;
    private Context context;

    //账户id
    private String antID = "antID";
    //密码id
    private String pasID = "pasID";
    //记住密码状态，是否选中(选中的话自动填充账户或者密码)
    private String rmbCk = "rmbCk";

    public synchronized static SharePreferenceUtil getInstance(Context context) {
        if (null == instance) {
            instance = new SharePreferenceUtil(context);
        }
        return instance;
    }

    private SharePreferenceUtil(Context context) {
        this.context = context;
        this.sp = context.getSharedPreferences("YERPSP", context.MODE_PRIVATE);
        this.editor = sp.edit();
    }


    /**
     * 清空临时数据库
     */
    public void clearSharePreference() {
        editor.clear();
        editor.commit();
    }

    /**
     * @param ant       用户名
     * @param psd       密码
     * @param rmbAccPsd 选中状态
     */
    public void saveAccountInfo(String ant, String psd, boolean rmbAccPsd) {
        editor.putString(antID, ant);
        editor.putString(pasID, psd);
        editor.putBoolean(rmbCk, rmbAccPsd);
        editor.commit();
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public String getAntID() {
        return sp.getString(antID, "");
    }

    /**
     * 获取密码
     *
     * @return
     */
    public String getPasID() {
        return sp.getString(pasID, "");
    }

    /**
     * 获取选择框按钮状态
     *
     * @return
     */
    public boolean getRmbCk() {
        return sp.getBoolean(rmbCk, false);
    }


    /**
     * application run times
     *
     * @param runtimes
     */
    public void setRuntimes(int runtimes) {
        editor.putInt("times", runtimes);
        editor.commit();
    }

    public int getRunTimes() {
        return sp.getInt("times", 0);
    }


    public void setMyUserId(String userId) {
        editor.putString("myUserId", userId);
        editor.commit();
    }


    public String getMyId() {
        return sp.getString("myUserId", "");
    }

    public void setMyUserName(String userName) {
        editor.putString("username", userName);
        editor.commit();
    }


    public String getMyUserName() {
        return sp.getString("username", "");
    }

    public String getMyUserId() {
        return sp.getString("myUserId", "");
    }

    public void setMyPWD(String pwd) {
        editor.putString("password", pwd);
        editor.commit();
    }

    public String getMyPWD() {
        return sp.getString("password", "");
    }

    public void setForceExit(boolean forceExit) {
        editor.putBoolean("forceExit", forceExit);
        editor.commit();
    }

    public boolean isForceExit() {
        return sp.getBoolean("forceExit", false);
    }

    // 单位代码
    public void setComid(String comID) {
        // editor.getBoolean("comid", false);
        editor.putString("comid", comID);
        editor.commit();

    }

    public String getComid() {
        return sp.getString("comid", "");
    }

    // /单位名称
    public void setComName(String comName) {
        // editor.getBoolean("comid", false);
        editor.putString("comname", comName);
        editor.commit();

    }


    public String getComName() {
        return sp.getString("comname", "");
    }

    public void setUserImager(String imager) {
        editor.putString("userimager", imager);
        editor.commit();
    }

    public String getUserImager() {
        return sp.getString("userimager", "");
    }

    // 工作流列表表单类型
    public void setWorkListBillType(String gson) {
        editor.putString("worklistbilltype", gson);
        editor.commit();
    }

    public String getWorkListBillType() {
        return sp.getString("worklistbilltype", "");
    }

    public void setRegCurrCom(String regCurrCom) {
        editor.putString("regCurrCom", regCurrCom);
        editor.commit();
    }

    public String getRegCurrCom() {
        return sp.getString("regCurrCom", "");
    }

    // SessionId
    public void setMySessionId(String SessionId) {
        editor.putString("mySessionId", SessionId);
        editor.commit();
    }

    public String getMySessionId() {
        return sp.getString("mySessionId", "");
    }

    // /设置代码
    public void setDeviceId(String deviceID) {
        // editor.getBoolean("comid", false);
        editor.putString("device", deviceID);
        editor.commit();

    }
}
