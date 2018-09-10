package com.yang.iread.view.fragment.mine;

import android.view.View;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.socks.library.KLog;
import com.yang.iread.R;
import com.yang.iread.base.BaseContract;
import com.yang.iread.base.BaseFragment;
import com.yang.iread.util.URLUtils;

import butterknife.OnClick;

public class MineFragment extends BaseFragment {
    @Override
    public int loadLayout() {
        return R.layout.activity_mine;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        KLog.d("MinePresenter");
        return null;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void loadData() {

    }

    @OnClick(R.id.btn_download)
    void downLoad() {
        AllenVersionChecker.getInstance()
                .downloadOnly(
                        UIData.create()
                                .setDownloadUrl(URLUtils.WEIXIN_URL)
                                .setContent("修复一些bug")
                                .setTitle("发现新版本")
                ).setShowNotification(true)
                .setShowDownloadingDialog(false)
                .excuteMission(getActivity());

    }
}
