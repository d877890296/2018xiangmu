package com.goods.shoppingcar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.customview.CustomListView;
import com.dev.customview.MyToast;
import com.goods.city.GoodsListModel;
import com.goods.city.GoodsValue;
import com.goods.details.GoodsDetailsActivity;
import com.goods.netrequst.Logger;
import com.goods.netrequst.PostRequst;
import com.goods.order.SureOrderActivity;
import com.goods.shoppingcar.ShoppingCarAdapter.OnCheckBoxBack;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.config.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.goods.netrequst.PostRequst.UPSUCCESS;

/****
 * 购物车
 *
 * @author
 *
 */
public class ShoppingCarActivity extends BaseActivity {
    private String goodsStoreId;
    private CustomListView mine_myListView;
    private ShoppingCarAdapter tradeIntegralAdapter;
    private List<GoodsListModel> data;

    public List<String> deleteArray;

    private CheckBox seletall_btn;
    private boolean isCheckAll;
    private TextView allPrace_textView, count_textView;
    private double allPrace = 0;

    private boolean isEdit;
    private LinearLayout showcountinfo_liner;

    private PostRequst postRequst;
    private ArrayList<Integer> list_shopid = new ArrayList<>();

    public void deaultDataInit() {
        // TODO Auto-generated method stub
        postRequst = new PostRequst(handler);

        SureCarValue.getInstance().init();
        data = new ArrayList<GoodsListModel>();
        deleteArray = new ArrayList<String>();


    }


    @Override
    protected int getLayoutResource() {
        return R.layout.gd_shoppingcar_activity_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        deaultDataInit();

        viewInit();
        dataInit();
    }

    @Override
    protected void loadData() {

    }

    public void viewInit() {

        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setVisibility(View.VISIBLE);
        TextView topbar_img_title = (TextView) findViewById(R.id.topbar_img_title);
        topbar_img_title.setVisibility(View.GONE);
        topbar_title = (TextView) findViewById(R.id.topbar_title);
        topbar_title.setVisibility(View.VISIBLE);
        topbar_title.setText("购物车");
        other_btn = (ImageButton) findViewById(R.id.other_btn);
        other_btn.setVisibility(View.GONE);
        other_morbtn = (Button) findViewById(R.id.other_morbtn);
        other_morbtn.setVisibility(View.GONE);
        other_morbtn.setText("编辑");
        back_btn.setOnClickListener(onClickListener);
        other_morbtn.setOnClickListener(onClickListener);

        nodataview_textview = (TextView) findViewById(R.id.nodataview_textview);

        mine_myListView = (CustomListView) findViewById(R.id.mine_myListView);

        mine_myListView.setOnItemClickListener(onItemClickListener);

        showcountinfo_liner = (LinearLayout) findViewById(R.id.showcountinfo_liner);
        allPrace_textView = (TextView) findViewById(R.id.allPrace_textView);
        count_textView = (TextView) findViewById(R.id.count_textView);

        seletall_btn = (CheckBox) findViewById(R.id.checkBox);
        seletall_btn.setOnClickListener(onClickListener);
        count_textView.setOnClickListener(onClickListener);
    }

    private boolean isRemove;

