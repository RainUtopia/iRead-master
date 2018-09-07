package com.yang.iread.base;

/**
 * Description:
 * Data: 18-9-6 上午11:36
 *
 * @author: yang
 */
public interface BaseContract {
    interface View {
        void handleError(String errMsg);

        void showLoadingDialog(String... label);

        void hideLoadingDialog();
    }

    interface Presenter<T> {
        void attachView(T view);

        void detachView();
    }
}
