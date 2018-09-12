package com.xfkc.caimai.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.BannerBean;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.customview.RecyclingPagerAdapter;
import com.xfkc.caimai.web.TDWebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.扶贫企业  横向滑动适配器
 * 2.@dongjinxu
 * 3.@2018/4/11.
 */

public class TubatuAdapter extends RecyclingPagerAdapter {

//    private final List<Integer> mList;
    private final List<BannerBean.DataBean.ListBean> mList;
    private final Context mContext;


    public TubatuAdapter(Context context) {
        mList = new ArrayList<>();
        mContext = context;
    }

    public void addAll(List<BannerBean.DataBean.ListBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    //添加扶贫企业轮播图数据
//    public void addAllUrl() {
//        list.addAll(FPEnterprise);
//        notifyDataSetChanged();
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        ImageView imageView = null;
        if (convertView == null) {
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }
//        imageView.setTag(position);

//        imageView.setImageResource(mList.get(position));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(mList.get(position).image)
                .placeholder(R.mipmap.error_icon)//占位符
                .error(R.mipmap.error_icon)//加载错误时
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //企业网址链接
                mContext.startActivity(new Intent(mContext, TDWebViewActivity.class)
                        .putExtra(Constant.WEB_URL, mList.get(position).url));
            }
        });
        return imageView;
    }


//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        ViewHolder viewHodler;
//        if (convertView == null) {
//            convertView = View.inflate(mContext, R.layout.fupin_hitem, null);
//            viewHodler = new ViewHolder(convertView);
//            convertView.setTag(viewHodler);
//        } else {
//            viewHodler = (ViewHolder) convertView.getTag();
//        }
//
//        Logger.e("----------------------------",""+position);
//        viewHodler.enterpriseName.setText(list.get(position).appImgDesc);
//        Glide.with(mContext).load(list.get(position).appImgUrl)
//                .placeholder(R.mipmap.default_banner)//占位符
//                .error(R.mipmap.default_banner)//加载错误时
//                .dontAnimate()
//                .into(viewHodler.image);
//
//        viewHodler.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //企业网址链接
//                mContext.startActivity(new Intent(mContext, TDWebViewActivity.class)
//                        .putExtra(Constant.WEB_URL, list.get(position).reqPath));
//                //企业详情页
////                mContext.startActivity(new Intent(mContext, FPEnterpriseContentActivity.class)
////                        .putExtra("enterPriseId", list.get(position).reqPath));
//            }
//        });
//
//        return convertView;
//    }


    @Override
    public int getCount() {
        return mList.size();
    }

//    static class ViewHolder {
//        @Bind(R.id.image)
//        ImageView image;
//        @Bind(R.id.enterprise_name)
//        TextView enterpriseName;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
}
