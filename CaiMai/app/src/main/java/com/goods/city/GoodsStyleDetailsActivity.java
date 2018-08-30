package com.goods.city;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
 * <p>
 * 商城列表
 */

public class GoodsStyleDetailsActivity extends BaseActivity implements RefreshLayout.RefreshLayoutDelegate {

    private Button class_btn;



    private RecyclerView recyclerView;
    private RefreshLayout mBGARefreshLayout;
    /**
     * 设置刷新和加载
     */
    private DefineBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;
    private MyRecyclerView myRecyclerView;
    private GoodsCityListAdapter goodsCityListAdapter;



    private List<GoodsModel> goodsData;





    @Override
    protected int getLayoutResource() {
        return R.layout.gd_goodsstyle_details_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        viewInit();
    }

    public void viewInit() {
        defaultDataInit();


        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setVisibility(View.VISIBLE);

        topbar_img_title= (TextView) findViewById(R.id.topbar_img_title);
        topbar_img_title.setVisibility(View.GONE);
        topbar_title= (TextView) findViewById(R.id.topbar_title);
        topbar_title.setVisibility(View.VISIBLE);
        topbar_title.setText("清凉解暑");

        other_btn = (ImageButton) findViewById(R.id.other_btn);
        other_btn.setVisibility(View.GONE);

        back_btn.setOnClickListener(onClickListener);
        other_btn.setOnClickListener(onClickListener);

        progress_liner = (LinearLayout) findViewById(R.id.progress_liner);
        nodataview_textview = (TextView) findViewById(R.id.nodataview_textview);

        mBGARefreshLayout = (RefreshLayout) findViewById(R.id.define_sliding_bga);
        recyclerView = (RecyclerView) findViewById(R.id.goodsCitylist_recycler);
        goodsCityListAdapter.setOnListViewClickLinstener(onListViewClickLinstener);
        // 设置刷新和加载监听
        mBGARefreshLayout.setDelegate(this);
        setBgaRefreshLayout();
        if (myRecyclerView == null) {
            myRecyclerView = new MyRecyclerView(this, recyclerView);
        }
        // myRecyclerView.setListView(true);
        myRecyclerView.setGridView(false, 2);
        recyclerView.setAdapter(goodsCityListAdapter);

        showMbProgress("数据加载中...");

//        app.netRequst.shoppingShowGoodtypesRequst(storeid, netRequstAjaxCallBack.shoppShowGoodsTypeCallback);
//        app.netRequst.shoppingGoodsRequst(storeid, "1", "100", "", goodclassid,
//                netRequstAjaxCallBack.shoppShowGoodsCallback);
        String[] img = {"", ""};
        for (int i = 0; i < 10; i++) {
            String goodsStoreId = "1000";
            String goodsId = "10001";
            String image1 = "10001";
            String title = "w我的衣服价格";
            double goodsPrice = 234.0;
            double storePrice = 234.9;
            String goodsProperty = "1000dd";

            GoodsModel model = new GoodsModel();
            model.setGoodsStoreId(goodsStoreId);
            model.setGoodsId(goodsId);
            model.setGoodsName(title);
            model.setImage(image1);
            model.setGoodsPrice(goodsPrice);
            model.setStorePrice(storePrice);
            model.setGoodsProperty(goodsProperty);
            model.setGoodsMainPhotoId(image1);
            goodsData.add(model);
        }

        goodsCityListAdapter.setGoodsData(goodsData);

        dissMbProgress();

    }

    public void defaultDataInit() {
        goodsCityListAdapter = new GoodsCityListAdapter(this);
        goodsData = new ArrayList<GoodsModel>();

    }

    @Override
    protected void loadData() {


    }

    private OnListViewClickLinstener onListViewClickLinstener = new OnListViewClickLinstener() {

        @Override
        public void itemClick(int position, GoodsCityListAdapter.ViewHolder holder) {
            // TODO Auto-generated method stub
            GoodsModel model = goodsData.get(position);
            extraMap.put("goodsStoreId", model.getGoodsStoreId());
            extraMap.put("goodsId", model.getGoodsId());
            extraMap.put("price", model.getGoodsPrice());
            extraMap.put("goodsProperty", model.getGoodsProperty());
            extraMap.put("goodsImg", model.getGoodsMainPhotoId());
            extraMap.put("goodsName", model.getGoodsName());
            skip_classView(GoodsDetailsActivity.class, extraMap, false);
        }

    };
    //
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stubs
            switch (v.getId()) {
                case R.id.back_btn:
                    backHistory(-1, true, false, extraMap);
                    break;





                default:
                    break;
            }

        }

    };

    /**
     * 设置 BGARefreshLayout刷新和加载
     */
    public void setBgaRefreshLayout() {
        mDefineBAGRefreshWithLoadView = new DefineBAGRefreshWithLoadView(this, true, true);
        // 设置刷新样式
        mBGARefreshLayout.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("自定义加载更多");
        // mBGARefreshLayout.beginRefreshing();
        // onBGARefreshLayoutBeginRefreshing(mBGARefreshLayout);

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        // TODO Auto-generated method stub
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("自定义加载更多");
        mDefineBAGRefreshWithLoadView.showLoadingMoreImg();
        msgHandler.sendEmptyMessage(LIST_REFUSH_WHAT);
        mBGARefreshLayout.endRefreshing();

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        // TODO Auto-generated method stub
        if (isMoreData == false) {
            mDefineBAGRefreshWithLoadView.updateLoadingMoreText("没有更多数据");
            mDefineBAGRefreshWithLoadView.hideLoadingMoreImg();
            msgHandler.sendEmptyMessage(LIST_LOADMORE_WHAT);
            return true;
        } else {
            msgHandler.sendEmptyMessage(LIST_LOADMORE_WHAT);
        }
        mBGARefreshLayout.endLoadingMore();
        return true;
    }

    private Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LIST_REFUSH_WHAT:// 刷新
                    // reSetData(true);
                    break;
                case LIST_LOADMORE_WHAT:// 加载更多
                    // reSetData(false);
                    break;
                case 2:
                    mBGARefreshLayout.endLoadingMore();
                    break;
            }
        }
    };
}
