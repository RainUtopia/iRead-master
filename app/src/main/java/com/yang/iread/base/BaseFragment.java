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

import butterknife.ButterKnife;

/**
 * Date：2018/9/9 on 14:41
 * Description:
 *
 * @author jianhong
 */
public abstract class BaseFragment extends Fragment {
    private View mRootView;

    @LayoutRes
    public abstract int loadLayout();

    public abstract void initView(View view);

    public abstract void loadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        Log.d("UUU","BaseFragment onAttach");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment( childFragment );
        Log.d("UUU","BaseFragment onAttachFragment");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate( loadLayout(), container, false );
        Log.d("UUU","BaseFragment onCreateView");
        if (mRootView != null) {
            ButterKnife.bind( this, mRootView );

            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView( mRootView );
            }
        }

        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        Log.d("UUU","BaseFragment onViewCreated");
        initView( view );
        Log.d("UUU","BaseFragment after initView");
        loadData();
    }
}
