package com.xfkc.caimai.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.FeelingBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 1.情怀链  适配器
 * 2.@dongjinxu
 * 3.@2018/4/13.
 */

public class FeelingAdapter extends BaseAdapter {


    private final int TYPE1 = 0;//首页轮播
    private final int TYPE2 = 1;//其余模块
    private Context context;

    private ArrayList<FeelingBean.DataBean.NextMemUserPageBean.ListBean> moduleList;

    public FeelingAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<FeelingBean.DataBean.NextMemUserPageBean.ListBean> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE1;
        else
            return TYPE2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return moduleList.size();
    }

    @Override
    public Object getItem(int position) {
        return moduleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取
        int type = getItemViewType(position);

        ViewHolder viewHolder = null;

        if (convertView == null) {
                    convertView = View.inflate(context, R.layout.feeling_item, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
        } else {
                    viewHolder = (ViewHolder) convertView.getTag();
        }

        FeelingBean.DataBean.NextMemUserPageBean.ListBean listBean = moduleList.get(position);
        switch (type) {
            case TYPE1://
                viewHolder.kbNumber.setVisibility(View.GONE);
                viewHolder.feelingPeopleNumber.setVisibility(View.GONE);
                Glide.with(context).load(listBean.userImg).error(R.mipmap.heart_icon).into(viewHolder.feelingUserIv);
                viewHolder.feelingUserName.setText(listBean.kcId);
                viewHolder.feelingTime.setText(listBean.createTime+"");
                break;
            case TYPE2:
                Glide.with(context).load(listBean.userImg).error(R.mipmap.heart_icon).into(viewHolder.feelingUserIv);
                viewHolder.feelingUserName.setText(listBean.kcId);
                viewHolder.feelingTime.setText(listBean.createTime+"");
                viewHolder.feelingPeopleNumber.setText("情怀链"+listBean.nextNum+"人");
                viewHolder.kbNumber.setText("贡献康币"+listBean.nextMoney);
                break;
        }

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.feeling_user_iv)
        CircleImageView feelingUserIv;
        @Bind(R.id.feeling_user_name)
        TextView feelingUserName;
        @Bind(R.id.kb_number)
        TextView kbNumber;
        @Bind(R.id.feeling_time)
        TextView feelingTime;
        @Bind(R.id.feeling_people_number)
        TextView feelingPeopleNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
