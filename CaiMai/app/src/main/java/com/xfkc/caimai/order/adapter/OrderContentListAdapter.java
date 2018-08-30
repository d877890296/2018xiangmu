package com.xfkc.caimai.order.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.EmptyBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.订单详情商品 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class OrderContentListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<EmptyBean> list;


    public OrderContentListAdapter(Context context) {
        this.context = context;
    }

    /*设置数据*/
    public void setData(ArrayList<EmptyBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHodler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.module_shop_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }

        viewHodler.shopTitle.setText("商品" + position);

        Glide.with(context).load(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)//占位符
                .error(R.mipmap.ic_launcher)//加载错误时
                .into(viewHodler.shopImage);

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.shop_image)
        ImageView shopImage;
        @Bind(R.id.shop_title)
        TextView shopTitle;
        @Bind(R.id.shop_content)
        TextView shopContent;
        @Bind(R.id.shop_price)
        TextView shopPrice;
        @Bind(R.id.shop_unit_price)
        TextView shopUnitPrice;
        @Bind(R.id.shop_number)
        TextView shopNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
