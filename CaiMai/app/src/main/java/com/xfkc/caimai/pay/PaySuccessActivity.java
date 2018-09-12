package com.xfkc.caimai.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.goods.mineOrderforgoods.OrderforgoodsActivity;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.home.myjoinshop.MyJoinShopActivity;
import com.xfkc.caimai.home.vipcard.MineVipCardActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class PaySuccessActivity extends BaseActivity {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.commit)
    StateButton commit;
    @Bind(R.id.look_order)
    TextView lookOrder;
    @Bind(R.id.des)
    TextView des;

    private String type;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("支付完成");

        type = getIntent().getStringExtra("type");

        switch (type) {
            case "1"://购买商品成功

                break;
            case "2"://购买会员卡成功
                des.setText("恭喜您已成为幸福社员");
                commit.setText("回到会员卡中心");
                lookOrder.setText("查看我的会员卡");
                break;
            case "3"://充值成功
                commit.setText("回到充值页面");
                lookOrder.setVisibility(View.GONE);
                break;
            case "4"://招募大厅
                des.setText("恭喜您已成为幸福合伙人!");
                commit.setText("回到招募大厅");
                lookOrder.setText("查看我的店铺");
                break;
        }

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.commit, R.id.look_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit:
                switch (type) {
                    case "1"://购买商品成功
                        backHistory(102, true, true, extraMap);
                        break;
                    case "2"://购买会员卡成功
                        finish();
                        break;
                    case "3"://充值成功
                        finish();
                        break;
                    case "4"://招募
                        finish();
                        break;
                }
                break;
            case R.id.look_order:
                switch (type) {
                    case "1"://购买商品成功
                        extraMap.put("baseType", -1);
                        skip_classView(OrderforgoodsActivity.class, extraMap, true);
                        break;
                    case "2"://购买会员卡成功
                        skip_classView(MineVipCardActivity.class, extraMap, true);
                        break;
                    case "3"://充值成功

                        break;
                    case "4"://招募大厅
                        skip_classView(MyJoinShopActivity.class, extraMap, true);
                        break;
                }
                break;
        }
    }

}
