package com.yang.iread.view.fragment.discovery;

import com.yang.iread.base.BasePresenter;
import com.yang.iread.http.HttpManager;
import com.yang.iread.result.Images;
import com.yang.iread.util.URLUtils;
import com.yang.iread.util.Utils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: jianhong
 * @createDate: 2018/9/10 15:11
 * @description:
 */
public class DiscoveryPresenter extends BasePresenter<DiscoveryContract.View> implements DiscoveryContract.Presenter<DiscoveryContract.View> {
    private int defaultPage = 0;

    @Override
    public void getRecommand() {
        HttpManager.getInstance()
                .getHttpApi(URLUtils.IMAGES_URL)
                .getImages(Utils.getRandom())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoadingDialog();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Images>() {
                    @Override
                    public void onNext(Images images) {
                        mView.handleRecommandData(images.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoadingDialog();
                    }
                });
    }

    @Override
    public void loadMore() {
        if (++defaultPage > Utils.IMG_TOTAL_PAGE) {
            mView.handleLoadMoreData(null, true);
            return;
        }
        HttpManager.getInstance()
                .getHttpApi(URLUtils.IMAGES_URL)
                .getImages(defaultPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Images>() {
                    @Override
                    public void onNext(Images images) {
                        mView.handleLoadMoreData(images.getData(), false);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void setDefaultPage(int defaultPage) {
        this.defaultPage = defaultPage;
    }
}
