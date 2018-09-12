package com.xfkc.caimai.web.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfkc.caimai.R;

import butterknife.ButterKnife;

/**
 * Created by LK on 2016/3/7 15:08
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected TextView toolbar_title;
    protected TextView toolbar_right_txt;
    protected ImageView toolbar_right_img;
    protected View.OnClickListener listener;
    //只有返回按钮
    public static final int MODE_BACK = 0;
    //没有标题
    public static final int MODE_NONE = 1;
    //全部都有
    public static final int MODE_ALL = 2;
    public Context mContext;
    private boolean invisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setUpContentView();
        ButterKnife.bind(this);
        initViews(savedInstanceState);
        loadData();
    }

    protected abstract void setUpContentView();

    //没有标题栏
    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, -1, -1, -1, MODE_NONE);
    }

    public void setContentView(int layoutResId, int titleResId, boolean invisible) {
        this.invisible = invisible;
        setContentView(layoutResId, titleResId, -1, -1, MODE_BACK);
    }

    //有标题栏和返回键
    public void setContentView(int layoutResID, int titleResId) {
        setContentView(layoutResID, titleResId, -1, -1, MODE_BACK);
    }

    public void setContentView(int layoutResId, int titleResId,
                               int rightTextResId, int rightImgResId) {
        setContentView(layoutResId, titleResId, rightImgResId, rightTextResId, MODE_ALL);
    }

    public void setContentView(int layoutResID, int titleResId,
                               int imgResId, int rightTextResId, int mode) {
        super.setContentView(layoutResID);
        if (mode == MODE_ALL || mode == MODE_BACK) {
            setUpToolbar(titleResId, imgResId, rightTextResId);
        }
    }

    private void setUpToolbar(int titleResId, int imgResId, int rightTextResId) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_right_img = (ImageView) findViewById(R.id.toolbar_right_img);
        toolbar_right_txt = (TextView) findViewById(R.id.toolbar_right_text);
        toolbar.setTitle("");
        toolbar_title.setTextColor(Color.BLACK);
        //返回箭头是否显示
        if (!invisible) {
            toolbar.setNavigationIcon(R.mipmap.back_white);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationBtnClicked();
                }
            });
        }
        setUpTitle(titleResId);
        setUpRightImg(imgResId);
        setUpRightText(rightTextResId);
    }

    private void setUpRightText(int rightTextResId) {
        if (toolbar_right_txt != null && rightTextResId != -1) {
            toolbar_right_txt.setText(rightTextResId);
            toolbar_right_txt.setOnClickListener(listener);
        }
    }

    private void setUpRightImg(int imgResId) {
        if (toolbar_right_img != null && imgResId != -1) {
            toolbar_right_img.setImageResource(imgResId);
            toolbar_right_img.setOnClickListener(listener);
        }
    }

    private void setUpTitle(int titleResId) {
        if (titleResId > 0 && toolbar_title != null) {
            toolbar_title.setText(titleResId);
        }
    }

    protected void onNavigationBtnClicked() {
        finish();
    }

    /**
     * 初始化控件，设置控件事件
     *
     * @param savedInstanceState
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * 调用api获取数据
     */
    protected abstract void loadData();


    protected String getName() {
        return BaseActivity.class.getName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
