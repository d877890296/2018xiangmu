package com.goods.details;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dev.customview.AdViewPaper;
import com.dev.customview.MyGridView;
import com.dev.customview.TextViewUtils;
import com.goods.city.GoodsListModel;
import com.goods.city.GoodsValue;
import com.goods.netrequst.Logger;
import com.goods.netrequst.NetRequstAjaxCallBack;
import com.goods.netrequst.PostRequst;
import com.goods.order.SureOrderActivity;
import com.goods.shoppingcar.ShoppingCarActivity;
import com.goods.shoppingcar.SureCarValue;
import com.google.gson.Gson;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.GoodsCarNumBean;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.bean.ParamBean;
import com.xfkc.caimai.bean.ParamPriceBean;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.shopselect.BigClassification;
import com.xfkc.caimai.shopselect.OnSelectedListener;
import com.xfkc.caimai.shopselect.ShoppingSelectView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import rx.Subscriber;

import static com.goods.netrequst.PostRequst.UPSUCCESS;
import static com.xfkc.caimai.R.id.shop_price;

/**
 * Created by 10835 on 2018/8/26.
 */

public class GoodsDetailsActivity extends BaseActivity implements OnSelectedListener {
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
    private TextView gs_textView, xq_textView, pj_textView, tj_textView, youfei_tv;
    private TextView goodsType_textView, goods_num_tv;//规格选择
    private List<ImageView> locationImg;

    private AdViewPaper adViewPaper;
    private TextView point_textView;
    private GoodsDetailsHeader goodsDetailsHeader;


    private PostRequst postRequst;
    private NetRequstAjaxCallBack ajaxCallBack;
    private int number = 1;//商品数量
    private ImageView goods_remove_button, goods_add_bt;

    @Override
    protected int getLayoutResource() {
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
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
        userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
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
        goods_remove_button = (ImageView) topHeadView.findViewById(R.id.goods_remove_btn);
        goods_add_bt = (ImageView) topHeadView.findViewById(R.id.goods_add_btn);
        goods_num_tv = (TextView) topHeadView.findViewById(R.id.goodsNum);
        youfei_tv = (TextView) topHeadView.findViewById(R.id.youfei_tv);
        if (Tools.IsEmpty(goodsListModel.shippingPrice)){
            youfei_tv.setText(0 + "康币");
        }else {
            youfei_tv.setText(goodsListModel.shippingPrice + "康币");
        }
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
        if (!Tools.IsEmpty(goodsListModel.pic)) {
            String[] imges = goodsListModel.pic.split(",");
            goodsDetailsHeader.setImgArray(imges);
        }
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
        goods_remove_button.setOnClickListener(onClickListener);
        goods_add_bt.setOnClickListener(onClickListener);
        setBaseContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCarNum();
    }

    @Override
    protected void loadData() {
//        updateCarNum();

    }

