package com.goods.details;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.customview.AdViewPaper;
import com.dev.customview.TextViewUtils;
import com.goods.city.GoodsListModel;
import com.goods.city.GoodsValue;
import com.goods.netrequst.Logger;
import com.goods.netrequst.NetRequstAjaxCallBack;
import com.goods.netrequst.PostRequst;
import com.goods.shoppingcar.ShoppingCarActivity;
import com.goods.sortlsitview.AjaxShopModel;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.config.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.goods.netrequst.PostRequst.UPSUCCESS;

/**
 * Created by 10835 on 2018/8/26.
 */

public class GoodsDetailsActivity extends BaseActivity {
    public GoodsListModel goodsListModel;
    private String goodsImg, goodsName, goodsStoreId, goodsId;
    private String count, specInfo, price;
    private LinearLayout public_top_layout;
    private ListView goodsListView;
    private GoodsDetailsAdapter adapter;
    private FrameLayout top_frameLayout;
    private ImageButton search_img_btn;
    private View topHeadView;
    private TextView goodsTitle_textView, goods_discroubTitle_textView, goodsPrace_textView;

    private TextView back_textView, shoppingcar_textView, botoom_shopCarTtv, botoom_shopNumber, addShoppingCar_textView, buy_textView;

    private ImageView gs_img, xq_img, pj_img, tj_img;
    private TextView gs_textView, xq_textView, pj_textView, tj_textView;
    private TextView goodsType_textView;//规格选择
    private List<ImageView> locationImg;

    private AdViewPaper adViewPaper;
    private TextView point_textView;
    private GoodsDetailsHeader goodsDetailsHeader;


    private PostRequst postRequst;
    private NetRequstAjaxCallBack ajaxCallBack;

    @Override
    protected int getLayoutResource() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        return R.layout.gd_goodsdetailsactivity_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        postRequst = new PostRequst(handler);
        ajaxCallBack = new NetRequstAjaxCallBack(mContext);
        ajaxCallBack.setOnNetRequstAjaxCallBack(onNetRequstAjaxCallBack);
        goodsListModel = GoodsValue.getInstance().getGoodsListModel();
        locationImg = new ArrayList<ImageView>();

