package com.xfkc.caimai.home.comment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/***
 * 发表评论
 */
public class ToCommentActivity extends BaseActivity {


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
    @Bind(R.id.tocomment_et)
    EditText tocommentEt;
    @Bind(R.id.add_image)
    ImageView addImage;
    @Bind(R.id.tocomment_commit)
    TextView tocommentCommit;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_to_comment;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("发表评论");

        toolbarLeftImg.setImageResource(R.mipmap.back_white);
    }

    @Override
    protected void loadData() {

    }



    @OnClick({R.id.toolbar_left_img, R.id.add_image, R.id.tocomment_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
               break;
            case R.id.add_image:
                ToastUtil.showToast("暂未开放");
                break;
            case R.id.tocomment_commit:
                String content=tocommentEt.getText().toString();
                if (Tools.IsEmpty(content)){
                    ToastUtil.showToast("请填写您的意见!");
                }else {
                   showMbProgress("正在提交");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);//休眠3秒
                                dissMbProgress();
                                finish();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                }
                break;
        }
    }
}
