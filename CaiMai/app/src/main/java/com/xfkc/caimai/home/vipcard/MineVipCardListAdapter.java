package com.xfkc.caimai.home.vipcard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.EmptyBean;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.我的会员卡 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class MineVipCardListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<EmptyBean> list;


    public MineVipCardListAdapter(Context context) {
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
            convertView = View.inflate(context, R.layout.minevip_card_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }


        switch (position) {
            case 0:
                viewHodler.days.setText("剩余有效期: 365天");
                viewHodler.title.setText("初级会员");
                viewHodler.price.setText("");
                viewHodler.imageBg.setBackgroundResource(R.mipmap.vip01);
                break;
            case 1:
                viewHodler.days.setText("剩余有效期: 365天");
                viewHodler.title.setText("中级会员");
                viewHodler.price.setText("");
                viewHodler.imageBg.setBackgroundResource(R.mipmap.vip03);
                break;
            case 2:
                viewHodler.days.setText("剩余有效期: 365天");
                viewHodler.title.setText("高级会员");
                viewHodler.price.setText("");
                viewHodler.imageBg.setBackgroundResource(R.mipmap.vip02);
                break;
        }


        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.price)
        TextView price;
        @Bind(R.id.days)
        TextView days;
        @Bind(R.id.vip_qy)
        TextView vipQy;
        @Bind(R.id.image_bg)
        AutoLinearLayout imageBg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
