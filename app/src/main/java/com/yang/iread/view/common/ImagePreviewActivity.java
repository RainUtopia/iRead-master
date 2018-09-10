package com.yang.iread.view.common;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.yang.iread.R;
import com.yang.iread.base.BaseActivity;
import com.yang.iread.base.BaseContract;

import butterknife.BindView;

/**
 * Created by jianhong on 2018/9/10.
 */

public class ImagePreviewActivity extends BaseActivity {
    @BindView(R.id.iv_img_preview)
    ImageView mIvImgPreview;

    @BindView(R.id.photo_view)
    PhotoView mPhotoView;

    private PhotoViewAttacher mAttacher;

    private String mImageUrl;


    @Override
    public int loadLayout() {
        return R.layout.activity_image_preview;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public void initView() {
        setTitle( "" );
    }

    @Override
    public void loadData() {
        mImageUrl = getIntent().getStringExtra( "select_img_url" );
        if (TextUtils.isEmpty( mImageUrl )) {
            finish();
        }
        /*RequestOptions options = new RequestOptions().
                centerCrop()
                .placeholder( R.drawable.img_loading_bg )
                .error( R.drawable.img_loading_bg )
                .priority( Priority.HIGH )
                .fitCenter();*/

        Glide.with( this ).asBitmap().load( mImageUrl ).into( new SimpleTarget<Bitmap>() {

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                //mIvImgPreview.setImageBitmap( resource );

                mAttacher = new PhotoViewAttacher( mPhotoView );
                mPhotoView.setImageBitmap( resource );
                mAttacher.update();
            }
        } );

    }
}
