package com.yang.iread.view.home;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.yang.iread.R;
import com.yang.iread.base.BaseActivity;
import com.yang.iread.view.fragment.discovery.DiscoveryFragment;
import com.yang.iread.view.fragment.mine.MineFragment;
import com.yang.iread.view.fragment.sort.SortFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Data: 18-9-6 上午11:36
 *
 * @author: yang
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    @BindView(R.id.fl_container)
    FrameLayout mFrameLayout;

    @BindView(android.R.id.tabhost)
    FragmentTabHost mFragmentTabHost;

    private List<Tab> mTabs;

    @Override
    public int loadLayout() {
        return R.layout.activity_home;
    }

    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void initView() {
        initTabHost();
    }

    @Override
    public void loadData() {
        mPresenter.getHomaData();
    }

    @Override
    public void handleHomeData(String data) {
        showToast(data);
    }

    private void initTabHost() {
        mTabs = new ArrayList<>();
        mTabs.add(new Tab(R.drawable.tab_icon_selector_discovery, R.string.discovery, DiscoveryFragment.class));
        mTabs.add(new Tab(R.drawable.tab_icon_selector_sort, R.string.sort, SortFragment.class));
        mTabs.add(new Tab(R.drawable.tab_icon_selector_mine, R.string.mine, MineFragment.class));

        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.fl_container);
        mFragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);

        for (Tab tab : mTabs) {
            View view = LayoutInflater.from(this).inflate(R.layout.main_tab, null);
            ((ImageView) view.findViewById(R.id.iv_icon)).setImageResource(tab.getIcon());
            ((TextView) view.findViewById(R.id.tv_title)).setText(getString(tab.getTitle()));

            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(view);

            mFragmentTabHost.addTab(tabSpec, tab.getFragment(), null);
        }

        mFragmentTabHost.setCurrentTab(0);

        /*mFragmentTabHost.getTabWidget().getChildAt( 1 ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity( TestActivity.class );
            }
        } );*/
    }

    class Tab {
        private int icon;
        private int title;
        private Class fragment;

        public Tab(int icon, int title, Class fragment) {
            this.icon = icon;
            this.title = title;
            this.fragment = fragment;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public int getTitle() {
            return title;
        }

        public void setTitle(int title) {
            this.title = title;
        }

        public Class getFragment() {
            return fragment;
        }

        public void setFragment(Class fragment) {
            this.fragment = fragment;
        }
    }
}
