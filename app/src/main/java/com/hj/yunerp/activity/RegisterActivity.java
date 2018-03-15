package com.hj.yunerp.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.yunerp.R;
import com.hj.yunerp.common.ActivityBaseHeader;
import com.hj.yunerp.common.ActivityMainHeader;
import com.hj.yunerp.utils.CountDownTimerUtils;
import com.hj.yunerp.utils.StringUtil;
import com.hj.yunerp.widget.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author zhangdongdong
 *         <p>
 *         验证手机号码
 */
public class RegisterActivity extends ActivityBaseHeader implements View.OnClickListener {
    //手机号
    @BindView(R.id.register_myPhone_et)
    EditText register_myPhone_et;
    //验证码
    @BindView(R.id.register_captcha_et)
    EditText register_captcha_et;
    //获取验证码
    @BindView(R.id.register_captcha_tv)
    TextView register_captcha_tv;
    //下一步
    @BindView(R.id.register_nextStep_btn)
    TextView register_nextStep_btn;

    //倒计时
    private CountDownTimerUtils countDownTimer;
    //手机号
    private String userPhone;
    //验证码
    private String userCaptcha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_register);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setBaseCenterTv(getString(R.string.module_register_acName));

        register_myPhone_et.addTextChangedListener(textWatcher);
        register_captcha_et.addTextChangedListener(textWatcher);

        register_captcha_tv.setOnClickListener(this);
        register_nextStep_btn.setOnClickListener(this);
    }


    /**
     * 输入监听时间
     */
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userPhone = getEditextCt(register_myPhone_et);
            userCaptcha = getEditextCt(register_captcha_et);

            if (StringUtil.isStrTrue(userPhone)
                    && userPhone.length()==11
                    && StringUtil.isStrTrue(userCaptcha)
                    && userCaptcha.length() == 6)
            {
                register_nextStep_btn.setEnabled(true);
            }
            else
            {
                register_nextStep_btn.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_captcha_tv:

                //获取验证码
                gainCaptch();

                break;
            case R.id.register_nextStep_btn:

                //下一步
                nextStep();

                break;
        }
    }


    /**
     * 获取验证码的操作
     * 请求网络
     */
    private void gainCaptch() {
        if (!StringUtil.isStrTrue(userPhone)) {
            new MyToast(mContext, getString(R.string.module_register_myPhone_tips));
            return;
        }

        if (!StringUtil.isMobileNO(userPhone)) {
            new MyToast(mContext, getString(R.string.module_register_ckMyPhone_tips));
            return;
        }

        //获取验证码
        countDownTimer = new CountDownTimerUtils(register_captcha_tv, 60000, 1000);
        countDownTimer.start();

    }


    /**
     * 下一步操作请求（注册）
     */
    private void nextStep() {

        if (!StringUtil.isStrTrue(userCaptcha)) {
            new MyToast(mContext, getString(R.string.module_register_CAPTCHA_tips));
            return;
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

}
