package com.hj.yunerp.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.hj.yunerp.MainActivity;
import com.hj.yunerp.R;
import com.hj.yunerp.common.ActivitySupport;
import com.hj.yunerp.common.Constant;
import com.hj.yunerp.utils.StringUtil;
import com.hj.yunerp.widget.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

import static android.Manifest.permission.READ_CONTACTS;

@RuntimePermissions
public class LoginActivity extends ActivitySupport implements OnClickListener {

    //公司Logo或者个人头像
    @BindView(R.id.companyLogo)
    ImageView companyLogo;
    //账户
    @BindView(R.id.userAccount)
    EditText userAccount;
    //密码
    @BindView(R.id.userPassword)
    EditText userPassword;
    //记住我
    @BindView(R.id.login_rmbPsd_ck)
    CheckBox login_rmbPsd_ck;
    //注册
    @BindView(R.id.account_register_in_tv)
    TextView account_register_in_tv;
    //忘记密码
    @BindView(R.id.account_register_forgetPsd_tv)
    TextView account_register_forgetPsd_tv;
    //登录按钮
    @BindView(R.id.account_sign_in_button)
    Button account_sign_in_button;


    //账户名
    private String accountID;
    //密码
    private String pasdID;

    private Bundle mBundle;


    /**
     * 包含已知用户名和密码的假身份验证存储库。
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "13718999044", "12345"
    };
    /**
     * 跟踪登录任务，以确保我们可以在需要时取消它。.
     */
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_login);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //权限申请
        populateAutoComplete();

        if (mBundle == null)
            mBundle = new Bundle();

        userAccount.setText(spUtil.getAntID());
        userAccount.addTextChangedListener(textWatcher);
        if (spUtil.getRmbCk())
            userPassword.setText(spUtil.getPasID());
        userPassword.addTextChangedListener(textWatcher);
        login_rmbPsd_ck.setChecked(spUtil.getRmbCk());

        userPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        account_sign_in_button.setOnClickListener(this);
        account_register_in_tv.setOnClickListener(this);
        account_register_forgetPsd_tv.setOnClickListener(this);


        accountID = getEditextCt(userAccount);
        pasdID = getEditextCt(userPassword);

        if (StringUtil.isStrTrue(getEditextCt(userAccount)) &&
                StringUtil.isStrTrue(getEditextCt(userPassword)))
        {
            account_sign_in_button.setEnabled(true);
        }

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            accountID = getEditextCt(userAccount);
            pasdID = getEditextCt(userPassword);

            if (StringUtil.isStrTrue(accountID)&&StringUtil.isStrTrue(pasdID))
            {
                account_sign_in_button.setEnabled(true);
            }
            else
            {
                account_sign_in_button.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    /**
     * 授权操作
     */

    public void populateAutoComplete() {
        LoginActivityPermissionsDispatcher.mayRequestContactsWithCheck(this);
    }


    /**
     * 授权
     *
     * @return
     */


    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void mayRequestContacts() {

    }


    /**
     * 尝试登录或注册登录表单指定的帐户。
     * 如果有表格错误(无效的电子邮件、丢失的字段等)，则。
     * 出现错误，并没有实际的登录尝试。
     */
    private void attemptLogin() {

        if (mAuthTask != null) {
            return;
        }

        //重置错误。
        userAccount.setError(null);
        userPassword.setError(null);

        boolean cancel = false;
        View focusView = null;


        // 检查手机号是否正确
        if (!StringUtil.isMobileNO(accountID)) {
            userAccount.setError(getString(R.string.module_login_error_field_required));
            focusView = userAccount;
            cancel = true;
        }

        // 检查一个有效的密码，如果用户输入了一个。
        if (!StringUtil.isStrTrue(pasdID)) {
            userPassword.setError(getString(R.string.module_login_error_incorrect_password));
            focusView = userPassword;
            cancel = true;
        }


        if (cancel) {
            //有一个错误;不要尝试登录并将焦点放在第一个。
            //表单字段有错误。
            focusView.requestFocus();
        } else {
            //展示一个进展，并启动一个后台任务。
            //执行用户登录尝试。
            mAuthTask = new UserLoginTask(accountID, pasdID);
            mAuthTask.execute((Void) null);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_sign_in_button:
                attemptLogin();
                break;
            case R.id.account_register_in_tv:
                mBundle.putBoolean(Constant.REGISTER_TYPE,false);
                intentBdActivity(RegisterActivity.class,mBundle);
                break;
            case R.id.account_register_forgetPsd_tv:
                mBundle.putBoolean(Constant.REGISTER_TYPE,true);
                intentBdActivity(RegisterActivity.class,mBundle);
                break;
        }
    }


    /**
     * 用户异步登录
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mAccount;
        private final String mPassword;

        UserLoginTask(String account, String password) {
            mAccount = account;
            mPassword = password;

            waitDialogRectangle.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            if (DUMMY_CREDENTIALS[0].equals(mAccount)) {
                //帐户存在，如果密码匹配，返回true。
                return DUMMY_CREDENTIALS[1].equals(mPassword);
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            if (success) {
                //密码账户正确
                intentActivity(MainActivity.class);
//                if (login_rmbPsd_ck.isChecked())
                    spUtil.saveAccountInfo(mAccount, mPassword,login_rmbPsd_ck.isChecked());
                finish();
            } else {
                userPassword.setError(getString(R.string.module_login_error_incorrect_password));
                userPassword.requestFocus();
            }

            waitDialogRectangle.cancel();
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;

        }
    }




    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LoginActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}

