package com.xfkc.caimai.info;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 完善信息
 */
public class PerfectInforActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.id_card)
    EditText idCard;
    @Bind(R.id.choose_address)
    SuperTextView chooseAddress;
    @Bind(R.id.address_content)
    EditText addressContent;
    @Bind(R.id.commit)
    StateButton commit;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_perfectinfor;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("完善信息");
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.toolbar_left_img, R.id.choose_address, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.choose_address:

                break;
            case R.id.commit:

                break;
        }
    }
}
