package com.yang.iread.view.home;

import com.yang.iread.base.BasePresenter;

/**
 * Description:
 * Data: 18-9-6 下午1:44
 *
 * @author: yang
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View>  {
    @Override
    public void getHomaData() {
        mView.handleHomeData("Hello");
    }
}