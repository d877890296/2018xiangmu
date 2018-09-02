package com.goods.details;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dev.customview.MyWebView;
import com.goods.city.GoodsListModel;
import com.goods.city.GoodsValue;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;

import java.net.URLEncoder;

public class GoodsDetailsAdapter extends BaseAdapter {
    private int[] type = {0, 1, 2};
    public Context context;
    public LayoutInflater inflater;
    final String mimeType = "text/html";
    final String encoding = "utf-8";

    private GoodsListModel goodsListModel;

    public GoodsDetailsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        // TODO Auto-generated constructor stub
        goodsListModel = GoodsValue.getInstance().getGoodsListModel();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2;
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
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return type[position];
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        switch (position) {
            case 0://详情
                convertView = inflater.inflate(R.layout.gd_goodsother_fristitem, null);
                MyWebView detailsContent_web = (MyWebView) convertView.findViewById(R.id.detailsContent_web);
                try {
                    if(Tools.IsEmpty(goodsListModel.content)){

                        detailsContent_web.loadData("暂无详情", mimeType, encoding);
                    }else{
                        detailsContent_web.loadData(goodsListModel.content,  mimeType, encoding);
                       // detailsContent_web.loadData(URLEncoder.encode(goodsListModel.content, encoding), mimeType, encoding);
                    }

                } catch (Exception ex) {

                    ex.printStackTrace();

                }

                break;
            case 1://评价
                convertView = inflater.inflate(R.layout.gd_goodsother_seconditem, null);
                break;
            case 2://推荐
                convertView = inflater.inflate(R.layout.gd_goodsother_threeitem, null);
                break;
            default:
                break;
        }

        return convertView;
    }


}
