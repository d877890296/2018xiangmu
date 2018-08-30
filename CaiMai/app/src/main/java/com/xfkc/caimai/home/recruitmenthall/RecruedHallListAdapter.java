package com.xfkc.caimai.home.recruitmenthall;

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
 * 1.招募大厅 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class RecruedHallListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<EmptyBean> list;


    public RecruedHallListAdapter(Context context) {
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
            convertView = View.inflate(context, R.layout.recruing_hall_item02, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.dianpu_title)
        TextView dianpuTitle;
        @Bind(R.id.recru_status)
        TextView recruStatus;
        @Bind(R.id.a_type)
        TextView aType;
        @Bind(R.id.a_type_des)
        TextView aTypeDes;
        @Bind(R.id.b_type)
        TextView bType;
        @Bind(R.id.b_type_des)
        TextView bTypeDes;
        @Bind(R.id.c_type)
        TextView cType;
        @Bind(R.id.c_type_des)
        TextView cTypeDes;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
