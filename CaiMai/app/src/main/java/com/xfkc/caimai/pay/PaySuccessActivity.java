package com.xfkc.caimai.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;

import butterknife.Bind;
import butterknife.OnClick;

public class PaySuccessActivity extends BaseActivity {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.commit)
    StateButton commit;
    @Bind(R.id.look_order)
    TextView lookOrder;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("支付完成");
    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.commit, R.id.look_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit:
                finish();
                break;
            case R.id.look_order:
                
                break;
        }
    }
}
