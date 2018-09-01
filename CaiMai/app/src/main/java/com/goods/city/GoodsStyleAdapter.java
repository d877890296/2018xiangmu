package com.goods.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.LzBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10835 on 2018/8/30.
 */

public class GoodsStyleAdapter extends LzBaseAdapter {


    public List<GoodsListModel> data;


    public  int currentIndex=0;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        this.notifyDataSetChanged();
    }

    public GoodsStyleAdapter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void setData(List<GoodsListModel> data) {
        this.data = data;
        if (this.data==null){
            this.data=new ArrayList<GoodsListModel>();
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder=null;
        if(convertView==null){

            convertView=inflater.inflate(R.layout.goods_style_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        if (currentIndex==position){
            viewHolder.curchooseView.setVisibility(View.VISIBLE);
        }else{
            viewHolder.curchooseView.setVisibility(View.GONE);
        }
        GoodsListModel model=data.get(position);
        viewHolder.styleTitle.setText(model.cname);
        return convertView;
    }

    public  class  ViewHolder{
        View curchooseView;
        TextView styleTitle;
        public    ViewHolder(View view){
            curchooseView=(View)view.findViewById(R.id.curchooseView);
            styleTitle=(TextView)view.findViewById(R.id.styleTitle);

        }
    }

}
