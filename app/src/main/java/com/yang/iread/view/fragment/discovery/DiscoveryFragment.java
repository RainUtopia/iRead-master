package com.yang.iread.view.fragment.discovery;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yang.iread.R;
import com.yang.iread.base.BaseFragment;
import com.yang.iread.result.Image;
import com.yang.iread.view.common.ImagePreviewActivity;

import java.util.List;

import butterknife.BindView;

public class DiscoveryFragment extends BaseFragment<DiscoveryPresenter> implements DiscoveryContract.View {

    @BindView(R.id.srf_images)
    SwipeRefreshLayout mSrfImages;

    @BindView(R.id.rv_images)
    RecyclerView mRvImages;

    private ImagesRvAdapter mAdapter;
    private List<Image> images;

    @Override
    public int loadLayout() {
        return R.layout.activity_discovery;
    }

    @Override
    public DiscoveryPresenter getPresenter() {
        return new DiscoveryPresenter();
    }

    @Override
    public void initView(View view) {
        initRecyclerview();

        mSrfImages.setColorSchemeColors(ContextCompat.getColor(getActivity(),R.color.mainColor));
        mSrfImages.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getRecommand();
                mAdapter.setEnableLoadMore(false);
            }
        });
    }

    @Override
    public void loadData() {
        mPresenter.getRecommand();
    }

    @Override
    public void handleRecommandData(List<Image> images) {
        this.images = images;
        mAdapter.setNewData(images);
        mSrfImages.setRefreshing(false);
        mAdapter.setEnableLoadMore(true);
        mPresenter.setDefaultPage(0);
    }

    @Override
    public void handleLoadMoreData(List<Image> images, boolean isLoadMoreEnd) {
        if(isLoadMoreEnd) {
            mAdapter.loadMoreEnd();
            //mAdapter.setEnableLoadMore(false);
            return;
        }
        mAdapter.addData(images);
        mAdapter.loadMoreComplete();
    }

    private void initRecyclerview() {
        mAdapter = new ImagesRvAdapter(R.layout.item_image);
        mRvImages.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRvImages.setAdapter(mAdapter);

        //mAdapter.setEnableLoadMore(true);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ImagePreviewActivity.class);
                intent.putExtra("select_img_url",images.get(position).getUrl());
                startActivity(intent);
            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        },mRvImages);
    }
}
