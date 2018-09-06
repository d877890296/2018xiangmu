package com.goods.city;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.LzBaseAdapter;

import java.util.List;

/**
 * Created by 10835 on 2018/8/30.
 */

public class GoodsStyleGridContentAdapter extends LzBaseAdapter {

    public List<GoodsStyleModel> data;


    public List<GoodsStyleModel> getData() {
        return data;
    }

    public void setData(List<GoodsStyleModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public GoodsStyleGridContentAdapter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.goods_stylegridcontent_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GoodsStyleModel model = data.get(position);
        String ch2 = model.getStyleName();
        if (position > 2) {
            String ch1 = data.get(position - 1).getStyleName();
            if (ch1.equals(ch2)) {
                viewHolder.fristWord.setVisibility(View.GONE);
            } else {
                viewHolder.fristWord.setVisibility(View.VISIBLE);
                viewHolder.fristWord.setText(ch2);
            }
        } else {
            viewHolder.fristWord.setVisibility(View.VISIBLE);
            if (position == 0) {
                viewHolder.fristWord.setText(ch2);
            } else {
                viewHolder.fristWord.setText("");
            }
//            viewHolder.fristWord.setText(ch2);
        }
        viewHolder.testTitle.setText(model.getGoodsName());
        Glide.with(context).load(model.headImg).error(R.mipmap.error_icon).into(viewHolder.imageView);
        return convertView;
    }

    public class ViewHolder {
        TextView fristWord, testTitle;
        de.hdodenhof.circleimageview.CircleImageView imageView;

        public ViewHolder(View view) {
            fristWord = (TextView) view.findViewById(R.id.fristWord);
            testTitle = (TextView) view.findViewById(R.id.shop_title);
            imageView = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.shop_iv);
        }
    }


}