        viewInit();
    }

    public void viewInit() {
        public_top_layout = (LinearLayout) findViewById(R.id.public_top_layout);
        public_top_layout.setVisibility(View.GONE);
        back_btn = (ImageButton) findViewById(R.id.back_img_btn);
        topbar_title = (TextView) findViewById(R.id.text_title_content);
        topbar_title.setText("商品详情");

        search_img_btn = (ImageButton) findViewById(R.id.search_img_btn);
        search_img_btn.setOnClickListener(onClickListener);

        gs_img = (ImageView) findViewById(R.id.gs_img);
        xq_img = (ImageView) findViewById(R.id.xq_img);
        pj_img = (ImageView) findViewById(R.id.pj_img);
        //  tj_img = (ImageView) findViewById(R.id.tj_img);
        locationImg.add(gs_img);
        locationImg.add(xq_img);
        locationImg.add(pj_img);
        // locationImg.add(tj_img);


        gs_textView = (TextView) findViewById(R.id.gs_textView);
        xq_textView = (TextView) findViewById(R.id.xq_textView);
        pj_textView = (TextView) findViewById(R.id.pj_textView);
        // tj_textView = (TextView) findViewById(R.id.tj_textView);

        gs_textView.setOnClickListener(onClickListener);
        xq_textView.setOnClickListener(onClickListener);
        pj_textView.setOnClickListener(onClickListener);
        //  tj_textView.setOnClickListener(onClickListener);

        goodsListView = (ListView) findViewById(R.id.goodsListView);
        adapter = new GoodsDetailsAdapter(mContext);
        topHeadView = getLayoutInflater().inflate(R.layout.gd_goodsdetails_topview, null);
        goodsTitle_textView = (TextView) topHeadView.findViewById(R.id.goodsTitle_textView);
        goods_discroubTitle_textView = (TextView) topHeadView.findViewById(R.id.goods_discroubTitle_textView);
        goodsPrace_textView = (TextView) topHeadView.findViewById(R.id.goodsPrace_textView);
        goodsType_textView = (TextView) topHeadView.findViewById(R.id.goodsTypeTextView);

        top_frameLayout = (FrameLayout) topHeadView.findViewById(R.id.top_frameLayout);
        top_frameLayout.setVisibility(View.VISIBLE);

        back_textView = (TextView) topHeadView.findViewById(R.id.back_textView);
        shoppingcar_textView = (TextView) topHeadView.findViewById(R.id.shoppingcar_textView);
        botoom_shopCarTtv = (TextView) findViewById(R.id.botoom_shopCarTtv);
        botoom_shopNumber = (TextView) findViewById(R.id.botoom_shopNumber);


        adViewPaper = (AdViewPaper) topHeadView.findViewById(R.id.adViewPager);
        point_textView = (TextView) topHeadView.findViewById(R.id.point_textView);
        if (goodsDetailsHeader == null) {
            // GoodsImgValue.getInstance().init();
            goodsDetailsHeader = new GoodsDetailsHeader(GoodsDetailsActivity.this);
        }
        goodsDetailsHeader.setView(adViewPaper, point_textView);

        goodsListView.addHeaderView(topHeadView);

        goodsListView.setAdapter(adapter);

        goodsListView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                // 在onScroll方法中判断：
                showLocation(firstVisibleItem);
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView = view.getChildAt(view.getChildCount() - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == view.getHeight()) {
                        // Log.d("ListView", "##### 滚动到底部 ######");
                        showLocation(locationImg.size() - 1);
                    }
                } else {
                    View firstView = view.getChildAt(firstVisibleItem);
                    if (firstView != null) {
                        if (firstView.getTop() == 0) { // 判断第一个item到顶部的距离
                            // Log.d("HWGT", "滑动到顶部了，卧槽！");
                            public_top_layout.setVisibility(View.GONE);
                        } else {
                            public_top_layout.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        });
        buy_textView = (TextView) findViewById(R.id.buy_textView);
        addShoppingCar_textView = (TextView) findViewById(R.id.addShoppingCar_textView);

        back_btn.setOnClickListener(onClickListener);
        back_textView.setOnClickListener(onClickListener);
        shoppingcar_textView.setOnClickListener(onClickListener);
        botoom_shopCarTtv.setOnClickListener(onClickListener);
        addShoppingCar_textView.setOnClickListener(onClickListener);
        buy_textView.setOnClickListener(onClickListener);
        goodsType_textView.setOnClickListener(onClickListener);
        setBaseContent();
    }

    @Override
    protected void loadData() {


    }

    public void setBaseContent() {
        if (goodsListModel != null) {
            goodsTitle_textView.setText(goodsListModel.title);

            goods_discroubTitle_textView.setText(goodsListModel.sellPoint+"");
            goodsPrace_textView.setText(goodsListModel.itemPrice + "康币");
            setSitis(goodsPrace_textView);
        }

    }

    public void setSitis(TextView goods_prace) {
        String content = goods_prace.getText().toString();

        TextViewUtils.setContentTextSize(goods_prace, content, (int) Tools.dip2px(mContext, 20), 0, content.length() - 2);
    }

    public void showLocation(int position) {
        for (int i = 0; i < locationImg.size(); i++) {
            if (position == i) {
                locationImg.get(i).setVisibility(View.VISIBLE);
            } else {
                locationImg.get(i).setVisibility(View.GONE);
            }
        }
    }

    /**
     * 出现渐变效果
     */
    public void titleAnima(int y) {

        // int scrollHeight = myScrollView.getChildAt(0).getHeight() -
        // myScrollView.getHeight();
        // float scrollPercent = ((float) y / scrollHeight);

        // public_top_layout.getBackground().setAlpha((int) (255 * (y / 5)));

        if (y > 2) {
            public_top_layout.setVisibility(View.VISIBLE);
        } else {
            public_top_layout.setVisibility(View.GONE);
        }

    }


    public boolean isShow;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.back_img_btn:
                case R.id.back_textView:
                    finish();
                    // backHistory(-1, true, false, extraMap);
                    break;
                case R.id.gs_textView:

                    goodsListView.setSelection(0);
                    public_top_layout.setVisibility(View.GONE);
                    break;
                case R.id.xq_textView:
                    goodsListView.setSelection(1);
                    break;
                case R.id.pj_textView:
                    goodsListView.setSelection(2);
                    break;
                case R.id.tj_textView:
                    goodsListView.setSelection(3);
                    break;

                case R.id.shoppingcar_textView:
                case R.id.search_img_btn:
                case R.id.botoom_shopCarTtv:
                    //extraMap.put("goodsStoreId", goodsStoreId);
                    skip_classView(ShoppingCarActivity.class, extraMap, false);
                    break;
                case R.id.addShoppingCar_textView:// 加入购物车
                    isShow = true;
                    showMbProgress("添加中...");
