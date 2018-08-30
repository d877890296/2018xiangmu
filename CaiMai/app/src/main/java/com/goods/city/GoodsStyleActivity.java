package com.goods.city;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.goods.city.GoodsCityListAdapter.OnListViewClickLinstener;
import com.goods.details.GoodsDetailsActivity;
import com.goods.model.GoodsModel;
import com.recycle.view.MyRecyclerView;
import com.refushView.RefreshLayout;
import com.refushView.holder.DefineBAGRefreshWithLoadView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10835 on 2018/8/20.
 *
 * 商城分类列表
 */

public class GoodsStyleActivity extends BaseActivity  {




    private ListView goods_styleListView,goodsListView;
    private GoodsStyleAdapter goodsStyleAdapter;


    public List<GoodsStyleModel> data;
    private GoodsStyleContentAdapter goodsStyleContentAdapter;



private String style[]={"水果","蔬菜"};


    private String content[]={"水果2","蔬2菜","水fs果2","蔬2菜","水果csa2"};

    @Override
    protected int getLayoutResource() {
        return R.layout.goods_style_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
       viewInit();
    }

    public void viewInit() {
        defaultDataInit();

        topbar_img_title= (TextView) findViewById(R.id.topbar_img_title);
        topbar_img_title.setVisibility(View.GONE);
        topbar_title= (TextView) findViewById(R.id.topbar_title);
        topbar_title.setVisibility(View.VISIBLE);
        topbar_title.setText("百货分类");

        other_btn= (ImageButton) findViewById(R.id.other_btn);
        other_btn.setVisibility(View.GONE);
        other_morbtn= (Button) findViewById(R.id.other_morbtn);
        other_morbtn.setVisibility(View.VISIBLE);
        other_morbtn.setText("取消");
        other_morbtn.setOnClickListener(onClickListener);


        goods_styleListView=(ListView) findViewById(R.id.goods_styleListView);
        goods_styleListView.setAdapter(goodsStyleAdapter);
        goodsListView=(ListView) findViewById(R.id.goodsListView);
        goodsStyleContentAdapter.setData(data);
        goodsListView.setAdapter(goodsStyleContentAdapter);
        goodsListView.setOnItemClickListener(onItemClickListener);
    }

    public void defaultDataInit(){
        goodsStyleAdapter=new GoodsStyleAdapter(mContext);
        goodsStyleContentAdapter=new GoodsStyleContentAdapter(mContext);
        data=new ArrayList<GoodsStyleModel>();
        for (int i=0;i<content.length;i++){
            GoodsStyleModel model=new GoodsStyleModel();
            model.setStyleName(style[0]);
            model.setGoodsName(content[i]);

            data.add(model);
        }
        for (int i=0;i<content.length;i++){
            GoodsStyleModel model=new GoodsStyleModel();
            model.setStyleName(style[1]);
            model.setGoodsName(content[i]);
            data.add(model);
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            skip_classView(GoodsStyleDetailsActivity.class, extraMap, false);
        }
    };

    @Override
    protected void loadData() {


    }

//
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stubs
            switch (v.getId()) {
                case R.id.other_morbtn:
                    backHistory(-1,true,false,extraMap);
                    break;



                default:
                    break;
            }

        }

    };







}
