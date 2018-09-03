package com.goods.city;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.customview.BarLineSetting;
import com.goods.city.GoodsCityListAdapter.OnListViewClickLinstener;
import com.goods.details.GoodsDetailsActivity;
import com.goods.netrequst.Logger;
import com.goods.netrequst.NetRequstAjaxCallBack;
import com.goods.netrequst.PostRequst;
import com.goods.shoppingcar.ShoppingCarActivity;
import com.goods.sortlsitview.AjaxShopModel;
import com.goods.sortlsitview.GoodsChooseCityActivity;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.recycle.view.MyRecyclerView;
import com.refushView.RefreshLayout;
import com.refushView.holder.DefineBAGRefreshWithLoadView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.bean.TopCategory;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static com.goods.netrequst.PostRequst.UPSUCCESS;

/**
 * Created by 10835 on 2018/8/20.
 * <p>
 * 商城列表
 */

public class GoodsCityActivity extends BaseActivity implements RefreshLayout.RefreshLayoutDelegate {

    private Button class_btn;

    private RadioButton all_textView, socle_textView, prace_textView, more_textView;

    private TextView barLine;
    // bar相关配置
    private List<RadioButton> barView;
    private BarLineSetting barLineSetting;

    private int curSelectBarTextColor = 0xFFFF704D;
    // 默认bar字体颜色
    private int defaultBarTextColor = 0xFF323232;

    private RecyclerView recyclerView;
    private RefreshLayout mBGARefreshLayout;
    /**
     * 设置刷新和加载
     */
    private DefineBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;
    private MyRecyclerView myRecyclerView;
    private GoodsCityListAdapter goodsCityListAdapter;

    private TextView goods_grid_list_change;
    private int curStyle = 0;

    private List<GoodsListModel> goodsData;
    private boolean isFristLoadData;
    private String sidx = "";

    private String storeid = "1";

//    private TextView background_view;
//    private List<GoodsClassModel> goodsTypeData;
//    private ListView choose_class_list;
//    private GoodsTypeAdapter goodsTypeAdapter;
//    private String goodclassid = "";

    private String topCategoryId = "1";
    public GoodsKey goodsKey;
    //
    private PostRequst postRequst;
    private NetRequstAjaxCallBack ajaxCallBack;
    private List<TopCategory.DataBean.ListBean> top_list;

    @Override
    protected int getLayoutResource() {
        setSoftInputMode();
        hindKey();
        GoodsValue.getInstance().init();
        return R.layout.gd_goodscity_fragment_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        postRequst = new PostRequst(handler);
        ajaxCallBack = new NetRequstAjaxCallBack(mContext);
        ajaxCallBack.setOnNetRequstAjaxCallBack(onNetRequstAjaxCallBack);
        viewInit();
    }

    public void viewInit() {
        defaultDataInit();

        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setVisibility(View.VISIBLE);

        topbar_img_title = (TextView) findViewById(R.id.topbar_img_title);
        if (app.shopModel != null) {
            topbar_img_title.setText(app.shopModel.getName());
        } else {
            topbar_img_title.setText("点击获取");
        }
        topbar_img_title.setOnClickListener(onClickListener);
        other_btn = (ImageButton) findViewById(R.id.other_btn);
        all_textView = (RadioButton) findViewById(R.id.all_textView);
        socle_textView = (RadioButton) findViewById(R.id.socle_textView);
        prace_textView = (RadioButton) findViewById(R.id.prace_textView);
        more_textView = (RadioButton) findViewById(R.id.more_textView);
        barLine = (TextView) findViewById(R.id.barLine);
        barView.add(all_textView);
        barView.add(socle_textView);
        barView.add(prace_textView);
        barView.add(more_textView);


        // 设置barline
        barLineSetting = new BarLineSetting(barLine);
        barLineSetting.setCurSelectBarTextColor(curSelectBarTextColor);
        barLineSetting.setDefaultBarTextColor(defaultBarTextColor);
        // 设置barView
        barLineSetting.setBarView(barView, app.phoneResolution_w);
        // 设置参考的view视图
        barLineSetting.setReferenceView(all_textView);

        back_btn.setOnClickListener(onClickListener);
        other_btn.setOnClickListener(onClickListener);
        all_textView.setOnClickListener(onClickListener);
        socle_textView.setOnClickListener(onClickListener);
        prace_textView.setOnClickListener(onClickListener);
        more_textView.setOnClickListener(onClickListener);

        goods_grid_list_change = (TextView) findViewById(R.id.goods_grid_list_change);
        goods_grid_list_change.setOnClickListener(onClickListener);

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
        setBarTextColor(0);
        showMbProgress("数据加载中...");


//        String[] img = {"", ""};
//        for (int i = 0; i < 10; i++) {
//            String goodsStoreId = "1000";
//            String goodsId = "10001";
//            String image1 = "10001";
//            String title = "w我的衣服价格";
//            double goodsPrice = 234.0;
//            double storePrice = 234.9;
//            String goodsProperty = "1000dd";
//
//            GoodsModel model = new GoodsModel();
//            model.setGoodsStoreId(goodsStoreId);
//            model.setGoodsId(goodsId);
//            model.setGoodsName(title);
//            model.setImage(image1);
//            model.setGoodsPrice(goodsPrice);
//            model.setStorePrice(storePrice);
//            model.setGoodsProperty(goodsProperty);
//            model.setGoodsMainPhotoId(image1);
//            goodsData.add(model);
//        }

        goodsCityListAdapter.setGoodsData(goodsData);
        requstNetData();
        dissMbProgress();

    }