//                    app.netRequst.shoppingCartSaveRequst(acc.getUserId(), goodsStoreId, goodsId, "1", specInfo, price,
//                            netRequstAjaxCallBack.shopingCarAddCallback);

                    break;
                case R.id.buy_textView:
                    //    SureCarValue.getInstance().init();
                    ShoppingCarModel model = new ShoppingCarModel();
                    model.setShopName(goodsName);
                    model.setShopId(goodsStoreId);
                    model.setShopGoodsName(goodsName);
                    model.setShopGoodsId(goodsId);
                    model.setGcId("0");
                    model.setShopGoodsImg(goodsImg);
                    model.setShopGoodsInfo(goodsName);
                    model.setShopGoodsNumber("1");
                    model.setShopGoodsPrace(price + "");
                    model.setShopGoodsOriginalPrace(price + "");
                    model.setShopGoodsStyle("上衣");

                    // SureCarValue.getInstance().setAddressData(model);
                    isShow = false;
                    // 添加购物车
//                    app.netRequst.shoppingCartSaveRequst(acc.getUserId(), goodsStoreId, goodsId, "1", specInfo, price,
//                            netRequstAjaxCallBack.shopingCarAddCallback);

                    extraMap.put("allPrace", price);
                    //  skip_classView(SureOrderforgoodsActivity.class, extraMap, false);
                    break;
                case R.id.goodsTypeTextView:
//                    showGoodsType();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backHistory(-1, true, false, extraMap);
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GoodsValue.getInstance().reSet();
    }

    /*展示商品规格*/
    private void showGoodsType() {
        final Dialog dialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.goods_type_dialog, null);
//        TextView horizontal = (TextView) contentView.findViewById(R.id.choose_camera_horizontal);
//        TextView vertical = (TextView) contentView.findViewById(R.id.choose_camera_vertical);
//        TextView finish = (TextView) contentView.findViewById(R.id.choose_camera_finish);
//        horizontal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });
//        vertical.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });

//        finish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
        dialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        dialog.show();
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
            goodsKey.shopId = app.shopModel.getShopId();
            goodsKey.recordName = "";
            postRequst.getProductBySearch(handler, goodsKey);
        }

    }
    private NetRequstAjaxCallBack.OnNetRequstAjaxCallBack onNetRequstAjaxCallBack = new NetRequstAjaxCallBack.OnNetRequstAjaxCallBack() {

        @Override
        public void MsgCallBack(boolean isSuccess, String errorMsg, Object object) {
            // TODO Auto-generated method stub
            dissMbProgress();
            if (isSuccess) {

                ArrayList<GoodsListModel> shopsList = (ArrayList) object;

            } else {

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



}
