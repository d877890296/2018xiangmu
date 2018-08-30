package com.goods.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.LzBaseAdapter;

/**
 * Created by 10835 on 2018/8/30.
 */

public class GoodsStyleAdapter extends LzBaseAdapter {



    public GoodsStyleAdapter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 10;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
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

        return convertView;
    }

    public  class  ViewHolder{
        TextView styleTitle;
        public    ViewHolder(View view){
            styleTitle=(TextView)view.findViewById(R.id.styleTitle);

        }
    }

}
