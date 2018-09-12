package com.goods.mineOrderforgoods;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.customview.MyListView;
import com.goods.city.GoodsValue;
import com.goods.details.GoodsDetailsActivity;
import com.goods.netrequst.NetRequstAjaxCallBack;
import com.goods.netrequst.PostRequst;
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
 * 物流详情
 * Created by 10835 on 2018/9/12.
 */

public class OrderforgoodsLogisticsActivity extends BaseActivity {

    private List<LogisticsModel> data;
    private MyListView timeline_list;
    private GoodsLogisticsAdapter goodsLogisticsAdapter;
    private String content[] = {"[北京市] 您的订单正在配送途中，请您准备签收（配送员：李元，电话：010-718980或者13141199287），感谢你的耐心等待", "你的订单已经达到[北京站]",
            "您提交了订单，请等待系统确认"};
    private String time[] = {"2018-03-02 13:44:23", "2018-03-01 10:24:13", "2018-03-01 08:20:13"};
    private String orderNum;
    private PostRequst postRequst;
    private NetRequstAjaxCallBack ajaxCallBack;

    @Override
    protected int getLayoutResource() {

        return R.layout.gd_orderforgoodslogisticsactivity_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        deaultDataInit();
        viewInit();
        dataInit();
    }

    public void deaultDataInit() {
        // TODO Auto-generated method stub
        orderNum = getIntent().getStringExtra("orderNum");
        data = new ArrayList<LogisticsModel>();
        postRequst = new PostRequst(handler);
        ajaxCallBack = new NetRequstAjaxCallBack(mContext);
        ajaxCallBack.setOnNetRequstAjaxCallBack(onNetRequstAjaxCallBack);

    }

    public void viewInit() {
        back_btn = (ImageButton) findViewById(R.id.back_img_btn);
        topbar_title = (TextView) findViewById(R.id.text_title_content);
        topbar_title.setText("查看物流");
        back_btn.setOnClickListener(onClickListener);

        timeline_list = (MyListView) findViewById(R.id.timeline_list);
        goodsLogisticsAdapter = new GoodsLogisticsAdapter(mContext);

        queryLogisticsInfo(orderNum);
    }

    public void dataInit() {
        for (int i = 0; i < content.length; i++) {
            LogisticsModel model = new LogisticsModel();
            model.setContentInfo(content[i]);
            model.setTime(time[i]);
            data.add(model);
        }
        goodsLogisticsAdapter.setData(data);
        timeline_list.setAdapter(goodsLogisticsAdapter);
    }

    /***
     * 查序物流
     * @param orderNum
     */
    public void queryLogisticsInfo(String orderNum) {
        String userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        GoodsKey goodsKey = new GoodsKey();
        goodsKey.token = userToken;
        goodsKey.orderNum = orderNum + "";
        postRequst.queryLogisticsInfo(handler, goodsKey);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.back_img_btn:
                    backHistory(-1, true, false, extraMap);
                    break;
                case R.id.orderdetails_liner:
                    skip_classView(GoodsDetailsActivity.class, extraMap, false, -1);

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
                        Log.e("-------", jsonObj);
                        if (Tools.IsEmpty(jsonObj)) {
                            android.widget.Toast.makeText(mContext,
                                    "数据错误", Toast.LENGTH_LONG).show();
                            nodataview_textview.setVisibility(View.VISIBLE);
                            nodataview_textview.setText("暂无数据");

                            return;
                        }

//                            app.jsonHttp.getJsonObj(jsonObj, AjaxShopModel.class,
                        //ajaxCallBack.getMyOrder);


                        break;

                    }
            }
        }

    };

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backHistory(-1, true, false, extraMap);
            return true;
        }
        return false;
    }
}
