package com.yang.iread.view.fragment.discovery;

import com.yang.iread.base.BaseContract;
import com.yang.iread.result.Image;

import java.util.List;

/**
 * @author: jianhong
 * @createDate: 2018/9/10 15:09
 * @description:
 */
public interface DiscoveryContract {
    interface View extends BaseContract.View {
        void handleRecommandData(List<Image> images);

        void handleLoadMoreData(List<Image> images,boolean isLoadMoreEnd);
    }

    interface Presenter<V> extends BaseContract.Presenter<V> {
        void getRecommand();

        void loadMore();
    }
}
