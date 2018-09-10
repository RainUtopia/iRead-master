package com.yang.iread.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;
import com.yang.iread.R;
import com.yang.iread.util.ClassUtil;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Data: 18-9-6 上午11:36
 *
 * @author: yang
 */
public abstract class BaseActivity<P extends BaseContract.Presenter> extends AppCompatActivity implements BaseContract.View {
    public P mPresenter;
    private ZLoadingDialog mLoadingDialog;
    private Toast mToast;

    @BindView(R.id.tb_base)
    Toolbar mToolbar;

    //@BindView(R.id.fl_content)
    FrameLayout mFlContent;

    @BindView(R.id.tv_tb_title)
    TextView mBaseTitleTv;

    @LayoutRes
    public abstract int loadLayout();

    public abstract P getPresenter();

    public abstract void initView();

    public abstract void loadData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

       // mToolbar = findViewById(R.id.tb_base);
        mFlContent = findViewById(R.id.fl_content);

        if (loadLayout() != 0) {
            View content  = getLayoutInflater().inflate(loadLayout(),null);
            if (content != null) {
                mFlContent.addView(content);
            }
        }
        ButterKnife.bind(this);
        //instancePresenter();
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initToolobar();
        initView();
        loadData();
    }

    public void startActivity(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }

    @Override
    public void handleError(String errMsg) {
        //设置出现错误时候的布局
        setContentView(R.layout.activity_error);
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog(@Nullable String... label) {
        mLoadingDialog = new ZLoadingDialog(this);
        mLoadingDialog.setLoadingBuilder(Z_TYPE.CIRCLE_CLOCK)//设置类型
                .setLoadingColor(Color.GRAY)//颜色
                .setHintText(label == null ? "加载中..." : label[0])
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#FFFFFF")) // 设置背景色，默认白色
                .setCanceledOnTouchOutside(false)
                .setCancelable(false)
                .show();
    }

    @Override
    public void hideLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    public void setFullScreen() {
        hideStatusBar();
        hideToolbar();
        hideNavigationBar();
        //hideBackNavigation();
    }

    public void hideStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //View decorView = getWindow().getDecorView();
        //decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);
    }


    public void hideToolbar() {
        mToolbar.setVisibility( View.GONE );
    }

    public void showToolbar() {
        mToolbar.setVisibility( View.VISIBLE );
    }

    //隐藏toolbar返回按钮
    public void hideBackNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    //设置toolbar返回按钮图标
    public void setBackNavigationDrawable(@DrawableRes int id) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(id);
    }

    public void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    public void showToast(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(this, resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    private void instancePresenter() {
        mPresenter = ClassUtil.getNewClass(this, 0);
    }

    private void initToolobar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //setBackNavigationDrawable(R.drawable.ic_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //自定义标题
    public void setToolbarTitle(@StringRes int id) {
        mBaseTitleTv.setText(id);
    }

    public void setToolbarTitle(String title) {
        mBaseTitleTv.setText(title);
    }

    public void setToolbarTitleColor(@ColorRes int colorRes) {
        mBaseTitleTv.setTextColor(ContextCompat.getColor(this,colorRes));
    }

    //默认标题
    public void setDefaultTitle(String title) {
        //设置颜色
        mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        //设置标题
        mToolbar.setTitle(title);
    }
}
