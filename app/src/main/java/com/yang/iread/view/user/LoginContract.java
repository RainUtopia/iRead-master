package com.yang.iread.view.user;

import com.yang.iread.base.BaseContract;

/**
 * @author: jianhong
 * @createDate: 2018/9/6 12:55
 * @description:
 */
public interface LoginContract {
    interface View extends BaseContract.View {
        void handleLogin();
    }

    interface Presenter<T> extends BaseContract.Presenter<T> {
        void login(String userName,String password);
    }
}
