package com.hj.yunerp.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.hj.yunerp.R;

/**
 * Created by zhangdongdong on 2018/3/1.
 */

public class CountDownTimerUtils extends CountDownTimer {
    //发送短信按钮
    private TextView mTv;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(TextView textView,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTv = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTv.setEnabled(false);
        mTv.setText(millisUntilFinished / 1000 +" 后重发");

        SpannableString spannableString = new SpannableString(mTv.getText().toString());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
        spannableString.setSpan(foregroundColorSpan, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色

        mTv.setText(spannableString);

    }

    @Override
    public void onFinish() {
        mTv.setText("重新发送");
        mTv.setEnabled(true);//重新获得点击
    }
}
