package com.xfkc.caimai.home.fragment;


import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;

import butterknife.Bind;

/**
 * 社员中心
 */
public class SocialCentreFragment extends BaseFragment {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_socialcentre;
    }

    @Override
    protected void initData() {
        toolbarTitle.setTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.parseColor("#d51628"));
        toolbarTitle.setText("社员中心");
    }

}
