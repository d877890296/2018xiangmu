package com.xfkc.caimai.home.mineprofit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.ProfitListBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class ProfitListActivity extends BaseActivity {

    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_title_image)
    ImageView toolbarTitleImage;
    @Bind(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @Bind(R.id.toolbar_right_img)
    ImageView toolbarRightImg;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.listview)
    CustomListView listview;

    private String type = "1";
    private ProfitListAdapter profitListAdapter;
    private ArrayList<ProfitListBean.DataBean.ListBean> list = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_profit_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        token = SharedPrefUtil.get(this, SharedPref.TOKEN);

        type = getIntent().getStringExtra("type");

        Log.e("type===",type+"");
        if (type.equals("1")) {
            toolbarTitle.setText("情怀链收益明细");
        } else if (type.equals("2")) {
            toolbarTitle.setText("幸福公社收益明细");
        }

        profitListAdapter = new ProfitListAdapter(this);
        profitListAdapter.setData(list);
        listview.setAdapter(profitListAdapter);

    }

    @Override
    protected void loadData() {

        PayFactory.getPayService().findMyRevenueDet(token, pageNum, pageSize, type)
                .compose(RxHelper.<ProfitListBean>io_main())
                .subscribe(new ProgressSubscriber<ProfitListBean>(this) {
                    @Override
                    public void onNext(ProfitListBean profitListBean) {
                        if (profitListBean.data.list!=null && profitListBean.data.list.size()!=0){
                            list.addAll(profitListBean.data.list);
                            profitListAdapter.setData(list);
                        }
                    }
                });


    }


    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }
}
