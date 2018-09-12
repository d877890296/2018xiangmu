package com.xfkc.caimai.home.mineinfo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.camera.MyImageCaptureManager;
import com.xfkc.caimai.camera.MyPhotoPickerIntent;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.order.ChooseAddressActivity;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

import static com.xfkc.caimai.config.Constant.REQUEST_CAMERA_CODE;
import static com.xfkc.caimai.config.Constant.REQUEST_PREVIEW_CODE;

/**
 * 社员信息
 */
public class MineInfoActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_title_image)
    ImageView toolbarTitleImage;
    @Bind(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @Bind(R.id.toolbar_right_img)
    ImageView toolbarRightImg;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.account_iv)
    CircleImageView accountIv;
    @Bind(R.id.title_youjiantou)
    ImageView titleYoujiantou;
    @Bind(R.id.person_title_image)
    RelativeLayout personTitleImage;
    @Bind(R.id.person_name)
    SuperTextView personName;
    @Bind(R.id.real_name)
    SuperTextView real_name_tv;
    @Bind(R.id.phone)
    SuperTextView phone;
    @Bind(R.id.person_regist_address)
    SuperTextView personRegistAddress;
    @Bind(R.id.get_goods_address)
    SuperTextView getGoodsAddress;

    private ShowPersonDialog personDialog;
    private String nickName, imageUrl, detailAdress;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("社员信息");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarRightText.setText("保存");
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        imageUrl = getIntent().getStringExtra("imageUrl");
        nickName = getIntent().getStringExtra("nickName");
        String user_phone = getIntent().getStringExtra("phone");
        detailAdress = getIntent().getStringExtra("detailAdress");

        Glide.with(this).load(imageUrl).error(R.mipmap.heart_icon).into(accountIv);
        personName.setRightString(nickName);
        phone.setRightString(user_phone);
        personRegistAddress.setRightString(detailAdress);
        getGoodsAddress.setRightString(detailAdress);
        personDialog = new ShowPersonDialog(this);
        personDialog.setActivity(this);
    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.account_iv,
            R.id.person_title_image, R.id.person_name, R.id.real_name, R.id.phone,
            R.id.person_regist_address, R.id.get_goods_address, R.id.toolbar_right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
//            case R.id.account_iv://头像
//                break;
            case R.id.person_title_image://头像
                personDialog.showAddImageDialog();
                break;
            case R.id.person_name:
                personDialog.updateName(personName.getRightString());
                break;
            case R.id.real_name:
                break;
            case R.id.phone:
                break;
            case R.id.person_regist_address:
                break;
            case R.id.get_goods_address:
                skip_classView(ChooseAddressActivity.class, extraMap, false);
                break;
            case R.id.toolbar_right_text:
                ToastUtil.showToast("保存成功");
                backHistory(101, true, true, extraMap);
                break;
        }
    }

    /*设置昵称*/
    public void setUpdate(String updateName) {
        personName.setRightString(updateName);
        nickName = updateName;
        PayFactory.getPayService()
                .updateUserNicName(token, nickName)
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new Subscriber<EmptyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(EmptyBean emptyBean) {

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 拍照
                case MyImageCaptureManager.REQUEST_TAKE_PHOTO:
                    if (captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        // 照片地址
                        String imagePath = captureManager.getCurrentPhotoPath();
                        // ...
                        accountIv.setImageBitmap(BitmapFactory.decodeFile(imagePath));
//                        upLoadPic(imagePath);
                    }
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
            }
        }
    }

    private void refreshAdpater(ArrayList<String> paths) {
        // 处理返回照片地址
        Log.e("url----------------", paths.get(0));
        accountIv.setImageBitmap(BitmapFactory.decodeFile(paths.get(0)));
//        upLoadPic(paths.get(0));
    }

    /*拍照*/
    private MyImageCaptureManager captureManager;

    public void takeCarema() {
        try {
            if (captureManager == null) {
                captureManager = new MyImageCaptureManager(this);
            }
            Intent intent = captureManager.dispatchTakePictureIntent();
            startActivityForResult(intent, MyImageCaptureManager.REQUEST_TAKE_PHOTO);
        } catch (IOException e) {
            Toast.makeText(this, R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /*相册选择  多选*/
    ArrayList<String> imagePaths = new ArrayList<>();

    public void chooseImages() {
//        ImageConfig config = new ImageConfig();
//        config.minHeight = 400;
//        config.minWidth = 400;
//        config.mimeType = new String[]{"image/jpeg", "image/png"}; // 图片类型 image/gif ...
//        config.minSize = 1 * 1024 * 1024; // 1Mb 图片大小
        MyPhotoPickerIntent intent = new MyPhotoPickerIntent(this);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCarema(true); // 是否显示拍照， 默认false
        intent.setMaxTotal(9); // 最多选择照片数量，默认为9
        intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
//        intent.setImageConfig(config);
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
    }

//    public void upLoadPic(String path) {
//        Map<String, String> params = new HashMap<>();
//        params.put("token", token);
//        Map<String, File> fileMap = new HashMap<>();
//        Log.e("-------------", new File(path).getName() + "======");
//        fileMap.put("imgfile", new File(path));
//
//        PayFactory
//                .getFileService(new ProgressListener() {
//                    @Override
//                    public void onProgress(long currentSize, long totalSize, boolean isFinish) {
//                        int progress = (int) (100D * currentSize / totalSize);
//
//                    }
//                }).saveModia(MultipartUtil.getRequestBodyMap(params, fileMap))
//                .compose(RxHelper.<UpImageBean>io_main())
//                .subscribe(new ProgressSubscriber<UpImageBean>(mContext) {
//                    @Override
//                    public void onNext(UpImageBean upImageBean) {
//                        Log.e("=============", "图片上传成功");
//                        updateUserId.headFileId = upImageBean.mediaId;
//                        updateUserInfo();
//                        Glide.with(PersonInfoActivity.this).load(upImageBean.url).error(R.mipmap.mine_default).into(accountIv);
//                    }
//                });
//    }

}
