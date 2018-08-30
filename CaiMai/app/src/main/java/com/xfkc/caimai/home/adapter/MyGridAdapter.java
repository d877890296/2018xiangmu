package com.xfkc.caimai.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyf.tdlibrary.utils.AdapterUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.EmptyBean;

import java.util.ArrayList;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class MyGridAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<EmptyBean> list;

    private String[] bt_name ={"康币钱包","我的会员卡","我的收益","我加入的店铺","我发布的服务","我提供的服务"};
    private int[] bt_icons={R.mipmap.mine_wallet_icon,R.mipmap.mine_vipcard_icon,
            R.mipmap.mine_sy_icon,R.mipmap.mine_jrdp_icon,R.mipmap.mine_fbfw_icon,
            R.mipmap.mine_tgfu_icon,};

    public MyGridAdapter(Context context) {

        this.context = context;
    }


    @Override
    public int getCount() {
        return bt_name.length;
    }

    @Override
    public Object getItem(int position) {
        return bt_name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHodler viewHodler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.grid_item, null);
            viewHodler = new MyViewHodler();
            viewHodler.imgIcon= (ImageView) convertView.findViewById(R.id.img_icon);
            viewHodler.tvIconTitle= (TextView) convertView.findViewById(R.id.tv_icon_title);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (MyViewHodler) convertView.getTag();
        }


        viewHodler.tvIconTitle.setText(bt_name[position]);
        Glide.with(context).load(bt_icons[position])
                .placeholder(R.mipmap.ic_launcher)//占位符
                .error(R.mipmap.ic_launcher)//加载错误时
                .into(viewHodler.imgIcon);

        return convertView;
    }

    private class MyViewHodler extends AdapterUtil.ViewHolder {
        ImageView imgIcon;
        TextView tvIconTitle;

    }


}
