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
import android.widget.Toast;

import com.goods.city.GoodsCityListAdapter.OnListViewClickLinstener;
import com.goods.details.GoodsDetailsActivity;
import com.goods.model.GoodsModel;
import com.goods.netrequst.GetJson;
import com.goods.netrequst.Logger;
import com.goods.netrequst.NetRequst;
import com.goods.netrequst.NetRequstAjaxCallBack;
import com.goods.netrequst.PostRequst;
import com.goods.sortlsitview.AjaxShopModel;
import com.hyf.tdlibrary.utils.Tools;
import com.json.CommonConvert;
import com.recycle.view.MyRecyclerView;
import com.refushView.RefreshLayout;
import com.refushView.holder.DefineBAGRefreshWithLoadView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.goods.netrequst.PostRequst.UPSUCCESS;

/**
 * Created by 10835 on 2018/8/20.
 *
 * 商城分类列表
 */

public class GoodsStyleActivity extends BaseActivity  {

    private ListView goods_styleListView,goodsListView;
    private GoodsStyleAdapter goodsStyleAdapter;
    private List<GoodsListModel> goodsData;

    public List<GoodsStyleModel> data;
    private GoodsStyleContentAdapter goodsStyleContentAdapter;

    private NetRequst netRequst;
    private NetRequstAjaxCallBack ajaxCallBack;

  private String style[]={"水果","蔬菜"};

    private String content[]={"水果2","蔬2菜","水fs果2","蔬2菜","水果csa2"};

    @Override
    protected int getLayoutResource() {
        return R.layout.goods_style_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        goodsData=new ArrayList<GoodsListModel>();
        netRequst = NetRequst.getInstance();
        ajaxCallBack = new NetRequstAjaxCallBack(mContext);
        ajaxCallBack.setOnNetRequstAjaxCallBack(onNetRequstAjaxCallBack);
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
        progress_liner = (LinearLayout) findViewById(R.id.progress_liner);
        nodataview_textview = (TextView) findViewById(R.id.nodataview_textview);


        goods_styleListView=(ListView) findViewById(R.id.goods_styleListView);
        goodsStyleAdapter.setData(goodsData);
        goods_styleListView.setAdapter(goodsStyleAdapter);
        goods_styleListView.setOnItemClickListener(onItemClickListener1);


        goodsListView=(ListView) findViewById(R.id.goodsListView);
        goodsStyleContentAdapter.setData(data);
        goodsListView.setAdapter(goodsStyleContentAdapter);
        goodsListView.setOnItemClickListener(onItemClickListener);

        requstNetData();
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

    public void  requstNetData(){
        showMbProgress("数据加载中");
        netRequst.getAllCategory(ajaxCallBack.getAllCategory);
    }
    private AdapterView.OnItemClickListener onItemClickListener1=new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                goodsStyleAdapter.setCurrentIndex(position);

        }
    };

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




    private NetRequstAjaxCallBack.OnNetRequstAjaxCallBack onNetRequstAjaxCallBack = new NetRequstAjaxCallBack.OnNetRequstAjaxCallBack() {

        @Override
        public void MsgCallBack(boolean isSuccess, String errorMsg, Object object) {
            // TODO Auto-generated method stub
            dissMbProgress();
            if (isSuccess){
                ArrayList<GoodsListModel> shopsList=(ArrayList)object;
                goodsData.addAll(shopsList);
                goodsStyleAdapter.setData(goodsData);
                nodataview_textview.setVisibility(View.GONE);
            }else{
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

                            CommonConvert convert = new CommonConvert(obj);
                            jsonObj = convert.getString("data");
                            Logger.e("jsonObj:---",jsonObj);
                            app.jsonHttp.getJsonObj(jsonObj, AjaxShopModel.class,
                                    ajaxCallBack.getAllCategory);
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
