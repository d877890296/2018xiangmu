package com.xfkc.caimai.home.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.dev.customview.MyGridView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.home.adapter.MyGridAdapter;
import com.xfkc.caimai.order.OrderActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 社员中心
 */
public class SocialCentreFragment extends BaseFragment {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.account_iv)
    CircleImageView accountIv;
    @Bind(R.id.setting_iv)
    ImageView settingIv;
    @Bind(R.id.mine_name)
    TextView mineName;
    @Bind(R.id.mine_id)
    TextView mineId;
    @Bind(R.id.surplus_days)
    TextView surplusDays;
    @Bind(R.id.years_vip)
    TextView yearsVip;
    @Bind(R.id.kb_number)
    TextView kbNumber;
    @Bind(R.id.kb_surplus)
    TextView kbSurplus;
    @Bind(R.id.account_layout)
    LinearLayout accountLayout;
    @Bind(R.id.order_tv)
    SuperTextView orderTv;
    @Bind(R.id.wait_pay_tv)
    TextView waitPayTv;
    @Bind(R.id.wait_goods_number_tv)
    TextView waitGoodsNumberTv;
    @Bind(R.id.psz_tv)
    TextView pszTv;
    @Bind(R.id.dsh_number_tv)
    TextView dshNumberTv;
    @Bind(R.id.dpj_tv)
    TextView dpjTv;
    @Bind(R.id.dpj_number_tv)
    TextView dpjNumberTv;
    @Bind(R.id.tk_sh_tv)
    TextView tkShTv;
    @Bind(R.id.tksh_number_tv)
    TextView tkshNumberTv;
    @Bind(R.id.gridview)
    MyGridView gridview;

    private MyGridAdapter myGridAdapter;
    private ArrayList<EmptyBean> button_listInfo = new ArrayList();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_socialcentre;
    }

    @Override
    protected void initData() {
        toolbarTitle.setTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.parseColor("#d51628"));
        toolbarTitle.setText("社员中心");

        myGridAdapter = new MyGridAdapter(mContext);
        for (int i = 0; i < 7; i++) {
            button_listInfo.add(new EmptyBean());
        }
        gridview.setAdapter(myGridAdapter);
        setGridClick();
    }

    /*设置更多监听*/
    private void setGridClick() {
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                }
            }
        });
    }


    @OnClick({R.id.account_iv, R.id.setting_iv, R.id.order_tv, R.id.wait_pay_tv, R.id.psz_tv, R.id.dpj_tv, R.id.tk_sh_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_iv:
                break;
            case R.id.setting_iv:
                break;
            case R.id.order_tv:
                startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"0"));
                break;
            case R.id.wait_pay_tv:
                startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"1"));
                break;
            case R.id.psz_tv:
                startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"2"));
                break;
            case R.id.dpj_tv:
                startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"3"));
                break;
            case R.id.tk_sh_tv:
                startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"4"));
                break;
        }
    }
}
