package com.yang.iread.view.home;

import com.yang.iread.base.BaseContract;

/**
 * Description:
 * Data: 18-9-6 下午1:45
 *
 * @author: yang
 */
public interface HomeContract {
    interface View extends BaseContract.View {
        void handleHomeData(String data);
    }

    interface Presenter<T> extends BaseContract.Presenter<T> {
        void getHomaData();
    }
}