    public void defaultDataInit() {
        goodsCityListAdapter = new GoodsCityListAdapter(this);
        goodsData = new ArrayList<GoodsListModel>();
        barView = new ArrayList<RadioButton>();
    }

    @Override
    protected void loadData() {
        PayFactory.getPayService().getTopCategory()
                .compose(RxHelper.<TopCategory>io_main())
                .subscribe(new Subscriber<TopCategory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TopCategory topCategory) {
                        if (topCategory.data.list != null && topCategory.data.list.size() != 0) {
                            top_list.addAll(topCategory.data.list);
                            topCategoryId = top_list.get(0).id+"";
                        }
                    }
                });

    }

    /****
     *
     * 请求数据
     */
    public void requstNetData() {
        if (app.shopModel != null) {
            userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
            GoodsKey goodsKey = new GoodsKey();
            goodsKey.token = userToken;
            goodsKey.pageNum = pageNum + "";
            goodsKey.pageSize = pageSize + "";
            goodsKey.shopId = app.shopModel.getShopId();
            goodsKey.topCategoryId = Integer.parseInt(topCategoryId);
            goodsKey.recordName = "";
            postRequst.getProductBySearch(handler, goodsKey);
        }

    }


    private OnListViewClickLinstener onListViewClickLinstener = new OnListViewClickLinstener() {

        @Override
        public void itemClick(int position, GoodsCityListAdapter.ViewHolder holder) {
            // TODO Auto-generated method stub
            GoodsListModel model = goodsData.get(position);
//            extraMap.put("goodsStoreId", model.getGoodsStoreId());
//            extraMap.put("goodsId", model.getGoodsId());
//            extraMap.put("price", model.getGoodsPrice());
//            extraMap.put("goodsProperty", model.getGoodsProperty());
//            extraMap.put("goodsImg", model.getGoodsMainPhotoId());
//            extraMap.put("goodsName", model.getGoodsName());
            GoodsValue.getInstance().setGoodsListModel(model);
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
                case R.id.topbar_img_title:
                    skip_classView(GoodsChooseCityActivity.class, extraMap, false, 1000);
                    break;
                case R.id.other_btn:
                    skip_classView(ShoppingCarActivity.class, extraMap, false);
                    break;
                case R.id.all_textView:
                    sidx = "";
                    setBarTextColor(0);
                    barLineSetting.Amination(0);

                    topCategoryId = "1";
                    requstNetData();
                    isFristLoadData = true;
                    showMbProgress("数据加载中...");
//                    app.netRequst.shoppingGoodsRequst("1", "1", "100", "", goodclassid,
//                            netRequstAjaxCallBack.shoppShowGoodsCallback);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dissMbProgress();
                        }
                    }, 2000);
                    break;
                case R.id.socle_textView:
                    sidx = "";
                    isFristLoadData = true;
                    setBarTextColor(1);
                    barLineSetting.Amination(1);
                    showMbProgress("数据加载中...");
