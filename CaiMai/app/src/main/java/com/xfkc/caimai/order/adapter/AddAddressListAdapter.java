package com.xfkc.caimai.order.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.AddressBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.地址信息展示 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class AddAddressListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<AddressBean.DataBean> list;


    public AddAddressListAdapter(Context context) {
        this.context = context;
    }

    /*设置数据*/
    public void setData(ArrayList<AddressBean.DataBean> list) {
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
            convertView = View.inflate(context, R.layout.address_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }

        AddressBean.DataBean dataBean = list.get(position);

        viewHodler.name.setText(dataBean.receiveName);
        viewHodler.phone.setText(dataBean.phone);
        viewHodler.addressContent.setText(dataBean.detailAdress);

        if (dataBean.acquiesce == 1){
            viewHodler.status.setVisibility(View.VISIBLE);
        }else {
            viewHodler.status.setVisibility(View.GONE);
        }

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.phone)
        TextView phone;
        @Bind(R.id.status)
        TextView status;
        @Bind(R.id.address_content)
        TextView addressContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
