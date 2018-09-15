package com.xfkc.caimai.home.recruitmenthall;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.RecruiHallBean;

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
    private ArrayList<RecruiHallBean.DataBean.ListBean> list;


    public RecruedHallListAdapter(Context context) {
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
            convertView = View.inflate(context, R.layout.recruing_hall_item02, null);
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
                showType(listBean.inrecruiList.get(i),viewHodler.aTypeDes);
            }else if (i==1){
                viewHodler.bType.setText(listBean.inrecruiList.get(i).partnerType+"类事业合伙人:"+listBean.inrecruiList.get(i).joinPersonNumber+"(人)/"+listBean.inrecruiList.get(i).personNumber);
                showType(listBean.inrecruiList.get(i),viewHodler.bTypeDes);
            }else if (i==2){
                viewHodler.cType.setText(listBean.inrecruiList.get(i).partnerType+"类事业合伙人:"+listBean.inrecruiList.get(i).joinPersonNumber+"(人)/"+listBean.inrecruiList.get(i).personNumber);
                showType(listBean.inrecruiList.get(i),viewHodler.cTypeDes);
            }
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


    private void showType(RecruiHallBean.DataBean.ListBean.InrecruiListBean inrecruiListBean, TextView TypeDes){
        if (inrecruiListBean.type == 0){//按百分比
            switch (inrecruiListBean.welfareUnit){
                case 1://月
                    TypeDes.setText("加入后每个月可获得店铺年营业额" +inrecruiListBean.rate+"%的康币分红");
                    break;
                case 2://季度
                    TypeDes.setText("加入后每个季度可获得店铺年营业额" +inrecruiListBean.rate+"%的康币分红");
                    break;
                case 3://年
                    TypeDes.setText("加入后每年可获得店铺年营业额" +inrecruiListBean.rate+"%的康币分红");
                    break;
            }
        }else if (inrecruiListBean.type == 1){//按定额
            String comKangbi ="0";
            if(!Tools.IsEmpty(inrecruiListBean.comKangbi)){
                comKangbi = inrecruiListBean.comKangbi;
            }
            switch (inrecruiListBean.welfareUnit){
                case 1://月
                    TypeDes.setText("加入后每月可获得"+comKangbi+"个康币分红");
                    break;
                case 2://季度
                    TypeDes.setText("加入后每季度可获得"+comKangbi+"个康币分红");
                    break;
                case 3://年
                    TypeDes.setText("加入后每年可获得"+comKangbi+"个康币分红");
                    break;
            }

        }
    }

}
