package com.yang.iread.view.home;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.yang.iread.R;
import com.yang.iread.base.BaseActivity;
import com.yang.iread.util.URLUtils;

import butterknife.OnClick;

/**
 * Description:
 * Data: 18-9-6 上午11:36
 *
 * @author: yang
 */
public class HomeActivity extends BaseActivity implements HomeContract.View{

    @Override
    public int loadLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void handleHomeData(String data) {

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
                .excuteMission(this);

    }
}
