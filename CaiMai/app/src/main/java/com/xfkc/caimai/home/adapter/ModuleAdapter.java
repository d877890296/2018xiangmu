package com.xfkc.caimai.home.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.BannerBean;
import com.xfkc.caimai.customview.ClipViewPager;
import com.xfkc.caimai.customview.ScalePageTransformer;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 1.各模块设置  适配器
 * 2.@dongjinxu
 * 3.@2018/4/13.
 */

public class ModuleAdapter extends BaseAdapter {


    private final int TYPE1 = 0;//首页轮播
    private final int TYPE2 = 1;//其余模块
    private Context context;

    //图片左右可见适配器
    private TubatuAdapter tubatuAdapter;
    private List<String> moduleList;
    //其他模块适配器
//    private OthersModuleAdapter othersModuleAdapter;
    private String[] titls = {"各种会员商品任君挑选", "加入我们成为我们的一员", "购买会员享受会员权益", "暂未开放 敬请期待"};
    private int[] images = {R.mipmap.xingfu, R.mipmap.zhaomudating, R.mipmap.vip_car, R.mipmap.every_8};
    private List<BannerBean.DataBean.ListBean> list_banner;

    public ModuleAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> moduleList, List<BannerBean.DataBean.ListBean> list) {
        this.moduleList = moduleList;
        this.list_banner = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE1;
        else
            return TYPE2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return moduleList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return moduleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取
        int type = getItemViewType(position);

        ViewHolder01 viewHolder01 = null;
        ViewHolder02 viewHolder02 = null;

        if (convertView == null) {
            switch (type) {
                case TYPE1:
                    convertView = View.inflate(context, R.layout.module_item01, null);
                    viewHolder01 = new ViewHolder01(convertView);
                    convertView.setTag(viewHolder01);
                    break;
                case TYPE2:
                    convertView = View.inflate(context, R.layout.module_item02, null);
                    viewHolder02 = new ViewHolder02(convertView);
                    convertView.setTag(viewHolder02);
                    break;
            }
        } else {
            switch (type) {
                case TYPE1:
                    viewHolder01 = (ViewHolder01) convertView.getTag();
                    break;
                case TYPE2:
                    viewHolder02 = (ViewHolder02) convertView.getTag();
                    break;
            }
        }

        switch (type) {
            case TYPE1://
                setClipViewpager(viewHolder01);
                break;
            case TYPE2:
                position = position - 1;
                viewHolder02.title.setText(moduleList.get(position));
                viewHolder02.content.setText(titls[position]);
                viewHolder02.image.setImageResource(images[position]);
                break;
        }

        return convertView;
    }


    /*设置左右滑动图片可见数据*/
    private void setClipInitData(ViewHolder01 viewHolder01) {
//        List<Integer> list = new ArrayList<>();
//        list.add(R.mipmap.ic_launcher);
//        list.add(R.mipmap.ic_launcher);
//        list.add(R.mipmap.ic_launcher);
//        list.add(R.mipmap.ic_launcher);

        //设置OffscreenPageLimit
        viewHolder01.clipViewpager.setOffscreenPageLimit(list_banner.size());
        tubatuAdapter.addAll(list_banner);
    }


    /*设置选择式图片*/
    private void setClipViewpager(final ViewHolder01 viewHolder01) {
        viewHolder01.clipViewpager.setPageTransformer(true, new ScalePageTransformer());

        viewHolder01.pageContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewHolder01.clipViewpager.dispatchTouchEvent(event);
            }
        });

        tubatuAdapter = new TubatuAdapter(context);
        viewHolder01.clipViewpager.setAdapter(tubatuAdapter);
        //设置左右滑动图片可见数据
        setClipInitData(viewHolder01);
    }

    static class ViewHolder01 {
        @Bind(R.id.clip_viewpager)
        ClipViewPager clipViewpager;
        @Bind(R.id.page_container)
        AutoRelativeLayout pageContainer;

        ViewHolder01(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder02 {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.content)
        TextView content;

        ViewHolder02(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
