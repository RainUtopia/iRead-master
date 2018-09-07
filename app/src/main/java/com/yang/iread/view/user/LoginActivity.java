package com.yang.iread.view.user;

import android.widget.EditText;
import android.widget.Toast;

import com.yang.iread.R;
import com.yang.iread.base.BaseActivity;
import com.yang.iread.view.home.HomeActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: jianhong
 * @createDate: 2018/9/6 12:46
 * @description:
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.edt_login_user)
    EditText mEdtUserName;

    @BindView(R.id.edt_login_password)
    EditText mEdtPassword;

    private LoginPresenter mPresener;

    @Override
    public int loadLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {
        mPresener = new LoginPresenter();
        mPresener.attachView(this);
    }

    @OnClick(R.id.btn_login)
    void login() {
        String name = mEdtUserName.getText().toString().trim();
        String password = mEdtPassword.getText().toString().trim();
        mPresener.login(name,password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresener.detachView();
    }

    @Override
    public void handleLogin() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();

        startActivity(HomeActivity.class);
    }
}
