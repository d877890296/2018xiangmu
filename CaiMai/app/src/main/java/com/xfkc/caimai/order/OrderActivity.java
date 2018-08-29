package com.xfkc.caimai.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.order.adapter.SelectFragmentPagerAdapter;
import com.xfkc.caimai.rx.activity.RxActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;



/***
 * 订单列表
 */
public class OrderActivity extends RxActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    //设置订单类型
    private String[] order = {"全部", "待支付", "待发货", "配送中", "待评价"};
    //订单类型  编号
    private String[] order_staus = {"0", "1", "2", "3", "4"};

    private ArrayList<EmptyBean> mDataList;
    private SelectFragmentPagerAdapter selectFragmentPagerAdapter;
    private CommonNavigator mCommonNavigator;
    private String category_id;//请求模块id


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        toolbarTitle.setText("我的订单");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        mDataList = new ArrayList<>();
        category_id = getIntent().getStringExtra(Constant.CATEGORY_ID);

        //设置加载更多
        setLoadMore();
        setMagIcindicator();
    }


    @Override
    protected void loadData() {

    }


    /*设置顶部导航*/
    private void setMagIcindicator() {
        selectFragmentPagerAdapter = new SelectFragmentPagerAdapter(getSupportFragmentManager(), order_staus);
        viewPager.setAdapter(selectFragmentPagerAdapter);

        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setAdjustMode(false);
        mCommonNavigator.setSkimOver(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return order.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(order[index]);
                clipPagerTitleView.setTextColor(Color.BLACK);
                clipPagerTitleView.setClipColor(Color.parseColor("#ff704d"));
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(mCommonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);

        for (int i = 0; i < order.length; i++) {
            if (category_id.equals(order_staus[i])) {
                viewPager.setCurrentItem(i);
            }
        }
    }

    /*请求刷新数据*/
    public void questData(int position) {


    }


    @OnClick({R.id.toolbar_left_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103 && resultCode == 104) {

        }
    }


    /*加载更多*/
    private void setLoadMore() {

    }

    /*停止刷新  加载更多*/
    public void stopMore() {

    }

}
