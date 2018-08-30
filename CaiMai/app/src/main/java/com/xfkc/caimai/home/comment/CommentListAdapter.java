package com.xfkc.caimai.home.comment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.customview.MyGridView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.EmptyBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 1.评论列表
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class CommentListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<EmptyBean> list;


    public CommentListAdapter(Context context) {
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
            convertView = View.inflate(context, R.layout.comment_list_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }

        viewHodler.pinglunUserName.setText("name" + position);
        viewHodler.pinglunTime.setText("2018-08-0" + position);
        viewHodler.commentContent.setText("内容");
        Glide.with(context).load(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)//占位符
                .error(R.mipmap.ic_launcher)//加载错误时
                .into(viewHodler.pinglunUserIv);

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.pinglun_user_iv)
        CircleImageView pinglunUserIv;
        @Bind(R.id.pinglun_user_name)
        TextView pinglunUserName;
        @Bind(R.id.pinglun_time)
        TextView pinglunTime;
        @Bind(R.id.comment_content)
        TextView commentContent;
        @Bind(R.id.comment_gridview)
        MyGridView commentGridview;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
