package com.xfkc.caimai.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.xfkc.caimai.order.SelectFragment;


/**
 * 1.优品 商品列表 Viewpager+fragment 适配器
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class SelectFragmentPagerAdapter extends FragmentPagerAdapter {


    private final String[] order_staus;

    public SelectFragmentPagerAdapter(FragmentManager fm, String[] order_staus) {
        super(fm);
        this.order_staus = order_staus;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int arg0) {
        SelectFragment selectFragment=new SelectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("catId",order_staus[arg0]);
        selectFragment.setArguments(bundle);
        return selectFragment;//显示第几个页面
    }

    @Override
    public int getCount() {
        return order_staus.length;//有几个页面
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

}