    public void dataInit() {

        // data = ShoppingCar.get().getCarData();
        // if (data != null && data.size() != 0) {
        // tradeIntegralAdapter = new ShoppingCarAdapter(mContext);
        // tradeIntegralAdapter.setOnCheckBoxBack(onCheckBoxBack);
        // tradeIntegralAdapter.setData(data);
        // mine_myListView.setAdapter(tradeIntegralAdapter);
        // } else {
        tradeIntegralAdapter = new ShoppingCarAdapter(mContext);
        tradeIntegralAdapter.setOnCheckBoxBack(onCheckBoxBack);
        tradeIntegralAdapter.setData(data);
        mine_myListView.setAdapter(tradeIntegralAdapter);
        // }

        isRemove = false;
//        showMbProgress("数据加载中...");
//		app.netRequst.shoppingCartsDtasRequst(acc.getUserId(), goodsStoreId, "0", "20",
//				netRequstAjaxCallBack.shopingCarDataCallback);
        requstNetData(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dissMbProgress();
            }
        }, 2000);
    }


    private boolean isClear;

    private OnCheckBoxBack onCheckBoxBack = new OnCheckBoxBack() {

        @Override
        public void backState(int index, String position, boolean check) {
            // TODO Auto-generated method stub

            SureCarValue.getInstance().setAddressData(data.get(index));
            if (isRepeateData(position) == false) {
                deleteArray.add(position);
                list_shopid .add(data.get(index).shopId);
            } else {
                int id_ = isRepeate(position);
                if (id_ != -1) {
                    list_shopid .remove(id_);
                    deleteArray.remove(id_);
                }
            }
            tradeIntegralAdapter.setDeleteArray(deleteArray);
            // 选择情况
            statisticsChoose();
        }

        @Override
        public void backShopState(int position, String id, boolean check) {
            // TODO Auto-generated method stub
            isClear = true;
            GoodsListModel model = data.get(position);
            String shopGoodsNumber = model.buyNum + "";
            if (Tools.IsEmpty(shopGoodsNumber)) {
                shopGoodsNumber = "0";
            }
            int num = Integer.parseInt(shopGoodsNumber);
            if (check) {// 添加
                num++;
                showMbProgress("数据加载中...");
                requstNetDataEditNum(model, num + "");
                // 添加购物车
//				app.netRequst.shoppingCartSaveRequst(acc.getUserId(), goodsStoreId, id, "1", "", "",
//						netRequstAjaxCallBack.shopingCarAddCallback);
            } else {// 减少
                if (num > 0) {
                    showMbProgress("数据加载中...");
                    num--;
                    requstNetDataEditNum(model, num + "");
                }


//				app.netRequst.shoppingCartreduceRequst(acc.getUserId(), goodsStoreId, id,
//						netRequstAjaxCallBack.shopingCarRemoveCallback);
            }

        }

    };

    /****
     *
     * 请求数据
     */
    public void requstNetDataEditNum(GoodsListModel model, String num) {
        this.requstType = 1;
        userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        GoodsKey goodsKey = new GoodsKey();
        goodsKey.token = userToken;
        goodsKey.shopId = model.shopId + "";
        goodsKey.itemId = model.itemId + "";
        goodsKey.buyNum = num;
        postRequst.editCartItemsNum(handler, goodsKey);


    }

    /***
     *
     * 是否有重复对象
     *
     * @param ldid
     * @return
     */
    public boolean isRepeateData(String ldid) {
        for (String tempLdid : deleteArray) {
            if (tempLdid.equals(ldid)) {
                return true;
            }
        }
        return false;
    }

    /***
     *
     * 是否有重复对象
     *
     * @param ldid
     * @return
     */
    public int isRepeate(String ldid) {
        int count = deleteArray.size();
        for (int i = 0; i < count; i++) {
            String tempLdid = deleteArray.get(i);
            if (tempLdid.equals(ldid)) {
                return i;
            }
        }
        return -1;
    }

    /***
     * 统计选择情况
     *
     */
    public void statisticsChoose() {
        if (deleteArray == null) {
            seletall_btn.setText(" 全选");
        }
        int count = deleteArray.size();
        if (count >= 0 && count < data.size()) {
            seletall_btn.setText(" 全选");
            seletall_btn.setChecked(false);
            isCheckAll = false;
        } else if (count == data.size()) {
            seletall_btn.setText(" 全选");
            seletall_btn.setChecked(true);
            isCheckAll = true;
        }
        countParce();
    }

    public void countParce() {
        allPrace = 0;
        String ids = "";
        for (int i = 0; i < deleteArray.size(); i++) {
            String id = deleteArray.get(i);
            ids += id + ",";
            for (int j = 0; j < data.size(); j++) {
                String tempId = data.get(j).id + "";
                if (id.equals(tempId)) {

                    int num = data.get(j).buyNum;

                    double prace = data.get(j).itemPrice * num;
                    allPrace += prace;
                }

            }
        }

        if (isEdit == false) {
            allPrace_textView.setText(allPrace + "康币");
            count_textView.setText("结算(" + deleteArray.size() + ")");
        } else {
            isRemove = true;

            showcountinfo_liner.setVisibility(View.INVISIBLE);
            count_textView.setText("删除(" + deleteArray.size() + ")");

        }

    }

    public String[] getIds() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < deleteArray.size(); i++) {
            String id = deleteArray.get(i) + ",";
            sb.append(id);

        }
        String str = sb.toString();
        String ids = str.substring(0, str.length() - 1);
        return ids.split(",");

    }

    public String getIdString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < deleteArray.size(); i++) {
            String id = deleteArray.get(i) + ",";
            sb.append(id);

        }
        String str = sb.toString();
        String ids = str.substring(0, str.length() - 1);
        return ids;

    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // TODO Auto-generated method stub

            GoodsValue.getInstance().setGoodsListModel(data.get(arg2 - 1));
            skip_classView(GoodsDetailsActivity.class, extraMap, false);
        }
    };

    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.back_btn:
                    backHistory(-1, true, false, extraMap);
                    break;
                case R.id.other_morbtn:
                    if (isEdit == false) {
                        isEdit = true;
                        other_morbtn.setText("完成");
                        showcountinfo_liner.setVisibility(View.INVISIBLE);
                        count_textView.setText("删除(" + deleteArray.size() + ")");
                    } else {
                        isEdit = false;
                        other_morbtn.setText("编辑");
                        count_textView.setText("结算(" + deleteArray.size() + ")");
                        showcountinfo_liner.setVisibility(View.VISIBLE);
                    }

                    break;
                case R.id.checkBox:
                    if (isCheckAll == false) {
                        deleteArray.clear();
                        for (int i = 0; i < data.size(); i++) {
                            deleteArray.add(data.get(i).id + "");
                        }
                        SureCarValue.getInstance().setAllData(data);
                    } else {
                        SureCarValue.getInstance().removeAllData();
                        deleteArray.clear();
                    }

                    tradeIntegralAdapter.setDeleteArray(deleteArray);
                    // 选择情况
                    statisticsChoose();
                    break;
                case R.id.count_textView:
                    if (isEdit) {
                        isClear = true;
                        showMbProgress("移除中...");

                        String[] ids = getIds();
                        for (int i = 0; i < ids.length; i++) {
                            String id = ids[i];
                            for (int j = 0; j < deleteArray.size(); j++) {
                                String tempid = deleteArray.get(j);
                                if (id.equals(tempid)) {
                                    deleteArray.remove(j);
                                }
                            }

                        }
                        countParce();
                        allPrace_textView.setText(allPrace + "康币");
                        seletall_btn.setText(" 全选");
                        seletall_btn.setChecked(false);
                        isCheckAll = false;
                        tradeIntegralAdapter.setDeleteArray(deleteArray);
                    } else {
                        if (allPrace == 0) {
                            MyToast.showMyToast(mContext, "请选择商品", -1);
                        } else {
//                            if (ischeck_shopId){
//                                ToastUtil.showToast("请单个店铺购买商品!");
//                            }else {
                                extraMap.put("allPrace", allPrace + "");
                                skip_classView(SureOrderActivity.class, extraMap, false);
//                            }
                        }

                    }

                    isEdit = false;
                    other_morbtn.setText("编辑");
                    count_textView.setText("结算(" + deleteArray.size() + ")");
                    showcountinfo_liner.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }

        }

    };

    int requstType = 0;

    /****
     *
     * 请求数据
     */
    public void requstNetData(int type) {
        this.requstType = type;
        if (app.shopModel != null) {
            userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
            GoodsKey goodsKey = new GoodsKey();
            goodsKey.token = userToken;
            postRequst.getShopCart(handler, goodsKey);

        }
    }


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
                            nodataview_textview.setVisibility(View.VISIBLE);
                            nodataview_textview.setText("购物车空空如也，赶快去商城添加吧！");

                            return;
                        }
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(jsonObj);
                            //   CommonConvert convert = new CommonConvert(obj);
                            //   jsonObj = convert.getString("data");

                            if (requstType == 0) {
                                JSONArray jsonObjData = obj.getJSONArray("data");
                                Logger.e("jsonObj:---", jsonObj);
                                analysisData(jsonObjData);
                            } else {
                                requstNetData(0);
                                deleteArray.clear();

                                tradeIntegralAdapter.setDeleteArray(deleteArray);
                                // 选择情况
                                statisticsChoose();
                                dissMbProgress();
                            }
