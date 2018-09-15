package com.xfkc.caimai.home.wallet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;

import butterknife.Bind;
import butterknife.OnClick;

public class EditPayNumActivity extends BaseActivity {


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
    @Bind(R.id.pay_et)
    EditText payEt;
    @Bind(R.id.commit)
    StateButton commit;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_editpay_num;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("账户充值");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.commit:
                String paynumber = payEt.getText().toString();
                if (Tools.IsEmpty(paynumber)) {
                    paynumber = "0";
                }
                extraMap.put("paynum", paynumber);
                backHistory(1008, true, true, extraMap);
                break;
        }
    }
}
