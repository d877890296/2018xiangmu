package com.xfkc.caimai.home.vipcard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.MineVipCardBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的会员卡
 */
public class MineVipCardActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.listview)
    CustomListView listview;

    private MineVipCardListAdapter mineVipCardListAdapter;
    private ArrayList<MineVipCardBean.DataBean> list = new ArrayList<>();

    private String token;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_mine_vip_card;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("我的会员卡");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        mineVipCardListAdapter = new MineVipCardListAdapter(this);
        mineVipCardListAdapter.setData(list);
        listview.setAdapter(mineVipCardListAdapter);
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);

        setClick();
    }

    /*设置list监听*/
    private void setClick() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MineVipCardBean.DataBean listBean = list.get(position - 1);
                extraMap.put("carTypeId", listBean.carTypeId);
                extraMap.put("cardTypeName", listBean.cardName+"");
                extraMap.put("remainDays", listBean.remainDays + "");
                skip_classView(MineVipContentActivity.class, extraMap, false);
            }
        });
    }

    @Override
    protected void loadData() {

        PayFactory.getPayService()
                .getUserVipCard(token)
                .compose(RxHelper.<MineVipCardBean>io_main())
                .subscribe(new ProgressSubscriber<MineVipCardBean>(this) {
                    @Override
                    public void onNext(MineVipCardBean mineVipCardBean) {
                        if (mineVipCardBean.data != null && mineVipCardBean.data.size() != 0) {
                            list.addAll(mineVipCardBean.data);
                            mineVipCardListAdapter.setData(list);
                        }
                    }
                });
    }

    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }
}
