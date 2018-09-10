package com.yang.iread.view.fragment.discovery;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yang.iread.R;
import com.yang.iread.result.Image;

/**
 * @author: jianhong
 * @createDate: 2018/9/10 17:02
 * @description:
 */
public class ImagesRvAdapter extends BaseQuickAdapter<Image,BaseViewHolder> {
    public ImagesRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Image item) {
        Glide.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.iv_img));
    }
}
