package com.goods.sortlsitview;

/**
 * Created by 10835 on 2018/8/30.
 */

<<<<<<< HEAD
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;


import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

=======
>>>>>>> d6bf3f6255991080d9b8b47c2f8cf2f3aceb187e
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
<<<<<<< HEAD
=======
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
>>>>>>> d6bf3f6255991080d9b8b47c2f8cf2f3aceb187e
import android.widget.TextView;
import android.widget.Toast;

import com.dev.customview.ClearEditText;
<<<<<<< HEAD
import com.goods.netrequst.NetRequstAjaxCallBack;
import com.goods.netrequst.PostRequst;
=======
>>>>>>> d6bf3f6255991080d9b8b47c2f8cf2f3aceb187e
import com.goods.sortlsitview.SideBar.OnTouchingLetterChangedListener;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.json.CommonConvert;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
<<<<<<< HEAD
=======
import com.xfkc.caimai.bean.AllShopsModel;
>>>>>>> d6bf3f6255991080d9b8b47c2f8cf2f3aceb187e
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.config.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import static com.goods.netrequst.PostRequst.UPSUCCESS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 为了使用者方便，一进入主布局就能看懂大致思路的指导，免得花太多时间去看源码
 * <p>
 * 根据需要不懂得请资讯QQ：1083573260
 * <p>
 * <p>
 * 在搜索时如果想按照全拼音，拼音首字母，汉字模糊搜索请自行添加字段。自己改之
 * <p>
 * <p>
 * 这是一个带索引的排序的listView 这个是数据源 R.array.date
 * <p>
 * filledData(getResources().getStringArray(R.array.date));
 * <p>
 * 设置排序的横标题，这个可以自己改成你想设置的内容
 * sortModel.setSortLetters(sortString.toUpperCase());
 *
 * @author Lyy
 */
public class GoodsChooseCityActivity extends BaseActivity {
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;
    private View headView;
    private PostRequst postRequst;
    private NetRequstAjaxCallBack ajaxCallBack;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    private TextView firstShopName;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected int getLayoutResource() {
        setSoftInputMode();
        hindKey();



        return R.layout.goods_choosecity_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        postRequst = new PostRequst(handler);
        ajaxCallBack = new NetRequstAjaxCallBack(mContext);
        ajaxCallBack.setOnNetRequstAjaxCallBack(onNetRequstAjaxCallBack);
        initViews();
    }

    @Override
    protected void loadData() {


    }

    private void initViews() {
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setVisibility(View.GONE);
        back_btn.setOnClickListener(onClickListener);
        topbar_img_title = (TextView) findViewById(R.id.topbar_img_title);
        topbar_img_title.setVisibility(View.GONE);
        topbar_title = (TextView) findViewById(R.id.topbar_title);
        topbar_title.setVisibility(View.VISIBLE);
        topbar_title.setText("选择店铺");

        other_btn = (ImageButton) findViewById(R.id.other_btn);
        other_btn.setVisibility(View.GONE);
        other_morbtn = (Button) findViewById(R.id.other_morbtn);
        other_morbtn.setVisibility(View.VISIBLE);
        other_morbtn.setText("取消");
        other_morbtn.setOnClickListener(onClickListener);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);
        sideBar.setTextSize((int) Tools.dip2px(mContext, 10));

        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
                if (position == 0) {
                    return;
                }
                Toast.makeText(getApplication(),
                        ((SortModel) adapter.getItem(position - 1)).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        headView = getLayoutInflater().inflate(R.layout.goods_chooseshop_head_layout, null);
        firstShopName=(TextView)headView.findViewById(R.id.firstShopName);
        sortListView.addHeaderView(headView);
        SourceDateList=new ArrayList<SortModel>();

//        SourceDateList = filledData(getResources().getStringArray(R.array.date));
//
//        // 根据a-z进行排序源数据
//        Collections.sort(SourceDateList, pinyinComparator);
//        adapter = new SortAdapter(this, SourceDateList);
//        sortListView.setAdapter(adapter);

        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        requstNetData();
    }

    /****
     *
     * 请求数据
     */
    public void requstNetData() {
<<<<<<< HEAD

        showMbProgress("数据加载中");
        userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
=======
<<<<<<< HEAD
        userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        GoodsKey goodsKey = new GoodsKey();
        goodsKey.token=userToken;
        goodsKey.longitude = app.longitude;
        goodsKey.latitude =  app.latitude;
        Log.e("---", userToken+"---"+app.longitude+"---"+app.latitude);
=======
        userToken= SharedPrefUtil.get(mContext, SharedPref.TOKEN);
>>>>>>> d6bf3f6255991080d9b8b47c2f8cf2f3aceb187e
        GoodsKey goodsKey = new GoodsKey();
        goodsKey.token = userToken;
        goodsKey.longitude = "115.569030";
<<<<<<< HEAD
        goodsKey.latitude = "33.9996937";
        postRequst.getAllShopsAndNearshop(handler, goodsKey);

//        PayFactory.getPayService()
//                .getAllShopsAndNearshop(goodsKey)
//                .compose(RxHelper.<AllShopsModel>io_main())
//                .subscribe(new ProgressSubscriber<AllShopsModel>(this) {
//                    @Override
//                    public void onNext(AllShopsModel loginInfo) {
////                        SharedPrefUtil.put(mContext, SharedPref.TOKEN,loginInfo.data);
////                        skip_classView(MainActivity.class,extraMap,true);
//
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//
//
//                    }
//                });
=======
        goodsKey.latitude =  "33.9996937";

>>>>>>> 52fdb13965afef9f796a9d9785c020722b9808a8
        PayFactory.getPayService()
//                .getAllShopsAndNearshop(app.latitude,app.longitude,userToken)
                .getAllShopsAndNearshop(goodsKey)
                .compose(RxHelper.<AllShopsModel>io_main())
                .subscribe(new ProgressSubscriber<AllShopsModel>(this) {
                    @Override
                    public void onNext(AllShopsModel loginInfo) {
//                        SharedPrefUtil.put(mContext, SharedPref.TOKEN,loginInfo.data);
//                        skip_classView(MainActivity.class,extraMap,true);

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);


                    }
                });
>>>>>>> d6bf3f6255991080d9b8b47c2f8cf2f3aceb187e
    }


    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

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

            ArrayList<SortModel> sortData=(ArrayList)object;
            for (int i=1;i<sortData.size();i++){
                SourceDateList.add( sortData.get(i));
            }
            firstShopName.setText(sortData.get(0).getName());
            // 根据a-z进行排序源数据
            Collections.sort(SourceDateList, pinyinComparator);
            adapter = new SortAdapter(mContext, SourceDateList);
            sortListView.setAdapter(adapter);


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
                            jsonObj= convert.getString("data");
                            app.jsonHttp.getJsonObj(jsonObj, AjaxShopModel.class,
                                    ajaxCallBack.getAllShopsAndNearshopCallBack);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {//失败

                    }

                    break;
            }
        }
    };

}