//                    app.netRequst.shoppingGoodsRequst("1", "1", "100", "", goodclassid,
//                            netRequstAjaxCallBack.shoppShowGoodsCallback);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dissMbProgress();
                        }
                    }, 2000);
                    topCategoryId = "2";
                    requstNetData();
                    break;
                case R.id.prace_textView:
                    sidx = "";
                    isFristLoadData = true;
                    setBarTextColor(2);
                    barLineSetting.Amination(2);
                    showMbProgress("数据加载中...");
//                    app.netRequst.shoppingGoodsRequst("1", "1", "100", "", goodclassid,
//                            netRequstAjaxCallBack.shoppShowGoodsCallback);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dissMbProgress();
                        }
                    }, 2000);
                    topCategoryId ="3";
                    requstNetData();
                    break;
                case R.id.more_textView:
                    sidx = "";
                    isFristLoadData = true;
                    setBarTextColor(3);
                    barLineSetting.Amination(3);
                    showMbProgress("数据加载中...");
//                    app.netRequst.shoppingGoodsRequst("1", "1", "100", "", goodclassid,
//                            netRequstAjaxCallBack.shoppShowGoodsCallback);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dissMbProgress();
                        }
                    }, 2000);
                    topCategoryId = "4";
                    requstNetData();
                    break;
                case R.id.goods_grid_list_change:
                    //myRecyclerView = null;
//                    if (curStyle == 0) {
//                        //myRecyclerView = new MyRecyclerView(context, recyclerView);
//                        myRecyclerView.setListView(true);
//                        goodsCityListAdapter.setBaseType(1);
//                        curStyle = 1;
//                    } else {
//                        //myRecyclerView = new MyRecyclerView(context, recyclerView);
//                        myRecyclerView.setGridView(false, 2);
//                        goodsCityListAdapter.setBaseType(0);
//                        curStyle = 0;
//                    }
                    extraMap.put("topCategoryId",topCategoryId);
                    skip_classView(GoodsStyleActivity.class, extraMap, false);
                    break;

                default:
                    break;
            }

        }

    };

    /***
     * 设置bar字体颜色
     *
     * @param pager
     */
    public void setBarTextColor(int pager) {
        int size = barView.size();
        for (int i = 0; i < size; i++) {
            RadioButton rb = barView.get(i);
            if (i == pager) {
                rb.setTextColor(curSelectBarTextColor);

            } else {
                rb.setTextColor(defaultBarTextColor);

            }
        }
    }

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


    private NetRequstAjaxCallBack.OnNetRequstAjaxCallBack onNetRequstAjaxCallBack = new NetRequstAjaxCallBack.OnNetRequstAjaxCallBack() {

        @Override
        public void MsgCallBack(boolean isSuccess, String errorMsg, Object object) {
            // TODO Auto-generated method stub
            dissMbProgress();
            if (isSuccess) {

                ArrayList<GoodsListModel> shopsList = (ArrayList) object;
                goodsData.addAll(shopsList);
                goodsCityListAdapter.setGoodsData(goodsData);
                nodataview_textview.setVisibility(View.GONE);
            } else {
                nodataview_textview.setVisibility(View.VISIBLE);
            }


        }

    };
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPSUCCESS://数据获取成功
                    if (msg.arg1 == 1) {//成功
                        String jsonObj = msg.obj.toString();
                        if (Tools.IsEmpty(jsonObj)) {
                            android.widget.Toast.makeText(mContext,
                                    "数据错误", Toast.LENGTH_LONG).show();
                            return;
                        }
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(jsonObj);
                            //   CommonConvert convert = new CommonConvert(obj);
                            //   jsonObj = convert.getString("data");
                            jsonObj = obj.getString("data");
                            Logger.e("jsonObj:---", jsonObj);
                            app.jsonHttp.getJsonObj(jsonObj, AjaxShopModel.class,
                                    ajaxCallBack.getProductBySearch);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {//失败
                        android.widget.Toast.makeText(mContext,
                                "加载数据失败", Toast.LENGTH_LONG).show();
                    }

                    break;
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            if (app.shopModel != null) {
                topbar_img_title.setText(app.shopModel.getName());
                requstNetData();
            } else {
                topbar_img_title.setText("点击获取");
            }
        }
    }
}
