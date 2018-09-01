package com.xfkc.caimai.home.wallet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.pay.SettingPayPasswordActivity;
import com.xfkc.caimai.pay.UpdatePayPasswordActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 康币钱包
 */
public class WalletActivity extends BaseActivity {


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
    @Bind(R.id.kbye_tv)
    TextView kbyeTv;
    @Bind(R.id.djb_tv)
    TextView djbTv;
    @Bind(R.id.zhcz_text)
    SuperTextView zhczText;
    @Bind(R.id.szmx_text)
    SuperTextView szmxText;
    @Bind(R.id.update_paypassword_tv)
    SuperTextView updatePaypasswordTv;

    private String payPwd = "", kbAmount = "";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("康币钱包");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        kbAmount = getIntent().getStringExtra("kbAmount");
        payPwd = getIntent().getStringExtra("payPwd");

        kbyeTv.setText(kbAmount+"");
    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.zhcz_text, R.id.szmx_text, R.id.update_paypassword_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.zhcz_text://账户充值
                skip_classView(AccountRechargeActivity.class, extraMap, false);
                break;
            case R.id.szmx_text://收支明细

                break;
            case R.id.update_paypassword_tv://修改支付密码
                if (Tools.IsEmpty(payPwd)) {
                    skip_classView(SettingPayPasswordActivity.class, extraMap, false);
                } else {
                    skip_classView(UpdatePayPasswordActivity.class, extraMap, false);
                }
                break;
        }
    }

}
