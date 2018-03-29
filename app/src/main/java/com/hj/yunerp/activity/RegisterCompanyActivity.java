package com.hj.yunerp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hj.yunerp.R;
import com.hj.yunerp.common.ActivityBaseHeader;
import com.hj.yunerp.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterCompanyActivity extends ActivityBaseHeader {

    @BindView(R.id.registerCpName)
    EditText registerCpName;
    @BindView(R.id.registerContact)
    EditText registerContact;
    @BindView(R.id.registerCpPsd)
    EditText registerCpPsd;
    @BindView(R.id.registerAgainCpPsd)
    EditText registerAgainCpPsd;
    @BindView(R.id.register_cpNextStep)
    Button register_cpNextStep;

    private String cpName;
    private String contact;
    private String psd;
    private String cpPsd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_register_company);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        //设置标题
        setBaseCenterTv(getString(R.string.module_registerCompany_acName));
        register_cpNextStep.setText(getString(R.string.btn_register));
        register_cpNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRegisterInfo();
            }
        });
    }

    private void sendRegisterInfo() {
        cpName = getEditextCt(registerCpName);
        contact = getEditextCt(registerContact);
        psd = getEditextCt(registerCpPsd);
        cpPsd = getEditextCt(registerAgainCpPsd);

        if (!StringUtil.isStrTrue(cpName)) {
            myToastShow(getString(R.string.module_toast_register_cpName));
            return;
        }
        if (!StringUtil.isStrTrue(contact)) {
            myToastShow(getString(R.string.module_toast_register_cpContact));
            return;
        }
        if (!StringUtil.isStrTrue(psd)) {
            myToastShow(getString(R.string.module_toast_register_psd));
            return;
        }
        if (!StringUtil.isStrTrue(cpPsd)) {
            myToastShow(getString(R.string.module_toast_changepsd_aginPsd));
            return;
        }

        if (!psd.equals(cpPsd)){
            myToastShow(getString(R.string.module_toast_changepsd_cancel));
            return;
        }

        intentActivity(LoginActivity.class);
        finish();
    }
}
