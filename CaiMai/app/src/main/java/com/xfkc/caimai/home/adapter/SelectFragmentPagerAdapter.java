//package com.xfkc.caimai.home.adapter;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.view.ViewGroup;
//
//
//
///**
// * 1.优品 商品列表 Viewpager+fragment 适配器
// * 2.@dongjinxu
// * 3.@2018/4/11.
// */
//
//public class SelectFragmentPagerAdapter extends FragmentPagerAdapter {
//    private ArrayList<SelectOrderCatBean.CateListBean> mlist;
//
//
//    public SelectFragmentPagerAdapter(FragmentManager fm, ArrayList<SelectOrderCatBean.CateListBean> list) {
//        super(fm);
//        this.mlist = list;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public Fragment getItem(int arg0) {
//        SelectFragment selectFragment=new SelectFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("catId",mlist.get(arg0).catId);
//        selectFragment.setArguments(bundle);
//        return selectFragment;//显示第几个页面
//    }
//
//    @Override
//    public int getCount() {
//        return mlist.size();//有几个页面
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//
//    }
//
//}
