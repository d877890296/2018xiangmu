package com.xfkc.caimai.home.recruitmenthall;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.RecruiHallBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;
import com.xfkc.caimai.pay.PayWAyActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 招募详情
 */
public class RecruContentActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.dianpu_title)
    TextView dianpuTitle;
    @Bind(R.id.a_type)
    TextView aType;
    @Bind(R.id.a_type_des)
    TextView aTypeDes;
    @Bind(R.id.a_type_price)
    TextView aTypePrice;
    @Bind(R.id.a_rb)
    RadioButton aRb;
    @Bind(R.id.b_type)
    TextView bType;
    @Bind(R.id.b_type_des)
    TextView bTypeDes;
    @Bind(R.id.b_type_price)
    TextView bTypePrice;
    @Bind(R.id.b_rb)
    RadioButton bRb;
    @Bind(R.id.c_type)
    TextView cType;
    @Bind(R.id.c_type_des)
    TextView cTypeDes;
    @Bind(R.id.c_type_price)
    TextView cTypePrice;
    @Bind(R.id.c_rb)
    RadioButton cRb;
    @Bind(R.id.commit)
    StateButton commit;
    @Bind(R.id.a_layout)
    LinearLayout aLayout;
    @Bind(R.id.b_layout)
    LinearLayout bLayout;
    @Bind(R.id.c_layout)
    LinearLayout cLayout;
    private ArrayList<RadioButton> list_radio = new ArrayList<>();
    private String shopId;
    private String partnerType = "A";
    private double a_price = 0, b_price = 0, c_price = 0,reall_price= 0;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_recru_content;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("招募详情");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        list_radio.add(aRb);
        list_radio.add(bRb);
        list_radio.add(cRb);
        shopId = getIntent().getStringExtra("shopId");

    }

    @Override
    protected void loadData() {
        PayFactory.getPayService()
                .recruitmentHall(pageNum, pageSize, token, "2", shopId)
                .compose(RxHelper.<RecruiHallBean>io_main())
                .subscribe(new ProgressSubscriber<RecruiHallBean>(this) {
                    @Override
                    public void onNext(RecruiHallBean recruiHallBean) {
                        if (recruiHallBean.data.list != null && recruiHallBean.data.list.size() != 0) {
                            RecruiHallBean.DataBean.ListBean listBean = recruiHallBean.data.list.get(0);
                            dianpuTitle.setText(listBean.shopName);
                            for (int i = 0; i < listBean.inrecruiList.size(); i++) {
                                if (i == 0) {
                                    aLayout.setVisibility(View.VISIBLE);
                                    aType.setText(listBean.inrecruiList.get(i).partnerType + "类事业合伙人:" + listBean.inrecruiList.get(i).joinPersonNumber + "(人)/" + listBean.inrecruiList.get(i).personNumber);
                                    a_price = listBean.inrecruiList.get(i).kangbiCount ;
                                    reall_price = a_price;
                                    aTypePrice.setText("￥" + a_price);
                                    showType(listBean.inrecruiList.get(i),aTypeDes);
                                } else if (i == 1) {
                                    bLayout.setVisibility(View.VISIBLE);
                                    bType.setText(listBean.inrecruiList.get(i).partnerType + "类事业合伙人:" + listBean.inrecruiList.get(i).joinPersonNumber + "(人)/" + listBean.inrecruiList.get(i).personNumber);
                                    b_price = listBean.inrecruiList.get(i).kangbiCount ;
                                    bTypePrice.setText("￥" + b_price);
                                    showType(listBean.inrecruiList.get(i),bTypeDes);
                                } else if (i == 2) {
                                    cLayout.setVisibility(View.VISIBLE);
                                    c_price = listBean.inrecruiList.get(i).kangbiCount ;
                                    cTypePrice.setText("￥" + c_price);
                                    cType.setText(listBean.inrecruiList.get(i).partnerType + "类事业合伙人:" + listBean.inrecruiList.get(i).joinPersonNumber + "(人)/" + listBean.inrecruiList.get(i).personNumber);
                                    showType(listBean.inrecruiList.get(i),cTypeDes);
                                }
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.toolbar_left_img, R.id.commit, R.id.a_rb, R.id.b_rb, R.id.c_rb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.commit:
                commit();
                break;
            case R.id.a_rb:
                partnerType = "A";
                reall_price = a_price;
                setRadioButton(0);
                break;
            case R.id.b_rb:
                partnerType = "B";
                reall_price = b_price;
                setRadioButton(1);
                break;
            case R.id.c_rb:
                partnerType = "C";
                reall_price = c_price;
                setRadioButton(2);
                break;
        }
    }

    /*添加*/
    private void commit() {
        extraMap.put("token",token);
        extraMap.put("shopId",shopId);
        extraMap.put("partnerType",partnerType);
        extraMap.put("reall_price",reall_price+"");
        skip_classView(PayWAyActivity.class,extraMap,true);

    }


    //设置radiobutton  选中
    private void setRadioButton(int number) {
        for (int i = 0; i < list_radio.size(); i++) {
            if (i == number) {
                list_radio.get(i).setChecked(true);
            } else {
                list_radio.get(i).setChecked(false);
            }
        }
    }

    private void showType(RecruiHallBean.DataBean.ListBean.InrecruiListBean inrecruiListBean, TextView TypeDes){
        if (inrecruiListBean.type == 0){//按百分比
            switch (inrecruiListBean.welfareUnit){
                case 1://月
                    TypeDes.setText("加入后每个月可获得店铺年营业额" +inrecruiListBean.rate+"%的康币分红");
                    break;
                case 2://季度
                    TypeDes.setText("加入后每个季度可获得店铺年营业额" +inrecruiListBean.rate+"%的康币分红");
                    break;
                case 3://年
                    TypeDes.setText("加入后每年可获得店铺年营业额" +inrecruiListBean.rate+"%的康币分红");
                    break;
            }
        }else if (inrecruiListBean.type == 1){//按定额
            String comKangbi ="0";
            if(!Tools.IsEmpty(inrecruiListBean.comKangbi)){
                comKangbi = inrecruiListBean.comKangbi;
            }
            switch (inrecruiListBean.welfareUnit){
                case 1://月
                    TypeDes.setText("加入后每月可获得"+comKangbi+"个康币分红");
                    break;
                case 2://季度
                    TypeDes.setText("加入后每季度可获得"+comKangbi+"个康币分红");
                    break;
                case 3://年
                    TypeDes.setText("加入后每年可获得"+comKangbi+"个康币分红");
                    break;
            }

        }
    }
}
