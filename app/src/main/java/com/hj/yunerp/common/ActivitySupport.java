package com.hj.yunerp.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hj.yunerp.R;
import com.hj.yunerp.db.SharePreferenceUtil;
import com.hj.yunerp.widget.WaitDialogRectangle;

/**
 * Created by zhangdongdong on 2018/2/23.
 */

public class ActivitySupport extends AppCompatActivity implements IActivitySupport {

    protected Context mContext = null;
    //通知
    protected NotificationManager notificationManager;
    //临时数据库
    public static SharePreferenceUtil spUtil = null;
    //过度进度条
    protected WaitDialogRectangle waitDialogRectangle;

    /**
     * @author haijian 增加变量判断键盘是否收回
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (waitDialogRectangle == null)
            waitDialogRectangle = new WaitDialogRectangle(mContext);

        if (spUtil == null)
            spUtil = SharePreferenceUtil.getInstance(mContext);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        waitDialogRectangle.cancel();
    }

    @Override
    public void startService() {

    }

    @Override
    public void stopService() {

    }

    @Override
    public boolean validateInternet() {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getSystemService(mContext.CONNECTIVITY_SERVICE);
        if (manager == null) {
            openWirelessSet();
            return false;
        } else {
            NetworkInfo[] info = manager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        openWirelessSet();
        return false;
    }

    public void openWirelessSet() {

    }

    @Override
    public boolean hasInternetConnected() {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getSystemService(mContext.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo network = manager.getActiveNetworkInfo();
            if (network != null && network.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void isExit() {

    }

    @Override
    public boolean hasLocationGPS() {
        LocationManager manager = (LocationManager) mContext
                .getSystemService(mContext.LOCATION_SERVICE);
        if (manager
                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasLocationNetWork() {
        LocationManager manager = (LocationManager) mContext
                .getSystemService(mContext.LOCATION_SERVICE);
        if (manager
                .isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void checkMemoryCard() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            new android.app.AlertDialog.Builder(mContext)
                    .setTitle(R.string.prompt)
                    .setMessage(R.string.check_sdcard)
                    .setPositiveButton(R.string.menu_settings,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                    Intent intent = new Intent(
                                            Settings.ACTION_SETTINGS);
                                    mContext.startActivity(intent);
                                }
                            })
                    .setNegativeButton(R.string.module_app_Exit,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                    //退出app
//                                    eapApplication.exit();
                                }
                            }).create().show();
        }
    }

    @Override
    public ProgressDialog getProgressDialog() {
        return null;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 发出Notification的method.
     *
     * @param iconId       图标
     * @param contentTitle 标题
     * @param contentText  你内容
     * @param activity
     */
    public void setNotiType(int iconId, String contentTitle,
                            String contentText, Class activity, String from) {
        /*
         * 创建新的Intent，作为点击Notification留言条时， 会运行的Activity
		 */
        Intent notifyIntent = new Intent(this, activity);
        notifyIntent.putExtra("to", from);
        // notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		/* 创建PendingIntent作为设置递延运行的Activity */
        PendingIntent appIntent = PendingIntent.getActivity(this, 0,
                notifyIntent, 0);

		/* 创建Notication，并设置相关参数 */
//        Notification myNoti = new Notification();
//		// 点击自动消失
//		myNoti.flags = Notification.FLAG_AUTO_CANCEL;
//		/* 设置statusbar显示的icon */
//		myNoti.icon = iconId;
//		/* 设置statusbar显示的文字信息 */
//		myNoti.tickerText = contentTitle;
//		/* 设置notification发生时同时发出默认声音 */
//		myNoti.defaults = Notification.DEFAULT_SOUND;
        /* 设置Notification留言条的参数 */
        Notification myNoti = new Notification.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(appIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setSmallIcon(iconId)
                .setTicker(contentTitle)
                .setOngoing(true).build();


//		myNoti.setLatestEventInfo(this, contentTitle, contentText, appIntent);
        /* 送出Notification */
        notificationManager.notify(0, myNoti);
    }


    /**
     * @author haijian 增加点击edittext区域外，收起软键盘功能
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > right
                    && event.getY() > top && event.getY() < bottom) {//如果是输入框右边的部分就保留
                return false;
            }
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 小数点后两位
     *
     * @param editText
     */
    public static void setEditextPoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });
    }


    /**
     * 跳转界面
     */
    protected void IntentInterface(Context context, Class cs) {
        Intent intent = new Intent(context, cs);
        startActivity(intent);
    }

    /**
     * 获取输入框的内容
     * @param editText
     * @return
     */
    protected String getEditextCt(EditText editText){
        return editText.getText().toString().trim();
    }

}
