package com.xfkc.caimai.home.recruitmenthall;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.RecruiHallBean;
import com.xfkc.caimai.customview.StateButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.招募大厅 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class RecruHallListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<RecruiHallBean.DataBean.ListBean> list;


    public RecruHallListAdapter(Context context) {
        this.context = context;
    }

    /*设置数据*/
    public void setData(ArrayList<RecruiHallBean.DataBean.ListBean> list) {
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
            convertView = View.inflate(context, R.layout.recruing_hall_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }

        RecruiHallBean.DataBean.ListBean listBean = list.get(position);

        viewHodler.dianpuTitle.setText(listBean.shopName);
        for (int i=0;i<listBean.inrecruiList.size();i++){
            if (i==0){
                viewHodler.aType.setText(listBean.inrecruiList.get(i).partnerType+"类事业合伙人:"+listBean.inrecruiList.get(i).joinPersonNumber+"(人)/"+listBean.inrecruiList.get(i).personNumber);
            }else if (i==1){
                viewHodler.bType.setText(listBean.inrecruiList.get(i).partnerType+"类事业合伙人:"+listBean.inrecruiList.get(i).joinPersonNumber+"(人)/"+listBean.inrecruiList.get(i).personNumber);
            }else if (i==2){
                viewHodler.cType.setText(listBean.inrecruiList.get(i).partnerType+"类事业合伙人:"+listBean.inrecruiList.get(i).joinPersonNumber+"(人)/"+listBean.inrecruiList.get(i).personNumber);
            }
        }
        viewHodler.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,RecruContentActivity.class));
            }
        });

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.dianpu_title)
        TextView dianpuTitle;
        @Bind(R.id.a_type)
        TextView aType;
        @Bind(R.id.b_type)
        TextView bType;
        @Bind(R.id.c_type)
        TextView cType;
        @Bind(R.id.pay_btn)
        StateButton payBtn;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
