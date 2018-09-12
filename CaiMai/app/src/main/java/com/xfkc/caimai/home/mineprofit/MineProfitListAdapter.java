package com.xfkc.caimai.home.mineprofit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.EmptyBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.我的收益 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class MineProfitListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<EmptyBean> list;
    private double huiyuanCount;
    private double shangchengCount;


    public MineProfitListAdapter(Context context) {
        this.context = context;
    }

    /*设置数据*/
    public void setData(ArrayList<EmptyBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setCount(double huiyuanCount, double shangchengCount) {
        this.huiyuanCount = huiyuanCount;
        this.shangchengCount = shangchengCount;
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
            convertView = View.inflate(context, R.layout.mineprofit_list_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            viewHodler.mineprofitName.setText("情怀链带来的收益");
            viewHodler.mineProfitPrice.setText(huiyuanCount + "康币");
        } else if (position == 1) {
            viewHodler.mineprofitName.setText("幸福公社带来的收益");
            viewHodler.mineProfitPrice.setText(shangchengCount + "康币");
        }


        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.mineprofit_name)
        TextView mineprofitName;
        @Bind(R.id.mine_profit_price)
        TextView mineProfitPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
