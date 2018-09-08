package com.goods.city;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.goods.netrequst.NetRequst;
import com.goods.netrequst.NetRequstAjaxCallBack;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10835 on 2018/8/20.
 * <p>
 * 商城分类列表
 */

public class GoodsStyleActivity extends BaseActivity {

    private ListView goods_styleListView, goodsListView;
    private GoodsStyleAdapter goodsStyleAdapter;
    private List<GoodsListModel> goodsData;

    public List<GoodsStyleModel> data;
    private GridView goodsGridView;
//    private GoodsStyleContentAdapter goodsStyleContentAdapter;
    private GoodsStyleGridContentAdapter goodsStyleGridContentAdapter;

    private TextView second_nodataview_textview;

    private NetRequst netRequst;
    private NetRequstAjaxCallBack ajaxCallBack;


    private int requstType = 0;
    private String firstTitle = "";
    private String topCategoryId;

    @Override
    protected int getLayoutResource() {
        return R.layout.goods_style_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        goodsData = new ArrayList<GoodsListModel>();

        netRequst = NetRequst.getInstance();
        ajaxCallBack = new NetRequstAjaxCallBack(mContext);
        ajaxCallBack.setOnNetRequstAjaxCallBack(onNetRequstAjaxCallBack);
        topCategoryId = getIntent().getStringExtra("topCategoryId");
        viewInit();
    }

    public void viewInit() {
        defaultDataInit();

        topbar_img_title = (TextView) findViewById(R.id.topbar_img_title);
        topbar_img_title.setVisibility(View.GONE);
        topbar_title = (TextView) findViewById(R.id.topbar_title);
        topbar_title.setVisibility(View.VISIBLE);
        topbar_title.setText("百货分类");

        other_btn = (ImageButton) findViewById(R.id.other_btn);
        other_btn.setVisibility(View.GONE);
        other_morbtn = (Button) findViewById(R.id.other_morbtn);
        other_morbtn.setVisibility(View.VISIBLE);
        other_morbtn.setText("取消");
        other_morbtn.setOnClickListener(onClickListener);
        progress_liner = (LinearLayout) findViewById(R.id.progress_liner);
        nodataview_textview = (TextView) findViewById(R.id.nodataview_textview);
        second_nodataview_textview = (TextView) findViewById(R.id.second_nodataview_textview);

        goods_styleListView = (ListView) findViewById(R.id.goods_styleListView);
        goodsStyleAdapter.setData(goodsData);
        goods_styleListView.setAdapter(goodsStyleAdapter);
        goods_styleListView.setOnItemClickListener(onItemClickListener1);

//        goodsListView = (ListView) findViewById(R.id.goodsListView);
//        goodsStyleContentAdapter.setData(data);
//        goodsListView.setAdapter(goodsStyleContentAdapter);
//        goodsListView.setOnItemClickListener(onItemClickListener);


        goodsGridView = (GridView) findViewById(R.id.goodsGridView);
        goodsStyleGridContentAdapter.setData(data);
        goodsGridView.setAdapter(goodsStyleGridContentAdapter);


        goodsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                extraMap.put("id", data.get(position).getStyleId());
                extraMap.put("categoryId", data.get(position).getCategoryId());
                extraMap.put("goodsName", data.get(position).getGoodsName());
                skip_classView(GoodsStyleDetailsActivity.class, extraMap, false);
            }
        });

        requstNetData();
    }

    public void defaultDataInit() {
        goodsStyleAdapter = new GoodsStyleAdapter(mContext);
//        goodsStyleContentAdapter = new GoodsStyleContentAdapter(mContext);
        goodsStyleGridContentAdapter = new GoodsStyleGridContentAdapter(mContext);

        data = new ArrayList<GoodsStyleModel>();
//        for (int i=0;i<content.length;i++){
//            GoodsStyleModel model=new GoodsStyleModel();
//            model.setStyleName(style[0]);
//            model.setGoodsName(content[i]);
//            data.add(model);
//        }
//        for (int i=0;i<content.length;i++){
//            GoodsStyleModel model=new GoodsStyleModel();
//            model.setStyleName(style[1]);
//            model.setGoodsName(content[i]);
//            data.add(model);
//        }
    }

    public void requstNetData() {
        requstType = 0;
        showMbProgress("数据加载中");
        netRequst.getAllCategory(ajaxCallBack.getAllCategory, topCategoryId);
    }


    public void requstNetDataFor(String categoryId) {
        requstType = 1;
        netRequst.getSecoCategory(ajaxCallBack.getSecoCategory, pageNum + "", pageSize + "", categoryId);
    }

    private AdapterView.OnItemClickListener onItemClickListener1 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            goodsStyleAdapter.setCurrentIndex(position);
            showMbProgress("数据加载中");
            firstTitle = goodsData.get(position).cname;
            requstNetDataFor(goodsData.get(position).id + "");

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
                    backHistory(-1, true, false, extraMap);
                    break;


                default:
                    break;
            }

        }

    };


    private NetRequstAjaxCallBack.OnNetRequstAjaxCallBack onNetRequstAjaxCallBack = new NetRequstAjaxCallBack.OnNetRequstAjaxCallBack() {

        @Override
        public void MsgCallBack(boolean isSuccess, String errorMsg, Object object) {
            // TODO Auto-generated method stub
            dissMbProgress();
            if (isSuccess) {
                if (requstType == 0) {
                    ArrayList<GoodsListModel> shopsList = (ArrayList) object;
                    goodsData.addAll(shopsList);
                    goodsStyleAdapter.setData(goodsData);
                    nodataview_textview.setVisibility(View.GONE);
                    second_nodataview_textview.setVisibility(View.GONE);
                    firstTitle = goodsData.get(0).cname;
                    requstNetDataFor(shopsList.get(0).id + "");
                } else {
                    ArrayList<GoodsListModel> shopsList = (ArrayList) object;
                    if (shopsList != null && shopsList.size() > 0) {
                        data.clear();
                        second_nodataview_textview.setVisibility(View.GONE);
                        for (int i = 0; i < shopsList.size(); i++) {
                            GoodsStyleModel model = new GoodsStyleModel();
                            model.setStyleName(firstTitle);
                            model.setGoodsName(shopsList.get(i).sname);
                            model.setStyleId(shopsList.get(i).id + "");
                            model.setCategoryId(shopsList.get(i).categoryId + "");
                            data.add(model);
                        }
//                        goodsStyleContentAdapter.setData(data);
                        goodsStyleGridContentAdapter.setData(data);
                    } else {
                        second_nodataview_textview.setVisibility(View.VISIBLE);
                    }

                }
            } else {
                if (requstType == 0) {
                    nodataview_textview.setVisibility(View.VISIBLE);
                } else {
                    second_nodataview_textview.setVisibility(View.VISIBLE);
                }
            }
        }

    };


}