//							app.jsonHttp.getJsonObj(jsonObj, AjaxShopModel.class,
//									ajaxCallBack.getShopCart);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {//失败

                        nodataview_textview.setVisibility(View.VISIBLE);
                        nodataview_textview.setText("购物车空空如也，赶快去商城添加吧！");
                    }

                    break;
            }
        }
    };

    //15295180301
    public void analysisData(JSONArray jsonObjData) {
        if (jsonObjData != null && jsonObjData.length() > 0) {
            data.clear();
        }
        for (int j = 0; j < jsonObjData.length(); j++) {
            try {
                JSONObject jsonObject = jsonObjData.getJSONObject(j);
                String shopName = jsonObject.getString("shopName");
                JSONArray woquArr = jsonObject.getJSONArray("items");
                for (int i = 0; i < woquArr.length(); i++) {
                    JSONObject obj = woquArr.getJSONObject(i);

                    //   ShoppingCarModel model = new ShoppingCarModel();

                    GoodsListModel model = new GoodsListModel();
                    model.shopName = shopName;

                    model.id = obj.getInt("id");
                    model.itemId = obj.getInt("itemId");
                    model.title = obj.getString("title");
                    model.sellPoint = obj.getString("sellPoint");
                    //model.category=obj.getString("category");
                    model.pic = obj.getString("pic");
                    model.status = obj.getInt("status");

//                    "createTime": null,
//                     "updateTime": null,
                    model.scid = obj.getInt("scid");
                    model.mailType = obj.getInt("mailType");
                    model.itemType = obj.getInt("itemType");
                    model.itemPrice = obj.getInt("itemPrice");

                    model.allParamData = obj.getString("allParamData");
                    model.paramData = obj.getString("paramData");
                    model.buyNum = obj.getInt("buyNum");
                    model.shopId = obj.getInt("shopId");
//                    model.mailPrice = obj.getDouble("mailPrice");
                    model.inventory = obj.getInt("inventory");
                    model.receiveProvince = obj.getString("receiveProvince");
                    model.unit = obj.getString("unit");
                    model.cid = obj.getInt("cid");
                    model.topCategoryId = obj.getInt("topCategoryId");
                    model.periodTime = obj.getInt("periodTime");
                    model.backSelf = obj.getDouble("backSelf");
                    model.saleType = obj.getInt("saleType");
                    model.backType = obj.getInt("backType");
                    model.firstBack = obj.getDouble("firstBack");
                    model.secondBack = obj.getDouble("secondBack");
                    model.useType = obj.getInt("useType");
                    model.content = obj.getString("content");

                    data.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (data.size() == 0) {
            nodataview_textview.setVisibility(View.VISIBLE);
            nodataview_textview.setText("购物车空空如也，赶快去商城添加吧！");
        } else {
            nodataview_textview.setVisibility(View.GONE);
            tradeIntegralAdapter.setData(data);
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

//    private

}
