package com.yang.iread.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;

import butterknife.ButterKnife;

/**
 * Date：2018/9/9 on 14:41
 * Description:
 *
 * @author jianhong
 */
public abstract class BaseFragment<P extends BaseContract.Presenter> extends Fragment implements BaseContract.View {
    public P mPresenter;

    private View mRootView;

    @LayoutRes
    public abstract int loadLayout();

    public abstract P getPresenter();

    public abstract void initView(View view);

    public abstract void loadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("UUU", "BaseFragment onAttach");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.d("UUU", "BaseFragment onAttachFragment");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(loadLayout(), container, false);
        }
        Log.d("UUU", "BaseFragment onCreateView");
        ButterKnife.bind(this, mRootView);

        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("UUU", "BaseFragment onViewCreated");
        initView(view);
        Log.d("UUU", "BaseFragment after initView");
        loadData();
    }

    @Override
    public void handleError(String errMsg) {

    }

    @Override
    public void showLoadingDialog(String... label) {
    }

    @Override
    public void hideLoadingDialog() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KLog.d("onDestroyView");
    }

    @Override
    public void onDestroy() {
        KLog.d("onDestroy");
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
