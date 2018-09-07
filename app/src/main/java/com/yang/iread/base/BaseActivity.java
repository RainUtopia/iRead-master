package com.yang.iread.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yang.iread.R;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.ButterKnife;

/**
 * Description:
 * Data: 18-9-6 上午11:36
 *
 * @author: yang
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {
    private ZLoadingDialog mLoadingDialog;

    @LayoutRes
    public abstract int loadLayout();

    public abstract void initView();

    public abstract void loadData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(loadLayout());

        ButterKnife.bind(this);

        initView();
        loadData();
    }

    public void startActivity(Class<?> activity) {
        startActivity(new Intent(this,activity));
    }

    @Override
    public void handleError(String errMsg) {
        //设置出现错误时候的布局
        setContentView(R.layout.activity_error);
        Toast.makeText(this,errMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog(String... label) {
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
        hideNavigationBar();
        hideBackNavigation();
    }

    public void hideBackNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled( false );
    }

    public void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility( uiOptions );
    }

    /*public void hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusbar.setVisibility( View.GONE );
        }
    }

    public void hideToolbar() {
        mBaseToolbar.setVisibility( View.GONE );
        mToolbarBottomLine.setVisibility( View.GONE );
    }*/

}