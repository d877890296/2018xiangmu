package com.xfkc.caimai.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.BigLectureBean;
import com.xfkc.caimai.util.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.大讲堂 列表展示类
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class BigListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<BigLectureBean.DataBean> list;


    public BigListAdapter(Context context) {
        this.context = context;
    }

    /*设置数据*/
    public void setData(ArrayList<BigLectureBean.DataBean> list) {
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
            convertView = View.inflate(context, R.layout.big_list_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }

        BigLectureBean.DataBean dataBean = list.get(position);
        viewHodler.titleTv.setText(dataBean.name);
        viewHodler.timeTv.setText(Utils.timeStamp2Date(dataBean.createTime, "yyyy-MM-dd"));
        Glide.with(context).load(dataBean.image)
                .placeholder(R.mipmap.error_icon)//占位符
                .error(R.mipmap.error_icon)//加载错误时
                .into(viewHodler.imgIcon);
        Glide.with(context).load(R.mipmap.nocollect_btn)
                .placeholder(R.mipmap.nocollect_btn)//占位符
                .error(R.mipmap.nocollect_btn)//加载错误时
                .into(viewHodler.heartIv);

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.title_tv)
        TextView titleTv;
        @Bind(R.id.time_tv)
        TextView timeTv;
        @Bind(R.id.heart_iv)
        ImageView heartIv;
        @Bind(R.id.img_icon)
        ImageView imgIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
