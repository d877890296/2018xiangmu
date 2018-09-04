package com.xfkc.caimai.home.vipcard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.VipCardBean;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.会员卡 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class VipCardListAdapter extends BaseAdapter {

    private final Context context;
    private List<VipCardBean.DataBean.ListBean> list;
    private int type;


    public VipCardListAdapter(Context context) {
        this.context = context;
    }

    /*设置数据*/
    public void setData(List<VipCardBean.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setType(int type){
        this.type =type;
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
            convertView = View.inflate(context, R.layout.vip_card_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }


        VipCardBean.DataBean.ListBean listBean = list.get(position);

        if (listBean.carTypeId == type){
            viewHodler.days.setText("有效期: 365天" );
            viewHodler.title.setText(listBean.cardName);
            viewHodler.price.setText("￥" + listBean.cardPrice);
            viewHodler.imageBg.setBackgroundResource(R.mipmap.vip02);
        }


//        switch (position){
//            case 0:
//                viewHodler.days.setText("有效期: 365天" );
//                viewHodler.title.setText("初级会员");
//                viewHodler.price.setText("￥36.5" );
//               viewHodler.imageBg.setBackgroundResource(R.mipmap.vip01);
//                break;
//            case 1:
//                viewHodler.days.setText("有效期: 365天" );
//                viewHodler.title.setText("中级会员");
//                viewHodler.price.setText("￥365" );
//                viewHodler.imageBg.setBackgroundResource(R.mipmap.vip03);
//                break;
//            case 2:
//                viewHodler.days.setText("有效期: 365天" );
//                viewHodler.title.setText("高级会员");
//                viewHodler.price.setText("￥3650" );
//                viewHodler.imageBg.setBackgroundResource(R.mipmap.vip02);
//                break;
//        }


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
