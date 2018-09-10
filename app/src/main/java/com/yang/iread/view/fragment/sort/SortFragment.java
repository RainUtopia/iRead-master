package com.yang.iread.view.fragment.sort;

import android.support.v4.app.Fragment;
import android.view.View;

import com.socks.library.KLog;
import com.yang.iread.R;
import com.yang.iread.base.BaseContract;
import com.yang.iread.base.BaseFragment;

public class SortFragment extends BaseFragment {
    @Override
    public int loadLayout() {
        return R.layout.activity_login;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        KLog.d("SortPresenter");
        return null;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void loadData() {

    }
}
