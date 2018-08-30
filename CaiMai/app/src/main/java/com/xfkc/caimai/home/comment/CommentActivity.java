package com.xfkc.caimai.home.comment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 评论
 */
public class CommentActivity extends BaseActivity {

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
    @Bind(R.id.listview)
    CustomListView listview;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("评论");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }
}
