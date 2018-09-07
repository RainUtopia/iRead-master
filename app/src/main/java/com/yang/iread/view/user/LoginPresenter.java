package com.yang.iread.view.user;

import com.yang.iread.base.BasePresenter;
import com.yang.iread.http.HttpManager;
import com.yang.iread.result.Token;
import com.yang.iread.util.URLUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: jianhong
 * @createDate: 2018/9/6 12:58
 * @description:
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter<LoginContract.View> {
    @Override
    public void login(String userName, String password) {
        HttpManager.getInstance()
                .getHttpApi(URLUtils.LOGIN_URL)
                .login("00d91e8e0cca2b76f515926a36db68f5", "13594347817","123456")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoadingDialog("登录中...");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Token>() {
                    @Override
                    public void onNext(Token token) {
                        mView.handleLogin();
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleThrowable(e);
                        mView.hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoadingDialog();
                    }
                });
    }
}
