package com.hj.yunerp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hj.yunerp.R;
import com.hj.yunerp.common.ActivityBaseHeader;
import com.hj.yunerp.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 修改密码
 */
public class ChangePwsActivity extends ActivityBaseHeader {
    //密码
    @BindView(R.id.changePsd)
    EditText changePsd;
    //确认密码
    @BindView(R.id.changeAgainCpPsd)
    EditText changeAgainCpPsd;
    //完成
    @BindView(R.id.change_nextStep_btn)
    TextView change_nextStep_btn;

    private String psd;
    private String aginpsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_change_pws);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setBaseCenterTv(getString(R.string.module_change_acPsd_acTitle));

        changePsd.addTextChangedListener(textWatcher);
        changeAgainCpPsd.addTextChangedListener(textWatcher);

        change_nextStep_btn.setText(getString(R.string.btn_confirm));

        change_nextStep_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changPsd();
            }
        });
    }


    /**
     * 提交修改的操作
     */
    private void changPsd(){
        psd = getEditextCt(changePsd);
        aginpsd = getEditextCt(changeAgainCpPsd);

        if (!psd.equals(aginpsd))
        {
            myToastShow(getString(R.string.module_toast_changepsd_cancel));
            return;
        }

        intentActivity(LoginActivity.class);
        finish();
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            psd = getEditextCt(changePsd);
            aginpsd = getEditextCt(changeAgainCpPsd);

            if (StringUtil.isStrTrue(psd)
                    && StringUtil.isStrTrue(aginpsd))
            {
                change_nextStep_btn.setEnabled(true);
            }
            else
            {
                change_nextStep_btn.setEnabled(false);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
