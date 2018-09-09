package com.yang.iread.view.splash;

import android.os.Handler;

import com.yang.iread.R;
import com.yang.iread.base.BaseActivity;
import com.yang.iread.base.BaseContract;
import com.yang.iread.view.user.login.LoginActivity;

/**
 * Description:
 * Data: 18-9-6 上午11:36
 *
 * @author: yang
 */
public class SplashActivity extends BaseActivity {
    private Handler mHandler;

    @Override
    public int loadLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public void initView() {
        setFullScreen();
    }

    @Override
    public void loadData() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(HomeActivity.class);
                startActivity(LoginActivity.class);
                finish();
            }
        },2000);
    }
}
