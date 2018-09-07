package com.yang.iread.base;

import java.net.UnknownHostException;

/**
 * Description:
 * Data: 18-9-6 上午11:36
 *
 * @author: yang
 */
public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {
    protected T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public void handleThrowable(Throwable throwable) {
        if (throwable instanceof UnknownHostException) {
            mView.handleError("网络错误");
        } else {

        }
    }
}