    public void setBaseContent() {
        if (goodsListModel != null) {
            goodsTitle_textView.setText(goodsListModel.title);

            goods_discroubTitle_textView.setText(goodsListModel.sellPoint + "");
            itemPrice = goodsListModel.itemPrice;
            goodsPrace_textView.setText( itemPrice + "康币");
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
//                    requstNetDataAddProduct();
                    showGoodsType();

                    break;
                case R.id.buy_textView://直接购买
                    isShow = false;

                    showGoodsType();
                    //  skip_classView(SureOrderforgoodsActivity.class, extraMap, false);
                    break;
                case R.id.goodsTypeTextView:
                    showGoodsType();
                    break;
                case R.id.fenqi_3:
                    fenqi_type = 3;
                    setFenQiShow(0);
                    break;
                case R.id.fenqi_6:
                    fenqi_type = 6;
                    setFenQiShow(1);
                    break;
                case R.id.fenqi_9:
                    fenqi_type = 9;
                    setFenQiShow(2);
                    break;
                case R.id.fenqi_12:
                    fenqi_type = 12;
                    setFenQiShow(3);
                    break;
                case R.id.goods_remove_btn:
                    if (number > 1) {
                        number--;
                        setAllPrice(number);
                    }
                    break;
                case R.id.goods_add_btn:
                    final int inventory;
                    if (Tools.IsEmpty(goodsListModel.inventory + "")) {
                        inventory = 0;
                    } else {
                        inventory = Integer.parseInt(goodsListModel.inventory + "");
                    }
                    if (number < inventory) {
                        number++;
                        setAllPrice(number);
                    } else {
                        ToastUtil.showToast("库存量不足");
                    }
                    break;
                case R.id.type_price:
                    param = paramBean.params.get(0);
                    setType(0);
                    getParamPrice();
                    break;
                case R.id.type_price02:
                    param = paramBean.params.get(1);
                    setType(1);
                    getParamPrice();
                    break;
                case R.id.type_price03:
                    param = paramBean.params.get(2);
                    setType(2);
                    getParamPrice();
                    break;
                case R.id.type_price04:
                    param = paramBean.params.get(3);
                    setType(3);
                    getParamPrice();
                    break;
                default:
                    break;
            }
        }
    };

    /****
     *
     * 请求数据
     */
    public void requstNetDataAddProduct() {

        if (app.shopModel != null) {

            GoodsKey goodsKey = new GoodsKey();
            goodsKey.token = userToken;

//            GoodsValue.getInstance().getGoodsListModel().buyNum = 1;
//            GoodsValue.getInstance().getGoodsListModel().paramData = param;
            GoodsValue.getInstance().getGoodsListModel().itemPrice = itemPrice;
            postRequst.addProduct(handler, goodsKey, number, param);
        }


    }

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

    private ArrayList<RadioButton> fenqi_radios = new ArrayList<>();
    private int fenqi_type = 0, inventory = 0;
    private TextView botoom_typeshopNumber;
    private String param = "";
    private ArrayList<TextView> list_type_tv = new ArrayList<>();
    private ParamBean paramBean;
    private TextView dialog_shop_price, shop_kc;
    private String unit;
    private double itemPrice;
    /*展示商品规格*/
    private void showGoodsType() {
        final Dialog dialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.goods_type_dialog, null);
        ImageView shop_iv = (ImageView) contentView.findViewById(R.id.shop_iv);
        ImageView close_iv = (ImageView) contentView.findViewById(R.id.close_iv);
        dialog_shop_price = (TextView) contentView.findViewById(shop_price);
        shop_kc = (TextView) contentView.findViewById(R.id.shop_kc);
        TextView type_name01 = (TextView) contentView.findViewById(R.id.type_name01);
        TextView type_price = (TextView) contentView.findViewById(R.id.type_price);
        TextView type_price02 = (TextView) contentView.findViewById(R.id.type_price02);
        TextView type_price03 = (TextView) contentView.findViewById(R.id.type_price03);
        TextView type_price04 = (TextView) contentView.findViewById(R.id.type_price04);
        ShoppingSelectView shpSelectView = (ShoppingSelectView) contentView.findViewById(R.id.v);
        //设置监听需要在设置数据源之前
        shpSelectView.setOnSelectedListener(this);
        list_type_tv.clear();
        list_type_tv.add(type_price);
        list_type_tv.add(type_price02);
        list_type_tv.add(type_price03);
        list_type_tv.add(type_price04);
        type_price.setOnClickListener(onClickListener);
        type_price02.setOnClickListener(onClickListener);
        type_price03.setOnClickListener(onClickListener);
        type_price04.setOnClickListener(onClickListener);
        MyGridView type01_gridview = (MyGridView) contentView.findViewById(R.id.type_gridview);
        TextView type_name02 = (TextView) contentView.findViewById(R.id.type_name02);
        TextView delete_goods_tv = (TextView) contentView.findViewById(R.id.delete_goods_tv);
        TextView add_goods_tv = (TextView) contentView.findViewById(R.id.add_goods_tv);
        final TextView show_goodsnum_tv = (TextView) contentView.findViewById(R.id.show_goodsnum_tv);

        TextView botoom_shopCarTtv = (TextView) contentView.findViewById(R.id.botoom_shopCarTtv);
        botoom_typeshopNumber = (TextView) contentView.findViewById(R.id.botoom_shopNumber);
        TextView addShoppingCar_textView = (TextView) contentView.findViewById(R.id.addShoppingCar_textView);
        TextView buy_textView = (TextView) contentView.findViewById(R.id.buy_textView);
        LinearLayout fenqi_layout = (LinearLayout) contentView.findViewById(R.id.fenqi_layout);
        LinearLayout type_layout = (LinearLayout) contentView.findViewById(R.id.type_layout01);
        RadioButton fenqi_3 = (RadioButton) contentView.findViewById(R.id.fenqi_3);
        RadioButton fenqi_6 = (RadioButton) contentView.findViewById(R.id.fenqi_6);
        RadioButton fenqi_9 = (RadioButton) contentView.findViewById(R.id.fenqi_9);
        RadioButton fenqi_12 = (RadioButton) contentView.findViewById(R.id.fenqi_12);
        fenqi_radios.add(fenqi_3);
        fenqi_radios.add(fenqi_6);
        fenqi_radios.add(fenqi_9);
        fenqi_radios.add(fenqi_12);
        setAllPrice(number, show_goodsnum_tv);
        botoom_typeshopNumber.setText(goodsCarNum + "");
        String picture = "";
        if (!Tools.IsEmpty(goodsListModel.pic) && goodsListModel.pic.contains(",")) {
            String[] imges = goodsListModel.pic.split(",");
           picture = imges[0];
        }else {
            picture = goodsListModel.pic+"";
        }
        Glide.with(this).load(picture).error(R.mipmap.error_icon).into(shop_iv);
        itemPrice = goodsListModel.itemPrice;
        dialog_shop_price.setText(itemPrice + "康币");
        if (Tools.IsEmpty(goodsListModel.inventory + "")) {
            inventory = 0;
        } else {
            inventory = Integer.parseInt(goodsListModel.inventory + "");
        }
        unit = goodsListModel.unit;
        shop_kc.setText("库存 " + inventory + " " + goodsListModel.unit);
        if (goodsListModel.itemType == 0) {
            if (Tools.IsEmpty(goodsListModel.paramData)) {
                type_name01.setVisibility(View.GONE);
                type_price.setVisibility(View.GONE);
                type_price02.setVisibility(View.GONE);
            } else {
                type_name01.setVisibility(View.VISIBLE);
                type_price.setVisibility(View.VISIBLE);
                type_price02.setVisibility(View.GONE);
                type_price.setText(goodsListModel.paramData);
            }
        } else {
            if (goodsListModel.allParamData == null || Tools.IsEmpty(goodsListModel.allParamData) || goodsListModel.allParamData.equals("null")) {
                type_name01.setVisibility(View.GONE);
                type_price.setVisibility(View.GONE);
                type_price02.setVisibility(View.GONE);
            } else {
                type_name01.setVisibility(View.VISIBLE);
                type_price.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                if (!goodsListModel.allParamData.contains("},{")) {
                    paramBean = gson.fromJson(goodsListModel.allParamData.substring(1, goodsListModel.allParamData.length() - 1), ParamBean.class);
                    type_name01.setText(paramBean.group);
                    if (paramBean.params.size() != 0) {
                        param = paramBean.params.get(0);
                        for (int i = 0; i < paramBean.params.size(); i++) {
                            list_type_tv.get(i).setText(paramBean.params.get(i));
                        }
                        if (paramBean.params.size() == 2) {
                            type_price02.setVisibility(View.VISIBLE);
                        } else if (paramBean.params.size() == 3) {
                            type_layout.setVisibility(View.VISIBLE);
                            type_price04.setVisibility(View.GONE);
                        } else if (paramBean.params.size() > 3) {
                            type_layout.setVisibility(View.VISIBLE);
                            type_price04.setVisibility(View.VISIBLE);
                        }

                    }
                } else {
                    type_name01.setVisibility(View.GONE);
                    type_price.setVisibility(View.GONE);
                    type_price02.setVisibility(View.GONE);
                    shpSelectView.setVisibility(View.VISIBLE);
                    getJsonData(goodsListModel.allParamData, shpSelectView);
//                    type_price.setText(goodsListModel.paramData.substring(1, goodsListModel.paramData.length() - 1));
                }

            }
        }
        if (goodsListModel.mailType == 0) {
            setFenQiShow(-1);
            type_name02.setVisibility(View.GONE);
            fenqi_layout.setVisibility(View.GONE);
        } else {
            type_name02.setVisibility(View.VISIBLE);
            fenqi_layout.setVisibility(View.VISIBLE);
            fenqi_3.setOnClickListener(onClickListener);
            fenqi_6.setOnClickListener(onClickListener);
            fenqi_9.setOnClickListener(onClickListener);
            fenqi_12.setOnClickListener(onClickListener);
        }

        delete_goods_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 1) {
                    number--;
                    setAllPrice(number, show_goodsnum_tv);
                }
                dissMbProgress();
            }
        });
        add_goods_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number < inventory) {
                    number++;
                    setAllPrice(number, show_goodsnum_tv);
                } else {
                    ToastUtil.showToast("库存量不足");
                }
                dissMbProgress();
            }
        });

        botoom_shopCarTtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip_classView(ShoppingCarActivity.class, extraMap, false);
                dissMbProgress();
                dialog.dismiss();
            }
        });
        addShoppingCar_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requstNetDataAddProduct();
                dissMbProgress();
                dialog.dismiss();
            }
        });
        buy_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//直接购买
                extraMap.put("allPrace", allPrice + "");
                extraMap.put("sourceType", 1);
                SureCarValue.getInstance().init();
                goodsListModel.buyNum = number;
                goodsListModel.paramData = param;
                SureCarValue.getInstance().setAddressData(goodsListModel);
                skip_classView(SureOrderActivity.class, extraMap, false, 104);
                dissMbProgress();
                dialog.dismiss();
            }
        });
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dissMbProgress();
                dialog.dismiss();
            }
        });
        dialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        dialog.show();
    }

    private double allPrice;

    private void setAllPrice(int number, TextView show_goodsnum_tv) {
        show_goodsnum_tv.setText(number + "");
        double price = number * goodsListModel.itemPrice;
        allPrice = (double) (Math.round(price * 10000)) / 10000;
//        noebuytvPrice.setText("￥" + decimalFormat.format(allPrice + mpmodityFreight));
    }

    private void setAllPrice(int number) {
        goods_num_tv.setText("x" + number);
//        float price = number * goodsListModel.itemPrice;
//        allPrice = (float) (Math.round(price * 10000)) / 10000;
//        noebuytvPrice.setText("￥" + decimalFormat.format(allPrice + mpmodityFreight));
    }

    /*设置分期显示*/
    private void setFenQiShow(int id) {
        for (int i = 0; i < fenqi_radios.size(); i++) {
            if (id == -1) {
                fenqi_radios.get(i).setBackgroundResource(R.drawable.type_grid_bg03);
                fenqi_radios.get(i).setTextColor(Color.parseColor("#dfdfdf"));
            } else {
                switch (goodsListModel.periodTime) {
                    case 3:
                        if (i > 0) {
                            fenqi_radios.get(i).setBackgroundResource(R.drawable.type_grid_bg03);
                            fenqi_radios.get(i).setTextColor(Color.parseColor("#dfdfdf"));
                            fenqi_radios.get(i).setClickable(false);
                            fenqi_radios.get(i).setEnabled(false);
                        } else {
                            setFenqiCheck(id, i);
                        }
                        break;
                    case 6:
                        if (i > 1) {
                            fenqi_radios.get(i).setBackgroundResource(R.drawable.type_grid_bg03);
                            fenqi_radios.get(i).setTextColor(Color.parseColor("#dfdfdf"));
                            fenqi_radios.get(i).setClickable(false);
                            fenqi_radios.get(i).setEnabled(false);
                        } else {
                            setFenqiCheck(id, i);
                        }
                        break;
                    case 9:
                        if (i > 2) {
                            fenqi_radios.get(i).setBackgroundResource(R.drawable.type_grid_bg03);
                            fenqi_radios.get(i).setTextColor(Color.parseColor("#dfdfdf"));
                            fenqi_radios.get(i).setClickable(false);
                            fenqi_radios.get(i).setEnabled(false);
                        } else {
                            setFenqiCheck(id, i);
                        }
                        break;
                    case 12:
                        setFenqiCheck(id, i);
                        break;
                }

            }
        }

    }

    private void setFenqiCheck(int id, int i) {
        if (i == id) {
            fenqi_radios.get(i).setChecked(true);
        } else {
            fenqi_radios.get(i).setChecked(false);
        }
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
                        Logger.e("jsonObj:--1-", jsonObj);
                        if (Tools.IsEmpty(jsonObj)) {
                            Toast.makeText(mContext,
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
//                            app.jsonHttp.getJsonObj(jsonObj, AjaxShopModel.class,
//                                    ajaxCallBack.getProductBySearch);
                            updateCarNum();
                            ToastUtil.showToast("添加成功");
                            dissMbProgress();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {//失败
                        Toast.makeText(mContext,
                                "加载数据失败", Toast.LENGTH_LONG).show();
                    }

                    break;
            }
        }
    };

    private int goodsCarNum = 0;

    /*刷新购物车数量*/
    private void updateCarNum() {
        PayFactory.getPayService().getShopCartItemNum(userToken)
                .compose(RxHelper.<GoodsCarNumBean>io_main())
                .subscribe(new Subscriber<GoodsCarNumBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsCarNumBean goodsCarNumBean) {
                        goodsCarNum = goodsCarNumBean.data;
                        botoom_shopNumber.setText(goodsCarNum + "");
                        botoom_typeshopNumber.setText(goodsCarNum + "");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 104 && resultCode == 103) {
            finish();
        }

    }

    private void setType(int id) {
        for (int i = 0; i < list_type_tv.size(); i++) {
            if (i == id) {
                list_type_tv.get(i).setBackgroundResource(R.drawable.type_grid_bg02);
            } else {
                list_type_tv.get(i).setBackgroundResource(R.drawable.type_grid_bg);
            }
        }
    }

    private JSONArray jsonArray;

    /*解析所有规格*/
    private void getJsonData(String str, ShoppingSelectView shpSelectView) {
        param = "";
        try {
            List<BigClassification> bigList = new ArrayList<>();
            jsonArray = new JSONArray(str);
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {
                ParamBean bigbean = gson.fromJson(jsonArray.get(i).toString(), ParamBean.class);
                BigClassification big = new BigClassification();
                big.setTitle(bigbean.group);
                List<BigClassification.SmallClassification> smallList = new ArrayList<>();
                param += bigbean.params.get(0) + ",";
                for (int j = 0; j < bigbean.params.size(); j++) {
                    BigClassification.SmallClassification small = new BigClassification.SmallClassification();
                    small.setName(bigbean.params.get(j));
                    smallList.add(small);
                }
                big.setList(smallList);
                bigList.add(big);
            }
            //最终数据
            shpSelectView.setData(bigList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        param = param.substring(0, param.length() - 1);
    }

    @Override
    public void onSelected(int postion, String title, String smallTitle) {
        String[] strs = param.split(",");
        String typeStr = "";
        for (int i = 0; i < strs.length; i++) {
            if (postion == i) {
                strs[i] = smallTitle;
            }
            typeStr += strs[i] + ",";
        }
        param = typeStr.substring(0, typeStr.length() - 1);

        getParamPrice();
    }

    /*获取取商品库存  价格*/
    private void getParamPrice() {
        showMbProgress("正在加载...");
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("shopId", goodsListModel.shopId);//店铺id
        params.put("itemId", goodsListModel.itemId);//商品id
        params.put("paramData", param);//商品参数

        OkGo.post(Constant.BASE_URL + "/api/happycommune/getInventory")
                .tag(this)//url请求地址
                .params(params)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ParamPriceBean paramPriceBean = gson.fromJson(s, ParamPriceBean.class);

                        if (paramPriceBean.retCode == 1) {
                            itemPrice = paramPriceBean.data.price;
                            dialog_shop_price.setText(itemPrice + "康币");
                            inventory = paramPriceBean.data.inventory;
                            shop_kc.setText("库存 " + inventory + unit);
                        } else {
                            ToastUtil.showToast(paramPriceBean.message);
                        }
                        dissMbProgress();
                    }
                });

    }
}
