package com.xfkc.caimai.home.mineprofit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.ProfitListBean;
import com.xfkc.caimai.util.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.收益 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class ProfitListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<ProfitListBean.DataBean.ListBean> list;


    public ProfitListAdapter(Context context) {
        this.context = context;
    }

    /*设置数据*/
    public void setData(ArrayList<ProfitListBean.DataBean.ListBean> list) {
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
            convertView = View.inflate(context, R.layout.profit_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }

        ProfitListBean.DataBean.ListBean listBean = list.get(position);

        viewHodler.title.setText(listBean.tradeComment);
        viewHodler.time.setText(Utils.timeStamp2Date(listBean.createTime, "yyyy-MM-dd"));
        if (listBean.payType==1){
            viewHodler.addPrice.setText("+" + listBean.tradeMoneyStr);
        }else if (listBean.payType==2){
            viewHodler.addPrice.setText("-" + listBean.tradeMoneyStr);
        }
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.add_price)
        TextView addPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
